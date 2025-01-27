package com.ascrm.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.util.Date;
import java.lang.String;
import java.lang.Integer;

/**
 * 实体类。
 *
 * @author ascrm
 * @since 1.0
 */
@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "tb_user")
public class User {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 账号
     */
    @Column(value = "username")
    private String username;

    /**
     * 密码
     */
    @Column(value = "password")
    private String password;

    /**
     * 昵称
     */
    @Column(value = "nick_name")
    private String nickName;

    /**
     * 性别: 1男;2女
     */
    @Column(value = "gender")
    private Integer gender;

    /**
     * 头像
     */
    @Column(value = "avatar")
    private String avatar;

    /**
     * 邮箱
     */
    @Column(value = "email")
    private String email;

    /**
     * 电话
     */
    @Column(value = "phone")
    private String phone;

    /**
     * 权限: 1 管理员; 2 用户
     */
    @Column(value = "permission")
    private Integer permission;

    /**
     * 创建人
     */
    @Column(value = "created_by")
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(value = "created_at")
    private Date createdAt;

    /**
     * 逻辑删除 1被删除;0未被删除
     */
    @Column(value = "is_delete")
    private Integer isDelete;


}
