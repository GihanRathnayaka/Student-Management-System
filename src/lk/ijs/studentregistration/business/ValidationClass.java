package lk.ijs.studentregistration.business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationClass {

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidPhoneNumber(String number){

        Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
        Matcher matcher = pattern.matcher(number);

        if (matcher.matches()) {
            return true;
        }
        else
        {
           return false;
        }
    }
    public static boolean validateNIC(String nic){
        if(!(nic.trim().matches("^[0-9]{9}[vV]$")))
        {
            return false;
        }
        return true;
    }
}
