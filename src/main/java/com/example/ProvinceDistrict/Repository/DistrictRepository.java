package com.example.ProvinceDistrict.Repository;

import com.example.ProvinceDistrict.Entity.District;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DistrictRepository extends CrudRepository<District, Integer> {

    void deleteByName(String districtName);

    District findDistrictByName(String districtName);

    List<District> findByProvinceName(String provinceName);
}
