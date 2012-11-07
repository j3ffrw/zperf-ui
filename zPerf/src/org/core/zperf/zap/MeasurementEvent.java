/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf.zap;

import java.util.EventObject;

/**
 *
 * @author core
 */
public class MeasurementEvent extends EventObject{
    
    public MeasurementEvent(Measurement source){
        super(source);
    }
}
