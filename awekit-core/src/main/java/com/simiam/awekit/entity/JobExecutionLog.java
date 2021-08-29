package com.simiam.awekit.entity;

import java.util.Date;

/**
 * <p>Title: JobExecutionLog</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.CMCC Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/5/8 8:34</p>
 */
public class JobExecutionLog {
    private String jobName;
    private Long scheduleFlag;
    private Date startTime;
    private Date endTime;
    private Boolean success;

    public JobExecutionLog() {

    }

    public JobExecutionLog(String jobName, Long scheduleFlag) {
        this.jobName = jobName;
        this.scheduleFlag = scheduleFlag;
    }

    public JobExecutionLog(String jobName, Long scheduleFlag, Date startTime, Date endTime, Boolean success) {
        this.jobName = jobName;
        this.scheduleFlag = scheduleFlag;
        this.startTime = startTime;
        this.endTime = endTime;
        this.success = success;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getScheduleFlag() {
        return scheduleFlag;
    }

    public void setScheduleFlag(Long scheduleFlag) {
        this.scheduleFlag = scheduleFlag;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
