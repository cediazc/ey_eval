package com.eval.cediaz.evaljava.domain;

import java.util.Objects;

public class PhoneDomain {

    private Long id;
    private Integer number;
    private Integer cityCode;
    private Integer countryCode;

    public PhoneDomain() {
    }

    public PhoneDomain(Long id, Integer number, Integer cityCode, Integer countryCode) {
        this.id = id;
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        PhoneDomain that = (PhoneDomain) o;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects.equals(cityCode, that.cityCode) && Objects.equals(countryCode, that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, cityCode, countryCode);
    }

    @Override
    public String toString() {
        return "PhoneDomain{" +
                "id=" + id +
                ", number=" + number +
                ", cityCode=" + cityCode +
                ", countryCode=" + countryCode +
                '}';
    }
}
