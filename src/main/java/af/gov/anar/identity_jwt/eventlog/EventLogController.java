package af.gov.anar.identity_jwt.eventlog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/eventlogs"})
public class EventLogController {

    @Autowired private EventLogService eventLogService;

    @GetMapping(value = { "" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity<List<EventLog>> findAll()
    {
        return ResponseEntity.ok(eventLogService.findAll());
    }

    @GetMapping(value = { "/{id}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity<EventLog> findByid(@PathVariable(value = "id", required = true) long id)
    {
        return ResponseEntity.ok(eventLogService.findById(id));
    }

    @GetMapping(value = { "/user/{userId}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity<List<EventLog>> findByUser(@PathVariable(value = "userId", required = true) long id)
    {
        return ResponseEntity.ok(eventLogService.findByUser(id));
    }

    @GetMapping(value = { "/level/{level}" }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity<List<EventLog>> findByUser(@PathVariable(value = "level", required = true) String level)
    {
        return ResponseEntity.ok(eventLogService.findByLoggerLevel(level));
    }
}