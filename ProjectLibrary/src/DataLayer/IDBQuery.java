package DataLayer;

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
	
	
	public void getBookDetails();
	public void getBookDetails(int bookId);
    public void getBookDetails(String title);
	public void getBookDetails(String title,String author);
	public void getBookDetailsUISBN(String ISBN);
	public void getBookDetailsUBarCode(String barCode);
	public void getBookDetailsUYear(String title,String year);
	public void getBookDetailsUYear(String year);
	public void getBookDetailsUPublisher(String publisher);
	public void getBookDetailsUPublisher(String title,String publisher);
	public void getBookDetailsUPublisher(String title,String author,String publisher);
	public void getBookDetailsUAuthor(String author);
	public void getBookDetailsUAuthor(String author,String year);
	
	public void closeConnection();
}
