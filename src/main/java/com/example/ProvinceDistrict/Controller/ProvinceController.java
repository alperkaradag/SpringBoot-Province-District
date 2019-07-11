package com.example.ProvinceDistrict.Controller;

import com.example.ProvinceDistrict.AlreadyExistException;
import com.example.ProvinceDistrict.Entity.Province;
import com.example.ProvinceDistrict.Service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;

@RestController
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;


    @ResponseBody
    @GetMapping("/provinces")
    @Consumes("application/json")
    @Produces("application/json")
    private List<Province> getProvinces(){
        return provinceService.getAllProvinces();
    }

    @GetMapping("/provinces/{name}")
    private Province getProvince(@PathVariable("name") String name){
        return provinceService.findByName(name);
    }

    @PutMapping("/provinces/{name}")
    private void updateProvince(@PathVariable("name") String name, @RequestBody Province p){
        provinceService.updateProvince(name, p);
    }

    @PostMapping("/provinces")
    private ResponseEntity<?> saveProvince(@RequestBody Province province){
        try {
            provinceService.saveProvince(province);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AlreadyExistException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/provinces/{name}")
    private void deleteProvince(@PathVariable("name") String name){
        provinceService.deleteProvinceByName(name);
    }

    @DeleteMapping("/provinces/{name}/{districtName}")
    private void deleteDistrict(@PathVariable("name") String name, @RequestParam String districtName){
        provinceService.removeDistrict(name, districtName);
    }
}
