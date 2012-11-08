/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf.ui.charts;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.pointpainters.PointPainterDisc;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.gui.util.ColorIterator;
import info.monitorenter.gui.util.ColorIterator.HueStepper;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import org.core.zperf.CustomFileFilter;
import org.core.zperf.ZPerfUiTopComponent;
import org.core.zperf.ZapCCDFParser;
import org.core.zperf.ZapUserSettings;
import org.openide.util.Exceptions;


/**
 *
 * @author core
 */
public class ZapCCDFChartForm extends javax.swing.JFrame {

    /**
     * Creates new form ZapCCDFChart
     */
    public ZapCCDFChartForm(String title) {
        super(title);
        
        this.zapUserSettings = ZPerfUiTopComponent.zapUserSettings;
        
        traceTableModel = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        traceTableModel.addColumn("Zap trace file");
        
        initComponents();
        
        zapCCDFChart = zapCCDFChartPanel1.getZapCCDFChart();
        
        zapCCDFChart.enablePointHighlighting(true);
        
        zapCCDFChart.getAxisX().setAxisTitle(new IAxis.AxisTitle("% of Probability"));
        zapCCDFChart.getAxisY().setAxisTitle(new IAxis.AxisTitle("Throughput (Mbps)"));

        HueStepper model = new HueStepper(10);
        
        colorIterator = new ColorIterator(Color.RED, model);
        
        
    }

    public void setInitialCCDFTrace(ZapCCDFParser zapCCDFParser){
        addNewCCDFTrace(zapCCDFParser, zapCCDFParser.getFileName());
    }
    
