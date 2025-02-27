package com.orderhere.dish.DishService.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.orderhere.dish.DishService.dto.DeleteIngredientDTO;
import com.orderhere.dish.DishService.dto.GetIngredientDTO;
import com.orderhere.dish.DishService.dto.PostIngredientDTO;
import com.orderhere.dish.DishService.mapper.LinkIngredientDishMapper;
import com.orderhere.dish.DishService.model.Dish;
import com.orderhere.dish.DishService.model.Ingredient;
import com.orderhere.dish.DishService.model.LinkIngredientDish;
import com.orderhere.dish.DishService.repository.DishRepository;
import com.orderhere.dish.DishService.repository.IngredientRepository;
import com.orderhere.dish.DishService.repository.LinkIngredientDishRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@XRayEnabled
public class LinkIngredientDishService {
    @Autowired
    private LinkIngredientDishRepository linkIngredientDishRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private LinkIngredientDishMapper linkIngredientDishMapper;

    public Integer createLink(PostIngredientDTO addIngredientDTO) {
        Dish dish = dishRepository.findById(addIngredientDTO.getDishId()).orElseThrow(() -> new EntityNotFoundException("Dish not found"));
        Ingredient ingredient = ingredientRepository.findByName(addIngredientDTO.getName()).orElseGet(() -> {
            Ingredient newIngredient = new Ingredient();
            newIngredient.setName(addIngredientDTO.getName());
            newIngredient.setUnit(addIngredientDTO.getUnit());
            return ingredientRepository.save(newIngredient);
        });

        LinkIngredientDish link = new LinkIngredientDish();
        link.setDish(dish);
        link.setIngredient(ingredient);
        link.setQuantityValue(addIngredientDTO.getQuantityValue());
        link.setQuantityUnit(addIngredientDTO.getUnit());
        linkIngredientDishRepository.save(link);

        return ingredient.getIngredientId();
    }

    public List<GetIngredientDTO> findGetIngredientDTOByDishID(Integer dishID) {
        Dish dish = dishRepository.findById(dishID).orElseThrow(() -> new EntityNotFoundException("Dish not found"));
        List<LinkIngredientDish> links = linkIngredientDishRepository.findByDish(dish);
        return links.stream().map(linkIngredientDishMapper::toDto).collect(Collectors.toList());
    }

    public void deleteById(DeleteIngredientDTO deleteIngredientDTO) {
        Dish dish = dishRepository.findById(deleteIngredientDTO.getDishId()).orElseThrow(() -> new EntityNotFoundException("Dish not found"));
        Ingredient ingredient = ingredientRepository.findById(deleteIngredientDTO.getIngredientId()).orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));
        LinkIngredientDish link = (LinkIngredientDish) linkIngredientDishRepository.findByDishAndIngredient(dish, ingredient).orElseThrow(() -> new EntityNotFoundException("Link not found"));
        linkIngredientDishRepository.delete(link);
    }


}
