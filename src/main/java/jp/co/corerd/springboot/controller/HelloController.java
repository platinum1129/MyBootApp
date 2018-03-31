package jp.co.corerd.springboot.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.corerd.springboot.service.HelloService;

@Controller // VIEW層を使ったやり取りをするためのController　return値がテンプレートファイル名となる。
			// pom.xmlで、spring-boot-starter-thymeleafを設定してあるため、
			// 「名前でテンプレートが自動的にロードされる」
@ComponentScan("service")
public class HelloController extends BaseController {
	
	private int counter = 0;
	private static String HTML_NAME = "index";
	
	@Autowired
	private HelloService helloSv;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName(HTML_NAME);
		mav.addObject("msg", "フォームを送信してください。");
		
		ArrayList<String[]> data = new ArrayList<>();
		data.add(new String[] {"あああ", "いいい", "ううう"});
		data.add(new String[] {"かかか", "ききき", "くくく"});
		data.add(new String[] {"さささ", "ししし", "すすす"});
		mav.addObject("data", data);
		mav.addObject("counter2", helloSv.add(counter, counter));
		mav.addObject("counter", counter++);
		
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ModelAndView send(
			@RequestParam(value="text1", required=true) String text1,
			@RequestParam(value="check1", required=false) boolean check1,
			@RequestParam(value="radio1", required=false) String radio1,
			@RequestParam(value="select1", required=false) String select1,
			@RequestParam(value="select2", required=false) String select2,
			ModelAndView mav) {
		
		StringBuilder sb = new StringBuilder();
		sb.append(" text1:").append(text1);
		sb.append(" check1:").append(check1);
		sb.append(" radio1:").append(radio1);
		sb.append(" select1:").append(select1);
		sb.append(" select2:").append(select2);

		ArrayList<String[]> data = new ArrayList<>();
		data.add(new String[] {"あああ", "いいい", "ううう" + counter});
		data.add(new String[] {"かかか", "ききき", "くくく" + counter});
		data.add(new String[] {"さささ", "ししし", "すすす" + counter});
		mav.addObject("data", data);
		
		mav.addObject("msg", sb.toString());
		mav.addObject("counter", counter++);
		mav.setViewName(HTML_NAME);
		return mav;
	}
	
}
