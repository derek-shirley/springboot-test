package derek.shirley.springboot.test.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

@Embeddable
public class UserRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 19802348902345L;

	@NotNull
	@JoinColumn(name = "userId")
	private User user;

	@NotNull
	@JoinColumn(name = "roleId")
	private Role role;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
