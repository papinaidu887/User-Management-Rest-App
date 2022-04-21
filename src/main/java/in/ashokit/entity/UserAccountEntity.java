package in.ashokit.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Data
@Entity
@Table(name="USER_DETAILS")
public class UserAccountEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Integer UserId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="USER_PWD")
	private String passWord;
	
	@Column(name="Phone_Number")
	private Long phoneNumber;
	
	@Column(name="DOB")
	private Date birthDate;
	
	@Column(name="GENDER")
	private String Gender;
	
	@Column(name="CITY_ID")
	private Integer cityId;
	
	@Column(name="STATE_ID")
	private Integer stateId;
	
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	@Column(name="ACC_STATUS")
	private String accStatus;
	
	@Column(name="CREATE_DATE", updatable = false)
	@CreationTimestamp
	private LocalDate createDate;
	
	@Column(name="UPDATE_DATE", insertable = false)
	@UpdateTimestamp
	private LocalDate updateDate;
	

}
