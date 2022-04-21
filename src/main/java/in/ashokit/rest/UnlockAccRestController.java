package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.UnlockAccForm;
import in.ashokit.service.UserManagementService;

@CrossOrigin
@RestController
public class UnlockAccRestController {

	@Autowired
	private UserManagementService service;

	@PostMapping("/unlock")
	public ResponseEntity<String> unlocAccount(@RequestBody UnlockAccForm unlock) {
		String unlockAccount = service.unlockAcc(unlock);
		return new ResponseEntity<>(unlockAccount, HttpStatus.OK);
	}

}
