package netdb.courses.softwarestudio.qaqserver.service.objectify;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import netdb.courses.softwarestudio.qaqserver.mvc.model.domain.Question;
import netdb.courses.softwarestudio.qaqserver.mvc.model.domain.Speech;

import com.googlecode.objectify.ObjectifyService;

public class ObjectifyInitializer implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent sce)
		{
			// TODO Auto-generated method stub
			ObjectifyService.register(Question.class);
			ObjectifyService.register(Speech.class);
		}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
		{
			// TODO Auto-generated method stub
		}
}