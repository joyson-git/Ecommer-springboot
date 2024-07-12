/*package com.Ecommer.repositary;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommer.model.User;
import com.Ecommer.model.WishList;


@Repository
public interface wishlistrepositary  extends JpaRepository<WishList, Integer>{

	
	List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
*/