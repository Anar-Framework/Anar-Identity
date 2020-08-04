package af.gov.anar.identity_jwt.authentication.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public interface CustomUserService extends UserDetailsService {
	public CustomUser loadUserByUsername(String username);
	public CustomUser loadUserByUsername(String username, String currentEnv, String currentLang);

	public String getCurrentEnv();
}
