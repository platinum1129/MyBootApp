package jp.co.corerd.springboot;

public class DataObject {
	
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
