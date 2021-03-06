package com.dragon.book.controller;


import com.alibaba.fastjson.JSON;
import com.dragon.book.mapper.TSystemConfigMapper;
import com.dragon.book.model.TCompensate;
import com.dragon.book.model.TSysUser;
import com.dragon.book.model.TSystemConfig;
import com.dragon.book.model.TType;
import com.dragon.book.pojo.BlackList;
import com.dragon.book.pojo.PcInfo;
import com.dragon.book.service.HomeService;
import com.dragon.book.service.UserService;
import com.dragon.book.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.web.Servlets;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/usermanage")
public class UserManageController {
    @Autowired
    private UserService userService;
    @Autowired
    private HomeService homeService;

    @RequestMapping("/showuser")
    public  String backView(){
        return "/usermanage/userManage";
    }

    @RequestMapping("/usergetdata")
    @ResponseBody
    public String showUser(PageBean pageBean, HttpServletRequest request) {
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Integer total = userService.getCounts(searchParams);
        searchParams.put("first", (pageBean.getPage() - 1) * pageBean.getPagesize());
        searchParams.put("rowNum", pageBean.getPagesize());
        List<TSysUser> tSysUsers = userService.getAllUserByPage(searchParams);
        pageBean.setRows(tSysUsers);
        pageBean.setTotal(total);
//        QueryVo vo = new QueryVo() ;
//        vo.setDim(dim);
//        vo.setStart((pageBean.getPage()-1) * pageBean.getPagesize());
//        vo.setEnd(pageBean.getPage() * pageBean.getPagesize());
//        PageBean pageBean1 = userService.getAllUserByPage(pageBean, vo);
        return JSON.toJSONString(pageBean).replaceAll("rows","Rows").replaceAll("total","Total");
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
        TSystemConfig tSystemConfig =userService.getConfic();
        if (user.getIsadmin()==0){
            user.setKjtscs(tSystemConfig.getBookNum());
            user.setKjsc(tSystemConfig.getBookTime());
        }else {
            user.setKjtscs(tSystemConfig.getAdminBooks());
            user.setKjsc(tSystemConfig.getAdminTime());
        }
        boolean status = userService.updataUser(user);
        return status == true ? "0" : "1" ;
    }

    @RequestMapping("/reset_pwd")
    @ResponseBody
    public boolean resetPwd(int userId){
        boolean status = false;
        int i = userService.updataUserPwd(userId);
        if (i>0)
            status= true;
        return status ;
    }

//    黑名单
   @RequestMapping("/show_blacklist")
    public  String backBlackListView(Model model){
       TSystemConfig tSystemConfig = homeService.getConfig();
       int penTime = tSystemConfig.getPenTime();
       model.addAttribute("PenTime",penTime);
        return "/usermanage/blacListManage";
}

    @RequestMapping("/get_blacklist_data")
    @ResponseBody
    public String showBlackListUser(PageBean pageBean, HttpServletRequest request,Model model) {
        //去除点缀名为“search_”的前缀
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Integer total = userService.getBlCounts(searchParams);
        searchParams.put("first", (pageBean.getPage() - 1) * pageBean.getPagesize());
        searchParams.put("rowNum", pageBean.getPagesize());
        List<BlackList> blackLists = userService.getBlackListByPage(searchParams);
        pageBean.setRows(blackLists);
        pageBean.setTotal(total);
        return JSON.toJSONString(pageBean).replaceAll("rows","Rows").replaceAll("total","Total");
    }
//跳转到修改黑名单惩罚时间
    @RequestMapping("/show_penTime")
    public  String backPenTimeView(Model model){
        TSystemConfig tSystemConfig = homeService.getConfig();
        int penTime = tSystemConfig.getPenTime();
        model.addAttribute("PenTime",penTime);
        return "/usermanage/alterPenTime";
    }
    //修改黑名单惩罚时间
    @RequestMapping("/alterPenTime")
    @ResponseBody
    public boolean editPcInfo(int penTime){
        int status = homeService.modifyPenTime(penTime);
        return  status>0?true:false;
    }


    @RequestMapping("/del_black_user")
    @ResponseBody
    public boolean delBlackUser(int id){
        boolean status = userService.deleteBlackUser(id);
        status =userService.deleteBlUser(id);
        return status ;
    }

    @RequestMapping("/showallpc")
    public  String backPcView(){
        return "/usermanage/show_pc";
    }

    @RequestMapping("/getpcdata")
    @ResponseBody
    public String showPcList(PageBean pageBean) {
        PageBean pageBean1 = userService.gestAllPcInfoByPage(pageBean);
        return JSON.toJSONString(pageBean1).replaceAll("rows","Rows").replaceAll("total","Total");
    }
    @RequestMapping("/pc_add")
    public String showPcAddView(){
        return "usermanage/pc_add" ;
    }

    @RequestMapping("/pc_del")
    @ResponseBody
    public boolean delPcInfo(int pcId){
        boolean status = userService.deletePcById(pcId);
        return  status;
    }
    @GetMapping("/pc_info")
    public String showPcInfo(@RequestParam("id") int id,Model model){
        PcInfo pcInfo = userService.selectPcById(id);
        TType type = userService.selectTypeById(pcInfo.getBook().getTypeId());
        model.addAttribute("pc",pcInfo) ;
        model.addAttribute("type",type) ;
        return  "/usermanage/pc_info";
    }
    @GetMapping("/showPc_edit")
    public String showPcEditInfo(@RequestParam("id") int id,Model model){
        PcInfo pcInfo = userService.selectPcById(id);
        model.addAttribute("pc",pcInfo) ;
        return  "/usermanage/pc_edit";
    }
    @RequestMapping("/pc_edit")
    @ResponseBody
    public boolean editPcInfo(TCompensate tCompensate){
        boolean status = userService.updataByPc(tCompensate);
        return  status;
    }
    @RequestMapping("/evaluation")
    public String userEvaluation(){
        boolean status =false;
        return status == true ? "0" : "1" ;
    }

    @PostMapping("doReg")
    @ResponseBody
    public String doReg(TSysUser tSysUser){
        System.out.println(tSysUser.toString());
        int i = userService.managerDoReg(tSysUser);

        return i>0?"1":"0";
    }
}