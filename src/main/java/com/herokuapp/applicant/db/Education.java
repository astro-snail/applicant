package com.herokuapp.applicant.db;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The persistent class for the education database table.
 * 
 */
@Entity
@XmlRootElement
@NamedQuery(name="Education.findAllForApplicant", query="SELECT e FROM Education e WHERE e.id.applicantId = :id ORDER BY e.id.dateFrom DESC")
public class Education implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EducationPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_to")
	private Date dateTo;

	private String details;

	@Column(name="educational_institution")
	private String educationalInstitution;
	
	//bi-directional many-to-one association to Applicant
	@ManyToOne
	@JoinColumn(name="applicant_id", referencedColumnName="applicant_id", nullable=false, insertable=false, updatable=false)
	private Applicant applicant;

	public Education() {
	}

	public EducationPK getId() {
		return this.id;
	}

	public void setId(EducationPK id) {
		this.id = id;
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

	public String getEducationalInstitution() {
		return this.educationalInstitution;
	}

	public void setEducationalInstitution(String educationalInstitution) {
		this.educationalInstitution = educationalInstitution;
	}

}