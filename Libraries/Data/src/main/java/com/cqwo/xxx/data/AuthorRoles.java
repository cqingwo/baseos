package com.cqwo.xxx.data;

import com.cqwo.xxx.core.cache.CacheKeys;
import com.cqwo.xxx.core.domain.authors.AuthorRoleInfo;
import com.cqwo.xxx.core.cache.CacheKeys;
import com.cqwo.xxx.core.domain.authors.AuthorRoleInfo;
import com.cqwo.xxx.core.cache.CacheKeys;
import com.cqwo.xxx.core.domain.authors.AuthorRoleInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by cqnews on 2017/4/11.
 */


//用户分组表
@Service(value = "AuthorRolesData")
public class AuthorRoles extends DataService {


    //region  用户分组表方法

    /**
     * 获得用户分组表数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getAuthorRoleCount(Specification<AuthorRoleInfo> condition) throws IOException {
        return getCwmData().getIAuthorStrategy().getAuthorRoleCount(condition);
    }

    /**
     * 创建一条用户分组表数据
     *
     * @param authorroleInfo 用户分组表模型
     * @return 返回创建信息
     **/
    public AuthorRoleInfo createAuthorRole(AuthorRoleInfo authorroleInfo) throws IOException {
        return getCwmData().getIAuthorStrategy().createAuthorRole(authorroleInfo);
    }

    /**
     * 更新一条用户分组表数据
     *
     * @param authorroleInfo 用户分组表模型
     **/
    public AuthorRoleInfo updateAuthorRole(AuthorRoleInfo authorroleInfo) throws IOException {
        return getCwmData().getIAuthorStrategy().updateAuthorRole(authorroleInfo);
    }

    /**
     * 删除一条用户分组表数据
     *
     * @param roleId 用户分组表模型
     **/
    public void deleteAuthorRoleByRoleId(Integer roleId) throws IOException {
        getCwmData().getIAuthorStrategy().deleteAuthorRoleByRoleId(roleId);
    }

    /**
     * 批量删除一批用户分组表数据
     **/
    public void deleteAuthorRoleByRoleIdList(String roleIdList) throws IOException {
        getCwmData().getIAuthorStrategy().deleteAuthorRoleByRoleIdList(roleIdList);
    }

    /**
     * 获取一条用户分组表数据
     *
     * @param roleId 用户分组表模型
     **/
    public AuthorRoleInfo getAuthorRoleByRoleId(Integer roleId) throws IOException {
        return getCwmData().getIAuthorStrategy().getAuthorRoleByRoleId(roleId);
    }


    /**
     * 获得用户分组表数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回AuthorRoleInfo
     **/
    public List<AuthorRoleInfo> getAuthorRoleList(Specification<AuthorRoleInfo> condition, Sort sort) throws IOException {
        return getCwmData().getIAuthorStrategy().getAuthorRoleList(condition, sort);
    }


    /**
     * 获得用户分组表数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回AuthorRoleInfo
     **/
    public Page<AuthorRoleInfo> getAuthorRoleList(Integer pageSize, Integer pageNumber, Specification<AuthorRoleInfo> condition, Sort sort) throws IOException {
        return getCwmData().getIAuthorStrategy().getAuthorRoleList(pageSize, pageNumber, condition, sort);
    }

    /**
     * 获取用户的角色权限
     *
     * @param uid
     */
    public List<AuthorRoleInfo> getUserAuthorRoleList(Integer uid) throws IOException {

        List<AuthorRoleInfo> authorRoleInfoList = getCwmCache().getListValue(CacheKeys.GET_USER_AUTHOR_ROLE_LIST + uid, AuthorRoleInfo.class);

        if (authorRoleInfoList == null) {
            authorRoleInfoList = getCwmData().getIAuthorStrategy().getUserAuthorRoleList(uid);
            getCwmCache().setListValue(CacheKeys.GET_USER_AUTHOR_ROLE_LIST + uid, authorRoleInfoList);
        }
        return authorRoleInfoList;
    }



    //endregion

}
