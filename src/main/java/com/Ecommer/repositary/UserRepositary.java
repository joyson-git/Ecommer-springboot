package com.Ecommer.repositary;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommer.model.User;


@Repository
public interface UserRepositary extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
