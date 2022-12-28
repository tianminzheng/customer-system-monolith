package org.geekbang.projects.cs.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.HashMap;
import java.util.Map;

public class CustomCounter {

    private String name;
    private String tagName;
    private MeterRegistry registry;

    private Map<String, Counter> counters = new HashMap<>();

    public CustomCounter(String name, String tagName, MeterRegistry registry) {
        this.name = name;
        this.tagName = tagName;
        this.registry = registry;
    }

    public void increment(String tagValue) {
        Counter counter = counters.get(tagValue);
        if(counter == null) {
            counter = Counter.builder(name).tags(tagName, tagValue).register(registry);
            counters.put(tagValue, counter);
        }

        counter.increment();
    }

    public double getCount(String tagValue) {
        return counters.get(tagValue).count();
    }
}
