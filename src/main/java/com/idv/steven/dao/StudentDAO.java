package com.idv.steven.dao;

import com.idv.steven.vo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDAO {
    int insertStudent(Student student);
    int deleteStudent(String stuNum);

    int updateStudent(Student student);
    List<Student> listStudents();

    Student queryStudent(String stuNum);

    List<Student> listStudentsByGender(String gender);

    //如下為多參數分頁查詢
    //多參數查詢 方法一 @Param指定參數名
//    List<Student> listStudentsByPage(@Param("start")int start,
//                                    @Param("pageSize")int pageSize);
    //多參數查詢 方法二 HashMap
//    List<Student> listStudentsByPage(HashMap<String,Integer> map);
    //多參數查詢 方法三 arg* 於Mapper.xml寫入arg* 或 param*來對應參數順序
//    List<Student> listStudentsByPage(int start, int pageSize);

//    int getCount();
}
