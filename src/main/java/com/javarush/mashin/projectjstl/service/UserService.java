package com.javarush.mashin.projectjstl.service;

import com.javarush.mashin.projectjstl.beanFactory.BeanFactory;
import com.javarush.mashin.projectjstl.entity.ROLE;
import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.repository.UserRepository;

import java.util.concurrent.atomic.AtomicInteger;

public class UserService {
	private UserRepository userRepo;
	private AtomicInteger userCounter = new AtomicInteger(0);

	public UserService() {
		this.userRepo = BeanFactory.getBean(UserRepository.class);
		startFillUserRepository();
	}

	public synchronized UserDTO createUser(String name, String password, ROLE role) {
		Integer userId = userCounter.incrementAndGet();
		UserDTO newUser = new UserDTO(userId, name, password, role);
		userRepo.add(newUser);
		return newUser;
	}

	public UserDTO getUser(int userId) {
		return userRepo.getById(userId);
	}

	public UserDTO getUserByName(String name) {
		return userRepo.getByName(name);
	}

	public boolean authenticate(String name , String password) {
		UserDTO user = userRepo.getByName(name);
		if (user == null) {
			return false;
		}
		return user.checkPassword(password);
	}

	private void startFillUserRepository() {
		for (int i = 0; i < 3; i++) {
			createUser(i + "", "123", i % 2 == 0 ? ROLE.USER : ROLE.ADMIN);
		}
	}
}
