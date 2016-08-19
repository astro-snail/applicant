package com.herokuapp.applicant.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the work_experience database table.
 * 
 */
@Entity
@XmlRootElement
@Table(name="work_experience")
@NamedQuery(name="Experience.findAllForApplicant", query="SELECT e FROM Experience e WHERE e.id.applicantId = :id ORDER BY e.id.dateFrom DESC")
public class Experience implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ExperiencePK id;

	@Column(name="company_name")
	private String companyName;

	@Temporal(TemporalType.DATE)
	@Column(name="date_to")
	private Date dateTo;

	private String details;

	private String position;
	
	//bi-directional many-to-one association to Applicant
	@ManyToOne
	@JoinColumn(name="applicant_id", referencedColumnName="applicant_id", nullable=false, insertable=false, updatable=false)
	private Applicant applicant;

	public Experience() {
	}

	public ExperiencePK getId() {
		return this.id;
	}

	public void setId(ExperiencePK id) {
		this.id = id;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getDateTo() {
		return this.dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}