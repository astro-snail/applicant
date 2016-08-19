package com.herokuapp.applicant.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the application_view database table.
 * 
 */
@Entity
@XmlRootElement
@Table(name="application_view")
@NamedQueries({
	@NamedQuery(name="Application.findAll", query="SELECT a FROM Application a ORDER BY a.id.positionId, a.id.applicantId"),
	@NamedQuery(name="Application.findByPositionId", 
	query="SELECT a FROM Application a WHERE a.id.positionId = :positionId ORDER BY a.id.applicantId"),
	@NamedQuery(name="Application.findByPositionIdByApplicantName", 
	query="SELECT a FROM Application a WHERE a.id.positionId = :positionId AND a.firstName = :firstName AND a.lastName = :lastName")	
})
public class Application implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ApplicationPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_applied")
	private Date dateApplied;

	private String department;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String status;

	private String title;

	public Application() {
	}

	public ApplicationPK getId() {
		return this.id;
	}

	public void setId(ApplicationPK id) {
		this.id = id;
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