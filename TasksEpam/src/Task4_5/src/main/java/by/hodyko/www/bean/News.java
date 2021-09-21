package by.hodyko.www.bean;

import java.io.Serializable;

public class News implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String title;
	private String brief;
	

	
	private String fullText;
	private String imgLink;
	
	public News(String title, String fullText, String brief, String imgLink) {
		super();
		this.title = title;
		this.brief = brief;
		this.fullText = fullText;
		if(imgLink==null || imgLink.equals("")) {
			this.imgLink="resources/pictures/surpriseface.jpg";
		}
		else {
		this.imgLink = imgLink;}
	}
	
	public Integer getId() {
		return id;
	}

	public News(Integer id, String title, String fullText, String brief, String imgLink) {
		super();
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.fullText = fullText;
		if(imgLink==null || imgLink.equals("")) {
			this.imgLink="resources/pictures/surpriseface.jpg";
		}
		else {
		this.imgLink = imgLink;}
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFulText(String fullText) {
		this.fullText = fullText;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brief == null) ? 0 : brief.hashCode());
		result = prime * result + ((fullText == null) ? 0 : fullText.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imgLink == null) ? 0 : imgLink.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		News other = (News) obj;
		if (brief == null) {
			if (other.brief != null)
				return false;
		} else if (!brief.equals(other.brief))
			return false;
		if (fullText == null) {
			if (other.fullText != null)
				return false;
		} else if (!fullText.equals(other.fullText))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imgLink == null) {
			if (other.imgLink != null)
				return false;
		} else if (!imgLink.equals(other.imgLink))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", brief=" + brief + ", fullText=" + fullText + ", imgLink="
				+ imgLink + "]";
	}

	

	
	

}
