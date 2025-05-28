package com.example.pricesscraper.utils;

public class AbrasionUtils {
    public static String GetAbrasion(String str){
        String result = "";
        if(str.contains("(崭新出厂)")){
            result = "崭新出厂";
        }
        if(str.contains("(略有磨损)")){
            result = "略有磨损";
        }
        if(str.contains("(久经沙场)")){
            result = "久经沙场";
        }
        if(str.contains("(战痕累累)")){
            result = "战痕累累";
        }
        if(str.contains("(破损不堪)")){
            result = "破损不堪";
        }
        return result;




    }
}
