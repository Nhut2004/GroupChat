package GroupChatServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
	
	private Socket socket;
	private String id;
	private ChatServer chatServer;
	private InputStream input;
	private OutputStream output;
	
	
	public ClientHandler(Socket socket, String id, ChatServer chatServer) {
		super();
		this.socket = socket;
		this.id = id;
		this.chatServer = chatServer;
		try {
			this.input=socket.getInputStream();
			this.output=socket.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			byte[] buffer=new byte[1024];
			int bytesRead;
			while((bytesRead=input.read(buffer))!=-1){
				String message=new String(buffer, 0, bytesRead);
				this.chatServer.boardcastMessage(id,message);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	public void sendMessage(String message) {
		try {
			this.output.write(message.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
