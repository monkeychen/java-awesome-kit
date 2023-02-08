package com.simiam.awekit.entity;

import com.google.common.base.MoreObjects;

/**
 * <p>Title: Notification</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2020</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2020/2/29 1:31 下午</p>
 */
public class Notification {
    protected String sender;

    protected String receiver;

    protected String content;

    public Notification() {

    }

    public Notification(String sender, String receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sender", sender)
                .add("receiver", receiver)
                .add("content", content)
                .toString();
    }
}
