package study.restapi.Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.restapi.Data.DbContext;
import study.restapi.Entities.Person;
import study.restapi.Exceptions.ServerErrorException;
import study.restapi.Exceptions.BadRequestException;
import study.restapi.Exceptions.NotFoundException;

@RestController
@RequestMapping("person")
public class PersonController {
	private final DbContext dbContext;

	public PersonController() throws SQLException {
		dbContext = new DbContext();
	}

	@GetMapping
	public List<Person> getAll() {
		try {
			return dbContext.getAll();
		} catch (SQLException e) { }
		return new ArrayList<Person>();
	}

	@GetMapping("{id}")
	public Person get(@PathVariable int id) {
		try {
			return dbContext.getById(id);
		} catch (SQLException e) { }

		throw new NotFoundException("No object found with specified id.");
	}

	@PostMapping
	public Person add(@RequestBody Person person) {
		try {
			dbContext.add(person);

			var list = dbContext.getAll();
			var inserted = list.get(list.size() - 1);

			person.setId(inserted.getId());
			return person;
		} catch (SQLException e) {
			if (person.getName() == null) {
				throw new BadRequestException("Value of name cannot be null.");
			}
		}		
		
		throw new ServerErrorException("Internal server error.");
	}

	@PutMapping("{id}")
	public Person update(@PathVariable int id, @RequestBody Person person) {
		person.setId(id);

		try {
			return dbContext.update(person);
		} catch (SQLException e) { }

		throw new NotFoundException("No object found with specified id.");
	}
	
	@DeleteMapping("{id}")
	public Person delete(@PathVariable int id) {
		try {
			Person person = dbContext.getById(id);
			dbContext.delete(id);
			return person;
		} catch (SQLException e) { }
		
		throw new NotFoundException("No object found with specified id.");
	}
}
