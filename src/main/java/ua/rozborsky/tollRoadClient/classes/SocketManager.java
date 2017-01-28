package ua.rozborsky.tollRoadClient.classes;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

/**
 * Created by roman on 21.01.2017.
 */
@Service("socketManager")
public class SocketManager {

    public boolean checkId(int id) throws IOException {
        String host = Properties.host();
        int port = Integer.valueOf(Properties.port());
        
        Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(id);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String isActive = in.readLine();

        return Boolean.valueOf(isActive);
    }
}
