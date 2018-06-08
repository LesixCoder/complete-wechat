package com.lunchtasting.server.exception;

import java.io.Serializable;

/**
 * Created by xuqian on 3/22/16.
 * 错误的模型
 */
public class ErrorModel implements Serializable {
    /**
     * 异常编码
     */
    public String err_code;
    /**
     * 异常处置策略(可以是springEl表达式,可以是提示语)
     */
    public String err_plan;

}
