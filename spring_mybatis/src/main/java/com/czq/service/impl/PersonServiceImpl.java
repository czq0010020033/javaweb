package com.czq.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.czq.dao.PersonDao;
import com.czq.entity.Person;
import com.czq.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;

	@Override
	public List<Person> queryAll() {

		return personDao.queryAll();
	}

}
