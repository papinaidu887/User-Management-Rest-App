package in.ashokit.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.UnlockAccForm;
import in.ashokit.bindings.UserRegForm;
import in.ashokit.entity.CityMasterEntity;
import in.ashokit.entity.CountryMasterEntity;
import in.ashokit.entity.StateMasterEntity;
import in.ashokit.entity.UserAccountEntity;
import in.ashokit.repository.CityRepository;
import in.ashokit.repository.CountryRepository;
import in.ashokit.repository.StateRepository;
import in.ashokit.repository.UserAccountRepository;
import in.ashokit.util.EmailUtils;


@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private UserAccountRepository userAccountRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private EmailUtils emailUtils;

	// Login Functionality implementation
	@Override
	public String loginCheck(LoginForm loginForm) {

		UserAccountEntity userAcc = userAccountRepo.findByEmailAndPassWord(loginForm.getEmail(),
				loginForm.getPassWord());
		if (userAcc == null) {
			return "Invalid Credentials";
		}
		if (userAcc.getAccStatus().equals("LOCKED")) {
			return "Your Account is Locked";
		}

		return "SUCCESS";
	}

	@Override
	public String emailCheck(String emailId) {
		UserAccountEntity userAcc = userAccountRepo.findByEmail(emailId);
		if (userAcc == null) {
			return "UNIQUE";
		}
		return "DUPLICATE";
	}

	@Override
	public Map<Integer, String> loadCountries() {

		List<CountryMasterEntity> countries = countryRepo.findAll();

		Map<Integer, String> countryMap = new HashMap<>();
		countries.forEach(country -> {
			countryMap.put(country.getCountryId(), country.getCountryName());
		});

		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStates(Integer countryId) {

		List<StateMasterEntity> states = stateRepo.findByCountryId(countryId);

		Map<Integer, String> statesMap = new HashMap<>();
		states.forEach(state -> {
			statesMap.put(state.getStateId(), state.getStateName());
		});

		return statesMap;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {

		List<CityMasterEntity> cities = cityRepo.findByStateId(stateId);

		Map<Integer, String> citiesMap = new HashMap<>();
		cities.forEach(city -> {
			citiesMap.put(city.getCityId(), city.getCityName());
		});

		return citiesMap;
	}

	@Override
	public String saveUser(UserRegForm userRegForm) {

		userRegForm.setPassword(generateRandomPwd(6));

		UserAccountEntity entity = new UserAccountEntity();
		entity.setAccStatus("LOCKED");
		BeanUtils.copyProperties(userRegForm, entity);

		userAccountRepo.save(entity);

		// TODO: send email with Temp passWord to unlock acc
		String subject = "User Registration - Ashok IT";
		String emailBody = readUnlockAccountEmailBody(userRegForm);
		emailUtils.sendEmail(userRegForm.getEmail(), subject, emailBody);

		return "SUCCESS";
	}

	@Override
	public String unlockAcc(UnlockAccForm unlockAccForm) {

		// TODO Auto-generated method stub
		
		UserAccountEntity userAcc  = 
				userAccountRepo.findByEmailAndPassWord(unlockAccForm.getEmail(), unlockAccForm.getTempPwd());
		if(userAcc==null)
		{
			return "Invalid Temporary Pwd";
		}
		userAcc.setPassWord(unlockAccForm.getNewPwd());
		userAcc.setAccStatus("UNLOCKED");
		return "SUCCESS";
	}

	@Override
	public String forgotPwd(String emailId) {
		UserAccountEntity userAcc = userAccountRepo.findByEmail(emailId);

		if (userAcc == null) {
			return "Invalid Email";
		}

		String subject = "Recover Password - Ashok IT";
		String body = readRecoverPwdEmailBody(userAcc);
		emailUtils.sendEmail(emailId, subject, body);
		
		return "Password sent to your registered email.";
	}

	private static String generateRandomPwd(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	private String readUnlockAccountEmailBody(UserRegForm user) {
		String body = "";

		StringBuffer buffer = new StringBuffer();
		Path filePath = Paths.get("UNLOCK-ACCOUNT-EMAIL-BODY-TEMPLATE.txt");

		try (Stream<String> stream = Files.lines(filePath)) {
			stream.forEach(line -> {
				buffer.append(line);
			});
			body = buffer.toString();
			body = body.replace("{FNAME}", user.getFirstName());
			body = body.replace("{LNAME}", user.getLastName());
			body = body.replace("{TEMP-PWD}", user.getPassword());
			body = body.replace("{EMAIL}", user.getEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	private String readRecoverPwdEmailBody(UserAccountEntity entity) {
		String body = "";

		StringBuffer buffer = new StringBuffer();
		Path filePath = Paths.get("RECOVER-PASSWORD-EMAIL-BODY-TEMPLATE.txt");

		try (Stream<String> stream = Files.lines(filePath)) {
			stream.forEach(line -> {
				buffer.append(line);
			});
			body = buffer.toString();
			body = body.replace("{FNAME}", entity.getFirstName());
			body = body.replace("{LNAME}", entity.getLastName());
			body = body.replace("{PWD}", entity.getPassWord());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

}
