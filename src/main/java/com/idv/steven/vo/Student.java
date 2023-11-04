package com.idv.steven.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Alias("student")
//<typeAliases>
//  <package name="com.idv.steven.vo"/>
//</typeAliases>
public class Student {
    private int stuId;
    private String stuNum;
    private String stuName;
    private String stuGender;
    private int stuAge;
}
