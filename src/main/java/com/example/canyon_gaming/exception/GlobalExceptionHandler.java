package com.example.canyon_gaming.exception;

import com.example.canyon_gaming.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 如果抛出的是ServiceException，则调用该方法
     *
     * @param serviceException 业务异常
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException serviceException) {
        return Result.fail(serviceException.getCode(), serviceException.getMessage());
    }
}
