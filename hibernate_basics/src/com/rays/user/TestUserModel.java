package com.rays.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class TestUserModel {

	public static void main(String[] args) throws Exception {
//		testAdd();
//		testFindByLogin();
//		testAuthenticate();
//		testSearch();
//		testUpdate();
	}
		private static void testAdd() throws Exception {
	

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			UserModel model = new UserModel();
			UserDTO dto = new UserDTO();

			dto.setFirstName("Ram");
			dto.setLastName("Sharma");
			dto.setLoginId("ram123@gmail.com");
			dto.setPassword("ram123");
			dto.setAddress("Indore");
			dto.setDob(sdf.parse("2001-01-01"));

			model.add(dto);

		}
		private static void testFindByLogin() {
			UserDTO dto = new UserDTO();
			UserModel model = new UserModel();

			dto = model.findByLogin("ram123@gmail.com");

			if (dto != null) {
				System.out.print(dto.getId());
				System.out.print("\t" + dto.getFirstName());
				System.out.print("\t" + dto.getLastName());
				System.out.print("\t" + dto.getLoginId());
				System.out.print("\t" + dto.getPassword());
				System.out.print("\t" + dto.getDob());
				System.out.println("\t" + dto.getAddress());
			} else {
				System.out.println("user not found");
			}	
		}
		private static void testAuthenticate() {
			UserDTO dto = new UserDTO();
			UserModel model = new UserModel();

			dto = model.authenticate("ram@gmail.com", "ram123");

			if (dto != null) {
				System.out.print(dto.getId());
				System.out.print("\t" + dto.getFirstName());
				System.out.print("\t" + dto.getLastName());
				System.out.print("\t" + dto.getLoginId());
				System.out.print("\t" + dto.getPassword());
				System.out.print("\t" + dto.getDob());
				System.out.println("\t" + dto.getAddress());
			} else {
				System.out.println("invalid login or password");
			}
		}
		

		private static void testUpdate() throws Exception {

		    
		        UserModel model = new UserModel();

		        // Create DTO object
		        UserDTO dto = new UserDTO();

		        // Set existing ID (must already exist in DB)
		        dto.setId(3);

		        // Set updated values
		        dto.setFirstName("Romit");
		        dto.setLastName("Verma");
		        dto.setLoginId("romit@gmail.com");
		        dto.setPassword("rom123");
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        Date dob = sdf.parse("1995-08-15");
		        dto.setDob(dob);;
		        dto.setAddress("Bhopal");

		        // Call update method
		        model.update(dto);

		        System.out.println("Record Updated Successfully !!");
		    
		}
		private static void testSearch() {

			UserModel model = new UserModel();
			UserDTO dto = new UserDTO();

			//dto.setFirstName("r");
			
			List<UserDTO> list = model.search(dto, 1, 10);

			Iterator<UserDTO> it = list.iterator();

			while (it.hasNext()) {
				dto = it.next();
				System.out.print(dto.getId());
				System.out.print("\t" + dto.getFirstName());
				System.out.print("\t" + dto.getLastName());
				System.out.print("\t" + dto.getLoginId());
				System.out.print("\t" + dto.getPassword());
				System.out.print("\t" + dto.getDob());
				System.out.println("\t" + dto.getAddress());
			}
		}
		}
