package com.Payment.Payment.Request;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

public class CardRequest {

    private String name;

    private String number;

    private String maturity;

    private Integer cvv;

    private Long value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
