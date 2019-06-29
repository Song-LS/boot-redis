package com.sls.receiver;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sls
 **/
@Slf4j
public class Receiver {

    public void messageReceiver(String message) {
        log.info("监听者1收到信息：{}", message);
    }
}
