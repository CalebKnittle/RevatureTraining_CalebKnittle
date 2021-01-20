package dev.knittle.daos;

import dev.knittle.entities.SuperUser;

public interface SuperUserDAO extends UserDAO {

	//CREATE
	public boolean createSuperUser(SuperUser superuser);
	
	//READ
	public SuperUser getSuperUserByID(int userID);
	
	public SuperUser getSuperUserByUsername(String username);
	
	//Use UserDAO methods to UPDATE and DELETE
	
}
