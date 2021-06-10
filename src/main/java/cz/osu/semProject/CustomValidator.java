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
        notEmpty(name,"jméno nebo příjmení");
        if(!name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$")){
            throw new IllegalArgumentException("Toto není validní jméno");
        }
    }

    public static void datePicked(LocalDate date){
        if(date == null){
            throw new NullPointerException("Musíte vybrat datum");
        }
    }

    public static void timePicked(Object time){
        if(time == null || time.equals("")){
            throw new NullPointerException("Musíte vybrat čas");
        }

    }

    public static void validatePhoneNumber(String phoneNumber) throws Exception {
        if(phoneNumber == null){
            throw new NullPointerException("Nezadal jste telefonní číslo");
        }
        Pattern pattern = Pattern.compile(phoneNumberPattern);
        Matcher matcher = pattern.matcher(phoneNumber);
        if(!matcher.matches())
            throw new IllegalArgumentException("Toto není platné telefonní číslo");
    }

    public static void validateEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();

        if(!emailValidator.isValid(email))
            throw new IllegalArgumentException("Email není platný");
    }

    public static void notEmpty(String value, String input) {
        if(value.equals("")){
            throw new IllegalArgumentException("Pole " + input + " nesmí být prázdné");
        }
    }
    
    public static void validateBirthId(String birthId) {
        notEmpty(birthId,"rodné číslo");
        String birthIdPattern = "^\\d{6}[/]?\\d{3,4}$";
        Pattern pattern = Pattern.compile(birthIdPattern);
        Matcher matcher = pattern.matcher(birthId);
        if(!matcher.matches())
            throw new IllegalArgumentException("Nesprávný formát rodného čísla");
        if(!checkBirthDate(birthId))
            throw new IllegalArgumentException("Rodné číslo není validní");
    }

    private static boolean checkBirthDate(String birthDate) {
        birthDate = birthDate.replace("/","");
        int year = Integer.parseInt(birthDate.substring(0,2));
        int month = Integer.parseInt(birthDate.substring(2,4));
        int day = Integer.parseInt(birthDate.substring(4,6));
        Integer c = null;
        if(birthDate.length() == 10)
            c = Integer.parseInt(birthDate.substring(9,10));

        if(c == null){
            year += year < 54 ? 1900 : 1800;
        } else {
            int mod = Integer.parseInt(birthDate.substring(0,9)) % 11;
            if(mod == 10)
                mod = 0;
            if(mod != c)
                return false;
            year += year < 54 ? 2000 : 1900;
        }
        if(month > 70 && year > 2003) {
            month -= 70;
        }
        else if(month > 50) {
            month -= 50;
        } else if(month > 20 && year > 2003) {
            month -= 20;
        }
        try{
            LocalDate localDate = LocalDate.of(year,month,day);
            System.out.println(localDate.toString());
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
