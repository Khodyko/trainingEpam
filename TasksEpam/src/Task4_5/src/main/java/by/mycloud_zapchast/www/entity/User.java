package by.mycloud_zapchast.www.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id_user;
	private String name;
	private String secondName;
	private UserRole role;
	private String email;
	private String nameDepo;
	private String nameSector;
	private Boolean agreed;

	public User() {
	}
	
	public User(Integer id_user, String name, String secondName, UserRole role, String email, String nameDepo,
			String nameSector, Boolean agreed) {
		super();
		this.id_user = id_user;
		this.name = name;
		this.secondName = secondName;
		this.role = role;
		this.email = email;
		this.nameDepo = nameDepo;
		this.nameSector = nameSector;
		this.agreed = agreed;
	}

	public User(String name, String secondName, UserRole role, String email, String nameDepo,
			String nameSector, Boolean agreed) {
		super();
		
		this.name = name;
		this.secondName = secondName;
		this.role = role;
		this.email = email;
		this.nameDepo = nameDepo;
		this.nameSector = nameSector;
		this.agreed = agreed;
	}

	public Integer getId_user() {
		return id_user;
	}

	public void setId_user(Integer id_user) {
		this.id_user = id_user;
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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNameDepo() {
		return nameDepo;
	}

	public void setNameDepo(String nameDepo) {
		this.nameDepo = nameDepo;
	}

	public String getNameSector() {
		return nameSector;
	}

	public void setNameSector(String nameSector) {
		this.nameSector = nameSector;
	}

	public Boolean getAgreed() {
		return agreed;
	}

	public void setAgreed(Boolean agreed) {
		this.agreed = agreed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agreed == null) ? 0 : agreed.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nameDepo == null) ? 0 : nameDepo.hashCode());
		result = prime * result + ((nameSector == null) ? 0 : nameSector.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
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
		User other = (User) obj;
		if (agreed == null) {
			if (other.agreed != null)
				return false;
		} else if (!agreed.equals(other.agreed))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id_user == null) {
			if (other.id_user != null)
				return false;
		} else if (!id_user.equals(other.id_user))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nameDepo == null) {
			if (other.nameDepo != null)
				return false;
		} else if (!nameDepo.equals(other.nameDepo))
			return false;
		if (nameSector == null) {
			if (other.nameSector != null)
				return false;
		} else if (!nameSector.equals(other.nameSector))
			return false;
		if (role != other.role)
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id_user=" + id_user + ", name=" + name + ", secondName=" + secondName + ", role=" + role
				+ ", email=" + email + ", nameDepo=" + nameDepo + ", nameSector=" + nameSector + ", agreed=" + agreed
				+ "]";
	}

}
