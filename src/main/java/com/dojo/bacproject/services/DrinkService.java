package com.dojo.bacproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dojo.bacproject.models.Drink;
import com.dojo.bacproject.models.User;
import com.dojo.bacproject.repositories.DrinkRepo;

@Service
public class DrinkService {
	private final DrinkRepo drinkRepo;
	
	public DrinkService(DrinkRepo drinkRepo) {
		this.drinkRepo = drinkRepo;
	}
	
	public List<Drink> allDrinks(){
		return drinkRepo.findAll();
	}
	
	public Drink updateDrink(Drink drink) {
		return drinkRepo.save(drink);
	}
	
	public List<Drink> getAssignedDrinks(User user){
		return drinkRepo.findAllByUsers(user);
	}
	
	public Drink addDrink(Drink drink) {
		return drinkRepo.save(drink);
	}
	
	public void deleteDrink(Drink drink) {
		drinkRepo.delete(drink);
	}
	
	public Drink findById(Long id) {
		Optional<Drink> optionalDrink = drinkRepo.findById(id);
		if(optionalDrink.isPresent()) {
			return optionalDrink.get();
		}else {
			return null;
		}
	}
	
}