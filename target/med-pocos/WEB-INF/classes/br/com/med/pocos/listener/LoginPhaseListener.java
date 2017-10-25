package br.com.med.pocos.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.Cookie;

/** 
 * @author Artur
 *
 */
public class LoginPhaseListener implements PhaseListener {

	private static final long serialVersionUID = -7727086585081676029L;

	@Override
	public void afterPhase(PhaseEvent event) {
		
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
