package af.gov.anar.identity_jwt.authentication.environment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, Long> {

    public Environment findBySlug(String slug);
    public Environment findBySecretKey(String secretKey);
}