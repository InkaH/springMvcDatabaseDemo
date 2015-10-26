package com.springcookbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springcookbook.dao.UserDAO;

/**
 * Controller class.
 * 
 * @author Inka
 */

//@Controller annotation on class name declares this class as spring bean
@Controller
//@RequestMapping annotation declares that this class is default handler for all requests of type ‘/’
@RequestMapping("/")
public class UserController {

	//	Because of @Autowired, the userDAO field will be automatically initialized by Spring using
	//	dependency injection.
	@Autowired
	private UserDAO userDAO;
}
