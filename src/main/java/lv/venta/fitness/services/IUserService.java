package lv.venta.fitness.services;

import java.util.ArrayList;

import lv.venta.fitness.models.User;

public interface IUserService {
	ArrayList<User> selectAllUsers();
	void updateUserById(long idus, String name, String surname, String username);
	void deleteUserById(long idus);
}
