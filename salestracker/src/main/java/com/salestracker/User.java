package com.salestracker;

import java.util.UUID;

public class User {
  private final UUID id;
  private final String firstName;
  private final String lastName;
  private final int age;
  private final String phone;
  private final String address;

  private User(UserBuilder builder) {
    this.id = builder.id;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.phone = builder.phone;
		this.address = builder.address;
	}

  //All getter, and NO setter to provide immutability
  public UUID getId() {
    return id;
  }
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getAge() {
		return age;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}

  @Override
	public String toString() {
		return "User: " + this.id+", " +this.firstName+", "+this.lastName+", "+this.age+", "+this.phone+", "+this.address;
	}


  public static class UserBuilder
	{
		private final String firstName;
		private final String lastName;
		private int age;
		private String phone;
		private String address;
    private UUID id;

		public UserBuilder(String firstName, String lastName, String id) {
			this.firstName = firstName;
			this.lastName = lastName;
      // random UUID
      this.id = UUID.fromString(id);
		}
		public UserBuilder age(int age) {
			this.age = age;
			return this;
		}
		public UserBuilder phone(String phone) {
			this.phone = phone;
			return this;
		}
		public UserBuilder address(String address) {
			this.address = address;
			return this;
		}

		public User build() {
			User user =  new User(this);
			// validateUserObject(user);
			return user;
		}
		// private void validateUserObject(User user) {
		// 	//Do some basic validations to check
		// 	//if user object does not break any assumption of system
		// }
	}
}
