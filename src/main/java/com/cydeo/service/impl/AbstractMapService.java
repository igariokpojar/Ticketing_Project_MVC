package com.cydeo.service.impl;


import java.util.*;
// we created abstract class to avoid implementing same methods again and again
public abstract class AbstractMapService <T,ID>{ // we don't need the Objects from Abstract class

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

     void  update (ID id, T object){
         map.put(id,object);
     }
}
