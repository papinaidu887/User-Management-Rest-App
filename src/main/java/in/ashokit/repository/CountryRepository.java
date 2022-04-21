package in.ashokit.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.CountryMasterEntity;


public interface CountryRepository extends JpaRepository<CountryMasterEntity, Serializable> {
	
	public List<CountryMasterEntity> findAll();
}
