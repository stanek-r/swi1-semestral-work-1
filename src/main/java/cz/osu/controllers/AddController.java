package cz.osu.controllers;


import com.google.gson.Gson;
import cz.osu.semProject.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class AddController {
    public ToggleGroup nationality;
    private String nat;

    @FXML private TextField tfName;
    @FXML private TextField tfSurname;
    @FXML private ComboBox cbArriveTime;
    @FXML private DatePicker dpArriveDate;
    @FXML private TextArea taDescription;
    @FXML private TextField tfSPZ;
    @FXML private RadioButton rbNationality;
    @FXML private TextField personId;
    @FXML private TextField tfEmail;
    @FXML private TextField tfPhoneNumber;


    @FXML
    private void getTimes(){
        String tmp = dpArriveDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        cbArriveTime.setDisable(false);
        ObservableList<Object> ol = cbArriveTime.getItems();

        ObservableList<LocalTime> defaultListOfTimes = FXCollections.observableArrayList();
        for(String time : parseJsonFromBackend(tmp)){
            defaultListOfTimes.add(LocalTime.parse(time));
        }
        if(defaultListOfTimes.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR,"Na toto datum již nejsou žádné volné termíny", ButtonType.OK);
            a.setTitle("Chyba");
            a.showAndWait();
            cbArriveTime.setDisable(true);
        }
        dpArriveDate.requestFocus();
        ol.clear();
        ol.addAll(defaultListOfTimes);
    }

    private String[] parseJsonFromBackend(String date) {
        try{
            Gson gson = new Gson();
            String[] reservations = gson.fromJson(BackendConnector.getTimes(date), String[].class);

            return reservations;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("chyba");
        }
        return null;
    }

    @FXML
    private void radioButtonChanged(){
        if(rbNationality.isSelected())
            personId.setPromptText("Rodné číslo");
        else
            personId.setPromptText("Identifikační číslo");
    }

    @FXML
    private void sendForm(ActionEvent event) {

        try{
            validateForm();
            nat = rbNationality.isSelected() ? "RC_" : "IC_";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
            JsonReservation jsonReservation = new JsonReservation(
                    nat+personId.getText(),
                    tfName.getText(),
                    tfSurname.getText(),
                    dpArriveDate.getValue().toString(),
                    LocalTime.parse(cbArriveTime.getValue().toString(),dtf).toString(),
                    tfEmail.getText(),
                    tfPhoneNumber.getText(),
                    tfSPZ.getText(),
                    taDescription.getText()
            );
            BackendConnector.sendingPostRequest(new Gson().toJson(jsonReservation));
            Thread.sleep(1000);
            changeWindow(event);
        }catch (Exception e){
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            a.setTitle("Chyba");
            a.showAndWait();
        }
    }

    private void validateForm() throws Exception {
        CustomValidator.validateName(tfName.getText());
        CustomValidator.validateName(tfSurname.getText());
        CustomValidator.datePicked(dpArriveDate.getValue());
        CustomValidator.timePicked(cbArriveTime.getValue());
        CustomValidator.validateBirthId(personId.getText());
        System.out.println(personId.getText());
        CustomValidator.validatePhoneNumber(tfPhoneNumber.getText());
        CustomValidator.validateEmail(tfEmail.getText());
        CustomValidator.notEmpty(tfSPZ.getText(),"SPZ");
        CustomValidator.notEmpty(taDescription.getText(),"popis");
    }

    @FXML
    public void changeWindow(ActionEvent event) throws IOException {
        Parent second = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlFiles/show.fxml"));
        Scene hehe = new Scene(second);
        Stage w = (Stage)((Node)event.getSource()).getScene().getWindow();
        w.setScene(hehe);
        w.show();
    }
}
