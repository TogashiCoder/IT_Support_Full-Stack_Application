package com.baseapp.it_support_api.repository;

import com.baseapp.it_support_api.model.Entity.Admin;
import com.baseapp.it_support_api.model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {


}
