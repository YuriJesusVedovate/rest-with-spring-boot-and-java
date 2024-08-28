package br.com.yuri.services.person;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import br.com.yuri.data.vo.PersonVO;
import br.com.yuri.models.Person;
import br.com.yuri.repositories.IPersonRepository;
import br.com.yuri.unittests.mapper.mocks.MockPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	MockPerson input;

	@InjectMocks
	private PersonService service;

	@Mock
	private IPersonRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		var result = service.findById(1L);

		assertNotNull(result);
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/entity/1>;rel=\"self\"]"));

		assertEquals("First Name Test0", result.getFirstName());
		assertEquals("Last Name Test0", result.getLastName());
		assertEquals("Addres Test0", result.getAddress());
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testCreate() {
		Person entity = input.mockEntity(1);

		Person persisted = entity;
		persisted.setId(1L);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(entity)).thenReturn(persisted);

		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/entity/1>;rel=\"self\"]"));

		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Addres Test1", result.getAddress());
	}

	@Test
	void testUpdate() {

		Person entity = input.mockEntity(1);

		Person persisted = entity;
		persisted.setId(1L);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);

		var result = service.update(vo, 1L);

		assertNotNull(result);
		assertNotNull(result.getLinks());

		assertTrue(result.toString().contains("links: [</api/entity/1>;rel=\"self\"]"));

		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Addres Test1", result.getAddress());
	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);
	}

}
