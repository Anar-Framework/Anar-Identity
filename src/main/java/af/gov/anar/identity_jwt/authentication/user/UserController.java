package af.gov.anar.identity_jwt.authentication.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import af.gov.anar.identity_jwt.authentication.group.Group;
import af.gov.anar.identity_jwt.authentication.group.GroupService;
import af.gov.anar.identity_jwt.authentication.profession.Profession;
import af.gov.anar.identity_jwt.util.ExceptionUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping({ "/api/users" })
@Slf4j
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${spring.mail.to}")
    private String emailTo;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private GroupService groupService;
    // @Autowired
    // private CustomUserService customUserService;

    @Autowired
    private ExceptionUtil exceptionUtil;

    private String failureEmailSub = "500 - ASIMS Exception - User";
    
    ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public List<User> findAll() {
        logger.info("Entry UserController>findAll() - GET");
        String envSlug = userAuthService.getCurrentEnv();
        return userAuthService.findAllByEnv(envSlug);
    }

    @GetMapping("/{id}")
    public ObjectNode detail(@PathVariable("id") Long id) {
        User user = userAuthService.findById(id);
        // CustomUser customUser = customUserService.loadUserByUsername(user.getUsername());
        List<Group> groupList = groupService.findAllByEnv(userAuthService.getCurrentEnv());
        ArrayNode allGroups = mapper.valueToTree(groupList);
        JsonNode userJson = mapper.convertValue(user, JsonNode.class);
        JsonNode groupJson = mapper.convertValue(user.getGroups(), JsonNode.class);
        JsonNode jobsJson = mapper.convertValue(user.getJob(), JsonNode.class);
        // JsonNode authoritiesJson = mapper.convertValue(customUser.getAuthorities(), JsonNode.class);
        ObjectNode userObj = mapper.createObjectNode();
        List<Profession> professionsList = this.userAuthService.findAllProfessionsByEnvSlug(userAuthService.getCurrentEnv());
        // List<Job> jobsList = this.daoUtil.findAllJobs( userAuthService.getCurrentEnv());
        ArrayNode jobsNode = mapper.valueToTree(professionsList);
        userObj.set("user", userJson);
        userObj.set("job", jobsJson);
        userObj.set("allJobs", jobsNode);
        userObj.set("groups", groupJson);
        // userObj.set("authorities", authoritiesJson);
        userObj.set("allGroups", allGroups);
        return userObj;
    }

    @PostMapping
    public User create(@RequestBody String userPayload, HttpServletRequest request) throws Exception {
        logger.info("Entry UserController>CREATE() - POST");
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return userAuthService.create(userPayload);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable("id") Long id, @RequestBody String userPayload, HttpServletRequest request)
            throws Exception {
        logger.info("Entry UserController>update() - PUT");
        // Gson gson=new Gson();
        // User newUser=gson.fromJson(user, User.class);
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return userAuthService.update(id, userPayload);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }

    @GetMapping(value = "/{id}/groups", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Group> getLoggedInUserGroups(@PathVariable(value = "id", required = true) Long loggedInUserId) {
        return userAuthService.findById(loggedInUserId).getGroups();
    }

    @PutMapping(value = "/preferences")
    public User updatePreferences(@RequestBody String preferences, HttpServletRequest request) throws Exception {
        logger.info("Entry UserController>updatePreferences() - PUT");
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return userAuthService.updatePreferences(preferences);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return null;
        }
    }

    @PutMapping(value = "/cpassword")
    public boolean updateUserPassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest request) throws Exception {
        logger.info("Entry UserController>updateUserPassword() - PUT");

        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return userAuthService.updateUserPassword(currentPassword, newPassword);
        } catch (Exception e) {

            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }

    @PutMapping(value = "/change-odk-password")
    public boolean updateUserOkdPassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, HttpServletRequest request) throws Exception {
        
        logger.info("Entry UserController>updateUserOkdPassword() - PUT");
       
        User currentLoggedInUser = userAuthService.getLoggedInUser();
        try {
            return userAuthService.updateUserOdkPassword(currentPassword, newPassword);
        } catch (Exception e) {
            exceptionUtil.notifyDevTeam(failureEmailSub, e, currentLoggedInUser, userAuthService.getCurrentEnv(), null, request, false);
            return false;
        }
    }
      /**
         * fetch necessary info to create a new user
         * it includes groups, jobs 
         * @returns ObjectNode of the response object
         * */
    @GetMapping(value = "/creation-data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ObjectNode getCreationRequiredData() {
        List<Profession> professionsList = userAuthService.findAllProfessionsByEnvSlug(userAuthService.getCurrentEnv());
        List<Group> groupList = groupService.findAllByEnv(userAuthService.getCurrentEnv());
        ArrayNode allGroups = mapper.valueToTree(groupList);
        ArrayNode professionsNode = mapper.valueToTree(professionsList);
        ObjectNode dataNode = mapper.createObjectNode();
        dataNode.set("groups", allGroups);
        dataNode.set("professions", professionsNode);
        return dataNode; 
    }
}
