package com.xftxyz.rocketblog.parameter;

import com.xftxyz.rocketblog.config.EnvironmentVariables;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ChatMessageBody {
    /**
     * 目标用户ID
     */
    @Min(value = 1, message = "目标用户ID不合法")
    private Long to;
    /**
     * 消息内容
     */
    @NotNull(message = "消息内容不能为空")
    @Size(max = EnvironmentVariables.MAX_LENGTH, message = "消息内容不能超过" + EnvironmentVariables.MAX_LENGTH + "个字符")
    private String content;

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
