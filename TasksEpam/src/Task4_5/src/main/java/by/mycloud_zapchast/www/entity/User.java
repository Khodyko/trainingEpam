package by.mycloud_zapchast.www.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id_user;
	private String name;
	private String secondName;
	private UserRole role;
	private String email;
	private Integer id_depo;
	private Integer id_sector;
	private Boolean agreed;

	public User() {
	}

	public User(Integer id_user, String name, String secondName, UserRole role, String email, Integer id_depo,
			Integer id_sector, Boolean agreed) {
		super();
		this.id_user = id_user;
		this.name = name;
		this.secondName = secondName;
		this.role = role;
		this.email = email;
		this.id_depo = id_depo;
		this.id_sector = id_sector;
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

	public Integer getId_depo() {
		return id_depo;
	}

	public void setId_depo(Integer id_depo) {
		this.id_depo = id_depo;
	}

	public Integer getId_sector() {
		return id_sector;
	}

	public void setId_sector(Integer id_sector) {
		this.id_sector = id_sector;
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
		result = prime * result + ((id_depo == null) ? 0 : id_depo.hashCode());
		result = prime * result + ((id_sector == null) ? 0 : id_sector.hashCode());
		result = prime * result + ((id_user == null) ? 0 : id_user.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (id_depo == null) {
			if (other.id_depo != null)
				return false;
		} else if (!id_depo.equals(other.id_depo))
			return false;
		if (id_sector == null) {
			if (other.id_sector != null)
				return false;
		} else if (!id_sector.equals(other.id_sector))
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
				+ ", email=" + email + ", id_depo=" + id_depo + ", id_sector=" + id_sector + ", agreed=" + agreed + "]";
	}
	
	
}
