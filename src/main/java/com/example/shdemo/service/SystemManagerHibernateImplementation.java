package com.example.shdemo.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Console;
import com.example.shdemo.domain.Brand;

@Component
@Transactional
public class SystemManagerHibernateImplementation implements SystemManager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addConsole(Console console) {
        console.setId(null);
        sessionFactory.getCurrentSession().persist(console);
    }

    @Override
    public void deleteConsole(Console console) {
        console = (Console) sessionFactory.getCurrentSession().get(Console.class, console.getId());
        sessionFactory.getCurrentSession().delete(console);
    }

    @Override
    public void updateConsole(Long id, String name) {
        Console console = (Console) sessionFactory.getCurrentSession().get(Console.class, id);

        console.setName(name);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Console> getAllConsoles() {
        return sessionFactory.getCurrentSession().getNamedQuery("console.all").list();
    }

    @Override
    public Console searchConsoleByName(String name) {
        return (Console) sessionFactory.getCurrentSession().getNamedQuery("console.byName").setString("name", name).uniqueResult();
    }

    @Override
    public void addBrand(Brand brand) {
        brand.setId(null);
        sessionFactory.getCurrentSession().persist(brand);
    }

    @Override
    public void deleteBrand(Brand brand) {
        brand = (Brand) sessionFactory.getCurrentSession().get(Brand.class, brand.getId());
        sessionFactory.getCurrentSession().delete(brand);
    }

    @Override
    public void updateBrand(Brand brand, Brand newBrand) {
        brand = (Brand) sessionFactory.getCurrentSession().get(Brand.class, brand.getId());

        brand.setBrandName(newBrand.getBrandName());
        brand.setCountry(newBrand.getCountry());

        sessionFactory.getCurrentSession().update(brand);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Brand> getAllBrands() {
        return sessionFactory.getCurrentSession().getNamedQuery("brand.all").list();
    }

    @Override
    public Brand searchBrandByName(String brandName) {
        return (Brand) sessionFactory.getCurrentSession().getNamedQuery("brand.byBrandName").setString("brandName", brandName).uniqueResult();
    }
}
