package entity;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
    private String name;
    private Integer age;
    private String address;
    private Date birthDay;
    private BigDecimal amount;

    public Account() {
    }

    public Account(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Account(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}