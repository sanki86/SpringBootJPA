import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.textToSpeech.ssmlConversion;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    
    System.out.println("I am here in helloservlet");
    
		/*
		 * SampleSpeech speech = new SampleSpeech();
		 * 
		 * speech.sampleRecognize();
		 */
    
		
		  SampleSpeechWithMic speechMic = new SampleSpeechWithMic(); 
		  try {
		 
		  speechMic.streamingMicRecognize();
		  }
		  
		  catch(Exception exception) {
		  System.err.println("Failed to get mic request " + exception); }
		 
    
		/*
		 * ssmlConversion conv = new ssmlConversion(); try { conv.basic(); } catch
		 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */

    response.getWriter().print("Hello App Engine!\r\n");
    
  }
}