package com.eBay.batch.utils;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * <pre>
 * com.eBay.batch.utils_ AssertUtil.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class AssertUtil extends Assert {
	
	public static void hasLength(Object[] array, int length) {
		hasLength(array, length, "[Assertion failed] - this array length must be [" + length + "]");
	}
	
	public static void hasLength(Object[] array, int length, String message) {
		if ((ObjectUtils.isEmpty(array)) || (array.length != length)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void greaterLength(Object[] array, int length) {
		greaterLength(array, length, "[Assertion failed] - this array length must be greater than " + length);
	}
	
	public static void greaterLength(Object[] array, int length, String message) {
		if ((ObjectUtils.isEmpty(array)) || (array.length < length)) {
			throw new IllegalArgumentException(message);
		}
	}
}
