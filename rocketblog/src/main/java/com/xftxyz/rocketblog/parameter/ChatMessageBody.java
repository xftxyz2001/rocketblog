package com.xftxyz.rocketblog.parameter;

import com.xftxyz.rocketblog.validation.ValidInfo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ChatMessageBody {
    /**
     * 目标用户ID
     */
    @NotBlank(message = ValidInfo.USER_ID_NOT_NULL)
    @Min(value = 1, message = ValidInfo.USER_ID_LESS_THAN_ONE)
    private Long to;
    /**
     * 消息内容
     */
    @NotNull(message = ValidInfo.CONTENT_NOT_NULL)
    @Size(max = ValidInfo.CONTENT_MAX_LENGTH, message = "消息内容不能超过" + ValidInfo.CONTENT_MAX_LENGTH + "个字符")
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
