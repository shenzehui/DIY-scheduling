package com.marico.scheduling.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 根据用户传入的 bean 名称去
 * Created by marico on 2023-03-27
 */

@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 根据Bean的名称来查找一个bean
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 根据类型去Spring容器中查找一个 Bean
     */
    public static <T> T getBean(Class<T> requireType) {
        return applicationContext.getBean(requireType);
    }

    /**
     * 根据名称和类型去查找一个Bean
     */
    public static <T> T getBean(String name, Class<T> requireType) {
        return applicationContext.getBean(name, requireType);
    }

    /**
     * 判断Spring容器中是否存在某一个Bean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 判断一个Bean是否单例
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 根据名字判断Bean的类型
     */
    public static Class<? extends Object> getType(String name) {
        return applicationContext.getType(name);
    }
}
