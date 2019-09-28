package ru.weatheranalyze.sheduler.service;

import org.quartz.SchedulerException;
import org.quartz.Trigger;
import ru.weatheranalyze.sheduler.common.JobDefinition;

import java.util.List;

public interface JobService {

    List<String> findAll() throws SchedulerException;

    void trigger(JobDefinition definition, String cronExpression) throws SchedulerException;

    void reshedule(JobDefinition definition, String cronExpression) throws SchedulerException;

    void pause(JobDefinition definition) throws SchedulerException;

    Trigger.TriggerState status(JobDefinition definition) throws SchedulerException;

    String getCronExpression(JobDefinition definition) throws SchedulerException;
}
