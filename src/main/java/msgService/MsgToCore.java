package msgService;

import core.Core;
import msgSystem.Abonent;
import msgSystem.Adress;
import msgSystem.Msg;

public abstract class MsgToCore extends Msg{

	public MsgToCore(Adress from, Adress to) {
		super(from, to);
	}
	
	public void exec(Abonent abonent) {
		if (abonent instanceof Core) {
			exec((Core) abonent);
		}
	}
	
	public abstract void exec(Core abonent);
}
