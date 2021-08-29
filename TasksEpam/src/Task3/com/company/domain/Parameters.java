package Task3.com.company.domain;

public class Parameters {
	String lovelyNumber;
	Integer tarificationTime;
	Integer comingPay;

	public Parameters() {
	}

	public String getLovelyNumber() {
		return lovelyNumber;
	}

	public void setLovelyNumber(String lovelyNumber) {
		this.lovelyNumber = lovelyNumber;
	}

	public Integer getTarificationTime() {
		return tarificationTime;
	}

	public void setTarificationTime(Integer tarificationTime) {
		this.tarificationTime = tarificationTime;
	}

	public Integer getComingPay() {
		return comingPay;
	}

	public void setComingPay(Integer comingPay) {
		this.comingPay = comingPay;
	}

	@Override
	public String toString() {
		return "Parameters{" + "lovelyNumber='" + lovelyNumber + '\'' + ", tarificationTime=" + tarificationTime
				+ ", comingPay=" + comingPay + '}';
	}
}
