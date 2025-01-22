package priv.ns.ecommerce.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import priv.ns.ecommerce.annotation.IgnoreResponseAdvice;
import priv.ns.ecommerce.vo.CommonResponse;

/**
 * Achieve unified response
 */
@RestControllerAdvice(value="priv.ns.ecommerce")
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    /**
     * Determine whether the response needs to be processed
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if(methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        return !methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class);
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        // The final response definition
        CommonResponse<Object> commonResponse = new CommonResponse<>(0,"");

        if(null == o){
            return commonResponse;
        }else if(o instanceof CommonResponse){
            commonResponse = (CommonResponse<Object>) o;
        }else{
            commonResponse.setData(o);
        }

        return commonResponse;
    }
}
