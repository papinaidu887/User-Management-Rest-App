package in.ashokit.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.UserAccountEntity;


public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Serializable> {

		public UserAccountEntity findByEmailAndPassWord(String email, String passWord);
		
		public UserAccountEntity findByEmail(String emailId);
}
