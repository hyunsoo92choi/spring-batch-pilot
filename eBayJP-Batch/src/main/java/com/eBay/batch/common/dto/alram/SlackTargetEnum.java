package com.eBay.batch.common.dto.alram;

import lombok.Getter;

/**
 * <pre>
 * com.eBay.batch.common.dto.alram_SlackTargetEnum.java
 * </pre>
 * @date : 2019. 5. 31. 오후 3:29:23
 * @author : hychoi
 */
@Getter
public enum SlackTargetEnum {
	
	// Enum Declaration : 발급받은 Web Hook URL, 채널 이름
	DEFAULT_CHANNEL("https://hooks.slack.com/services/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","jp-productdevelopment")
,	BATCH_LOG_CHANNEL("https://hooks.slack.com/services/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", "batch-logging");
    
	private final String webHookUrl;
    private final String channel;
    
    SlackTargetEnum(String webHookUrl, String channel) {
        this.webHookUrl = webHookUrl;
        this.channel = channel;
    }
}
