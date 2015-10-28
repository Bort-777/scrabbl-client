package by.andrew.scrabl.control;

import ch.makery.adress.model.ServerComands;
import ch.makery.adress.model.MyPackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.InterruptedByTimeoutException;
import by.andrew.scrabl.control.GameControl;
import ch.makery.adress.model.Field;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import javafx.scene.text.Text;

public class Server {

    private ServerSocket serverSocket;
    private DataProv dataProvider;
    private Text text;

    public Server() {

        try {
            serverSocket = new ServerSocket(0);

        } catch (IOException e) {

        }
    }

    public void setTextArea(Text jTextArea) {
        this.text = jTextArea;
        text.setText("IP: " + serverSocket.getLocalSocketAddress());

        setClipboard(Integer.toString(serverSocket.getLocalPort()));
    }

    public static void setClipboard(String str) {
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

    }

    public void waite() {
        try {
            final Socket socket = serverSocket.accept();
            text.setText("Connected" + "\n" + text.getText());
            // Connected

            Thread thread = new ClientThread(socket);
            thread.start();

            waite();
        } catch (IOException e) {
        }
    }

    public void stop() {
        try {
            serverSocket.close();

            // System.exit(0);
        } catch (IOException e) {
            // "can't to stop"
        }
    }

    private class ClientThread extends Thread {

        private ObjectInputStream reader;
        private ObjectOutputStream writer;
        private Socket socket;

        public ClientThread(Socket socket) {
            try {
                this.socket = socket;
                this.socket.setSoTimeout(100000);
            } catch (SocketException e1) {

            }
            try {
                writer = new ObjectOutputStream(socket.getOutputStream());
                reader = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {

            }
        }

        public void run() {
            waitCommand();
        }

        public void waitCommand() {
            //System.out.println("waiting command");

            if (socket.isClosed()) {
                return;
            }
            try {
                try {
                    int command = -1;
                    try {
                        command = (int) reader.readObject();
                    } catch (java.lang.ClassCastException e) {
                       // System.out.print(reader.readObject());
                        System.out.println(e);
                    }
                    switch (command) {
                        case ServerComands.NEW_USER:
                            System.out.println("Начало1");

                            dataProvider.newUser(getName());
                            System.out.println("Конец1");

                            break;
                        case ServerComands.GET_DATA:
                            System.out.println("Начал2");

                            Field tmp1 = (Field) dataProvider.getField(getName());
                            String tmp2 = dataProvider.getAsk(getName());

                            

                            writer.writeObject(tmp1.toText());

                            text.setText(text.getText() + "1");

                            writer.writeObject(tmp2);

                            text.setText(text.getText() + "2");
                            System.out.println("Конец2");

                            break;
                        case ServerComands.ANSWER:
                            System.out.println("Начало3");

                           //MyPackage answer = (MyPackage) reader.readObject();
                           
                           dataProvider.setAnswer(getName(), (String) reader.readObject(), (ArrayList) reader.readObject());
                            //text.setText("\"" + answer + "\"" + "\n" + text.getText());
                            System.out.println("Конец3");

                            break;

                        default:
                            text.setText("Default" + "\n" + text.getText());
                            break;

                    }
                } catch (SocketException e) {
                    text.setText("Disconnect" + "\n" + text.getText().substring(0, 50));
                    // ("disconnect");
                    return;
                }
            } catch (InterruptedByTimeoutException e2) {
                waitCommand();
            } catch (ClassNotFoundException | IOException e) {
                // log.error("bad data");
            }
            if (!socket.isClosed()) {
                waitCommand();
            }
        }
    }

    public void setGameControl(GameControl gameControl) {
        dataProvider = new ServDProv(gameControl);

    }
}
