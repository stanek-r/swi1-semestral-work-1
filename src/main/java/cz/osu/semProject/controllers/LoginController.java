package cz.osu.semProject.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField tfUsername;
    @FXML private TextField tfPassword;
    @FXML private ImageView imgViewLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


//        String str = getClass().getClassLoader().getResource("../images/login.png").toString();
//        System.out.println(str);
//        imgViewLogin.setImage(new Image());
    }

    @FXML
    private void onLoginClick(ActionEvent event){
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Špatné jméno/heslo");
//
//        alert.setHeaderText(null);
//        alert.setContentText("ERROR");
//        alert.showAndWait();
    }

    @FXML
    private void onSettingsClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("sample.fxml"));
        Parent parent = fxmlLoader.load();
//        Controller controller = fxmlLoader.<Controller>getController();

        Scene scene = new Scene(parent, 200,200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void setStyleSheetHehe(ActionEvent event) throws URISyntaxException {
        Scene currentScene = tfUsername.getScene();
        ObservableList<String> styles = currentScene.getStylesheets();
        for (int i = 0; i < styles.size();i++) {
            if(styles.get(i).contains("themes/"))
                styles.clear();
        }

        currentScene.getStylesheets().add(changeTheme(getEventSenderName(event.getSource())));
        if(getEventSenderName(event.getSource()).equals("darkPink"))
            imgViewLogin.setImage(new Image(getClass().getClassLoader().getResource("images/loginBlack.png").toURI().toString()));
        else
            imgViewLogin.setImage(new Image(getClass().getClassLoader().getResource("images/loginWhite.png").toURI().toString()));

        System.out.println(getEventSenderName(event.getSource()));
    }

    private String getEventSenderName(Object sender){
        String ret;
        ret = sender.toString().substring(sender.toString().lastIndexOf("]")+1);
        ret = ret.replace("'","");
        return ret;
    }
    private String changeTheme(String name){
        String ret;
        ret = getClass().getClassLoader().getResource("themes/"+name+".css").toExternalForm();
        return ret;
    }
}
