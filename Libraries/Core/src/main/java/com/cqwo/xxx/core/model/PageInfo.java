package com.cqwo.xxx.core.model;

import com.cqwo.xxx.core.model.PageModel;
import lombok.*;

import java.util.List;

/**
 * 分页模型
 *
 * @param <T>
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageInfo<T> {

    /**
     * 分页模型
     */
    private PageModel pageModel;

    /**
     * 列表
     */
    private List<T> list;
}
