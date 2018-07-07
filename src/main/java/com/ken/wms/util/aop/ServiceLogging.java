package com.ken.wms.util.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ServiceLogging {

    private static Logger exceptionLogger = LoggerFactory.getLogger("ExceptionLogging");
    private static Logger methodInvokeLogger = LoggerFactory.getLogger("MethodInvokeLogging");

    /**
     * 鎹曡幏Service灞傛姏鍑虹殑寮傚父骞跺仛鏃ュ織
     *
     * @param throwable 鏂规硶鎶涘嚭鐨勫紓甯�
     */
    public void loggingServiceException(Throwable throwable) {
        if (exceptionLogger.isErrorEnabled()) {
            StringBuilder builder = new StringBuilder();
            builder.append("cause:").append(throwable.getMessage());
            builder.append("\n\tstackTrack:\n");
            for (StackTraceElement stack : throwable.getStackTrace()) {
                builder.append("\t\t");
                builder.append(stack.toString());
                builder.append("\n");
            }
            exceptionLogger.error(builder.toString());
        }
    }

    /**
     * 璁板綍Service鏂规硶鐨勮皟鐢�
     *
     * @param joinPoint 鍒囧叆鐐�
     */
    public void loggingMethodInvoked(JoinPoint joinPoint) {
        if (methodInvokeLogger.isDebugEnabled()) {
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            StringBuilder builder = new StringBuilder();
            builder.append("Invoked Method:").append(methodName);
            builder.append("\targs锛�");
            for (Object arg : args) {
                builder.append(arg.toString());
            }
            methodInvokeLogger.debug(builder.toString());
        }
    }

}
