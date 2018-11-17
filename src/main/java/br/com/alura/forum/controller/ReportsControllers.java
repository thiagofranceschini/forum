package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.forum.model.OpenTopicsByCategory;
import br.com.alura.forum.repository.OpenTopicByCategoryRepository;

@Controller
@RequestMapping("/admin/reports")
public class ReportsControllers {

	@Autowired
	private OpenTopicByCategoryRepository openTopicsByCategory;

	@GetMapping("/open-topics-by-category")
	public String showOpenTopicsByCategoryReport(Model model) {
		List<OpenTopicsByCategory> openTopics = openTopicsByCategory.findAllByCurrentMonth();
		model.addAttribute("openTopics", openTopics);
		return "report";

	}
}
