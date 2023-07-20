package com.dojo.bacproject.services;

import java.util.List;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dojo.bacproject.models.FavDrink;
import com.dojo.bacproject.models.User;
import com.dojo.bacproject.repositories.FavDrinkRepo;

@Service
public class FavDrinkService {
	
		private final FavDrinkRepo favdrinkrepo;
		
		public FavDrinkService(FavDrinkRepo favdrinkrepo) {
			this.favdrinkrepo = favdrinkrepo;
		}
		
		public List<FavDrink> allDrinks(){
			return favdrinkrepo.findAll();
		}
		
		public FavDrink updateDrink(FavDrink drink) {
			return favdrinkrepo.save(drink);
		}
		
		public List<FavDrink> getAssignedDrinks(User user){
			return favdrinkrepo.findAllByUsers(user);
		}
		
		public List<FavDrink> getUnassignedDrinks(User user){
			return favdrinkrepo.findByUsersNotContains(user);
		}
		
		public FavDrink addDrink(FavDrink drink) {
			return favdrinkrepo.save(drink);
		}
		
		public void deleteDrink(FavDrink drink) {
			favdrinkrepo.delete(drink);
		}
		
		public FavDrink findById(Long id) {
			Optional<FavDrink> optionalFavDrink = favdrinkrepo.findById(id);
			if(optionalFavDrink.isPresent()) {
				return optionalFavDrink.get();
			}else {
				return null;
			}
		}

}
