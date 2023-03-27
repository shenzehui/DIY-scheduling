package com.marico.scheduling.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 每一个定时任务都对应一个子线程
 * Created by marico on 2023-03-27
 */

public class SchedulingRunnable implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SchedulingRunnable.class);

    private String beanName;

    private String methodName;

    private String params;

    private Object targetBean;

    private Method method;

    public SchedulingRunnable(String beanName, String methodName) {
        this(beanName, methodName, null);
    }

    public SchedulingRunnable(String beanName, String methodName, String params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
        init();
    }

    private void init() {
        try {
            targetBean = SpringContextUtils.getBean(beanName);
            if (StringUtils.hasText(params)) {
                // 假设定时任务只有一个参数，并且参数类型是 String
                method = targetBean.getClass().getDeclaredMethod(methodName, String.class);
            } else {
                // 没有参数
                method = targetBean.getClass().getDeclaredMethod(methodName);
            }
            // 使这个方法可访问
            ReflectionUtils.makeAccessible(method);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    /**
     * 定时任务，时间到了就执行 run 方法
     */
    @Override
    public void run() {
        logger.info("定时任务开始执行 - bean:{},方法:{},参数:{}", beanName, method, params);
        long startTime = System.currentTimeMillis();
        try {
            if (StringUtils.hasText(params)) {
                // 如果方法有参数
                method.invoke(targetBean, params);
            } else {
                method.invoke(targetBean);
            }
        } catch (Exception e) {
            logger.error(String.format("定时任务执行异常 - bean:%s,参数:%s,方法:%s", beanName, methodName, params), e);
        }
        long endTime = System.currentTimeMillis();
        logger.info("定时任务执行结束 - bean:{},方法:{},参数:{},耗时:{}毫秒", beanName, method, params, endTime - startTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchedulingRunnable that = (SchedulingRunnable) o;

        if (!beanName.equals(that.beanName)) return false;
        if (!methodName.equals(that.methodName)) return false;
        return params.equals(that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanName, methodName, params);
    }
}
