package af.gov.anar.identity_jwt.authentication.role;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface RoleService {

    public Role create(Role role);
    public List<Role> delete(Long id);
    public List<Role> findAll();
    public List<Role> findAllByEnv(String envSlug);
    public Role findById(Long id);
    public boolean update(Long id, Role role);
}
