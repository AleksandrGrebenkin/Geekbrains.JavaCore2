package chat.server;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainApp {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)){
            System.out.println("Server started.");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true){
                String message = in.readUTF();
                if(message.equals("/end")){
                    tryClose(in);
                    tryClose(out);
                    tryClose(socket);
                    break;
                }
                System.out.println("Message from client: " + message);
                out.writeUTF("echo: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void tryClose(Closeable closeable){
        try{
            if(closeable != null){
                closeable.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
