package derek.shirley.springboot.test.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserRoles")
public class UserRoles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 19802348902345L;

	@Id
	private UserRole userRole;
}