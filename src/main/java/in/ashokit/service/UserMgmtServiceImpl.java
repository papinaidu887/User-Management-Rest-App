package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

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

public class UserMgmtServiceImpl implements UserMgmtService{
	
	@Autowired
	private UserAccountRepository userAccountRepo;
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private StateRepository stateRepo;
	
	@Autowired
	private CityRepository cityRepo;
	
	

	
	// Login Functionality implementation
	@Override
	public String loginCheck(LoginForm loginForm) {
		
		UserAccountEntity userAcc  = userAccountRepo.findByEmailandPassWord(loginForm.getEmail(), loginForm.getPassWord());
		if(userAcc == null)
		{
			return "Invalid Credentials";
		}
		if(userAcc.getAccStatus().equals("LOCKED"))
		{
			return "Your Account is Locked";
		}
		
		return "SUCCESS";
	}

	@Override
	public String emailCheck(String emailId) {
		UserAccountEntity userAcc = userAccountRepo.findByEmail(emailId);
		if(userAcc==null)
		{
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
		
		List<StateMasterEntity> states =  stateRepo.findByCountryId(countryId);		
		
		Map<Integer, String> statesMap = new HashMap<>();
		states.forEach(state -> {
			statesMap.put(state.getStateId(), state.getStateName());
		});
		
		return statesMap;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {
		
		List<CityMasterEntity> cities= cityRepo.findByStateId(stateId);
		
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
		
		return "SUCCESS";
	}

	@Override
	public String unlockAcc(UnlockAccForm unlockAccForm) {
		
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String forgotPwd(String emailId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static String generateRandomPwd(int n)
    {
  
        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
  
        for (int i = 0; i < n; i++) {
  
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }
	

}
