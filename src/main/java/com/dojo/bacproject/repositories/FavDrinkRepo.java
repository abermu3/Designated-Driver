package com.dojo.bacproject.repositories;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.dojo.bacproject.models.FavDrink;
import com.dojo.bacproject.models.User;

@Repository
public interface FavDrinkRepo extends CrudRepository<FavDrink, Long> {
		List<FavDrink> findAll();
		FavDrink findByIdIs(Long id);
		List<FavDrink> findAllByUsers(User user);
		List<FavDrink> findByUsersNotContains(User user);
	}