package eyeofsauron.servlet.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;


import eyeofsauron.controllers.CoreArgument;
import eyeofsauron.controllers.CoreResult;
import eyeofsauron.controllers.impl.simple.*;
import eyeofsauron.domain.utils.arguments.UtilityArgumentException;


/**
 * Servlet implementation class SentTestForm
 */
@WebServlet("/EyeOfSauronAPI")
public class EyeOfSauronAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleCoreController controller;
	protected String defaultText;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public EyeOfSauronAPI() {
        super();
        
        try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void init(){
		try {
			controller = new SimpleCoreController();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			controller = new SimpleCoreController();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		PrintWriter out = response.getWriter();
		out.flush();
		out.append("<head>"+ 
				"</head><body>");
		
		String text = request.getParameter("text");
		if (text == null ){
			out.append("</body>");
			return;
		}

		try {

			CoreArgument arg = new CoreArgument();
			arg.addArgument(text);
			
			CoreResult res =  controller.process(arg);
			out.append("<json>");
			String escapedText = StringEscapeUtils.escapeHtml(res.toJson());
			out.println(escapedText );
			out.append("</json>");
		} catch (UtilityArgumentException e) {
			e.printStackTrace();
		}
		out.append("</body>");

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String json = request.getParameter("config");
		SimpleCoreConfig conf = new SimpleCoreConfig (json);
		controller.configure(conf);
		System.out.println("configuration: " +json+"\n accepted!");
	}

}
