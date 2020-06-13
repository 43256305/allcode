package org.example;

import javax.sql.DataSource;
import java.util.List;

public interface StudentDao {
    void setDataSource(DataSource ds);
    void create(String name,Integer age,Integer marks,Integer year);
    List<StudentMarks> listStudents();
    void deleteAll();
}
