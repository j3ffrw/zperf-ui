/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf.ui.charts;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.axis.scalepolicy.AxisScalePolicyManualTicks;
import info.monitorenter.gui.chart.pointpainters.PointPainterDisc;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.gui.chart.views.ChartPanel;
import info.monitorenter.gui.util.ColorIterator;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import org.core.zperf.ZapCCDFParser;

/**
 *
 * @author core
 */
public class ZapCCDFChartPanel extends javax.swing.JPanel {

    /**
     * Creates new form ChartPanel
     */
    public ZapCCDFChartPanel() {
        initComponents();
    }    
    
    public ZapCCDFChartPanel(String name){
        
        this.setName(name);
        
        setLayout(new BorderLayout());
        
        zapCCDFChart = new Chart2D();
        
        zapCCDFChart.getAxisX().setPaintGrid(true);
        zapCCDFChart.getAxisY().setPaintGrid(true);
        zapCCDFChart.getAxisX().setAxisTitle(new IAxis.AxisTitle("Percentage"));
        zapCCDFChart.setToolTipType(Chart2D.ToolTipType.VALUE_SNAP_TO_TRACEPOINTS);
        
        @SuppressWarnings("unchecked")
        IAxis<AxisScalePolicyManualTicks> xAxis = (IAxis<AxisScalePolicyManualTicks>) zapCCDFChart.getAxisX();
        xAxis.setAxisScalePolicy(new AxisScalePolicyManualTicks()); 
        
        xAxis.setMajorTickSpacing(100);
        xAxis.setMinorTickSpacing(5);
        xAxis.setStartMajorTick(true);
        
        zapCCDFChart.setPreferredSize(new Dimension(800, 350));
        
        ChartPanel chartPanel = new ChartPanel(zapCCDFChart);
        
        JScrollPane scrollPane = new JScrollPane();
        
        scrollPane.setViewportView(chartPanel);
        
        add(scrollPane);
        
        traceTableModel = new DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        traceTableModel.addColumn("Zap trace file");
        
        zapCCDFChart.enablePointHighlighting(true);
        
        zapCCDFChart.getAxisX().setAxisTitle(new IAxis.AxisTitle("% of Probability"));
        zapCCDFChart.getAxisY().setAxisTitle(new IAxis.AxisTitle("Throughput (Mbps)"));

        ColorIterator.HueStepper model = new ColorIterator.HueStepper(10);
        
        colorIterator = new ColorIterator(Color.RED, model);
        
    }
    /**
     * @return the zapCCDFChart
     */
    public Chart2D getZapCCDFChart() {
        return zapCCDFChart;
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
    
    public void isolateSelectedTrace(int[] selectedRows){
    
        if (selectedRows.length >= 0) {
            for (Iterator<ITrace2D> it = zapCCDFChart.getTraces().iterator(); it.hasNext();) {
                ITrace2D iTrace2D = it.next();
                iTrace2D.setVisible(false);
            }

            for (int selectedItem : selectedRows) {
                ITrace2D iTrace2D = (ITrace2D) traceTableModel.getValueAt(selectedItem, 0);
                iTrace2D.setVisible(true);
            }
        }
        
    }
    
    public void showAllTraces(){
        
        for (Iterator it = traceTableModel.getDataVector().iterator(); it.hasNext();) {
            Vector object = (Vector) it.next();
            ITrace2D trace = (ITrace2D) object.get(0);

            trace.setVisible(true);
        }
    
    }
    
    public void removeTrace(int index){
        
        ITrace2D trace = (ITrace2D) traceTableModel.getValueAt(index, 0);
        
        traceTableModel.removeRow(index);
        
        traceTableModel.fireTableDataChanged();
            
        zapCCDFChart.removeTrace(trace);
    }
    
    public BufferedImage getSnapShot(int x, int y){
        return zapCCDFChart.snapShot(x, y);
    }
    
    public void highlightTrace(int index){
        
            if (previouslySelectedTrace != null) {
                previouslySelectedTrace.removeAllPointHighlighters();
                previouslySelectedTrace.setStroke( normalTraceStroke );
            }

            if (index >= 0) {
                ITrace2D selectedTrace = (ITrace2D) traceTableModel.getValueAt(index, 0);

                selectedTrace.setStroke( highlightedTraceStroke );

                selectedTrace.setPointHighlighter(pointPainterDisc);

                previouslySelectedTrace = selectedTrace;
            }
        
    }
    
    public DefaultTableModel getTraceTableModel(){
        return traceTableModel;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        zapCCDFChart = new info.monitorenter.gui.chart.Chart2D();

        setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout zapCCDFChartLayout = new javax.swing.GroupLayout(zapCCDFChart);
        zapCCDFChart.setLayout(zapCCDFChartLayout);
        zapCCDFChartLayout.setHorizontalGroup(
            zapCCDFChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        zapCCDFChartLayout.setVerticalGroup(
            zapCCDFChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(zapCCDFChart, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private info.monitorenter.gui.chart.Chart2D zapCCDFChart;
    // End of variables declaration//GEN-END:variables
    
    private DefaultTableModel traceTableModel;
    
    private ColorIterator colorIterator;
    
    private ITrace2D previouslySelectedTrace;
    
    private final PointPainterDisc pointPainterDisc = new PointPainterDisc(8);
    
    private final BasicStroke normalTraceStroke = new BasicStroke();
    
    private final BasicStroke highlightedTraceStroke = new BasicStroke(3);
    
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