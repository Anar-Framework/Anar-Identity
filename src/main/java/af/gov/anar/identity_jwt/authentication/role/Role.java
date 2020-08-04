package af.gov.anar.identity_jwt.authentication.role;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import af.gov.anar.identity_jwt.authentication.group.Group;
import af.gov.anar.identity_jwt.authentication.permission.Permission;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "Role")
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
	@SequenceGenerator(name="role_generator", sequenceName = "role_seq", allocationSize = 1)
	@Column(unique = true, updatable = false, nullable = false)
	private Long id;

	@Column
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "active", length = 1, nullable = false)
    private boolean active;

	@Column(name = "env_slug")
	private String envSlug;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
    private Collection<Group> groups;

	@ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "role_permission", 
        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    @JsonIgnore
    private Collection<Permission> permissions; 
    
    public Role(Long id,String name,String desc, Collection<Permission> permissions)
    {
    	this.id=id;
    	this.name=name;
    	this.description=desc;
    	this.permissions=permissions;
    }
    
	public Role(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
        return "Form [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active + ", envSlug=" + envSlug + "]";
	}
}
