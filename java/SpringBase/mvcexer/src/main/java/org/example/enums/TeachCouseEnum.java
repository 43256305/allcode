package org.example.enums;

/**
 * @program: mvcexer
 * @description:   老师预约课程的数据字典
 * @author: xjh
 * @create: 2020-03-29 20:51
 **/
public enum  TeachCouseEnum {
    SUCCESS(1,"预约成功"),ID_NOTFOUND(0,"ID找不到"),REAPEAT_APPOINT(-1,"重复预约")
    ,SYSTEM_EXECPTION(-2,"系统异常");

    private int state;
    private String stateInfo;
    TeachCouseEnum(int state,String stateInfo){
        this.state=state;
        this.stateInfo=stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

}
