package org.currency.starter.service;

import org.currency.starter.domain.MC40200;
import org.springframework.data.domain.Page;

public interface MC40200Service {

	public MC40200 save(MC40200 MC40200);
	
	public Page<MC40200> findDataByCondition(String orderBy, String direction, int page, int size);

}
