package jp.co.corerd.springboot;

import java.util.ArrayList;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.corerd.springboot.entities.User;
import jp.co.corerd.springboot.repositories.MyDataRepository;
import jp.co.corerd.springboot.service.HelloService;

@Controller // VIEW層を使ったやり取りをするためのController　return値がテンプレートファイル名となる。
			// pom.xmlで、spring-boot-starter-thymeleafを設定してあるため、
			// 「名前でテンプレートが自動的にロードされる」
@ComponentScan("service")
public class HelloController {
	
	private int counter = 0;
	
	@Autowired
	private HelloService helloSv;
	
	@Autowired
	MyDataRepository repository;
	
	@PostConstruct // 初期化（起動時に読みこみ）
	public void initHelloController() {
		log("initHelloController - S");
		User user = new User();
		user.setName("しゃちょー");
		user.setCode("crd00000");
		repository.saveAndFlush(user);
		log("initHelloController - E");
	}

	// repo：初期表示
	@RequestMapping(value="/repo", method=RequestMethod.GET)
	public ModelAndView init(
			@ModelAttribute("formModel") User user,
			ModelAndView mav) {
		
		log("init - S", user);
		
		// 明細検索
		searchList(mav);
		
		log("init - E", user);
		
		return mav;
	}
	
	/**
	 * SELECTボタン
	 */
	@RequestMapping(
			value="/repo",
			params="select", // Submitボタンのname
			method=RequestMethod.POST)
	@Transactional(readOnly=true)
	public ModelAndView doSelect(
			@ModelAttribute("formModel") User user,
			ModelAndView mav) {
		
		log("doSelect - S", user);
		
		if (user.getId() != 0L) {
			Optional<User> entity = repository.findById(user.getId());
			mav.addObject("formModel", entity.orElse(user));
		} else {
			mav.addObject("formModel", user);
		}
		
		// 明細検索
		searchList(mav);
		
		log("doSelect - E", user);
		
		return mav;
	}
	
	/**
	 * 登録／更新ボタン
	 */
	@RequestMapping(
			value="/repo",
			params="update", // Submitボタンのname
			method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView doUpdate(
			@ModelAttribute("formModel") User user,
			ModelAndView mav) {
		
		log("doUpdate - S", user);
		
		// 登録も更新もこれ1行（idが0でなければ更新）
		repository.saveAndFlush(user);
		
		log("doUpdate - E", user);
		
		return new ModelAndView("redirect:repo");
	}
	
	/**
	 * 削除ボタン
	 */
	@RequestMapping(value="/repo/delete/{id}", method=RequestMethod.GET)
	@Transactional(readOnly=false)
	public ModelAndView doDelete(
			@PathVariable long id,
			ModelAndView mav) {
		
		log("doDelete - S", id);
		
		// 行削除
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			log("削除対象のデータが存在しません。ID：", id);
		}
		
		log("doDelete - E", id);
		
		return new ModelAndView("redirect:/repo"); // GETは/付で返す
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

	/**
	 * 明細検索処理
	 * @param mav ModelAndView
	 */
	private void searchList(ModelAndView mav) {
		Iterable<User> list = repository.findAll();
		mav.addObject("datalist", list);
		mav.setViewName("repo");
	}
	
	private void log(Object... arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (Object obj : arr) {
			if (sb.length() != 0) {
				sb.append(",");
			}
			sb.append(obj);
		}
		System.out.println(sb);
	}
}
