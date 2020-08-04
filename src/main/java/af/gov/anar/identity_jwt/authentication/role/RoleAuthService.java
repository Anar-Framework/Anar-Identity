package af.gov.anar.identity_jwt.authentication.role;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class RoleAuthService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RoleService roleService;

	@PreAuthorize("hasAuthority('ROLE_CREATE')")
	public Role create(Role role) {
		return roleService.create(role);
	}

	@PreAuthorize("hasAuthority('ROLE_DELETE')")
	public List<Role> delete(Long id) {
		return roleService.delete(id);
	}

	@PreAuthorize("hasAuthority('ROLE_LIST')")
	public List<Role> findAll() {
		return roleService.findAll();
	}

	@PreAuthorize("hasAuthority('ROLE_LIST')")
	public List<Role> findAllByEnv(String envSlug) {
		return roleService.findAllByEnv(envSlug);
	}

	@PreAuthorize("hasAuthority('ROLE_VIEW')")
	public Role findById(Long id) {
		return roleService.findById(id);
	}

	@PreAuthorize("hasAuthority('ROLE_EDIT')")
	public boolean update(Long id, Role role) {
		return roleService.update(id, role);
	}

}
