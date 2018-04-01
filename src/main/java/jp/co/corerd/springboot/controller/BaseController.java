package jp.co.corerd.springboot.controller;

/**
 * 共通処理用コントローラー
 */
public class BaseController {

	/**
	 * スリープ処理
	 * @param mills 待機ミリ秒
	 */
	protected void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	
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
