package by.mycloud_zapchast.www.entity;

import java.io.Serializable;

public class StandartSearchItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer idItem;
	private String nnSap;
	private String nn;
	private String name;
	
	public StandartSearchItem() {}

	public StandartSearchItem(Integer idItem, String nnSap, String nn, String name) {
		super();
		this.idItem = idItem;
		this.nnSap = nnSap;
		this.nn = nn;
		this.name = name;
	}

	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public String getNnSap() {
		return nnSap;
	}

	public void setNnSap(String nnSap) {
		this.nnSap = nnSap;
	}

	public String getNn() {
		return nn;
	}

	public void setNn(String nn) {
		this.nn = nn;
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
		result = prime * result + ((idItem == null) ? 0 : idItem.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nn == null) ? 0 : nn.hashCode());
		result = prime * result + ((nnSap == null) ? 0 : nnSap.hashCode());
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
		StandartSearchItem other = (StandartSearchItem) obj;
		if (idItem == null) {
			if (other.idItem != null)
				return false;
		} else if (!idItem.equals(other.idItem))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nn == null) {
			if (other.nn != null)
				return false;
		} else if (!nn.equals(other.nn))
			return false;
		if (nnSap == null) {
			if (other.nnSap != null)
				return false;
		} else if (!nnSap.equals(other.nnSap))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StandartSearchItem [idItem=" + idItem + ", nnSap=" + nnSap + ", nn=" + nn + ", name=" + name + "]";
	}
	
	
	
	
	
}
