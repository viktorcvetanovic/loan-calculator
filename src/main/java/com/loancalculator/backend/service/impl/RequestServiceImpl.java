package com.loancalculator.backend.service.impl;

import com.loancalculator.backend.entity.*;
import com.loancalculator.backend.repository.RequestRepository;
import com.loancalculator.backend.service.RequestService;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
	private final RequestRepository requestRepository;

	@Override
	public List<Request> findAll(Specification<Request> specification) {
		return requestRepository.findAll(specification);
	}

	@Override
	public Request findById(Integer requestId) {
		return requestRepository.findById(requestId)
				.orElseThrow(() -> new NoSuchElementException("RequestService.notFound"));
	}

	@Override
	public Request save(Request request) {
		return requestRepository.save(request);
	}

	@Override
	public Request update(Request request) {
		return requestRepository.save(request);
	}

	@Override
	public void deleteById(Integer requestId) {
		requestRepository.deleteById(requestId);
	}


}