package com.pedroanjosps.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pedroanjosps.workshopmongo.domain.Post;
import com.pedroanjosps.workshopmongo.domain.User;
import com.pedroanjosps.workshopmongo.dto.AuthorDTO;
import com.pedroanjosps.workshopmongo.repository.PostRepository;
import com.pedroanjosps.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Gray", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("25/06/2024"), "Bom dia a todos!", 
				"Uma semana espetacular para todos", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("08/07/2025"), "Nada melhor do que a praia!", 
				"Melhor sensação de todas é de estar presente "
				+ "em um lugar tão calmo e relachante quanto esse!!", new AuthorDTO(maria));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
