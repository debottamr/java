package com.app.criteria;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class App2 {

	public void executeEqualsCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.eq("name", "Mouse"));
		List results = crit.list();
		displayProductsList(results);
	}

	public void executeNotEqualsCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.ne("name", "Mouse"));
		List results = crit.list();
		displayProductsList(results);
	}

	public void executeLikePatternCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.like("name", "Mou%"));
		List results = crit.list();
		displayProductsList(results);
	}

	public void executeILikeMatchModeCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.ilike("name", "browser", MatchMode.END));
		List results = crit.list();
		displayProductsList(results);
	}

	public void executeNullCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.isNull("name"));
		List results = crit.list();
		displayProductsList(results);
	}

	private void displayProductsList(List results) {
		// TODO Auto-generated method stub

	}

	public void executeGreaterThanCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.gt("price", new Double(25.0)));
		List results = crit.list();
		displayProductsList(results);
	}

	public void executeAndCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.gt("price", new Double(25.0)));
		crit.add(Restrictions.like("name", "K%"));
		List results = crit.list();
		displayProductsList(results);
	}

	public void executeOrCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		Criterion price = Restrictions.gt("price", new Double(25.0));
		Criterion name = Restrictions.like("name", "Mou%");
		LogicalExpression orExp = Restrictions.or(price, name);
		crit.add(orExp);
		List results = crit.list();
		displayProductsList(results);
	}

	public void executeAndOrCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		Criterion price = Restrictions.gt("price", new Double(25.0));
		Criterion name = Restrictions.like("name", "Mou%");
		LogicalExpression orExp = Restrictions.or(price, name);
		crit.add(orExp);
		crit.add(Restrictions.ilike("description", "blocks%"));
		List results = crit.list();
		displayProductsList(results);
	}

	public void executeDisjunctionCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		Criterion price = Restrictions.gt("price", new Double(25.0));
		Criterion name = Restrictions.like("name", "Mou%");
		Criterion desc = Restrictions.ilike("description", "blocks%");
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(price);
		disjunction.add(name);
		disjunction.add(desc);
		crit.add(disjunction);
		List results = crit.list();
		displayProductsList(results);
	}

	public void executeSQLCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.sqlRestriction("{alias}.name like 'Mou%'"));
		List results = crit.list();
		displayProductsList(results);
	}

	public void executePagingCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.setFirstResult(1);
		crit.setMaxResults(2);
		List results = crit.list();
		displayProductsList(results);
	}

	public void executeUniqueResultCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		Criterion price = Restrictions.gt("price", new Double(25.0));
		crit.setMaxResults(1);
		Product product = (Product) crit.uniqueResult();
		// test for null here if needed

		List results = new ArrayList();
		results.add(product);
		displayProductsList(results);
	}

	public void executeUniqueResultExceptionCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		Criterion price = Restrictions.gt("price", new Double(25.0));
		Product product = (Product) crit.uniqueResult();
		// test for null here if needed

		List results = new ArrayList();
		results.add(product);
		displayProductsList(results);
	}

	public void executeOrderCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.gt("price", new Double(25.0)));
		crit.addOrder(Order.desc("price"));
		List results = crit.list();
		displayProductsList(results);
	}

	public void executeOneToManyAssociationsCriteria(Session session) {
		Criteria crit = session.createCriteria(Supplier.class);
		Criteria prdCrit = crit.createCriteria("products");
		prdCrit.add(Restrictions.gt("price", new Double(25.0)));
		List results = crit.list();
		displaySupplierList(results);
	}

	public void executeAssociationsSortingCriteria(Session session) {
		Criteria crit = session.createCriteria(Supplier.class);
		crit.addOrder(Order.desc("name"));
		Criteria prdCrit = crit.createCriteria("products");
		prdCrit.add(Restrictions.gt("price", new Double(25.0)));
		// prdCrit.addOrder(Order.desc("price"));
		List results = prdCrit.list();
		displaySupplierList(results);
	}

	public void executeManyToOneAssociationsCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		Criteria suppCrit = crit.createCriteria("supplier");
		suppCrit.add(Restrictions.eq("name", "MegaInc"));
		List results = crit.list();

		displayProductsList(results);
	}

	public void executeQBECriteria(Session session) {
		Criteria crit = session.createCriteria(Supplier.class);
		Supplier supplier = new Supplier();
		supplier.setName("MegaInc");
		crit.add(Example.create(supplier));
		List results = crit.list();

		displaySupplierList(results);
	}

	private void displaySupplierList(List results) {
		// TODO Auto-generated method stub

	}

	public void executeNotNullOrZeroQBECriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		Product exampleProduct = new Product();
		exampleProduct.setName("Mouse");
		Example example = Example.create(exampleProduct);
		example.excludeZeroes();
		crit.add(example);
		List results = crit.list();

		displayProductsList(results);
	}

	public void executeQBEAdvancedCriteria(Session session) {
		Criteria prdCrit = session.createCriteria(Product.class);
		Product product = new Product();
		product.setName("M%");
		Example prdExample = Example.create(product);
		prdExample.excludeProperty("price");
		prdExample.enableLike();
		Criteria suppCrit = prdCrit.createCriteria("supplier");
		Supplier supplier = new Supplier();
		supplier.setName("SuperCorp");
		suppCrit.add(Example.create(supplier));
		prdCrit.add(prdExample);
		List results = prdCrit.list();

		displayProductsList(results);
	}

	public void executeRowCountCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.setProjection(Projections.rowCount());
		List results = crit.list();
		displayObjectList(results);
	}

	private void displayObjectList(List results) {
		// TODO Auto-generated method stub

	}

	public void executeAggregatesCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.max("price"));
		projList.add(Projections.min("price"));
		projList.add(Projections.avg("price"));
		projList.add(Projections.countDistinct("description"));
		crit.setProjection(projList);
		List results = crit.list();
		displayObjectsList(results);
	}

	public void executeProjectionCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("name"));
		projList.add(Projections.property("description"));
		crit.setProjection(projList);
		List results = crit.list();
		displayObjectsList(results);
	}

	private void displayObjectsList(List results) {
		// TODO Auto-generated method stub

	}

	public void executeGroupByCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.groupProperty("name"));
		projList.add(Projections.property("price"));
		crit.setProjection(projList);
		List results = crit.list();
		displayObjectsList(results);
	}

	public void executeDistinctCriteria(Session session) {
		Criteria crit = session.createCriteria(Product.class);
		crit.add(Restrictions.gt("price", new Double(25.0)));
		crit.add(Restrictions.like("name", "K%"));
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List results = crit.list();
		displayProductsList(results);
	}

}
//https://howtodoinjava.com/hibernate/hibernate-criteria-queries-tutorial/