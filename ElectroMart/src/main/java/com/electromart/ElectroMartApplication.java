package com.electromart;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ElectroMartApplication {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()//
				.configure()//
				.addAnnotatedClass(Actors.class)//
				.buildSessionFactory();
		try (Session session = factory.openSession()) {
			var transaction = session.beginTransaction();
			var actor = session.get(Actors.class, 201);
			System.out.println(actor.getFirstName());
			actor.setFirstName("Ben");
			session.merge(actor);
			// for (var actor : actors) {
			// System.out.println(actor.getId() + ": " + actor.getFirstName() + " " +
			// actor.getLastName());
			// }
			transaction.commit();
		} finally {
			factory.close();
		}
	}

}
