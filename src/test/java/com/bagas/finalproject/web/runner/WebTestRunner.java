package com.bagas.finalproject.web.runner;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features/web")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.bagas.finalproject.web.steps")
@ConfigurationParameter(
        key = Constants.PLUGIN_PROPERTY_NAME,
        value = "pretty, html:build/reports/cucumber/web/cucumber.html, json:build/reports/cucumber/web/cucumber.json"
)
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@web")
public class WebTestRunner {
}
