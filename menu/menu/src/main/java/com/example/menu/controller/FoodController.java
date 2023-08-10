package com.example.menu.controller;

import com.example.menu.food.Food;
import com.example.menu.food.FoodRepository;
import com.example.menu.food.FoodRequestDTO;
import com.example.menu.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    private FoodRepository foodRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data) {
        Food foodData = new Food(data);
        foodRepository.save(foodData);
        return;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAllFoods() {
        return foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFoodById(@PathVariable Long id, @RequestBody FoodRequestDTO updatedFood) {
        Optional<Food> optionalFood = foodRepository.findById(id);

        if (optionalFood.isPresent()) {
            Food existingFood = optionalFood.get();

            existingFood.setTitle(updatedFood.title());
            existingFood.setImage(updatedFood.image());
            existingFood.setPrice(updatedFood.price());

            foodRepository.save(existingFood);

            return ResponseEntity.ok("Prato atualizado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFood(@PathVariable Long id) {
        Optional<Food> foodOptional = foodRepository.findById(id);

        if(foodOptional.isPresent()) {
            foodRepository.deleteById(id);
            return ResponseEntity.ok("Prato deletado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
