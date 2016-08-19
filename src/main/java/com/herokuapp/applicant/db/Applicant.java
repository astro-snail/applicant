package com.herokuapp.applicant.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the applicant database table.
 * 
 */
@Entity
@XmlRootElement
@Table(name="applicant")
@NamedQuery(name="Applicant.findAll", query="SELECT a FROM Applicant a ORDER BY a.applicantId")
public class Applicant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="applicant_id")
	private Integer applicantId;

	private String address;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_birth")
	private Date dateOfBirth;

	private String email;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	private String landline;

	@Column(name="last_name")
	private String lastName;

	private String mobile;
	
	//bi-directional many-to-one association to WorkExperience
	@OneToMany(mappedBy="applicant")
	@OrderBy("id.applicantId, id.dateFrom DESC")
	private List<Experience> experience;

	//bi-directional many-to-one association to Education
	@OneToMany(mappedBy="applicant")
	@OrderBy("id.applicantId, id.dateFrom DESC")
	private List<Education> education;

	public Applicant() {
	}

	public Integer getApplicantId() {
		return this.applicantId;
	}

	public void setApplicantId(Integer applicantId) {
		this.applicantId = applicantId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLandline() {
		return this.landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public List<Experience> getExperience() {
		return this.experience;
	}
	
	public void setExperience(List<Experience> experience) {
		this.experience = experience;
	}

	public List<Education> getEducation() {
		return this.education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}
}