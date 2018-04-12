package jp.co.corerd.springboot.controller;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * <pre>
 * Singleton検証画面用コントローラー
 *   ・ログイン画面
 *   ・シート画面
 * </pre>
 */
@Controller
public class StaticController extends BaseController {
	
	
	/**
	 * 初期表示処理
	 * 
	 * @param mav 遷移元画面情報
	 * @return 遷移先画面情報
	 */
	@RequestMapping(value="/static_login", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		// ドロップダウン
		mav.addObject("users", createUserList());
		mav.setViewName("static_login");
		return mav;
	}

	// インスタンス変数（グローバル変数）
	private String idUserInstance;
	
	/**
	 * ログイン処理
	 * 
	 * @param idUser ユーザーID
	 * @param idPass パスワード
	 * @param mav 遷移元画面情報
	 * @return 遷移先画面情報
	 */
	@RequestMapping(value="/static_login", method=RequestMethod.POST)
	public ModelAndView login(
			@RequestParam(value="idUser", required=false) String idUser,
			@RequestParam(value="idPass", required=false) String idPass,
			ModelAndView mav) {
		
		// Singleton設計のControllerに定義されている
		// インスタンス変数にパラメータを代入
		idUserInstance = idUser;
		
		// 障害を発生し易くするため3秒wait
		for (int i = 0; i < 3000; i++) {
			sleep(1);
		}
		
		// ユーザーIDはパラメータの値を返却
		mav.addObject("idUser", idUser);
		// ユーザー名は、インスタンス変数の値からユーザー名を検索して取得
		mav.addObject("nmUser", users.get(idUserInstance)); 
		
		// カウンター
		mav.addObject("counter", counter);
		
		mav.setViewName("static_sheet");
		return mav;
	}
	
	/**
	 * ログアウト処理
	 * 
	 * @param mav 遷移元画面情報
	 * @return 遷移先画面情報
	 */
	@RequestMapping(value="/static_sheet",
			params="logout", // Submitボタンのname
			method=RequestMethod.POST)
	public ModelAndView logout(ModelAndView mav) {
		return new ModelAndView("redirect:static_login");
	}
	
	/** カウンターをインスタンス変数（システムで1個）定義 */
	int counter = 0;
	
	/**
	 * カウンター加算処理
	 */
	@RequestMapping(value="/static_sheet",
			params="count", // Submitボタンのname
			method=RequestMethod.POST)
	public ModelAndView count(ModelAndView mav,
		@RequestParam(value="idUser", required=false) String idUser,
		@RequestParam(value="nmUser", required=false) String nmUser) {
		
		// 1づつ1000加算する
		for (int i = 0; i < 1000; i++) {
			sleep(2);
			counter = counter + 1;
		}
		
		// カンマ区切りで設定しておく
		mav.addObject("counter", NumberFormat.getNumberInstance().format(counter));
		
		// 値がクリアされないための設定
		mav.addObject("idUser", idUser);
		mav.addObject("nmUser", nmUser);

		mav.setViewName("static_sheet");
		return mav;
	}
	
	/**
	 * カウンタークリア処理
	 */
	@RequestMapping(value="/static_sheet",
			params="clear", // Submitボタンのname
			method=RequestMethod.POST)
	public ModelAndView clear(ModelAndView mav,
		@RequestParam(value="idUser", required=false) String idUser,
		@RequestParam(value="nmUser", required=false) String nmUser) {
		
		counter = 0;
		mav.addObject("idUser", idUser);
		mav.addObject("nmUser", nmUser);
		mav.addObject("counter", counter);
		
		mav.setViewName("static_sheet");
		return mav;
	}
	
	// ユーザー情報作成
	private static Map<String, String> users = new LinkedHashMap<>();
	static {
		users.put("002","a");
		users.put("003","a");
		users.put("014","a");
		users.put("017","a");
		users.put("040","a");
		users.put("046","a");
		users.put("054","a");
		users.put("055","a");
		users.put("056","a");
		users.put("057","a");
		users.put("062","a");
		users.put("066","a");
		users.put("069","a");
		users.put("070","a");
		users.put("072","a");
		users.put("073","a");
		users.put("074","a");
		users.put("077","a");
		users.put("080","a");
		users.put("086","a");
		users.put("087","a");
		users.put("090","a");
		users.put("091","a");
		users.put("092","a");
		users.put("094","a");
		users.put("095","a");
		users.put("097","a");
		users.put("100","a");
		users.put("104","a");
		users.put("106","a");
		users.put("107","a");
		users.put("110","a");
		users.put("112","a");
		users.put("114","a");
		users.put("115","a");
		users.put("116","a");
		users.put("117","a");
		users.put("118","a");
		users.put("119","a");
		users.put("120","a");
		users.put("121","a");
		users.put("123","a");
		users.put("124","a");
		users.put("126","a");
		users.put("127","a");
		users.put("128","a");
		users.put("129","a");
		users.put("130","a");
		users.put("131","a");
		users.put("134","a");
		users.put("135","a");
		users.put("136","a");
		users.put("137","a");
		users.put("138","a");
		users.put("139","a");
		users.put("140","a");
		users.put("141","a");
		users.put("142","a");
		users.put("143","a");
	}

	/**
	 * ユーザー情報
	 */
	class User {
		User(String id, String name) {
			this.id = id;
			this.name = name;
		}
		private String id;
		private String name;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	/**
	 * SELECT用のList作成
	 */
	private ArrayList<User> createUserList() {
		ArrayList<User> usersList = new ArrayList<>();
		for (Map.Entry<String, String> entry : users.entrySet()) {
			usersList.add(new User(entry.getKey(), entry.getValue()));
		}
		return usersList;
	}
	

}
