package org.example.service.imp;

import org.example.DAO.ManageCour;
import org.example.DAO.ManageTea;
import org.example.dto.AppointMent;
import org.example.execption.NotFoundId;
import org.example.execption.RepeatAppoint;
import org.example.service.TeachCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @program: mvcexer
 * @description:
 * @author: xjh
 * @create: 2020-03-29 21:04
 **/
@Service
public class TeachCourseImp implements TeachCourse {

    @Autowired
    private ManageTea manageTea;

    @Autowired
    private ManageCour manageCour;

    @Override
    @Transactional
    public AppointMent teachCouse(long teaId, long couId){
        try{
            if (null==manageTea.getTeacher(teaId)){
                throw new NotFoundId("教师ID不存在");
            }else if(null==manageCour.getCourse(couId)){
                throw new NotFoundId("课程ID不存在");
            }else{
               int insert= manageTea.teachCourse(teaId,couId);
               if (insert<=0){
                    throw new RepeatAppoint("重复预约");
               }else{
                   return new AppointMent(teaId,couId,new Date());
               }
            }
        }catch (NotFoundId e1){
            throw e1;
        }catch (RepeatAppoint e2){
            throw e2;
        }catch (Exception e){
            throw e;
        }
    }
}
