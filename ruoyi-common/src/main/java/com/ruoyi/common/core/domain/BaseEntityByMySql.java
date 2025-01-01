package com.ruoyi.common.core.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: lb
 * @Date: 2021/8/24 16:39
 */
@Data
@MappedSuperclass
public class BaseEntityByMySql extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint(20) comment 'id'")
    private Long id;
}
