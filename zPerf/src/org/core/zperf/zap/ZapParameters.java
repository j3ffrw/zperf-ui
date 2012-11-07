/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf.zap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.core.zperf.AppLogger;

/**
 *
 * @author core
 */
public class ZapParameters {
    
    public ZapParameters(String workingDirectory){
        this.workingDirectory = workingDirectory;
    }
    
    public boolean isMinimumRequiredOptionSet(){
        return getSrcAddress() != null 
                && !srcAddress.isEmpty() 
                && getDstAddress() != null 
                && !dstAddress.isEmpty();
    }
    
    public List<String> getParameters(){
        
        if (isMinimumRequiredOptionSet()) {
            return buildParameters();
        }
        
        return null;
    }
    
    private List<String> buildParameters(){
    
        List<String> zapArguments = new ArrayList<String>();
        
        zapArguments.add(zapCommandLocation);
        
        if (srcControllerAddress != null) {
            zapArguments.add(" -s" + getSrcAddress() + "," + srcControllerAddress);
        }else{
            zapArguments.add("-s" + getSrcAddress());
        }
        
        if (dstControllerAddress != null) {
            zapArguments.add("-d" + getSrcAddress() + "," + dstControllerAddress);
        }else{
            zapArguments.add("-d" + getDstAddress());
        }
        
        if (secondsToRun != null) {
            zapArguments.add("-X" + secondsToRun);
        }
        
        if (invertConnection != null && invertConnection) {
            zapArguments.add("-i");
        }
        
        if (swapSourceAndDestination != null && swapSourceAndDestination) {
            zapArguments.add("-R");
        }
        
        if (multicastAddress != null) {
            zapArguments.add("-m" + multicastAddress);
        }
        
        if (payloadLength != null ) {
            zapArguments.add("-l" + payloadLength);
        }
        
        if (frameLength != null) {
            zapArguments.add("-f" + frameLength);
        }
        
        if (sampleSize != null) {
            zapArguments.add("-a" + sampleSize);
        }
        
        if (period != null) {
            zapArguments.add("-p" + period);
        }
        
        if (tosBits != null) {
            zapArguments.add("-q" + tosBits);
        }
        
        if (numberOfSamples != null) {
            zapArguments.add("-n" + numberOfSamples);
        }
        
        if (outstanding != null) {
            zapArguments.add("-o" + outstanding);
        }
        
        if (transmitRate != null) {
            zapArguments.add("-r" + transmitRate);
        }
        
        if (averageValueOfThroughput != null) {
            zapArguments.add("-w" + averageValueOfThroughput);
        }
        
        if (resultFilename != null && !resultFilename.isEmpty()) {
            zapArguments.add("-F" + workingDirectory + "/" + resultFilename);
        }else{
            Date now = new Date();
            resultFilename = "/zapResult_" + now.getTime() + "." + resultFileExtenstion;
            zapArguments.add("-F" + workingDirectory 
                    + resultFilename);
            
        }
        
        if (tag != null) {
            zapArguments.add("-T\"" + tag + "\"");
        }
        
        if (subTag != null) {
            zapArguments.add("-S\"" + subTag + "\"");
        }
        
        if (note != null) {
            zapArguments.add("-N\"" + note + "\"");
        }
        
        
        return zapArguments;
    }

    /**
     * @param srcAddress the srcAddress to set
     */
    public void setSrcAddress(String srcAddress) {
        this.srcAddress = srcAddress;
        AppLogger.appLogger(this.getClass().getName(), "Set src to " + srcAddress);
    }

    /**
     * @param dstAddress the dstAddress to set
     */
    public void setDstAddress(String dstAddress) {
        this.dstAddress = dstAddress;
        AppLogger.appLogger(this.getClass().getName(), "Set dst to " + dstAddress);
    }

    /**
     * @param secondsToRun the secondsToRun to set
     */
    public void setSecondsToRun(Long secondsToRun) {
        this.secondsToRun = secondsToRun;
        AppLogger.appLogger(this.getClass().getName(), "Set seconds to run (-X) to " + secondsToRun);
    }
    
    /**
     * @param invertConnection the invertConnection to set
     */
    public void setInvertConnection(Boolean invertConnection) {
        this.invertConnection = invertConnection;
    }

    /**
     * @param swapSourceAndDestination the swapSourceAndDestination to set
     */
    public void setSwapSourceAndDestination(Boolean swapSourceAndDestination) {
        this.swapSourceAndDestination = swapSourceAndDestination;
    }

