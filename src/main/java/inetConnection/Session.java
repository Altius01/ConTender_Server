package inetConnection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.google.gson.Gson;

import io.InetMsg;
import msgService.MsgManageInetMsg;
import msgSystem.Abonent;
import msgSystem.Adress;
import msgSystem.MsgSystem;

public class Session extends Thread implements Abonent {
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

	public void send(String msg) {
		System.out.println("Send to client: \n");
        try {
            out.write(msg + "\n");
            out.flush();
            System.out.println(msg + "\n");
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
