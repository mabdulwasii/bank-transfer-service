package com.indexpay.transfer.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class ErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(
            WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes =
                super.getErrorAttributes(webRequest, options);

        String message = (String) errorAttributes.get("message");
        errorAttributes.put("code", "02");
        errorAttributes.put("description", message);

        errorAttributes.remove("error");
        errorAttributes.remove("timestamp");
        errorAttributes.remove("status");
        errorAttributes.remove("message");
        errorAttributes.remove("path");
        errorAttributes.remove("trace");

        return errorAttributes;
    }
}
