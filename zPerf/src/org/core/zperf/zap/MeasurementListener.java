/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf.zap;

import java.util.EventListener;

/**
 *
 * @author core
 */
public interface MeasurementListener extends EventListener {
    public void measurementAdded(MeasurementEvent evt);
}
