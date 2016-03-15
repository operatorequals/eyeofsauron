package eyeofsauron.servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybozu.labs.langdetect.LangDetectException;
import com.sun.org.apache.xml.internal.serialize.Printer;

import eyeofsauron.coreutils.sentimentanalysis.*;
import eyeofsauron.domain.utils.arguments.UtilityArgumentException;

/**
 * Servlet implementation class SentTestForm
 */
@WebServlet("/SentTestForm")
public class SentTestForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SentimentAnalyzerImpl sentAnalysis;
	protected String defaultText;
	protected String htmlForm1 = "<form action=\"SentTestForm\" method=\"POST\">\n" + 
			"Text to Analyze:<br>\n" + 
			"<input type=\"text\" name=\"text\" value=\"\">\n" + 
			"<br>\n" + 

			"<input type=\"submit\" value=\"Submit\">\n" + 
			"</form> ";
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SentTestForm() {
        super();
        try {
			sentAnalysis = new SentimentAnalyzerImpl ();
		} catch (LangDetectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			SentimentAnalysisResult res = sentAnalysis.process(new SentimentAnalysisArgument(text));
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
