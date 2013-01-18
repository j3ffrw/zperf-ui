/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.openide.util.Exceptions;

/**
 *
 * @author core
 */
public class ZapFileParser {
    
    
    //private double[] percentage = new double[110];
    
    public static final double[] percentage = {
        0.0,1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,
        11.0,12.0,13.0,14.0,15.0,16.0,17.0,18.0,19.0,
        20.0,21.0,22.0,23.0,24.0,25.0,26.0,27.0,28.0,29.0,
        30.0,31.0,32.0,33.0,34.0,35.0,36.0,37.0,38.0,39.0,
        40.0,41.0,42.0,43.0,44.0,45.0,46.0,47.0,48.0,49.0,
        50.0,51.0,52.0,53.0,54.0,55.0,56.0,57.0,58.0,59.0,
        60.0,61.0,62.0,63.0,64.0,65.0,66.0,67.0,68.0,69.0,
        70.0,71.0,72.0,73.0,74.0,75.0,76.0,77.0,78.0,79.0,
        80.0,81.0,82.0,83.0,84.0,85.0,86.0,87.0,88.0,89.0,
        90.0,91.0,92.0,93.0,94.0,95.0,96.0,97.0,98.0,99.0,
        99.1,99.2,99.3,99.4,99.5,99.6,99.7,99.8,99.9,100.0};
    
    //List<double[]> throughputList = new ArrayList<double[]>();
    
    //private List<String> traceTag = new ArrayList<String>();
    
    
    private List<ZapResult> zapResults = new ArrayList<ZapResult>();
    
    private int resultCntr = 0;
    
     private BufferedReader  input;

    private String filename;
    
    public ZapFileParser(String filePath){
                
        this.filename = filePath;
        
        try {
            input = new BufferedReader(new FileReader(filePath));
            
            String input_line;
            
            boolean validZapFile = false;
            
            if((input_line = input.readLine()) != null){
                
                if(input_line.indexOf("Zap Version") == 0){
                    
                    validZapFile = true;                   
                    
                }
                
                ZPerfUiTopComponent.appendOptionalToOutput(input_line);
            }
            
            if(validZapFile){
                               
                while ((input_line = input.readLine()) != null){
                
                    parseLine(input_line);
                
                }
            }
            
            input.close();
            
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    
    }
    
    private void parseLine(String line){
        
        Pattern pattern = Pattern.compile(",");
        
        String[] results = pattern.split(line.trim());
            
        // check if tokenized string is of correct length
        if (results.length != 130) {
            Exceptions.printStackTrace(new Exception("Incorrect tokenized length at trace #" + resultCntr));
            return;
        }
        
        ZapResult zapResult = new ZapResult();
        
        zapResult.setFilename(results[1]);
        
        zapResult.setProtocol(results[2]);
        
        if (results[3].toString().indexOf("Off") == 0) {
            zapResult.setInvertOpen(false);
        } else {
            zapResult.setInvertOpen(true);
        }
        
        zapResult.setTxIP(results[4]);
        
        zapResult.setRxIP(results[5]);
        
        if (results[6].toString().indexOf("Off") == 0) {
            zapResult.setMulticast(false);
        } else {
            zapResult.setMulticast(true);
        }
        
        zapResult.setTermsOfService(
                Integer.parseInt(results[7].replaceAll("h", ""),16));
        
        zapResult.setSamples(Long.parseLong(results[8]));
        
        zapResult.setSampleSize(Long.parseLong(results[9]));
        
        zapResult.setPayloadLength(Long.parseLong(results[10]));
        
        zapResult.setPayloadTransmitDelay(Long.parseLong(results[11]));
        
        zapResult.setPayloadsReceived(Long.parseLong(results[12]));
        
        zapResult.setPayloadsDropped(Long.parseLong(results[13]));
        
        zapResult.setPayloadsOutOfOrder(Long.parseLong(results[15]));
                
        try {
            DateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy");
            Date dateTaken = df.parse(results[16]);
            
            zapResult.setTimeTaken(dateTaken);
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        zapResult.setNotes(results[17]);
        
        zapResult.setTag(results[18]);
        
        zapResult.setSubTag(results[19]);
        
        int offset = 20;
        
        for (int cntr = 0; cntr <= 109; cntr++) {
            
            zapResult.getThroughput()[cntr] = 
                    Double.parseDouble(results[cntr+offset]);
        }
        
        zapResult.setResultTitle(++resultCntr);
        
        getZapResults().add(zapResult);        
 
    }
    
    public String getFileName(){
        return filename;
    }
  
    /**
     * @return the zapResults
     */
    public List<ZapResult> getZapResults() {
        return zapResults;
    }
}
