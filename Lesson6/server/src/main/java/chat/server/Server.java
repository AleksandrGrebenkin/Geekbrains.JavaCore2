package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private AuthManager authManager;
    private List<ClientHandler> clients;

    public AuthManager getAuthManager(){return authManager;}

    public Server(int port){
        clients = new ArrayList<>();
        authManager = new BasicAuthManager();
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server started.");
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Client connected.");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(String message){
        for (ClientHandler c : clients){
            c.sendMessage(message);
        }
    }

    public boolean whisperMessage(String sender, String recipient, String message){
        ClientHandler rec = getClientHandlerByNickname(recipient);
        ClientHandler sen = getClientHandlerByNickname(sender);
        if(rec != null) {
            rec.sendMessage("Whispered from " + sender + ": " + message);
            sen.sendMessage("Whispered to " + recipient + ": " + message);
            return true;
        }
        return false;
    }

    public boolean isNickBusy(String nickname){
        for(ClientHandler c : clients){
            if(c.getNickname().equals(nickname)){
                return true;
            }
        }
        return false;
    }

    public synchronized void subscribe(ClientHandler clientHandler){
        broadcastMessage(clientHandler.getNickname() + " logged in.");
        clients.add(clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler){
        broadcastMessage(clientHandler.getNickname() + " logged out.");
        clients.remove(clientHandler);
    }

    private ClientHandler getClientHandlerByNickname(String nickname){
        for(ClientHandler c : clients){
            if(c.getNickname().equals(nickname)){
                return c;
            }
        }
        return null;
    }
}
