package com.quangsinh.cleanarchitecture;

import com.quangsinh.cleanarchitecture.controller.UserController;
import com.quangsinh.cleanarchitecture.infrastructure.MySQLUserRepository;
import com.quangsinh.cleanarchitecture.infrastructure.SQLServerUserRepository;
import com.quangsinh.cleanarchitecture.usecase.UserServiceImplementation;

public class Main {
	public static void main(String[] args) {
		MySQLUserRepository mysqlRepo = new MySQLUserRepository();
		UserServiceImplementation mysqlService = new UserServiceImplementation(mysqlRepo);
		UserController mysqlController = new UserController(mysqlService);
		testController(mysqlController);

		SQLServerUserRepository sqlServerRepo = new SQLServerUserRepository();
		UserServiceImplementation sqlServerService = new UserServiceImplementation(sqlServerRepo);
		UserController sqlServerController = new UserController(sqlServerService);
		testController(sqlServerController);
	}

	private static void testController(UserController controller) {
	}
}