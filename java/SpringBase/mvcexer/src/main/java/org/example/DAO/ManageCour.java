package org.example.DAO;

import org.apache.ibatis.annotations.Param;
import org.example.POJO.Course;

import java.util.List;

public interface ManageCour {

    /**
    * @Description: 新增课程
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/26
    */
    void insertCourse(@Param("name") String name);

    /**
    * @Description: 获得所有课程
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/26
    */
    List<Course> listCourse();
    
    /**
    * @Description: 获得单个课程
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/26
    */
    Course getCourse(@Param("id") long id);
    
    /**
    * @Description: 删除课程
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/26
    */
    void deleteCourse(@Param("id") long id);
}
