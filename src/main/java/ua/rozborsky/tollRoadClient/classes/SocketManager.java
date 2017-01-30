package ua.rozborsky.tollRoadClient.classes;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import ua.rozborsky.transmittedObjects.AnswerFromServer;
import ua.rozborsky.transmittedObjects.Client;
import ua.rozborsky.transmittedObjects.RequestFromClient;

import java.io.*;
import java.net.Socket;

/**
 * Created by roman on 21.01.2017.
 */
@Service("socketManager")
public class SocketManager {
    private boolean canRide;
    private String message;

    public void checkId(int id) throws IOException,  ClassNotFoundException{
        String host = Properties.host();
        int port = Integer.valueOf(Properties.port());
        
        Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationConfig.xml");
        RequestFromClient requestFromClient = (RequestFromClient) context.getBean("requestFromClient");
        requestFromClient.setValues(Client.valueOf(Properties.terminalMarker()), id);

        out.writeObject(requestFromClient);
        out.flush();

        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

        AnswerFromServer answerFromServer = (AnswerFromServer)inStream.readObject();
        this.canRide = answerFromServer.canRide();
        this.message = answerFromServer.message();
    }

    public boolean isCanRide() {
        return canRide;
    }

    public String getMessage() {
        return message;
    }
}
