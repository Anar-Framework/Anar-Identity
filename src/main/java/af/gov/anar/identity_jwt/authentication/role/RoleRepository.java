package af.gov.anar.identity_jwt.authentication.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
	public List<Role> findByEnvSlug(String envSlug);
}
