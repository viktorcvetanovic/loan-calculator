package com.loancalculator.backend.repository;

import com.loancalculator.backend.entity.SimpleLoan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleLoanRepository extends JpaRepository<SimpleLoan, Integer>, JpaSpecificationExecutor<SimpleLoan> {

}