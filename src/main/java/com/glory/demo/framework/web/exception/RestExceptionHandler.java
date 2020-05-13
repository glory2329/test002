package com.glory.demo.framework.web.exception;

import com.glory.demo.framework.web.exception.RestData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * author : glory
 * date : 2019/12/14 19:54
 * description : 全局统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * 校验错误拦截处理
     * 使用 @RequestBody 接收入参时，校验失败抛 MethodArgumentNotValidException 异常
     */
   /* @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RestData handler(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException handler", e);
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasFieldErrors()) {
            return RestData.build().error(bindingResult.getFieldError().getDefaultMessage());
        }
        return RestData.build().error("parameter is not valid");
    }*/

    /**
     * 校验错误拦截处理
     * 使用 @RequestBody 接收入参时，数据类型转换失败抛 HttpMessageConversionException 异常
     */
    /*@ExceptionHandler(value = HttpMessageConversionException.class)
    public RestData handler(HttpMessageConversionException e) {
        log.error("HttpMessageConversionException handler", e);
        return RestData.build().error(e.getMessage());
    }*/

    /**
     * 全局异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public RestData handler(Exception e) {
        log.info("exception handler", e);
        return RestData.build().error(e.getMessage());
    }
}
