package cz.osu.controllers;



import com.google.gson.Gson;
import cz.osu.semProject.*;
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

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
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
    @FXML public TableColumn<Reservation, String> idEmail;
    @FXML public TableColumn<Reservation, String> idPhoneNumber;
    @FXML public TableColumn<Reservation, String> idSPZ;
    @FXML public TableColumn<Reservation, String> idDesc;

    private CheckBox checkBox;


    private ObservableList<Reservation> reservationModels = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tvVypis.setPlaceholder(new Label("V tabulce nejsou žádná data"));
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
        idEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        idPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        idSPZ.setCellValueFactory(new PropertyValueFactory<>("SPZ"));
        idDesc.setCellValueFactory(new PropertyValueFactory<>("Description"));

        parseJsonFromBackend();
        tvVypis.setItems(reservationModels);
    }

    private void checkAll(boolean isChecked){
        for (Reservation reservationModel : reservationModels) {
            reservationModel.getCheckBox().setSelected(isChecked);
        }
    }

    public void deleteFromTable(){
        HashSet<Reservation> toRemove = new HashSet<>();
        for (int i = 0; i < reservationModels.size(); i++) {
            if(reservationModels.get(i).getCheckBox().isSelected()){
                try{
                    BackendConnector.sendingDeleteRequest(reservationModels.get(i).getRcIC());
                    toRemove.add(reservationModels.get(i));
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        reservationModels.removeAll(toRemove);
    }

    public void reloadTable(){
        reservationModels.clear();
        parseJsonFromBackend();
        GUIUtils.autoFitTable(tvVypis);
    }

    private void parseJsonFromBackend() {
        try{
            Gson gson = new Gson();
            JsonReservation[] reservations = gson.fromJson(BackendConnector.sendingGetRequest(), JsonReservation[].class);

            for (JsonReservation wc : reservations) {
                reservationModels.add(new Reservation(new CheckBox(),wc.getRC_IC(),wc.getName(),wc.getSurname(), LocalDate.parse(wc.getDate()), LocalTime.parse(wc.getTime()),wc.getEmail(),wc.getPhoneNumber(),wc.getSPZ(),wc.getDescription()));
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Chyba");
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
