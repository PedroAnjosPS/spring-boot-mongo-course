package com.pedroanjosps.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedroanjosps.workshopmongo.domain.Post;
import com.pedroanjosps.workshopmongo.repository.PostRepository;
import com.pedroanjosps.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		
		return post.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
	}
	
}
