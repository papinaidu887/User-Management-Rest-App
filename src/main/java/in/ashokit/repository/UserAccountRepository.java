package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.UserAccountEntity;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Integer> {

		public UserAccountEntity findByEmailandPassWord(String email, String password);
		
		public UserAccountEntity findByEmail(String emailId);
}
