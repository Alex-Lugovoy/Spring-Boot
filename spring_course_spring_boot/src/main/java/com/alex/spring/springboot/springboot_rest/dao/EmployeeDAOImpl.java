package com.alex.spring.springboot.springboot_rest.dao;

import com.alex.spring.springboot.springboot_rest.entity.Employee;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Employee> getAllEmployees() {
//        Session session = entityManager.unwrap(Session.class);
//        List<Employee> employees = session.createQuery("from Employee"
//                , Employee.class).getResultList();
        Query query = entityManager.createQuery("from Employee");
        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public void saveEmployee(Employee emp) {
//        Session session = entityManager.unwrap(Session.class);
//        session.saveOrUpdate(emp);
        Employee newemp = entityManager.merge(emp);
        emp.setId(newemp.getId());
    }

    @Override
    public Employee getEmployee(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        Employee emp = session.get(Employee.class, id);
        Employee employee = entityManager.find(Employee.class,id);
        return employee;
    }

    @Override
    public void deleteEmployee(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        Employee emp = session.get(Employee.class,id);
//        session.delete(emp);
        Query query = entityManager.createQuery("delete from Employee "+ "where id = :employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
