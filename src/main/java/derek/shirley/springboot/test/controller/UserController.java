package derek.shirley.springboot.test.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import derek.shirley.springboot.test.entities.Role;
import derek.shirley.springboot.test.model.rest.UserRest;
import derek.shirley.springboot.test.service.UserService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Iterable<UserRest> findAll() {
		return userService.findAll();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> findById(@PathVariable("id") Integer id) throws JsonProcessingException {

		ObjectMapper mappter = new ObjectMapper();

		Optional<UserRest> user = userService.findById(id);
		String json = "";

		if (user.isPresent()) {
			json = mappter.writeValueAsString(user.get());
		}

		ResponseEntity<String> entity = new ResponseEntity<>(json, HttpStatus.OK);

		return entity;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody UserRest user)
			throws JsonProcessingException {

		Optional<UserRest> userOptional = userService.findById(id);

		if (!userOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		try {
			validateModel(user);

			user.setId(id);

			userService.save(user);

			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			ResponseEntity<String> entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			return entity;
		}
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody UserRest user) throws JsonProcessingException {
		userService.create(user);

		try {
			validateModel(user);

			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			ResponseEntity<String> entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			return entity;
		}
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws JsonProcessingException {
		userService.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	private void validateModel(UserRest user) {
		if (user == null) {
			throw new RuntimeException("No User information provided");
		}

		StringBuffer errors = new StringBuffer();

		if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
			errors.append("\n\"firstName\": \"First name is mandatory\"");
		}

		if (user.getRoles() != null && !user.getRoles().isEmpty()) {
			for (int i = 0; i < user.getRoles().size(); i++) {
				Role r = user.getRoles().get(i);

				if (r.getName() == null || r.getName().isEmpty()) {
					errors.append(String.format("\n\"roles[%s].name\": \"Name is mandatory\"", i));
				}
			}
		}

		if (!errors.toString().isEmpty()) {
			throw new RuntimeException(errors.toString());
		}
	}
}
