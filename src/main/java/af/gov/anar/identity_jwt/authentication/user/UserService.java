package af.gov.anar.identity_jwt.authentication.user;

import java.util.List;
import java.util.Optional;

import af.gov.anar.identity_jwt.authentication.profession.Profession;

public interface UserService {

    public User create(String userJson);
    public User delete(Long id);
    public List findAll();
    public List findAllByEnv(String envSlug);

    public User findById(Long id);
    public User findByUsername(String username);
    public boolean update(Long id, String userJson);
    public void updateAvatar(User user, String avatarFilename);
    public User getLoggedInUser();
    public User getLoggedInUser(Boolean forceFresh);
    public String getSecurityContextHolderUsername(Boolean forceFresh);
    public boolean isAdmin();
    public String getCurrentEnv();

    public Profession findProfessionByUserId(Long userId);
    public List<Profession> findAllProfessionsByEnv(String envSlug);
    // public List<String> findDistinctJobs(String envSlug);

    public long countByEnvSlug(String envSlug);
    public long countByEnvSlugAndActive(String envSlug, Boolean active);
    public User updatePreferences(String preferences);
	public boolean updateUserPassword(String currentPassword, String newPassword);
    public boolean updateUserOdkPassword(String currentPassword, String newPassword);
    public Optional<User> findByEmail(String email);
    public void createPasswordResetTokenForUser(User user, String token, boolean active);
    public boolean validatePasswordResetToken(String token);
    public boolean createNewPassword(String newPassword, String confirmPassword, String token);
}
