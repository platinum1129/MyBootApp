package jp.co.corerd.springboot.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StaticController extends BaseController {
	
	@RequestMapping(value="/static_login", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("static_login");
		return mav;
	}
	
	private String idUserInstance;
	
	@RequestMapping(value="/static_login", method=RequestMethod.POST)
	public ModelAndView login(
			@RequestParam(value="idUser", required=false) String idUser,
			@RequestParam(value="idPass", required=false) String idPass,
			ModelAndView mav) {
		mav.setViewName("static_sheet");
		
		// Singleton設計のControllerに定義されている
		// インスタンス変数（idUserInstance）に、パラメータを代入し、
		idUserInstance = idUser;
		
		// 障害を発生させるため3秒wait
		for (int i = 0; i < 3000; i++) {
			sleep(1);
		}
		
		// ユーザーIDはパラメータの値を返却
		mav.addObject("idUser", idUser);
		// ユーザー名は、インスタンス変数の値からユーザー名を検索して取得
		mav.addObject("nmUser", users.get(idUserInstance)); 
		
		return mav;
	}
	
	@RequestMapping(value="/static_sheet", method=RequestMethod.POST)
	public ModelAndView logout(ModelAndView mav) {
		mav.setViewName("static_login");
		return mav;
	}
	
	
	
	// ユーザー情報作成
	private static HashMap<String, String> users = new HashMap<>();
	static {
		users.put("002","002なまえ");
		users.put("003","003なまえ");
		users.put("014","014なまえ");
		users.put("017","017なまえ");
		users.put("040","040なまえ");
		users.put("046","046なまえ");
		users.put("054","054なまえ");
		users.put("055","055なまえ");
		users.put("056","056なまえ");
		users.put("057","057なまえ");
		users.put("062","062なまえ");
		users.put("066","066なまえ");
		users.put("069","069なまえ");
		users.put("070","070なまえ");
		users.put("072","072なまえ");
		users.put("073","073なまえ");
		users.put("074","074なまえ");
		users.put("077","077なまえ");
		users.put("080","080なまえ");
		users.put("086","086なまえ");
		users.put("087","087なまえ");
		users.put("090","090なまえ");
		users.put("091","091なまえ");
		users.put("092","092なまえ");
		users.put("094","094なまえ");
		users.put("095","095なまえ");
		users.put("097","097なまえ");
		users.put("100","100なまえ");
		users.put("104","104なまえ");
		users.put("106","106なまえ");
		users.put("107","107なまえ");
		users.put("110","110なまえ");
		users.put("112","112なまえ");
		users.put("114","114なまえ");
		users.put("115","115なまえ");
		users.put("116","116なまえ");
		users.put("117","117なまえ");
		users.put("118","118なまえ");
		users.put("119","119なまえ");
		users.put("120","120なまえ");
		users.put("121","121なまえ");
		users.put("123","123なまえ");
		users.put("124","124なまえ");
		users.put("126","126なまえ");
		users.put("127","127なまえ");
		users.put("128","128なまえ");
		users.put("129","129なまえ");
		users.put("130","130なまえ");
		users.put("131","131なまえ");
		users.put("134","134なまえ");
		users.put("135","135なまえ");
		users.put("136","136なまえ");
		users.put("137","137なまえ");
		users.put("138","138なまえ");
		users.put("139","139なまえ");
		users.put("140","140なまえ");
		users.put("141","141なまえ");
		users.put("142","142なまえ");
		users.put("143","143なまえ");
	}
}
