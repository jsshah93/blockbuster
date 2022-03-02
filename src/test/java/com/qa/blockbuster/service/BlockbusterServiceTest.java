package com.qa.blockbuster.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.blockbuster.domain.Blockbuster;
import com.qa.blockbuster.repo.BlockbusterRepo;

@SpringBootTest
@ActiveProfiles("test")

public class BlockbusterServiceTest {
	
	private Blockbuster newBlock; 
	private Blockbuster savedBlock;
	
	@Autowired 
	private BlockbusterService service;
	
	@MockBean
	private BlockbusterRepo repo;
	
	@BeforeEach
	void setUp() {
		newBlock = new Blockbuster("Spider-man", "2021-12-25", 10);
		savedBlock = new Blockbuster(1L, "Spider-man", "2021-12-25", 10);
		System.out.println("before");
	}
	
	@Test
	void testCreate() {
		System.out.println("create");
		Mockito.when(this.repo.save(newBlock)).thenReturn(savedBlock);
		assertThat(this.service.create(newBlock)).isEqualTo(savedBlock);
		Mockito.verify(this.repo, Mockito.times(1)).save(newBlock);
	}
	
	@Test
	void testUpdate() {
		long id = 1L;
		Blockbuster toUpdate = new Blockbuster("Taken", "2015-10-20", 8);
		Optional<Blockbuster> optBlock = Optional.of(new Blockbuster(id, null, null, 0));
		Blockbuster updated = new Blockbuster(id, toUpdate.getMovieName(), toUpdate.getMovieRelDate(), toUpdate.getMovieRating());
		
		Mockito.when(this.repo.findById(id)).thenReturn(optBlock);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);
		
		assertThat(this.service.update(id, toUpdate)).isEqualTo(updated);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		System.out.println("update");
		}
	
	@Test
	void testDelete() {
		long id = 1L;
		Optional<Blockbuster> optBlock = Optional.of(new Blockbuster(id, null, null, 0));
		Blockbuster deleted = optBlock.get();
		Mockito.when(this.repo.findById(id)).thenReturn(optBlock);
		assertThat(this.service.delete(id)).isEqualTo(deleted);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	
}
