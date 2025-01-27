package com.ascrm.viewer;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.lang.String;
import java.lang.Integer;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserViewer {

    private Integer id;
    private String username;
    private String password;
    private String role;
    private String nickName;
    private String gender;
    private String avatar;
    private String email;
    private String phone;
    private String createdBy;
    private String createdAt;
    private Integer isDelete;
}
