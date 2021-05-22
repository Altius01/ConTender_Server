package msgService;

import core.Core;
import io.InetMsg;
import msgSystem.Adress;

public class MsgManageInetMsg extends MsgToCore{
	private InetMsg msg;
	
	public MsgManageInetMsg(Adress from, Adress to, InetMsg msg) {
		super(from, to);
		this.msg = msg;
	}

	@Override
	public void exec(Core abonent) {
		abonent.manageMsg(msg, this.getFrom());
	}
}
