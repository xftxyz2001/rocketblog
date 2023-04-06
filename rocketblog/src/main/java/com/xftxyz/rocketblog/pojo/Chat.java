package com.xftxyz.rocketblog.pojo;

import java.util.Date;

public class Chat {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.chat_id
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    private Long chatId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.userid_from
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    private Long useridFrom;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.userid_to
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    private Long useridTo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.message_content
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    private String messageContent;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.createtime
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_chat.readed
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    private Integer readed;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.chat_id
     *
     * @return the value of t_chat.chat_id
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public Long getChatId() {
        return chatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.chat_id
     *
     * @param chatId the value for t_chat.chat_id
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.userid_from
     *
     * @return the value of t_chat.userid_from
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public Long getUseridFrom() {
        return useridFrom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.userid_from
     *
     * @param useridFrom the value for t_chat.userid_from
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public void setUseridFrom(Long useridFrom) {
        this.useridFrom = useridFrom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.userid_to
     *
     * @return the value of t_chat.userid_to
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public Long getUseridTo() {
        return useridTo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.userid_to
     *
     * @param useridTo the value for t_chat.userid_to
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public void setUseridTo(Long useridTo) {
        this.useridTo = useridTo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.message_content
     *
     * @return the value of t_chat.message_content
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.message_content
     *
     * @param messageContent the value for t_chat.message_content
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.createtime
     *
     * @return the value of t_chat.createtime
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.createtime
     *
     * @param createtime the value for t_chat.createtime
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_chat.readed
     *
     * @return the value of t_chat.readed
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public Integer getReaded() {
        return readed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_chat.readed
     *
     * @param readed the value for t_chat.readed
     *
     * @mbg.generated Fri Apr 07 01:04:13 CST 2023
     */
    public void setReaded(Integer readed) {
        this.readed = readed;
    }
}