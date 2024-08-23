package com.fastcampuspay.common;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
public @interface UseCase {

    /*사용을 위한 케이스
    * 인터페이스 UseCase 정의
    * 
    * */
    
    
    @AliasFor(annotation = Component.class)
    String value() default "";
}