package com.cqwo.xxx.strategy.rdbs.service;

import com.cqwo.xxx.core.data.rdbs.ILog2Strategy;
import com.cqwo.xxx.core.data.rdbs.repository.authors.AuthorLogRepository;
import com.cqwo.xxx.core.data.rdbs.repository.users.CreditLogRepository;
import com.cqwo.xxx.core.data.rdbs.repository.users.LoginFailLogRepository;
import com.cqwo.xxx.core.domain.authors.AuthorLogInfo;
import com.cqwo.xxx.core.domain.users.CreditLogInfo;
import com.cqwo.xxx.core.domain.users.LoginFailLogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component(value = "Log2Strategy")
public class Log2Strategy implements ILog2Strategy {

    @Autowired
    AuthorLogRepository authorLogRepository;

    //region 管理员日志

    /**
     * 获得管理员日志数量
     *
     * @param condition 条件
     * @return 返回数量
     * @throws IOException
     **/
    @Override
    public long getAuthorLogCount(Specification<AuthorLogInfo> condition) throws IOException {

        return authorLogRepository.count();
    }


    /**
     * 创建一条管理员日志数据
     *
     * @param AuthorLogInfo 管理员日志模型
     * @return 返回创建信息
     * @throws IOException
     **/
    @Override
    public AuthorLogInfo createAuthorLog(AuthorLogInfo AuthorLogInfo) throws IOException {

        return authorLogRepository.save(AuthorLogInfo);
    }


    /**
     * 更新一条管理员日志数据
     *
     * @param AuthorLogInfo 管理员日志模型
     **/
    @Override
    public AuthorLogInfo updateAuthorLog(AuthorLogInfo AuthorLogInfo) throws IOException {

        if (AuthorLogInfo.getAdminGid() >= 1) {
            return authorLogRepository.save(AuthorLogInfo);
        }

        return AuthorLogInfo;

    }


    /**
     * 删除一条管理员日志数据
     *
     * @param logid 管理员日志模型
     **/
    @Override
    public void deleteAuthorLogByLogid(Integer logid) throws IOException {

        authorLogRepository.deleteById(logid);
    }

    /**
     * 批量删除一批管理员日志数据
     **/
    @Override
    public void deleteAuthorLogByLogidList(String logidlist) throws IOException {


    }

    /**
     * 获得管理员日志一条记录
     *
     * @param logid logid
     * @return 返回一条AuthorLogInfo
     **/
    @Override
    public AuthorLogInfo getAuthorLogByLogid(Integer logid) throws IOException {
        return authorLogRepository.findById(logid).get();
    }


    /**
     * 获得管理员日志数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回AuthorLogInfo
     **/
    @Override
    public List<AuthorLogInfo> getAuthorLogList(Specification<AuthorLogInfo> condition, Sort sort) throws IOException {


        if (sort == null) {
            sort = new Sort(Sort.Direction.DESC, "logId");
        }

        return authorLogRepository.findAll(condition, sort);

    }


