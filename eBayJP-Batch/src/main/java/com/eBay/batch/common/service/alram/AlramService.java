package com.eBay.batch.common.service.alram;
/**
 * <pre>
 * com.eBay.batch.common.service_AlramService.java
 * </pre>
 * @date : 2019. 5. 31. 오후 2:50:20
 * @author : hychoi
 */
public interface AlramService {
	/* 모든 알람 서비스들이 공통으로 하는 행위를 정의 함. */
	public boolean alramSend(final String receive, final String alramTitle, final String alramMessage, final String somethingUwant);
}
