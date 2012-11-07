/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf;

import java.util.prefs.Preferences;

/**
 *
 * @author core
 */
public class ZapUserSettings {
    
    private Preferences fPrefs = Preferences.userNodeForPackage(this.getClass());
    
    public static final String WORKING_FOLDER = "working_folder";
    public static final String SRC_ADDRESS = "src_address";
    public static final String DST_ADDRESS = "dst_address";
    
    private String workingFolder = "";
    private String srcAddress = "";
    private String dstAddress = "";
    private String zapResultFileExtension = "zap";
    
    public ZapUserSettings(){
        workingFolder = fPrefs.get(WORKING_FOLDER, ".");
        srcAddress = fPrefs.get(SRC_ADDRESS, "");
        dstAddress = fPrefs.get(DST_ADDRESS, "");
        System.setProperty(WORKING_FOLDER, workingFolder);
    }

    /**
     * @return the workingFolder
     */
    public String getWorkingFolder() {
        return workingFolder;
    }

    /**
     * @param workingFolder the workingFolder to set
     */
    public void setWorkingFolder(String workingFolder) {
        this.workingFolder = workingFolder;
        
    }

    /**
     * @return the srcAddress
     */
    public String getSrcAddress() {
        return srcAddress;
    }

    /**
     * @param srcAddress the srcAddress to set
     */
    public void setSrcAddress(String srcAddress) {
        this.srcAddress = srcAddress;
    }

    /**
     * @return the dstAddress
     */
    public String getDstAddress() {
        return dstAddress;
    }

    /**
     * @param dstAddress the dstAddress to set
     */
    public void setDstAddress(String dstAddress) {
        this.dstAddress = dstAddress;
    }
    
    public void savePreferences(){
        fPrefs.put(SRC_ADDRESS, srcAddress);
        fPrefs.put(DST_ADDRESS, dstAddress);
        fPrefs.put(WORKING_FOLDER, workingFolder);
    }

    /**
     * @return the zapResultFileExtension
     */
    public String getZapResultFileExtension() {
        return zapResultFileExtension;
    }

    /**
     * @param zapResultFileExtension the zapResultFileExtension to set
     */
    public void setZapResultFileExtension(String zapResultFileExtension) {
        if (zapResultFileExtension != null &&zapResultFileExtension.isEmpty()) {
            this.zapResultFileExtension = zapResultFileExtension;
        }
    }
    
    
}
