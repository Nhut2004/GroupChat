package GroupChatClient;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final int port=800;
	private static final String URL="localhost";
	
	public void startClient() {
		try {
			
			Socket socket=new Socket(URL, port);
			System.out.println("Successful connection");
			
			ClientListener clientListener=new ClientListener(socket);
			new Thread(clientListener).start();
			
			OutputStream output=socket.getOutputStream();
			Scanner sc=new Scanner(System.in);
			
			while(true) {
				String message=sc.nextLine();
				output.write(message.getBytes());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
