package com.czq.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.czq.entity.Person;
import com.czq.service.PersonService;

@Controller
@RequestMapping(value = "/person")
public class PersonController {
	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getPerson(HttpServletRequest request) {
		List<Person> list = personService.queryAll();
		
		request.setAttribute("list", list);
		
		return "person";
	}
}
