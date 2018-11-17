package br.com.alura.forum.service.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.topic_domain.Topic;

@Service
public class ForumMailService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private NewReplyMailFactory newReplymailFactory;

	@Async
	public void sendNewReplayMail(Answer answer){
		Topic answeredTopic = answer.getTopic();
		
		MimeMessagePreparator messagePreparator = (mimeMessage) -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			
			messageHelper.setTo(answeredTopic.getOwnerEmail());
			messageHelper.setSubject("Novo comentário: "+answeredTopic.getShortDescription());
			String messageContent = this.newReplymailFactory.generateNewReplyMailContent(answer);
			messageHelper.setText(messageContent, true);
		};
	
		
		try {
			mailSender.send(messagePreparator);
		} catch (Exception e) {
			throw new ForumMailServiceException("Não foi possível enviar email.", e);
		}
	}

}
