package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientFormController {
    public TextArea ClientTextArea;
    public TextField ClienttxtField;
    public Button btnSendClient;
    public Label lblClient;

    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private  String message="";
    private  String reply = "";

    @FXML
    void initialize() {

        Thread initialThrade = new Thread(() -> {
            try {
                socket = new Socket("localhost",3030);
                ClientTextArea.appendText("sever started !");
                ClientTextArea.appendText("client accepted !");

                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("finish")) {
                    message = inputStream.readUTF();
                    ClientTextArea.appendText("client : " + "\n\t" + message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        initialThrade.start();
    }

        public void btnSendOnAction (ActionEvent actionEvent) throws IOException {
            outputStream.writeUTF(ClienttxtField.getText().trim());
            outputStream.flush();
            }

        }
