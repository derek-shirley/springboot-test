package derek.shirley.springboot.test.repository;

import org.springframework.data.repository.CrudRepository;

import derek.shirley.springboot.test.entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
