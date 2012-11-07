/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf.ui.charts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.core.zperf.ZPerfUiTopComponent;
import org.core.zperf.ZapCCDFParser;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
    category = "Edit",
id = "org.core.zperf.ui.charts.OpenCCDFCharter")
@ActionRegistration(
    iconBase = "org/core/zperf/ui/charts/graph.gif",
displayName = "#CTL_OpenCCDFCharter")
@ActionReference(path = "Toolbars/MyToolbar", position = 300)
@Messages("CTL_OpenCCDFCharter=CCDF Chart Viewer")
public final class OpenCCDFCharter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ZapCCDFParser zapCCDFParser = null;
        
        if (ZPerfUiTopComponent.parameters.getResultFilename() != null 
                && !ZPerfUiTopComponent.parameters.getResultFilename().isEmpty()) {
            
            File file = new File(ZPerfUiTopComponent.zapUserSettings.getWorkingFolder() 
                + "/"+ ZPerfUiTopComponent.parameters.getResultFilename());
            
            if (file.exists()) {
                zapCCDFParser = new ZapCCDFParser(ZPerfUiTopComponent.zapUserSettings.getWorkingFolder() 
                + "/"+ ZPerfUiTopComponent.parameters.getResultFilename());
            }
            
        }
        
        ZapCCDFChartForm zapCCDFChart = new ZapCCDFChartForm("CCDF Chart Viewer");
        
        if (zapCCDFParser != null) {
            
            zapCCDFChart.setInitialCCDFTrace(zapCCDFParser);
            
        }
        
        zapCCDFChart.setEnabled(true);
        
        zapCCDFChart.setVisible(true);
    }
}
