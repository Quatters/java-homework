package study.restapi.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import study.restapi.Entities.Person;

public class DbContext {
	private Connection connection;
	private Statement statement;

	public Person getById(int id) throws SQLException {
		var ps = connection.prepareStatement("SELECT * FROM person WHERE id = ?");
		ps.setInt(1, id);
		var result = ps.executeQuery();

		if (!result.next()) 
			throw new SQLDataException("No such found.");

		Person person = new Person();
		person.setId(id);
		person.setName(result.getString("name"));
		person.setAge(result.getInt("age"));
		person.setAbout(result.getString("about"));

		return person;
	}

	public List<Person> getAll() throws SQLException {
		var result = statement.executeQuery("SELECT * FROM person");
		List<Person> persons = new ArrayList<>();

		while (result.next()) {
			Person p = new Person();
			p.setId(result.getInt("id"));
			p.setName(result.getString("name"));
			p.setAge(result.getInt("age"));
			p.setAbout(result.getString("about"));

			persons.add(p);
		}

		return persons;
	}
	
	public void add(Person person) throws SQLException {
		var ps = connection.prepareStatement("INSERT INTO person (name, age, about) VALUES (?, ?, ?)");
		ps.setString(1, person.getName());
		if (person.getAge() == null)
			ps.setNull(2, Types.NULL);
		else
			ps.setInt(2, person.getAge());
		ps.setString(3, person.getAbout());

		ps.executeUpdate();
	}

	public Person update(Person person) throws SQLException {
		var ps = connection.prepareStatement("UPDATE person SET name = ?, age = ?, about = ? WHERE id = ?");
		Person existingPerson = getById(person.getId());

		if (person.getName() != null)
			ps.setString(1, person.getName());
		else {
			ps.setString(1, existingPerson.getName());
			person.setName(existingPerson.getName());
		}

		if (person.getAge() != null)
			ps.setInt(2, person.getAge());
		else {
			ps.setInt(2, existingPerson.getAge());
			person.setAge(existingPerson.getAge());
		}

		if (person.getAbout() != null)
			ps.setString(3, person.getAbout());
		else {
			ps.setString(3, existingPerson.getAbout());
			person.setAbout(existingPerson.getAbout());
		}

		ps.setInt(4, person.getId());

		ps.executeUpdate();

		return person;
	}

	public void delete(int id) throws SQLException {
		var ps = connection.prepareStatement("DELETE FROM person WHERE id = ?");
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	public DbContext() throws SQLException {
		String url = "jdbc:sqlite:local.db";
		connection = DriverManager.getConnection(url);
		statement = connection.createStatement();
	}
}
