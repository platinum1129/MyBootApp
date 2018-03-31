package jp.co.corerd.springboot.controller;

/**
 * 共通処理用コントローラー
 */
public class BaseController {
	
	/**
	 * ログ出力
	 * @param arr 出力対象オブジェクト
	 */
	protected void log(Object... arr) {
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
