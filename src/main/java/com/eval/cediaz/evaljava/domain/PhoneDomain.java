package com.eval.cediaz.evaljava.domain;

import java.util.Objects;

public class PhoneDomain {

    private Integer number;
    private Integer cityCode;
    private Integer countryCode;

    public PhoneDomain() {
    }

    public PhoneDomain(Integer number, Integer cityCode, Integer countryCode) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneDomain phoneDomain = (PhoneDomain) o;
        return Objects.equals(number, phoneDomain.number) && Objects.equals(cityCode, phoneDomain.cityCode) && Objects.equals(countryCode, phoneDomain.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, cityCode, countryCode);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number=" + number +
                ", cityCode=" + cityCode +
                ", countryCode=" + countryCode +
                '}';
    }
}
