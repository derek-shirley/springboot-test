package derek.shirley.springboot.test.repository.rest;

import org.springframework.data.repository.CrudRepository;

import derek.shirley.springboot.test.model.rest.UserRest;

public interface UserRestRepository extends CrudRepository<UserRest, Integer> {

}
