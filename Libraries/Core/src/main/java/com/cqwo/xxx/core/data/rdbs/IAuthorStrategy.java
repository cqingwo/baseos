package com.cqwo.xxx.core.data.rdbs;

import com.cqwo.xxx.core.domain.authors.AuthorActionInfo;
import com.cqwo.xxx.core.domain.authors.AuthorPermissionInfo;
import com.cqwo.xxx.core.domain.authors.AuthorRoleInfo;
import com.cqwo.xxx.core.domain.authors.AuthorSessionInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.io.IOException;
import java.util.List;

public interface IAuthorStrategy {

    //region 用户分组表

    /**
     * 获得用户分组表数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    long getAuthorRoleCount(Specification<AuthorRoleInfo> condition) throws IOException;


    /**
     * 创建一条用户分组表数据
     *
     * @param authorroleInfo 用户分组表模型
     * @return 返回创建信息
     **/
    AuthorRoleInfo createAuthorRole(AuthorRoleInfo authorroleInfo) throws IOException;


    /**
     * 更新一条用户分组表数据
     *
     * @param authorroleInfo 用户分组表模型
     **/
    AuthorRoleInfo updateAuthorRole(AuthorRoleInfo authorroleInfo) throws IOException;

    /**
     * 删除一条用户分组表数据
     *
     * @param roleId 用户分组表模型
     **/
    void deleteAuthorRoleByRoleId(Integer roleId) throws IOException;

    /**
     * 批量删除一批用户分组表数据
     **/
    void deleteAuthorRoleByRoleIdList(String roleIdList) throws IOException;


    /**
     * 获得用户分组表一条记录
     *
     * @param roleId roleid
     * @return 返回一条AuthorRoleInfo
     **/
    AuthorRoleInfo getAuthorRoleByRoleId(Integer roleId) throws IOException;

    /**
     * 获得用户分组表数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回AuthorRoleInfo
     **/
    List<AuthorRoleInfo> getAuthorRoleList(Specification<AuthorRoleInfo> condition, Sort sort) throws IOException;


    /**
     * 获得用户分组表数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回AuthorRoleInfo
     **/
    Page<AuthorRoleInfo> getAuthorRoleList(Integer pageSize, Integer pageNumber, Specification<AuthorRoleInfo> condition, Sort sort) throws IOException;


    /**
     * 获取用户的角色权限
     *
     * @param uid
     */
    List<AuthorRoleInfo> getUserAuthorRoleList(Integer uid) throws IOException;

    //endregion 用户分组表结束

    //region 用户-组关联表

    /**
     * 获得用户-组关联表数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    long getAuthorSessionCount(Specification<AuthorSessionInfo> condition) throws IOException;


    /**
     * 创建一条用户-组关联表数据
     *
     * @param authorsessionInfo 用户-组关联表模型
     * @return 返回创建信息
     **/
    AuthorSessionInfo createAuthorSession(AuthorSessionInfo authorsessionInfo) throws IOException;


    /**
     * 更新一条用户-组关联表数据
     *
     * @param authorsessionInfo 用户-组关联表模型
     **/
    AuthorSessionInfo updateAuthorSession(AuthorSessionInfo authorsessionInfo) throws IOException;

    /**
     * 删除一条用户-组关联表数据
     *
     * @param id 用户-组关联表模型
     **/
    void deleteAuthorSessionById(Integer id) throws IOException;

    /**
     * 批量删除一批用户-组关联表数据
     **/
    void deleteAuthorSessionByIdList(String idList) throws IOException;


    /**
     * 获得用户-组关联表一条记录
     *
     * @param id id
     * @return 返回一条AuthorSessionInfo
     **/
    AuthorSessionInfo getAuthorSessionById(Integer id) throws IOException;

    /**
     * 获得用户-组关联表数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回AuthorSessionInfo
     **/
    List<AuthorSessionInfo> getAuthorSessionList(Specification<AuthorSessionInfo> condition, Sort sort) throws IOException;


