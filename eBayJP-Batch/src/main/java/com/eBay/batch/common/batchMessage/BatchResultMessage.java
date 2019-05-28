package com.eBay.batch.common.batchMessage;

import lombok.Builder;
import lombok.Data;

/**
 * <pre>
 * com.eBay.batch.common.batchMessage_ BatchResultMessage.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
@Data
@Builder
public class BatchResultMessage {
	
	private String code;
    private String message;

    public BatchResultMessage() {
    	this.code = "0";
    	this.message = "SUCCESS";
    }

    public BatchResultMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
