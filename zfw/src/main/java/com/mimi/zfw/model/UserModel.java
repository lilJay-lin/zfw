package com.mimi.zfw.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

import com.mimi.zfw.web.validator.DateFormat;

@Entity
@Table(name = "tbl_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserModel extends AbstractModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6129619356724468683L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(name = "ID")
	private String id;

	@Pattern(regexp = "[A-Za-z0-9_]{5,20}", message = "{username.illegal}")
	private String userName;

	@Email(message = "{email.illegal}")
	private String email;

	@Pattern(regexp = "[0-9_]{11}", message = "{phoneNum.illegal}")
	private String phoneNum;

	@Pattern(regexp = "[A-Za-z0-9]{5,80}", message = "{password.illegal}")
	private String password;

	@DateFormat(message = "{register.date.error}")
	private Date registerDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

//	@Override
//	public int hashCode() {
//		return Integer.parseInt(id);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		UserModel other = (UserModel) obj;
//		if (id != other.id)
//			return false;
//		return true;
//	}

}
