package in.ashokit.bindings;

import java.time.LocalDate;

import lombok.Data;



@Data
public class UserRegForm {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String accStatus;
	private Long phoneNumber;
	private LocalDate dob;
	private String gender;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	

}
