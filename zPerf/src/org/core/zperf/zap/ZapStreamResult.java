package org.core.zperf.zap;


import java.util.Vector;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author core
 */
public class ZapStreamResult {
    
    
    public ZapStreamResult(){
        
        id++;
        bandwidth = new Vector<Measurement>();
    
    }    
    
    public void addBandwidth(Measurement bandwidth){
        this.bandwidth.add(bandwidth);
        fireMyEvent(new MeasurementEvent(bandwidth));
    }
    
    public int getId(){
        return id;
    }
    
    public Vector<Measurement> getBandwidth(){
        return bandwidth;
    }
    
    @Override
    public boolean equals(Object o){
        return o instanceof ZapStreamResult && ((ZapStreamResult) o).getId() == id;
    }
    
    public void addEventListener(MeasurementListener listener) {
        listenerList.add(MeasurementListener.class, listener);
    }
    
    public void removeEventListener(MeasurementListener listener) {
        listenerList.remove(MeasurementListener.class, listener);
    }
    
     void fireMyEvent(MeasurementEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i=0; i<listeners.length; i+=2) {
            if (listeners[i]==MeasurementListener.class) {
                ((MeasurementListener)listeners[i+1]).measurementAdded(evt);
            }
        }
         
    }
    
    private Vector<Measurement> bandwidth;
    private static int id = 0;
    
    protected javax.swing.event.EventListenerList listenerList =
        new javax.swing.event.EventListenerList();
}
