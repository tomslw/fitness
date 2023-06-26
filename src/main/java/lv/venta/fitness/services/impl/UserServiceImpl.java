package lv.venta.fitness.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import lv.venta.fitness.models.User;
import lv.venta.fitness.repos.UserRepo;
import lv.venta.fitness.services.IUserService;

public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public ArrayList<User> selectAllUsers() {
		return (ArrayList<User>) userRepo.findAll();
	}
	
	@Override
	public User selectUserById(long idus) throws Exception {
		if(idus > 0) {
			User user = userRepo.findByIdus(idus);
			return user;
		}
		else {
			throw new Exception("Invalid id");
		}
	}

	@Override
	public void insertNewUser(String name, String surname, String username, String password, LocalDateTime birthday, float weightGoal) throws Exception {
		if(name != null && surname != null && username != null && password != null && birthday != null && weightGoal > 0) {
			User user = new User(name, surname, username, password, birthday, weightGoal);
			userRepo.save(user);
		}
		else {
			throw new Exception("Invalid arguments");
		}
	}

	@Override
	public void updateUserById(long idus, String name, String surname, String username, String password, float weightGoal) throws Exception {
		if(name != null && surname != null && username != null && password != null && weightGoal > 0) {
			User user = selectUserById(idus);
			user.setName(name);
			user.setSurname(surname);
			user.setUsername(username);
			user.setPassword(password);
			user.setWeightGoal(weightGoal);
			userRepo.save(user);
		}
		else {
			throw new Exception("Invalid arguments");
		}
	}

	@Override
	public void deleteUserById(long idus) throws Exception {
		if(idus > 0) {
			userRepo.deleteById(idus);
		}
		else {
			throw new Exception("Invalid id");
		}
	}

}
