package com.leonardonatali.smartclockpunch.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String businessName;
	private String cnpj;
	private Date createdAt;
	private Date updatedAt;
	private List<Employee> Employees;

	public Company() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "business_name", nullable = false)
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	@Column(name = "cnpj", nullable = false)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Column(name = "created_at", nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at", nullable = false)
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@OneToMany(mappedBy = "company_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Employee> getEmployees() {
		return Employees;
	}

	public void setEmployees(List<Employee> Employees) {
		this.Employees = Employees;
	}

	@PreUpdate
	public void preUpdate() {
		this.updatedAt = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date currentDate = new Date();
		this.createdAt = currentDate;
		this.updatedAt = currentDate;
	}

	@Override
	public String toString() {
		return String.format("Company [id: %d, businessName: %s, cnpj: %s, createdAt: %s, updatedAt: %s]", id,
				businessName, cnpj, createdAt.toString(), updatedAt.toString());
	}
}
