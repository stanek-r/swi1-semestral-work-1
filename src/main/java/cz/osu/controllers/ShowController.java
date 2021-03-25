package cz.osu.controllers;



import cz.osu.semProject.MysqlConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import cz.osu.semProject.Reservation;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ShowController implements Initializable {
    @FXML private AnchorPane anchorPane;
    @FXML private TableView<Reservation> tvVypis;
    @FXML public TableColumn<Reservation, Boolean> idCheckbox;
    @FXML public TableColumn<Reservation, String> idRC_IC;
    @FXML public TableColumn<Reservation, String> idName;
    @FXML public TableColumn<Reservation, String> idSurname;
    @FXML public TableColumn<Reservation, LocalDate> idDate;
    @FXML public TableColumn<Reservation, LocalTime> idTime;
    @FXML public TableColumn<Reservation, String> idSPZ;
    @FXML public TableColumn<Reservation, String> idDesc;

    private CheckBox checkBox;


    private ObservableList<Reservation> reservationModels = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkBox = new CheckBox();
        checkBox.setOnAction(event -> checkAll(checkBox.isSelected()));
        idCheckbox.setGraphic(checkBox);
        idCheckbox.setStyle("-fx-alignment: CENTER");
        idCheckbox.setCellValueFactory(new PropertyValueFactory<>("CheckBox"));
        idRC_IC.setCellValueFactory(new PropertyValueFactory<>("RcIC"));
        idName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        idSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        idDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        idTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        idSPZ.setCellValueFactory(new PropertyValueFactory<>("SPZ"));
        idDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));

        try{
            ResultSet rs = MysqlConnection.getStatement().executeQuery("SELECT * FROM aaaaa");
            while (rs.next()){
                reservationModels.add(new Reservation(new CheckBox(),rs.getString("RC_IC"),rs.getString("name"),rs.getString("surname"),
                        rs.getDate("date").toLocalDate(),rs.getTime("time").toLocalTime(),rs.getString("SPZ"),rs.getString("description")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        tvVypis.setItems(reservationModels);


    }

    private void checkAll(boolean isChecked){
        for (Reservation reservationModel : reservationModels) {
            reservationModel.getCheckBox().setSelected(isChecked);
        }
    }

    public void deleteFromTable(){

        for (int i = 0; i < reservationModels.size(); i++) {
            if(reservationModels.get(i).getCheckBox().isSelected()){
                try{
                    MysqlConnection.getStatement().executeUpdate("DELETE FROM aaaaa where RC_IC='" + reservationModels.get(i).getRcIC() +"'");
                } catch (SQLException e){
                    e.printStackTrace();
                }
                reservationModels.remove(i);
            }
        }

    }

    public void reloadTable(){
        reservationModels.clear();
        try{
            ResultSet rs = MysqlConnection.getStatement().executeQuery("SELECT * FROM aaaaa");
            while (rs.next()){
                reservationModels.add(new Reservation(new CheckBox(),rs.getString("RC_IC"),rs.getString("name"),rs.getString("surname"),
                        rs.getDate("date").toLocalDate(),rs.getTime("time").toLocalTime(),rs.getString("SPZ"),rs.getString("description")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void goBack(ActionEvent event) throws IOException {
        Parent main = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlFiles/addNew.fxml"));
        Scene mainScene = new Scene(main,1280,720);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(mainScene);
        stage.show();
    }
}
