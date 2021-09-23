package ApplicationLayer;
 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;


import DataLayer.DBQuery;
import DataLayer.IDBQuery;
//import org.joda.time.*;

public class Library implements ILibrary { 

	IDBQuery query=DBQuery.get_instance();
	
	public void closeConnection() {
		query.closeConnection();
	}
	
	public boolean getBookStatus(int bookId) {
		return query.getBookStatus(bookId);
	}
	
	public String issueBook(int memberId,int bookId) {	
		
		LocalDateTime dateTime =LocalDateTime.now();
		dateTime=dateTime.plusDays(30);
		 //System.out.println("----"+dateTime);
		 //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String returnDate = dateTime.format(formatter); 
		
        
		String message=null;
		double pAmount=0;
		if(query.getMemberStatus(memberId)) {
			pAmount=query.getPendingAmount(memberId);
			if(pAmount==0) {
				if(query.getBookStatus(bookId)) {					
					query.issueBook(memberId, bookId, returnDate);	
					int noOfBooks=query.getNoOfBooksAv(bookId);
					noOfBooks--;
					query.changeNoOfBooksAv(bookId, noOfBooks);
					if(noOfBooks==0) {
						query.changeBookStatus(bookId, false);
					}
					message="Book Id("+bookId+") is issued";
				}
				else {
					message="Sorry! Book Id("+bookId+") is currently NOT available.\n"+"Please try later or contact LibraryOfficials.";
				}
			}
			else {
				 query.changeMemberStatus(memberId, false);
				message="It appears to be that you already have due amount of Rs:"+pAmount+"/- due to Late Return! Can't proceed further.";		       
			}
		}
		else {
			message="Your MemberShip is temporarily suspended! Please contact Admin or Library Offcials. Thank You!";
		}
		return message;
	}
	public void returnBook(int memberId,int bookId) {
		
		query.returnBook(memberId,bookId);
		query.changeReturnedStatus(memberId, bookId, true);
		int noOfBooks=query.getNoOfBooksAv(bookId);
		noOfBooks++;
		query.changeNoOfBooksAv(bookId, noOfBooks);
		if(noOfBooks>0) {
			query.changeBookStatus(bookId, true);
		}
		//---//		
		String dueDate=query.getDueDate(memberId, bookId);
		String returnedDate=query.getReturnedDate(memberId, bookId);
		//if(dueDate.contains("T")) {
		//String str[]=dueDate.split("T");
		//dueDate=str[0]+" "+str[1];
		//}
		DateTimeFormatter dtfr=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date1=LocalDateTime.parse(dueDate);
		LocalDateTime date2=LocalDateTime.parse(returnedDate, dtfr);
		System.out.println("Date1:"+date1);
		System.out.println("Date2:"+date2);
		
		long dateDiff=ChronoUnit.DAYS.between(date1, date2);
		System.out.println(dateDiff);
		if(dateDiff>0) { 
			double pendAmount=dateDiff*5;
			double currAmount=query.getPendingAmount(memberId);
			currAmount=currAmount+pendAmount;
			query.addPendingAmount(memberId, currAmount);
			if(currAmount>0) {
				query.changeMemberStatus(memberId, false);
			}
		}
		double currAmount=query.getPendingAmount(memberId);
		if(currAmount<=0) {
			query.changeMemberStatus(memberId, true);
		}
		else {
			query.changeMemberStatus(memberId, false);
		}
		
	}
	
	public ArrayList<HashMap<String,Object>> getBookDetails() {
		return query.getBookDetails();
	}
	public HashMap<String,Object> getBookDetails(int bookId) {
		return query.getBookDetails(bookId);
	}
    public ArrayList<HashMap<String,Object>> getBookDetails(String title) {
		return query.getBookDetails(title);
	}
	public ArrayList<HashMap<String,Object>> getBookDetails(String title,String author) {
		return query.getBookDetails(title, author);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUISBN(String ISBN) {
		return query.getBookDetailsUISBN(ISBN);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUBarCode(String barCode) {
		return query.getBookDetailsUBarCode(barCode);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUYear(String title,String year) {
		return query.getBookDetailsUYear(title, year);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUYear(String year) {
		return query.getBookDetailsUYear(year);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String publisher) {
		return query.getBookDetailsUPublisher(publisher);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String title,String publisher) {
		return query.getBookDetailsUPublisher(title, publisher);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String title,String author,String publisher) {
		return query.getBookDetailsUPublisher(title, author, publisher);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUAuthor(String author) {
		return query.getBookDetailsUAuthor(author);
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUAuthor(String author,String year) {
		return query.getBookDetailsUAuthor(author, year);
	}
}
