package com.snxy.business.domain;

public class CustomerMessage {
    private Long id;

    private String content;

    private Long onlineUserId;

    private String messageAnswer;

    private Byte isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getOnlineUserId() {
        return onlineUserId;
    }

    public void setOnlineUserId(Long onlineUserId) {
        this.onlineUserId = onlineUserId;
    }

    public String getMessageAnswer() {
        return messageAnswer;
    }

    public void setMessageAnswer(String messageAnswer) {
        this.messageAnswer = messageAnswer;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}