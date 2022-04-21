package in.ashokit.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.UserRegForm;
import in.ashokit.service.UserManagementService;

@CrossOrigin
@RestController
public class RegistrationRestController {

	@Autowired
	private UserManagementService service;

	@GetMapping("/emailCheck/{email}")
	public ResponseEntity<String> emailCheck(@PathVariable("email") String emailId) {
		String status = service.emailCheck(emailId);
		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

	@GetMapping("/countries")
	public ResponseEntity<Map<Integer, String>> getCountries() {

		Map<Integer, String> countriesMap = service.loadCountries();
		return new ResponseEntity<>(countriesMap, HttpStatus.OK);
	}

	@GetMapping("/states/{countryid}")
	public ResponseEntity<Map<Integer, String>> getStates(@PathVariable("countryid") Integer countryId) {
		Map<Integer, String> statesMap = service.loadStates(countryId);
		return new ResponseEntity<>(statesMap, HttpStatus.OK);
	}

	@GetMapping("/cities/{stateid}")
	public ResponseEntity<Map<Integer, String>> getCities(@PathVariable("stateid") Integer stateId) {
		Map<Integer, String> citiesMap = service.loadCities(stateId);
		return new ResponseEntity<>(citiesMap, HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody UserRegForm user) {
		String status = service.saveUser(user);
		return new ResponseEntity<>(status, HttpStatus.CREATED);

	}

}
