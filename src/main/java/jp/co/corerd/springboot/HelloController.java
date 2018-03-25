package jp.co.corerd.springboot;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.HelloService;

@Controller // VIEW層を使ったやり取りをするためのController　return値がテンプレートファイル名となる。
			// pom.xmlで、spring-boot-starter-thymeleafを設定してあるため、
			// 「名前でテンプレートが自動的にロードされる」
@ComponentScan("service")
public class HelloController {
	
	private int counter = 0;
	
	@Autowired
	private HelloService helloSv;

	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/sum/{num}")
	public String sum(@PathVariable int num, Model model) {
		int res = 0;
		for (int i = 1; i <= num; i++) {
			res += i;
		}
		model.addAttribute("msg", "total: " + res);
		return "index";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
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
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="/data")
	public ModelAndView data(ModelAndView mav) {
		mav.setViewName("data");
		mav.addObject("msg", "current data...");
		
		DataObject obj = new DataObject(123, "matsu", "matsumoto@corerd.co.jp", "<h2>松</h2>");
		mav.addObject("object", obj);
		return mav;
	}
	
}
