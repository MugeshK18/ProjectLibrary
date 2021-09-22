package ApplicationLayer;

import java.util.ArrayList;
import java.util.HashMap;

import DataLayer.BookData;
import DataLayer.DBQuery;
import DataLayer.IDBQuery;
import DataLayer.LibrarianData;
import DataLayer.MemberData;

public class LibraryOfficials implements ILibraryOfficials{

	IDBQuery query=DBQuery.get_instance();
	LibrarianData lib=null;
	MemberData member=null;
	ILibrary library=new Library();
	
	
	public void createMember(String firstName, String lastName, int age, String gender, String permanentAddress,
					         String currentAddress, String phoneNo, String emailId,String userName,String password)
	{
		member=new MemberData(firstName,lastName,age,gender,permanentAddress,currentAddress,
	                        phoneNo,emailId,userName,password);
		query.addMember(member);
	}
	public HashMap<String,Object> get_LibrarianDetails(int librarianId) {
		return query.getLibrarianDetails(librarianId);
		
	
	}
	public  HashMap<String,Object> get_MemberDetails(int memberId) {		
		
		return query.getMemberDetails(memberId);
	}
	
	public void edit_MemberDetails(int memberId,String firstName, String lastName, int age, String gender,
            String permanentAddress,String currentAddress, String phoneNo, String emailId) {
		
		query.updateMemberDetails(memberId, firstName, lastName, age, gender, permanentAddress, currentAddress, phoneNo, emailId);
	}
	
	public void delete_Member(int memberId) {
		query.deleteMember(memberId);
	}
	
	
	public void addBook(String iSBN, String barCode, String title, String author, String publisher, String yearPublished,
			double bookMRP, String department,int noOfBooks) {
		
		BookData book=new BookData(iSBN,barCode,title,author,publisher,yearPublished,bookMRP,department,noOfBooks);
		query.addBook(book);
	}
	public void deleteBook(int bookId) {
		query.deleteBook(bookId);
	}
	public void editBookDetails(int bookId,String iSBN, String barCode, String title, String author,
			String publisher, String yearPublished,double bookMRP, String department) {
		query.editBookDetails(bookId, iSBN, barCode, title, author, publisher, yearPublished, bookMRP, department);
	}
	
	
public String issueBook(int memberId,int bookId) {
		
		return library.issueBook(memberId, bookId);
	}
	public void returnBook(int memberId,int bookId) {
		
		library.returnBook(memberId, bookId);
	}
	
	public void addPendingAmount(int memberId,double amount) {
		
		double currAmount=query.getPendingAmount(memberId);
		currAmount=currAmount+amount;
		query.addPendingAmount(memberId, currAmount);
	}
	public void changePendingAmount(int memberId,double amount) {
		
		double currA=query.getPendingAmount(memberId);
		currA=currA-amount;
		query.addPendingAmount(memberId, currA);
		if(currA<0) {
			query.changeMemberStatus(memberId, true);
		}
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
	
	public void changeMemberStatus(int memberId,boolean status) {
		query.changeMemberStatus(memberId, status);
	}
	public void changeBookStatus(int bookId,boolean status) {
		query.changeBookStatus(bookId, status);
	}
	
	public boolean getBookStatus(int bookId) {
		return library.getBookStatus(bookId);
	}
	
	public ArrayList<HashMap<String,Object>> getBookDetails() {
		return library.getBookDetails();
	}
	public HashMap<String,Object> getBookDetails(int bookId) {
		return library.getBookDetails(bookId);
	}
    public ArrayList<HashMap<String,Object>> getBookDetails(String title) {
		return library.getBookDetails(title);
	}
	public ArrayList<HashMap<String,Object>> getBookDetails(String title,String author) {
		return library.getBookDetails(title, author);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUISBN(String ISBN) {
		return library.getBookDetailsUISBN(ISBN);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUBarCode(String barCode) {
		return library.getBookDetailsUBarCode(barCode);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUYear(String title,String year) {
		return library.getBookDetailsUYear(title, year);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUYear(String year) {
		return library.getBookDetailsUYear(year);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String publisher) {
		return library.getBookDetailsUPublisher(publisher);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String title,String publisher) {
		return library.getBookDetailsUPublisher(title, publisher);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String title,String author,String publisher) {
		return library.getBookDetailsUPublisher(title, author, publisher);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUAuthor(String author) {
		return library.getBookDetailsUAuthor(author);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUAuthor(String author,String year) {
		return library.getBookDetailsUAuthor(author, year);
	}
	public void closeConnection() {
		library.closeConnection();
	}
}