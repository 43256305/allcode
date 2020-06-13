package org.example;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;

/**
 * @program: transaction
 * @description:
 * @author: xjh
 * @create: 2020-03-19 20:46
 **/
//声明式事务只需要在xml文件中配置
    //jdbcTemplate中不需要写一行关于Transaction的代码（aop就是如此）
public class OtherStudentJDBCTemplate implements StudentDao {
    private JdbcTemplate jdbcTemplateObject;
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    public void create(String name, Integer age, Integer marks, Integer year){
        try {
            String SQL1 = "insert into Student (name, age) values (?, ?)";
            jdbcTemplateObject.update( SQL1, name, age);
// Get the latest student id to be used in Marks table
            String SQL2 = "select max(id) from Student";
            int sid = jdbcTemplateObject.queryForObject( SQL2 ,Integer.class);
            String SQL3 = "insert into Marks(sid, marks, year) " +
                    "values (?, ?, ?)";
            jdbcTemplateObject.update( SQL3, sid, marks, year);
            System.out.println("Created Name = " + name + ", Age = " + age);
// to simulate the exception.  把这行删掉就可以insert数据了
            throw new RuntimeException("simulate Error condition") ;
        } catch (DataAccessException e) {
            System.out.println("Error in creating record, rolling back");
            throw e;
        }
    }
    public List<StudentMarks> listStudents() {
        String SQL = "select * from Student, Marks where Student.id=Marks.sid";
        List<StudentMarks> studentMarks = jdbcTemplateObject.query(SQL,
                new StudentMarksMapper());
        return studentMarks;
    }

    @Override
    public void deleteAll() {
        try{
            jdbcTemplateObject.update("delete from marks");
            jdbcTemplateObject.update("delete from student");
            System.out.println("Delete All Student");
        }catch (DataAccessException e){
            throw e;
        }
    }

}
