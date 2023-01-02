package org.geekbang.projects.cs.metrics;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

public class CustomerStaffCounter {

    private static SimpleMeterRegistry registry = new SimpleMeterRegistry();

    private static CustomCounter customCounter = new CustomCounter("customerStaff", "phone", registry);

    public static void countPhoneNumber(String phoneNumber) {
        customCounter.increment(phoneNumber);
    }

    public static double getPhoneNumberCount(String phoneNumber) {
        return customCounter.getCount(phoneNumber);
    }

}
