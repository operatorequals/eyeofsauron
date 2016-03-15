package eyeofsauron.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.serialize.Printer;

import eyeofsauron.coreutils.clusterer.ClustererArgument;
import eyeofsauron.coreutils.clusterer.ClustererResult;
import eyeofsauron.coreutils.clusterer.semclusterer.SemanticClustererImpl;
import eyeofsauron.coreutils.sentimentanalysis.*;
import eyeofsauron.domain.utils.arguments.UtilityArgumentException;

/**
 * Servlet implementation class SentTestForm
 */
@WebServlet("/ClustTestForm")
public class ClustTestForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SemanticClustererImpl clusterer;
	protected String defaultText;
	protected String htmlForm1 = "<form action=\"ClustTestForm\" method=\"POST\">\n" + 
			"Text to Analyze:<br>\n" + 
//			"<input type=\"textarea\" name=\"text\" value=\"\">\n" + 
			"<textarea name=\"text\" cols=\"50\" rows=\"10\"> </textarea> "+
"<br>\n" + 

			"<input type=\"submit\" value=\"Submit\">\n" + 
			"</form> ";
	/**
     * @see HttpServlet#HttpServlet()
     */
    public ClustTestForm() {
        super();
        clusterer = new SemanticClustererImpl  ();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.flush();
		out.append("<head></head><body>");
		out.append(htmlForm1);
		
		out.append("</body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String text = request.getParameter("text");
		
		doGet(request, response);
		PrintWriter out = response.getWriter();
		out.append("<p>");
		out.append("<p>");
		out.append("The result is:");
		out.append("<p>");
		out.append("<p>");
		try {
			String[] docs = text.split("//");
			ClustererArgument arg = new ClustererArgument();
			for (Object s : docs)
				arg.addArgument(s);
			
			ClustererResult res = clusterer.process(arg);
			out.append("<json>");
			out.append(res.toJson());
//			System.out.println(res.toJson());
			out.append("</json>");
		} catch (UtilityArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
