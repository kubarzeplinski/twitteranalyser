package com.twitteranalyser.apachespark;

import com.twitteranalyser.apachespark.settings.SparkSettings;
import lombok.extern.log4j.Log4j;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spark")
@Log4j
public class SparkController {

    @Autowired
    private SparkService sparkManager;

    @PostMapping(value = "/start")
    public boolean start(@RequestBody String keyWord) {
        JavaStreamingContext javaStreamingContext = sparkManager.start();
        log.info("Apache Spark initialized");
        return true;
    }

    @GetMapping(value = "/stop")
    public boolean stop() {
        sparkManager.stop();
        log.info("Apache Spark stopped");
        return true;
    }

    @PostMapping(value = "/setCustomSettings")
    public boolean setCustomSettings(@RequestBody SparkSettings settings) {
        JavaStreamingContext javaStreamingContext = sparkManager.setCustomSettings(settings);
        log.info("Apache Spark custom settings set");
        return true;
    }

}
