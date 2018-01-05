package examples;

// import Java libraries
import java.io.*;
import java.util.Properties;

// import mail service libraries
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// import Servlet libraries
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mailService")
public class mailService extends HttpServlet 
{
   // This is a bogus email created for this example 
   // To allow any program (e.g., this servlet) to login and send email from a gmail account, 
   // please go to the gmail account >> sign-in and security >> turn on the "less security" option. 

   // username and password used for this example will be given during the class activity
	
   final String username = "someone";
   final String password = "some-password";
		
   final String default_email = "someone@gmail.com";
   
   private String str_cofm = "";

   protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		     throws ServletException, IOException 
   {	    	   
      response.setContentType ("text/html");
      PrintWriter out = response.getWriter();
      
      String to_email = request.getParameter("your_email");
      String email_msg = request.getParameter("email_msg");
      String email_subject = request.getParameter("email_subject");

      out.println("<html>\n<head>\n <title>Sending mail exercise</title>\n</head>");      
      out.println("<body>");
      
      sendEmail(to_email, email_msg, email_subject);
      
      out.println(str_cofm);    // print confirmation 
      
      out.println("</body>");
      out.println("</html>");
      out.close();
   }   
   
   private void sendEmail(String email_address, String email_message, String email_subject)
   {
      Properties props = new Properties();
      
      // Specifies the IP address of your default mail server
      // for example, if you are using gmail server as an email sever
      // you will pass smtp.gmail.com as value of mail.smtp host.
      
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      // pass properties object and authenticator object
      // for authentication to Session instance
      
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
         }
      });        
               
      if (email_address.length() > 0 && email_message.length() > 0)
      {
         try 
         {        	 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(default_email));       // from which email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_address));     // send to which email address
            message.addRecipient(Message.RecipientType.CC,  new InternetAddress(default_email));       // cc to self
            // message.addRecipient(Message.RecipientType.TO, new InternetAddress(another_email));     // add more recipients
			message.setSubject(email_subject);               // set a subject of an email
            message.setContent(email_message, "text/html; charset=utf-8");     // set a message of an email with standard scheme 
            
            Transport.send(message);
            System.out.println("Email sent");                              
               
            // always provide feedback, so that the users know what happens, what to do next
            str_cofm = "Email notification was sent";
            
         } catch (MessagingException e) {
            // if something's wrong, let the user knows        	 
            str_cofm = "There is a problem while sending an email. " + 
                       "Please check your code and try sending an email again."; 
            throw new RuntimeException(e);
         }
       }                 
   }
  
}

