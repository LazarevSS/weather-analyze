package ru.weatheranalyze.sheduler.common;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

public class CommonTriggerDetailBuilder {

    public static Trigger buildTrigger(String cronExpression, JobDetail jobDetail, String triggerName) {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        return TriggerBuilder
            .newTrigger()
            .forJob(jobDetail)
            .withIdentity(triggerName)
            .withSchedule(scheduleBuilder)
            .build();
    }
}
