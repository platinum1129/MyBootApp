package jp.co.corerd.springboot;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 「REST」でやり取りするためのController　return値がレスポンスとなる。
public class HelloRestController {

	@RequestMapping("/rest/sum/{num}") // パス変数
	public String sum(@PathVariable int num) { // @PathVariable：パス変数によって値を渡されるもの
		int res = 0;
		for (int i = 1; i <= num; i++) {
			res += i;
		}
		return "total: " + res;
	}
	
	@RequestMapping("/rest/json/{id}")
	public DataObject json(@PathVariable int id) {
		return new DataObject(id, "なまえ", "めーる", "<h1></h1>"); // オブジェクトを返却するとjson形式でレスポンスされる
	}
}
