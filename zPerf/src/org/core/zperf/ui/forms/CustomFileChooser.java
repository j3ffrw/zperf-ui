/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf.ui.forms;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jefferson Fermo <jefffermo@yahoo.com>
 */
public class CustomFileChooser extends JFileChooser{
    
    public CustomFileChooser(String[] extension, boolean acceptAllFilter) {
    super();
    

    setAcceptAllFileFilterUsed(acceptAllFilter);    
    
    for (String string : extension) {
        addChoosableFileFilter(new FileNameExtensionFilter(string,string));
    }
    
  }

  @Override public File getSelectedFile() {
    File selectedFile = super.getSelectedFile();

    if (selectedFile != null) {
      String name = selectedFile.getName();
      if (!name.contains(".")){
          selectedFile = new File(selectedFile.getParentFile(), 
          name + '.' + getFileFilter().getDescription());
      }
        
    }

    return selectedFile;
  }

  @Override public void approveSelection() {
    if (getDialogType() == SAVE_DIALOG) {
      File selectedFile = getSelectedFile();
      if ((selectedFile != null) && selectedFile.exists()) {
        int response = JOptionPane.showConfirmDialog(this,
          "The file " + selectedFile.getName() + 
          " already exists. Do you want to replace the existing file?",
          "Ovewrite file", JOptionPane.YES_NO_OPTION,
          JOptionPane.WARNING_MESSAGE);
        if (response != JOptionPane.YES_OPTION){
            return;
        }
        
      }
    }

    super.approveSelection();
  }
}
