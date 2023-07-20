package com.dojo.bacproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dojo.bacproject.models.Drink;
import com.dojo.bacproject.models.User;

@Repository
public interface DrinkRepo extends CrudRepository<Drink, Long> {
		List<Drink> findAll();
		Drink findByIdIs(Long id);
		List<Drink> findAllByUsers(User user);
		List<Drink> findByUsersNotContains(User user);
	}
