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
	
	class DataObject {
		
		public DataObject(int id, String name, String address, String htmlText) {
			super();
			this.id = id;
			this.name = name;
			this.address = address;
			this.htmlText = htmlText;
		}
	
		private int id;
		private String name;
		private String address;
		private String htmlText;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getHtmlText() {
			return htmlText;
		}
		public void setHtmlText(String htmlText) {
			this.htmlText = htmlText;
		}
		
	}
}
