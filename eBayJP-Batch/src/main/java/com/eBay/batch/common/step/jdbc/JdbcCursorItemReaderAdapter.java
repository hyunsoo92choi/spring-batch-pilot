package com.eBay.batch.common.step.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCreatorUtils;

import com.eBay.batch.utils.JdbcParameterUtil;

/**
 * <pre>
 * com.eBay.batch.common.step.jdbc_ JdbcCursorItemReaderAdapter.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public class JdbcCursorItemReaderAdapter <T> extends JdbcCursorItemReader<T> implements PreparedStatementSetter{
	
	private Map<String, Object> parameterValues;
	private Object[] arguments;
	private RowMapper rowMapper;
	
	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		
		if (this.arguments != null) {
			
			for (int i = 0; i < this.arguments.length; i++) {
				Object arg = this.arguments[i];

				if (this.log.isDebugEnabled()) {
					this.log.debug("setParametervalue(" + (i + 1) + ", " + arg + ")");
				}
				
				StatementCreatorUtils.setParameterValue(ps, ++i, StatementCreatorUtils.javaTypeToSqlParameterType(arg.getClass()), arg);
			}
		}
		
	}
	
	protected void openCursor(Connection conn) {
		if (this.log.isDebugEnabled()) {
		this.log.debug(JdbcParameterUtil.getParameterValueAddedSql(getSql(), this.arguments));
		}
		super.openCursor(conn);
	}

	public void setRowMapper(RowMapper rowMapper) {
		this.rowMapper = rowMapper;
		super.setRowMapper(rowMapper);
	}

}
