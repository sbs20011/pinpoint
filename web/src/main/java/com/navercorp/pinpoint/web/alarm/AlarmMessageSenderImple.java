package com.navercorp.pinpoint.web.alarm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.navercorp.pinpoint.web.alarm.checker.AlarmChecker;
import com.navercorp.pinpoint.web.service.UserGroupService;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

public class AlarmMessageSenderImple implements AlarmMessageSender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserGroupService userGroupService;

    @Override
    public void sendSms(AlarmChecker checker, int sequenceCount) {
    	logger.info("sendSms checker.getuserGroupId() {}, sequenceCount {}", checker.getuserGroupId(), sequenceCount);
        List<String> receivers = userGroupService.selectPhoneNumberOfMember(checker.getuserGroupId());

        if (receivers.size() == 0) {
            return;
        }
        
        for(int i=0, n=receivers.size(); i<n; i++) {
        	logger.info("sendSms receiver : {}", receivers.get(i));
        }
        
        SlackApi api = new SlackApi("https://hooks.slack.com/services/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"); 
        api.call(new SlackMessage("#pinpoint", null, "sendSms called"));
        

        /*for (String message : checker.getSmsMessage()) {
            logger.info("send SMS : {}", message);

            //TODO sms 전송 로직 구현
        }*/
    }

    @Override
    public void sendEmail(AlarmChecker checker, int sequenceCount) {
    	logger.info("sendEmail checker.getuserGroupId() {}, sequenceCount {}", checker.getuserGroupId(), sequenceCount);
        List<String> receivers = userGroupService.selectEmailOfMember(checker.getuserGroupId());

        if (receivers.size() == 0) {
            return;
        }
        
        for(int i=0, n=receivers.size(); i<n; i++) {
        	logger.info("sendEmail receiver : {}", receivers.get(i));
        }
        
        SlackApi api = new SlackApi("https://hooks.slack.com/services/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"); 
        api.call(new SlackMessage("#pinpoint", null, "sendSms called"));
        
        /*for (String message : checker.getEmailMessage()) {
            logger.info("send email : {}", message);

            //TODO email 전송 로직 구현
        }*/
    }
}