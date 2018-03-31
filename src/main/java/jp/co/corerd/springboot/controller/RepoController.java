package jp.co.corerd.springboot.controller;

import java.math.BigDecimal;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.corerd.springboot.entities.User;
import jp.co.corerd.springboot.repositories.MyDataRepository;

@Controller
@ComponentScan("service")
/**
 * DBアクセス検証用コントローラー
 */
public class RepoController extends BaseController {
	
	private static String HTML_NAME = "repo";
	
	@Autowired
	MyDataRepository repository;

	/**
	 * 起動時に読み込むデータ
	 */
	@PostConstruct
	public void initHelloController() {
		log("initHelloController - S");
		User entity = new User(
				"crd00000",
				"しゃちょう",
				"crd@corerd.co.jp",
				"0364350570", // @PhoneのonlyNumberでハイフン有無制御するように手動実装している
				30,
				new BigDecimal("123.4"),
				"めも"
				);
		
		repository.saveAndFlush(entity);
		
		log("initHelloController - E");
	}
	
	/**
	 * 初期表示
	 * 
	 * @param user User
	 * @param mav リクエストModelAndView
	 * @return レスポンスModelAndView
	 */
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
			BindingResult result, // Validation結果
			ModelAndView mav) {
		
		log("doSelect - S", user, result);
		
		if (user.getId() != 0L) {
			Optional<User> entity = repository.findById(user.getId());
			mav.addObject("formModel", entity.orElse(user));
		} else {
			mav.addObject("formModel", user);
		}
		
		// 明細検索
		searchList(mav);
		
		log("doSelect - E");
		
		return mav;
	}
	
	/**
	 * <pre>
	 * 登録／更新ボタン
	 * ・Validated → Validation設定
	 * ・BindingResult → Validation結果
	 * 
	 * </pre>
	 */
	@RequestMapping(
			value="/repo",
			params="update", // Submitボタンのname
			method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView doUpdate(
			@ModelAttribute("formModel") @Validated User user,
			BindingResult result,
			ModelAndView mav) {
		
		log("doUpdate - S", user, result.hasErrors());
		
		if (result.hasErrors()) {
			// エラー
			// ※ @GroupSequenceアノテーションによって、 順序制御することもできる
			//    しかし実装がめんどうなため、入力項目の横にエラーを出力する方法が推奨。とはいえ、業務エラーチェックがどのみち…
			log("error");
			mav.addObject("msg", "sorry, error is occured...");
			return mav;
		} else {
			// 登録も更新もこれ1行（idが0でなければ更新）
			log("regist");
			repository.saveAndFlush(user);
		}
		
		log("doUpdate - E");
		
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
		
		return new ModelAndView("redirect:/" + HTML_NAME); // GETは/付で返す
	}

	/**
	 * 明細検索処理
	 * @param mav ModelAndView
	 */
	private void searchList(ModelAndView mav) {
		Iterable<User> list = repository.findAll();
		mav.addObject("datalist", list);
		mav.setViewName(HTML_NAME);
	}
	
}
