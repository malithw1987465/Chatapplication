package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    public TextArea txtAreaSever;
    public TextField txtServer;
    public Button btnSend;

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private  String message="";
    private  String reply = "";

    @FXML
    void initialize(){

        Thread initialThrade = new Thread(() -> {
            try {
                serverSocket=new ServerSocket( 3030);
                txtAreaSever.appendText("sever started !");
                socket=serverSocket.accept();
                txtAreaSever.appendText("client accepted !");

                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());

                while (!message.equals("finish")){
                    message=inputStream.readUTF();
                    txtAreaSever.appendText("client : "+"\n\t"+message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        initialThrade.start();
    }

    public void bntSendOnAction(ActionEvent actionEvent) throws IOException {
        outputStream.writeUTF(txtServer.getText().trim());
        outputStream.flush();
        }

    }

