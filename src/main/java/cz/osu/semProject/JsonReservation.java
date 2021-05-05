package cz.osu.semProject;

public class JsonReservation {
    private String RC_IC;
    private String name;
    private String surname;
    private String date;
    private String time;
    private String email;
    private String phoneNumber;
    private String SPZ;
    private String description;

    public JsonReservation(String RC_IC, String name, String surname, String date, String time, String email, String phoneNumber, String SPZ, String description) {
        this.RC_IC = RC_IC;
        this.name = name;
        this.surname = surname;
        this.date = date;
        this.time = time;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.SPZ = SPZ;
        this.description = description;
    }

    public String getRC_IC() {
        return RC_IC;
    }

    public void setRC_IC(String RC_IC) {
        this.RC_IC = RC_IC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSPZ() {
        return SPZ;
    }

    public void setSPZ(String SPZ) {
        this.SPZ = SPZ;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
