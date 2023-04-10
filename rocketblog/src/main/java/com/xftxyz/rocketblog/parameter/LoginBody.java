package com.xftxyz.rocketblog.parameter;

import com.xftxyz.rocketblog.validation.ValidInfo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginBody {

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Size(min = ValidInfo.MIN_PASSWORD_LENGTH, max = ValidInfo.MAX_LENGTH, message = "密码长度必须在"
            + ValidInfo.MIN_PASSWORD_LENGTH + "-" + ValidInfo.MAX_PASSWORD_LENGTH + "位之间")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
