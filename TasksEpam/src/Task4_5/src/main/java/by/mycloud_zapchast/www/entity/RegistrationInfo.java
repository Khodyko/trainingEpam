package by.mycloud_zapchast.www.entity;

import java.io.Serializable;

public class RegistrationInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String secondName;
	private String password;
	private String role;
	private String email;
	private Integer depoId;
	private Integer sectorId;

	public RegistrationInfo() {
		
	}

	public RegistrationInfo(String name, String secondName, String password, String role, String email, Integer depoId,
			Integer sectorId) {
		
		this.name = name;
		this.secondName = secondName;
		this.password = password;
		this.role = role;
		this.email = email;
		this.depoId = depoId;
		this.sectorId = sectorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDepoId() {
		return depoId;
	}

	public void setDepoId(Integer depoId) {
		this.depoId = depoId;
	}

	public Integer getSectorId() {
		return sectorId;
	}

	public void setSectorId(Integer sectorId) {
		this.sectorId = sectorId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((depoId == null) ? 0 : depoId.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + ((sectorId == null) ? 0 : sectorId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationInfo other = (RegistrationInfo) obj;
		if (depoId == null) {
			if (other.depoId != null)
				return false;
		} else if (!depoId.equals(other.depoId))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		if (sectorId == null) {
			if (other.sectorId != null)
				return false;
		} else if (!sectorId.equals(other.sectorId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RegistrationInfo [name=" + name + ", secondName=" + secondName + ", password=" + password + ", role="
				+ role + ", email=" + email + ", depoId=" + depoId + ", sectorId=" + sectorId + "]";
	}

	
	
	
	
	
	
}
