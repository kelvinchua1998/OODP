import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public void sendgmail(String senderGMail,String senderGMailUsername, String senderGMailPassword, String RecipientEmail, String EmailSubject, String EmailContent){
		final String username = senderGMailUsername;
		final String password = senderGMailPassword; // to be added

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderGMail));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(RecipientEmail)); // to be added an email addr
			message.setSubject(EmailSubject);
			message.setText(EmailContent);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public void droppedCourse(StudentCourse courseObj, String StudentEmail){
		String EmailContent = "Dear Sir/Mdm,\n This is a confirmation email that your course "+ courseObj.getCourseCode()+" "+courseObj.getCourseName()+" index "+courseObj.getCourseIndex()+"been successfully dropped\n Thank You\n NTU STARS";

            sendgmail("melvinchuaqwerty@gmail.com", "melvinchuaqwerty@gmail.com", "s9825202i",StudentEmail, "Course dropped", EmailContent);
	}

	public void allocatedRegfromWaitlist(StudentCourse courseObj, String StudentEmail){
		String EmailContent = "Dear Sir/Mdm,\n This is a confirmation email that your course "+ courseObj.getCourseCode()+" "+courseObj.getCourseName()+" index "+courseObj.getCourseIndex()+" been successfully added from waitlist\n Thank You\n NTU STARS";

            sendgmail("melvinchuaqwerty@gmail.com", "melvinchuaqwerty@gmail.com", "s9825202i",StudentEmail, "Course Allocated from Waitlist", EmailContent);
	}
	public static void main(String[] args) {

		final String username = "melvinchuaqwerty@gmail.com"; // to be added
		final String password = "s9825202i"; // to be added

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("melvinchuaqwerty@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("kelvin_chua_1998@hotmail.com")); // to be added an email addr
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
