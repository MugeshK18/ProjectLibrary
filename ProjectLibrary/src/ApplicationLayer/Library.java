package ApplicationLayer;

import java.util.*;

import DataLayer.BookData;
import DataLayer.DBQuery;
import DataLayer.IDBQuery;
import DataLayer.LibrarianData;
import DataLayer.MemberData;
public class Library implements IAdmin,ILibraryOfficials,IMembers {

	IDBQuery query=DBQuery.get_instance();
	LibrarianData lib=null;
	MemberData member=null;
	
	public void createLibraryOfficials(String firstName, String lastName, int age, String gender, String permanentAddress,
			                           String currentAddress, String phoneNo, String emailId, String governmentProof,
			                           String validProofId,String userName,String password)
	{
		
		lib=new LibrarianData(firstName,lastName,age,gender,permanentAddress,currentAddress,
				             phoneNo,emailId,governmentProof,validProofId,userName,password);
		query.addLibrarian(lib);
	}
	public void createMember(String firstName, String lastName, int age, String gender, String permanentAddress,
					         String currentAddress, String phoneNo, String emailId,String userName,String password)
	{
		member=new MemberData(firstName,lastName,age,gender,permanentAddress,currentAddress,
	                        phoneNo,emailId,userName,password);
		query.addMember(member);
	}
	public void get_LibrarianDetails(int librarianId) {
		
		HashMap<String,Object> map=new HashMap<>();
		map=query.getLibrarianDetails(librarianId);
		
		System.out.println("Library Official Details:- \n");
		
		System.out.println("Library Official Id: "+map.get("LibrarianId"));
		System.out.println("First Name: "+map.get("FirstName"));
		System.out.println("Last Name : "+map.get("LastName"));
		System.out.println("Age       : "+map.get("Age"));
		System.out.println("Gender    : "+map.get("Gender"));
		System.out.println("Current Address    : "+map.get("CurrentAddress"));
		System.out.println("Permanent Address  : "+map.get("PermanentAddress"));
		System.out.println("Phone No. : "+map.get("PhoneNo"));
		System.out.println("Email Id  : "+map.get("EmailId"));
		System.out.println("Government Proof submitted : "+map.get("GovernmentProof"));
		System.out.println("Proof Valid Id             : "+map.get("ProofId"));
		System.out.println();
		map.clear();
	}
	public void get_MemberDetails(int memberId) {
		
		HashMap<String,Object> map=new HashMap<>();
		map=query.getLibrarianDetails(memberId);
		
		System.out.println("Member Details:- \n");
		
		System.out.println("Member Id : "+memberId);  //map.get("MemberId"));
		System.out.println("First Name: "+map.get("FirstName"));
		System.out.println("Last Name : "+map.get("LastName"));
		System.out.println("Age       : "+map.get("Age"));
		System.out.println("Gender    : "+map.get("Gender"));
		System.out.println("Current Address    : "+map.get("CurrentAddress"));
		System.out.println("Permanent Address  : "+map.get("PermanentAddress"));
		System.out.println("Phone No. : "+map.get("PhoneNo"));
		System.out.println("Email Id  : "+map.get("EmailId"));
		System.out.println();
		map.clear();
	}
	
	public void edit_LibrarianDetails(int librarianId,String firstName, String lastName, int age, String gender, 
			String permanentAddress,String currentAddress, String phoneNo, String emailId) {
		
		query.updateLibrarianDetails(librarianId, firstName, lastName, age, gender, permanentAddress, currentAddress, phoneNo, emailId);
	}
	public void edit_MemberDetails(int memberId,String firstName, String lastName, int age, String gender,
            String permanentAddress,String currentAddress, String phoneNo, String emailId) {
		
		query.updateMemberDetails(memberId, firstName, lastName, age, gender, permanentAddress, currentAddress, phoneNo, emailId);
	}
	
