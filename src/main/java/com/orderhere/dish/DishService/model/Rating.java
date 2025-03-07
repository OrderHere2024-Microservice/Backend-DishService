package com.orderhere.dish.DishService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@DynamicUpdate
@DynamicInsert
@Table(name = "rating")
@org.hibernate.annotations.Subselect("SELECT 1") // we don't use rating table to store ratings currently
@org.hibernate.annotations.Synchronize({}) // so it is not created yet, we have to skip hibernate validation for now
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rating_id", nullable = false, unique = true)
  private Integer ratingId;

  @Column(name = "user_id", nullable = false)
  private String userId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "dish_id", nullable = false)
  private Dish dish;

  @Column(name = "rating_value", nullable = false)
  private BigDecimal ratingValue;

  @Column(name = "comments")
  private String comments;

  @CreationTimestamp
  @Column(name = "created_time", nullable = true)
  private ZonedDateTime createdTime;

  @UpdateTimestamp
  @Column(name = "updated_time", nullable = true)
  private ZonedDateTime updatedTime;
}
