package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Car;

import java.util.List;

public interface CarService extends IService<Car> {
    Car getCarById(Long id);

    List<Car> getCarsByConditions(String brand, String price, String age, String odometer, String displacement, String transmission, String model,
                                  String capacity, String fuelType, String color, String activeIndex, String searchInfo);

    List<Car> getAllCars();

    List<Car> getBySearchInfo(String searchInfo);
}
