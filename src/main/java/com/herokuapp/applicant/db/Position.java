package com.herokuapp.applicant.db;

import java.io.Serializable;
import javax.persistence.*;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the position database table.
 * 
 */
@Entity
@XmlRootElement
@NamedQuery(name="Position.findAll", query="SELECT p FROM Position p ORDER BY p.positionId")
public class Position implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="position_id")
	private Integer positionId;

	private String department;

	private String title;

	public Position() {
	}

	public Integer getPositionId() {
		return this.positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}