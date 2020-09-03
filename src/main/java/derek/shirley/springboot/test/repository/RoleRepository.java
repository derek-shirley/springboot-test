package derek.shirley.springboot.test.repository;

import org.springframework.data.repository.CrudRepository;

import derek.shirley.springboot.test.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}
