package jp.co.corerd.springboot.entities;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import jp.co.corerd.springboot.annotation.Phone;

@Entity
@Table(name="M_USER")
public class User {

	public User() {
		// デフォルトコンストラクタがないと「No default constructor for entity」エラーとなる
	}
	
	public User(String code, @NotEmpty String name, @Email String mail, String phone, @Min(0) @Max(200) Integer age,
			@Min(0) @Digits(integer = 3, fraction = 1) BigDecimal height, String memo) {
		this.code = code;
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.age = age;
		this.height = height;
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", code=" + code + ", name=" + name + ", mail=" + mail + ", phone=" + phone +
				", age=" + age + ", height="
				+ height + ", memo=" + memo + "]";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // 自動採番（任意設定しても自動で上書きされる）
	@Column
	private long id;

	@Length(min=8, max=8)
	@Column(length = 8, nullable=false)
	private String code;
	
	@Column(length = 50, nullable=true)
	@NotEmpty
	private String name;
	
	@Column(length = 50, nullable=true)
	@Email
	private String mail;
	
	@Column(length = 15, nullable=true)
	@Phone(onlyNumber = true) // 手動作成アノテーション
	private String phone;
	
	@Column(nullable=true)
	@Min(0)
	@Max(200)
	private Integer age;

	@Min(0)
	@Digits(integer = 3, fraction = 1)
	private BigDecimal height;

	@Length(max = 100) // 文字数100まで。100バイトではない。
	@Column(length = 100, nullable=true)
	private String memo;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	

}
