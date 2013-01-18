/*
 * Just a place holder for ZapResult and ITrace2D
 * .
 */
package org.core.zperf;

import info.monitorenter.gui.chart.ITrace2D;

/**
 *
 * @author Jefferson Fermo <jefffermo@yahoo.com>
 */
public class ZapResultTrace {
    
    public ZapResultTrace(ZapResult zapResult, ITrace2D zapCCDFTrace){
    
        this.zapResult = zapResult;
        
        this.zapCCDFTrace = zapCCDFTrace;
    }    
    
    /**
     * @return the zapResult
     */
    public ZapResult getZapResult() {
        return zapResult;
    }

    /**
     * @return the zapCCDFTrace
     */
    public ITrace2D getZapCCDFTrace() {
        return zapCCDFTrace;
    }
    
    @Override
    public String toString(){
        return zapResult.toString();
    }
    
    private ZapResult zapResult;
    
    private ITrace2D zapCCDFTrace;

}
