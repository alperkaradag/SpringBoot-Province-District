package com.example.ProvinceDistrict.Repository;

import com.example.ProvinceDistrict.Entity.Province;
import org.springframework.data.repository.CrudRepository;

public interface ProvinceRepository extends CrudRepository<Province, Integer> {
    Province findProvinceByName(String provinceName);

    void deleteByName(String name);
}
