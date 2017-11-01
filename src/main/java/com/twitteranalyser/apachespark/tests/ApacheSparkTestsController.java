package com.twitteranalyser.apachespark.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApacheSparkTestsController {

    @Autowired
    ApacheSparkTestsService apacheSparkTestsService;

    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @RequestMapping(value = "/apacheSpark/test")
    public void testMethod() {
        apacheSparkTestsService.testMethod();
    }

}
