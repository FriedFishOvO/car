package com.example.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cars")
public class Car implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String brand;

    private String image;

    private String color;

    private Double price;

    private Double displacement;

    private String transmission;

    private String model;

    private String capacity;

    private String fuelType;

    private LocalDateTime registrationDate;

    private String registrationProvince;

    private String registrationCity;

    @TableField(exist = false)
    private String[] registrationArea;

    private Double fuelConsumption;

    private Integer age;

    private Integer batteryRange;

    private Integer odometer;

    @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm:ss")
    private LocalDateTime createTime;

    @TableLogic
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}