package ua.rozborsky.tollRoadClient.classes;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

/**
 * Created by roman on 21.01.2017.
 */
@Service("socketManager")
public class SocketManager {

    private static Socket socket;
    private String hostName = "localhost";
    private int portNumber = 4444;

    public boolean connect() {
        try {
            socket = new Socket(hostName, portNumber);

            return true;
        } catch (IOException e) {
            e.printStackTrace();//todo log4j

            return false;
        }
    }

    public boolean checkId(int id) throws IOException {
        sendId(id);
        //throw new IOException();
        return canRide();
    }

    private void sendId(int id) throws IOException {
        OutputStream out = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        writer.write(id + "\n");
        writer.flush();
    }

    private boolean canRide() throws IOException {
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();

        return Boolean.valueOf(line);
    }

    public void setConnectingParameters(String hst, int prt) {
        this.hostName = hst;
        this.portNumber = prt;
    }
}
