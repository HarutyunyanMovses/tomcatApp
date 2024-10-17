package com.Tomcat.repository.addressRepositoryImpl;

import com.Tomcat.model.enttis.Address;
import com.Tomcat.repository.AddressRepository;
import com.Tomcat.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddressRepositoryJPA implements AddressRepository {
    @Override
    public void addAddress(Address address) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction =  session.beginTransaction();
            session.save(address);
            transaction.commit();
            session.close();
    }
}
