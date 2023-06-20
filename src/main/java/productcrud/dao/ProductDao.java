package productcrud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import productcrud.model.Product;

@Component
public class ProductDao
{
	@Autowired
   private HibernateTemplate hibernateTemplate;
	
	//create
	@Transactional
	public void createProoduct(Product product)
	{
		this.hibernateTemplate.saveOrUpdate(product);
	}
	
	//grt all product
	public List<Product> getProduct()
	{
		List<Product> products = this.hibernateTemplate.loadAll(Product.class);
		return products;
	}
	
	//delete the single product
	@Transactional
	public void deleteProduct(int pid)
	{
		
		//first I am fetching the  id  data using load method of hibernate store in variable
		Product p =this.hibernateTemplate.load(Product.class, pid);
		//using delete method iam deleting the data
		this.hibernateTemplate.delete(p);
	}
	
	//get single product
	public Product getsingleProduct(int pid)
	{
		return this.hibernateTemplate.get(Product.class, pid);
	}
}
