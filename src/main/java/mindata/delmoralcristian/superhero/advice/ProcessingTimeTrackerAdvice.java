package mindata.delmoralcristian.superhero.advice;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
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
        var startTime = System.currentTimeMillis();
        var objectProceeded = proceedingJoinPoint.proceed();
        var timeToExecute = System.currentTimeMillis() - startTime;
        var simpleDateFormat = new SimpleDateFormat("mm:ss.SSS");
        var logMessage = new StringBuilder("Method name: ")
                .append(proceedingJoinPoint.getSignature())
                .append(" time taken to execute: ")
                .append(simpleDateFormat.format(new Date(timeToExecute)));
        log.info(logMessage.toString());
        return objectProceeded;
    }
}
