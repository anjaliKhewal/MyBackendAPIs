/**
 * 
 */
package com.myapp.service.dbconnection;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Component;

import com.myapp.service.products.MyProducts;


/**
 * @author Anjali
 *
 */
@Component
public class MyDbDetails {
	
	private static int IdfromDb=145;
	
	public MyProducts crud(int id) {
		
		MyProducts myprod = new MyProducts();
		@SuppressWarnings("deprecation")
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		//create(session);
		myprod = read(session, id);
		
		/*Configuration cfg = new Configuration().addResource("hibernate.cfg.xml").configure();
	     StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	     SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
	     Session session = sessionFactory.openSession();
	     logger.debug(" connection with the database created successfuly."); */
	     
	   /*  Configuration configuration = new Configuration();
	     configuration.configure("hibernate.cfg.xml");
	     ServiceRegistry  serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();        
	     SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	     Session session = sessionFactory.openSession();
	     read(session);*/

		session.close();
		
		return myprod;
				
	}
	
	private MyProducts read(Session session, int id) {
		System.out.println("read: ");
		Query q = session.createQuery("select prods from MyProducts prods where prods.id= :id");
		q.setParameter("id", id);
		
		List<MyProducts> produts = q.list();
		System.out.println("MyProduct details: ");
		
		   System.out.println("Reading product records...");
		   System.out.printf("%-30.30s  %-30.30s  %-30.30s%n", "Id", "Name","Quantity");
		   MyProducts prodReturn = new MyProducts();
		   if(produts!= null && produts.size()>0 ) {
		   for (MyProducts p : produts) {
			   System.out.printf("%-30.30s  %-30.30s  %-30.30s%n", p.getProductId(), p.getProductName(),p.getQuantity());
			   		IdfromDb = p.getProductId()+1;
			   		prodReturn = p;
		           }		
		   }
		   return prodReturn;
	}
	
	private void create(Session session) {
		System.out.println("create session for products table");
		MyProducts myprod = new MyProducts();
		/*int random=2;
		for (int i = 10; i < 100; i++) { 
			 random = (int)(Math.random() * 3) + 2;
			//System.out.println(random); 
		} */
		
		System.out.println(IdfromDb);
		myprod.setProductId(IdfromDb);
		myprod.setProductName("notebook");
		myprod.setQuantity(9);
		
		session.beginTransaction();
		session.save(myprod);
		session.getTransaction().commit();
	}

}