    /**
     * 获得管理员日志数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回AuthorLogInfo
     **/
    @Override
    public Page<AuthorLogInfo> getAuthorLogList(Integer pageSize, Integer pageNumber, Specification<AuthorLogInfo> condition, Sort sort) throws IOException {


        if (sort == null) {
            sort = new Sort(Sort.Direction.DESC, "logId");
        }

        if (pageNumber >= 1) {
            pageNumber--;
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        return authorLogRepository.findAll(condition, pageable);


    }


    //endregion


    @Autowired
    CreditLogRepository creditLogRepository;

    //region 积分日志

    /**
     * 获得积分日志数量
     *
     * @param condition 条件
     * @return 返回数量
     * @throws IOException
     **/
    @Override
    public long getCreditLogCount(Specification<CreditLogInfo> condition) throws IOException {

        return creditLogRepository.count();
    }


    /**
     * 创建一条积分日志数据
     *
     * @param creditlogInfo 积分日志模型
     * @return 返回创建信息
     * @throws IOException
     **/
    @Override
    public CreditLogInfo createCreditLog(CreditLogInfo creditlogInfo) throws IOException {

        return creditLogRepository.save(creditlogInfo);
    }


    /**
     * 更新一条积分日志数据
     *
     * @param creditLogInfo 积分日志模型
     **/
    @Override
    public CreditLogInfo updateCreditLog(CreditLogInfo creditLogInfo) throws IOException {

        if (creditLogInfo.getLogId() >= 1) {
            return creditLogRepository.save(creditLogInfo);
        }

        return creditLogInfo;

    }


    /**
     * 删除一条积分日志数据
     *
     * @param logid 积分日志模型
     **/
    @Override
    public void deleteCreditLogByLogid(Integer logid) throws IOException {

        creditLogRepository.deleteById(logid);
    }

    /**
     * 批量删除一批积分日志数据
     **/
    @Override
    public void deleteCreditLogByLogidList(String logidlist) throws IOException {


    }

    /**
     * 获得积分日志一条记录
     *
     * @param logid logid
     * @return 返回一条CreditLogInfo
     **/
    @Override
    public CreditLogInfo getCreditLogByLogid(Integer logid) throws IOException {
        return creditLogRepository.findById(logid).get();
    }


    /**
     * 获得积分日志数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回CreditLogInfo
     **/
    @Override
    public List<CreditLogInfo> getCreditLogList(Specification<CreditLogInfo> condition, Sort sort) throws IOException {


        if (sort == null) {
            sort = new Sort(Sort.Direction.DESC, "logId");
        }

        return creditLogRepository.findAll(condition, sort);

    }


    /**
     * 获得积分日志数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回CreditLogInfo
     **/
    @Override
    public Page<CreditLogInfo> getCreditLogList(Integer pageSize, Integer pageNumber, Specification<CreditLogInfo> condition, Sort sort) throws IOException {


        if (sort == null) {
            sort = new Sort(Sort.Direction.DESC, "logId");
        }

        if (pageNumber >= 1) {
            pageNumber--;
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        return creditLogRepository.findAll(condition, pageable);


    }


    //endregion

    @Autowired
    LoginFailLogRepository loginFailLogRepository;


    //region 登录日志

    /**
     * 获得登录日志数量
     *
     * @param condition 条件
     * @return 返回数量
     * @throws IOException
     **/
    @Override
    public long getLoginFailLogCount(Specification<LoginFailLogInfo> condition) throws IOException {

        return loginFailLogRepository.count();
    }


    /**
     * 创建一条登录日志数据
     *
     * @param loginfaillogInfo 登录日志模型
     * @return 返回创建信息
     * @throws IOException
     **/
    @Override
    public LoginFailLogInfo createLoginFailLog(LoginFailLogInfo loginfaillogInfo) throws IOException {

        return loginFailLogRepository.save(loginfaillogInfo);
    }


    /**
     * 更新一条登录日志数据
     *
     * @param loginFailLogInfo 登录日志模型
     **/
    @Override
    public LoginFailLogInfo updateLoginFailLog(LoginFailLogInfo loginFailLogInfo) throws IOException {

        if (loginFailLogInfo.getId() >= 1) {
            return loginFailLogRepository.save(loginFailLogInfo);
        }

        return loginFailLogInfo;

    }


    /**
     * 删除一条登录日志数据
     *
     * @param id 登录日志模型
     **/
    @Override
    public void deleteLoginFailLogById(Integer id) throws IOException {

        loginFailLogRepository.deleteById(id);
    }

    /**
     * 批量删除一批登录日志数据
     **/
    @Override
    public void deleteLoginFailLogByIdList(String idlist) throws IOException {


    }

    /**
     * 获得登录日志一条记录
     *
     * @param id id
     * @return 返回一条LoginFailLogInfo
     **/
    @Override
    public LoginFailLogInfo getLoginFailLogById(Integer id) throws IOException {
        return loginFailLogRepository.findById(id).get();
    }


    /**
     * 获得登录日志数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回LoginFailLogInfo
     **/
    @Override
    public List<LoginFailLogInfo> getLoginFailLogList(Specification<LoginFailLogInfo> condition, Sort sort) throws IOException {


        if (sort == null) {
            sort = new Sort(Sort.Direction.DESC, "id");
        }

        return loginFailLogRepository.findAll(condition, sort);

    }


    /**
     * 获得登录日志数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回LoginFailLogInfo
     **/
    @Override
    public Page<LoginFailLogInfo> getLoginFailLogList(Integer pageSize, Integer pageNumber, Specification<LoginFailLogInfo> condition, Sort sort) throws IOException {


        if (sort == null) {
            sort = new Sort(Sort.Direction.DESC, "id");
        }

        if (pageNumber >= 1) {
            pageNumber--;
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        return loginFailLogRepository.findAll(condition, pageable);


    }

    /**
     * 删除登录ip地址
     *
     * @param ip
     */
    @Override
    public void deleteLoginFailLogByIP(Integer ip) throws IOException {
        loginFailLogRepository.deleteLoginFailLogByIP(ip);
    }


    //endregion
}
