package cz.osu.semProject;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BackendConnector {
    public static String sendingGetRequest() throws Exception {

        String urlString = "http://localhost:3000/reservations";

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("Sending get request : "+ url);
        System.out.println("Response code : "+ responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        return response.toString();
    }

    public static String sendingGetRequest(String id) throws Exception {

        String urlString = "http://localhost:3000/reservations/"+id;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        System.out.println("Sending get request : "+ url);
        System.out.println("Response code : "+ responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        return response.toString();
    }

    public static String getTimes(String date) throws IOException {
        String urlString = "http://localhost:3000/reservations/get-time/" + date;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");


        int responseCode = con.getResponseCode();
        System.out.println("Sending get request : "+ url);
        System.out.println("Response code : "+ responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        return response.toString();
    }

    public static void sendingDeleteRequest(String RC_IC) throws Exception {
        String urlString = "http://localhost:3000/reservations/" + RC_IC;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("DELETE");

        int responseCode = con.getResponseCode();
        System.out.println("Sending delete request : " + url);
        System.out.println("Response code : "+ responseCode);
    }

    public static String sendingPostRequest(String postJsonData) throws Exception {

        String url = "http://localhost:3000/reservations";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type","application/json");

        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postJsonData);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("nSending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + postJsonData);
        System.out.println("Response Code : " + responseCode);

        if(responseCode == 406){
//            Alert a = new Alert(Alert.AlertType.ERROR,"Na tento čas je již zarezervovaný jiný termín", ButtonType.OK);
//            a.setTitle("Chyba");
//            a.showAndWait();
            throw new RuntimeException("Na tento čas je již zarezervovaný jiný termín");
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        System.out.println(response.toString());
        return response.toString();
    }
}

