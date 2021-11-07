import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ProginEnjoyer {
	@JSONField(name = "Name", ordinal = 1)
	private String name;

	@JSONField(name = "Surname", ordinal = 2)
	private String surname;

	@JSONField(name = "Birthdate", ordinal = 3, format = "dd.MM.yyyy")
	private Date birthdate;

	@JSONField(name = "Lives in", ordinal = 4)
	private String hostel;

	@JSONField(name = "Favorite items", ordinal = 5)
	private List<String> favItems;

	public ProginEnjoyer() { }

	public ProginEnjoyer(String name, String surname, Date birthdate, String hostel, List<String> favItems) {
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.hostel = hostel;
		this.favItems = favItems;
	}

	public ProginEnjoyer(String fileName) throws IOException {
		if (!fileName.endsWith(".json")) fileName = fileName + ".json";
		var readJson = Files.readAllLines(Path.of(fileName));
		var converted = JSON.parse(readJson.toString()).toString();
		var enjoyer = JSON.parseArray(converted, ProginEnjoyer.class).get(0);

		name = enjoyer.name;
		surname = enjoyer.surname;
		birthdate = enjoyer.birthdate;
		hostel = enjoyer.hostel;
		favItems = enjoyer.favItems;
	}

	public void showInfo() {
		System.out.println(name + " " + surname);
		System.out.print("Born on ");
		var dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		if (birthdate != null)
			System.out.println(dateFormatter.format(birthdate));
		else
			System.out.println("null");
		System.out.println("Lives in " + hostel);
		if (favItems != null && favItems.size() > 0) {
			System.out.print("Favorite items: ");
			for (var item :
					favItems) {
				System.out.print(item);
				if (item != favItems.get(favItems.size() - 1))
					System.out.print(", ");
			}
		}
		System.out.println();
	}

	public void saveToFile(String fileName) throws IOException {
		String jsonString = JSON.toJSONString(this, true);
		if (!fileName.endsWith(".json")) fileName = fileName + ".json";
		Files.write(Path.of(fileName), Collections.singleton(jsonString));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getHostel() {
		return hostel;
	}

	public void setHostel(String hostel) {
		this.hostel = hostel;
	}

	public List<String> getFavItems() {
		return favItems;
	}

	public void setFavItems(List<String> favItems) {
		this.favItems = favItems;
	}
}