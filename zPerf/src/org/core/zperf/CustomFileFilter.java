/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author core
 */
public class CustomFileFilter extends FileFilter{
    
    public CustomFileFilter(String [] supportedExtension, String description){
        super();
        this.supportedExtension = supportedExtension;
        this.description = description;
    }
    
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        
        String extension = getExtension(f);
        
        if (extension != null) {
            
            for (String string : supportedExtension) {
                if (extension.indexOf(string) == 0) {
                    return true;
                } 
            }
            return false;
        }

        return false;
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    //The description of this filter
    @Override
    public String getDescription() {
        return description;
    }
    
    String[] supportedExtension;
    
    String description;
}
