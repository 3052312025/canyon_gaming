package com.example.canyon_gaming.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author sen
 * @since 2023-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("msg")
@NoArgsConstructor
@AllArgsConstructor
public class Msg implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评论内容
     */
    private String comment;

    /**
     * 评论的人
     */
    private String username;

    private String avatarUrl;

    /**
     * 评论时间
     */
    private LocalDateTime time;

    /**
     * 评论对象的id(最上的一)
     */
    private Integer pid;

    /**
     * 回复的对象
     */
    private String target;
    //表示不属于数据库的字段  @TableField(exist = false)
    private transient List<Msg> childrenMsgList;

}
