package cc;

import java.util.Random;

public class User {

    private int id;
    private String name;
    private int age;

    public User() {
        Random random = new Random();
        this.id = random.nextInt() & Integer.MAX_VALUE;
    }

    public User(String name, int age) {
        Random random = new Random();
        this.id = random.nextInt() & Integer.MAX_VALUE;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setString(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

	@Override
	public String toString() {
		return name + " (" + id +  ") ";
	}
}
