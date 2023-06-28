package com.loancalculator.backend.service;

import com.loancalculator.backend.entity.*;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

public interface RequestService {

	List<Request> findAll(Specification<Request> specification);

	Request save(Request request);

	Request update(Request request);

	Request findById(Integer requestId);

	void deleteById(Integer requestId);

}