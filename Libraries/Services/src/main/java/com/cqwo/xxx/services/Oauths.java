package com.cqwo.xxx.services;

import com.cqwo.xxx.core.domain.users.OauthInfo;
import com.cqwo.xxx.core.helper.StringHelper;
import com.cqwo.xxx.core.domain.users.OauthInfo;
import com.cqwo.xxx.core.helper.StringHelper;
import com.cqwo.xxx.core.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cqnews on 2017/4/11.
 */

//第三方登录
@Service(value = "Oauths")
public class Oauths {
    @Resource(name = "OauthsData")
    com.cqwo.xxx.data.Oauths oauths;

    @Autowired
    private Logs logs;


    //region  第三方登录方法

    /**
     * 获得第三方登录数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getOauthCount(Specification<OauthInfo> condition) {

        try {
            return oauths.getOauthCount(condition);
        } catch (Exception e) {
            logs.write(e, "获得第三方登录数量失败");
        }
        return 0;
    }

    /**
     * 创建一条第三方登录数据
     *
     * @param oauthInfo 第三方登录模型
     * @return 返回创建信息
     **/
    public OauthInfo createOauth(OauthInfo oauthInfo) {
        try {
            return oauths.createOauth(oauthInfo);
        } catch (Exception e) {
            logs.write(e, "创建一条第三方登录数据失败");
        }
        return null;
    }

    /**
     * 更新一条第三方登录数据
     *
     * @param oauthInfo 第三方登录模型
     **/
    public OauthInfo updateOauth(OauthInfo oauthInfo) {
        try {
            return oauths.updateOauth(oauthInfo);
        } catch (Exception e) {
            logs.write(e, "更新一条第三方登录数据异常");
        }

        return null;
    }

    /**
     * 删除一条第三方登录数据
     *
     * @param id 第三方登录模型
     **/
    public void deleteOauthById(Integer id) {
        try {
            oauths.deleteOauthById(id);
        } catch (Exception e) {
            logs.write(e, "删除一条第三方登录数据异常");
        }
    }

    /**
     * 批量删除一批第三方登录数据
     **/
    public void deleteOauthByIdList(String idList) {
        try {
            oauths.deleteOauthByIdList(idList);
        } catch (Exception e) {
            logs.write(e, "批量删除一批第三方登录数据异常");
        }
    }

    /**
     * 获取一条第三方登录数据
     *
     * @param id 第三方登录模型
     **/
    public OauthInfo getOauthById(Integer id) {
        try {
            return oauths.getOauthById(id);
        } catch (Exception e) {
            logs.write(e, "获取一条第三方登录数据");
        }

        return null;
    }


    /**
     * 获得第三方登录数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回OauthInfo
     **/
    public List<OauthInfo> getOauthList(Specification<OauthInfo> condition, Sort sort) {

        List<OauthInfo> oauthList = new ArrayList<OauthInfo>();

        try {
            oauthList = oauths.getOauthList(condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得第三方登录数据列表异常");
        }

        return oauthList;
    }


    /**
     * 获得第三方登录数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回OauthInfo
     **/
    public Page<OauthInfo> getOauthList(Integer pageSize, Integer pageNumber, Specification<OauthInfo> condition, Sort sort) {

        Page<OauthInfo> oauthList = null;

        try {
            oauthList = oauths.getOauthList(pageSize, pageNumber, condition, sort);
        } catch (Exception e) {
            logs.write(e, "获得第三方登录数据列表异常");
        }

        return oauthList;
    }

    /**
     * 通过openid获取开发登录信息
     *
     * @param server 服务
     * @param openId openid
     * @return 返回开发信息
     */
    public OauthInfo getOauthByOpenId(String server, String openId) {
        try {
            return oauths.getOauthByOpenId(server, openId);
        } catch (Exception ex) {
            logs.write(ex, "通过openid获取开发登录信息");
        }
        return null;
    }

    /**
     * 通过unionId获取授权列表
     *
     * @param server
     * @param unionId
     * @return
     */
    public List<OauthInfo> getOauthListByUnionId(String server, String unionId) {

        List<OauthInfo> oauthInfoList = new ArrayList<>();

        try {
            if (StringHelper.isNotNullOrWhiteSpace(unionId)) {
                oauthInfoList = oauths.getOauthListByUnionId(server, unionId);
            }


        } catch (Exception ex) {

            logs.write(ex, "通过unionId获取授权列表");
        }

        return oauthInfoList;
    }


    /**
     * 通过unionId和openId获取授权
     *
     * @param server  服务
     * @param openId  开发id
     * @param unionId 联合id
     * @return
     * @throws IOException
     */
    public OauthInfo getOauthByOpenIdAndUnionId(String server, String openId, String unionId){

        OauthInfo oauthInfo = null;

        try {

            if (StringHelper.isNotNullOrWhiteSpace(unionId)) {
                oauthInfo = oauths.getOauthByOpenIdAndUnionId(server, openId, unionId);
            } else {
                oauthInfo = getOauthByOpenId(server, openId);
            }
        } catch (Exception ex) {

            logs.write(ex, "通过unionId和openId获取授权");
        }

        return oauthInfo;
    }

    /**
     * 通过uid获取Oauth列表
     *
     * @param uid 用户uid
     * @return
     */
    public List<OauthInfo> getOauthByUid(Integer uid) {

        return getOauthByUid("wechat", uid);
    }

    public OauthInfo getOneOauthByUid(Integer uid) {

        List<OauthInfo> oauthInfoList = getOauthByUid(uid);

        if (oauthInfoList.size() >= 0) {
            return oauthInfoList.get(0);
        }

        return null;
    }

    /**
     * 通过uid获取Oauth列表
     *
     * @param server server
     * @param uid    用户uid
     * @return
     */
    public List<OauthInfo> getOauthByUid(String server, Integer uid) {

        List<OauthInfo> oauthInfoList = new ArrayList<>();

        try {

            oauthInfoList = oauths.getOauthByUid(server, uid);

        } catch (Exception ex) {


        }

        return oauthInfoList;
    }

//    /**
//     * 通过openid获取开发者登录信息
//     *
//     * @param server  服务
//     * @param openId  openid
//     * @param unionId 联合id
//     * @return
//     */
//    public OauthInfo getOauthByOpenId(String server, String openId, String unionId) {
//
//        try {
//            return oauths.getOauthByOpenIdAnd(server, openId, unionId);
//        } catch (Exception ex) {
//            logs.write(ex, "通过openid获取开发者登录信息");
//        }
//        return null;
//    }


    //endregion

}
