package com.qa.blockbuster.service;

import java.util.List;

public interface BlockbusterInterface<I> {

	I create(I createI);
	
	List<I> read();
	
	I update(Long id, I updateI);
	
	I delete(Long id);
	
	I readOne(Long id);
	
}
