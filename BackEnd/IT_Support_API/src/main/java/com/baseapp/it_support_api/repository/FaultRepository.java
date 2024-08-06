package com.baseapp.it_support_api.repository;

import com.baseapp.it_support_api.model.Fault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaultRepository extends JpaRepository<Fault,Long> {
}
