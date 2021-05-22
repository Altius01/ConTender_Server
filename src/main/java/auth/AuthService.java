package auth;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AuthService {
	private static AuthService instance;
	
	private Map<String, Token> tokens;
	
	private AuthService() {
		tokens = new HashMap<String, Token>();
	}
	
	public static AuthService getInstance() {
		if (instance == null) {
			instance = new AuthService();
		}
		return instance;
	}
	
	public String addToken(Integer pid) {
		synchronized (tokens) {
			String token = generateToken(pid);
			tokens.put(token, new Token(pid, new Date()));
			return token;
		}
	}
	
	public void deleteToken(String token) {
		synchronized (tokens) {
			tokens.remove(token);
		}
	}
	
	public Integer getPidFromToken(String token) {
		synchronized (tokens) {
			return tokens.get(token).pid;
		}
	}
	
	private String generateToken(Integer pid) {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    return generatedString;
	}
	
	private class Token {
		
		public Token(Integer pid, Date date) {
			this.pid = pid;
			this.date = date;
		}
		public Integer pid;
		public Date date;
	}
}
