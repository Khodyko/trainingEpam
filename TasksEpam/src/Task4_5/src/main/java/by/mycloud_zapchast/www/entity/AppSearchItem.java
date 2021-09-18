package by.mycloud_zapchast.www.entity;

import java.io.Serializable;

public class AppSearchItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idItem;
	private String nnSap;
	private String nn;
	private String name;
	private String units;
	private String year;
	private Double sectorsSummApplication;
	private Double depoFinalApplication;

	public AppSearchItem() {	}

	public AppSearchItem(Integer idItem, String nnSap, String nn, String name, String units, String year,
			Double sectorsSummApplication, Double depoFinalApplication) {
		super();
		this.idItem = idItem;
		this.nnSap = nnSap;
		this.nn = nn;
		this.name = name;
		this.units = units;
		this.year = year;
		this.sectorsSummApplication = sectorsSummApplication;
		this.depoFinalApplication = depoFinalApplication;
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

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getSectorsSummApplication() {
		return sectorsSummApplication;
	}

	public void setSectorsSummApplication(Double sectorsSummApplication) {
		this.sectorsSummApplication = sectorsSummApplication;
	}

	public Double getDepoFinalApplication() {
		return depoFinalApplication;
	}

	public void setDepoFinalApplication(Double depoFinalApplication) {
		this.depoFinalApplication = depoFinalApplication;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((depoFinalApplication == null) ? 0 : depoFinalApplication.hashCode());
		result = prime * result + ((idItem == null) ? 0 : idItem.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nn == null) ? 0 : nn.hashCode());
		result = prime * result + ((nnSap == null) ? 0 : nnSap.hashCode());
		result = prime * result + ((sectorsSummApplication == null) ? 0 : sectorsSummApplication.hashCode());
		result = prime * result + ((units == null) ? 0 : units.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
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
		AppSearchItem other = (AppSearchItem) obj;
		if (depoFinalApplication == null) {
			if (other.depoFinalApplication != null)
				return false;
		} else if (!depoFinalApplication.equals(other.depoFinalApplication))
			return false;
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
		if (sectorsSummApplication == null) {
			if (other.sectorsSummApplication != null)
				return false;
		} else if (!sectorsSummApplication.equals(other.sectorsSummApplication))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseItem [idItem=" + idItem + ", nnSap=" + nnSap + ", nn=" + nn + ", name=" + name + ", units=" + units
				+ ", year=" + year + ", sectorsSummApplication=" + sectorsSummApplication + ", depoFinalApplication="
				+ depoFinalApplication + "]";
	};
	
	
	

}
