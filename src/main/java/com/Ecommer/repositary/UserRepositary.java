package com.Ecommer.repositary;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommer.model.user;


@Repository
public interface UserRepositary extends JpaRepository<user, Long> {
    user findByEmail(String email);
}
