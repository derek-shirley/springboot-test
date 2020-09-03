package derek.shirley.springboot.test.repository;

import org.springframework.data.repository.CrudRepository;

import derek.shirley.springboot.test.entities.UserRoles;

public interface UserRolesRepository extends CrudRepository<UserRoles, Integer> {

}
