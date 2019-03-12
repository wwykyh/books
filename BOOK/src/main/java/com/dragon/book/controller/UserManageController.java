package com.dragon.book.controller;


import com.alibaba.fastjson.JSON;
import com.dragon.book.model.TSysUser;
import com.dragon.book.pojo.QueryVo;
import com.dragon.book.service.UserService;
import com.dragon.book.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/usermanage")
public class UserManageController {
    @Autowired
    private UserService userService;

    @RequestMapping("/showuser")
    public  String backView(){
        return "/usermanage/userManage";
    }

    @RequestMapping("/usergetdata")
    @ResponseBody
    public String showUser(PageBean pageBean, String dim) {
        QueryVo vo = new QueryVo() ;
        vo.setDim(dim);
        vo.setStart((pageBean.getPage()-1) * pageBean.getPagesize());
        vo.setEnd(pageBean.getPage() * pageBean.getPagesize());
        PageBean pageBean1 = userService.getAllUserByPage(pageBean, vo);
        return JSON.toJSONString(pageBean1).replaceAll("rows","Rows").replaceAll("total","Total");
    }

    @RequestMapping("/del_user")
    @ResponseBody
    public boolean delBook(int id){
        boolean status = userService.deleteUser(id);
        return status ;
    }

    /**
     * 用户详情
     *
     * @param id
     *            用户id
     * @return
     */
    @GetMapping("/user_info")
    public String userInfo(@RequestParam("id") int id, Model model) {
        TSysUser userInfo = userService.getUserInfo(id);
        model.addAttribute("userInfo",userInfo);
		return "/usermanage/user_info" ;
    }

    @GetMapping("/showuser_edit")
    public String ShowUserEdit(@RequestParam("id") int id, Model model) {
        TSysUser userInfo = userService.getUserInfo(id);
        model.addAttribute("userInfo",userInfo);
        return "/usermanage/user_edit" ;
    }

    @RequestMapping("/user_edit")
    @ResponseBody
    public String userUpdate(TSysUser user){
        boolean status = userService.updataUser(user);
        return status == true ? "0" : "1" ;
    }

//    黑名单
@RequestMapping("/show_blacklist")
public  String backBlackListView(){
    return "/usermanage/blacListManage";
}

    @RequestMapping("/get_blacklist_data")
    @ResponseBody
    public String showBlackListUser(PageBean pageBean, String dim) {
        QueryVo vo = new QueryVo() ;
        vo.setDim(dim);
        vo.setStart((pageBean.getPage()-1) * pageBean.getPagesize());
        vo.setEnd(pageBean.getPage() * pageBean.getPagesize());
        PageBean pageBean1 = userService.getBlackListByPage(pageBean,vo);
        return JSON.toJSONString(pageBean1).replaceAll("rows","Rows").replaceAll("total","Total");
    }

    @RequestMapping("/del_black_user")
    @ResponseBody
    public boolean delBlackUser(int id){
        boolean status = userService.deleteBlackUser(id);
        return status ;
    }
}