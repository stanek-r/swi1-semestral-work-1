package cz.osu.semProject;

import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CustomValidator {

    private static String phoneNumberPattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

    public static void validateName(String name) {
        notEmpty(name);
        if(!name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$")){
            throw new IllegalArgumentException("Toto není validní jméno");
        }
    }

    public static void datePicked(LocalDate date){
        if(date == null){
            throw new NullPointerException("Musíte vybrat datum");
        }
    }

    public static void timePicked(String time){
        if(time == null){
            throw new NullPointerException("Musíte vybrat čas");
        }
    }

    public static void validatePhoneNumber(String phoneNumber) throws Exception {
        Pattern pattern = Pattern.compile(phoneNumberPattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        if(!matcher.matches())
            throw new Exception("Toto není platné telefonní číslo");
    }

    public static void validateEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();

        if(!emailValidator.isValid(email))
            throw new IllegalArgumentException("Email není platný");
    }

    public static void notEmpty(String value) {
        if(value.equals("")){
            throw new IllegalArgumentException("Pole nesmí být prázdné");
        }
    }
}
