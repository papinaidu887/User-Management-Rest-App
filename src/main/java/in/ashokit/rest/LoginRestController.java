package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.LoginForm;
import in.ashokit.service.UserManagementService;

@CrossOrigin
@RestController
public class LoginRestController {
	
	@Autowired
	private UserManagementService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginForm form){
		
		String status = service.loginCheck(form);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}

}
