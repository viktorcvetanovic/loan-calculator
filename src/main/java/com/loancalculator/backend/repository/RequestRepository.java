package com.loancalculator.backend.repository;

import com.loancalculator.backend.entity.Request;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>, JpaSpecificationExecutor<Request> {

}