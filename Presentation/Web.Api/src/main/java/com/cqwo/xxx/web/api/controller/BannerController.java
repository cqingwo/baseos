package com.cqwo.xxx.web.api.controller;


import com.cqwo.xxx.web.framework.controller.BaseApiController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.cqwo.xxx.core.errors.SateCollect.SUCCESS;

/**
 * banner
 */
@RestController(value = "ApiBannerController")
public class BannerController extends BaseApiController {



    @RequestMapping(value = "banner/list")
    public String list(){

        List<String> bannerList = new ArrayList<>();

//        List<ActivityInfo> activityBannerList = activitys.getActivityBannerList();
//
//        for (ActivityInfo info : activityBannerList) {
//            bannerList.add(info.getLitpic());
//        }

        return JsonView(SUCCESS, bannerList, "banner加载成功");

    }
}

