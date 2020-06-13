package org.example.DAO;

import org.example.BaseTest;
import org.example.POJO.Course;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: mvcexer
 * @description:
 * @author: xjh
 * @create: 2020-03-27 17:36
 **/
public class CourseDaoTest extends BaseTest {

    @Autowired
    private ManageCour manageCour;

    @Test
    public void testInsert(){
        manageCour.insertCourse("数据结构");
        manageCour.insertCourse("高数");
        List<Course> list=manageCour.listCourse();
        for (Course c:list){
            System.out.println("id:"+c.getId()+" name:"+c.getName());
        }
    }

    @Test
    public void testGet(){
        Course c=manageCour.getCourse(1001);
        System.out.println("id:"+c.getId()+" name:"+c.getName());
    }

    @Test
    public void testDelete(){
        manageCour.deleteCourse(1000);
        List<Course> list=manageCour.listCourse();
        for (Course c:list){
            System.out.println("id:"+c.getId()+" name:"+c.getName());
        }
    }
}
