package connection;

import java.util.List;

import command.CommandInterpreter;
import communication.MailVerificationThread;
import communication.SendEmail;
import interfaces.IEmailEventListener;
import utils.Email;

public class ConnectionCore {
    public SendEmail sendEmail = new SendEmail();

    public static void main(String[] args) {
        MailVerificationThread mail = new MailVerificationThread();
        ConnectionCore core = new ConnectionCore();
        mail.setEmailEventListener(new IEmailEventListener() {

            @Override
            public void onReceiveEmailEvent(List<Email> emails){
                for(Email email : emails){
                    System.out.println("Este es el email" + email);
                    String emailFrom = email.getFrom();
                    System.out.println("el from es:" + email.getFrom());
                    String emailSubject = email.getSubject();
                    String response = CommandInterpreter.interpret(emailSubject);
                    System.out.println(response);                    
                    core.sendEmail.sendEmail(emailFrom,response);
                }
            }
        });

        Thread thread = new Thread(mail);
        thread.setName("Mail Verification Thread");
        thread.start();
    }
}
