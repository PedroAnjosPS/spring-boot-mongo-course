package com.pedroanjosps.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedroanjosps.workshopmongo.domain.User;
import com.pedroanjosps.workshopmongo.dto.UserDTO;
import com.pedroanjosps.workshopmongo.repository.UserRepository;
import com.pedroanjosps.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		
		return user.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
	}
	
	public User insert(User user) {
		return repo.insert(user);
	}
	
	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
	}
}
