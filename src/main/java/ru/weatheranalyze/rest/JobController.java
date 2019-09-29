package ru.weatheranalyze.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.weatheranalyze.sheduler.common.JobDefinition;
import ru.weatheranalyze.sheduler.service.JobService;

import java.util.List;


@SwaggerDefinition(tags = {
        @Tag(name = "job", description = "Executing and setting for application jobs")
})
@Api(tags = {"job"})
@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @ApiOperation(
        value = "Get all executed jobs(include jobs in status 'PAUSED')"
    )
    @GetMapping("/jobs")
    public List<String> all() throws SchedulerException {
        return jobService.findAll();
    }

    @ApiOperation(
        value = "Get the status of a specific job(NORMAL - executing now, PAUSED - executed early, but paused now, NONE - not started)"
    )
    @GetMapping("/jobs/{name}/status")
    public ResponseEntity<Trigger.TriggerState> status(@PathVariable("name") JobDefinition definition) throws SchedulerException {
        return ResponseEntity.ok(jobService.status(definition));
    }

    @ApiOperation(
        value = "Get a cron expression of  specific job"
    )
    @GetMapping("/jobs/{name}/cron-expression")
    public ResponseEntity<String> cronExpression(@PathVariable("name") JobDefinition definition) throws SchedulerException {
        return ResponseEntity.ok(jobService.getCronExpression(definition));
    }

    @ApiOperation(
        value = "Execute job or change a status of job from PAUSED to NORMAL"
    )
    @PutMapping("/jobs/{name}/start")
    public ResponseEntity<String> start(@PathVariable("name") JobDefinition definition,
                                        @RequestParam(value = "cron", required = false) String cronExpression) throws SchedulerException {
        jobService.trigger(definition, cronExpression);
        return ResponseEntity.ok("SUCCESS");
    }

    @ApiOperation(
        value = "Re-execute job"
    )
    @PutMapping("/jobs/{name}/reschedule")
    public ResponseEntity<String> reschedule(@PathVariable("name") JobDefinition definition,
                                             @RequestParam(value = "cron") String cronExpression) throws SchedulerException {
        jobService.reshedule(definition, cronExpression);
        return ResponseEntity.ok("SUCCESS");
    }

    @ApiOperation(
        value = "Pause the job"
    )
    @PutMapping("/jobs/{name}/pause")
    public ResponseEntity<String> pause(@PathVariable("name") JobDefinition definition) throws SchedulerException {
        jobService.pause(definition);
        return ResponseEntity.ok("SUCCESS");
    }
}
