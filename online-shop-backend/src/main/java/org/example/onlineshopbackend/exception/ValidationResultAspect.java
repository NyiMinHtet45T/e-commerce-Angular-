package org.example.onlineshopbackend.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class ValidationResultAspect {

    @Pointcut("within(org.example.onlineshopbackend.api.*)")
    public void apiPointCut(){}

    @Before(value = "apiPointCut() and args(..,result)", argNames = "result")
    public void handleAdvice(BindingResult result){
        if (result.hasErrors()) {
            throw new ApiValidationException(result);
        }
    }
}
