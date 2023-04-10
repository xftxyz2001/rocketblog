package com.xftxyz.rocketblog.parameter;

import com.xftxyz.rocketblog.config.EnvironmentVariables;
import com.xftxyz.rocketblog.validation.ValidInfo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterBody {
    /**
     * 用户名（昵称）
     */
    private String name;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    @Size(min = ValidInfo.MIN_PASSWORD_LENGTH, max = ValidInfo.MAX_PASSWORD_LENGTH, message = "密码长度必须在"
            + ValidInfo.MIN_PASSWORD_LENGTH + "-" + ValidInfo.MAX_PASSWORD_LENGTH + "位之间")
    private String password;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    @Size(min = EnvironmentVariables.CODE_LENGTH, max = EnvironmentVariables.CODE_LENGTH, message = "验证码长度不正确（"
            + EnvironmentVariables.CODE_LENGTH + "位）")
    private String vertify;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVertify() {
        return vertify;
    }

    public void setVertify(String vertify) {
        this.vertify = vertify;
    }

}
