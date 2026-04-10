package com.rays.user;

import java.text.SimpleDateFormat;

public class TestUserModel {

	public static void main(String[] args) throws Exception {
		testAdd();
//		testFindByLogin();
//		testAuthenticate();
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
	


}
