package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.pojo.Car;
import com.example.pojo.Favorite;

import java.util.List;

public interface FavoriteService extends IService<Favorite> {
    List<Car> getFavoritesByUserId(Long userId);

    void addFavorite(Long userId, Long carId);

    Integer checkFavorite(Long userId, Long carId);

    void cancelFavorite(Long userId, Long carId);
}
