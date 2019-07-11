package com.example.ProvinceDistrict.Service;

import com.example.ProvinceDistrict.*;
import com.example.ProvinceDistrict.Entity.District;
import com.example.ProvinceDistrict.Entity.Province;
import com.example.ProvinceDistrict.Repository.DistrictRepository;
import com.example.ProvinceDistrict.Repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    DistrictRepository districtRepository;

    public List<Province> getAllProvinces(){
        List<Province> list = new ArrayList<>();
        provinceRepository.findAll().forEach(province -> list.add(province));
        return list;
    }

    public Province findByName(String name){
        return provinceRepository.findProvinceByName(name);
    }

    @Transactional
    public void removeDistrict(String name, String districtName){
        Province p = findByName(name);
        if(p==null) System.out.println("province is null: " + name);
        p.removeDistrict(districtName);
        districtRepository.deleteByName(districtName);
    }

    @Transactional
    public void updateProvince(String name, Province p){
        int id = provinceRepository.findProvinceByName(name).getId();
        provinceRepository.findById(id).map(province -> {
            List<District> toRemove = new ArrayList<>(province.getDistricts());
            for(District d : toRemove){
                removeDistrict(province.getName(), d.getName());
            }
            for(District d : p.getDistricts()){
                d.setProvince(province);
                districtRepository.save(d);
            }

            province.getDistricts().clear();
            province.getDistricts().addAll(p.getDistricts());
            province.setName(p.getName());
            return provinceRepository.save(province);
        });
    }

    public void saveProvince(Province province) throws AlreadyExistException {
        List<Province> list = getAllProvinces();
        for(Province p : list){
            if(p.getName().equals(province.getName())){
                throw new AlreadyExistException(p.getName());
            }
        }
        List<District> pd = province.getDistricts();
        Iterator it = pd.listIterator();
        while(it.hasNext()){
            District d = (District) it.next();
            d.setProvince(province);
        }
        provinceRepository.save(province);
    }

    @Transactional
    public void deleteProvinceByName(String name) {
        provinceRepository.deleteByName(name);
    }
}
