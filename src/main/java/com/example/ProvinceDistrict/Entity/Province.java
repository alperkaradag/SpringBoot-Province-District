package com.example.ProvinceDistrict.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Province{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<District> districts;

    public Province(){}

    public Province(String name){
        this.name = name;
    }

    public Province(String name, List<District> districts){
        this.name = name;
        this.districts = districts;
        districts.forEach(district -> district.setProvince(this));
    }

    public void addDistrict(District district) {
        districts.add(district);
    }

    public void removeDistrict(String districtName){
        for(District d : districts){
            if(d.getName().equals(districtName)){
                System.out.println(districts.size());
                d.setProvince(null);
                districts.remove(d);
                System.out.println("district removed: " + districtName);
                System.out.println(districts.size());
                return;
            }
        }
    }

    @JsonIgnore
    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<District> getDistricts(){
        return districts;
    }

    public void setDistricts(List<District> toAdd) {
        districts = toAdd;
    }
}
