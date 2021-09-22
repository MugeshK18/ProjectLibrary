package ApplicationLayer;

import java.util.ArrayList;
import java.util.HashMap;

public interface ILibraryOfficials {

	public void createMember(String firstName, String lastName, int age, String gender, String permanentAddress,
	         String currentAddress, String phoneNo, String emailId,String userName,String password);
	
	public void edit_MemberDetails(int memberId,String firstName, String lastName, int age, String gender,
            String permanentAddress,String currentAddress, String phoneNo, String emailId);
	public void delete_Member(int memberId);
	
	public void addBook(String iSBN, String barCode, String title, String author, String publisher,
	           String yearPublished,double bookMRP, String department,int noOfBooks);
    public void deleteBook(int bookId);
    public void editBookDetails(int bookId,String iSBN, String barCode, String title, String author,
	            String publisher, String yearPublished,double bookMRP, String department);
	
    public HashMap<String,Object> get_LibrarianDetails(int librarianId);
    public HashMap<String,Object> get_MemberDetails(int memberId);
	
	public String issueBook(int memberId,int bookId);
	public void returnBook(int memberId,int bookId);
	public void changePendingAmount(int memberId,double amount);
	public void addPendingAmount(int memberId,double amount);
	
	public boolean getLibrarianStatus(int librarianId);
	public void changeBookStatus(int bookId,boolean status);
	public double getPendingAmount(int memberId);
	public String getDueDate(int memberId,int bookId);
	public boolean getMemberStatus(int memberId);
	public boolean getBookStatus(int bookId);
	
	public ArrayList<HashMap<String,Object>> getBookDetails();
	public HashMap<String,Object> getBookDetails(int bookId);
    public ArrayList<HashMap<String,Object>> getBookDetails(String title);
	public ArrayList<HashMap<String,Object>> getBookDetails(String title,String author);
	public ArrayList<HashMap<String,Object>> getBookDetailsUISBN(String ISBN);
	public ArrayList<HashMap<String,Object>> getBookDetailsUBarCode(String barCode);    
	public ArrayList<HashMap<String,Object>> getBookDetailsUYear(String title,String year);
	public ArrayList<HashMap<String,Object>> getBookDetailsUYear(String year);  
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String publisher);
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String title,String publisher);  
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String title,String author,String publisher);
	public ArrayList<HashMap<String,Object>> getBookDetailsUAuthor(String author);   
	public ArrayList<HashMap<String,Object>> getBookDetailsUAuthor(String author,String year);
	public void closeConnection();
}
