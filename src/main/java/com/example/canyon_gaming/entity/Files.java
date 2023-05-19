package com.example.canyon_gaming.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName files
 */
@TableName(value ="files")
@Data
public class Files implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String fileurl;

    /**
     * 
     */
    private String md5;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}