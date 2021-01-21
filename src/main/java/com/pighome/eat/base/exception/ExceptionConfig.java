package com.pighome.eat.base.exception;


import com.pighome.eat.base.response.RestResponse;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import tk.mybatis.mapper.util.StringUtil;

/**
 * 异常处理切面类 注：只截获未被catch的异常
 *
 * @author cjh
 */
@ControllerAdvice
public class ExceptionConfig {

    private final Environment env;
    private final Logger logger = LoggerFactory.getLogger(ExceptionConfig.class);

    @Autowired
    public ExceptionConfig(Environment env) {
        this.env = env;
    }

    /**
     * 生成异常ID
     */
    private String genExceptionId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 自定义异常处理
     *
     * @param req 客户端请求对象
     * @param e 异常信息
     * @return RestResponse对象
     */
    @ExceptionHandler(value = EatException.class)
    @ResponseBody
    public ResponseEntity handleCloudException(HttpServletRequest req, EatException e) {
        return buildResponse(e.getStatus(), e.getMessage(), e.getData());

    }

    @ExceptionHandler(value = MultipartException.class)
    @ResponseBody
    public Object handleMultipartException(HttpServletRequest req, Exception e) {
        String maxSize = env.getProperty("spring.servlet.multipart.max-file-size");
        return RestResponse.error("单文件大小超过" + (StringUtil.isEmpty(maxSize) ? "20M" : maxSize) + ",请压缩后重试");
    }


    /**
     * 系统异常处理
     *
     * @param req 客户端请求对象
     * @param e 异常信息
     * @return RestResponse对象
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handlerException(HttpServletRequest req, Exception e) {
        String status;
        String message;
        String eid = genExceptionId();
        logger.error(req.getRequestURI() + " ---> 出现异常", e);
        if (e instanceof HttpRequestMethodNotSupportedException) {
            status = ExceptionStatus.METHOD_NOT_ALLOWED.getStatus();
            message = ExceptionStatus.METHOD_NOT_ALLOWED.getMessage() + "(" + req.getMethod() + ")";
        } else {
            status = ExceptionStatus.SYSTEM_ERROR.getStatus();
            message = ExceptionStatus.SYSTEM_ERROR.getMessage();
        }
        return buildResponse(status, message, eid);
    }


    private ResponseEntity buildResponse(String status, String message, Object data) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(RestResponse.error(status, message, data));
    }

    private EatException transformationBusinessException(String validationMsg) {
        return new EatException(ExceptionStatus.BUSINESS_ERR.getStatus(), validationMsg);
    }
}
