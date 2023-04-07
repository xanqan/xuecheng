package com.xuecheng.base.execption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description 全局异常处理器
 * @date 2022/9/6 11:29
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(XueChengPlusException.class)
    public RestErrorResponse customException(XueChengPlusException e) {
        log.error("【系统异常】{}", e.getErrMessage(), e);
        return new RestErrorResponse(e.getErrMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestErrorResponse doValidException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();
        StringBuffer errMsg = new StringBuffer();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            errMsg.append(fieldError.getDefaultMessage()).append(",");
        });

        log.error(errMsg.toString());
        return new RestErrorResponse(errMsg.toString());
    }

    @ExceptionHandler(Exception.class)
    public RestErrorResponse exception(Exception e) {
        log.error("【系统异常】{}", e.getMessage(), e);
        e.printStackTrace();
        if ("不允许访问".equals(e.getMessage())) {
            return new RestErrorResponse("没有操作此功能的权限");
        }
        return new RestErrorResponse(CommonError.UNKOWN_ERROR.getErrMessage());
    }
}
