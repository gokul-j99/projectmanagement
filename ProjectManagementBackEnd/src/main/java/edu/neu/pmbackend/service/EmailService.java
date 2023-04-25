/**
 * 
 */
package edu.neu.pmbackend.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import edu.neu.pmbackend.dao.StoryDAO;
import edu.neu.pmbackend.dao.StoryDAOImpl;
import edu.neu.pmbackend.dao.UserDAO;
import edu.neu.pmbackend.dao.UserDAOImpl;
import edu.neu.pmbackend.dto.StoryStatus;
import edu.neu.pmbackend.entity.Story;
import edu.neu.pmbackend.entity.User;

/**
 * @author gokuljayavel
 *
 */

@Service
public class EmailService {
	
	
    @Autowired
    private JavaMailSender emailSender;
	
	@Autowired
	private StoryDAOImpl story;
	
	@Autowired
	private UserDAOImpl user;
	
	
	   public void sendReminderEmail(String to, String subject, String text) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(to);
	        message.setSubject(subject);
	        message.setText(text);
	        emailSender.send(message);
	    }
	
	
	@Scheduled(cron = "0 0 0 * * ?") // Run at 12:00 AM every day
	//@Scheduled(cron = "0 */1 * * * ?") // Run every 5 minutes
    public void sendScheduledReminders() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);// Get tomorrow's date
        calendar.set(Calendar.HOUR_OF_DAY, 0); // Set hour to midnight
        calendar.set(Calendar.MINUTE, 0); // Set minute to 0
        calendar.set(Calendar.SECOND, 0); // Set second to 0
        calendar.set(Calendar.MILLISECOND, 0); 
        Date tomorrow = calendar.getTime();
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        String formattedTomorrow = formatter.format(tomorrow);
        
       
        
        Date parsedDate = formatter.parse(formattedTomorrow);
        
        System.out.println(parsedDate);
        
       
        LocalDate localDate2 = parsedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        List<Story> reminders = story.findByDueDate(parsedDate);
        System.out.println(reminders);
        
        System.out.println("Get the mail schedule running succesfully");

        for (Story reminder : reminders) {
        	
        	 LocalDate localDate1 = reminder.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        	
        	System.out.println(reminder.getDueDate());
        	
        	if( localDate1.isEqual(localDate2) && (reminder.getStatus().equals(StoryStatus.IN_PROGRESS) || reminder.getStatus().equals(StoryStatus.TO_DO))) {
        	
        	User userDetail  = user.getById(reminder.getUser_id());
        	System.out.println(userDetail.getUsername());
        	
            sendReminderEmail(userDetail.getUsername(), "Reminder", "Finish the story  story id -  " + reminder.getProjectIdentifier()  +"by tomorrow.");
        	}
            
        }
    }

}
