package af.gov.anar.identity_jwt.eventlog;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface EventLogService {

    public List<EventLog> findAll();

    public EventLog store(EventLog eventLog);

    public List<EventLog> findByUser(Long userId);

    public List<EventLog> findByLoggerLevel(String level);

    public void log(Long userId, String envSlug, String logger, String loggerLevel, String exception, String detailsMessage);

	public EventLog findById(long id);
}