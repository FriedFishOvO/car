package com.example.controller;

import com.example.pojo.Car;
import com.example.pojo.Favorite;
import com.example.pojo.Result;
import com.example.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/{id}")
    public Result getFavoritesById(@PathVariable Long id) {
        List<Car> favorites = favoriteService.getFavoritesByUserId(id);
        return Result.success(favorites);
    }

    @PostMapping("/add")
    public Result addFavorite(@RequestBody Favorite favorite) {
        favoriteService.addFavorite(favorite.getUserId(), favorite.getCarId());
        return Result.success();
    }

    @PostMapping("/check")
    public Result checkFavorite(@RequestBody Favorite favorite) {
        int res = favoriteService.checkFavorite(favorite.getUserId(), favorite.getCarId());
        return Result.success(res);
    }

    @PostMapping("/cancel")
    public Result cancelFavorite(@RequestBody Favorite favorite) {
        favoriteService.cancelFavorite(favorite.getUserId(), favorite.getCarId());
        return Result.success();
    }
}
