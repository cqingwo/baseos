package com.cqwo.xxx.web.api.controller;


import com.cqwo.xxx.core.domain.users.PartUserInfo;
import com.cqwo.xxx.core.model.PageModel;
import com.cqwo.xxx.web.api.model.UCenterHomeModel;
import com.cqwo.xxx.web.api.model.UCenterListModel;
import com.cqwo.xxx.web.framework.controller.BaseApiController;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.cqwo.xxx.core.errors.SateCollect.SUCCESS;


/**
 * 用户中心
 */
@RestController(value = "ApiUCenterController")
public class UCenterController extends BaseApiController {


    private Lock lock = new ReentrantLock();

    /**
     * Ucenter用户中心首页
     *
     * @return
     */
    @RequestMapping(value = "ucenter/index")
    public String index() {

        UCenterHomeModel model = new UCenterHomeModel(workContext.getUid(), workContext.getPartUserInfo(), workContext.getUserRankInfo());
        return JsonView(SUCCESS, model, "用户中心首页");

    }

    /**
     * 用户列表测试
     *
     * @return
     */
    @RequestMapping(value = "ucenter/list")
    public String list() {

        Page<PartUserInfo> userList = users.getPartUserList(1, 1, null, null);

        PageModel pageModel = new PageModel(userList.getSize(), userList.getNumber(), userList.getTotalPages());

        System.out.println(userList);

        UCenterListModel model = new UCenterListModel(userList.getContent(), pageModel);

        return JsonView(SUCCESS, model, "列表加载");
    }


}
