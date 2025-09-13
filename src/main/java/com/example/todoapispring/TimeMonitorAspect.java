package com.example.todoapispring;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeMonitorAspect {


    @Around("@annotation(TimeMonitor)")
    public void logTime(ProceedingJoinPoint joinPoint){

//        System.out.println("Logging time" );
        long start=System.currentTimeMillis(); //start time of the code

        //execute the join point   ---> How--->discuss in min

        try {
            // execute the join point
            joinPoint.proceed();
        }
        catch (Throwable e) {
            System.out.println("Something went wrong during the execution");
        } finally {
            long end = System.currentTimeMillis();
            long totalexecutionTime = end - start;
            System.out.println("The total  time of the exectuion method is : " + totalexecutionTime + "  ms..");
        }
    }
}
