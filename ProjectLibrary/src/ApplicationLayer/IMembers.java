package ApplicationLayer;

public interface IMembers {

	public void get_MemberDetails(int memberId);
	public void issueBook(int memberId,int bookId,String returnDate);
	public void returnBook(int memberId,int bookId);
	
	public double getPendingAmount(int memberId);
	public String getDueDate(int memberId,int bookId);
	public boolean getMemberStatus(int memberId);
	public boolean getBookStatus(int bookId);
	
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
