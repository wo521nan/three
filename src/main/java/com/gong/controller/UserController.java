package com.gong.controller;

import com.gong.model.User;
import com.gong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/6/29.
 */
@Controller
public class UserController {

   private UserService userService;

    public void setUserService(UserService userService)
    {
       this.userService = userService;
    }
    private static String notExistent = "用户不存在";
    private static String passwordError = "密码错误";

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(ModelMap modelMap, User user){
        User user1 =userService.getUser(user.getUsername());
        if (user1 != null){
            if (user1.getPassword().equals(user.getPassword())){
                return "success";
            }else {
                modelMap.addAttribute("error",passwordError);
                return "failpassword";
            }
        }else {
            modelMap.addAttribute("error",notExistent);
            return "failusername";
        }
    }

    /**
     * 初始登陆界面
     * @param user
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(User user){
        ModelAndView mv = new ModelAndView();
        return mv;
    }

    @RequestMapping(value = "/reg",method = RequestMethod.GET)
    public String reg(ModelMap modelMap,User user){
        User u = userService.getUser(user.getUsername());
        if (u != null){
            modelMap.addAttribute("msg","用户已存在，请重新注册");
            return "failreg";
        }else {
            userService.saveUser(user);
            modelMap.addAttribute("msg","恭喜你注册成功");
            return "successreg";
        }
    }

    /**
     * 练习获取get方式url路径上的参数
     * @param username
     * @param password
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/index/{username}/{password}",method = RequestMethod.GET)
    public String index(@PathVariable String username,@PathVariable String password,ModelMap modelMap){
        User user = userService.getUser(username);
        System.out.println(username);
        System.out.println(password);
        if (user != null){
            if (user.getPassword().equals(password)){
                modelMap.addAttribute("username",username);
                modelMap.addAttribute("password",password);
                return "successo";
            }else {
                modelMap.addAttribute("error",passwordError);
                return "failpassword";
            }
        }else {
            modelMap.addAttribute("error",notExistent);
            return "failusername";
        }
    }

    /**
     * 初始登出界面
     * @param user
     * @return
     */
    @RequestMapping(value = "/dengchu",method = RequestMethod.GET)
    public ModelAndView dengchu(User user){
        ModelAndView mv = new ModelAndView("denglu");
        return mv;
    }

    /**
     * 练习获取post表单参数
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String dl(@ModelAttribute("user") User user, ModelMap modelMap){
        User usero =userService.getUser(user.getUsername());
        if (usero != null){
            if (usero.getPassword().equals(user.getPassword())){
                return "success";
            }else {
                modelMap.addAttribute("error",passwordError);
                return "failpassword";
            }
        }else {
            modelMap.addAttribute("error",notExistent);
            return "failusername";
        }
    }

    /**
     * 练习了get方式，通过httpservletrequest获取参数
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String log(HttpServletRequest request, ModelMap modelMap){
        User user = userService.getUser(request.getParameter("username"));
        if (user != null){
            if (user.getPassword().equals(request.getParameter("password"))){
                return "success";
            }else {
                modelMap.addAttribute("error",passwordError);
                return "failpassword";
            }
        }else {
            modelMap.addAttribute("error",notExistent);
            return "failusername";
        }
    }

    /**
     * 练习通过@RequestParam来获得参数
     * @param username
     * @param password
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String log(@RequestParam String username,@RequestParam String password, ModelMap modelMap){
        User user = userService.getUser(username);
        if (user != null){
            if (user.getPassword().equals(password)){
                return "success";
            }else {
                modelMap.addAttribute("error",passwordError);
                return "failpassword";
            }
        }else {
            modelMap.addAttribute("error",notExistent);
            return "failusername";
        }
    }



}


//    @RequestMapping(value = "/add",method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String, Object> add(HttpServletRequest request, @PathVariable("user")User user){
//        Map<String, Object> map = new HashMap<String, Object>();
//        System.out.println("eqwrq");
////        userService.add(user);
////        map.put(user.getId(),user);
//        return map;
//    }

//    @RequestMapping(value="/query",method={RequestMethod.GET,RequestMethod.POST})
//    public ModelAndView query(String id){
//
//    }
//    public Map<String,Object> query(HttpServletRequest request,HttpServletResponse response,
//                                    @PathVariable("id")String id){
//        Map<String,Object> map = new HashMap<String, Object>();
//        System.out.println(id);
//        User user = userService.getUser(id);
//        if(null!=user){
//            map.put("message", "查询Id=" + id + "的用户名为:" + user.getUsername());
//        }else{
//            map.put("message", "没有查询到与Id=" + id + "相关的数据");
//        }
//        return map;
//    }
//    @RequestMapping(value="delete",method={RequestMethod.GET,RequestMethod.POST})
//    public Map<String,Object> delete(HttpServletRequest request, HttpServletResponse response,
//                                     @PathVariable("id")String id){
//        Map<String,Object> map = new HashMap<String, Object>();
//        try {
//            userService.delete(id);
//            map.put("message", "删除Id为" + id +"的用户成功.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("message", "删除Id为" + id +"的用户失败, "+e.getMessage());
//        }
//        return map;
//    }

