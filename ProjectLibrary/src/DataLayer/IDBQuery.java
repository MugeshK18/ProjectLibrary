package DataLayer;

import java.util.ArrayList;
import java.util.HashMap;

public interface IDBQuery {

	public boolean admin_LogIn(String userName,String password);
	public boolean librarian_LogIn(String userName,String password);
	public boolean member_LogIn(String userName,String password);
	public int getMemberId();
	public int getLibrarianId();
	
	public void addLibrarian(LibrarianData lib);
	public void addMember(MemberData member);
	public HashMap<String,Object> getLibrarianDetails(int librarianId);
	public HashMap<String,Object> getMemberDetails(int memberId);
	public void updateLibrarianDetails(int librarianId,String firstName, String lastName, int age, String gender, 
			String permanentAddress,String currentAddress, String phoneNo, String emailId);
	public void updateMemberDetails(int memberId,String firstName, String lastName, int age, String gender,
            String permanentAddress,String currentAddress, String phoneNo, String emailId);
	public void deleteLibrarian(int librarianId);
	public void deleteMember(int memberId);
	
	public void addBook(BookData book);
	public void deleteBook(int bookId);
	public void editBookDetails(int bookId,String iSBN, String barCode, String title, String author,
			String publisher, String yearPublished,double bookMRP, String department);
	
	public boolean getLibrarianStatus(int librarianId);
	public boolean getMemberStatus(int memberId);
	public boolean getBookStatus(int bookId);
	public void changeLibrarianStatus(int librarianId,boolean status);
	public void changeMemberStatus(int memberId,boolean status);
	public void changeBookStatus(int bookId,boolean status);
	public double getPendingAmount(int memberId);
	public void addPendingAmount(int memberId,double amount);
	
	public void issueBook(int memberId,int bookId,String returnDate);
	public void returnBook(int memberId,int bookId);
	public void changeReturnedStatus(int memberId,int bookId,boolean status);
	
	public String getDueDate(int memberId,int bookId);
	public String getReturnedDate(int memberId,int bookId);
	
	public int getNoOfBooks(int bookId);
	public int getNoOfBooksAv(int bookId);
	public void changeNoOfBooks(int bookId,int noOfBooks);
	public void changeNoOfBooksAv(int bookId,int noOfBooksAv);
	
	public ArrayList<HashMap<String,Object>> getBorrowedBooks(int memberId);
	public ArrayList<HashMap<String,Object>> getMyBooks(int memberId);
	public void changeName(int memberId,String firstName,String lastName);
	public void changeAddress(int memberId,String currAddress,String permAddress);
	public void changePhoneNo(int memberId,String phoneNo);
	public void changePassword(int memberId,String password);
	
	public void changeLibName(int librarianId,String firstName,String lastName);
	public void changeLibAddress(int librarianId,String currAddress,String permAddress);
	public void changeLibPhoneNo(int librarianId,String phoneNo);
	public void changeLibPassword(int librarianId,String password);
	
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
