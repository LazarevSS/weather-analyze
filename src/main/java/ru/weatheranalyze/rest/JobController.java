package ru.weatheranalyze.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.weatheranalyze.sheduler.common.JobDefinition;
import ru.weatheranalyze.sheduler.service.JobService;

import java.util.List;

@Api(description = "Контроллер для работы с шедулерами, есть возможность для запуска, приостановления, снятия с паузы")
@RestController
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @ApiOperation(
        value = "Получить все запущенные джобы(включая находящиеся на паузе)"
    )
    @GetMapping("/jobs")
    public List<String> all() throws SchedulerException {
        return jobService.findAll();
    }

    @ApiOperation(
        value = "Получить статус конкретного джоба(NORMAL - работает в штатном режиме, PAUSED - приостановлен, NONE - не запущен)"
    )
    @GetMapping("/jobs/{name}/status")
    public ResponseEntity<Trigger.TriggerState> status(@PathVariable("name") JobDefinition definition) throws SchedulerException {
        return ResponseEntity.ok(jobService.status(definition));
    }

    @ApiOperation(
        value = "Получить cron expression конкретного джоба(временной промежуток выполения джоба)"
    )
    @GetMapping("/jobs/{name}/cronExpression")
    public ResponseEntity<String> cronExpression(@PathVariable("name") JobDefinition definition) throws SchedulerException {
        return ResponseEntity.ok(jobService.getCronExpression(definition));
    }

    @ApiOperation(
        value = "Запустить джоб, либо снять с паузы"
    )
    @PutMapping("/jobs/{name}/start")
    public ResponseEntity<String> start(@PathVariable("name") JobDefinition definition,
                                        @RequestParam(value = "cron", required = false) String cronExpression) throws SchedulerException {
        jobService.trigger(definition, cronExpression);
        return ResponseEntity.ok("SUCCESS");
    }

    @ApiOperation(
        value = "Перезапустить джоб"
    )
    @PutMapping("/jobs/{name}/reschedule")
    public ResponseEntity<String> reschedule(@PathVariable("name") JobDefinition definition,
                                             @RequestParam(value = "cron") String cronExpression) throws SchedulerException {
        jobService.reshedule(definition, cronExpression);
        return ResponseEntity.ok("SUCCESS");
    }

    @ApiOperation(
        value = "Приостановить работу джоба"
    )
    @PutMapping("/jobs/{name}/pause")
    public ResponseEntity<String> pause(@PathVariable("name") JobDefinition definition) throws SchedulerException {
        jobService.pause(definition);
        return ResponseEntity.ok("SUCCESS");
    }
}
