package org.jboss.spring.examples.primrose.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.jboss.spring.examples.primrose.entity.Flower;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class FlowerDaoImpl implements FlowerDao
{
   protected SessionFactory sessionFactory;

   Class<Flower> clazz = Flower.class;

   @Autowired
   public void setSessionFactory(SessionFactory sessionFactory) {
       this.sessionFactory = sessionFactory;
   }

   protected Session getCurrentSession() {
       return this.sessionFactory.getCurrentSession();
   }

   @Transactional(readOnly = true)
   public List<Flower> getAllFlowers() {
       return getCurrentSession().createCriteria(clazz).list();
   }

   public List<Flower> getForLocation(String location) {
       return (List<Flower>) getCurrentSession().createQuery("from Flower where location = :location")
           .setParameter("location", location).list();
   }

   public Flower water(Flower flower, double amount) {
      flower.setLastWatered(new Date());
      return (Flower) getCurrentSession().merge(flower);
   }

   public void create(Flower flower) {
       getCurrentSession().merge(flower);
   }

   public void delete(Flower flower) {
       getCurrentSession().delete(flower);
   }

   @Transactional(readOnly = true)
   public Flower find(long id) {
       return (Flower) getCurrentSession().get(clazz, id);
   }
}
