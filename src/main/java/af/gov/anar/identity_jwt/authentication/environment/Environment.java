package af.gov.anar.identity_jwt.authentication.environment;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import af.gov.anar.identity_jwt.authentication.user.User;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "environment")
public class Environment {
 
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "environment_generator")
	@SequenceGenerator(name="environment_generator", sequenceName = "environment_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private long id;
	
	@Column(name = "slug", unique = true)
	private String slug;
 
	@Column(name = "name")
	private String name;
 
	@Column(name = "description")
	private String description;

	@Column(name = "secret_key", unique = true)
	private String secretKey;
 
	@Column(name = "active")
	private boolean active;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@ManyToMany(mappedBy = "environments")
    @JsonIgnore
	private Set<User> users;
 
	public Environment(String name, String description) {
		this.name = name;
		this.description = description;
		this.active = false;
	}

	@Override
	public String toString() {
        return "Form [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active + ", envSlug=" + slug + ", created_at=" + createdAt  + ", updated_at=" + updatedAt + "]";
	}
}