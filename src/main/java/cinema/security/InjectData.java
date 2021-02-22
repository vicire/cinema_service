package cinema.security;

import cinema.model.Role;
import cinema.model.RoleName;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

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
