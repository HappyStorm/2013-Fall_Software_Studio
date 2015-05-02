package netdb.courses.softwarestudio.qaqserver.mvc.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import netdb.courses.softwarestudio.qaqserver.mvc.ModelAwareServlet;
import netdb.courses.softwarestudio.qaqserver.mvc.model.domain.Speech;
import netdb.courses.softwarestudio.qaqserver.service.json.JsonService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class SpeechJsonView extends ModelAwareServlet<Speech> 
{
	private static final Log log = LogFactory.getLog(SpeechJsonView.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		if (log.isDebugEnabled())
			log.debug("Responsing 200 OK");
		
		Object spe = getModel(req);
		resp.setContentType("application/json");
		// make sure no intermediate node caches the result
		resp.setHeader("Cache-Control", "no-cache");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(JsonService.serialize(spe));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		if(log.isDebugEnabled()) 
			log.debug("Responsing 201 Created");
		
		Speech spe = (Speech) getModel(req);
		resp.setStatus(201);
		/* 
		 * Append id to the request URI of the controller rather than that of the current 
		 * view.
		 * The URI information of a forwarding servlet can be accessed by the request attributes
		 * javax.servlet.forward.[request_uri|context_path|servlet_path|path_info|query_string] in
		 * the forwared servlet. 
		 */
		resp.setHeader("Location", req.getAttribute("javax.servlet.forward.request_uri")
				+ "/" + spe.getTopic());
	}
}