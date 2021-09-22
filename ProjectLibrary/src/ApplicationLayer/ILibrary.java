package ApplicationLayer;

import java.util.ArrayList;
import java.util.HashMap;

public interface ILibrary {

	public boolean getBookStatus(int bookId);
	public String issueBook(int memberId,int bookId);
	public void returnBook(int memberId,int bookId);
	
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
