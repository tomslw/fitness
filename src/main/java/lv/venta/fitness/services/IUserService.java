package lv.venta.fitness.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import lv.venta.fitness.models.User;

public interface IUserService {
	ArrayList<User> selectAllUsers();
	User selectUserById(long idus) throws Exception;
	void insertNewUser(String name, String surname, String username, String password, LocalDateTime birthday, float weightGoal) throws Exception;
	void updateUserById(long idus, String name, String surname, String username, String password, float wieghtGoal) throws Exception;
	void deleteUserById(long idus) throws Exception;
}
