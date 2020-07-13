package ru.gribnoff.l1.t1;

class Person {
	String firstName;
	String lastName;
	String middleName;
	String country;
	String address;
	String phone;
	int age;
	String gender;

	private Person() {}

	public static Builder newBuilder() {
		return new Person().new Builder();
	}

	public class Builder {
		private Builder() {}

		public Builder setFirstName(String firstName) {
			Person.this.firstName = firstName;
			return this;
		}

		public Builder setLastName(String lastName) {
			Person.this.lastName = lastName;
			return this;
		}

		public Builder setMiddleName(String middleName) {
			Person.this.middleName = middleName;
			return this;
		}

		public Builder setCountry(String country) {
			Person.this.country = country;
			return this;
		}

		public Builder setAddress(String address) {
			Person.this.address = address;
			return this;
		}

		public Builder setPhone(String phone) {
			Person.this.phone = phone;
			return this;
		}

		public Builder setAge(int age) {
			Person.this.age = age;
			return this;
		}

		public Builder setGender(String gender) {
			Person.this.gender = gender;
			return this;
		}

		public Person build() {
			return Person.this;
		}
	}
}
