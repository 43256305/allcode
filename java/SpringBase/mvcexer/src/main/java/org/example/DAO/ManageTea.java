package org.example.DAO;

import org.apache.ibatis.annotations.Param;
import org.example.POJO.Course;
import org.example.POJO.Teacher;

import java.util.List;

public interface ManageTea {
    /**
    * @Description: 插入老师
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/26
    */
    void insertTea(@Param("name") String name,@Param("password") String password);

    /**
    * @Description: 获得所有老师
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/26
    */
    List<Teacher> listTeacher();
    
    /**
    * @Description: 获得单个老师
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/26
    */
    Teacher getTeacher(@Param("id") long id);
    
    /**
    * @Description: 删除老师
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/26
    */
    void deleteTeacher(@Param("id") long id);

    /**
    * @Description: 教授课程
    * @Param: id为课程id
    * @return:
    * @Author: xjh
    * @Date: 2020/3/26
    */
    int teachCourse(@Param("tea_id") long tea_id,@Param("cou_id") long cou_id);

    /**
    * @Description: 查看某个老师所有教导的课程
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/27
    */
    List<Course> getCourse(@Param("id") long id);

}
