package GroupChatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	private static final int port=800;
	List<ClientHandler> clients= new ArrayList<>();
	
	public void startServer() {
		try {
			ServerSocket serverSocket=new ServerSocket(port);
			System.out.println("Server started. Listening on port " + port);
			
			while(true) {
				Socket clientSocket=serverSocket.accept();
				System.out.println("Client has conneted with port : "+ clientSocket.getInetAddress().getHostName() );
				
				ClientHandler clientHandler=new ClientHandler(clientSocket,System.currentTimeMillis()+"",this);
				clients.add(clientHandler);
				new Thread(clientHandler).start();
				
			}
			
					
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public void boardcastMessage(String id, String message) {
		
		for (ClientHandler clientHandler : clients) {
			if(!(clientHandler.getId().equals(message))) {
				clientHandler.sendMessage(id+" : "+message);
			}
		}
		
	}
}
