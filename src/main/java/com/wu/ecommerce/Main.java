package com.wu.ecommerce;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

import com.wu.ecommerce.dto.Product;
import com.wu.ecommerce.dto.User;
import com.wu.ecommerce.exception.DataNotFoundException;
import com.wu.ecommerce.exception.IdNotFoundException;
import com.wu.ecommerce.exception.InvalidIdException;
import com.wu.ecommerce.exception.InvalidPriceException;
import com.wu.ecommerce.service.ProductService;
import com.wu.ecommerce.service.ProductServiceImpl;
import com.wu.ecommerce.service.UserService;
import com.wu.ecommerce.service.UserServiceImpl;

import lombok.ToString;
import com.wu.ecommerce.utils.DBUtils;

public class Main {

	public static void main(String[] args) {

		ProductService productService = ProductServiceImpl.getInstance();
		UserService userService = UserServiceImpl.getInstance();

		Scanner sc = new Scanner(System.in).useLocale(Locale.US);
		ArrayList<Product> p1 = null;
		while (true) {
			System.out.println(
					"Enter Your Choice\n1. Add Product\n2.Get By ID\n3.Get By Categoty\n4.Get All Product\n5.Remove By ID\n6. Update By Id\n7.Exit");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				try {
					System.out.println("Enter for 0:product, 1:User");
					int add_ch = sc.nextInt();
					if (add_ch == 1) {
						System.out.println("Enter Details\n1.ID, 2.Name, 3.Price, 4.Category");
						String id = sc.next();
						String first = sc.next();
						String last = sc.next();
						String contact = sc.next();
						userService.addUser(new User(id, first, last, contact));
					} else {
						System.out.println("Enter Details\n1.ID, 2.Name, 3.Price, 4.Category");
						String id = sc.next();
						String name = sc.next();
						float price = sc.nextFloat();
						String cat = sc.next();
						productService.addProduct(new Product(id, name, price, cat));

					}
				} catch (InvalidIdException | InvalidPriceException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 2:
				try {
					System.out.println(productService.getProductById("prod1"));
				} catch (IdNotFoundException | SQLException | InvalidIdException | InvalidPriceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					p1 = (ArrayList<Product>) productService.getAllProductByCategory("Tech");
				} catch (DataNotFoundException | SQLException | InvalidIdException | InvalidPriceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // productService.getProducts();
				for (Product product : p1) {
					System.out.println(product);
				}
				break;

			case 4:
				try {
					p1 = (ArrayList<Product>) productService.getProducts();
				} catch (DataNotFoundException | SQLException | InvalidIdException | InvalidPriceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (Product product : p1) {
					System.out.println(product);
				}
				break;
			case 5:
				try {
					System.out.println(productService.removeProductByProductId("prod1"));
				} catch (IdNotFoundException | SQLException | InvalidIdException | InvalidPriceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 6:
				try {
					System.out.println("Enter Details to Update\n1.ID, 2.Name, 3.Price, 4.Category");
					String id = sc.next();
					String name = sc.next();
					float price = sc.nextFloat();
					String cat = sc.next();
					System.out.println(productService.updateProductByProductId(id, new Product(id, name, price, cat)));
				} catch (InvalidIdException | InvalidPriceException | SQLException | IdNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
			if (ch == 7) {
				break;
			}
		}
		System.out.println("Done");
//		userService.addUser(new User("userid1", "Shru", "Deshmukh", "123456789"));
//		userService.addUser(new User("userid2", "Anju", "Parbhane", "123456789"));	
	}

}
