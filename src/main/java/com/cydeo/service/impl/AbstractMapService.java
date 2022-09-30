package com.cydeo.service.impl;


import java.util.*;

public abstract class AbstractMapService <T,ID>{

    public Map<ID,T> map = new HashMap<>(); // fake DB (ID-key) and (T-value)

     T save(ID id, T object){
         map.put(id,object);
         return object;
     }

     List<T> findAll(){
         return new ArrayList<>(map.values()); // it will return List
     }

     T findById(ID id){
         return map.get(id);
     }
     void deleteById(ID id){
         map.remove(id);
     }
}
