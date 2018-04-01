package jp.co.corerd.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.corerd.springboot.entities.User;

@Repository
public interface MyDataRepository extends JpaRepository<User, Long>{

	// findByIdはCrudRepository<T, ID>で実装されているため不要
//	public Optional<User> findById(long id);
	
	// USER：× User：○ SQLだけど大文字小文字関係がある。
	// SELECT句は全項目指定しても上手くいかなかったので、無視したほうがよさそう。
	@Query("FROM User u WHERE name LIKE('%' || :name || '%') ORDER BY u.name")
	public List<User> findByName(
			@Param("name") String name);
}
