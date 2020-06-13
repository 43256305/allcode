package com.example.restfulexer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: webassignment2
 * @description: studentbean
 * @author: xjh
 * @create: 2020-03-11 10:51
 **/

//不需要让spring自动生成的类（我们是在factory中手动生成），不需要设@Component，要不然会报错
@Data
@AllArgsConstructor
@ApiModel("学生实体")
public class Student {
    @ApiModelProperty(value = "name",notes = "学生姓名")
    private String name;
    @ApiModelProperty(value = "id",notes = "学生id")
    private Long id;
    @ApiModelProperty(value = "age",notes = "学生年龄")
    private Integer age;

}
