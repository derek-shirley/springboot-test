package derek.shirley.springboot.test.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import derek.shirley.springboot.test.model.rest.UserRest;
import derek.shirley.springboot.test.repository.rest.UserRestRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRestRepository repo;

	@Override
	public Iterable<UserRest> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<UserRest> findById(Integer id) {
		return repo.findById(id);
	}

	@Override
	public void save(UserRest user) {
		repo.save(user);
	}

	@Override
	public Integer create(UserRest user) {
		UserRest savedUser = repo.save(user);

		return savedUser.getId();
	}

	@Override
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
