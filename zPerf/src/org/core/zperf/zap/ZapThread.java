/**
 * - 11/2012: Class modified by Jeff Fermo
 * 
 * Changelog:
 *      - modified and renamed class to handle zap's output
 *      - remove JPerfWaitWindow since I'm using Netbeans Platform, 
 *        it was no longer working as expected, still on my todo list
 * 
 * - 02/2008: Class created by Nicolas Richasse
 * 
 * Changelog:
 * 	- class created
 * 	- iperf line parsing fixed and improved
 * 
 * To do:
 * 	- ...
 *
 * Old notes:
 *	- The ParseLine results variable still ends up with a blank 0th string which may or may not ever matter (DC)
 */

package org.core.zperf.zap;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.regex.Pattern;
import javax.swing.SwingUtilities;
import org.core.zperf.ZPerfUiTopComponent;
import org.core.zperf.ZapUserSettings;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.awt.StatusDisplayer;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author core
 */
public class ZapThread extends Thread{
    
    public ZapThread(ZapParameters zapParameters, ZPerfUiTopComponent parentUi){
        this.zapParameters = zapParameters;
        this.zapStreamResult = new ZapStreamResult();
        this.parentUi = parentUi;
        
        zapUserSettings = parentUi.getSettings();
    }
    
    @Override
    public void run(){
        
        try{
            
            parentUi.setStartedStatus();
            
            StatusDisplayer.getDefault().setStatusText("Test started");                        

            p.start();
            
            p.switchToIndeterminate();
            
            testStarted = new Date();
            
            ProcessBuilder pb = new ProcessBuilder(zapParameters.getParameters());
            
            process = pb.start();
            
            // read in the output from zap
            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String input_line = null;
            while ((input_line = input.readLine()) != null)
            {
                parseLine(input_line);
                ZPerfUiTopComponent.appendOptionalToOutput(input_line);
            }

            String error_line = null;
            while ((error_line = errors.readLine()) != null)
            {
                ZPerfUiTopComponent.appendToError(error_line);
            }
            
            testFinished = new Date();

            }catch (Exception e){

                        ZPerfUiTopComponent.appendToError(e.getMessage());
            }finally{
                quit();
            }
    }
    
    private final Object waitWindowMutex = new Object();
    //private JPerfWaitWindow waitWindow;
    
    public synchronized void quit()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
                    @Override
			public void run()
			{
				if (process != null)
				{
					synchronized(waitWindowMutex)
					{
						//if (waitWindow != null)
						//{
						//	return;
						//}
						//waitWindow = new JPerfWaitWindow(parentUi);
						parentUi.setEnabled(false);
						//waitWindow.setVisible(true);
                                                
					}
					Thread t = new Thread()
					{
                                            @Override
						public void run()
						{
							process.destroy();
														
                                                        try
                                                        {
                                                                process.getInputStream().close();
                                                        }
                                                        catch (Exception e)
                                                        {
                                                                // nothing
                                                        }

                                                        try
                                                        {
                                                                process.getOutputStream().close();
                                                        }
                                                        catch (Exception e)
                                                        {
                                                                // nothing
                                                        }

                                                        try
                                                        {
                                                                process.getErrorStream().close();
                                                        }
                                                        catch (Exception e)
                                                        {
                                                                // nothing
                                                        }

                                                        if (input != null)
                                                        {
                                                                try
                                                                {
                                                                        input.close();
                                                                }
                                                                catch (Exception e)
                                                                {
                                                                        // nothing
                                                                }
                                                                finally
                                                                {
                                                                        input = null;
                                                                }
                                                        }

                                                        if (errors != null)
                                                        {
                                                                try
                                                                {
                                                                        errors.close();
                                                                }
                                                                catch (Exception e)
                                                                {
                                                                        // nothing
                                                                }
                                                                finally
                                                                {
                                                                        errors = null;
                                                                }
                                                        }
							
							
							try
							{
								process.waitFor();
							}
							catch (Exception ie)
							{
								// nothing
							}
				
							process = null;
							
							synchronized(waitWindowMutex)
							{
//								waitWindow.setVisible(false);
//								waitWindow.dispose();
//								waitWindow = null;
								parentUi.setStoppedStatus();
								parentUi.setEnabled(true);
                                                                p.finish();
            
                                                                StatusDisplayer.getDefault().setStatusText("Test done");
							}
						}
					};
					t.setDaemon(true);
					t.start();
				}
			}
		});
	}
    
    private void parseLine(String line){
        
        if(line.matches("[\\s]*[\\d]+[:]{1}.*")){
            
            Pattern p = Pattern.compile("[->\\s]+");
            
            String[] results = p.split(line.trim());
            

            
            if(results.length == 19){
                String[] temp = results[0].split(":");
                
                Integer burstTestNumber = new Integer(temp[0]);
                
                temp = results[3].split("=");
                Double totalReceivedPackets = new Double(temp[0]);
               
                temp = results[4].split("=");
                Double totalDroppedPackets = new Double(temp[0]);
               
                Double averageThroughput = new Double(results[11]);
               
                Double _0thPercentile = new Double(results[13]);
                Double _50thPercentile = new Double(results[14]);
                Double _90thPercentile = new Double(results[15]);
                Double _95thPercentile = new Double(results[16]);
                Double _99thPercentile = new Double(results[17]);
                Double _99_9thPercentile = new Double(results[18]);
                
                Measurement measurement = new Measurement(
                        burstTestNumber, 
                        totalReceivedPackets, 
                        totalDroppedPackets, 
                        averageThroughput, 
                        _0thPercentile, 
                        _50thPercentile, 
                        _90thPercentile, 
                        _95thPercentile, 
                        _99thPercentile, 
                        _99_9thPercentile);
                
                getZapStreamResult().addBandwidth(measurement);
                
            }else{
                ZPerfUiTopComponent.appendOptionalToOutput("incorrect format");
            }
        }else{
            ZPerfUiTopComponent.appendToOutput(line);
        }
    }

    /**
     * @return the zapStreamResult
     */
    public ZapStreamResult getZapStreamResult() {
        return zapStreamResult;
    }
    
    /**
     * @return the testStarted
     */
    public Date getTestStarted() {
        return testStarted;
    }

    /**
     * @return the testFinished
     */
    public Date getTestFinished() {
        return testFinished;
    }
    
    private ZapParameters zapParameters;
    private Process process;
    
    private BufferedReader  input;
    private BufferedReader  errors;
     
    private ZapStreamResult zapStreamResult;
    
    private Date testStarted;
    private Date testFinished;
    
    private ZPerfUiTopComponent parentUi;

    private ZapUserSettings zapUserSettings;
    
    ProgressHandle p = ProgressHandleFactory.createHandle("zap running");

    
}
