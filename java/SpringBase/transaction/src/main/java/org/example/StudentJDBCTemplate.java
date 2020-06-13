package org.example;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;

/**
 * @program: transaction
 * @description:
 * @author: xjh
 * @create: 2020-03-19 19:59
 **/
public class StudentJDBCTemplate implements StudentDao {
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;

//    jdbcTemplate的实例化需要datasource
    @Override
    public void setDataSource(DataSource ds) {
        this.jdbcTemplate=new JdbcTemplate(ds);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager){
        this.transactionManager=transactionManager;
    }

    @Override
    public void create(String name, Integer age, Integer marks, Integer year) {
        TransactionDefinition definition=new DefaultTransactionDefinition();
        TransactionStatus status=transactionManager.getTransaction(definition);
        try{
            String SQL1="insert into Student(name,age) values (?,?)";
            jdbcTemplate.update(SQL1,name,age);
            String SQL2="select max(id) from student";
            int sid=jdbcTemplate.queryForObject(SQL2,Integer.class);
            String SLQ3="insert into marks(sid,marks,year) values(?,?,?)";
            jdbcTemplate.update(SLQ3,sid,marks,year);
            System.out.println("Created Name = " + name + ", Age = " + age);
            //事务提交
            transactionManager.commit(status);

        }catch (DataAccessException e){
            System.out.println("Error in creating record, rolling back");
            //事务回滚   防止插入了一个表，第二个表却出错没有插入
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public List<StudentMarks> listStudents() {
        List<StudentMarks> list=jdbcTemplate.query("select * from Student,marks where student.id=marks.sid"
                ,new StudentMarksMapper());
        return list;
    }

    @Override
    public void deleteAll() {
        TransactionDefinition definition=new DefaultTransactionDefinition();
        TransactionStatus status=transactionManager.getTransaction(definition);
        try{
            jdbcTemplate.update("delete from marks");
            jdbcTemplate.update("delete from student");
            System.out.println("Delete All Student");
            transactionManager.commit(status);
        }catch (DataAccessException e){
            transactionManager.rollback(status);
            throw e;
        }
    }
}
