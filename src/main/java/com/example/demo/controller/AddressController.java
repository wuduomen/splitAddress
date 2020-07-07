package com.example.demo.controller;

import com.example.demo.service.AddressService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("address")
public class AddressController {

    @Resource
    private AddressService addressService;

    @GetMapping("parseAddress")
    public Map<String, String> parseAddress(@RequestParam("addressAndContacts") String addressAndContacts) {
        return this.addressService.parseAddress(addressAndContacts);
    }
}
