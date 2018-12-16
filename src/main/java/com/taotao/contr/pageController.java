package com.taotao.contr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.facade.service.ContentService;

@Controller
public class pageController {

	@Autowired
	ContentService contentService;
	
	@RequestMapping("/index")
	public String getIndex(Model model) {
		String contentList = contentService.getPageContentList();
		model.addAttribute("ad1", contentList);
		return "index";
	}
}
