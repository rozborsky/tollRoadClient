package ua.rozborsky.tollRoadClient.classes;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

/**
 * Created by roman on 21.01.2017.
 */
@Service("socketManager")
public class SocketManager {

    private Socket socket;
    private String hostName = "localhost";
    private int portNumber = 4444;

    public boolean isConnect() {
        try {
            socket = new Socket(hostName, portNumber);

            return true;
        } catch (IOException e) {
            e.printStackTrace();//todo-------------------------

            return false;
        }
    }

    public void checkId(int id) throws IOException {
        OutputStream out = socket.getOutputStream();;
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        try {
            writer.write(id + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();//todo---------------------------------------------
        }
    }

    public void setConnectingParameters(String hst, int prt) {
        this.hostName = hst;
        this.portNumber = prt;
    }
}
