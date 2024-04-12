package com.anunciadores.dto;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.anunciadores.model.Persona;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


/**
 *
 * @author valbuena
 */

public class BooksCreationDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Object> books;

	// default and parameterized constructor

	public void addBook(Object book) {
		this.books.add(book);
	}}

