package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    boolean addDept(Dept dept);

    Dept queryById(Long deptno);

    List<Dept> queryAll();
}
