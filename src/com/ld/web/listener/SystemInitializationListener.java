package com.ld.web.listener;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ld.web.biz.SystemBiz;

/**
 * 
 *<p>Title: SystemInitializationListener</p>
 *<p>Copyright: Copyright (c) 2017</p>
 *<p>Description: </p>
 *
 *@author LD
 *
 *@date 2017-01-11
 */
@Component("SystemInitializationListener")
public class SystemInitializationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = Logger.getLogger(SystemInitializationListener.class);

    @Resource
    private SystemBiz systemBiz;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("System initialization begin...");

        try {
            systemBiz.initBasicData();
        } catch (Exception e) {
            logger.error(String.format("System initialization error: %s", e.getMessage()), e);
        }

        logger.info("System initialization end...");
    }

}
