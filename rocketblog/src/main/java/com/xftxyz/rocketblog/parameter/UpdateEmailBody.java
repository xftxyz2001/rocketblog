package com.xftxyz.rocketblog.parameter;

import com.xftxyz.rocketblog.config.EnvironmentVariables;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateEmailBody {

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