package com.kaoyancun.controller;


import com.kaoyancun.entity.*;
import com.kaoyancun.sevice.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/apps")
public class DisController {

    @Autowired
    SchoolService schoolService;

    @Autowired
    UserService userService;

    @Autowired
    AchieveService achieveService;
    @Autowired
    DirectionSchoolService directionSchoolService;

    @Autowired
    DispenSingUserService dispenSingUserService;


    // http://423c2118.ngrok.io/apps/dispensing

    //调剂首页
    @RequestMapping("/dispensing")
    public Object showIndex() {
        return "dispensing/index";
    }

    /**
     * 根据省份选择院校，将院校信息提交到"填写信息"页面
     **/
    //院校选择（页面跳转）
    @RequestMapping("dispensing/disschool")
    public Object showDisSchool() {
        return "dispensing/chschool";
    }


    //处理sidschool数据请求

    @ResponseBody
    @RequestMapping("dispensing/disschools")
    public List<School> getSchools(@RequestParam("province") String province) {
        return schoolService.getSchools(province);
    }

    /**
     * 1、接收院校信息
     * 2、填写完成信息
     * 3、手机号验证
     **/
    //填写信息
    @RequestMapping("dispensing/dismsg")
    public Object showDisMsg(Model model, @RequestParam("schoolCode") String schoolCode,@RequestParam("schoolName") String schoolName) {
        List<Major> majorList =  schoolService.getMajorBySchoolCode(schoolCode);
        model.addAttribute("schoolCode",schoolCode);
        model.addAttribute("schoolName",schoolName);
        model.addAttribute("provinceList",directionSchoolService.showProvince());
        return "dispensing/msg";
    }


    //根据专业名称匹配专业

    @ResponseBody
    @RequestMapping("/dispensing/dismsg/mj")
    public Object getMajorByMajorName(@RequestBody Map<String,String> map) {
        List<Major> majorList = schoolService.getMajorByMajorName(map.get("majorName"),Integer.parseInt(map.get("schoolCode")));
        return majorList;
    }

    //提交Users信息处理
    @ResponseBody
    @RequestMapping(value = "dispensing/dismsg/us", method = RequestMethod.POST)
    public Long postUser(@RequestBody Users users) {
        return userService.insertUser(users);
    }

    //提交成绩信息
    @ResponseBody
    @RequestMapping(value= "dispensing/dismsg/ac", method = RequestMethod.POST)
    public Boolean insertAch(@RequestBody Achieve achieve){

        return (achieveService.insertAchieve(achieve).booleanValue());
        /////完成成绩提交
    }


    //根据院校代码、院系代码、专业代码调取专业表ID
    @ResponseBody
    @RequestMapping("dispensing/dismsg/mId")
    public Object getMajorIdBySCFCMC(@RequestBody Map<String,String> map)
    {

       return schoolService.getMajorIdBySCFCMC(Integer.parseInt(map.get("schoolCodes")),
                                                Integer.parseInt(map.get("facultyCode")),
                                                Integer.parseInt(map.get("majorCode")));
    }

    //完成调剂提交
    @RequestMapping("dispensing/disoK")
    public Object showDisOK() {
        return "dispensing/ok";
    }



//    短信验证
    @ResponseBody
    @RequestMapping("/verTel")
    public String getVerTel(@RequestParam("tel")String uTel)
    {
        Map<String,String> map = new HashMap();

        String sid = "c843e720756fb274416a15b7f0f13b37";
        String token = "e3de0469af7644e67302e05ccac96f89";
        String appid = "3505e543120444c0b42f833c1f44d406";
        String templateid = "432774";
        Random random = new Random();
        String param="";
        for (int i=0;i<4;i++){ param+=random.nextInt(10);}
        String uid = "";

        String rs=InstantiationRestAPI().sendSms(sid, token, appid, templateid, param, uTel, uid);
        map.put("verCode",param);
        return param;
    }
    static AbsRestClient InstantiationRestAPI() {
        return new JsonReqClient();
    }

    //根据省份调取学校
    @ResponseBody
    @RequestMapping("/dispensing/getschoolbyprovince")
    public Object getDispensingSchoolByProvice(@RequestParam("province")String province)
    {
        return  directionSchoolService.getDispensingSchoolByProvice(province);
    }


    //匹配调剂学校

    public List<DirectionSchool> getDispensingSchool(@RequestParam("schoolName")String schoolName)
    {
        return  directionSchoolService.getDispensingSchool(schoolName);
    }


    //插入调剂院校
    @ResponseBody
    @RequestMapping("/dispensing/insDisUser")
    public Boolean insertDispenSingUser(@RequestBody DispenSingUser dispenSingUser)
    {
        return  dispenSingUserService.insertDispenSingUser(dispenSingUser);
    }



}
