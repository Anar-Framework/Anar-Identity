package af.gov.anar.identity_jwt.eventlog.impl;

import java.util.List;

import af.gov.anar.identity_jwt.eventlog.EventLog;
import af.gov.anar.identity_jwt.eventlog.EventLogRepository;
import af.gov.anar.identity_jwt.eventlog.EventLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EventLogServiceImpl implements EventLogService{

    @Autowired
    private EventLogRepository eventLogRepository;

    public List<EventLog> findAll() {
        return eventLogRepository.findAll();
    }

    public EventLog store(EventLog eventLog) {
        return null;
    }

    public List<EventLog> findByUser(Long userId) {
        // return eventLogRepository.findByUserId(userId);
        return eventLogRepository.findAll();
    }

    public EventLog findById(long id) {
        return eventLogRepository.findById(id).get();
    }

    
    public List<EventLog> findByLoggerLevel(String level) {
        return eventLogRepository.findByLoggerLevel(level);
    }

    @Async
    public void log(Long userId, String envSlug, String logger, String loggerLevel, String exception, String detailsMessage){
        EventLog eventLog = new EventLog(userId, envSlug, logger, loggerLevel, exception, detailsMessage);
        eventLogRepository.save(eventLog);
    }
}