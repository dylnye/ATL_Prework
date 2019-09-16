package com.galvanize.prework.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "memdb.member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "city")
    private String city;
    @Column(name = "phone_number")
    private String phoneNumber;

    public Long getMemberId() {
        return memberId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getCity() {return city;}
    public void setCity(String city) {this.city = city;}
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}