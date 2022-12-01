package com.cydeo.service;
import com.cydeo.dto.RoleDTO;


// this Interface is dynamic.. we can come after 10 years and add some info in bc this is the business logic
public interface RoleService extends CrudService<RoleDTO,Long> { // if we use the RoleService the methods inside the CrudService should be able to work with the RoleDTO and Long Object



}
