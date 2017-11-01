package com.twitteranalyser.apachespark;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spark")
@Log4j
public class SparkController {

    @Autowired
    private SparkService sparkManager;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public boolean start() {
        sparkManager.start();
        log.info("Apache Spark initialized");
        return true;
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public boolean stop() {
        sparkManager.stop();
        log.info("Apache Spark stopped");
        return true;
    }

}
