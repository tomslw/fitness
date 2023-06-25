package lv.venta.fitness.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.venta.fitness.models.User;

public interface IUserService {
	ArrayList<User> selectAllUsers();
	void insertNewUser(String name, String surname, String username, String password, LocalDateTime birthday);
	void updateUserById(long idus, String name, String surname, String username);
	void deleteUserById(long idus);
}
