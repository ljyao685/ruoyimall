package com.ruoyi.common.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.utils.CommonUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: lb
 * @Date: 2022/1/12 11:51
 * @desc：级联选择器实体类
 */
@Data
public class CascaderSelect implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 节点值
     */
    private Long value;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CascaderSelect> children;

    public CascaderSelect() {
    }

    public CascaderSelect(Object ob) {
        this.value = (Long) CommonUtil.getValueByKey(ob, "id");
        this.label = (String) CommonUtil.getValueByKey(ob, "name");
        List<?> children = (List) CommonUtil.getValueByKey(ob, "children");
        this.children = children.stream().map(CascaderSelect::new).collect(Collectors.toList());
    }

}
