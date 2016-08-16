package com.herokuapp.applicant.db;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the work_experience database table.
 * 
 */
@Embeddable
public class ExperiencePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="applicant_id")
	private Integer applicantId;

	@Temporal(TemporalType.DATE)
	@Column(name="date_from")
	private java.util.Date dateFrom;

	public ExperiencePK() {
	}
	public Integer getApplicantId() {
		return this.applicantId;
	}
	public void setApplicantId(Integer applicantId) {
		this.applicantId = applicantId;
	}
	public java.util.Date getDateFrom() {
		return this.dateFrom;
	}
	public void setDateFrom(java.util.Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ExperiencePK)) {
			return false;
		}
		ExperiencePK castOther = (ExperiencePK)other;
		return 
			this.applicantId.equals(castOther.applicantId)
			&& this.dateFrom.equals(castOther.dateFrom);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.applicantId.hashCode();
		hash = hash * prime + this.dateFrom.hashCode();
		
		return hash;
	}
}