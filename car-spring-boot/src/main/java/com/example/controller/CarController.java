package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.pojo.Car;
import com.example.pojo.Result;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/getCars")
    public Result getCarsByConditions(String brand, String price, String age, String odometer, String displacement, String transmission, String model,
                                      String capacity, String fuelType, String color, String activeIndex, String searchInfo) {
        List<Car> cars = carService.getCarsByConditions(brand, price, age, odometer, displacement, transmission, model, capacity, fuelType, color, activeIndex, searchInfo);
        return Result.success(cars);
    }

    @GetMapping("/{id}")
    public Result getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        return Result.success(car);
    }

    @GetMapping("/all")
    public Result getAllCars() {
        List<Car> all = carService.getAllCars();
        return Result.success(all);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteCar(@PathVariable Long id) {
        carService.removeById(id);
        return Result.success();
    }

    @PostMapping("/add")
    public Result addCar(@RequestBody Car car) {
        System.out.println(car);
        car.setCreateTime(LocalDateTime.now());
        car.setRegistrationProvince(car.getRegistrationArea()[0]);
        car.setRegistrationCity(car.getRegistrationArea()[1]);
        car.setAge(2023 - car.getRegistrationDate().getYear());
        carService.save(car);
        return Result.success();
    }

    @GetMapping("/search")
    public Result getBySearchInfo(String searchInfo) {
        List<Car> cars = carService.getBySearchInfo(searchInfo);
        return Result.success(cars);
    }

    @PutMapping("/update")
    public Result updateCar(@RequestBody Car car) {
        car.setCreateTime(LocalDateTime.now());
        car.setRegistrationProvince(car.getRegistrationArea()[0]);
        car.setRegistrationCity(car.getRegistrationArea()[1]);
        car.setAge(2023 - car.getRegistrationDate().getYear());
        LambdaUpdateWrapper<Car> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Car::getId, car.getId());
        carService.update(car, updateWrapper);
        return Result.success();
    }
}
