package com.Payment.Payment.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("card_model_credit")
public class CardModel {

    @Id
    private String id;

    private String name;

    private String number;

    private String maturity;

    private Integer cvv;

    private Long limit;

    private Integer usage;

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

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Integer getUsage() {
        return usage;
    }

    public void setUsage(Integer usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "CardModel{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", maturity=" + maturity +
                ", cvv=" + cvv +
                ", limit=" + limit +
                ", usage=" + usage +
                '}';
    }
}
