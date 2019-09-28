package ru.weatheranalyze.sheduler.common;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;

public class CommonJobDetailBuilder {

    public static JobDetail buildJobDetail(Class<? extends Job> clazz, String name) {
        return JobBuilder.newJob(clazz)
            .withIdentity(name)
            .storeDurably().build();
    }
}
