package org.currency.starter.controller;

import org.currency.starter.domain.CurrencyParam;
import org.currency.starter.domain.MC40200;
import org.currency.starter.service.MC40200Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "${api.base-path}", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Currency Controller" })
public class CurrencyController {
	private static final Logger LOG = LoggerFactory.getLogger(CurrencyController.class);

	@Autowired
	private MC40200Service currencyService;
	
	@Autowired
	public Jackson2JsonObjectMapper jackson2JsonObjectMapper;

	@ApiOperation(value = "Get all currencies")
	@GetMapping("/currencies")
	public Page<MC40200> getCurrencies(@RequestParam(name = "params", required = false) String reqParams) {

		LOG.info(" Getting Currencies .. ");
		
		CurrencyParam postParam = null;
		try {
			postParam = jackson2JsonObjectMapper.fromJson(reqParams, CurrencyParam.class);
		} catch (Exception e) {
			e.printStackTrace();
			postParam = new CurrencyParam();
		}

		return currencyService.findDataByCondition("Date", "ASC", postParam.getPage(), 5);
	}

	@ApiOperation(value = "Add new Currency")
	@PostMapping("/currencies")
	public MC40200 addCurrency(@ApiParam(required = true) @RequestBody MC40200 currency) {
		
		return currencyService.save(currency);
	}
}