    public void addNewCCDFTrace(ZapCCDFParser zapCCDFParser, String traceName){
        
        
        List<double[]> throughputList = zapCCDFParser.getThroughput();
        
        List<String> traceTag = zapCCDFParser.getTraceTag();
        
        int traceNumber = 0;
        
        for (double[] throughput : throughputList) {
            ITrace2D zapCCDFTrace = new Trace2DLtd(110);
        
            //zapCCDFTrace.setColor(SeriesColorGenerator.nextColor());
            Color color = colorIterator.next();
            
            System.out.println(color);
            
            zapCCDFTrace.setColor(color);

            zapCCDFTrace.setName(new File(traceName).getName() + " - "+ traceTag.get(traceNumber) + "-#" + traceNumber++);

            zapCCDFChart.addTrace(zapCCDFTrace);
            
            double[] percentage = zapCCDFParser.getPercentage();

            for (int cntr = 0; cntr < throughput.length; cntr++) {

                double currentThroughput = throughput[cntr];

                zapCCDFTrace.addPoint(percentage[cntr], currentThroughput);            
            }

            Vector<ITrace2D> newTrace = new Vector<ITrace2D>();

            newTrace.add(zapCCDFTrace);
            
            traceTableModel.addRow(newTrace);
        }
        
    }
    
    
    public void createSnapShot(File filename){
        BufferedImage snapshot = zapCCDFChart.snapShot(1333, 600);
        
         int dot = filename.getName().lastIndexOf(".");
     
        String ext = filename.getName().substring(dot + 1);
        try {
            ImageIO.write(snapshot, ext, filename);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        traceTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        delTraceBtn = new javax.swing.JButton();
        snapshotBtn = new javax.swing.JButton();
        addTraceBtn = new javax.swing.JButton();
        isolateTBtn = new javax.swing.JToggleButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        zapCCDFChartPanel1 = new org.core.zperf.ui.charts.ZapCCDFChartPanel("");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        traceTable.setModel(traceTableModel);
        traceTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                traceTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(traceTable);

        org.openide.awt.Mnemonics.setLocalizedText(delTraceBtn, org.openide.util.NbBundle.getMessage(ZapCCDFChartForm.class, "ZapCCDFChartForm.delTraceBtn.text")); // NOI18N
        delTraceBtn.setToolTipText(org.openide.util.NbBundle.getMessage(ZapCCDFChartForm.class, "ZapCCDFChartForm.delTraceBtn.toolTipText")); // NOI18N
        delTraceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delTraceBtnActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(snapshotBtn, org.openide.util.NbBundle.getMessage(ZapCCDFChartForm.class, "ZapCCDFChartForm.snapshotBtn.text")); // NOI18N
        snapshotBtn.setToolTipText(org.openide.util.NbBundle.getMessage(ZapCCDFChartForm.class, "ZapCCDFChartForm.snapshotBtn.toolTipText")); // NOI18N
        snapshotBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snapshotBtnActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(addTraceBtn, org.openide.util.NbBundle.getMessage(ZapCCDFChartForm.class, "ZapCCDFChartForm.addTraceBtn.text")); // NOI18N
        addTraceBtn.setToolTipText(org.openide.util.NbBundle.getMessage(ZapCCDFChartForm.class, "ZapCCDFChartForm.addTraceBtn.toolTipText")); // NOI18N
        addTraceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTraceBtnActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(isolateTBtn, org.openide.util.NbBundle.getMessage(ZapCCDFChartForm.class, "ZapCCDFChartForm.isolateTBtn.text")); // NOI18N
        isolateTBtn.setToolTipText(org.openide.util.NbBundle.getMessage(ZapCCDFChartForm.class, "ZapCCDFChartForm.isolateTBtn.toolTipText")); // NOI18N
        isolateTBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isolateTBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addTraceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(delTraceBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(isolateTBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(snapshotBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addTraceBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delTraceBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(isolateTBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(snapshotBtn)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jSplitPane1.setDividerLocation(100);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(0.5);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setOneTouchExpandable(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        jSplitPane1.setBottomComponent(jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(zapCCDFChartPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(zapCCDFChartPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSplitPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addTraceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTraceBtnActionPerformed
        
        final String zapFile = getZapFile();
        
        if (zapFile == null) {
            return;
        }
        
        ZapCCDFParser zapCCDFParser = new ZapCCDFParser(zapFile);
        
        addNewCCDFTrace(zapCCDFParser, zapFile);
    }//GEN-LAST:event_addTraceBtnActionPerformed

    private void delTraceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delTraceBtnActionPerformed
        int selectedRow = traceTable.getSelectedRow();
        
        selectedRow = traceTable.convertRowIndexToModel(selectedRow);
        
        if (selectedRow >= 0) {
            ITrace2D trace = (ITrace2D) traceTableModel.getValueAt(selectedRow, 0);
            traceTableModel.removeRow(selectedRow);
            traceTableModel.fireTableDataChanged();
            zapCCDFChart.removeTrace(trace);
        }
        
    }//GEN-LAST:event_delTraceBtnActionPerformed

    private void snapshotBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snapshotBtnActionPerformed
        JFileChooser zapfileChooser = new JFileChooser(System.getProperty("user.home"));
        zapfileChooser.setDialogTitle("Select zPerf working directory");
        zapfileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        zapfileChooser.setAcceptAllFileFilterUsed(false);
        zapfileChooser.setFileFilter(new CustomFileFilter(new String[]{"bmp", "gif", "jpeg", "jpg", "png"}, "image files"));
        zapfileChooser.setCurrentDirectory(new File(zapUserSettings.getWorkingFolder()));
        
        if (zapfileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
                                
            createSnapShot(zapfileChooser.getSelectedFile());
            
        }else {
            // do nothing I guess
        }
    }//GEN-LAST:event_snapshotBtnActionPerformed

    private void traceTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_traceTableMouseClicked
        if (evt.getClickCount() == 1) {
            int selectedRow = traceTable.getSelectedRow();
        
            selectedRow = traceTable.convertRowIndexToModel(selectedRow);
            
            if (previouslySelectedTrace != null) {
                previouslySelectedTrace.removeAllPointHighlighters();                
                previouslySelectedTrace.setStroke( new BasicStroke());
            }
            
            if (selectedRow >= 0) {
                ITrace2D selectedTrace = (ITrace2D) traceTable.getValueAt(selectedRow, 0);

                selectedTrace.setStroke( new BasicStroke(2));
                
                selectedTrace.setPointHighlighter(pointPainterDisc);
                
                previouslySelectedTrace = selectedTrace;
            }
        }
    }//GEN-LAST:event_traceTableMouseClicked

    private void isolateTBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isolateTBtnActionPerformed
        
        if (isolateTBtn.isSelected()) {
            int[] selectedRows = traceTable.getSelectedRows();
        
            if (selectedRows.length >= 0) {
                for (Iterator<ITrace2D> it = zapCCDFChart.getTraces().iterator(); it.hasNext();) {
                    ITrace2D iTrace2D = it.next();
                    iTrace2D.setVisible(false);
                }

                for (int selectedItem : selectedRows) {
                   ITrace2D iTrace2D = (ITrace2D) traceTable.getValueAt(selectedItem, 0);
                   iTrace2D.setVisible(true);
                }    
            }
        }else{
            for (Iterator it = traceTableModel.getDataVector().iterator(); it.hasNext();) {
                Vector object = (Vector) it.next();
                ITrace2D trace = (ITrace2D) object.get(0);
                
                trace.setVisible(true);
            }
        }
    }//GEN-LAST:event_isolateTBtnActionPerformed

    private String getZapFile(){
        JFileChooser zapfileChooser = new JFileChooser(System.getProperty("user.home"));
        zapfileChooser.setDialogTitle("Select zPerf working directory");
        zapfileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        zapfileChooser.setAcceptAllFileFilterUsed(false);
        zapfileChooser.setFileFilter(new CustomFileFilter(new String[]{"zap"}, "zap file"));
        zapfileChooser.setCurrentDirectory(new File(zapUserSettings.getWorkingFolder()));
        
        if (zapfileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
                                
            return zapfileChooser.getSelectedFile().toString();            
            
        }else {
            // do nothing I guess
        }
        
        return null;
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addTraceBtn;
    private javax.swing.JButton delTraceBtn;
    private javax.swing.JToggleButton isolateTBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton snapshotBtn;
    private javax.swing.JTable traceTable;
    private org.core.zperf.ui.charts.ZapCCDFChartPanel zapCCDFChartPanel1;
    // End of variables declaration//GEN-END:variables

    private Chart2D zapCCDFChart;
    
    private String title;
    
    private ZapUserSettings zapUserSettings;
    
    private DefaultTableModel traceTableModel;
    
    private PointPainterDisc pointPainterDisc = new PointPainterDisc(8);
    
    private ColorIterator colorIterator;
    
    private ITrace2D previouslySelectedTrace;
    
}


final class ColorItem extends Color{
    
    private final String colorName;
    private final Color itemColor;
    
    public ColorItem(final Color color, final String colorName){
        super(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
        this.colorName = colorName;
        this.itemColor = color;
    
    }
    
    public Color getColor(){
        return itemColor;
    }
    
    @Override
    public String toString(){
        return colorName;
    }
}
