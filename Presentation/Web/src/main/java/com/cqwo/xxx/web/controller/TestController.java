package com.cqwo.xxx.web.controller;

import com.alibaba.fastjson.JSON;
import com.cqwo.xxx.services.Persons;
import com.cqwo.xxx.services.Users;
import com.cqwo.xxx.services.WechatMiniUtils;
import com.cqwo.xxx.web.framework.controller.BaseWebController;
import com.cqwo.xxx.web.model.WxFireWarnViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController(value = "TestController")
public class TestController extends BaseWebController {

    @Autowired
    Persons persons;

    @Autowired
    Users users;

    @Autowired
    WechatMiniUtils wechatMiniUtils;


    /**
     * hellow
     *
     * @return
     */
    @RequestMapping(value = "/hello")
    public String hello() {




        return JSON.toJSONString("");

//        Users users = (Users) ApplicationContextHelper.getBean("Users");
//
//        System.out.println(users.getPartUserByUid(1));
//
//        PartUserInfo userInfo = users.getPartUserByWechatOpenId("oWPGk5Fd48g6wWyvWfCrmpkUYB24", "");
//
//        System.out.println("userInfo:" + userInfo.toString());
//
//        System.out.println(wechatMiniUtils.getAccessToken());

//        return "我喜欢周婷";
    }

    @RequestMapping("/test")
    public String test() throws IOException {


        //System.out.println(forumPraises.isPraise(61,31));

        return "ok";

//        //ifnclouds.tunnel.echomod.cn/ifnclouds/weixin/api/receiveWxData
//        String url = "http://ifnclouds.tunnel.echomod.cn/ifnclouds/weixin/api/receiveWxData";
//
//        Map<String, Object> params = new HashMap<>();
//
//        params.put("address", "重庆九龙坡区二郎留学生创业园");
//
//        String s = HttpHelper.doPost(url, params);
//
//        return s;

//        Person person = new Person("liu");
//
//        System.out.println(person.toString());
//
//        Sort sort = new Sort(Sort.Direction.DESC, "uid");
//
//        Page<PartUserInfo> userInfoPage = users.getPartUserList(10, 1, new Specification<PartUserInfo>() {
//            @Override
//            public Predicate toPredicate(Root<PartUserInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                return null;
//            }
//        }, sort);
//
//
//        return persons.savePernson(person);
    }

    @RequestMapping("/test2")
    public String test2(WxFireWarnViewModel model) {

        System.out.println("参数模型接收:" + model.toString());

        return model.getAddress();
    }


}
