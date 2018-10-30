package com.app.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Employee", uniqueConstraints = { @UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "EMAIL") })
public class EmployeeEntity implements Serializable {

	private static final long serialVersionUID = -1798070786993154676L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer employeeId;

	@Column(name = "EMAIL", unique = true, nullable = false, length = 100)
	private String email;

	@Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
	private String firstName;

	@Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
	private String lastName;

	
	/*
	CascadeType.PERSIST : cascade type presist means that save() or persist() operations cascade to related entities.
	CascadeType.MERGE : cascade type merge means that related entities are merged when the owning entity is merged.
	CascadeType.REFRESH : cascade type refresh does the same thing for the refresh() operation.
	CascadeType.REMOVE : cascade type remove removes all related entities association with this setting when the owning entity is deleted.
	CascadeType.DETACH : cascade type detach detaches all related entities if a “manual detach” occurs.
	CascadeType.ALL : cascade type all is shorthand for all of the above cascade operations.
	 */
	
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	private Set<AccountEntity> accounts;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<AccountEntity> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<AccountEntity> accounts) {
		this.accounts = accounts;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof EmployeeEntity))
			return false;

		EmployeeEntity otherEmployee = (EmployeeEntity) o;

		if (getEmployeeId() != null ? !getEmployeeId().equals(otherEmployee.getEmployeeId())
				: otherEmployee.getEmployeeId() != null)
			return false;
		if (getFirstName() != null ? !getFirstName().equals(otherEmployee.getFirstName())
				: otherEmployee.getFirstName() != null)
			return false;
		if (getLastName() != null ? !getLastName().equals(otherEmployee.getLastName())
				: otherEmployee.getLastName() != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = getEmployeeId() != null ? getEmployeeId().hashCode() : 0;
		result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
		result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
		return result;
	}

}