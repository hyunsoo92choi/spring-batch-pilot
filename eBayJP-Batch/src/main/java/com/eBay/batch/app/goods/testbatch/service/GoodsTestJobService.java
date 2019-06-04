package com.eBay.batch.app.goods.testbatch.service;

import java.util.List;

import com.eBay.batch.app.goods.testbatch.dto.GoodsTestJobDto;
import com.eBay.batch.app.goods.testbatch.dto.RequestGoodsTestJobDto;

/**
 * <pre>
 * com.eBay.batch.app.goods.testbatch.service_ GoodsTestJobService.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public interface GoodsTestJobService {
	public List<GoodsTestJobDto> getGoodsList(RequestGoodsTestJobDto requestGoodsTestJobDto);
}
