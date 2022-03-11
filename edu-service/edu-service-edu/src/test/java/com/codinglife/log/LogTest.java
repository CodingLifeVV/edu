package com.codinglife.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


/**
 * @author CodingLife
 * @Description TODD
 * @since 2022/3/7 16:08
 */
@Slf4j
public class LogTest {
    @Test
    public void testLog4j2() {
        log.trace("trace1");
        log.debug("debug1");
        log.warn("warn1");
        log.info("info1");
        log.error("error1");
    }
}
