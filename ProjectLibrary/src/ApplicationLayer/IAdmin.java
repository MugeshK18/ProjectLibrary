package ApplicationLayer;


public interface IAdmin {

	public void createLibraryOfficials(String firstName, String lastName, int age, String gender, String permanentAddress,
            String currentAddress, String phoneNo, String emailId, String governmentProof,
            String validProofId,String userName,String password);
	public void createMember(String firstName, String lastName, int age, String gender, String permanentAddress,
	         String currentAddress, String phoneNo, String emailId,String userName,String password);
	
	public void get_LibrarianDetails(int librarianId);
	public void get_MemberDetails(int memberId);
	public void edit_LibrarianDetails(int librarianId,String firstName, String lastName, int age, String gender, 
			String permanentAddress,String currentAddress, String phoneNo, String emailId);
	public void edit_MemberDetails(int memberId,String firstName, String lastName, int age, String gender,
            String permanentAddress,String currentAddress, String phoneNo, String emailId);
	public void delete_Librarian(int librarianId);
	public void delete_Member(int memberId);
	
	public void addBook(String iSBN, String barCode, String title, String author, String publisher,
			           String yearPublished,double bookMRP, String department);
	public void deleteBook(int bookId);
	public void editBookDetails(int bookId,String iSBN, String barCode, String title, String author,
			String publisher, String yearPublished,double bookMRP, String department);
	
	public void issueBook(int memberId,int bookId,String returnDate);
	public void returnBook(int memberId,int bookId);
	
	public void changePendingAmount(int memberId,double amount);
	public void addPendingAmount(int memberId,double amount);
	public double getPendingAmount(int memberId);
	public String getDueDate(int memberId,int bookId);
	
	public boolean getLibrarianStatus(int librarianId);
	public boolean getMemberStatus(int memberId);
	public boolean getBookStatus(int bookId);
	public void changeLibrarianStatus(int librarianId,boolean status);
	public void changeMemberStatus(int memberId,boolean status);
	public void changeBookStatus(int bookId,boolean status);
	
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