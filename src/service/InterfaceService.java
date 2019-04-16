/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import javafx.collections.ObservableList;

/**
 *
 * @author khaled
 */
public interface InterfaceService<T> {
    
    public void insert(T o);
    public void delete(T o);
    public boolean update(T os);
    public ObservableList<T> DisplayAll();
    public T DisplayById(int id);
    
    
}
