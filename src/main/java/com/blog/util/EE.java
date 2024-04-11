package com.blog.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public enum EE {
    无效的请求(1001, "无效的请求。"),
    找不到用户(1002, "找不到用户。"),
    无效的凭据(1003, "无效的凭据。"),
    访问被拒绝(1004, "访问被拒绝。"),
    资源未找到(1005, "资源未找到。"),
    方法不被允许(1006, "方法不被允许。"),
    请求冲突(1007, "请求冲突。"),
    前置条件失败(1008, "前置条件失败。"),
    请求过多(1009, "请求过多。"),
    不支持的媒体类型(1010, "不支持的媒体类型。"),
    未授权(4010, "未授权。"),
    禁止访问(4030, "禁止访问。"),
    未找到(4040, "未找到。"),
    方法不支持(4050, "方法不支持。"),
    请求超时(4080, "请求超时。"),
    服务器内部错误(5000, "服务器内部错误。"),
    未实现(5010, "未实现。"),
    错误的网关(5020, "错误的网关。"),
    服务不可用(5030, "服务不可用。"),
    网关超时(5040, "网关超时。"),
    数据库错误(6000, "数据库错误。"),
    数据完整性违规(6001, "数据完整性违规。");

    private final int errorCode;
    private final String errorMessage;
}
