package com.mimi.zfw.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

//@Entity
//@Table(name = "tbl_role")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleModel extends AbstractModel{

//	@Id
//	@GeneratedValue(generator = "uuid")
//	@GenericGenerator(name = "uuid", strategy = "uuid")
//	@Column(name = "ID")
//	private String id;
//	
//	private String name;
//	
//	private String desc;
//	
//	@ManyToMany(mappedBy="roles",cascade={CascadeType.PERSIST})  
//    private List<UserModel> users;  
}
