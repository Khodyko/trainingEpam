package Task3.com.company.domain;

public class Tarif {
    private String name;
    private String operatorName;
    private Integer payRoll;
    private CallPrice callPrice;
    private Integer smsPrice;
    private Parameters parameters;

    public Tarif() {
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getPayRoll() {
        return payRoll;
    }

    public void setPayRoll(Integer payRoll) {
        this.payRoll = payRoll;
    }

    public CallPrice getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(CallPrice callPrice) {
        this.callPrice = callPrice;
    }

    public Integer getSmsPrice() {
        return smsPrice;
    }

    public void setSmsPrice(Integer smsPrice) {
        this.smsPrice = smsPrice;
    }

    @Override
    public String toString() {
        return "Tarif{" +
                "name='" + name + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", payRoll=" + payRoll +
                ", callPrice=" + callPrice +
                ", smsPrice=" + smsPrice +
                ", lovelynumber= "+ parameters.getLovelyNumber()+
                '}';
    }
}
