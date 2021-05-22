package msgService;

import core.Core;
import inetConnection.Session;
import msgSystem.Abonent;
import msgSystem.Adress;
import msgSystem.Msg;

public abstract class MsgToServer extends Msg {

	public MsgToServer(Adress from, Adress to) {
		super(from, to);
	}

	public void exec(Abonent abonent) {
		if (abonent instanceof Session) {
			exec((Session) abonent);
		}
	}
	
	public abstract void exec(Session abonent);

}
