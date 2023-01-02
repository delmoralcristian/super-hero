package mindata.delmoralcristian.superhero.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class ProcessingTimeTrackerAdvice {

    @Around("@annotation(mindata.delmoralcristian.superhero.advice.TrackProcessingTime)")
    public Object processingTrackTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object objectProceeded = proceedingJoinPoint.proceed();
        long timeToExecute = System.currentTimeMillis() - startTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss.SSS");
        log.info("Method name: " + proceedingJoinPoint.getSignature() + " time taken to execute: " + simpleDateFormat.format(new Date(timeToExecute)));
        return objectProceeded;
    }
}
