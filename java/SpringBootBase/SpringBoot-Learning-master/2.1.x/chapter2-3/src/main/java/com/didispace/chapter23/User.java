package com.didispace.chapter23;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel(description = "用户实体")
public class User {

    @ApiModelProperty("用户编号")
    private Long id;
    //使用jsr检验用户姓名必须非空，大小在2-5之间
    @NotNull
    @Size(min = 2, max = 5)
    @ApiModelProperty("用户姓名")
    private String name;

    @NotNull
    //1.数字，2.小于等于100
    @Max(100)
    @Min(10)
    @ApiModelProperty("用户年龄")
    private Integer age;

    @NotNull
    //必须是邮箱
    @Email
    @ApiModelProperty("用户邮箱")
    private String email;

}