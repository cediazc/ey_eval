package com.eval.cediaz.evaljava.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer number;
    @Column
    private Integer cityCode;
    @Column
    private Integer countryCode;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    public Phone() {

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(id, phone.id) && Objects.equals(number, phone.number) && Objects.equals(cityCode, phone.cityCode) && Objects.equals(countryCode, phone.countryCode) && Objects.equals(user, phone.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, cityCode, countryCode, user);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number=" + number +
                ", cityCode=" + cityCode +
                ", countryCode=" + countryCode +
                ", user=" + user +
                '}';
    }
}
