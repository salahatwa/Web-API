package org.currency.starter.service;

import javax.transaction.Transactional;

import org.currency.starter.domain.MC40200;
import org.currency.starter.repository.MC40200Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MC40200ServiceImpl implements MC40200Service {

	@Autowired
	private MC40200Repository repo;

	@Override
	public MC40200 save(MC40200 MC40200) {
		MC40200 = repo.save(MC40200);
		return MC40200;
	}

	public Page<MC40200> findDataByCondition(String orderBy, String direction, int page, int size) {
		Sort sort = null;
		if (direction.equals("ASC")) {
			sort = new Sort(new Sort.Order(Direction.ASC, orderBy));
		}
		if (direction.equals("DESC")) {
			sort = new Sort(new Sort.Order(Direction.DESC, orderBy));
		}
		Pageable pageable = new PageRequest(page, size, sort);
		Page<MC40200> data = repo.findAll(pageable);
		return data;
	}
}
