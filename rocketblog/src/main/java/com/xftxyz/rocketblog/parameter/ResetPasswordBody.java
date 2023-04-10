package com.xftxyz.rocketblog.parameter;

import com.xftxyz.rocketblog.validation.ValidInfo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ResetPasswordBody {
    /**
     * 旧密码
     */
    @NotEmpty(message = "旧密码不能为空")
    private String password;

    /**
     * 新密码
     */
    @NotEmpty(message = "新密码不能为空")
    @Size(min = ValidInfo.MIN_PASSWORD_LENGTH, max = ValidInfo.MAX_LENGTH, message = "密码长度必须在"
            + ValidInfo.MIN_PASSWORD_LENGTH + "-" + ValidInfo.MAX_PASSWORD_LENGTH + "位之间")
    private String newPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
