package com.example.pricesscraper.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName items
 */
@TableName(value ="items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Items {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 磨损度
     */
    private String abrasion;

    /**
     * 售价
     */
    private BigDecimal price;

    /**
     * 求购价
     */
    private BigDecimal seekPrice;

    /**
     * 当前在售（件）
     */
    private Integer onSale;

    /**
     * 当前求购（件）
     */
    private Integer onSeek;

    /**
     * 
     */
    private Date createdAt;
}