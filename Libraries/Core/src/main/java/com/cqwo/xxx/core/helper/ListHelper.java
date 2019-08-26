package com.cqwo.xxx.core.helper;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class ListHelper {


    // 删除ArrayList中重复元素，保持顺序
    public static <T> void removeDuplicateWithOrder(List<T> list) {

        Set<T> set = Sets.newHashSet();

        List<T> newList = Lists.newArrayList();

        for (T t : list) {
            if (set.add(t)) {
                newList.add(t);
            }
        }
        list.clear();
        list.addAll(newList); //System.out.println(" remove duplicate " + list);
    }
}
