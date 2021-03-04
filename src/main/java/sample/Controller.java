package sample;

import cz.osu.semProject.MysqlConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.IOException;
import java.lang.reflect.Array;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Controller {
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

    @FXML
    private void testAction(){
//        System.out.println("Funguje to kámo");

    }
    @FXML
    private void submitClick(){
        formToString();
    }

    private String formToString(){
        StringBuilder sb = new StringBuilder("Zákazník: {");
        sb.append(tfName.getText());
        sb.append(" ");
        sb.append(tfSurname.getText());
        sb.append(" Datum a čas příchodu: ");
        sb.append(dpArriveDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        sb.append(" ");
        sb.append(cbArriveTime.getValue());
        sb.append(" ID/RČ: ");
        sb.append(personId.getText());
        sb.append(" SPZ: ");
        sb.append(tfSPZ.getText());
        sb.append(" Popis závady: ");
        sb.append(taDescription.getText());
        sb.append("}");
        return sb.toString();
    }


    @FXML
    private void radioButtonChanged(){
        if(rbNationality.isSelected())
            personId.setPromptText("Rodné číslo");
        else
            personId.setPromptText("Identifikační číslo");
    }

//    private void sendForm(){
//        StringBuilder sb = new StringBuilder("INSERT INTO Tabulka VALUES (");
//        sb.append("RC/" + personId.getText() + ", ");
//
//        MysqlConnection.getStatement().executeUpdate(sb.toString());
//
//    }
    @FXML
    public void changeWindow(ActionEvent event) throws IOException {
        Parent second = FXMLLoader.load(getClass().getClassLoader().getResource("second.fxml"));
        Scene hehe = new Scene(second);

        Stage w = (Stage)((Node)event.getSource()).getScene().getWindow();
        w.setScene(hehe);
        w.show();
    }
}
