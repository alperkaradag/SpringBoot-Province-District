package com.example.ProvinceDistrict.Service;

import com.example.ProvinceDistrict.Entity.District;
import com.example.ProvinceDistrict.Repository.DistrictRepository;
import com.example.ProvinceDistrict.Entity.Province;
import com.example.ProvinceDistrict.Repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    DistrictRepository districtRepository;

    public List<District> getAllDistricts(String provinceName){
        return districtRepository.findByProvinceName(provinceName);
    }

    public void saveDistrict(String provinceName, District district){

        Province p = provinceRepository.findProvinceByName(provinceName);
        p.addDistrict(district);
        district.setProvince(p);
        districtRepository.save(district);


//        List<District> dList = provinceService.findByName(provinceName).getDistricts();
//        dList.add(district);
//        Province p = new Province();
//        p.setName(provinceName);
//        for(District d : dList){
//            p.addDistrict(d);
//        }
//        provinceService.updateProvince(provinceName, p);
    }

//    @Transactional
//    public void deleteDistrict(String districtName, String provinceName){
//        Province p = provinceRepository.findProvinceByName(provinceName);
//        District d = districtRepository.findDistrictByName(districtName);
//        String msg = "";
//        if(d==null) msg += "district is null";
//        if(p==null) msg += "\nprovince is null";
//        System.out.println(msg);
//        districtRepository.deleteByName(districtName);
//    }
}
