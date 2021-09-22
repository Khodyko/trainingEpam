package by.mycloud_zapchast.www.entity;

import java.io.Serializable;

public class Sector implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Integer idSector;
	String name;
	
	public Sector() {
	}

	public Sector(Integer idSector, String name) {
		super();
		this.idSector = idSector;
		this.name = name;
	}

	public Integer getIdSector() {
		return idSector;
	}

	public void setIdSector(Integer idSector) {
		this.idSector = idSector;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idSector == null) ? 0 : idSector.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Sector other = (Sector) obj;
		if (idSector == null) {
			if (other.idSector != null)
				return false;
		} else if (!idSector.equals(other.idSector))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sector [idSector=" + idSector + ", name=" + name + "]";
	}

	
	
	

}
