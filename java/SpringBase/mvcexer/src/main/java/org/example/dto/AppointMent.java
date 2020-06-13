package org.example.dto;

import org.example.enums.TeachCouseEnum;

import java.util.Date;

/**
 * @program: mvcexer
 * @description: 预约成功信息
 * @author: xjh
 * @create: 2020-03-29 21:26
 **/
//传递给前端的信息
public class AppointMent {
    private long teaid;
    private long couid;
    private Date date;
    private String stateInfo;
    private int state;

    public AppointMent(){}

    public AppointMent(long t,long c,Date date){
        this.teaid=t;
        this.couid=c;
        this.date=date;
    }
//预约失败构造器
    public AppointMent(long t, long c, TeachCouseEnum enum1){
        this.teaid=t;
        this.couid=c;
        this.stateInfo=enum1.getStateInfo();
        this.state=enum1.getState();
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getTeaid() {
        return teaid;
    }

    public void setTeaid(long teaid) {
        this.teaid = teaid;
    }

    public long getCouid() {
        return couid;
    }

    public void setCouid(long couid) {
        this.couid = couid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
