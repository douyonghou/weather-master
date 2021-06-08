package com.bs.weather.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 */
@Aspect
@Component
public class PersonFase {
    private final String POINT_CUT = "execution(* com.bs.weather.mapper.PersonInfoMapper.delPersonById(..))";
    @Pointcut(POINT_CUT)
    private void pointcut(){}

    @After(value = POINT_CUT)
    public void doAfterAdvice(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Integer personId = (Integer)args[0];
        System.out.println("删除"+personId);
    }
}
