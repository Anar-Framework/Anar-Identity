package af.gov.anar.identity_jwt.authentication.profession;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import af.gov.anar.identity_jwt.authentication.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Profession")
@Table(name = "profession")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profession_generator")
    @SequenceGenerator(name = "profession_generator", sequenceName = "profession_seq", allocationSize = 1)
    @Column(unique = true, updatable = false, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "remark")
    private String remark;

    @Column(name = "active", length = 1, nullable = false)
    private boolean active;

    @Column(name = "env_slug")
    private String envSlug;
    
    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    // @JoinColumn(name = "job_id")
    private Collection<User> users;
}