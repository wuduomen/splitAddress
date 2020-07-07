package com.example.demo.service.impl;

import com.example.demo.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AddressServiceImpl implements AddressService {

    private static final String patternPhone = "1[3|4|5|6|7|8|9][0-9]\\d{4,8}";
    private static final String patternName = "(^[\u4E00-\u9FA5]{2,5}[,|，|\\s|\\-|\b|\\d])|([\\d|,|，|\\-|\\s|\\|][\u4E00-\u9FA5]{2,5}[\\d|,|，|\\-|\\s|\\|])|([,|，|\\s|\\-|\b|\\d]+[\u4E00-\u9FA5]{2,5}$)";
    private static final String patternAddress = ",|，|\\-|\\s|\\|";
    private String paramsAddress;

    // 匹配并替换特定字符
    private String getMatchResult(String matchPattern) {
        String matchResult = "";
        Pattern pattern = Pattern.compile(matchPattern);
        Matcher matcher = pattern.matcher(this.paramsAddress);
        if (matcher.find()) {
            matchResult = matcher.group();
            this.paramsAddress = matcher.replaceAll(",");
        }
        return matchResult;
    }

    // 过滤特定字符
    private String filterSpecialCharacters(String str) {
        return str.replaceAll(patternAddress, "");
    }

    private String getUserPhone() {
        return getMatchResult(patternPhone);
    }

    private String getUserName() {
        return filterSpecialCharacters(getMatchResult(patternName));
    }

    // 得到用户姓名，手机号码及地址信息
    @Override
    public Map<String, String> parseAddress(String paramAddress) {
        this.paramsAddress = paramAddress;
        Map<String, String> userAndAddress = new HashMap<>();
        userAndAddress.put("userPhone", this.getUserPhone());
        userAndAddress.put("userName", this.getUserName());
        userAndAddress.put("address", filterSpecialCharacters(this.paramsAddress));
        return userAndAddress;
    }

    /*// 得到手机号码
    public String getUserPhone(String paramAddress) {
        this.paramsAddress = paramAddress;
        return getMatchResult(patternPhone);
    }

    // 得到用户姓名
    public String getUserName(String paramAddress) {
        this.paramsAddress = paramAddress;
        return filterSpecialCharacters(getMatchResult(patternName));
    }

    // 得到地址信息
    public String getAddress(String paramAddress) {
        this.paramsAddress = paramAddress;
        this.getUserPhone();
        this.getUserName();
        return filterSpecialCharacters(this.paramsAddress);
    }*/
}
