package com.herokuapp.applicant.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the applicant_view database table.
 * 
 */
@Entity
@XmlRootElement
@Table(name="applicant_view")
@NamedQueries({
	@NamedQuery(name="Applicant.findAll", query="SELECT a FROM Applicant a ORDER BY a.applicantId"),
	@NamedQuery(name="Applicant.findByName", query="SELECT a FROM Applicant a WHERE a.firstName = :firstName AND a.lastName = :lastName")
})
public class Applicant implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="applicant_id")
	private Integer applicantId;

	@Temporal(TemporalType.DATE)
	@Column(name="date_applied")
	private Date dateApplied;

	private String department;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="position_id")
	private Integer positionId;

	private String status;

	private String title;

	public Applicant() {
	}

	public Integer getApplicantId() {
		return this.applicantId;
	}

	public void setApplicantId(Integer applicantId) {
		this.applicantId = applicantId;
	}

	public Date getDateApplied() {
		return this.dateApplied;
	}

	public void setDateApplied(Date dateApplied) {
		this.dateApplied = dateApplied;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getPositionId() {
		return this.positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}