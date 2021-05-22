package inetConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.LinkedList;

import com.google.gson.Gson;

import core.Core;
import database.DBService;
import entities.UserAuth;
import io.InetMsg;
import msgService.MsgManageInetMsg;
import msgSystem.Abonent;
import msgSystem.Adress;
import msgSystem.MsgSystem;

public class Server {
    public static final int PORT = 8080;
    public static LinkedList<Session> serverList = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
    	Core core = new Core();
    	core.start();
    	
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server Started");
        
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new Session(socket, core.getAdress()));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
            core.interrupt();
        }
    }
}
   
/*
class Session extends Thread implements Abonent {
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private Gson gson;
    
    private Adress adress;
    private MsgSystem ms;
    private Adress coreAdress;

    public Session(Socket socket, Adress coreAdress) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        gson = new Gson();
        
        adress = new Adress();
        this.coreAdress = coreAdress;
        ms = MsgSystem.getInstance();
        start();
    }

    @Override
    public void run() {
        try {
        	ms.addAbonent(this);
            System.out.println("Начали слушать...");
            
            while (!Thread.interrupted()) {
            	if (in.ready()) {
            		MsgManageInetMsg msg = 
            				new MsgManageInetMsg(this.getAdress(), coreAdress, 
            						gson.fromJson(in.readLine(), InetMsg.class));
            		ms.sendMessage(msg);
            	}
            	ms.execForAbonent(this);
            }
        } catch (IOException throwables) {
            throwables.printStackTrace();
        }
        downService();
    }

	private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}   
    }
    
    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (Session vr : Server.serverList) {
                    if (vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }

	@Override
	public Adress getAdress() {
		return adress;
	}
}
*/
