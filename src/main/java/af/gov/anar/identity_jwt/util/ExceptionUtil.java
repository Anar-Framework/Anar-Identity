package af.gov.anar.identity_jwt.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;

import af.gov.anar.identity_jwt.authentication.user.User;
import af.gov.anar.identity_jwt.authentication.user.UserService;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class ExceptionUtil {
    static Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);
    
    @Value("${spring.mail.to}")
	private String emailTo;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailUtil emailUtil;
    
    public static void dumpException(Throwable exception, File file) {
        FileOutputStream fos = null;
        PrintStream ps = null;
        try {
            fos = new FileOutputStream(file);
            ps = new PrintStream(fos);  
            exception.printStackTrace(ps);  
        } catch (FileNotFoundException e) {
        	logger.trace("dumpException: ", e);
        } finally {
            IOUtils.closeQuietly(ps);
            IOUtils.closeQuietly(fos);
        }
    }

    public void notifyDevTeam(String emailSubj, Exception e, User currentLoggedInUser, String envSlug, File attachment, HttpServletRequest request, boolean reThrowExcep) throws Exception {
        String errorStr = ExceptionUtils.getStackTrace(e);
        if (!(e instanceof AccessDeniedException)){
            emailUtil.sendMessageWithDetails(emailTo, emailSubj, errorStr, attachment,
                currentLoggedInUser, envSlug , request);  
        }

        if(reThrowExcep) {
            throw e;
        }
    }
 
}