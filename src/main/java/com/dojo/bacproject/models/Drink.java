package com.dojo.bacproject.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="drinks")
public class Drink {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Type of alcohol is required!")
    private String spiritType;
    
    @NotNull(message="Amount of alcohol is required!")
    @Max(20)
    private Integer numOfDrinks;
    
    
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="drink_id")
    private User drinker;
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_drinks",
			joinColumns = @JoinColumn(name = "drink_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
    private List<User> users;
    
    public Drink() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSpiritType() {
		return spiritType;
	}
	public void setSpiritType(String spiritType) {
		this.spiritType = spiritType;
	}
	public Integer getNumOfDrinks() {
		return numOfDrinks;
	}
	public void setNumOfDrinks(Integer numOfDrinks) {
		this.numOfDrinks = numOfDrinks;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getDrinker() {
		return drinker;
	}
	public void setDrinker(User drinker) {
		this.drinker = drinker;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
    
	
}
