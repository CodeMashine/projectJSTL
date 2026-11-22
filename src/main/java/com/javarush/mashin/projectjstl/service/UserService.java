package com.javarush.mashin.projectjstl.service;

import com.javarush.mashin.projectjstl.entity.ROLE;
import com.javarush.mashin.projectjstl.entity.UserDTO;
import com.javarush.mashin.projectjstl.entity.UserEntity;
import com.javarush.mashin.projectjstl.entity.UserStatDTO;
import com.javarush.mashin.projectjstl.entity.UserStatEntity;
import com.javarush.mashin.projectjstl.repository.GameInProgressRepository;
import com.javarush.mashin.projectjstl.repository.PlayedGamesRepository;
import com.javarush.mashin.projectjstl.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
	private UserRepository userRepository;
	private PlayedGamesRepository playedGamesRepository;
	private GameInProgressRepository gameInProgressRepository;

	public UserService(UserRepository userRepository, PlayedGamesRepository playedGamesRepository,
					   GameInProgressRepository gameInProgressRepository) {
		this.userRepository = userRepository;
		this.playedGamesRepository = playedGamesRepository;
		this.gameInProgressRepository = gameInProgressRepository;
	}

	public int addUser(String username, String password, ROLE role) {
		int userId = userRepository.createUser(username, password, role.name());
		playedGamesRepository.addUser(userId);
		return userId;
	}

	public void addLoseGame(UserDTO user, int questId) {
		int userId = user.getId();
		removeGameInProgressFromUserDTO(user, questId);
		gameInProgressRepository.removeGameInProgress(userId, questId);
		playedGamesRepository.addLose(userId);
	}

	public void addWinGame(UserDTO user, int questId) {
		int userId = user.getId();
		removeGameInProgressFromUserDTO(user, questId);
		gameInProgressRepository.removeGameInProgress(userId, questId);
		playedGamesRepository.addWin(userId);
	}

	public void setGameInProgress(int userId, int questId, String stepId) {
		gameInProgressRepository.setGameInProgress(userId, questId, stepId);
	}

	public Integer authenticate(String username, String password) {
		UserEntity user = userRepository.getByName(username);
		if (user != null && user.getPassword().equals(password)) {
			return user.getId();
		}
		return null;
	}


	public List<UserStatEntity> getUsersStatistic() {
		return userRepository.getAllUserStat();
	}

	public UserDTO getUserDTO(int userId) {
		UserEntity user = userRepository.getById(userId);
		UserDTO userDTO = getDTOFromEntity(user);
		Map<Integer, String> map = gameInProgressRepository.getAllGameInProgressForUser(userId);
		userDTO.setGameInProgress(map);
		return userDTO;
	}

	public void deleteUser(int userId) {
		userRepository.deleteUser(userId);
	}

	public boolean updateUser(int id, String username) {
		int res = userRepository.updateUser(id, username);
		return res == 1;
	}

	public List<UserDTO> getAllUsers() {
		List<UserEntity> userEntityList = userRepository.getAll();
		List<UserDTO> userDTOList = new ArrayList<>();
		for (UserEntity userEntity : userEntityList) {
			userDTOList.add(getDTOFromEntity(userEntity));
		}
		return userDTOList;
	}

	private UserDTO getDTOFromEntity(UserEntity userEntity) {
		int id = userEntity.getId();
		String name = userEntity.getName();
		ROLE role = ROLE.valueOf(userEntity.getRole());
		return new UserDTO(id, name, role);
	}

	private void removeGameInProgressFromUserDTO(UserDTO user, int questId) {
		var gamesInProgress = user.getGamesInProgress();
		gamesInProgress.remove(questId);
	}

}
