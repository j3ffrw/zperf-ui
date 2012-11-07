/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.openide.util.Exceptions;

/**
 *
 * @author core
 */
public class ZapCCDFParser {
    
    
    private double[] percentage = new double[110];
    
    List<double[]> throughputList = new ArrayList<double[]>();
    
    private List<String> traceTag = new ArrayList<String>();
    
    public ZapCCDFParser(String filePath){
                
        this.filename = filePath;
        
        try {
            input = new BufferedReader(new FileReader(filePath));
            
            String input_line;
            
            boolean validZapFile = false;
            
            Pattern pattern = Pattern.compile(",");
            
            if((input_line = input.readLine()) != null){
                if(input_line.indexOf("Zap Version") == 0){
                    validZapFile = true;                   
                    
                    String[] results = pattern.split(input_line.trim());

                    for (int cntr = 0; cntr < percentage.length; cntr++) {
                        String val = results[cntr+20];
                        percentage[cntr] = Double.parseDouble(val.substring(0, val.length()-1));
                    }                    
                }
                ZPerfUiTopComponent.appendOptionalToOutput(input_line);
            }
            
            if(validZapFile){
                
//                String[] results = pattern.split(input_line.trim());
//
//                for (int cntr = 0; cntr < throughput.length; cntr++) {
//                    throughput[cntr] = Double.parseDouble(results[cntr+20]);
//                }
                
                while ((input_line = input.readLine()) != null){
                    parseLine(input_line);
                }
//                parseLine(input_line);
//                ZPerfUiTopComponent.appendOptionalToOutput(input_line);
            }
            
            input.close();
            
        } catch (FileNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    
    }
    
    private void parseLine(String line){

        double[] throughput = new double[110];
        
        Pattern pattern = Pattern.compile(",");
        
        String[] results = pattern.split(line.trim());

        getTraceTag().add(results[18]);
        
        for (int cntr = 0; cntr < throughput.length; cntr++) {
            throughput[cntr] = Double.parseDouble(results[cntr+20]);
        }
        
        throughputList.add(throughput);
    }
    
    private BufferedReader  input;

    private String filename;
    /**
     * @return the throughput
     */
    public List<double[]> getThroughput() {
        return throughputList;
    }
    
    public double[] getPercentage() {
        return percentage;
    }
    
    public String getFileName(){
        return filename;
    }

    /**
     * @return the traceTag
     */
    public List<String> getTraceTag() {
        return traceTag;
    }
}
