package com.cdsxt.web.controller;

import com.cdsxt.po.CrmResource;
import com.cdsxt.po.CrmRole;
import com.cdsxt.po.CrmUser;
import com.cdsxt.service.CrmUserService;
import com.cdsxt.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("crm")
public class CrmUserController {

    @Autowired
    private CrmUserService crmUserService;

    // 查询用户表, 全部查询
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main(ModelMap modelMap, @RequestParam(value = "curPage", defaultValue = "1") Integer curPage) {
        List<CrmUser> crmUserList = crmUserService.queryAll();

        PageUtil page = new PageUtil(crmUserList.size(), curPage);
        int startRow = page.getStartRow();
        int pageCount = page.getPageRow();
        List<CrmUser> crmUserListOnePage = crmUserService.queryOnePage(startRow, pageCount);

        modelMap.addAttribute("page", page);
        modelMap.addAttribute("crmUserList", crmUserListOnePage);
        return "main";
    }

    // 查询角色表, 将查询结果以 json 格式返回
    @RequestMapping(value = "role", method = RequestMethod.GET)
    public String queryAllRole(ModelMap modelMap, @RequestParam(value = "curPage", defaultValue = "1") Integer curPage) throws IOException {
        List<CrmRole> crmRoleList = crmUserService.queryAllRole();

        PageUtil page = new PageUtil(crmRoleList.size(), curPage);
        int startRow = page.getStartRow();
        int pageCount = page.getPageRow();
        List<CrmRole> crmRoleListOnePage = crmUserService.queryRoleOnePage(startRow, pageCount);

        modelMap.addAttribute("page", page);
        modelMap.addAttribute("crmRoleList", crmRoleListOnePage);
        return "role";
    }

    // 查询资源表, 全部查询
    @RequestMapping(value = "resource", method = RequestMethod.GET)
    public String resource(Model model, @RequestParam(value = "curPage", defaultValue = "1") Integer curPage) {

        List<CrmResource> crmResourceList = crmUserService.queryAllResource();

        PageUtil page = new PageUtil(crmResourceList.size(), curPage);
        int startRow = page.getStartRow();
        int pageCount = page.getPageRow();
        List<CrmResource> crmResourceListOnePage = crmUserService.queryResourceOnePage(startRow, pageCount);

        model.addAttribute("page", page);
        model.addAttribute("crmResourceList", crmResourceListOnePage);
        return "resource";
    }


    /*
    用户管理
     */

    // 添加用户
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String addUser(CrmUser crmUser) {
        // 数据库中插入记录
        crmUserService.addUser(crmUser);
        return "redirect:/crm/main";
    }

    // 删除用户
    @RequestMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        crmUserService.deleteUser(id);
        return "redirect:/crm/main";
    }

    // 修改用户: 查询用户并返回 json 对象
    @RequestMapping(value = "modifyUser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CrmUser modifyUser(@PathVariable("id") Integer id) {
        // 根据 id 查询用户, 返回 json 对象给页面
        return crmUserService.queryUserById(id);
    }

    // 修改用户: 修改数据库
    @RequestMapping(value = "modifyUser", method = RequestMethod.POST)
    public String modifyUser(CrmUser crmUser) {
        crmUserService.modifyUser(crmUser);
        return "redirect:/crm/main";
    }

    /*
    角色管理
     */
    // 添加角色
    @RequestMapping(value = "addRole", method = RequestMethod.POST)
    public String addRole(CrmRole crmRole) {
        // 数据库中插入记录
        crmUserService.addRole(crmRole);
        return "redirect:/crm/role";
    }

    // 删除角色
    @RequestMapping("deleteRole/{id}")
    public String deleteRole(@PathVariable("id") Integer id) {
        crmUserService.deleteRole(id);
        return "redirect:/crm/role";
    }

    // 修改角色: 查询角色并返回 json 对象
    @RequestMapping(value = "modifyRole/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CrmRole modifyRole(@PathVariable("id") Integer id) {
        // 根据 id 查询用户, 返回 json 对象给页面
        return crmUserService.queryRoleById(id);
    }

    // 修改角色: 修改数据库
    @RequestMapping(value = "modifyRole", method = RequestMethod.POST)
    public String modifyRole(CrmRole crmRole) {
        crmUserService.modifyRole(crmRole);
        return "redirect:/crm/role";
    }


    /*
    资源管理
     */

    // 添加资源
    @RequestMapping(value = "addResource", method = RequestMethod.POST)
    public String addResource(@Validated @ModelAttribute("crmResource") CrmResource crmResource, BindingResult result) {
        if (result.hasErrors()) {
            // 有错误
            System.out.println("出错");
            return "redirect:/crm/resource";
        }
        // 数据库中插入记录
        crmUserService.addResource(crmResource);
        return "redirect:/crm/resource";
    }

    // 删除资源
    @RequestMapping("deleteResource/{id}")
    public String deleteResource(@PathVariable("id") Integer id) {
        crmUserService.deleteResource(id);
        return "redirect:/crm/resource";
    }

    // 修改资源: 查询资源并返回 json 对象
    @RequestMapping(value = "modifyResource/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CrmResource modifyResource(@PathVariable("id") Integer id) {
        // 根据 id 查询用户, 返回 json 对象给页面
        return crmUserService.queryResourceById(id);
    }

    // 修改资源: 修改数据库
    @RequestMapping(value = "modifyResource", method = RequestMethod.POST)
    public String modifyResource(CrmResource crmResource) {
        crmUserService.modifyResource(crmResource);
        return "redirect:/crm/resource";
    }

}
