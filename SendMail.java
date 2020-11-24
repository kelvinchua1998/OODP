import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class is to send email to students when they add course, drop course, or allocated course from the waitlist
 */
public class SendMail {

/**
 * method to send the email to students
 * @param senderGMail email of admin
 * @param senderGMailUsername username of admin
 * @param senderGMailPassword password of admin
 * @param RecipientEmail email of receiver of the email
 * @param EmailSubject subject of the email
 * @param EmailContent body of the email
 */
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
/**
 * compose email to students that dropped course
 * @param courseObj course that students dropped
 * @param StudentEmail email of the student
 */
	public void droppedCourse(StudentCourse courseObj, String StudentEmail){
		String EmailContent = "Dear Sir/Mdm,\n This is a confirmation email that your course "+ courseObj.getCourseCode()+" "+courseObj.getCourseName()+" index "+courseObj.getCourseIndex()+"been successfully dropped\n Thank You\n NTU STARS";

            sendgmail("melvinchuaqwerty@gmail.com", "melvinchuaqwerty@gmail.com", "s9825202i",StudentEmail, "Course dropped", EmailContent);
	}
/**
 * compose email to students that are allocated the course from the waitlist
 * @param courseObj course that students were allocated to from the waitlist
 * @param StudentEmail email of the student
 */
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