    /**
     * 获得用户-组关联表数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回AuthorSessionInfo
     **/
    Page<AuthorSessionInfo> getAuthorSessionList(Integer pageSize, Integer pageNumber, Specification<AuthorSessionInfo> condition, Sort sort) throws IOException;


    //endregion 用户-组关联表结束

    //region 角色许可表

    /**
     * 获得角色许可表数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    long getAuthorPermissionCount(Specification<AuthorPermissionInfo> condition) throws IOException;


    /**
     * 创建一条角色许可表数据
     *
     * @param authorpermissionInfo 角色许可表模型
     * @return 返回创建信息
     **/
    AuthorPermissionInfo createAuthorPermission(AuthorPermissionInfo authorpermissionInfo) throws IOException;


    /**
     * 更新一条角色许可表数据
     *
     * @param authorpermissionInfo 角色许可表模型
     **/
    AuthorPermissionInfo updateAuthorPermission(AuthorPermissionInfo authorpermissionInfo) throws IOException;

    /**
     * 删除一条角色许可表数据
     *
     * @param id 角色许可表模型
     **/
    void deleteAuthorPermissionById(Integer id) throws IOException;

    /**
     * 批量删除一批角色许可表数据
     **/
    void deleteAuthorPermissionByIdList(String idList) throws IOException;


    /**
     * 获得角色许可表一条记录
     *
     * @param id id
     * @return 返回一条AuthorPermissionInfo
     **/
    AuthorPermissionInfo getAuthorPermissionById(Integer id) throws IOException;

    /**
     * 获得角色许可表数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回AuthorPermissionInfo
     **/
    List<AuthorPermissionInfo> getAuthorPermissionList(Specification<AuthorPermissionInfo> condition, Sort sort) throws IOException;


    /**
     * 获得角色许可表数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回AuthorPermissionInfo
     **/
    Page<AuthorPermissionInfo> getAuthorPermissionList(Integer pageSize, Integer pageNumber, Specification<AuthorPermissionInfo> condition, Sort sort) throws IOException;


    //endregion 角色许可表结束

    //region 动作管理

    /**
     * 获得动作管理数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    long getAuthorActionCount(Specification<AuthorActionInfo> condition) throws IOException;


    /**
     * 创建一条动作管理数据
     *
     * @param authorActionInfo 动作管理模型
     * @return 返回创建信息
     **/
    AuthorActionInfo createAuthorAction(AuthorActionInfo authorActionInfo) throws IOException;


    /**
     * 更新一条动作管理数据
     *
     * @param authorActionInfo 动作管理模型
     **/
    AuthorActionInfo updateAuthorAction(AuthorActionInfo authorActionInfo) throws IOException;

    /**
     * 删除一条动作管理数据
     *
     * @param aid 动作管理模型
     **/
    void deleteAuthorActionByAid(Integer aid) throws IOException;

    /**
     * 批量删除一批动作管理数据
     **/
    void deleteAuthorActionByAidList(String aidList) throws IOException;


    /**
     * 获得动作管理一条记录
     *
     * @param aid aid
     * @return 返回一条AuthorActionInfo
     **/
    AuthorActionInfo getAuthorActionByAid(Integer aid) throws IOException;

    /**
     * 获得动作管理数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回AuthorActionInfo
     **/
    List<AuthorActionInfo> getAuthorActionList(Specification<AuthorActionInfo> condition, Sort sort) throws IOException;


    /**
     * 获得动作管理数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回AuthorActionInfo
     **/
    Page<AuthorActionInfo> getAuthorActionList(Integer pageSize, Integer pageNumber, Specification<AuthorActionInfo> condition, Sort sort) throws IOException;


    /**
     * 获取角色的分组信息
     *
     * @param roleId
     * @return
     */
    List<AuthorActionInfo> getRoleAuthorActionList(Integer roleId) throws IOException;


    /**
     * 获取所有的节点列表
     *
     * @param group 分组名称
     * @return
     */
    List<AuthorActionInfo> getGroupAuthorActionList(String group) throws IOException;

    /**
     * 获取所有的节点列表
     *
     * @return
     */
    List<AuthorActionInfo> getAllAuthorActionList() throws IOException;



    //endregion 动作管理结束

}
