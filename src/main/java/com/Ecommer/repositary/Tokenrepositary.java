package com.Ecommer.repositary;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommer.model.Authencation;
import com.Ecommer.model.user;

@Repository
public interface Tokenrepositary  extends JpaRepository<Authencation, Integer>{

	Authencation findByUser(user user);
	
}
