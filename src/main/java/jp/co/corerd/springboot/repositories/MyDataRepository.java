package jp.co.corerd.springboot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.corerd.springboot.entities.User;

@Repository
public interface MyDataRepository extends JpaRepository<User, Long>{

	// findByIdはCrudRepository<T, ID>で実装されているため不要
//	public Optional<User> findById(long id);
	
	public Optional<User> findByName(String name);
}
