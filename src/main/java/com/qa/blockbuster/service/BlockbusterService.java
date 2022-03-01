package com.qa.blockbuster.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.blockbuster.domain.Blockbuster;
import com.qa.blockbuster.repo.BlockbusterRepo;

@Service
public class BlockbusterService implements BlockbusterInterface<Blockbuster> {

	private BlockbusterRepo r;

	public BlockbusterService(BlockbusterRepo repo) {
		super();
		this.r = repo;
	}

	@Override
	public Blockbuster create(Blockbuster createI) {
		// TODO Auto-generated method stub
		return this.r.save(createI);
	}

	@Override
	public List<Blockbuster> read() {
		// TODO Auto-generated method stub
		return this.r.findAll();
	}

	@Override
	public Blockbuster update(Long id, Blockbuster updateI) {
		// TODO Auto-generated method stub
		Optional<Blockbuster> toFind = this.r.findById(id);
		Blockbuster found = toFind.get();
		found.setMovieName(updateI.getMovieName());
		found.setMovieRelDate(updateI.getMovieRelDate());
		found.setMovieRating(updateI.getMovieRating());
		return this.r.save(found);
	}

	@Override
	public Blockbuster delete(Long id) {
		// TODO Auto-generated method stub
		Optional<Blockbuster> toDelete = this.r.findById(id);
		this.r.deleteById(id);
		return toDelete.orElse(null);
	}

	@Override
	public Blockbuster readOne(Long id) {
		// TODO Auto-generated method stub
		Optional<Blockbuster> optionalC = this.r.findById(id);
		return optionalC.orElse(null);
	}

}
