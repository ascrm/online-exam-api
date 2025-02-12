package com.ascrm.entity.DTO;

import com.ascrm.entity.PageParams;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: ascrm
 * @Date: 2025/2/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserDTO extends PageParams {
    private Integer id;
    private String username;
    private String password;
    private Integer role;
    private String nickName;
    private Integer gender;
    private String avatar;
    private String email;
    private String phone;
    private String createdBy;
    private String createdAt;
    private Integer isDelete;
}
