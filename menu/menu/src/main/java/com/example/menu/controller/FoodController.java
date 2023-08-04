package com.example.menu.controller;

import com.example.menu.food.Food;
import com.example.menu.food.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository foodRepository;
    @GetMapping
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
}
