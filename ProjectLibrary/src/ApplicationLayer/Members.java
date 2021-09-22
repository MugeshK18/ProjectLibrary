package ApplicationLayer;

import java.util.*; 

import DataLayer.DBQuery;
import DataLayer.IDBQuery;
import DataLayer.MemberData;

public class Members implements IMembers{

	IDBQuery query=DBQuery.get_instance();
	MemberData member=null;
	ILibrary library=new Library();
	
    public  HashMap<String,Object> get_MemberDetails(int memberId) {		
		
		return query.getMemberDetails(memberId);
	}
    public String issueBook(int memberId,int bookId ) {
		
		return library.issueBook(memberId, bookId);
	}
	public void returnBook(int memberId,int bookId) {
		
		library.returnBook(memberId, bookId);
	}
	
	public String getDueDate(int memberId,int bookId) {
		return query.getDueDate(memberId, bookId);
	}
	public double getPendingAmount(int memberId) {
		return query.getPendingAmount(memberId);
	}
	
	public boolean getMemberStatus(int memberId) {
		return query.getMemberStatus(memberId);
	}
	
	
	public String payAmount(int memberId,double amount) {
		
		String message=null;
		double curAmount=0;
		curAmount=query.getPendingAmount(memberId);
		curAmount=curAmount-amount;
		query.addPendingAmount(memberId, curAmount);
		if(curAmount<=0) {
			query.changeMemberStatus(memberId, true);		
		}
		message="Payment Successfull! \n Amount paid is Rs:"+amount+"/- ";
		return message;
	}
	
	public ArrayList<HashMap<String,Object>> getBorrowedBooks(int memberId){
		return query.getBorrowedBooks(memberId);
	}
	public ArrayList<HashMap<String,Object>> getMyBooks(int memberId){
		return query.getMyBooks(memberId);
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
