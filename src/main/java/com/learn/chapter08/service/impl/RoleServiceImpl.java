package com.learn.chapter08.service.impl;

import com.learn.chapter08.dao.mapper.RoleMapper;
import com.learn.chapter08.dao.pojo.Role;
import com.learn.chapter08.service.RoleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标题：角色服务实现<br>
 * 描述：角色服务实现<br>
 * 时间：2018/05/24<br>
 *
 * @author zc
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insert(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public int delete(Integer id) {
        return roleMapper.delete(id);
    }

    @Override
    public Role findOne(Integer id) {
        return roleMapper.findOne(id);
    }

    @Override
    public List<Role> list(String roleName, int start, int limit) {
        return roleMapper.list(roleName,new RowBounds(start,limit));
    }
}
