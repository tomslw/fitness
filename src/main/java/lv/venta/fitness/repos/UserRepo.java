package lv.venta.fitness.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.fitness.models.User;

public interface UserRepo extends CrudRepository<User, Long>{

	User findByIdus(long idus);

}
