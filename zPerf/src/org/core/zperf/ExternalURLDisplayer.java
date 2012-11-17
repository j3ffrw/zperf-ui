/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.core.zperf;

import java.awt.Desktop;
import java.net.URL;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openide.awt.HtmlBrowser;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Jefferson Fermo <jefffermo@yahoo.com>
 */
@ServiceProvider (service = HtmlBrowser.URLDisplayer.class, position = 0)
public class ExternalURLDisplayer extends HtmlBrowser.URLDisplayer{
    @Override
    public void showURL(URL link) {
        try {
            Desktop.getDesktop().browse(link.toURI());
        } catch(Exception ex) {

            Logger.getLogger("global").log(Level.FATAL, null, ex);
        // show the user a message dialog
        }
    }
}

