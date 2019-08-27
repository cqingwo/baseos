package com.cqwo.xxx.core.model;

import com.cqwo.xxx.core.model.SelectListItem;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cqnews
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiSelectListItem implements Serializable {

    public SelectListItem item;

    public List<SelectListItem> itemList = Lists.newArrayList();

    public MultiSelectListItem(SelectListItem item) {
        this.item = item;
    }


}
