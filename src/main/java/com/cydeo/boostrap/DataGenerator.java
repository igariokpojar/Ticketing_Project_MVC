package com.cydeo.boostrap;

import com.cydeo.dto.RoleDTO;
import org.springframework.boot.CommandLineRunner;

public class DataGenerator implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

        // This run method will execute FIRST BEFORE ANYTHING WHEN you start the application
        // create some users and put the DB


       // create some roles and put in the DB(map)
        RoleDTO adminRole = new RoleDTO(1L,"Admin");
        RoleDTO managerRole = new RoleDTO(2L,"Manager");
        RoleDTO employeeRole = new RoleDTO(3L,"Employee");

    }

}
