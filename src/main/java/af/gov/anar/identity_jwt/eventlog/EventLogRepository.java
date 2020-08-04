package af.gov.anar.identity_jwt.eventlog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLogRepository extends JpaRepository<EventLog, Long> {

    List<EventLog> findByLoggerLevel(String level);
    // List<EventLog> findByUserId(long userId);
}