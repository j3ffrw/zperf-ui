/**
 * - 02/2008: Class created by Nicolas Richasse
 * 
 * Changelog:
 * 	- class created
 * 
 *-05/2009:
 *	- code improved
 */


package org.core.zperf;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import org.jdesktop.swingx.JXBusyLabel;
import org.openide.windows.TopComponent;

public class JPerfWaitWindow extends JDialog
{
	private TopComponent parent;
	
	public JPerfWaitWindow(TopComponent parent)
	{
//		super(parent, "Stopping iperf...", false);
                setTitle("Stopping zap...");
		this.parent = parent;
                
		init();
	}
	
	private void init()
	{
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		JLabel messageLabel = new JLabel("Please wait while zap is stopping...");
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
		JXBusyLabel busyLabel = new JXBusyLabel();
		busyLabel.setOpaque(false);
		busyLabel.setBusy(true);
		busyLabel.setHorizontalAlignment(JLabel.CENTER);
		
		super.setLayout(new BorderLayout());
		add(messageLabel, BorderLayout.NORTH);
		add(busyLabel, BorderLayout.CENTER);
		pack();
		
		this.setLocationRelativeTo(parent);
	}
}
