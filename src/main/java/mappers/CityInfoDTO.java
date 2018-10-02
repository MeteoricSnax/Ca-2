/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import entity.CityInfo;

/**
 *
 * @author Mads
 */
public class CityInfoDTO {
    private String city;
    private int zipCode;

    public CityInfoDTO(CityInfo ci) {
        this.city = ci.getCity();
        this.zipCode = ci.getZipCode();
    }
    
}
