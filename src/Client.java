import java.net.*;
import java.io.*;

public class Client {
//Test with alex branch

	public static void main(String[] args) {
		try {
			Socket cs = new Socket("localhost", 8800); // server
			System.out.println("Client is Runnig");
			DataInputStream dins = new DataInputStream(cs.getInputStream());
			DataOutputStream douts = new DataOutputStream(cs.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			Thread sendMessage = new Thread(new Runnable() {
				public void run() {
					while (true) {
						String msgout;
						try {
							msgout = br.readLine();
							douts.writeUTF(msgout);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});
			Thread readMessage = new Thread(new Runnable() {
				public void run() {
					while (true) {
						String msgin;
						try {
							msgin = dins.readUTF();
							System.out.println(msgin);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});
			sendMessage.start();
			readMessage.start();
			//cs.close();
		} catch (Exception e) {
			System.out.println("\n" + e.getMessage());
			System.exit(1);
		}

	}
}
