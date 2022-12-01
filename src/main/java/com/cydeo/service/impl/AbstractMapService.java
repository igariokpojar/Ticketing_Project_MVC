package com.cydeo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapService <T,ID>{ // why Abstract?bc all the implementation is going to be similar as well.. so we don't need to write again and again
    // later on the SpringBoot is providing you free this
    public Map<ID,T> map = new HashMap<>(); // create custom temporary DB to save the Data .. why Map? bc Map is A key(ID) and (T)value

    // prepare a template.. this structure can work for any service
    T save(ID id,T object){
        map.put(id,object);
        return object;
    }

    List<T> findAll(){ // find all the users
        return new ArrayList<>(map.values()); // Collections  it will return List of Objects we ar going to put inside
    }

    T findById(ID id){

        return map.get(id);
    }

    void deleteById(ID id){

        map.remove(id);
    }

    void update(ID id, T object){
        map.put(id,object);
    }

}
