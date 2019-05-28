package com.eBay.batch.common.step.support;

import org.springframework.batch.core.step.AbstractStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.util.ExecutionContextUserSupport;
import org.springframework.util.Assert;

/**
 * <pre>
 * com.eBay.batch.common.step.support_ AbstractItemStreamReader.java
 * </pre>
 * @date : 2019. 5. 27. 
 * @author : hychoi
 */
public abstract class AbstractItemStreamReader <T> extends AbstractStep implements ItemReader<T>, ItemStream {

	private static final String READ_COUNT = "read.count";
	private static final String READ_COUNT_MAX = "read.count.max";
	private int currentItemCount = 0;
	
	private int maxItemCount = Integer.MAX_VALUE;
	
	private ExecutionContextUserSupport ecSupport = new ExecutionContextUserSupport();
	
	private boolean saveState = true;
	
	protected abstract T doRead()
	 throws Exception;
	
	protected abstract void doOpen()
	 throws Exception;
	
	protected abstract void doClose()
	 throws Exception;
	
	protected void jumpToItem(int itemIndex) throws Exception {
		for (int i = 0; i < itemIndex; i++) {
			read();
		}
	}
	
	public final T read() throws Exception, UnexpectedInputException, ParseException {
		if (this.currentItemCount >= this.maxItemCount) {
			return null;
		}
		this.currentItemCount += 1;
		return (T)doRead();
	}
	
	protected int getCurrentItemCount() {
		return this.currentItemCount;
	}
	
	public void setCurrentItemCount(int count) {
		this.currentItemCount = count;
	}
	
	public void setMaxItemCount(int count) {
		this.maxItemCount = count;
	}
	
	public void close() throws ItemStreamException {
		this.currentItemCount = 0;
		try {
			doClose();
		} catch (Exception e) {
			throw new ItemStreamException("Error while closing item reader", e);
		}
	}
	
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		
		try {
			doOpen();
		} catch (Exception e) {
			throw new ItemStreamException("Failed to initialize the reader", e);
		}
		
		if (executionContext.containsKey(this.ecSupport.getKey("read.count.max"))) {
			this.maxItemCount = executionContext.getInt(this.ecSupport
			 .getKey("read.count.max"));
		}
		
		if (executionContext.containsKey(this.ecSupport.getKey("read.count"))) {
			int itemCount = executionContext.getInt(this.ecSupport
			 .getKey("read.count"));
		
		if (itemCount < this.maxItemCount) {
			try {
				jumpToItem(itemCount);
			} catch (Exception e) {
				throw new ItemStreamException(
				"Could not move to stored position on restart", e);
				}
			}
			this.currentItemCount = itemCount;
		}
	}
	
	
	public void update(ExecutionContext executionContext)
	 throws ItemStreamException {
		
		if (this.saveState) {
			Assert.notNull(executionContext, "ExecutionContext must not be null");
			executionContext.putInt(this.ecSupport.getKey("read.count"), 
				this.currentItemCount);
			if (this.maxItemCount < Integer.MAX_VALUE) {
				executionContext.putInt(this.ecSupport.getKey("read.count.max"),
				this.maxItemCount);
		   }
		}
	}
	
	public void setName(String name) {
		this.ecSupport.setName(name);
	}
	
	public void setSaveState(boolean saveState) {
		this.saveState = saveState;
	}
}
