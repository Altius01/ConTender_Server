package core;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import com.google.gson.Gson;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import auth.AuthService;
import database.DBService;
import entities.Tender;
import entities.User;
import entities.UserAuth;
import inetConnection.Session;
import io.InetMsg;
import io.InetMsgType;
import io.SignInForm;
import msgService.CallableClass;
import msgService.MsgToServer;
import msgSystem.Abonent;
import msgSystem.Adress;
import msgSystem.Msg;
import msgSystem.MsgSystem;
import service.Service;
import service.ServiceTask;

public class Core extends Service {
	private Adress adress;
	private MsgSystem ms;
	private Gson gson;
	private DBService dbService;

	public Core() {
		adress = new Adress();
		ms = MsgSystem.getInstance();
		dbService = DBService.getInstance();
		ms.addAbonent(this);

		gson = new Gson();
	}

	public void manageMsg(InetMsg msg, Adress from) {
		
		switch(msg.getHead()) {
			case GET_USER:
				getUser(msg.getBody());
				break;
			case POST_USER:
				postUser(msg.getBody());
				break;
			case SIGN_IN_USER:
				signInUser(msg, from);
				break;
			case SIGN_UP_USER:
				signUpUser(msg, from);
				break;
			case GET_TENDER:
				getTender(msg.getBody());
				break;
			case POST_TENDER:
				postTender(msg.getBody());
		}
	}

	private void getUser(String body) {
		int pid = gson.fromJson(body, Integer.class);
		// TODO: send user info back to client 
	}

	private void postUser(String body) {
		// TODO: update user info on BD 
	}

	private void signInUser(InetMsg inetMsg, Adress from) {
		System.out.println("User posted:" + inetMsg.getBody());
		
		String response = null;
		
		SignInForm data = gson.fromJson(inetMsg.getBody(), SignInForm.class);
		
		ExecutorService executor;
        executor = Executors.newSingleThreadExecutor();
        
        CallableClass<UserAuth> callable = new CallableClass<UserAuth>() {

			@Override
			public UserAuth call() throws Exception {
				try {
					PreparedQuery<UserAuth> querry = dbService.getUserAuthDao().
							queryBuilder().where().eq(User.NAME_FIELD, data.getName()).prepare();
					return dbService.getUserAuthDao().queryForFirst(querry);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		};
        
        Future<UserAuth> future;
        future = executor.submit(callable);


        //while(!future.isDone()) {
        	//System.out.println("Wait for db...");
        //}
        
		try {
			if (future.get().getPassword().equals(data.getPassword())) {
				response = AuthService.getInstance().addToken(future.get().getPid());
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InetMsg responseMsg = new InetMsg(InetMsgType.SIGN_IN_USER, response);
		
		MsgToServer msg = new MsgToServer(this.adress, from) {
			
			@Override
			public void exec(Session abonent) {
				System.out.println("Sending response...");
				abonent.send(gson.toJson(responseMsg));
			}
		};
		
		ms.sendMessage(msg);
	}

	private void signUpUser(InetMsg inetMsg, Adress from) {
		System.out.println("User posted:" + inetMsg.getBody());
		SignInForm data = gson.fromJson(inetMsg.getBody(), SignInForm.class);
		
		String response = null;
				
		ExecutorService executor;
        executor = Executors.newSingleThreadExecutor();
        
        CallableClass<UserAuth> callable = new CallableClass<UserAuth>() {

			@Override
			public UserAuth call() throws Exception {
				try {
					return DBService.getInstance().getUserAuthDao()
							.createIfNotExists(new UserAuth(data.getName(), data.getPassword()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		};
        
        Future<UserAuth> future;
        future = executor.submit(callable);


        while(!future.isDone()) {
        	System.out.println("Wait for db...");
        }
        
		try {
			if (future.get().getPassword() == data.getPassword()) {
				response = "Alright!";
				// TODO make normal response
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InetMsg responseMsg = new InetMsg(InetMsgType.SIGN_IN_USER, response);
		
		MsgToServer msg = new MsgToServer(this.adress, from) {
			
			@Override
			public void exec(Session abonent) {
				System.out.println("Sending response...");
				abonent.send(gson.toJson(responseMsg));
			}
		};
		
		ms.sendMessage(msg);
	}

	private void getTender(String body) {
		int pid = gson.fromJson(body, Integer.class);
	}

	private void postTender(String body) {
		Tender data = gson.fromJson(body, Tender.class);
	}
}
