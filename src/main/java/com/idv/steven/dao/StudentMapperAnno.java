package com.idv.steven.dao;

import com.idv.steven.vo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapperAnno {

    @Results(id = "studentMap", value = {
            @Result(property = "stuId", column = "sid", id = true),
            @Result(property = "stuNum", column = "stu_num"),
            @Result(property = "stuName", column = "stu_name"),
            @Result(property = "stuGender", column = "stu_gender"),
            @Result(property = "stuAge", column = "stu_age")
    })

    // 插入學生記錄
    @Insert("insert into tb_students(stu_num, stu_name, stu_gender, stu_age) " +
            "values(#{stuNum}, #{stuName}, #{stuGender}, #{stuAge})")
    @Options(useGeneratedKeys = true, keyProperty = "stuId")
    int insertStudent(Student student);

    // 刪除學生記錄
    @Delete("delete from tb_students where stu_num = #{stuNum}")
    int deleteStudent(String stuNum);

    // 更新學生記錄
    @Update("update tb_students " +
            "set stu_name = #{stuName}, stu_gender = #{stuGender}, stu_age = #{stuAge} " +
            "where stu_num = #{stuNum}")
    int updateStudent(Student student);

    // 查詢所有學生記錄
    @Select("select sid, stu_num, stu_name, stu_gender, stu_age from tb_students")

    List<Student> listStudents();

    // 根據學號查詢學生記錄
    @Select("select sid, stu_num, stu_name, stu_gender, stu_age " +
            "from tb_students where stu_num=#{stuNum}")
    @ResultMap("studentMap")
    Student queryStudent(String stuNum);

    // 分頁查詢學生記錄，方法一：@Param指定參數名
    @Select("select sid, stu_num, stu_name, stu_gender, stu_age " +
            "from tb_students limit #{start}, #{pageSize}")
    @ResultMap("studentMap")
    List<Student> listStudentsByPage(@Param("start") int start, @Param("pageSize") int pageSize);

    // 查詢學生總數
    @Select("select count(1) from tb_students")
    int getCount();
}
