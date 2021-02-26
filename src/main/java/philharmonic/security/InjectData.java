package philharmonic.security;

import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import philharmonic.model.Role;
import philharmonic.model.RoleName;
import philharmonic.model.User;
import philharmonic.service.RoleService;
import philharmonic.service.UserService;

@Component
public class InjectData {
    private final RoleService roleService;
    private final UserService userService;

    public InjectData(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    private void injectRoles() {
        Role admin = new Role();
        admin.setRoleName(RoleName.ADMIN);
        roleService.add(admin);
        Role user = new Role();
        user.setRoleName(RoleName.USER);
        roleService.add(user);
        User firstAdmin = new User();
        firstAdmin.setEmail("bohdan@gmail.com");
        firstAdmin.setPassword("12345");
        firstAdmin.setRoles(Set.of(admin));
        userService.add(firstAdmin);
    }
}
