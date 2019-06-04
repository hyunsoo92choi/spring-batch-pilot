package com.eBay.batch.app.goods.testbatch.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.eBay.batch.app.goods.testbatch.dto.GoodsTestJobDto;
import com.eBay.batch.app.goods.testbatch.dto.RequestGoodsTestJobDto;

/**
 * <pre>
 * com.eBay.batch.app.goods.testbatch.dao_ GoodsTestSearchDAO.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
@Mapper
@Repository
public interface GoodsTestSearchDAO {
	public List<GoodsTestJobDto> getGoodsList(RequestGoodsTestJobDto requestGoodsTestJobDto);
}
