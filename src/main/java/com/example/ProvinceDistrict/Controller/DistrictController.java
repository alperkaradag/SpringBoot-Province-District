package com.example.ProvinceDistrict.Controller;

import com.example.ProvinceDistrict.Entity.District;
import com.example.ProvinceDistrict.Service.DistrictService;
import com.example.ProvinceDistrict.Service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;

@RestController
public class DistrictController {

    @Autowired
    DistrictService districtService;
    @Autowired
    ProvinceService provinceService;

    @ResponseBody
    @GetMapping("/provinces/{provinceName}/districts")
    @Consumes("application/json")
    @Produces("application/json")
    private List<District> getDistricts(@PathVariable("provinceName") String provinceName){
        return districtService.getAllDistricts(provinceName);
    }

//    @DeleteMapping("/provinces/{provinceName}/districts/{districtName}")
//    private void deleteDistrict(@PathVariable("provinceName") String provinceName, @PathVariable("districtName") String districtName){
//        districtService.deleteDistrict(provinceName, districtName);
//    }

    @PostMapping("/provinces/{provinceName}/districts")
    private void  saveDistrict(@PathVariable("provinceName") String provinceName, @RequestBody District district){
        districtService.saveDistrict(provinceName, district);
    }
}
