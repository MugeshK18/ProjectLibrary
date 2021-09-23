package ApplicationLayer;

import java.util.ArrayList;
import java.util.HashMap;

import DataLayer.BookData;
import DataLayer.DBQuery;
import DataLayer.IDBQuery;
import DataLayer.LibrarianData;
import DataLayer.MemberData;

public class Admin implements IAdmin {

	IDBQuery query=DBQuery.get_instance();
	LibrarianData lib=null;
	MemberData member=null;
	ILibrary library=new Library();
	
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
	public HashMap<String,Object> get_LibrarianDetails(int librarianId) {
		return query.getLibrarianDetails(librarianId);
		
	
	}
	public  HashMap<String,Object> get_MemberDetails(int memberId) {		
		
		return query.getMemberDetails(memberId);
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
	
	public void changeLibrarianStatus(int librarianId,boolean status) {
		query.changeLibrarianStatus(librarianId, status);
	}
	public void changeMemberStatus(int memberId,boolean status) {
		query.changeMemberStatus(memberId, status);
	}
	public void changeBookStatus(int bookId,boolean status) {
		query.changeBookStatus(bookId, status);
	}	

	public void changeName(int memberId,String firstName,String lastName) {
		query.changeName(memberId, firstName, lastName);
	}
	public void changeAddress(int memberId,String currAddress,String permAddress) {
		query.changeAddress(memberId, currAddress, permAddress);
	}
	public void changePhoneNo(int memberId,String phoneNo) {
		query.changePhoneNo(memberId, phoneNo);
	}
	public void changePassword(int memberId,String password) {
		query.changePassword(memberId, password);
	}
	
	public void changeLibName(int librarianId,String firstName,String lastName) {
		query.changeLibName(librarianId, firstName, lastName);
	}
	public void changeLibAddress(int librarianId,String currAddress,String permAddress) {
		query.changeLibAddress(librarianId, currAddress, permAddress);
	}
	public void changeLibPhoneNo(int librarianId,String phoneNo) {
		query.changeLibPhoneNo(librarianId, phoneNo);
	}
	public void changeLibPassword(int librarianId,String password) {
		query.changeLibPassword(librarianId, password);
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
