package af.gov.anar.identity_jwt.authentication.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{
    public List<Group> findByEnvSlug(String envSlug);
    public List<Group> findByIdNotIn(List<Long> groupIds);
}
