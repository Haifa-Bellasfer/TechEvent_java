/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

public interface InterfaceService<T> {
    public void insert(T o);
    public void delete(T o);
    public List<T> DisplayAll();
    public T DisplayById(int id);
    public boolean update(T os);
}
