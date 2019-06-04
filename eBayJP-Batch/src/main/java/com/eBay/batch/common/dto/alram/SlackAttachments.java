package com.eBay.batch.common.dto.alram;

import java.lang.reflect.Field;
import java.util.List;

import com.eBay.batch.common.dto.alram.SlackAttachments.SlackAttachment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <pre>
 * com.eBay.batch.common.dto.alram_SlackAttachments.java
 * </pre>
 * @date : 2019. 5. 31. 오후 3:30:14
 * @author : hychoi
 */
@Getter
@Setter
@NoArgsConstructor
public class SlackAttachments {
	
	private List<SlackAttachment> attachments;
    
	@Builder
    public SlackAttachments(List<SlackAttachment> attachments) {
		this.attachments = attachments;
    }
	@Setter
	@Getter
	@NoArgsConstructor
	public static class SlackAttachment {
		private String userName;
		private String fallBack;
	    private String color;
	    private String preText;
	    private String authorName;
	    private String authorLink;
	    private String authorIcon;
	    private String title;
	    private String titleLink;
	    private String text;
	    private String imageUrl;
	    private String thumbUrl;
	    private String footer;
	    private String footerIcon;
	    private String iconEmoji;
	    private Boolean colorCoding;
	    private Long ts;
	    
	    private List<Field> fields;
	    
	    @Builder
	    public SlackAttachment(String userName, String fallback, String color, String pretext
	    					 , String author_name, String author_link, String author_icon, String title
	    					 , String title_link, String text, String image_url, String thumb_url
	    					 , String footer, String footer_icon, String icon_emoji, Boolean color_coding
	    					 , Long ts, List<Field> fields) {
	        this.userName = userName;
	    	this.fallBack = fallback;
	        this.color = color;
	        this.preText = pretext;
	        this.authorName = author_name;
	        this.authorLink = author_link;
	        this.authorIcon = author_icon;
	        this.title = title;
	        this.titleLink = title_link;
	        this.text = text;
	        this.imageUrl = image_url;
	        this.thumbUrl = thumb_url;
	        this.footer = footer;
	        this.footerIcon = footer_icon;
	        this.iconEmoji = icon_emoji;
	        this.colorCoding = color_coding;
	        this.ts = ts;
	        this.fields = fields;
	    }
	}
}
