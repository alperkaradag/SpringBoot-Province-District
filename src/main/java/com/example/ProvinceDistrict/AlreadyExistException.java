package com.example.ProvinceDistrict;

public class AlreadyExistException extends Exception {
    public AlreadyExistException(String element){
        super(element + " already exists!");
    }
}
