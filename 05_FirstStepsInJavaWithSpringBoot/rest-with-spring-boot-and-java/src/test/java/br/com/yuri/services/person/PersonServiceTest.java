package br.com.yuri.services.person;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.yuri.data.vo.PersonVO;
import br.com.yuri.exceptions.RequiredObjectIsNullException;
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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
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
		assertTrue(result.toString().contains("links: [<http://localhost/person/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testFindAll() {
		List<Person> list = input.mockEntityList();
		when(repository.findAll()).thenReturn(list);
		var result = service.findAll();

		assertNotNull(result);
		assertEquals(14, result.size());

		var personOne = result.get(1);

		assertNotNull(personOne);
		assertEquals("First Name Test1", personOne.getFirstName());
		assertEquals("Last Name Test1", personOne.getLastName());
		assertEquals("Addres Test1", personOne.getAddress());
		assertEquals("Female", personOne.getGender());
	}

	@Test
	void testCreate() {
		Person persisted = input.mockEntity(1);
		persisted.setId(1L);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.save(any(Person.class))).thenReturn(persisted);

		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [<http://localhost/person/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testCreateWithNullPerson() {

		PersonVO vo = null;
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.create(vo));

		String expectedMessage = "Person cannot be null";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUpdate() {
		Person entity = input.mockEntity(1);
        entity.setId(1L);

		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(entity);

		var result = service.update(vo, 1L);

		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [<http://localhost/person/1>;rel=\"self\"]"));
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Addres Test1", result.getAddress());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testUpdateWithNullPerson() {
		PersonVO vo = null;
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> service.update(vo, 1L));

		String expectedMessage = "Person cannot be null";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);

		when(repository.findById(1L)).thenReturn(Optional.of(entity));

		service.delete(1L);
	}

}
