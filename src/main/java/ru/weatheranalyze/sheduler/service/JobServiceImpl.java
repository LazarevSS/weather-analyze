package ru.weatheranalyze.sheduler.service;

import lombok.val;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.weatheranalyze.sheduler.common.CommonJobDetailBuilder;
import ru.weatheranalyze.sheduler.common.CommonTriggerDetailBuilder;
import ru.weatheranalyze.sheduler.common.JobDefinition;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final Scheduler scheduler;

    @Autowired
    public JobServiceImpl(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public List<String> findAll() throws SchedulerException {
        return scheduler.getJobKeys(GroupMatcher.anyJobGroup()).stream()
            .map(JobKey::getName)
            .collect(Collectors.toList());
    }

    @Override
    public void trigger(JobDefinition definition, String cronExpression) throws SchedulerException {
        val jobKey = findByName(definition.getName());
        if (Objects.nonNull(jobKey)) {
            scheduler.resumeJob(jobKey);
        } else {
            createJob(definition, cronExpression);
        }
    }

    @Override
    public void reshedule(JobDefinition definition, String cronExpression) throws SchedulerException {
        val job = findByName(definition.getName());
        if (Objects.nonNull(job)) {
            TriggerKey key = TriggerKey.triggerKey(definition.getName() + "Trigger");
            Trigger trigger = CommonTriggerDetailBuilder.buildTrigger(cronExpression, scheduler.getJobDetail(job),
                definition.getName() + "Trigger");
            scheduler.rescheduleJob(key, trigger);
        }
    }

    private void createJob(JobDefinition definition, String cronExpression) throws SchedulerException {
        val jobDetail = CommonJobDetailBuilder.buildJobDetail(definition.getClazz(), definition.getName());
        Trigger trigger = CommonTriggerDetailBuilder.buildTrigger(
            cronExpression, jobDetail, definition.getName() + "Trigger");
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Override
    public void pause(JobDefinition definition) throws SchedulerException {
        val job = findByName(definition.getName());
        if (Objects.nonNull(job)) {
            scheduler.pauseJob(job);
        }
    }

    @Override
    public Trigger.TriggerState status(JobDefinition definition) throws SchedulerException {
        val trigger = scheduler.getTrigger(TriggerKey.triggerKey(definition.getName() + "Trigger"));
        if (Objects.isNull(trigger))
            return Trigger.TriggerState.NONE;
        return scheduler.getTriggerState(trigger.getKey());
    }

    @Override
    public String getCronExpression(JobDefinition definition) throws SchedulerException {
        val trigger = scheduler.getTrigger(TriggerKey.triggerKey(definition.getName() + "Trigger"));
        if (trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger) trigger;
            return cronTrigger.getCronExpression();
        }
        return "";
    }

    private JobKey findByName(String name) throws SchedulerException {
        return scheduler.getJobKeys(GroupMatcher.anyJobGroup()).stream()
            .filter((k) -> k.getName().equals(name))
            .findFirst()
            .orElse(null);
    }
}
