package com.javarush.mashin.projectjstl.repository;

import com.javarush.mashin.projectjstl.entity.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository implements Repository<UserDTO> {
	private final Map<Integer, UserDTO> users = new ConcurrentHashMap<>();

	public List<UserDTO> getAll() {
		return new ArrayList<>(users.values());
	}

	@Override
	public UserDTO getById(int id) {
		return users.get(id);
	}

	public UserDTO getByName(String name) {
		for (UserDTO user : users.values()) {
			if (user.getName().equals(name)) {
				return user;
			}
		}
		return null;
	}

	public synchronized void add(UserDTO user) {
		users.put(user.getId(), user);
	}

	public synchronized UserDTO remove(int id) {
		UserDTO user = users.get(id);
		users.remove(id);
		return user;
	}
}
