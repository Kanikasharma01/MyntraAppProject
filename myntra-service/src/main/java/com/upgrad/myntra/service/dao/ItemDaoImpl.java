package com.upgrad.myntra.service.dao;


import com.upgrad.myntra.service.entity.ItemEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * ItemDao class provides the database access for all the endpoints in Item controller.
 */
@Repository
public class ItemDaoImpl implements ItemDao{

    //When a container of the application(be it a Java EE container or any other custom container like Spring) manages the lifecycle of the Entity Manager, the Entity Manager is said to be Container Managed. The most common way of acquiring a Container Managed EntityManager is to use @PersistenceContext annotation on an EntityManager attribute.
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<ItemEntity> getItemsByCategoryAndBrand(String brandId, String categoryId) {
        try {
            return entityManager.createNamedQuery("getItemsByCategoryAndbrand", ItemEntity.class).setParameter("brandId", brandId).setParameter("categoryId", categoryId).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
