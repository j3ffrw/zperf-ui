/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf;

import java.lang.reflect.Field;
import java.util.Date;
import org.openide.util.Exceptions;

/**
 *
 * @author Jefferson Fermo <jefffermo@yahoo.com>
 */
public class ZapResult {
    
    private String resultTitle;
    
    private String filename;
    
    private String protocol;
    
    private boolean invertOpen;
    
    private String txIP;
    
    private String rxIP;
    
    private boolean multicast;
    
    private int termsOfService;
    
    private long samples;
    
    private long sampleSize;
    
    private long payloadLength;
            
    private long payloadTransmitDelay;
    
    private long payloadsReceived;
    
    private long payloadsDropped;
    
    private long payloadsRepeated;
    
    private long payloadsOutOfOrder;
    
    private Date timeTaken;
    
    private String notes;
    
    private String tag;
    
    private String subTag;
    
    private double[] throughput = new double[110];

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the invertOpen
     */
    public boolean isInvertOpen() {
        return invertOpen;
    }

    /**
     * @param invertOpen the invertOpen to set
     */
    public void setInvertOpen(boolean invertOpen) {
        this.invertOpen = invertOpen;
    }

    /**
     * @return the txIP
     */
    public String getTxIP() {
        return txIP;
    }

    /**
     * @param txIP the txIP to set
     */
    public void setTxIP(String txIP) {
        this.txIP = txIP;
    }

    /**
     * @return the rxIP
     */
    public String getRxIP() {
        return rxIP;
    }

    /**
     * @param rxIP the rxIP to set
     */
    public void setRxIP(String rxIP) {
        this.rxIP = rxIP;
    }

    /**
     * @return the multicast
     */
    public boolean isMulticast() {
        return multicast;
    }

    /**
     * @param multicast the multicast to set
     */
    public void setMulticast(boolean multicast) {
        this.multicast = multicast;
    }

    /**
     * @return the termsOfService
     */
    public int getTermsOfService() {
        return termsOfService;
    }

    /**
     * @param termsOfService the termsOfService to set
     */
    public void setTermsOfService(int termsOfService) {
        this.termsOfService = termsOfService;
    }

    /**
     * @return the samples
     */
    public long getSamples() {
        return samples;
    }

    /**
     * @param samples the samples to set
     */
    public void setSamples(long samples) {
        this.samples = samples;
    }

    /**
     * @return the sampleSize
     */
    public long getSampleSize() {
        return sampleSize;
    }

    /**
     * @param sampleSize the sampleSize to set
     */
    public void setSampleSize(long sampleSize) {
        this.sampleSize = sampleSize;
    }

    /**
     * @return the payloadLength
     */
    public long getPayloadLength() {
        return payloadLength;
    }

    /**
     * @param payloadLength the payloadLength to set
     */
    public void setPayloadLength(long payloadLength) {
        this.payloadLength = payloadLength;
    }

    /**
     * @return the payloadTransmitDelay
     */
    public long getPayloadTransmitDelay() {
        return payloadTransmitDelay;
    }

    /**
     * @param payloadTransmitDelay the payloadTransmitDelay to set
     */
    public void setPayloadTransmitDelay(long payloadTransmitDelay) {
        this.payloadTransmitDelay = payloadTransmitDelay;
    }

    /**
     * @return the payloadsReceived
     */
    public long getPayloadsReceived() {
        return payloadsReceived;
    }

    /**
     * @param payloadsReceived the payloadsReceived to set
     */
    public void setPayloadsReceived(long payloadsReceived) {
        this.payloadsReceived = payloadsReceived;
    }

    /**
     * @return the payloadsDropped
     */
    public long getPayloadsDropped() {
        return payloadsDropped;
    }

    /**
     * @param payloadsDropped the payloadsDropped to set
     */
    public void setPayloadsDropped(long payloadsDropped) {
        this.payloadsDropped = payloadsDropped;
    }

    /**
     * @return the payloadsRepeated
     */
    public long getPayloadsRepeated() {
        return payloadsRepeated;
    }

    /**
     * @param payloadsRepeated the payloadsRepeated to set
     */
    public void setPayloadsRepeated(long payloadsRepeated) {
        this.payloadsRepeated = payloadsRepeated;
    }

    /**
     * @return the payloadsOutOfOrder
     */
    public long getPayloadsOutOfOrder() {
        return payloadsOutOfOrder;
    }

    /**
     * @param payloadsOutOfOrder the payloadsOutOfOrder to set
     */
    public void setPayloadsOutOfOrder(long payloadsOutOfOrder) {
        this.payloadsOutOfOrder = payloadsOutOfOrder;
    }

    /**
     * @return the timeTaken
     */
    public Date getTimeTaken() {
        return timeTaken;
    }

    /**
     * @param timeTaken the timeTaken to set
     */
    public void setTimeTaken(Date timeTaken) {
        this.timeTaken = timeTaken;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return the subTag
     */
    public String getSubTag() {
        return subTag;
    }

    /**
     * @param subTag the subTag to set
     */
    public void setSubTag(String subTag) {
        this.subTag = subTag;
    }

    /**
     * @return the throughput
     */
    public double[] getThroughput() {
        return throughput;
    }

    /**
     * @param throughput the throughput to set
     */
    public void setThroughput(double[] throughput) {
        this.throughput = throughput;
    }
    
    @Override
    public String toString(){
    
        return resultTitle;
    }

    /**
     * @return the resultTitle
     */
    public String getResultTitle() {
        return resultTitle;
    }

    /**
     * @param resultTitle the resultTitle to set
     */
    public void setResultTitle(int lineNumber) {
        StringBuilder traceNameBuilder = new StringBuilder();
            
        traceNameBuilder = traceNameBuilder.append(getFilename())
                .append(" - #").append(lineNumber)
                .append(" - ")
                .append(getTag())
                .append(" - ")
                .append(getNotes());
        
        this.resultTitle = traceNameBuilder.toString();
    }
    
    public String getDetail(){
    
        StringBuilder result = new StringBuilder();
        
        //result.append("");
        
        for (Field field : this.getClass().getDeclaredFields()) {
            
            try {
                if(field.getType().isAssignableFrom(double[].class)){
//                    int cntr = 0;
//                    for (double val : (double[])field.get(this)) {
//                        result.append(ZapFileParser.percentage[cntr++])
//                                .append("\t:\t")
//                                .append(val)
//                                .append("\n");
//                    }
                }else{
                    result.append(field.getName())
                        .append("\t:\t")
                        .append(field.get(this))
                        .append("\n");
                }
                
                
            } catch (IllegalArgumentException ex) {
                Exceptions.printStackTrace(ex);
            } catch (IllegalAccessException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        
        return result.toString();
    }
    
}
