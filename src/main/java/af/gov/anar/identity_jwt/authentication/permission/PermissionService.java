package af.gov.anar.identity_jwt.authentication.permission;

import java.util.List;

import af.gov.anar.identity_jwt.authentication.user.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface PermissionService  {	   
	public List findAll();
	public List<Permission> findAllByUserAndEnv(User user, String envSlug);
}
