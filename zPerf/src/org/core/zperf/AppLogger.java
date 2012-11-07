/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author core
 */
public class AppLogger {

    public static void appLogger(String className, Exception exception){
        Logger.getLogger(className).log(Level.ERROR, null, exception);
    }

    public static void appLogger(String className, String message){
        Logger.getLogger(className).log(Level.INFO, message);
    }

}
