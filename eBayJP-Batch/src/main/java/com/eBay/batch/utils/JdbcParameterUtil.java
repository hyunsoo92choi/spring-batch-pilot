package com.eBay.batch.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.StatementCreatorUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.jdbc.core.namedparam.ParsedSql;

/**
 * <pre>
 * com.eBay.batch.utils_ JdbcParameterUtil.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public abstract class JdbcParameterUtil {

private static final Logger logger = LoggerFactory.getLogger(JdbcParameterUtil.class);
	
	public static String getParameterValueAddedSql(String query, Map parameterValues) {
		String parameterValueAddedSql = query;
		try {
			ParsedSql parsedSql = NamedParameterUtils.parseSqlStatement(query);
			Object[] arguments = NamedParameterUtils.buildValueArray(parsedSql, new MapSqlParameterSource(parameterValues), null);
			String sql = NamedParameterUtils.substituteNamedParameters(parsedSql, null);
			parameterValueAddedSql = getParameterValueAddedSql(sql, arguments);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("failed to transform parameter value added sql string", e);
			}
		}
		return parameterValueAddedSql;
	}
	
	public static String getParameterValueAddedSql(String query, Object[] arguments) {
		StringBuffer sb = new StringBuffer(query.length());
		sb.append("execute sql [\n");
		int offset = 0;
		int seq = 0;
		int i; 
		while ((i = query.indexOf('?', offset)) >= 0) 
		{ 
			sb.append(query.substring(offset, i));
			switch (StatementCreatorUtils.javaTypeToSqlParameterType(arguments[seq].getClass())) {
				case 2:
				case 3:
				sb.append(arguments[seq]);
				break;
				default:
				sb.append("'").append(arguments[seq]).append("'");
			}
		
			offset = i + 1;
			seq++;
		}
		if (offset <= query.length()) {
			sb.append(query.substring(offset));
		}
		sb.append("\n]");
		return sb.toString();
	}
}
