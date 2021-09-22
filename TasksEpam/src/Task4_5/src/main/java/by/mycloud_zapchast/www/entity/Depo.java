package by.mycloud_zapchast.www.entity;

import java.io.Serializable;

public class Depo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	Integer idDepo;
	String name;
	
	public Depo() {
	}

	public Depo(Integer idDepo, String name) {
		super();
		this.idDepo = idDepo;
		this.name = name;
	}

	public Integer getIdDepo() {
		return idDepo;
	}

	public void setIdDepo(Integer idDepo) {
		this.idDepo = idDepo;
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
		result = prime * result + ((idDepo == null) ? 0 : idDepo.hashCode());
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
		Depo other = (Depo) obj;
		if (idDepo == null) {
			if (other.idDepo != null)
				return false;
		} else if (!idDepo.equals(other.idDepo))
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
		return "Depo [idDepo=" + idDepo + ", name=" + name + "]";
	}

	
	
	

}
