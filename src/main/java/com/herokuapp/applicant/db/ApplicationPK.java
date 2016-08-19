package com.herokuapp.applicant.db;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the education database table.
 * 
 */
@Embeddable
public class ApplicationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="position_id")
	private Integer positionId;
	
	@Column(name="applicant_id")
	private Integer applicantId;

	public ApplicationPK() {
	}
	public Integer getApplicantId() {
		return this.applicantId;
	}
	public void setApplicantId(Integer applicantId) {
		this.applicantId = applicantId;
	}
	public Integer getPositionId() {
		return this.positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ApplicationPK)) {
			return false;
		}
		ApplicationPK castOther = (ApplicationPK)other;
		return 
			this.positionId.equals(castOther.positionId) && this.applicantId.equals(castOther.applicantId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.positionId.hashCode();
		hash = hash * prime + this.applicantId.hashCode();
		
		return hash;
	}
}
