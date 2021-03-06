/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.openadmin.client.view.dynamic.form;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Progressbar;
import com.smartgwt.client.widgets.Window;
import org.broadleafcommerce.openadmin.client.BLCMain;
import org.broadleafcommerce.openadmin.client.view.SplashWindow;
import org.broadleafcommerce.openadmin.client.view.Stoppable;

/**
 * 
 * @author jfischer
 *
 */
public class ServerProcessProgressWindow extends Window implements Stoppable {

    private Progress progressBar;
    private String titleKey;

    public ServerProcessProgressWindow() {
        setWidth(360);
        setHeight(52);
        setShowMinimizeButton(false);
        setIsModal(true);
        setTitle(BLCMain.getMessageManager().getString("contactingServerTitle"));
        setShowCloseButton(false);
    }

    public void startProgress() {
        if (BLCMain.SPLASH_PROGRESS.isActive()) {
            SplashWindow splash = (SplashWindow) BLCMain.SPLASH_PROGRESS;
            setTop(splash.getTop() + 280);
            setLeft(splash.getLeft() + 40);
        } else {
            centerInPage();
        }
        show();
        progressBar.startProgress();
    }
    
    public void stopProgress() {
        progressBar.stopProgress();
    }
    
    public void finalizeProgress() {
        hide();
    }
    
    public Boolean isActive() {
        return progressBar.isActive();
    }

    public Progress getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(Progress progressBar) {
        if (this.progressBar != null && contains((Canvas) this.progressBar)) {
            removeItem((Progressbar) this.progressBar);
        }
        this.progressBar = progressBar;
        addItem((Progressbar) progressBar);
        progressBar.setDisplay(this);
    }

    public String getTitleKey() {
        return titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
        setTitle(BLCMain.getMessageManager().getString(titleKey));
    }
}