    /**
     * @param srcControllerAddress the srcControllerAddress to set
     */
    public void setSrcControllerAddress(String srcControllerAddress) {
        this.srcControllerAddress = srcControllerAddress;
        AppLogger.appLogger(this.getClass().getName(), "Set src controller to " + srcControllerAddress);
    }

    /**
     * @param dstControllerAddress the dstControllerAddress to set
     */
    public void setDstControllerAddress(String dstControllerAddress) {
        this.dstControllerAddress = dstControllerAddress;
        AppLogger.appLogger(this.getClass().getName(), "Set dst controller to " + dstControllerAddress);
    }

    /**
     * @param multicastAddress the multicastAddress to set
     */
    public void setMulticastAddress(String multicastAddress) {
        this.multicastAddress = multicastAddress;
    }

    /**
     * @param sampleSize the sampleSize to set
     */
    public void setSampleSize(Long sampleSize) {
        this.sampleSize = sampleSize;
        period = null;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(Long period) {
        this.period = period;
        sampleSize = null;
    }

    /**
     * @param numberOfSamples the numberOfSamples to set
     */
    public void setNumberOfSamples(Long numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    /**
     * @param tosBits the tosBits to set
     */
    public void setTosBits(String tosBits) {
        this.tosBits = tosBits;
    }

    /**
     * @param payloadLength the payloadLength to set
     */
    public void setPayloadLength(Long payloadLength) {
        this.payloadLength = payloadLength;
    }

    /**
     * @param frameLength the frameLength to set
     */
    public void setFrameLength(Long frameLength) {
        this.frameLength = frameLength;
        payloadLength = null;
    }

    /**
     * @param outstanding the outstanding to set
     */
    public void setOutstanding(Long outstanding) {
        this.outstanding = outstanding;
    }
    
    
    /**
     * @param transmittedDataRate the transmittedDataRate to set
     */
    public void setTransmitRate(Long transmitRate) {
        this.transmitRate = transmitRate;
    }

    /**
     * @param averageValueOfThroughput the averageValueOfThroughput to set
     */
    public void setAverageValueOfThroughput(Long averageValueOfThroughput) {
        this.averageValueOfThroughput = averageValueOfThroughput;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @param sub the sub to set
     */
    public void setSubTag(String sub) {
        this.subTag = sub;
    }

    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the zapCommandLocation
     */
    public String getZapCommandLocation() {
        return zapCommandLocation;
    }

    /**
     * @param zapCommandLocation the zapCommandLocation to set
     */
    public void setZapCommandLocation(String zapCommandLocation) {
        this.zapCommandLocation = zapCommandLocation;
    }

    /**
     * @return the srcAddress
     */
    public String getSrcAddress() {
        return srcAddress;
    }

    /**
     * @return the dstAddress
     */
    public String getDstAddress() {
        return dstAddress;
    }
    
    /**
     * @return the zapResultFilename
     */
    public String getResultFilename() {
        return resultFilename;
    }

    /**
     * @param zapResultFilename the zapResultFilename to set
     */
    public void setResultFilename(final String zapResultFilename) {
        this.resultFilename = zapResultFilename + "." + resultFileExtenstion;
        AppLogger.appLogger(this.getClass().getName(), "Set result filename to " + resultFilename);
    }    
    
    private String srcAddress;
    private String dstAddress;
    private String srcControllerAddress;
    private String dstControllerAddress;
    private String multicastAddress;
    private Boolean invertConnection;
    private Boolean swapSourceAndDestination;
    private Long payloadLength = new Long(1472);
    private Long frameLength;
    private Long sampleSize;
    private Long period = new Long(50000);
    private String tosBits;
    private Long numberOfSamples = new Long(1000000);
    private Long outstanding;
    private Long transmitRate;
    private Long averageValueOfThroughput;    
    private String resultFilename;
    private String zapLogFilename;
    private String debuggingFilename;
    private String tag;
    private String subTag;
    private String note;    
    private Long secondsToRun = new Long(10);

    //private String zapCommandLocation = "/home/core/temp/zap/zapwireless-read-only/bin/linux/zap";
    private String zapCommandLocation = "zap";

    private String workingDirectory;
    
    private final String resultFileExtenstion = "zap";
    private final String logFileExtenstion = "log";
    
}
