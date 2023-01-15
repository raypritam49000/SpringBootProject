package com.rest.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.usertype.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "PERSON_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "CREATED_TIME")
	private Date creationTime;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_TIME")
	private Date updatedTime;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "DOB")
	private Date dateofBirth;

	@Lob
	@Column(name = "PROFILE_PIC")
	private byte[] profilePic;

	@Transient
	private transient String dateOfBirthString;
}