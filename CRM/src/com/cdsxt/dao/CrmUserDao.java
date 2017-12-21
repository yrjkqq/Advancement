package com.cdsxt.dao;

import com.cdsxt.po.CrmResource;
import com.cdsxt.po.CrmRole;
import com.cdsxt.po.CrmUser;

import java.util.List;

public interface CrmUserDao {

    List<CrmUser> queryAll();

    List<CrmUser> queryOnePage(int startRow, int pageRow);

    List<CrmRole> queryAllRole();

    List<CrmRole> queryRoleOnePage(int startRow, int pageRow);

    List<CrmResource> queryAllResource();

    List<CrmResource> queryResourceOnePage(int startRow, int pageRow);

    void deleteUser(int id);

    CrmUser queryUserById(int id);

    void modifyUser(CrmUser crmUser);

    void addUser(CrmUser crmUser);


    void deleteRole(int id);

    CrmRole queryRoleById(int id);

    void modifyRole(CrmRole crmRole);

    void addRole(CrmRole crmRole);


    void deleteResource(int id);

    CrmResource queryResourceById(int id);

    void modifyResource(CrmResource crmResource);

    void addResource(CrmResource crmResource);
}
