package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.CarMapper;
import com.example.pojo.Car;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public Car getCarById(Long id) {
        LambdaQueryWrapper<Car> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Car::getId, id);
        return carMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Car> getCarsByConditions(String brand, String price, String age, String odometer, String displacement, String transmission, String model,
                                         String capacity, String fuelType, String color, String activeIndex, String searchInfo) {
        LambdaQueryWrapper<Car> queryWrapper = new LambdaQueryWrapper<>();

        // 筛选品牌
        if (!brand.equals("不限") && !"".equals(brand))
            queryWrapper.eq(Car::getBrand, brand);

        // 筛选价格
        if (!price.equals("不限") && !"".equals(price)) {
            if (price.equals("3万以下"))
                queryWrapper.lt(Car::getPrice, 3);
            if (price.equals("3-5万"))
                queryWrapper.between(Car::getPrice, 3, 5);
            if (price.equals("5-7万"))
                queryWrapper.between(Car::getPrice, 5, 7);
            if (price.equals("7-9万"))
                queryWrapper.between(Car::getPrice, 7, 9);
            if (price.equals("9-12万"))
                queryWrapper.between(Car::getPrice, 9, 12);
            if (price.equals("12-16万"))
                queryWrapper.between(Car::getPrice, 12, 16);
            if (price.equals("16-20万"))
                queryWrapper.between(Car::getPrice, 16, 20);
            if (price.equals("20万以上"))
                queryWrapper.gt(Car::getPrice, 20);
        }

        //筛选车龄
        if (!"".equals(age)) {
            if (age.equals("1年以内"))
                queryWrapper.le(Car::getAge, 1);
            if (age.equals("3年以内"))
                queryWrapper.le(Car::getAge, 3);
            if (age.equals("5年以内"))
                queryWrapper.le(Car::getAge, 5);
            if (age.equals("8年以内"))
                queryWrapper.le(Car::getAge, 8);
            if (age.equals("8年以上"))
                queryWrapper.gt(Car::getAge, 8);
        }

        //筛选里程
        if (!"".equals(odometer)) {
            if (odometer.equals("1万公里内"))
                queryWrapper.le(Car::getOdometer, 10000);
            if (odometer.equals("3万公里内"))
                queryWrapper.le(Car::getOdometer, 30000);
            if (odometer.equals("5万公里内"))
                queryWrapper.le(Car::getOdometer, 50000);
            if (odometer.equals("8万公里内"))
                queryWrapper.le(Car::getOdometer, 80000);
            if (odometer.equals("10万公里内"))
                queryWrapper.le(Car::getOdometer, 100000);
            if (odometer.equals("10万公里以上"))
                queryWrapper.gt(Car::getOdometer, 100000);
        }

        // 筛选排量
        if (!"".equals(displacement)) {
            if (displacement.equals("1.0L以下"))
                queryWrapper.lt(Car::getDisplacement, 1.0);
            if (displacement.equals("1.0-1.6L"))
                queryWrapper.between(Car::getDisplacement, 1.0, 1.6);
            if (displacement.equals("1.6-2.0L"))
                queryWrapper.between(Car::getDisplacement, 1.6, 2.0);
            if (displacement.equals("2.0-3.0L"))
                queryWrapper.between(Car::getDisplacement, 2.0, 3.0);
            if (displacement.equals("3.0-4.0L"))
                queryWrapper.between(Car::getDisplacement, 3.0, 4.0);
            if (displacement.equals("4.0L以上"))
                queryWrapper.gt(Car::getDisplacement, 4.0);
        }

        // 筛选变速箱
        if (!"".equals(transmission))
            queryWrapper.eq(Car::getTransmission, transmission);

        // 筛选车型
        if (!"".equals(model))
            queryWrapper.eq(Car::getModel, model);

        // 筛选座位数
        if (!"".equals(capacity)) {
            if (capacity.equals("2座"))
                queryWrapper.eq(Car::getCapacity, 2);
            if (capacity.equals("4座"))
                queryWrapper.eq(Car::getCapacity, 4);
            if (capacity.equals("5座"))
                queryWrapper.eq(Car::getCapacity, 5);
            if (capacity.equals("6座"))
                queryWrapper.eq(Car::getCapacity, 6);
            if (capacity.equals("7座及以上"))
                queryWrapper.ge(Car::getCapacity, 7);
        }

        // 筛选能源类型
        if (!"".equals(fuelType))
            queryWrapper.eq(Car::getFuelType, fuelType);

        // 筛选颜色
        if (!"".equals(color))
            queryWrapper.eq(Car::getColor, color);

        // 根据输入内容筛选
        if (!"".equals(searchInfo))
            queryWrapper.like(Car::getName, searchInfo);

        // 排序方式
        if (!"".equals(activeIndex)) {
            if (activeIndex.equals("1")) {
                queryWrapper.orderByDesc(Car::getCreateTime);
            }
            if (activeIndex.equals("2")) {
                queryWrapper.orderByAsc(Car::getPrice);
            }
            if (activeIndex.equals("3")) {
                queryWrapper.orderByDesc(Car::getPrice);
            }
            if (activeIndex.equals("4")) {
                queryWrapper.orderByAsc(Car::getAge);
            }
            if (activeIndex.equals("5")) {
                queryWrapper.orderByAsc(Car::getOdometer);
            }
        }

        return carMapper.selectList(queryWrapper);
    }

    @Override
    public List<Car> getAllCars() {
        return carMapper.selectList(null);
    }

    @Override
    public List<Car> getBySearchInfo(String searchInfo) {
        LambdaQueryWrapper<Car> queryWrapper = new LambdaQueryWrapper<>();
        if (!"".equals(searchInfo))
            queryWrapper.like(Car::getName, searchInfo);
        return carMapper.selectList(queryWrapper);
    }
}