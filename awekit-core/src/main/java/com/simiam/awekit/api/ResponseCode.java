package com.simiam.awekit.api;

/**
 * <p>Title: ResponseCode</p>
 * <p>Description:</p>
 * <p>Copyright: FJ.SIMIAM Co., Ltd. (c) 2019</p>
 * <p>@Author: chenzhian </p>
 * <p>@Date: 2019/12/19 9:11 上午</p>
 */
public interface ResponseCode {
    Long CODE_UNKNOWN = -1L;
    Long CODE_OK = 8200L;
    Long CODE_LOGIN_OK = 8200L;
    Long CODE_LOGOUT_OK = 8200L;
    Long CODE_NOT_LOGIN = 8401L;
    Long CODE_USER_NOT_EXIST = 8404L;
    Long CODE_INVALID_PASSWORD = 8401L;
    Long CODE_USER_NOT_EXIST_OR_INVALID_PASSWORD = 8401L;
    Long CODE_LOCKED_ACCOUNT = 8404L;
    Long CODE_NOT_ENOUGH_PERMISSION = 8403L;
    Long CODE_BUSINESS_ERROR = 8500L;
    String MSG_LOGIN_OK = "登录成功！";
    String MSG_LOGOUT_OK = "注销成功！";
    String MSG_OK = "操作成功！";
    String MSG_NOT_LOGIN = "用户未登录或会话超时，请重新登录！";
    String MSG_USER_NOT_EXIST = "登录失败：用户不存在！";
    String MSG_INVALID_PASSWORD = "登录失败：无效密码！";
    String MSG_USER_NOT_EXIST_OR_INVALID_PASSWORD = "登录失败：用户不存在或无效密码！";
    String MSG_UNKNOWN = "未知原因，请联系管理员！";
    String MSG_NOT_ENOUGH_PERMISSION = "权限不足，请联系管理员！";
    String RESPONSE_BODY = "RESPONSE_BODY";
    String MSG_BUSINESS_ERROR = "业务异常";
    String MSG_FILE_NOT_EXIST = "文件不存在或已被删除！";
}
