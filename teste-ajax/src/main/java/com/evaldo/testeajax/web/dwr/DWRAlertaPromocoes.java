package com.evaldo.testeajax.web.dwr;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.evaldo.testeajax.repository.PromocaoRepository;

@Component
@RemoteProxy
public class DWRAlertaPromocoes {
	@Autowired
	private PromocaoRepository promocaoRepository;
	
	private Timer timer;
	
	//faz a ligacao entre a os metodos init do servidor e do cliente
	@RemoteMethod
	public synchronized void init() {
		System.out.println("DWR está ativado!");
		
		LocalDateTime lastDate= getDtCadastroByUltimaPagina();
		
		WebContext context = WebContextFactory.get();
		
		timer = new Timer();
		//executa AlertTask a cada 60000 mm =1 min
		timer.schedule(new AlertTask(context,lastDate), 10000,60000);
		
	}
	
	//classe interna
	class AlertTask extends TimerTask {

		private WebContext context;
		private LocalDateTime lastDate;
		private long count;
		
		public AlertTask(WebContext context,LocalDateTime lastDate) {
			
			super();
			this.context= context;
			this.lastDate=lastDate;
			
			
		}
		@Override
		public void run() {
			String session = context.getScriptSession().getId();
			
			Browser.withSession(context, session, new Runnable() {
				
				@Override
				public void run() {
					
					System.out.println("data parametro"+lastDate);
					
					Map<String,Object> map = promocaoRepository.totalAndUltimaDataDePromocao(lastDate);
					count = (long) map.get("count");
					lastDate = map.get("lastDate") == null ? lastDate:(LocalDateTime)map.get("lastDate") ; 
					
					
					Calendar time = Calendar.getInstance();
					time.setTimeInMillis(context.getScriptSession().getLastAccessedTime());
					System.out.println("count: "+ count 
							       + ", lastDate: " + lastDate 
							       +  " < " + session + " > " 
							       +    time.getTime() + "<" );
					
					
					if(count > 0) {
						ScriptSessions.addFunctionCall("showButton", count);
					}
					
				}
			});
			
			
			
			
		}
		
	}
	private LocalDateTime getDtCadastroByUltimaPagina() {
		PageRequest pageRequest = PageRequest.of(0, 1, Direction.ASC, "dtCadastro");
		return promocaoRepository.findUltimaDataDePromocao(pageRequest).getContent().get(0);
	}

}
