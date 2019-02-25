package com.dragon.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.dragon.book.mapper.TypeMapper;

import com.dragon.book.model.TType;

import com.dragon.book.service.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeMapper typeMapper;

	@Override
public List<TType> getAllTypes() {
	// TODO Auto-generated method stub
	return typeMapper.getAllType();
}
}
