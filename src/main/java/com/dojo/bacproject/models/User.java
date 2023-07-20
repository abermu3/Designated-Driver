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
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="First Name is required!")
    @Size(min=2, max=25, message="First Name must be between 2 and 25 characters")
    private String firstName;
    
    @NotEmpty(message="Last Name is required!")
    @Size(min=2, max=25, message="Last Name must be between  and 25 characters")
    private String lastName;
    
    @NotEmpty(message="Email is required!")
    @Email(message="Enter a valid email")
    private String email;
    
    @NotNull(message="Weight is required!")
    @Max(700)
    private Integer weight;
    
    @NotNull(message="Gender is required")
    @Column(name="gender", columnDefinition="TINYINT(1)")
    private Integer gender;
    
    @NotEmpty(message="Password is required!")
    @Size(min=10, max=200, message="Password must be between 10 and 200 characters")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=10, max=200, message="Password must be between 10 and 200 characters")
    private String confirm;
    
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
    
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_drinks",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "drink_id")
	)
    private List<Drink> drinks;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="drinker", fetch = FetchType.LAZY)
    private List<Drink> drinksHad;
  
    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "users_favdrinks",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "favdrink_id")
	)
    private List<FavDrink> favdrinks;
    
    @Column(updatable=false)
    @OneToMany(mappedBy="drinker", fetch = FetchType.LAZY)
    private List<FavDrink> drinksLiked;
    
    public User() {}
	public List<FavDrink> getFavdrinks() {
		return favdrinks;
	}
	public void setFavdrinks(List<FavDrink> favdrinks) {
		this.favdrinks = favdrinks;
	}
	public List<FavDrink> getDrinksLiked() {
		return drinksLiked;
	}
	public void setDrinksLiked(List<FavDrink> drinksLiked) {
		this.drinksLiked = drinksLiked;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirm() {
		return confirm;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
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
	public List<Drink> getDrinks() {
		return drinks;
	}
	public void setDrinks(List<Drink> drinks) {
		this.drinks = drinks;
	}
	public List<Drink> getDrinksHad() {
		return drinksHad;
	}
	public void setDrinksHad(List<Drink> drinksHad) {
		this.drinksHad = drinksHad;
	}
	

}