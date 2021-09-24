import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.Color;

public class FTP_Server {

	private static JFrame frmServer;
	
	// UI Stuff
	static JLabel lb_Status_Label;
	static JLabel lb_ClientConn_Label;
	static JLabel lb_RecievedFiles_Label;

	static ServerSocket serverSocket;
	
	static InputStream in;			// getting streams from Clients
	static OutputStream out;		// sending streams to Clients
	
	
	static JLabel lblNewLabel;
	static JLabel lblNewLabel_2;
	static JLabel lblNewLabel_3;
	static JLabel lblNewLabel_4;
	static JLabel lblNewLabel_5;
	
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FTP_Server window = new FTP_Server();
					window.frmServer.setVisible(true);
					
					lblNewLabel.setText("Running");
					lblNewLabel.setForeground(new Color(50, 205, 50));
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			
		
			serverSocket = new ServerSocket(1234);	
			// To accept clients
			Socket socket = serverSocket.accept();

			lblNewLabel_2.setText("Client Connected!");
			lblNewLabel_2.setForeground(new Color(255, 215, 0));

			
			lblNewLabel_4.setText("IP: " + socket.getInetAddress());
			lblNewLabel_5.setText("Port: " + socket.getPort());
			
			
			// in = InputStream; out = OutputStream objects
			in = socket.getInputStream();				
			out = new FileOutputStream("File_Recieved.txt"); // incoming.txt will be the re-named to the file received
			
			byte[] b = new byte[16 * 1024];
			
			int count;
			
			while (!socket.isClosed()) {
					while ((count = in.read(b)) >= 0) {
						out.write(b, 0, count);
						lblNewLabel_3.setText("New File Recieved!");
						lblNewLabel_3.setForeground(new Color(0, 255, 255));
					}
			}
	}

	/**
	 * Create the application.
	 */
	public FTP_Server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServer = new JFrame();
		frmServer.getContentPane().setForeground(new Color(64, 64, 64));
		frmServer.setBackground(Color.DARK_GRAY);
		frmServer.getContentPane().setBackground(Color.DARK_GRAY);
		frmServer.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		frmServer.setBounds(100, 100, 745, 420);
		frmServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServer.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Server");
		lblNewLabel_1.setForeground(new Color(255, 165, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		lblNewLabel_1.setBounds(0, 1, 727, 65);
		frmServer.getContentPane().add(lblNewLabel_1);
		
		lb_Status_Label = new JLabel("Status:");
		lb_Status_Label.setForeground(new Color(255, 255, 255));
		lb_Status_Label.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lb_Status_Label.setBounds(54, 133, 109, 34);
		frmServer.getContentPane().add(lb_Status_Label);
		
		lb_ClientConn_Label = new JLabel("Client:");
		lb_ClientConn_Label.setForeground(new Color(255, 255, 255));
		lb_ClientConn_Label.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lb_ClientConn_Label.setBounds(54, 201, 109, 34);
		frmServer.getContentPane().add(lb_ClientConn_Label);
		
		
		lb_RecievedFiles_Label = new JLabel("Recieved Files:");
		lb_RecievedFiles_Label.setForeground(new Color(255, 255, 255));
		lb_RecievedFiles_Label.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		lb_RecievedFiles_Label.setBounds(54, 318, 177, 42);
		frmServer.getContentPane().add(lb_RecievedFiles_Label);
		
		
		
		
		lblNewLabel = new JLabel("Stopped");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		lblNewLabel.setBounds(175, 129, 211, 42);
		frmServer.getContentPane().add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel("Waiting for Client");
		lblNewLabel_2.setForeground(new Color(128, 128, 128));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(175, 197, 265, 42);
		frmServer.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("No new File");
		lblNewLabel_3.setForeground(new Color(128, 128, 128));
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
		lblNewLabel_3.setBounds(273, 324, 234, 30);
		frmServer.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("IP: ");
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(175, 256, 234, 16);
		frmServer.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Port:");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(175, 289, 234, 16);
		frmServer.getContentPane().add(lblNewLabel_5);
	}
}
