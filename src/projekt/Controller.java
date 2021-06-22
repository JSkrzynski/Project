package projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

//CEZAR`S CIPHER
    private String cipher(int value,String text){
//CONVERT STRING INTO CHAR ARRAY
        char[] chars = text.toCharArray();
//MODIFYING CHARS
        for(int i=0;i<chars.length;i++){
            if(chars[i]!=' ') {
                chars[i] += value;
            }
        }
//BUILD A STRING OUT OF IT
        return new String(chars);
    }

    @FXML
    private BorderPane myPane;
    @FXML
    private Button ButtonSave;
    @FXML
    private Button ButtonLoad;
    @FXML
    private Button ButtonCipher;
    @FXML
    private Button ButtonClear;
    @FXML
    private TextArea textInput;
    @FXML
    private TextField operationTextField;

    FileChooser fc = new FileChooser();
    Alert a = new Alert(Alert.AlertType.NONE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleButtonAction(ActionEvent actionEvent) throws IOException {

//CHECK WHAT BUTTON HAS BEEN CLICKED
//SAVE
        if (actionEvent.getSource() == ButtonSave) {
    //CHECK IF USER HAS GIVEN NAME
    //IF NOT
            if(operationTextField.getText().equals("")){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Put in a filename first");
                a.show();
            }
    //IF GIVEN
            else{
                try {
                    File newfile = new File(operationTextField.getText() + ".txt");
    //IF FILE ALREADY EXISTS
                    if (newfile.exists()) {
                        a.setAlertType(Alert.AlertType.CONFIRMATION);
                        a.setContentText("File already exists, do you wish to continue?");
                        a.showAndWait();
//I KNOW AND WISH TO CONTINUE
                        if(a.getResult()==ButtonType.OK){
                            FileWriter myWriter = new FileWriter(operationTextField.getText() + ".txt");
                            myWriter.write(textInput.getText());
                            myWriter.close();
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Saved succesfully!");
                            a.show();
                        }
//I WANT TO CANCEL
                        else{
                            a.setAlertType(Alert.AlertType.INFORMATION);
                            a.setContentText("Canceled Saving");
                            a.show();
                        }
                    }
    //IF FILE NOT EXISTS
                    else {
                        FileWriter myWriter = new FileWriter(operationTextField.getText() + ".txt");
                        myWriter.write(textInput.getText());
                        myWriter.close();
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Saved succesfully!");
                        a.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
//LOAD
        if(actionEvent.getSource() == ButtonLoad){
            Window stage = myPane.getScene().getWindow();
            fc.setTitle("Load a file");
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text file","*.txt"));
            try{
    //LOAD THE FILE
                File file = fc.showOpenDialog(stage);
    //MAKE PATH A STRING AND DELETE THE EXTENSION TO PUT IT IN THE OPERATION TEXT FIELD
                String noExtensionPath = file.getAbsolutePath();
                for(int i=0;i<4;i++){
                    noExtensionPath = noExtensionPath.substring(0,noExtensionPath.length()-1);
                }
                operationTextField.setText(noExtensionPath);
    //PUT FILE CONTENTS INTO INPUT FIELD
                Scanner reader = new Scanner(file);
                String text;
                while(reader.hasNextLine()){
                    textInput.appendText(reader.nextLine());
                }
                reader.close();

            } catch (Exception e) {
                e.printStackTrace();
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("An Error occurred while loading the file.");
                a.show();
            }
        }
//CIPHER
        if(actionEvent.getSource()==ButtonCipher){
    //CHECK IF USER HAS PUT ANYTHING
            if(operationTextField.getText().equals("")){
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Put in a number!");
                a.show();
            }
    //IF HAS PUT SOMETHING
            else{
                try{
                    int value = Integer.parseInt(operationTextField.getText());
                    textInput.setText(cipher(value,textInput.getText()));

                } catch (Exception e) {
                    a.setAlertType(Alert.AlertType.ERROR);
                    a.setContentText("Put in a number, not a string!");
                    a.show();
                    e.printStackTrace();
                }
            }
        }
//CLEAR THE OPERATION SPACE
        if(actionEvent.getSource()==ButtonClear){
            operationTextField.setText("");
        }
    }
}
