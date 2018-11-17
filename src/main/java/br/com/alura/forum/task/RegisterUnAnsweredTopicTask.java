package br.com.alura.forum.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.alura.forum.model.OpenTopicsByCategory;
import br.com.alura.forum.repository.OpenTopicByCategoryRepository;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.service.NewReplyProcessorService;

@Component
public class RegisterUnAnsweredTopicTask {

	@Autowired private TopicRepository repository;
	@Autowired private OpenTopicByCategoryRepository openTopicsRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(NewReplyProcessorService.class);
	
	@Scheduled(fixedDelay=6000)
	public void execute(){
		List<OpenTopicsByCategory>topics = repository.findOpenTopicsByCategory();
		openTopicsRepository.saveAll(topics);
		logger.info("***** RODANDO A TAREFA AGENDADA *****");
	}
	
	
}
