package com.orderhere.dish.DishService.service;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.orderhere.dish.DishService.dto.DishCreateDto;
import com.orderhere.dish.DishService.dto.DishGetDto;
import com.orderhere.dish.DishService.dto.DishUpdateDTO;
import com.orderhere.dish.DishService.dto.PagingDto;
import com.orderhere.dish.DishService.enums.DishSort;
import com.orderhere.dish.DishService.exception.ResourceNotFoundException;
import com.orderhere.dish.DishService.mapper.DishMapper;
import com.orderhere.dish.DishService.model.Dish;
import com.orderhere.dish.DishService.repository.DishRepository;
import com.orderhere.dish.DishService.service.storageService.StorageService;
import com.orderhere.dish.DishService.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@XRayEnabled
public class DishService {
  private final DishRepository dishRepository;
  private final DishMapper dishMapper;

  @Autowired
  private StorageService storageService;

  @Value("${storage.bucketName}")
  private String bucketName;

  public PagingDto<List<DishGetDto>> getDishPageByRestaurantId(Integer restaurantId,
                                                               int page,
                                                               int size,
                                                               DishSort sort,
                                                               Sort.Direction order) {
    Pageable pageable = PageableUtil.determinePageable(page, size, Sort.by(order, sort.getName()));

    Page<Dish> dishPage = dishRepository.findDishesByRestaurantIdAndIsDeletedFalse(restaurantId, pageable);

    List<DishGetDto> dishGetDtoList = dishPage.getContent()
            .stream()
            .map(dishMapper::dishToDishGetDto)
            .toList();

    return PagingDto.<List<DishGetDto>>builder()
            .data(dishGetDtoList)
            .currentPage(dishPage.getNumber() + 1)
            .totalPages(dishPage.getTotalPages())
            .totalItems(dishPage.getTotalElements())
            .build();
  }

  public List<DishGetDto> getDishByCategory(Integer restaurantId, Integer categoryId) {
    return dishRepository.findAllByRestaurantIdAndCategoryCategoryIdAndIsDeletedFalse(restaurantId, categoryId)
            .stream()
            .map(dishMapper::dishToDishGetDto)
            .toList();
  }

  @Transactional
  public void createDish(DishCreateDto dishCreateDto) {
    try {

      if (dishCreateDto.getImageFile() != null && !dishCreateDto.getImageFile().isEmpty()) {
        String imageUrl = storageService.uploadFile(dishCreateDto.getImageFile(), bucketName);
        dishCreateDto.setImageUrl(imageUrl);
      }

      Dish dish = dishMapper.dishCreateDtoToDish(dishCreateDto);
      dishRepository.save(dish);

    } catch (Exception e) {
      log.error("Error occurred while creating dish", e);
    }
  }

  @Transactional
  public DishGetDto updateDish(DishUpdateDTO dishUpdateDto) {
    try {
      Dish existingDish = dishRepository.findById(dishUpdateDto.getDishId())
              .orElseThrow(() -> new ResourceNotFoundException("Dish not found"));

      if (dishUpdateDto.getImageFile() != null && !dishUpdateDto.getImageFile().isEmpty()) {
        String imageUrl = storageService.uploadFile(dishUpdateDto.getImageFile(), bucketName);
        storageService.deleteFile(bucketName, existingDish.getImageUrl());
        dishUpdateDto.setImageUrl(imageUrl);
      } else {
        dishUpdateDto.setImageUrl(existingDish.getImageUrl());
      }

      dishMapper.updateDishFromDishUpdateDTO(dishUpdateDto, existingDish);
      dishRepository.save(existingDish);

      return dishMapper.dishToDishGetDto(existingDish);

    } catch (Exception e) {
      log.error("Error occurred while updating dish", e);
    }
    return null;
  }

  @Transactional
  public void deleteDish(Integer dishId) throws Exception {
    Dish dish = dishRepository.findById(dishId)
            .orElseThrow(() -> new ResourceNotFoundException("Dish not found with id: " + dishId));
    // storageService.deleteFile(bucketName, dish.getImageUrl());
    dish.setIsDeleted(true);
    dishRepository.save(dish);
  }
}
