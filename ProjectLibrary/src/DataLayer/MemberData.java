package DataLayer;

public final class MemberData {

	private final String firstName,lastName,gender,permanentAddress,currentAddress,phoneNo,emailId,userName,password;
	private final int age;
	
	public MemberData(String firstName,String lastName,int age,String gender,String permanentAddress,
			         String currentAddress,String phoneNo,String emailId,String userName,String password)
	{
		this.firstName=firstName.toUpperCase();
		this.lastName=lastName.toUpperCase();
		this.age=age;
		this.gender=gender.toUpperCase();
		this.permanentAddress=permanentAddress.toUpperCase();
		this.currentAddress=currentAddress.toUpperCase();
		this.phoneNo=phoneNo;
		this.emailId=emailId.toLowerCase();	
		this.userName=userName;
		this.password=password;
	}


	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getGender() {
		return gender;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public String getCurrentAddress() {
		return currentAddress;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public int getAge() {
		return age;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
}
