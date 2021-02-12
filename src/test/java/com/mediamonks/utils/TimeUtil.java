package com.mediamonks.utils;

import java.io.IOException;

import com.mediamonks.base.PropKey;
import com.mediamonks.base.PropertyReader;

public class TimeUtil {

    public static Long getImplicitWait() throws IOException {
        return Long.parseLong(PropertyReader.getInstance().getProperty(PropKey.IMPLICIT_WAIT.getPropVal()));
    }

    public static Long getExplicitWait() throws IOException {
        return Long.parseLong(PropertyReader.getInstance().getProperty(PropKey.EXPLICIT_WAIT.getPropVal()));
    }
}
