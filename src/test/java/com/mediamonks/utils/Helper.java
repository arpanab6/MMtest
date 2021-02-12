package com.mediamonks.utils;

import com.mediamonks.base.PropKey;
import com.mediamonks.base.PropertyReader;
import com.mediamonks.webdriver.BrowserType;
import java.io.IOException;

import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

public class Helper {

    public static boolean isRemote() throws IOException {
        String isRemote = SystemPropertyHelper.getRemoteVal();

        if(isRemote.equalsIgnoreCase("TRUE")){
            return true;
        }
        return false;
    }

    public static String getHubUrl() throws IOException {
        return PropertyReader.getInstance().getProperty(PropKey.HUB_URL.getPropVal());
    }

    public static String getBrowserPath() throws IOException {

        //  System.getProperty("os.name");

        if(IS_OS_WINDOWS){

            if(SystemPropertyHelper.getBrowserFromSystemVariable()
                    == BrowserType.CHROME){

                return PropertyReader.getInstance()
                        .getProperty(PropKey.CHROME_DRIVER_PATH_WIN.getPropVal());

            }else if(SystemPropertyHelper.getBrowserFromSystemVariable()
                    == BrowserType.FIREFOX){

                return PropertyReader.getInstance()
                        .getProperty(PropKey.GECKO_DRIVER_PATH_WIN.getPropVal());

            }else if(SystemPropertyHelper.getBrowserFromSystemVariable()
                    == BrowserType.IE){

                return PropertyReader.getInstance()
                        .getProperty(PropKey.IE_DRIVER_PATH_WIN.getPropVal());
            }
        }

        if(SystemPropertyHelper.getBrowserFromSystemVariable() == BrowserType.FIREFOX){

            return PropertyReader.getInstance()
                    .getProperty(PropKey.GECKO_DRIVER_PATH_OSX.getPropVal());
        }else{
            return PropertyReader.getInstance()
                    .getProperty(PropKey.CHROME_DRIVER_PATH_OSX.getPropVal());
        }
    }

    
}
