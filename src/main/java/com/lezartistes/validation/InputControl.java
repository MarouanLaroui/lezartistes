package com.lezartistes.validation;

public class InputControl {

    public static boolean isValidString(String s,int maxlenght){
        return (s.length() <= maxlenght);
    }

    public static boolean isValidEmail(String s){
        String regex = "^(.+)@(.+)$";
        return s.matches(regex);
    }

    public static boolean isValidPhoneNumber(String s){
        String regex = "^\\d{10}$";
        return s.matches(regex);
    }


    public static boolean isValidDouble(double data,double min ,double max){
        return min<data && data<max;
    }
    //TODO : finish
    public static boolean isValidPostalCode(int postal_code){
        if(postal_code>0){
            return true;
        }
        return false;
    }
}
