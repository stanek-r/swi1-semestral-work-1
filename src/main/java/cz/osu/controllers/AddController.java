package cz.osu.controllers;

import cz.osu.semProject.MysqlConnection;
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
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class AddController {
    public ToggleGroup nationality;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private ComboBox cbArriveTime;
    @FXML
    private DatePicker dpArriveDate;
    @FXML
    private TextArea taDescription;
    @FXML
    private TextField tfSPZ;
    @FXML
    private Button btnSubmit;
    @FXML
    private RadioButton rbNationality;
    @FXML
    private TextField personId;
    private String nat;

    @FXML
    private void testAction(){
        String tmp = dpArriveDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ObservableList<Object> ol = cbArriveTime.getItems();
        ObservableList<LocalTime> defaultListOfTimes = FXCollections.observableArrayList(LocalTime.of(7, 0), LocalTime.of(8, 0), LocalTime.of(9, 0), LocalTime.of(10, 0), LocalTime.of(11, 0), LocalTime.of(12, 0), LocalTime.of(13, 0), LocalTime.of(14, 0), LocalTime.of(15, 0), LocalTime.of(16, 0));

        try{
            ResultSet rs = MysqlConnection.getStatement().executeQuery("SELECT * FROM `aaaaa` WHERE `date` = '" + tmp + "'");
            while(rs.next()){
                defaultListOfTimes.remove(rs.getTime("time").toLocalTime());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        ol.clear();
        ol.addAll(defaultListOfTimes);
    }

    @FXML
    private void radioButtonChanged(){
        if(rbNationality.isSelected())
            personId.setPromptText("Rodné číslo");
        else
            personId.setPromptText("Identifikační číslo");
    }

    @FXML
    private void sendForm(ActionEvent event){
        nat = rbNationality.isSelected() ? "RC_" : "IC_";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H:mm");
        StringBuilder sb = new StringBuilder("INSERT INTO aaaaa VALUES (");
        sb.append("'" + nat + personId.getText() + "', ");
        sb.append("'" + tfName.getText() + "', ");
        sb.append("'" + tfSurname.getText() + "', ");
        sb.append("'" + dpArriveDate.getValue() + "', ");
        sb.append("'" + LocalTime.parse(cbArriveTime.getValue().toString(),dtf) + "', ");
        sb.append("'" + tfSPZ.getText() + "', ");
        sb.append("'" + taDescription.getText() + "')");

        try{
            MysqlConnection.getStatement().executeUpdate(sb.toString());
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(sb.toString());

        try{
            Thread.sleep(1000);
            changeWindow(event);
        }catch (Exception e){
            e.printStackTrace();
        }
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
