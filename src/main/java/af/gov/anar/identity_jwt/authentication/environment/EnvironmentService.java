package af.gov.anar.identity_jwt.authentication.environment;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface EnvironmentService {

    public Environment create(Environment environment);
    public Environment delete(Long id);
    public List findAll();
    public Environment findById(Long id);
    public Environment findBySlug(String slug);
    public Environment findBySecretKey(String secretKey);
    public boolean update(Long id, Environment environment);
}