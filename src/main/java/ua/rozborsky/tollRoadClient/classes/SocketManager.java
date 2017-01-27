package ua.rozborsky.tollRoadClient.classes;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

/**
 * Created by roman on 21.01.2017.
 */
@Service("socketManager")
public class SocketManager {
    private String hostName = "localhost";
    private int portNumber = 4444;


    public boolean checkId(int id) throws IOException {
        Socket socket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(id);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String isActive = in.readLine();

        return Boolean.valueOf(isActive);
    }


    public void setConnectingParameters(String hst, int prt) {
        this.hostName = hst;
        this.portNumber = prt;
    }
}
