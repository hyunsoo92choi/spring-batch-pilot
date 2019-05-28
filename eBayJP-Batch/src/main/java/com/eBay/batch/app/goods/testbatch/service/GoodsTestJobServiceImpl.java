package com.eBay.batch.app.goods.testbatch.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eBay.batch.app.goods.testbatch.dao.GoodsTestSearchDAO;
import com.eBay.batch.app.goods.testbatch.dto.GoodsTestJobDto;
import com.eBay.batch.config.EnvConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * com.eBay.batch.app.goods.testbatch.service_ GoodsTestJobServiceImpl.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
@Slf4j
@Service
public class GoodsTestJobServiceImpl implements GoodsTestJobService {

	private static final Logger logger = LoggerFactory.getLogger(GoodsTestJobServiceImpl.class);
	@Autowired 
	GoodsTestSearchDAO goodsTestSearchDAO;
	
	@Override
	public List<GoodsTestJobDto> getGoodsList(GoodsTestJobDto goodsTestJobDto) {
		logger.warn("############ Service start #############");
		List<GoodsTestJobDto> testJobResult = goodsTestSearchDAO.getGoodsList(goodsTestJobDto);
		logger.warn("############ Service 5 end #############");
		return testJobResult;
	}

}
