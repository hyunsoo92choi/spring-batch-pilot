package com.eBay.batch.common.batchlog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * <pre>
 * com.eBay.batch.common.batchlog_ BatchLogStorage.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
/**
 * <pre>
 * com.eBay.batch.common.batchlog_ BatchLogStorage.java
 * </pre>
 * @date : 2019. 5. 27.
 * @author : hychoi
 */
@Component
public class BatchLogStorage {
	
	private Map<String, BatchLog> batchLogMap;
	
	public BatchLogStorage() {
        batchLogMap = new HashMap<>();
    }
	
	/**
	 * <pre>
	 * 1. 개요 : initialize
	 * 2. 처리내용 : 초기화
	 * </pre>
	 * @Method Name : init
	 * @date : 2019. 5. 27.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 27.		hychoi				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param jobName
	 */ 	
	public void init(String jobName) {
        remove(jobName);
    }
	
	/**
	 * <pre>
	 * 1. 개요 : remove method
	 * 2. 처리내용 : BatchLogMap 의 jobName을  지운다 
	 * </pre>
	 * @Method Name : remove
	 * @date : 2019. 5. 27.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 27.		hychoi				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param jobName
	 */ 	
	public void remove(String jobName) {
        if (batchLogMap.containsKey(jobName)) {
            batchLogMap.remove(jobName);
        }
    }
	
	/**
	 * <pre>
	 * 1. 개요 : Constructor
	 * 2. 처리내용 : 디폴트 생성자 이외의 jobName를 파라미터로 받아 BatchLog를 생성 함.
	 * </pre>
	 * @Method Name : batchLog
	 * @date : 2019. 5. 27.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 27.		hychoi				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param jobName
	 * @return BatchLog
	 */ 	
	public BatchLog batchLog(String jobName) {
     
		if (!batchLogMap.containsKey(jobName)) {
            
			BatchLog batchLog = new BatchLog();
            batchLogMap.put(jobName, batchLog);
            
            return batchLog;
        }

        return batchLogMap.get(jobName);
    }
	
	public boolean containsKey(String jobName) {
        return batchLogMap.containsKey(jobName);
    }
}
