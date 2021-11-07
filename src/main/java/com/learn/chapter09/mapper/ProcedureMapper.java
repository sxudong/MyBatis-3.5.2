package com.learn.chapter09.mapper;

import com.learn.chapter09.pojo.PageRole;
import com.learn.chapter09.pojo.ProcedurePojo;
import com.learn.chapter09.pojo.Role;

import java.util.List;

public interface ProcedureMapper {
    public void count(ProcedurePojo pojo);
    List<Role> findRole(PageRole pageRole);
}
