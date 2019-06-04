package com.eBay.batch.common.batchlog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.eBay.batch.common.consts.BatchConst;

/**
 * <pre>
 * com.eBay.batch.common.batchlog_ BatchLog.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class BatchLog {
	
	private int totalCnt;
    private int errorCnt;
    private boolean showResult;
    private boolean hasError;   
    private List<String> logs;
    
	/**
	 *  Constructor
	 */
	public BatchLog() {
        hasError = false;
        logs = new ArrayList<>();
        totalCnt = 0;
        errorCnt = 0;
    }
	
	/**
	 * <pre>
	 * 1. 개요 : 로그 추가
	 * 2. 처리내용 : 로그 내용을 추가한다.
	 * </pre>
	 * @Method Name : add
	 * @date : 2019. 5. 27.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 27.		hychoi				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param log
	 * @return BatchLog
	 */ 	
	public BatchLog add(String log) {
        if (logs == null) {
            logs = new ArrayList<>();
        }

        this.logs.add(log);

        return this;
    }
	
	/**
	 * <pre>
	 * 1. 개요 : Error 발견 시 
	 * 2. 처리내용 : hasError flag를 true로 변경 후 errorCnt 증가
	 * </pre>
	 * @Method Name : foundError
	 * @date : 2019. 5. 27.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 27.		hychoi				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @return BatchLog
	 */ 	
	public BatchLog foundError() {
        hasError = true;
        errorCnt++;
        return this;
    }
	
	/**
	 * <pre>
	 * 1. 개요 : 총 갯수를 추가 한다
	 * 2. 처리내용 : 
	 * </pre>
	 * @Method Name : addTotalCnt
	 * @date : 2019. 5. 27.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 27.		hychoi				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param cnt
	 * @return BatchLog
	 */ 	
	public BatchLog addTotalCnt(int cnt){
        totalCnt += cnt;
        return this;
    }
	
	public int getTotalCnt() {
        return totalCnt;
    }
	
	public int getErrorCnt() {
        return errorCnt;
    }
	
	public boolean getShowResult() {
        return showResult;
    }
	
	public BatchLog showResult() {
        showResult = true;
        return this;
    }
	
	@Override
    public String toString() {
        if (logs == null || logs.isEmpty()) {
            return "";
        }

        return logs.stream().collect(Collectors.joining(BatchConst.BATCH_LOG_SEPERATOR));
    }

    public boolean isHasError() {
        return hasError;
    }
}
