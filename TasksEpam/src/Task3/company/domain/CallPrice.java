package com.company.domain;

public class CallPrice {
    Integer InNetCallCost;
    Integer outNetCallCost;
    Integer homePhoneCallCost;


    public CallPrice() {
    }

    public Integer getInNetCallCost() {
        return InNetCallCost;
    }

    public void setInNetCallCost(Integer inNetCallCost) {
        InNetCallCost = inNetCallCost;
    }

    public Integer getOutNetCallCost() {
        return outNetCallCost;
    }

    public void setOutNetCallCost(Integer outNetCallCost) {
        this.outNetCallCost = outNetCallCost;
    }

    public Integer getHomePhoneCallCost() {
        return homePhoneCallCost;
    }

    public void setHomePhoneCallCost(Integer homePhoneCallCost) {
        this.homePhoneCallCost = homePhoneCallCost;
    }


    @Override
    public String toString() {
        return "CallPrice{" +
                "InNetCallCost=" + InNetCallCost +
                ", outNetCallCost=" + outNetCallCost +
                ", homePhoneCallCost=" + homePhoneCallCost +
                '}';
    }
}
