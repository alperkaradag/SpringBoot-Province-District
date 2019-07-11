package com.example.ProvinceDistrict.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Province province;

    public District(){}

    public District(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @JsonIgnore
    public void setProvince(Province province) {
        this.province = province;
    }
}
