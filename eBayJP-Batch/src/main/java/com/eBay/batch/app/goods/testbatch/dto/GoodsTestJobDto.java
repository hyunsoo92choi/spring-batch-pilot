package com.eBay.batch.app.goods.testbatch.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.eBay.batch.app.goods.testbatch.dto_ GoodsTestJobDto.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsTestJobDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String goodsNo;
	private String goodsNm;
}
