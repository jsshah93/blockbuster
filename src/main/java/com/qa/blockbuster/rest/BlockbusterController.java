package com.qa.blockbuster.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.blockbuster.domain.Blockbuster;
import com.qa.blockbuster.service.BlockbusterService;

@RestController

public class BlockbusterController {

	private BlockbusterService s;

	public BlockbusterController(BlockbusterService s) {
		super();
		this.s = s;

	}

	@PostMapping("/createMovie")
	public ResponseEntity<Blockbuster> addMovie(@RequestBody Blockbuster b) {
		return new ResponseEntity<>(this.s.create(b), HttpStatus.CREATED);
	}

	@GetMapping("/getMovie")
	public List<Blockbuster> getMovie() {
		return this.s.read();
	}

	@GetMapping("/getMovie/{id}")
	public Blockbuster getOne(@PathVariable Long id) {
		return this.s.readOne(id);
	}

	@PutMapping("/updateMovie/{id}")
	public Blockbuster updateMovie(@PathVariable Long id, @RequestBody Blockbuster bl) {
		return this.s.update(id, bl);
	}

	@DeleteMapping("/deleteMovie/{id}")
	public Blockbuster deleteMovie(@PathVariable Long id) {
		return this.s.delete(id);
	}

}
