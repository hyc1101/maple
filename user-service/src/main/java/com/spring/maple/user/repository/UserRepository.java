package com.spring.maple.user.repository;

import com.spring.maple.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hyc
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
