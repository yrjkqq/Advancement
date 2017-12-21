package com.cdsxt.dao.impl;

import com.cdsxt.dao.CrmUserDao;
import com.cdsxt.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CrmUserDaoImpl implements CrmUserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CrmUser> queryAll() {
        // 添加测试数据
//        addTestData();
        return jdbcTemplate.query("select * from crm_user", BeanPropertyRowMapper.newInstance(CrmUser.class));
    }

    @Override
    public List<CrmUser> queryOnePage(int startRow, int pageRow) {
        return jdbcTemplate.query("select * from crm_user limit ?,?", new Object[]{startRow, pageRow},
                BeanPropertyRowMapper.newInstance(CrmUser.class));
    }

    @Override
    public List<CrmRole> queryAllRole() {
        return jdbcTemplate.query("select * from crm_role", BeanPropertyRowMapper.newInstance(CrmRole.class));
    }

    @Override
    public List<CrmRole> queryRoleOnePage(int startRow, int pageRow) {
        return jdbcTemplate.query("select * from crm_role limit ?,?", new Object[]{startRow, pageRow},
                BeanPropertyRowMapper.newInstance(CrmRole.class));
    }

    @Override
    public List<CrmResource> queryAllResource() {
        return jdbcTemplate.query("select * from crm_resource", BeanPropertyRowMapper.newInstance(CrmResource.class));
    }

    @Override
    public List<CrmResource> queryResourceOnePage(int startRow, int pageRow) {
        return jdbcTemplate.query("select * from crm_resource limit ?,?", new Object[]{startRow, pageRow},
                BeanPropertyRowMapper.newInstance(CrmResource.class));
    }

    @Override
    public void deleteUser(int id) {
        // 删除的时候三张表同时删除
        int role_id = jdbcTemplate.query("select * from crm_user_role where user_id = ?", new Object[]{id},
                BeanPropertyRowMapper.newInstance(CrmUserRole.class)).get(0).getRole_id();

        int resource_id = jdbcTemplate.query("select * from crm_role_resource where role_id = ?", new Object[]{role_id},
                BeanPropertyRowMapper.newInstance(CrmRoleResource.class)).get(0).getResource_id();

        jdbcTemplate.update("delete from crm_role_resource where role_id  = ?", role_id);
        jdbcTemplate.update("delete from crm_resource where id  = ?", resource_id);

        jdbcTemplate.update("delete from crm_user_role where role_id  = ?", role_id);
        jdbcTemplate.update("delete from crm_role where id  = ?", role_id);

        jdbcTemplate.update("delete from crm_user where id  = ?", id);
    }

    @Override
    public CrmUser queryUserById(int id) {
        return jdbcTemplate.query("select * from crm_user where id = ?", new Object[]{id},
                BeanPropertyRowMapper.newInstance(CrmUser.class)).get(0);
    }

    @Override
    public void modifyUser(CrmUser crmUser) {
        jdbcTemplate.update("update crm_user set description = ?, email = ?, enabled = ?, locked = ?, password = ?, " +
                "sex = ?, username = ? where id = ?", new Object[]{crmUser.getDescription(), crmUser.getEmail(), crmUser.getEnabled(),
                crmUser.getLocked(), crmUser.getPassword(), crmUser.getSex(), crmUser.getUsername(), crmUser.getId()});
    }

    @Override
    public void addUser(CrmUser crmUser) {
        jdbcTemplate.update("insert into crm_user values(?, ?, ?, @IDENTITY, ?, ?, ?, ?)", crmUser.getDescription(), crmUser.getEmail(),
                crmUser.getEnabled(), crmUser.getLocked(), crmUser.getPassword(), crmUser.getSex(), crmUser.getUsername());
    }

    @Override
    public void deleteRole(int id) {
        int user_id = jdbcTemplate.query("select * from crm_user_role where role_id = ?", new Object[]{id},
                BeanPropertyRowMapper.newInstance(CrmUserRole.class)).get(0).getUser_id();
        deleteUser(user_id);
    }

    @Override
    public CrmRole queryRoleById(int id) {
        return jdbcTemplate.query("select * from crm_role where id = ?", new Object[]{id},
                BeanPropertyRowMapper.newInstance(CrmRole.class)).get(0);
    }

    @Override
    public void modifyRole(CrmRole crmRole) {
        jdbcTemplate.update("update crm_role set constant = ?, description = ?, enabled = ?, name = ? where id = ?",
                new Object[]{crmRole.getConstant(), crmRole.getDescription(), crmRole.getEnabled(), crmRole.getName(), crmRole.getId()});
    }

    @Override
    public void addRole(CrmRole crmRole) {
        jdbcTemplate.update("insert into crm_role values(?, ?, ?, @IDENTITY, ?)", crmRole.getConstant(), crmRole.getDescription(),
                crmRole.getEnabled(), crmRole.getName());
    }

    @Override
    public void deleteResource(int id) {
        int role_id = jdbcTemplate.query("select * from crm_role_resource where resource_id = ?", new Object[]{id},
                BeanPropertyRowMapper.newInstance(CrmRoleResource.class)).get(0).getRole_id();
        deleteRole(role_id);
    }

    @Override
    public CrmResource queryResourceById(int id) {
        return jdbcTemplate.query("select * from crm_resource where id = ?", new Object[]{id},
                BeanPropertyRowMapper.newInstance(CrmResource.class)).get(0);
    }

    @Override
    public void modifyResource(CrmResource crmResource) {
        jdbcTemplate.update("update crm_resource set constant = ?, enabled = ?, href = ?, name = ?, parent = ?, shown = ?, " +
                "target = ?, title = ?, type = ? where id = ?", new Object[]{crmResource.getConstant(),
                crmResource.getEnabled(), crmResource.getHref(), crmResource.getName(), crmResource.getParent(), crmResource.getShown(),
                crmResource.getTarget(), crmResource.getTitle(), crmResource.getType(), crmResource.getId()});
    }

    @Override
    public void addResource(CrmResource crmResource) {
        jdbcTemplate.update("insert into crm_resource values(?, ?, ?, @IDENTITY, ?, ?, ?, ?, ?, ?)", crmResource.getConstant(),
                crmResource.getEnabled(), crmResource.getHref(), crmResource.getName(), crmResource.getParent(), crmResource.getShown(),
                crmResource.getTarget(), crmResource.getTitle(), crmResource.getType());
    }

    // 插入测试数据
    private void addTestData() {
//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        int count = 1000;

        String constant = "常量";
        for (int i = 3; i <= count; i++) {
            int boolean1 = (int) (Math.random() * 2);
            int boolean2 = (int) (Math.random() * 2);
            int boolean3 = (int) (Math.random() * 2);
            int number = (int) (Math.random() * 20);


            jdbcTemplate.update("insert into crm_user values(?, ?, ?, ?, ?, ?, ?, ?)", "description" + i, i + "@qq.com",
                    boolean1, i, boolean2, number * 37 + "", boolean3, "用户" + i);

            jdbcTemplate.update("insert into crm_role values(?, ?, ?, ?, ?)", constant + i, "description" + i,
                    boolean1, i, "角色" + i);

            jdbcTemplate.update("insert into crm_user_role values(?, ?)", i, i);

            jdbcTemplate.update("insert into crm_resource values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", constant + i, boolean1,
                    boolean1 == 1 ? "/google.com" : "/baidu.com", i, "资源" + i, i * 7, boolean2, "目标" + i, "标题" + i, boolean3);

            jdbcTemplate.update("insert into crm_role_resource values(?, ?)", i, i);
        }

    }

}
