package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentDao studentJDBCTemplate =
                (StudentDao) context.getBean("studentJDBCTemplate");
        studentJDBCTemplate.deleteAll();
        System.out.println("------Records creation--------" );
        studentJDBCTemplate.create("Zara", 11, 99, 2010);
        studentJDBCTemplate.create("Nuha", 20, 97, 2010);
        studentJDBCTemplate.create("Ayan", 25, 100, 2011);
        System.out.println("------Listing all the records--------" );
        List<StudentMarks> studentMarks = studentJDBCTemplate.listStudents();
        for (StudentMarks record : studentMarks) {
            System.out.print("ID : " + record.getId() );
            System.out.print(", Name : " + record.getName() );
            System.out.print(", Marks : " + record.getMarks());
            System.out.print(", Year : " + record.getYear());
            System.out.println(", Age : " + record.getAge());
        }
        studentJDBCTemplate.deleteAll();
        System.out.println("------------声明式事务--------------");
        StudentDao studentJDBCTemplate1=(StudentDao) context.getBean("otherStudentJDBCTemplate");
        System.out.println("------Records creation--------" );
        studentJDBCTemplate1.create("Zara", 11, 99, 2010);
//        create会抛出错误
        studentJDBCTemplate1.create("Nuha", 20, 97, 2010);
        studentJDBCTemplate1.create("Ayan", 25, 100, 2011);
        System.out.println("------Listing all the records--------" );
        List<StudentMarks> studentMark = studentJDBCTemplate.listStudents();
        for (StudentMarks record : studentMark) {
            System.out.print("ID : " + record.getId() );
            System.out.print(", Name : " + record.getName() );
            System.out.print(", Marks : " + record.getMarks());
            System.out.print(", Year : " + record.getYear());
            System.out.println(", Age : " + record.getAge());
        }

    }
}
