package com.upgrad.myntra.service.dao;


import com.upgrad.myntra.service.entity.BrandEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * BrandDao class provides the database access for all the endpoints in brand controller.
 */
@Repository
@Transactional
public class BrandDaoImpl implements BrandDao{

    //When a container of the application(be it a Java EE container or any other custom container like Spring) manages the lifecycle of the Entity Manager, the Entity Manager is said to be Container Managed. The most common way of acquiring a Container Managed EntityManager is to use @PersistenceContext annotation on an EntityManager attribute.
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public BrandEntity brandByUUID(String brandId) {
        try {
            return entityManager.createNamedQuery("brandsByUUID", BrandEntity.class).setParameter("uuid", brandId).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<BrandEntity> brandByName(String brandName) {
        try {
            return entityManager.createNamedQuery("brandsByName", BrandEntity.class).setParameter("brandName", brandName).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<BrandEntity> brandByRating() {
        try {
            return entityManager.createNamedQuery("brandsByRating", BrandEntity.class).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<BrandEntity> brandByCategory(String categoryId) {
        try {
            return entityManager.createNamedQuery("brandsByCategory", BrandEntity.class).setParameter("uuid",categoryId).getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

}
