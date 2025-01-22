package priv.ns.ecommerce.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import priv.ns.ecommerce.vo.CommonResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Global exception catching processing
 * */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(value = Exception.class)
    public CommonResponse<String> handleException(HttpServletRequest request,Exception e) {
        CommonResponse<String> commonResponse = new CommonResponse<>(
                -1,"business error"
        );

        commonResponse.setData(e.getMessage());
        log.error("commerce service has error:[{}]",e.getMessage(),e);
        return commonResponse;
    }
}
