package jp.co.corerd.springboot.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="M_USER")
public class User {
	
	@Override
	public String toString() {
		return "User [id=" + id + ", code=" + code + ", name=" + name + ", mail=" + mail + ", age=" + age + ", memo="
				+ memo + "]";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // 自動採番（任意設定しても自動で上書きされる）
	@Column
	private long id;
	@Column(length = 8, nullable=false)
	private String code;
	@Column(length = 50, nullable=true)
	private String name;
	@Column(length = 50, nullable=true)
	private String mail;
	@Column(nullable=true)
	private Integer age;
	@Column(length = 1000, nullable=true)
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	

}
