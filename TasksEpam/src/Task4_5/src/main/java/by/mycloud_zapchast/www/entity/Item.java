package by.mycloud_zapchast.www.entity;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Map;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idItem;
	private String nnSap;
	private String nn;
	private String name;
	private String units;
	private String[] img;
	private String[] locomotive_seria;
	private String itemParent;
	private Timestamp dateOfChange;
	private Double cost;
	private Double sectorsSummApplication;
	private Double depoFinalApplication;
	private Map<String, Double> sectorApplication;
	
	
	
	
	public Item() {
		super();
	}
	
	public Item(Integer idItem, String nnSap, String nn, String name, String units, String[] img,
			String[] locomotive_seria, String itemParent, Timestamp dateOfChange, Double cost,
			Double sectorsSummApplication, Double depoFinalApplication, Map<String, Double> sectorApplication) {
		super();
		this.idItem = idItem;
		this.nnSap = nnSap;
		this.nn = nn;
		this.name = name;
		this.units = units;
		this.img = img;
		this.locomotive_seria = locomotive_seria;
		this.itemParent = itemParent;
		this.dateOfChange = dateOfChange;
		this.cost = cost;
		this.sectorsSummApplication = sectorsSummApplication;
		this.depoFinalApplication = depoFinalApplication;
		this.sectorApplication = sectorApplication;
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
	public String[] getImg() {
		return img;
	}
	public void setImg(String[] img) {
		this.img = img;
	}
	public String[] getLocomotive_seria() {
		return locomotive_seria;
	}
	public void setLocomotive_seria(String[] locomotive_seria) {
		this.locomotive_seria = locomotive_seria;
	}
	public String getItemParent() {
		return itemParent;
	}
	public void setItemParent(String itemParent) {
		this.itemParent = itemParent;
	}
	public Timestamp getDateOfChange() {
		return dateOfChange;
	}
	public void setDateOfChange(Timestamp dateOfChange) {
		this.dateOfChange = dateOfChange;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
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
	public Map<String, Double> getSectorApplication() {
		return sectorApplication;
	}
	public void setSectorApplication(Map<String, Double> sectorApplication) {
		this.sectorApplication = sectorApplication;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((dateOfChange == null) ? 0 : dateOfChange.hashCode());
		result = prime * result + ((depoFinalApplication == null) ? 0 : depoFinalApplication.hashCode());
		result = prime * result + ((idItem == null) ? 0 : idItem.hashCode());
		result = prime * result + Arrays.hashCode(img);
		result = prime * result + ((itemParent == null) ? 0 : itemParent.hashCode());
		result = prime * result + Arrays.hashCode(locomotive_seria);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nn == null) ? 0 : nn.hashCode());
		result = prime * result + ((nnSap == null) ? 0 : nnSap.hashCode());
		result = prime * result + ((sectorApplication == null) ? 0 : sectorApplication.hashCode());
		result = prime * result + ((sectorsSummApplication == null) ? 0 : sectorsSummApplication.hashCode());
		result = prime * result + ((units == null) ? 0 : units.hashCode());
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
		Item other = (Item) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (dateOfChange == null) {
			if (other.dateOfChange != null)
				return false;
		} else if (!dateOfChange.equals(other.dateOfChange))
			return false;
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
		if (!Arrays.equals(img, other.img))
			return false;
		if (itemParent == null) {
			if (other.itemParent != null)
				return false;
		} else if (!itemParent.equals(other.itemParent))
			return false;
		if (!Arrays.equals(locomotive_seria, other.locomotive_seria))
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
		if (sectorApplication == null) {
			if (other.sectorApplication != null)
				return false;
		} else if (!sectorApplication.equals(other.sectorApplication))
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
		return true;
	}

	@Override
	public String toString() {
		return "Item [idItem=" + idItem + ", nnSap=" + nnSap + ", nn=" + nn + ", name=" + name + ", units=" + units
				+ ", img=" + Arrays.toString(img) + ", locomotive_seria=" + Arrays.toString(locomotive_seria)
				+ ", itemParent=" + itemParent + ", dateOfChange=" + dateOfChange + ", cost=" + cost
				+ ", sectorsSummApplication=" + sectorsSummApplication + ", depoFinalApplication="
				+ depoFinalApplication + ", sectorApplication=" + sectorApplication + "]";
	}
	
}
