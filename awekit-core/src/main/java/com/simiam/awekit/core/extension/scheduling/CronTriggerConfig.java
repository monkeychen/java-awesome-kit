package com.simiam.awekit.core.extension.scheduling;

/**
 * <p>Title: CronTriggerConfig</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/7/22 11:37</p>
 */
public class CronTriggerConfig {
    private String triggerName;

    private String cron;

    private Boolean enable;

    private String app;

    public CronTriggerConfig() {

    }

    public CronTriggerConfig(String cron, Boolean enable) {
        this.cron = cron;
        this.enable = enable;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }
}
