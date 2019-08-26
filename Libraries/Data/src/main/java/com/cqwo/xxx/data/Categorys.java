package com.cqwo.xxx.data;

import com.cqwo.xxx.core.cache.CacheKeys;
import com.cqwo.xxx.core.domain.base.CategoryInfo;
import com.cqwo.xxx.core.cache.CacheKeys;
import com.cqwo.xxx.core.domain.base.CategoryInfo;
import com.cqwo.xxx.core.cache.CacheKeys;
import com.cqwo.xxx.core.domain.base.CategoryInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by cqnews on 2017/4/11.
 */


//发现分类
@Service(value = "CategorysData")
public class Categorys extends DataService {


    //region  发现分类方法

    /**
     * 获得发现分类数量
     *
     * @param condition 条件
     * @return 返回数量
     **/
    public long getCategoryCount(Specification<CategoryInfo> condition) throws IOException {
        return getCwmData().getIBaseStrategy().getCategoryCount(condition);
    }

    /**
     * 创建一条发现分类数据
     *
     * @param CategoryInfo 发现分类模型
     * @return 返回创建信息
     **/
    public CategoryInfo createCategory(CategoryInfo CategoryInfo) throws IOException {
        return getCwmData().getIBaseStrategy().createCategory(CategoryInfo);
    }

    /**
     * 更新一条发现分类数据
     *
     * @param CategoryInfo 发现分类模型
     **/
    public CategoryInfo updateCategory(CategoryInfo CategoryInfo) throws IOException {
        return getCwmData().getIBaseStrategy().updateCategory(CategoryInfo);
    }

    /**
     * 删除一条发现分类数据
     *
     * @param cateId 发现分类模型
     **/
    public void deleteCategoryByCateId(Integer cateId) throws IOException {
        getCwmData().getIBaseStrategy().deleteCategoryByCateId(cateId);
    }

    /**
     * 批量删除一批发现分类数据
     **/
    public void deleteCategoryByCateIdList(String cateIdList) throws IOException {
        getCwmData().getIBaseStrategy().deleteCategoryByCateIdList(cateIdList);
    }

    /**
     * 获取一条发现分类数据
     *
     * @param cateId 发现分类模型
     **/
    public CategoryInfo getCategoryByCateId(Integer cateId) throws IOException {

        CategoryInfo categoryInfo = getCwmCache().getValue(CacheKeys.CATEGORY_CATEGORYINFO_CATEID + cateId, CategoryInfo.class);


        if (categoryInfo == null) {

            categoryInfo = getCwmData().getIBaseStrategy().getCategoryByCateId(cateId);
            getCwmCache().setValue(CacheKeys.CATEGORY_CATEGORYINFO_CATEID + cateId, categoryInfo);
        }

        return categoryInfo;
    }


    /**
     * 获得发现分类数据列表
     *
     * @param condition 条件
     * @param sort      排序
     * @return 返回CategoryInfo
     **/
    public List<CategoryInfo> getCategoryList(Specification<CategoryInfo> condition, Sort sort) throws IOException {
        return getCwmData().getIBaseStrategy().getCategoryList(condition, sort);
    }


    /**
     * 获得发现分类数据列表
     *
     * @param pageSize   每页数
     * @param pageNumber 当前页数
     * @param condition  条件
     * @param sort       排序
     * @return 返回CategoryInfo
     **/
    public Page<CategoryInfo> getCategoryList(Integer pageSize, Integer pageNumber, Specification<CategoryInfo> condition, Sort sort) throws IOException {
        return getCwmData().getIBaseStrategy().getCategoryList(pageSize, pageNumber, condition, sort);
    }


    /**
     * 获取所有分类
     *
     * @return
     */
    public List<CategoryInfo> getAllCateInfoList() throws IOException {

        List<CategoryInfo> categoryInfoList = getCwmCache().getListValue(CacheKeys.CATEGORY_ALLCATEINFOLIST, CategoryInfo.class);

        if (categoryInfoList == null) {

            categoryInfoList = getCwmData().getIBaseStrategy().getAllCateInfoList();
            getCwmCache().setListValue(CacheKeys.CATEGORY_ALLCATEINFOLIST, categoryInfoList);
        }

        return categoryInfoList;
    }

    /**
     * 获得发现分类数据列表
     *
     * @param typeId
     * @param parentId
     * @return
     */
    public List<CategoryInfo> getCategoryList(Integer typeId, Integer parentId) throws Exception {

        List<CategoryInfo> categoryInfoList = getCwmCache().getListValue(CacheKeys.CATEGORY_TYPEID_PARENTID_LIST + typeId + parentId, CategoryInfo.class);

        if (categoryInfoList == null) {

            categoryInfoList = getCwmData().getIBaseStrategy().getCategoryList(typeId, parentId);
            getCwmCache().setListValue(CacheKeys.CATEGORY_TYPEID_PARENTID_LIST + typeId + parentId, categoryInfoList);
        }


        return categoryInfoList;
    }

    /**
     * 发现分类条件
     *
     * @param typeId   分类
     * @param parentId 上级id
     * @return
     */
    public Specification<CategoryInfo> getCateListCondition(Integer typeId, Integer parentId) throws Exception {
        return getCwmData().getIBaseStrategy().getCateListCondition(typeId, parentId);
    }


    //endregion

}
