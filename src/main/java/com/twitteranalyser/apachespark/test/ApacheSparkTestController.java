package com.twitteranalyser.apachespark.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sparktest")
public class ApacheSparkTestController {

    @Autowired
    ApacheSparkTestService apacheSparkTestsService;

    @GetMapping()
    public String index() {
        return "index.html";
    }

    @GetMapping(value = "/apacheSpark/test")
    public void testMethod() {
        apacheSparkTestsService.testMethod();
    }

    @GetMapping(value = "/apacheSpark/testWithNeo4jMethod")
    public void testWithNeo4jMethod() {
        apacheSparkTestsService.testWithNeo4jMethod();
    }

}
