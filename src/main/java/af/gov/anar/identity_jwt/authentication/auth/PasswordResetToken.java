package af.gov.anar.identity_jwt.authentication.auth;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import af.gov.anar.identity_jwt.authentication.user.User;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken {
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "token")
    private String token;

    @Column(name = "active")
    private Boolean active;
  
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
  
    @Column(name = "expiry_date")
    @CreationTimestamp
    private LocalDateTime expiryDate;

    public PasswordResetToken(String token, User user, boolean active){
        this.token = token;
        this.user = user;
        this.active = active;
    }

    public PasswordResetToken(){}
}