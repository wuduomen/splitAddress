package com.example.demo.service;

import java.util.Map;

public interface AddressService {
    /**
     * 地址拆分解析
     */
    Map<String, String> parseAddress(String addressAndContacts);
}
