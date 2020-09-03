package derek.shirley.springboot.test.service;

import java.util.Optional;

import derek.shirley.springboot.test.model.rest.UserRest;

public interface UserService {
	Iterable<UserRest> findAll();

	Optional<UserRest> findById(Integer id);

	void save(UserRest user);

	Integer create(UserRest user);

	void deleteById(Integer id);
}
