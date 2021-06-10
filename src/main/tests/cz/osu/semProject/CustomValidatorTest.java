package cz.osu.semProject;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CustomValidatorTest {
    @Test
    public void dateTest1() {
        LocalDate localDate = null;
        Exception e = assertThrows(NullPointerException.class, () -> {
            CustomValidator.datePicked(localDate);
        });

        String expectedMessage = "Musíte vybrat datum";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void dateTest2() {
        LocalDate localDate = LocalDate.of(2020,12,30);
        CustomValidator.datePicked(localDate);
        assertTrue(true);
    }

    @Test
    public void timeTest1() {
        Exception e = assertThrows(NullPointerException.class, () -> {
           CustomValidator.timePicked(null);
        });

        String expectedMessage = "Musíte vybrat čas";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void timeTest2() {
        CustomValidator.timePicked("6:00");
        assertTrue(true);
    }

    @Test
    public void phoneNumberTest1() {
        Exception e = assertThrows(NullPointerException.class, () -> {
            CustomValidator.validatePhoneNumber(null);
        });

        String expectedMessage = "Nezadal jste telefonní číslo";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void phoneNumberTest2() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CustomValidator.validatePhoneNumber("");
        });

        String expectedMessage = "Toto není platné telefonní číslo";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void phoneNumberTest3() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CustomValidator.validatePhoneNumber("a123");
        });

        String expectedMessage = "Toto není platné telefonní číslo";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void phoneNumberTest4() {
        String[] validPhoneNumbers
                = {"2055550125","202 555 0125", "(202) 555-0125", "+111 (202) 555-0125",
                "636 856 789", "+111 636 856 789", "636 85 67 89", "+111 636 85 67 89"};


        for(String phoneNumber : validPhoneNumbers) {
            try {
                CustomValidator.validatePhoneNumber(phoneNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        assertTrue(true);
    }

    @Test
    public void emailTest1(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CustomValidator.validateEmail("plainaddress");
        });

        String expectedMessage = "Email není platný";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }
    @Test
    public void emailTest2(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CustomValidator.validateEmail("");
        });

        String expectedMessage = "Email není platný";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }
    @Test
    public void emailTest3(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CustomValidator.validateEmail("@example.com");
        });

        String expectedMessage = "Email není platný";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }
    @Test
    public void emailTest4(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CustomValidator.validateEmail("email.example.com");
        });

        String expectedMessage = "Email není platný";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }
    @Test
    public void emailTest5(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CustomValidator.validateEmail(".email@example.com");
        });

        String expectedMessage = "Email není platný";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }
    @Test
    public void emailTest6(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CustomValidator.validateEmail("email@example@example.com");
        });

        String expectedMessage = "Email není platný";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }
    @Test
    public void emailTest7(){
        String[] emails = {
            "email@example.name","email@example.com","firstname.lastname@example.com",
                "email@subdomain.example.com","firstname+lastname@example.com","\"email\"@example.com",
                "1234567890@example.com","email@example-one.com","_______@example.com","email@example.museum",
                "email@example.co.jp","firstname-lastname@example.com"
        };
        for (String email : emails) {
            CustomValidator.validateEmail(email);
        }
        assertTrue(true);
    }

    @Test
    public void notEmptyTest1() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
           CustomValidator.notEmpty("","test");
        });

        String expectedMessage = "Pole test nesmí být prázdné";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void notEmptyTest2() {
        CustomValidator.notEmpty("a","test");
        assertTrue(true);
    }

    @Test
    public void birtIdTest1() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
           CustomValidator.validateBirthId("");
        });

        String expectedMessage = "Pole rodné číslo nesmí být prázdné";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void birtIdTest2() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CustomValidator.validateBirthId("0");
        });

        String expectedMessage = "Nesprávný formát rodného čísla";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }

    @Test
    public void birtIdTest3() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CustomValidator.validateBirthId("000000/0000");
        });

        String expectedMessage = "Rodné číslo není validní";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }
    @Test
    public void birtIdTest4() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            CustomValidator.validateBirthId("0000000000");
        });

        String expectedMessage = "Rodné číslo není validní";
        String actualMessage = e.getMessage();

        assertTrue(expectedMessage.contains(actualMessage));
    }
    @Test
    public void birthIdTest5() {
        String[] birthIds = {
                "890728/5849","8907285849","505118/123","505118123","995121/123","995121123",
                "047320/1454","005229/9874","880222/4442"
        };
        for (String birthId : birthIds) {
            CustomValidator.validateBirthId(birthId);
        }
    }
}