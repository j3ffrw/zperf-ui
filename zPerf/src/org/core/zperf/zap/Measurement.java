package org.core.zperf.zap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author core
 */
public class Measurement {
    
    public Measurement(
            int burstTestNumber,
            double totalReceivedPackets, 
            double totalDroppedPackets, 
            double totalAggregateThoroughput,
            double _0thPercentile,
            double _50thPercentile,
            double _90thPercentile,
            double _95thPercentile,
            double _99thPercentile,
            double _99_9thPercentile){
        this.burstTestNumber = burstTestNumber;
        this._0thPercentile = _0thPercentile;
        this._50thPercentile = _50thPercentile;
        this._90thPercentile = _90thPercentile;
        this._95thPercentile = _95thPercentile;
        this._99thPercentile = _99thPercentile;
        this._99_9thPercentile = _99_9thPercentile;
        
        this.totalReceivedPackets = totalReceivedPackets;
        this.totalDroppedPackets = totalDroppedPackets;
        this.totalAggregateThoroughput = totalAggregateThoroughput;
    }
    
    /**
     * @return the _0thPercentile
     */
    public double get0thPercentile() {
        return _0thPercentile;
    }

    /**
     * @return the _50thPercentile
     */
    public double get50thPercentile() {
        return _50thPercentile;
    }

    /**
     * @return the _90thPercentile
     */
    public double get90thPercentile() {
        return _90thPercentile;
    }

    /**
     * @return the _95thPercentile
     */
    public double get95thPercentile() {
        return _95thPercentile;
    }

    /**
     * @return the _99thPercentile
     */
    public double get99thPercentile() {
        return _99thPercentile;
    }

    /**
     * @return the _99_9thPercentile
     */
    public double get99_9thPercentile() {
        return _99_9thPercentile;
    }


    /**
     * @return the totalReceivedPackets
     */
    public double getTotalReceivedPackets() {
        return totalReceivedPackets;
    }

    /**
     * @return the totalDroppedPackets
     */
    public double getTotalDroppedPackets() {
        return totalDroppedPackets;
    }

    /**
     * @return the totalAggregateThoroughput
     */
    public double getTotalAggregateThoroughput() {
        return totalAggregateThoroughput;
    }
    
    /**
     * @return the burstTestNumber
     */
    public int getBurstTestNumber() {
        return burstTestNumber;
    }
    
    private double _0thPercentile, _50thPercentile, _90thPercentile, _95thPercentile, _99thPercentile, _99_9thPercentile;
    
    private double totalReceivedPackets;

    private double totalDroppedPackets;
    
    private double totalAggregateThoroughput;
    
    private int burstTestNumber;

    
}
