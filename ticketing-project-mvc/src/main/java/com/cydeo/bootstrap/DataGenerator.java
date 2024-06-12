package com.cydeo.bootstrap;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Gender;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.RoleService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import com.cydeo.service.impl.RoleServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    public DataGenerator(RoleService roleService, UserService userService, ProjectService projectService, TaskService taskService) {
        this.roleService = roleService;
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    //This run method will execute FIRST BEFORE ANYTHING when you start the application
    @Override
    public void run(String... args) throws Exception {

        //create some roles and put in the DB(map)  -->We will use DTO as we do not have a true database to utilize entities
        RoleDTO adminRole = new RoleDTO(1L,"Admin");
        RoleDTO managerRole = new RoleDTO(2L, "Manager");
        RoleDTO employeeRole = new RoleDTO(3L,"Employee");

        //create users and put in the DB(map)
            //RoleServiceImpl rs = new RoleServiceImpl();
                // -> Spring does not like new keyword because it is tightly coupled
                //We are controlling this way and need to use IoC (inversion of control) and let Spring control for us


        roleService.save(adminRole);
        roleService.save(managerRole);
        roleService.save(employeeRole);

        //Sample Users

        UserDTO user1 = new UserDTO("John", "Kesy", "john@cydeo.com","Abc1",
                true, "1234567890", managerRole, Gender.MALE);
        UserDTO user2 = new UserDTO("Edward", "Cruz", "edward@cydeo.com","Abc2",
                true, "1234567891", adminRole, Gender.MALE);
        UserDTO user3 = new UserDTO("Jason", "Macintosh", "jason@cydeo.com","Abc3",
                true, "1234567892", managerRole, Gender.MALE);
        UserDTO user4 = new UserDTO("Edward", "Cruz", "edward@cydeo.com","Abc4",
                true, "1234567893", adminRole, Gender.MALE);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);


        ProjectDTO project1 = new ProjectDTO("Spring MVC", "PR001", user1, LocalDate.now(), LocalDate.now().plusDays(25), "Creating Controllers", Status.OPEN );
        ProjectDTO project2 = new ProjectDTO("Spring ORM", "PR002", user2, LocalDate.now(), LocalDate.now().plusDays(10), "Creating Database", Status.IN_PROGRESS );
        ProjectDTO project3 = new ProjectDTO("Spring Container", "PR003", user1, LocalDate.now(), LocalDate.now().plusDays(32), "Creating Container", Status.IN_PROGRESS );

        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);

        TaskDTO task1 = new TaskDTO(1L,project1, user1, "Controller", "Request Mapping", Status.IN_PROGRESS, LocalDate.now().minusDays(4));
        TaskDTO task2 = new TaskDTO(2L,project3, user2, "Configuration", "Database Connection", Status.COMPLETE, LocalDate.now().minusDays(12));
        TaskDTO task3 = new TaskDTO(3L,project3, user3, "Mapping", "One-To-Many", Status.COMPLETE, LocalDate.now().minusDays(8));
        TaskDTO task4 = new TaskDTO(4L,project2, user4, "Dependency Injection", "Autowired", Status.IN_PROGRESS, LocalDate.now().minusDays(20));

        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);

    }
}
