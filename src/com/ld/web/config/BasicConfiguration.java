package com.ld.web.config;

/**
 * 
 *<p>Title: BasicConfiguration</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-11
 */
public class BasicConfiguration {

    private static final BasicConfiguration INSTANCE = new BasicConfiguration();

    private void loadConfig() {

    }

    public static BasicConfiguration getInstance() {
        return INSTANCE;
    }

    private BasicConfiguration() {
        loadConfig();
    }

}
