package com.cqwo.xxx.services;

import com.cqwo.xxx.core.domain.base.AttachmentInfo;
import com.cqwo.xxx.core.helper.StringHelper;
import com.cqwo.xxx.core.domain.base.AttachmentInfo;
import com.cqwo.xxx.core.helper.StringHelper;
import com.cqwo.xxx.core.log.Logs;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by cqnews on 2017/4/11.
 */

//附件表
@Service(value = "Attachments")
public class Attachments {

    @Resource(name = "AttachmentsData")
    com.cqwo.xxx.data.Attachments attachments;

    /**
     * 线程锁
     */
    private Lock lock = new ReentrantLock();


    @Autowired
    private Logs logs;

    //region  附件表方法

    /**
     * 获得附件表数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getAttachmentCount(Specification<AttachmentInfo> condition) {

        try {
            return attachments.getAttachmentCount(condition);
        } catch (Exception ex) {
            logs.write(ex, "获得附件表数量失败");
        }
        return 0;
    }

    /**
     * 创建一条附件表数据
     *
     * @param attachmentInfo 附件表模型
     * @return 返回创建信息
     **/
    public AttachmentInfo createAttachment(AttachmentInfo attachmentInfo) {
        try {
            return attachments.createAttachment(attachmentInfo);
        } catch (Exception ex) {
            logs.write(ex, "创建一条附件表数据失败");
        }
        return null;
    }

    /**
     * 批量创建图片
     *
     * @param uriList
     * @return
     */
    public List<AttachmentInfo> batchCreateAttchment(List<String> uriList, Integer uid, String title) {

        lock.lock();

        List<AttachmentInfo> attachmentInfoList = new ArrayList<>();

        try {

            for (String uri : uriList) {

                if (Strings.isNullOrEmpty(uri)) {
                    continue;
                }

                if (Strings.isNullOrEmpty(title)) {
                    title = "";
                }


                AttachmentInfo attachmentInfo = createAttachment(new AttachmentInfo(uri, uid, title));


                if (attachmentInfo != null && attachmentInfo.getAttachId() >= 1) {

                    attachmentInfoList.add(attachmentInfo);
                }

            }


        } catch (Exception ex) {

            logs.write(ex, "批量创建附件发生故障");

        } finally {
            lock.unlock();
        }

        return attachmentInfoList;

    }



    /**
     * 更新一条附件表数据
     *
     * @param attachmentInfo 附件表模型
     **/
    public AttachmentInfo updateAttachment(AttachmentInfo attachmentInfo) {
        try {
            return attachments.updateAttachment(attachmentInfo);
        } catch (Exception ex) {
            logs.write(ex, "更新一条附件表数据异常");
        }

        return null;
    }

    /**
     * 删除一条附件表数据
     *
     * @param attachId 附件表模型
     **/
    public void deleteAttachmentByAttachId(int attachId) {
        try {
            attachments.deleteAttachmentByAttachId(attachId);
        } catch (Exception ex) {
            logs.write(ex, "删除一条附件表数据异常");
        }
    }

    /**
     * 批量删除一批附件表数据
     **/
    public void deleteAttachmentByAttachIdList(String attachIdList) {
        try {
            attachments.deleteAttachmentByAttachIdList(attachIdList);
        } catch (Exception ex) {
            logs.write(ex, "批量删除一批附件表数据异常");
        }
    }

    /**
     * 获取一条附件表数据
     *
     * @param attachId 附件表模型
     **/
    public AttachmentInfo getAttachmentByAttachId(int attachId) {
        try {
            return attachments.getAttachmentByAttachId(attachId);
        } catch (Exception ex) {
            logs.write(ex, "获取一条附件表数据");
        }

        return null;
    }


    /**
     * 获得附件表数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回AttachmentInfo
     **/
    public List<AttachmentInfo> getAttachmentList(Specification<AttachmentInfo> condition, Sort sort) {

        List<AttachmentInfo> attachmentList = new ArrayList<AttachmentInfo>();

        try {
            attachmentList = attachments.getAttachmentList(condition, sort);
        } catch (Exception ex) {
            logs.write(ex, "获得附件表数据列表异常");
        }

        return attachmentList;
    }


    /**
     * 获得附件表数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回AttachmentInfo
     **/
    public Page<AttachmentInfo> getAttachmentList(Integer pageSize, Integer pageNumber, Specification<AttachmentInfo> condition, Sort sort) {

        Page<AttachmentInfo> attachmentList = null;

        try {
            attachmentList = attachments.getAttachmentList(pageSize, pageNumber, condition, sort);
        } catch (Exception ex) {
            logs.write(ex, "获得附件表数据列表异常");
        }

        return attachmentList;
    }


    //endregion

}
