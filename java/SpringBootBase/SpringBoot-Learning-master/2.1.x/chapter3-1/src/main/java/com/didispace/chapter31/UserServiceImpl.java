package com.didispace.chapter31;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //也可以用autowired自动获取
    private JdbcTemplate jdbcTemplate;

    UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(String name, Integer age) throws StudentExecption {
        int count =jdbcTemplate.queryForObject("select count(1) from user where name=? and age =?",Integer.class,name,age);
        if (count==1){
            throw new StudentDuplicated();
        }
        return jdbcTemplate.update("insert into USER(NAME, AGE) values(?, ?)", name, age);
    }

    @Override
    public List<User> getByName(String name) {
        List<User> users = jdbcTemplate.query("select NAME, AGE ,ID from USER where NAME = ?", (resultSet, i) -> {
            User user = new User();
            user.setName(resultSet.getString("NAME"));
            user.setAge(resultSet.getInt("AGE"));
            user.setId(resultSet.getLong("id"));
            return user;
        }, name);
        return users;
    }

    @Override
    public int deleteByName(String name) {
        return jdbcTemplate.update("delete from USER where NAME = ?", name);
    }

    //select count(1) from user就是计算有多少行并返回  也可以用count(*)
    //queryForObject返回一个查询后的结果对象（如下面返回Integer），也可以返回User，后一个参数要换成UserMapper
    @Override
    public int getAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
    }

    @Override
    public int deleteAllUsers() {
        return jdbcTemplate.update("delete from USER");
    }

    /**
    * @Description: 查询所有的name，age数据，注意写法，查询比较复杂，需要实现匿名类来映射User
    * @Param: 
    * @return: 
    * @Author: xjh
    * @Date: 2020/3/14
    */
    @Override
    public List<User> getListUsers() {
        //下面的匿名类是实现了rowMapper接口，功能是结果与User的映射（resultset：结果集，行数）
        List<User> users=jdbcTemplate.query("select name,age,id from User",(resultSet,i)->{
            User user=new User();
            user.setName(resultSet.getString("name"));
            user.setAge(resultSet.getInt("age"));
            user.setId(resultSet.getLong("id"));
            return user;
        });

        return users;
    }


    @Override
    public User getUserByNo(Long id) throws StudentNotFound{
        User user;
        try{
            user=jdbcTemplate.queryForObject("select id,age,name from User where id=?",new UserMapper(),id);
        }catch (Exception e){
            throw new StudentNotFound();
        }
        return user;
    }
}

//可以用new UserMapper() 来代替上面的匿名类
class UserMapper implements RowMapper<User>{
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user=new User();
        user.setName(resultSet.getString("name"));
        user.setAge(resultSet.getInt("age"));
        user.setId(resultSet.getLong("id"));
        return user;
    }
}