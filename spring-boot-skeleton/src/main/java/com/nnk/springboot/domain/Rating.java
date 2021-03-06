package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message="MoodysRating is mandatory")
    private String moodysRating;
    @NotBlank(message = "SandP Rating is mandatory")
    private String sandPRating;
    @NotBlank(message="Fitch Rating is mandatory")
    private String fitchRating;
    @NotNull(message = "Order Number is mandatory")
    private Integer orderNumber;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getMoodysRating() {
        return moodysRating;
    }
    
    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }
    
    public String getSandPRating() {
        return sandPRating;
    }
    
    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }
    
    public String getFitchRating() {
        return fitchRating;
    }
    
    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }
    
    public Integer getOrderNumber() {
        return orderNumber;
    }
    
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
