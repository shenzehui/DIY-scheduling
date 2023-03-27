package com.marico.scheduling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by marico on 2023-03-27
 */

@Data
public class RespBean {
    private Integer status;
    private String msg;
    private Object data;

    public static RespBean ok(String msg, Object data) {
        return new RespBean(200, msg, data);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(Integer status, String msg, Object data) {
        return new RespBean(status, msg, data);
    }

    public static RespBean error(String msg, Object data) {
        return new RespBean(500, msg, data);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    private RespBean() {
    }

    private RespBean(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
