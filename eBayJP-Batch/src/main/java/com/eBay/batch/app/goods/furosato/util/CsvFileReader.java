package com.eBay.batch.app.goods.furosato.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.eBay.batch.app.goods.furosato.dto.JbtCsvDto;

/**
 * <pre>
 * com.eBay.batch.app.goods.furosato.util_CsvFileReader.java
 * </pre>
 * @date : 2019. 6. 4. 오후 2:42:11
 * @author : hychoi
 */
public class CsvFileReader {
	
	/** This parse method requires the CSV file to have a header row */
	public static List<JbtCsvDto> parse(String csvFilePath) {
	    // TODO accept Reader or maybe InputStream rather than file path
	    File f = new File(csvFilePath);

	    List<JbtCsvDto> records = new ArrayList<>();
	    StringBuilder sb = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(
	    		  new InputStreamReader(new FileInputStream(f), "UTF-8"));) {
	        // get the header fields
	        String line = br.readLine();
	        String[] headers = line.split(",");
	        
	        String[] fields = new String[headers.length];
	        // iterate through record fields
	        int recordNum = 0;
	        while ((line = br.readLine()) != null) {
	        	sb.append(line);
	        	sb.append(System.lineSeparator());
	        }
	        records = splitCSV(sb.toString(), ',', '"', headers.length);
	    } catch (IOException e) {
	        // TODO trouble reading the file
	    } catch (IllegalArgumentException e) {
	        // TODO error while parsing the file
	    }

	    return records;
	}
	
	
    /**
     * <pre>
     * 1. 개요 : CSV 파일 내용을 컬럼 단위로 Split 한다. 
     * 2. 처리내용 : 
     * </pre>
     * @Method Name : splitCSV
     * @date : 2019. 6. 5.
     * @author : hychoi
     * @history : 
     *	-----------------------------------------------------------------------
     *	변경일				작성자						변경내용  
     *	----------- ------------------- ---------------------------------------
     *	2019. 6. 5.		hychoi				최초 작성 
     *	-----------------------------------------------------------------------
     * 
     * @param input
     * @param separator
     * @param single quote or double quotes
     * @param headerSize
     * @return
     * @throws IOException
     */ 	
    public static List<JbtCsvDto> splitCSV(String input, char separator, char quote, int headerSize) throws IOException{       
        
    	
    	String[] fields = new String[headerSize];
        List<JbtCsvDto> list = new ArrayList<JbtCsvDto>();
        final StringBuilder sb = new StringBuilder();
        
        int cnt = 0;
        boolean quoted=false;
        
        for (int i = 0, len= input.length(); i < len; i++) {
        	
            final char c = input.charAt(i);
            
            if (c == quote) {
            	//2xdouble quote in quoted
            	if( quoted   && i<len-1 && input.charAt(i+1) == quote ) { 
                    sb.append(c);
                    //skip
                    i++;
                }else {
                    
                	if (quoted) {
                        //다음 심벌은 반드시 separator 또는 EOL(See RFC 4180)
                        if (i==len-1 || input.charAt(i+1) == separator) {
                            quoted = false;
                            continue;
                        }
                    } else {
                    	//not quoted
                        if (sb.length()==0){
                            quoted=true;
                            continue;
                        }
                    }
                	// 만약 필드가 double quotes로 묶이지 않으면 큰 따옴표가 필드 안에 나타나지 않을 수 있음
                    sb.append(c);                   
                }
            } else if (c == separator && !quoted) {
            	// quoted 상태가 아니고 구분자를 만났다면 필드에 저장 후 StringBuilder 초기화
            	fields[cnt] = sb.toString();
                sb.setLength(0);
                cnt++;                
            } else {
            	/* 아무것도 아닐때 */
                sb.append(c);
                
                /* 마지막 필드 후 라인을 바꿔주는 작업
                 * 계속 카운트는 체크를 하고 
                 */
                if (cnt == headerSize-1) {
                	if ( sb.toString().endsWith("\r\n")) {
                		fields[cnt] = sb.toString();
                		list.add(new JbtCsvDto(fields));
                    	cnt = 0;
                    	fields = new String[headerSize];
                    	sb.setLength(0);
                	}
                }
            }
        }
        return list;
    }
}
