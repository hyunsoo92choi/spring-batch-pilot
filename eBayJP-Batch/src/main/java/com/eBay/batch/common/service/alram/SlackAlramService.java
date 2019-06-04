package com.eBay.batch.common.service.alram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eBay.batch.common.consts.SlackConst;
import com.eBay.batch.common.dto.alram.SlackAttachments.SlackAttachment;
import com.eBay.batch.common.dto.alram.SlackTargetEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * com.eBay.batch.common.service_SlackAlramService.java
 * </pre>
 * @date : 2019. 5. 31. 오후 2:52:04
 * @author : hychoi
 */
@Service
public class SlackAlramService implements AlramService {
	
	private static final Logger logger = LoggerFactory.getLogger(SlackAlramService.class);
	/**
	 * <pre>
	 * 1. 개요 : Slack Incomming WebHook 방식으로 알람을 전송한다.
	 * 2. 처리내용 : 알람 메시지를 수신 할 Channel 로 메시지를 전송한다. 	
	 * </pre>
	 * @Method Name : alramSend
	 * @date : 2019. 5. 31.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 31.		hychoi				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param receive (CHANNELS --- See SlackConst)
	 * @param alramTitle (알람제목 Ex BatchJob Name Start, Complete, Fail ) 
	 * @param alramMessage 메시지 내용
	 * @param templatePath 파일 경로 or stackTrace 등  
	 * @return
	 */ 	
	@Override
	public boolean alramSend(String receive, String alramTitle, String alramMessage, String somethingUwant) {

		
		SlackAttachment attachment = new SlackAttachment();
		StringBuilder sb = new StringBuilder();
		
		sb.append(alramMessage);
		
		SlackTargetEnum target = getSlackTargetEnum(receive);
		attachment.setUserName("test");
		attachment.setTitle(alramTitle);
		attachment.setText(sb.toString());
		attachment.setIconEmoji(":stuck_out_tongue_winking_eye:");
		attachment.setColorCoding(true);
		
		return send(target, attachment);
	}
	
	public boolean send(SlackTargetEnum target, SlackAttachment object) {
		RestTemplate restTemplate  = new RestTemplate();
		
	    try {
	        restTemplate.postForObject(target.getWebHookUrl(), object, String.class);

	        return true;
	    } catch (Exception e) {
	    	logger.error("Occur Exception: {}", e);
	        return false;
	    }
		
	}
	
	/**
	 * <pre>
	 * 1. 개요 : alramSend method 호출 시 param1에 매핑되는 SlackTargetEnum 을 얻는다. 
	 * 2. 처리내용 : receive param을 이용하여 해당하는 SlackTargetEnum 반환
	 * </pre>
	 * @Method Name : getSlackTargetEnum
	 * @date : 2019. 5. 31.
	 * @author : hychoi
	 * @history : 
	 *	-----------------------------------------------------------------------
	 *	변경일				작성자						변경내용  
	 *	----------- ------------------- ---------------------------------------
	 *	2019. 5. 31.		hychoi				최초 작성 
	 *	-----------------------------------------------------------------------
	 * 
	 * @param receive
	 * @return SlackTargetEnum
	 */ 	
	private SlackTargetEnum getSlackTargetEnum(String receive) {
		
		if (SlackConst.BATCH_LOG.equals(receive))
			return SlackTargetEnum.BATCH_LOG_CHANNEL;
		
		return SlackTargetEnum.DEFAULT_CHANNEL;
	}
}
