package productcrud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.event.spi.PostCollectionRecreateEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import productcrud.dao.ProductDao;
import productcrud.model.Product;

@Controller
public class MainController 
{
	@Autowired
	private ProductDao productdao;//to save datea in database we get inject ProductDao object
	
	@RequestMapping("/")
	public String home(Model m)
	{
		List<Product> products=productdao.getProduct();
		m.addAttribute("products", products);
		return "index";
	}
	
	
	//show add product form
	@RequestMapping("/add-product")
	public String addProduct(Model m)
	{
		m.addAttribute("title", "add product");
		return "add_product_form";
	}
	
	//handle add product form
	
	@RequestMapping(value="handle-product",method=RequestMethod.POST )
	public RedirectView handleForm(@ModelAttribute Product product,HttpServletRequest request)
	{
		System.out.println(product);
		productdao.createProoduct(product);
		RedirectView redirectView=new RedirectView();
		//to redirct on home page we use / also use httpservletRequest obj
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView ;
	}
	
	//delete handler
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId,HttpServletRequest request)
	{
		this.productdao.deleteProduct(productId);
		RedirectView redirectView=new RedirectView();
		//to redirct on home page we use / also use httpservletRequest obj
		redirectView.setUrl(request.getContextPath()+"/");
		return redirectView ;
	}
	
	//update handler
	
	@RequestMapping("/update/{productId}")
	public String updateForm(@PathVariable("productId") int pid,Model model)
	{
		Product product=this.productdao.getsingleProduct(pid);
		model.addAttribute("product", product);
		return "update_form";
	}
	
	@RequestMapping("/home")
	public String homego()
	{
		return "home";
	}

}
