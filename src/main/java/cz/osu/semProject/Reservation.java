package cz.osu.semProject;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private CheckBox checkBox;
    private SimpleStringProperty rcIC;
    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private LocalDate date;
    private LocalTime time;
    private SimpleStringProperty SPZ;
    private SimpleStringProperty description;

    public Reservation(CheckBox checkBox, String rcIC, String name, String surname, LocalDate date, LocalTime time, String SPZ, String description) {
        this.checkBox = checkBox;
        this.rcIC = new SimpleStringProperty(rcIC);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.date = date;
        this.time = time;
        this.SPZ = new SimpleStringProperty(SPZ);
        this.description = new SimpleStringProperty(description);
    }

    private void Event(){
        System.out.println("CEEEEEEEEEEEEST");
    }
    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getRcIC() {
        return rcIC.get();
    }

    public SimpleStringProperty rcICProperty() {
        return rcIC;
    }

    public void setRcIC(String rcIC) {
        this.rcIC.set(rcIC);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getSPZ() {
        return SPZ.get();
    }

    public SimpleStringProperty SPZProperty() {
        return SPZ;
    }

    public void setSPZ(String SPZ) {
        this.SPZ.set(SPZ);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}