package com.crud_operation.usermanagment.repository;

import com.crud_operation.usermanagment.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<OurUsers , Long> {
    Optional<OurUsers> findByEmail(String email);
}
