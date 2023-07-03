package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.CarMapper;
import com.example.mapper.FavoriteMapper;
import com.example.pojo.Car;
import com.example.pojo.Favorite;
import com.example.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceImpl extends ServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {
    @Autowired
    private FavoriteMapper favoriteMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> getFavoritesByUserId(Long userId) {
        LambdaQueryWrapper<Favorite> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Favorite::getUserId, userId);
        List<Favorite> favorites = favoriteMapper.selectList(queryWrapper);
        List<Car> carList = new ArrayList<>();
        for (Favorite item : favorites) {
            Car car = carMapper.selectById(item.getCarId());
            carList.add(car);
        }
        return carList;
    }

    @Override
    public void addFavorite(Long userId, Long carId) {
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setCarId(carId);
        favoriteMapper.insert(favorite);
    }

    @Override
    public Integer checkFavorite(Long userId, Long carId) {
        LambdaQueryWrapper<Favorite> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Favorite::getUserId, userId).eq(Favorite::getCarId, carId);
        Favorite favorite = favoriteMapper.selectOne(queryWrapper);
        if (favorite != null)
            return 1;
        return 0;
    }

    @Override
    public void cancelFavorite(Long userId, Long carId) {
        LambdaQueryWrapper<Favorite> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Favorite::getUserId, userId).eq(Favorite::getCarId, carId);
        favoriteMapper.delete(queryWrapper);
    }
}