	public void delete_Librarian(int librarianId) {
		
		query.deleteLibrarian(librarianId);
	}
	public void delete_Member(int memberId) {
		query.deleteMember(memberId);
	}
	
	
	public void addBook(String iSBN, String barCode, String title, String author, String publisher, String yearPublished,
			double bookMRP, String department) {
		
		BookData book=new BookData(iSBN,barCode,title,author,publisher,yearPublished,bookMRP,department);
		query.addBook(book);
	}
	public void deleteBook(int bookId) {
		query.deleteBook(bookId);
	}
	public void editBookDetails(int bookId,String iSBN, String barCode, String title, String author,
			String publisher, String yearPublished,double bookMRP, String department) {
		query.editBookDetails(bookId, iSBN, barCode, title, author, publisher, yearPublished, bookMRP, department);
	}
	
	
	public void issueBook(int memberId,int bookId,String returnDate) {
		
		double pAmount=0;
		if(query.getMemberStatus(memberId)) {
			pAmount=query.getPendingAmount(memberId);
			if(pAmount==0) {
				if(query.getBookStatus(bookId)) {
					System.out.println("Please note that If not return by the due date, Rs:5/- will be charged for each day past due date.");
					query.issueBook(memberId, bookId, returnDate);
					System.out.println("Book Id("+bookId+") is issued");
					query.changeBookStatus(bookId, false);				
				}
				else {
					System.out.println("Sorry! Book Id("+bookId+") is currently NOT available.");
					System.out.println("Please try after "+query.getDueDate(memberId, bookId));
				}
			}
			else {
				System.out.println("It appears to be that you already have due amount of Rs:"+pAmount+"/- due to Late Return! Can't proceed further.\n");
		        query.changeMemberStatus(memberId, false);
			}
		}
		else {
			System.out.println("Your MemberShip is temporarily suspended! Please contact Admin or Library Offcials. Thank You!\n");
		}
	}
	public void returnBook(int memberId,int bookId) {
		
		query.returnBook(memberId,bookId);
		System.out.println("Book Id("+bookId+") is suucessfully returned");
		query.changeReturnedStatus(memberId, bookId, true);
		query.changeBookStatus(bookId, true);
		
	}
	
	public void addPendingAmount(int memberId,double amount) {
		
		double currAmount=query.getPendingAmount(memberId);
		currAmount=currAmount+amount;
		query.addPendingAmount(memberId, currAmount);
	}
	public void changePendingAmount(int memberId,double amount) {
		query.addPendingAmount(memberId, amount);
	}
	public String getDueDate(int memberId,int bookId) {
		return query.getDueDate(memberId, bookId);
	}
	public double getPendingAmount(int memberId) {
		return query.getPendingAmount(memberId);
	}
	
	public boolean getLibrarianStatus(int librarianId) {
		return query.getLibrarianStatus(librarianId);
	}
	public boolean getMemberStatus(int memberId) {
		return query.getMemberStatus(memberId);
	}
	public boolean getBookStatus(int bookId) {
		return query.getBookStatus(bookId);
	}
	public void changeLibrarianStatus(int librarianId,boolean status) {
		query.changeLibrarianStatus(librarianId, status);
	}
	public void changeMemberStatus(int memberId,boolean status) {
		query.changeMemberStatus(memberId, status);
	}
	public void changeBookStatus(int bookId,boolean status) {
		query.changeBookStatus(bookId, status);
	}
	

	public void getBookDetails() {
		query.getBookDetails();
	}
	public void getBookDetails(int bookId) {
		query.getBookDetails(bookId);
	}
    public void getBookDetails(String title) {
		query.getBookDetails(title);
	}
	public void getBookDetails(String title,String author) {
		query.getBookDetails(title, author);
	}
	public void getBookDetailsUISBN(String ISBN) {
		query.getBookDetailsUISBN(ISBN);
	}
	public void getBookDetailsUBarCode(String barCode) {
		query.getBookDetailsUBarCode(barCode);
	}
	public void getBookDetailsUYear(String title,String year) {
		query.getBookDetailsUYear(title, year);
	}
	public void getBookDetailsUYear(String year) {
		query.getBookDetailsUYear(year);
	}
	public void getBookDetailsUPublisher(String publisher) {
		query.getBookDetailsUPublisher(publisher);
	}
	public void getBookDetailsUPublisher(String title,String publisher) {
		query.getBookDetailsUPublisher(title, publisher);
	}
	public void getBookDetailsUPublisher(String title,String author,String publisher) {
		query.getBookDetailsUPublisher(title, author, publisher);
	}
	public void getBookDetailsUAuthor(String author) {
		query.getBookDetailsUAuthor(author);
	}
	public void getBookDetailsUAuthor(String author,String year) {
		query.getBookDetailsUAuthor(author, year);
	}
	public void closeConnection() {
		query.closeConnection();
	}
}
