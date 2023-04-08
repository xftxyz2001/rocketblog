package com.xftxyz.rocketblog.parameter;

public class ChatMessageBody {
    /**
     * 目标用户ID
     */
    private Long to;
    /**
     * 消息内容
     */
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
