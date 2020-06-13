package org.example.DAO;

import org.example.BaseTest;
import org.example.POJO.Course;
import org.example.POJO.Teacher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: mvcexer
 * @description:
 * @author: xjh
 * @create: 2020-03-27 17:09
 **/
public class TeacherDaoTest extends BaseTest {
    @Autowired
    private ManageTea manageTea;

    @Test
    public void testInsert() throws Exception{
        manageTea.insertTea("xie","123456");
        List<Teacher> list=manageTea.listTeacher();
        for (Teacher l:list){
            System.out.println("id:"+l.getId()+" name:"+l.getName()+" password:"+l.getPassword());
        }
    }

    @Test
    public void testGet() throws Exception{
        Teacher t=manageTea.getTeacher(1720130);
        System.out.println(t);
        //System.out.println("id:"+t.getId()+" name:"+t.getName()+" password:"+t.getPassword());
    }

    @Test
    public void testDelete() throws Exception{
        manageTea.deleteTeacher(17201302);
        List<Teacher> list=manageTea.listTeacher();
        for (Teacher l:list){
            System.out.println("id:"+l.getId()+" name:"+l.getName()+" password:"+l.getPassword());
        }
    }

    @Test
    public void testTeach(){
        List<Course> c=manageTea.getCourse(17201303);
//        当list中明明size不为0，但是course确是null的时候，没写as
        for (Course course:c){
            System.out.println("name:"+course.getName());
        }
    }
}
