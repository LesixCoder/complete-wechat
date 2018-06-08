package com.lunchtasting.server.mvc;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lunchtasting.server.exception.BusinessError;
import com.lunchtasting.server.exception.ErrorCate;
import com.lunchtasting.server.util.ValidatorHelper;

import java.beans.PropertyEditorSupport;

/**
 * 
 * @author xq
 *
 */
public class BaseController {

//    @Value("${profile}")
    private String profile = "dev";
    /**
     * 日志
     */
    protected static final Logger log = Logger.getLogger("CONTROLLER"); 
    
    /**
     * 处理自定义异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessError.class)
    @ResponseBody
    public JSONObject handleMyException(BusinessError ex) {
        JSONObject jo = new JSONObject();
        jo.put("message", ex.msg);
        jo.put("error_code", ex.code);
        if ("dev".equals(profile)) {
            jo.put("stack", ex);
        }
        log.error(ex);
        return jo;
    }

    /**
     * 处理运行时异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public JSONObject handleThrowable(Throwable ex) {
        BusinessError b_ex = null;
        if (!(ex instanceof BusinessError)) {
            String msg = ex.getMessage();
            if (ValidatorHelper.isEmpty(msg)) {
                msg = "系统异常";
            }
            b_ex = new BusinessError(ErrorCate.SYS, msg, ex, "10002");
        } else {
            b_ex = (BusinessError) ex;
        }
        return handleMyException(b_ex);
    }

//    /**
//     * 初始化数据绑定器
//     *
//     * @param dataBinder
//     */
//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder) {
//        dataBinder.registerCustomEditor(JSONObject.class, new PropertyEditorSupport() {
//            JSONObject value;
//
//            @Override
//            public JSONObject getValue() {
//                return value;
//            }
//
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                value = JSON.parseObject(text);
//            }
//        });
//    }
}
