package com.cdsxt.service.impl;

import com.cdsxt.dao.CrmUserDao;
import com.cdsxt.po.CrmResource;
import com.cdsxt.po.CrmRole;
import com.cdsxt.po.CrmUser;
import com.cdsxt.service.CrmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmUserServiceImpl implements CrmUserService {

    @Autowired
    private CrmUserDao crmUserDao;

    public List<CrmUser> queryAll() {
        return crmUserDao.queryAll();
    }

    @Override
    public List<CrmUser> queryOnePage(int startRow, int pageRow) {
        return crmUserDao.queryOnePage(startRow, pageRow);
    }

    @Override
    public List<CrmRole> queryAllRole() {
        return crmUserDao.queryAllRole();
    }

    @Override
    public List<CrmRole> queryRoleOnePage(int startRow, int pageRow) {
        return crmUserDao.queryRoleOnePage(startRow, pageRow);
    }

    @Override
    public List<CrmResource> queryAllResource() {
        return crmUserDao.queryAllResource();
    }

    @Override
    public List<CrmResource> queryResourceOnePage(int startRow, int pageRow) {
        return crmUserDao.queryResourceOnePage(startRow, pageRow);
    }

    @Override
    public void deleteUser(int id) {
        crmUserDao.deleteUser(id);
    }

    @Override
    public CrmUser queryUserById(int id) {
        return crmUserDao.queryUserById(id);
    }

    @Override
    public void modifyUser(CrmUser crmUser) {
        crmUserDao.modifyUser(crmUser);
    }

    @Override
    public void addUser(CrmUser crmUser) {
        crmUserDao.addUser(crmUser);
    }

    @Override
    public void deleteRole(int id) {
        crmUserDao.deleteRole(id);
    }

    @Override
    public CrmRole queryRoleById(int id) {
        return crmUserDao.queryRoleById(id);
    }

    @Override
    public void modifyRole(CrmRole crmRole) {
        crmUserDao.modifyRole(crmRole);
    }

    @Override
    public void addRole(CrmRole crmRole) {
        crmUserDao.addRole(crmRole);
    }

    @Override
    public void deleteResource(int id) {
        crmUserDao.deleteResource(id);
    }

    @Override
    public CrmResource queryResourceById(int id) {
        return crmUserDao.queryResourceById(id);
    }

    @Override
    public void modifyResource(CrmResource crmResource) {
        crmUserDao.modifyResource(crmResource);
    }

    @Override
    public void addResource(CrmResource crmResource) {
        crmUserDao.addResource(crmResource);
    }

}
