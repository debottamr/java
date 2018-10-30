package com.app.inheritancetableperclass;

import org.hibernate.Session;

/*
 Advantage:

    Possible to define NOT NULL constraints on the table.

Disadvantage:

    Tables are not normalized.
    To support polymorphism either container has to do multiple trips to database or use SQL UNION kind of feature.

 */
public class App {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Vehicle vehicle = new Vehicle();
		vehicle.setVehicleName("Car");

		TwoWheeler twoWheeler = new TwoWheeler();
		twoWheeler.setVehicleName("Bike");
		twoWheeler.setSteeringTwoWheeler("Bike Steering Handle");

		FourWheeler fourWheeler = new FourWheeler();
		fourWheeler.setVehicleName("Alto");
		fourWheeler.setSteeringFourWheeler("Alto Steering Wheel");

		session.save(vehicle);
		session.save(twoWheeler);
		session.save(fourWheeler);

		session.getTransaction().commit();
		session.close();
		HibernateUtil.shutdown();

	}

}