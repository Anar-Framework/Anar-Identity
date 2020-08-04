package af.gov.anar.identity_jwt.authentication.permission.impl;

import java.util.List;

import af.gov.anar.identity_jwt.authentication.permission.Permission;
import af.gov.anar.identity_jwt.authentication.permission.PermissionRepository;
import af.gov.anar.identity_jwt.authentication.permission.PermissionService;
import af.gov.anar.identity_jwt.authentication.user.User;
import af.gov.anar.identity_jwt.authentication.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Permission> findAll() {
		return permissionRepository.findAll();
	}

	@Override
	public List<Permission> findAllByUserAndEnv(User user, String envSlug) {
		System.out.println("**************** USER ID IS "+user.getId()+" AND USER ENVSLUG IS "+envSlug+"*****************");
		return permissionRepository.findAllByUserAndEnv(user.getId(), envSlug);
	}
}
