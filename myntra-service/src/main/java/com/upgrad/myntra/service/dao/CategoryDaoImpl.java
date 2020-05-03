package com.upgrad.myntra.service.dao;


import com.upgrad.myntra.service.entity.CategoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * CategoryDao class provides the database access for all the endpoints in Category controller.
 */
@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao{

    //When a container of the application(be it a Java EE container or any other custom container like Spring) manages the lifecycle of the Entity Manager, the Entity Manager is said to be Container Managed. The most common way of acquiring a Container Managed EntityManager is to use @PersistenceContext annotation on an EntityManager attribute.
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CategoryEntity getCategoryById(String categoryId){
        try {
            return entityManager.createNamedQuery("getCategoryById", CategoryEntity.class).setParameter("uuid", categoryId).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<CategoryEntity> getAllCategoriesOrderedByName(){
        try {
            return entityManager.createNamedQuery("getAllCategoriesOrderedByName", CategoryEntity.class).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<CategoryEntity> getCategoriesByBrand(String brandId) {
        try {
            return entityManager.createNamedQuery("getCategoriesBybrand", CategoryEntity.class).setParameter("uuid",brandId).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
