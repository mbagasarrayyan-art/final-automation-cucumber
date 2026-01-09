package com.bagas.finalproject.web.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/web")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.bagas.finalproject.web.steps")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@web")
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty," +
                "html:build/reports/cucumber/web/cucumber.html," +
                "json:build/reports/cucumber/web/cucumber.json"
)
public class WebTestRunner { }
