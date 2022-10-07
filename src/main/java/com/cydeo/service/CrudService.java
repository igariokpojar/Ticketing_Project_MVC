package com.cydeo.service;

import java.util.List;

public interface CrudService<T,ID> { // what ever is coming for all services
    // GENERIC!! why? bc these methods we can write one time and base on the parameter is excepting it can execute that Structure (if it is User, Role..)=>
    //=> if I needed I can come back and write it more.. these are fundamental services
    T save(T user); // save user
    T findById(ID username); // find user base on  his ID
    List<T> findAll();
    void deleteById(ID username); // delete user base on  his ID
    void update(T object);



}
