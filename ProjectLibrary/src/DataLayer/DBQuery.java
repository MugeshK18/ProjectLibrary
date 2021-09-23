package DataLayer;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class DBQuery implements IDBQuery{

	private int adminId=0,librarianId=0,memberId=0;
	public static IDBQuery first_instance;
	private String DB_URL="jdbc:sqlite:C:\\sqlite3\\Library.db";
	private Connection con=null;
	private DBQuery() {
		
	}
	public static IDBQuery get_instance() {
		if(first_instance==null)
			first_instance=new DBQuery();
		return first_instance;
	}
	
	{//static block
		try {
			Class.forName("org.sqlite.JDBC");
			con=DriverManager.getConnection(DB_URL);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//--------//
	
	public boolean admin_LogIn(String userName,String password) {
		
		boolean result=false;
		String Select_sql="SELECT AdminId FROM Admin WHERE (AdminUserName=? AND AdminPassword=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			adminId=rs.getInt("AdminId");  
			if(adminId!=0) {
				result=true;
			}
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);	
		}
		catch(Exception e) {
			//e.printStackTrace();
			result=false;
		}		
		return result;
	}
	
	public boolean librarian_LogIn(String userName,String password) {
		
		boolean result=false;
		String Select_sql="SELECT LibrarianId FROM LibrarianLogInDetails WHERE (UserName=? AND Password=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);
			pstmt.setString(1, userName.toLowerCase());
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			librarianId=rs.getInt("LibrarianId");
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
			if(librarianId!=0) {
				result=true;
			}
		}
		catch(Exception e) {
			result=false;
		}
		return result;
	}
	public boolean member_LogIn(String userName,String password) {
		
		boolean result=false;
		String Select_sql="SELECT MemberId FROM MemberLogInDetails WHERE (UserName=? AND Password=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);
			pstmt.setString(1, userName.toLowerCase());
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			memberId=rs.getInt("MemberId");
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
			if(memberId!=0) {
				result=true;
			}
		}
		catch(Exception e) {
			result=false;
		}
		return result;
	}
	
	public int getMemberId() {
		return memberId;
	}
	public int getLibrarianId() {
		return librarianId;
	}
	
	public void addLibrarian(LibrarianData lib) {
		
		int librarianId;
		String insert_sql="INSERT INTO LibrarianDetails (FirstName,LastName,Age,Gender,CurrentAddress,PermanentAddress,PhoneNo,EmailId,GovernmentProof,ValidProofId) VALUES (?,?,?,?,?,?,?,?,?,?)";    
		String select_sql="SELECT LibrarianId FROM LibrarianDetails WHERE (EmailId=? AND ValidProofId=?)";
		String insert_sql2="INSERT INTO LibrarianLogInDetails (LibrarianId,UserName,Password) VALUES (?,?,?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(insert_sql);
			pstmt.setString(1, lib.getFirstName());
			pstmt.setString(2, lib.getLastName());
			pstmt.setInt(3, lib.getAge());
			pstmt.setString(4, lib.getGender());
			pstmt.setString(5, lib.getCurrentAddress());
			pstmt.setString(6, lib.getPermanentAddress());
			pstmt.setString(7, lib.getPhoneNo());
			pstmt.setString(8, lib.getEmailId());
			pstmt.setString(9, lib.getGovernmentProof());
			pstmt.setString(10, lib.getValidProofId());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			pstmt=con.prepareStatement(select_sql);
			pstmt.setString(1, lib.getEmailId());
			pstmt.setString(2, lib.getValidProofId());
			ResultSet rs1=pstmt.executeQuery();
			con.commit();
			librarianId=rs1.getInt("LibrarianId");
			rs1.close();
			pstmt.close();
			
			pstmt=con.prepareStatement(insert_sql2);
			pstmt.setInt(1, librarianId);
			pstmt.setString(2, lib.getUserName());
			pstmt.setString(3,lib.getPassword());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addMember(MemberData member) {
		
		int memberId;
		String insert_sql="INSERT INTO MemberDetails (FirstName,LastName,Age,Gender,CurrentAddress,PermanentAddress,PhoneNo,EmailId) VALUES (?,?,?,?,?,?,?,?)";    
		String select_sql="SELECT MemberId FROM MemberDetails WHERE (EmailId=? AND PhoneNo=?)";
		String insert_sql2="INSERT INTO MemberLogInDetails (MemberId,UserName,Password) VALUES (?,?,?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(insert_sql);
			pstmt.setString(1, member.getFirstName());
			pstmt.setString(2, member.getLastName());
			pstmt.setInt(3, member.getAge());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getCurrentAddress());
			pstmt.setString(6, member.getPermanentAddress());
			pstmt.setString(7, member.getPhoneNo());
			pstmt.setString(8, member.getEmailId());
			
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			pstmt=con.prepareStatement(select_sql);
			pstmt.setString(1, member.getEmailId());
			pstmt.setString(2, member.getPhoneNo());
			ResultSet rs1=pstmt.executeQuery();
			con.commit();
			memberId=rs1.getInt("memberId");
			rs1.close();
			pstmt.close();
			
			pstmt=con.prepareStatement(insert_sql2);
			pstmt.setInt(1, memberId);
			pstmt.setString(2, member.getUserName());
			pstmt.setString(3,member.getPassword());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String,Object> getLibrarianDetails(int librarianId){
		
		HashMap<String,Object> map=new HashMap<>();		
		String Select_sql="SELECT * FROM LibrarianDetails WHERE (LibrarianId=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);
			pstmt.setInt(1, librarianId);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			map.put("LibrarianId", librarianId);
			map.put("FirstName", rs.getString("FirstName"));
			map.put("LastName",rs.getString("LastName"));
			map.put("Age",rs.getInt("Age"));
			map.put("Gender",rs.getString("Gender"));
			map.put("CurrentAddress", rs.getString("CurrentAddress"));
			map.put("PermanentAddress", rs.getString("PermanentAddress"));
			map.put("PhoneNo",rs.getString("PhoneNo"));
			map.put("EmailId",rs.getString("EmailId"));
			map.put("GovernmentProof", rs.getString("GovernmentProof")); 
			map.put("ProofId", rs.getString("ValidProofId"));
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public HashMap<String,Object> getMemberDetails(int memberId){
		HashMap<String,Object> map=new HashMap<>();		
		String Select_sql="SELECT * FROM MemberDetails WHERE (MemberId=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);
			pstmt.setInt(1, memberId);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			map.put("MemberId", memberId);
			//System.out.println("###"+map.get("MemberId"));
			map.put("FirstName", rs.getString("FirstName"));
			map.put("LastName",rs.getString("LastName"));
			map.put("Age",rs.getInt("Age"));
			map.put("Gender",rs.getString("Gender"));
			map.put("CurrentAddress", rs.getString("CurrentAddress"));
			map.put("PermanentAddress", rs.getString("PermanentAddress"));
			map.put("PhoneNo",rs.getString("PhoneNo"));
			map.put("EmailId",rs.getString("EmailId"));

			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//System.out.println("#######"+map.get("MemberId"));
		return map;
	}
	
	public void updateLibrarianDetails(int librarianId,String firstName, String lastName, int age, String gender, 
			String permanentAddress,String currentAddress, String phoneNo, String emailId) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE LibrarianDetails SET  FirstName=?,LastName=?,Age=?,Gender=?,CurrentAddress=?,PermanentAddress=?,PhoneNo=?,EmailId=? WHERE LibrarianId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setInt(3, age);
			pstmt.setString(4, gender);
			pstmt.setString(5, currentAddress);
			pstmt.setString(6, permanentAddress);
			pstmt.setString(7, phoneNo);
			pstmt.setString(8, emailId);
			pstmt.setInt(9, librarianId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void updateMemberDetails(int memberId,String firstName, String lastName, int age, String gender,
			                    String permanentAddress,String currentAddress, String phoneNo, String emailId) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE MemberDetails SET  FirstName=?,LastName=?,Age=?,Gender=?,CurrentAddress=?,PermanentAddress=?,PhoneNo=?,EmailId=? WHERE MemberId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setInt(3, age);
			pstmt.setString(4, gender);
			pstmt.setString(5, currentAddress);
			pstmt.setString(6, permanentAddress);
			pstmt.setString(7, phoneNo);
			pstmt.setString(8, emailId);
			pstmt.setInt(9, memberId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteLibrarian(int librarianId) {
		
        PreparedStatement pstmt= null;		
        String delete_sql2="DELETE FROM LibrarianLogInDetails WHERE LibrarianId=?";
		String delete_sql1="DELETE FROM LibrarianDetails WHERE (LibrarianId=?)";
		
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(delete_sql2);
			pstmt.setInt(1, librarianId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
						
			pstmt=con.prepareStatement(delete_sql1);
			pstmt.setInt(1, librarianId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMember(int memberId) {
		
		 	PreparedStatement pstmt= null;		
	        String delete_sql2="DELETE FROM MemberLogInDetails WHERE MemberId=?";
			String delete_sql1="DELETE FROM MemberDetails WHERE (MemberId=?)";
			
			try
			{
				con.setAutoCommit(false);
				
				pstmt=con.prepareStatement(delete_sql2);
				pstmt.setInt(1, memberId);
				pstmt.executeUpdate();
				con.commit();
				pstmt.close();
							
				pstmt=con.prepareStatement(delete_sql1);
				pstmt.setInt(1, memberId);
				pstmt.executeUpdate();
				con.commit();
				pstmt.close();
				
				con.setAutoCommit(true);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	
	//
	public void addBook(BookData book) {
		
		String insert_sql="INSERT INTO BooksDetails (ISBN,BarCode,Title,Author,Publisher,YearPublished,BookMRP,Department,NoOfCopies,NoOfCopiesAvailable) VALUES (?,?,?,?,?,?,?,?,?,?)";    
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(insert_sql);
			pstmt.setString(1, book.getISBN());
			pstmt.setString(2, book.getBarCode());
			pstmt.setString(3, book.getTitle());
			pstmt.setString(4, book.getAuthor());
			pstmt.setString(5, book.getPublisher());
			pstmt.setString(6, book.getYearPublished());
			pstmt.setDouble(7, book.getBookMRP());
			pstmt.setString(8, book.getDepartment());
			pstmt.setInt(9, book.getNoOfBooks());
			pstmt.setInt(10, book.getNoOfBooks());
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
				
			con.setAutoCommit(true);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook(int bookId) {
		
		PreparedStatement pstmt= null;		
		String delete_sql1="DELETE FROM BooksDetails WHERE (BookId=?)";
		
		try
		{
			con.setAutoCommit(false);
						
			pstmt=con.prepareStatement(delete_sql1);
			pstmt.setInt(1, bookId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void editBookDetails(int bookId,String iSBN, String barCode, String title, String author,
			String publisher, String yearPublished,double bookMRP, String department) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE BooksDetails SET  ISBN=?,BarCode=?,Title=?,Author=?,Publisher=?,YearPublished=?,BookMRP=?,Department=? WHERE BookId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, iSBN);
			pstmt.setString(2, barCode);
			pstmt.setString(3, title.toUpperCase());
			pstmt.setString(4, author.toUpperCase());
			pstmt.setString(5, publisher.toUpperCase());
			pstmt.setString(6, yearPublished);
			pstmt.setDouble(7, bookMRP);
			pstmt.setString(8, department.toUpperCase());
			pstmt.setInt(9, bookId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<HashMap<String,Object>> getBookDetails() {           //1
		
	
		String Select_sql="SELECT * FROM BooksDetails";
		Statement stmt=null;	
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			stmt=con.createStatement();	
			ResultSet rs=stmt.executeQuery(Select_sql);
			con.commit();		
			while(rs.next()) {
			map=new HashMap<>();	
			map.put("BookId", rs.getInt("BookId"));
			map.put("ISBN", rs.getString("ISBN"));
	 		map.put("Title", rs.getString("Title"));
			map.put("Author",rs.getString("Author"));
			map.put("Publisher",rs.getString("Publisher"));
			map.put("YearPublished",rs.getString("YearPublished"));
			map.put("BookMRP",rs.getString("BookMRP"));
			map.put("Department",rs.getString("Department"));
			map.put("Status", rs.getBoolean("Status"));	
			list.add(map);
			}
			
			rs.close();
			stmt.close();
			
			con.setAutoCommit(true);
			
		}
		catch(Exception e) {
			list=null; //e.printStackTrace();
		}
		return list;
	}
	public HashMap<String,Object> getBookDetails(int bookId) { 
		
		String Select_sql="SELECT * FROM BooksDetails WHERE (BookId=?)";
		PreparedStatement pstmt=null;
		HashMap<String,Object> map=new HashMap<>();
		
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);
			pstmt.setInt(1, bookId);
			ResultSet rs=pstmt.executeQuery();
			con.commit();
			
			map.put("BookId",rs.getInt("BookId"));
			map.put("ISBN",rs.getString("ISBN"));
			map.put("Title",rs.getString("Title"));
			map.put("Author",rs.getString("Author"));
			map.put("Publisher",rs.getString("Publisher"));
			map.put("YearPublished",rs.getString("YearPublished"));
			map.put("BookMRP",rs.getString("BookMRP"));
			map.put("Department",rs.getString("Department"));
			map.put("Status",rs.getBoolean("Status"));
			
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);		
		}
		catch(Exception e) {
			map=null;//e.printStackTrace();
		}
		return map;
	}
	public ArrayList<HashMap<String,Object>> getBookDetails(String title) {
		
		
		String Select_sql="SELECT * FROM BooksDetails WHERE Title LIKE '%"+title+"%' ";
		Statement stmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			stmt=con.createStatement();
			//stmt.setString(1, title.toUpperCase());
			ResultSet rs=stmt.executeQuery(Select_sql);
			con.commit();		
			while(rs.next()) {
				map=new HashMap<>();	
				map.put("BookId", rs.getInt("BookId"));
				map.put("ISBN", rs.getString("ISBN"));
		 		map.put("Title", rs.getString("Title"));
				map.put("Author",rs.getString("Author"));
				map.put("Publisher",rs.getString("Publisher"));
				map.put("YearPublished",rs.getString("YearPublished"));
				map.put("BookMRP",rs.getString("BookMRP"));
				map.put("Department",rs.getString("Department"));
				map.put("Status", rs.getBoolean("Status"));	
				list.add(map);
			}
			rs.close();
			stmt.close();
			
			con.setAutoCommit(true);
		
		}
		catch(Exception e) {
			//list=null;
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<HashMap<String,Object>> getBookDetails(String title,String author) {
		
	
		String Select_sql="SELECT * FROM BooksDetails WHERE (Title LIKE '%"+title+"%' AND Author LIKE '%"+author+"%')";
		Statement stmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			stmt=con.createStatement();
//			stmt.setString(1, title.toUpperCase());
//			stmt.setString(2, author.toUpperCase());
			ResultSet rs=stmt.executeQuery(Select_sql);
			con.commit();		
			while(rs.next()) {
				map=new HashMap<>();	
				map.put("BookId", rs.getInt("BookId"));
				map.put("ISBN", rs.getString("ISBN"));
		 		map.put("Title", rs.getString("Title"));
				map.put("Author",rs.getString("Author"));
				map.put("Publisher",rs.getString("Publisher"));
				map.put("YearPublished",rs.getString("YearPublished"));
				map.put("BookMRP",rs.getString("BookMRP"));
				map.put("Department",rs.getString("Department"));
				map.put("Status", rs.getBoolean("Status"));	
				list.add(map);
			}
			rs.close();
			stmt.close();
			
			con.setAutoCommit(true);
	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUISBN(String ISBN) {
		
	
		String Select_sql="SELECT * FROM BooksDetails WHERE (ISBN=?)";
		PreparedStatement pstmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);
			pstmt.setString(1, ISBN);
			ResultSet rs=pstmt.executeQuery();
			con.commit();		
			while(rs.next()) {
				map=new HashMap<>();	
				map.put("BookId", rs.getInt("BookId"));
				map.put("ISBN", rs.getString("ISBN"));
		 		map.put("Title", rs.getString("Title"));
				map.put("Author",rs.getString("Author"));
				map.put("Publisher",rs.getString("Publisher"));
				map.put("YearPublished",rs.getString("YearPublished"));
				map.put("BookMRP",rs.getString("BookMRP"));
				map.put("Department",rs.getString("Department"));
				map.put("Status", rs.getBoolean("Status"));	
				list.add(map);
			}
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUBarCode(String barCode) {
		

		String Select_sql="SELECT * FROM BooksDetails WHERE (BarCode=?)";
		PreparedStatement pstmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);
			pstmt.setString(1, barCode);
			ResultSet rs=pstmt.executeQuery();
			con.commit();		
			
			while(rs.next()) {
				map=new HashMap<>();	
				map.put("BookId", rs.getInt("BookId"));
				map.put("ISBN", rs.getString("ISBN"));
		 		map.put("Title", rs.getString("Title"));
				map.put("Author",rs.getString("Author"));
				map.put("Publisher",rs.getString("Publisher"));
				map.put("YearPublished",rs.getString("YearPublished"));
				map.put("BookMRP",rs.getString("BookMRP"));
				map.put("Department",rs.getString("Department"));
				map.put("Status", rs.getBoolean("Status"));	
				list.add(map);
			}
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUYear(String title,String year) {
		

		String Select_sql="SELECT * FROM BooksDetails WHERE (Title LIKE '%"+title+"%' AND YearPublished=?)";
		PreparedStatement pstmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);
			//pstmt.setString(1, title.toUpperCase());
			pstmt.setString(1, year);
			ResultSet rs=pstmt.executeQuery();
			con.commit();		
			
			while(rs.next()) {
				map=new HashMap<>();	
				map.put("BookId", rs.getInt("BookId"));
				map.put("ISBN", rs.getString("ISBN"));
		 		map.put("Title", rs.getString("Title"));
				map.put("Author",rs.getString("Author"));
				map.put("Publisher",rs.getString("Publisher"));
				map.put("YearPublished",rs.getString("YearPublished"));
				map.put("BookMRP",rs.getString("BookMRP"));
				map.put("Department",rs.getString("Department"));
				map.put("Status", rs.getBoolean("Status"));	
				list.add(map);
			}
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUYear(String year) {
		

		String Select_sql="SELECT * FROM BooksDetails WHERE (YearPublished=?)";
		PreparedStatement pstmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);
			pstmt.setString(1, year);
			ResultSet rs=pstmt.executeQuery();
			con.commit();		
			
			while(rs.next()) {
				map=new HashMap<>();	
				map.put("BookId", rs.getInt("BookId"));
				map.put("ISBN", rs.getString("ISBN"));
		 		map.put("Title", rs.getString("Title"));
				map.put("Author",rs.getString("Author"));
				map.put("Publisher",rs.getString("Publisher"));
				map.put("YearPublished",rs.getString("YearPublished"));
				map.put("BookMRP",rs.getString("BookMRP"));
				map.put("Department",rs.getString("Department"));
				map.put("Status", rs.getBoolean("Status"));	
				list.add(map);
			}
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String publisher) {
		
	
		String Select_sql="SELECT * FROM BooksDetails WHERE (Publisher LIKE '%"+publisher+"%')";
		Statement stmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			stmt=con.createStatement();
			//stmt.setString(1, publisher.toUpperCase());
			ResultSet rs=stmt.executeQuery(Select_sql);
			con.commit();		
			
			while(rs.next()) {
				map=new HashMap<>();	
				map.put("BookId", rs.getInt("BookId"));
				map.put("ISBN", rs.getString("ISBN"));
		 		map.put("Title", rs.getString("Title"));
				map.put("Author",rs.getString("Author"));
				map.put("Publisher",rs.getString("Publisher"));
				map.put("YearPublished",rs.getString("YearPublished"));
				map.put("BookMRP",rs.getString("BookMRP"));
				map.put("Department",rs.getString("Department"));
				map.put("Status", rs.getBoolean("Status"));	
				list.add(map);
			}
			rs.close();
			stmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String title,String publisher) {
		
	
		String Select_sql="SELECT * FROM BooksDetails WHERE (Title LIKE '%"+title+"%' AND Publisher LIKE '%"+publisher+"%')";
		Statement stmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			stmt=con.createStatement();
//			stmt.setString(1, title.toUpperCase());
//			stmt.setString(2, publisher.toUpperCase());
			ResultSet rs=stmt.executeQuery(Select_sql);
			con.commit();		
			
			while(rs.next()) {
				map=new HashMap<>();	
				map.put("BookId", rs.getInt("BookId"));
				map.put("ISBN", rs.getString("ISBN"));
		 		map.put("Title", rs.getString("Title"));
				map.put("Author",rs.getString("Author"));
				map.put("Publisher",rs.getString("Publisher"));
				map.put("YearPublished",rs.getString("YearPublished"));
				map.put("BookMRP",rs.getString("BookMRP"));
				map.put("Department",rs.getString("Department"));
				map.put("Status", rs.getBoolean("Status"));	
				list.add(map);
			}
			rs.close();
			stmt.close();
			
			con.setAutoCommit(true);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUPublisher(String title,String author,String publisher) {
		
	
		String Select_sql="SELECT * FROM BooksDetails WHERE (Title LIKE '%"+title+"%' AND Author LIKE '%"+author+"%' AND Publisher LIKE '%"+publisher+"%' )";
		Statement stmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			stmt=con.createStatement();
//			stmt.setString(1, title.toUpperCase());
//			stmt.setString(2, author.toUpperCase());
//			stmt.setString(3, publisher.toUpperCase());
			ResultSet rs=stmt.executeQuery(Select_sql);
			con.commit();		
			
			while(rs.next()) {
				map=new HashMap<>();	
				map.put("BookId", rs.getInt("BookId"));
				map.put("ISBN", rs.getString("ISBN"));
		 		map.put("Title", rs.getString("Title"));
				map.put("Author",rs.getString("Author"));
				map.put("Publisher",rs.getString("Publisher"));
				map.put("YearPublished",rs.getString("YearPublished"));
				map.put("BookMRP",rs.getString("BookMRP"));
				map.put("Department",rs.getString("Department"));
				map.put("Status", rs.getBoolean("Status"));	
				list.add(map);
			}
			rs.close();
			stmt.close();
			
			con.setAutoCommit(true);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUAuthor(String author) {
		
	
		String Select_sql="SELECT * FROM BooksDetails WHERE (Author LIKE '%"+author+"%')";
		Statement stmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			stmt=con.createStatement();
			//stmt.setString(1, author.toUpperCase());
			ResultSet rs=stmt.executeQuery(Select_sql);
			con.commit();		
			
			while(rs.next()) {
				map=new HashMap<>();	
				map.put("BookId", rs.getInt("BookId"));
				map.put("ISBN", rs.getString("ISBN"));
		 		map.put("Title", rs.getString("Title"));
				map.put("Author",rs.getString("Author"));
				map.put("Publisher",rs.getString("Publisher"));
				map.put("YearPublished",rs.getString("YearPublished"));
				map.put("BookMRP",rs.getString("BookMRP"));
				map.put("Department",rs.getString("Department"));
				map.put("Status", rs.getBoolean("Status"));	
				list.add(map);
			}
			
			rs.close();
			stmt.close();
			
			con.setAutoCommit(true);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<HashMap<String,Object>> getBookDetailsUAuthor(String author,String year) {
	
		String Select_sql="SELECT * FROM BooksDetails WHERE (Author LIKE '%"+author+"%' AND YearPublished=?)";
		PreparedStatement pstmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);
			//pstmt.setString(1, author.toUpperCase());
			pstmt.setString(1, year);
			ResultSet rs=pstmt.executeQuery();
			con.commit();		
			
			while(rs.next()) {
				map=new HashMap<>();	
				map.put("BookId", rs.getInt("BookId"));
				map.put("ISBN", rs.getString("ISBN"));
		 		map.put("Title", rs.getString("Title"));
				map.put("Author",rs.getString("Author"));
				map.put("Publisher",rs.getString("Publisher"));
				map.put("YearPublished",rs.getString("YearPublished"));
				map.put("BookMRP",rs.getString("BookMRP"));
				map.put("Department",rs.getString("Department"));
				map.put("Status", rs.getBoolean("Status"));	
				list.add(map);
			}
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//
	public boolean getLibrarianStatus(int librarianId) {
		boolean libStatus=false;
		String select_sql="SELECT Status FROM LibrarianLogInDetails WHERE (LibrarianId=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1, librarianId);			
			ResultSet rs=pstmt.executeQuery();
			libStatus=rs.getBoolean("Status");
			con.commit();	
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			libStatus=false;
		}
		return libStatus;
	}
	public boolean getMemberStatus(int memberId) {
		
		boolean memberStatus=false;
		String select_sql="SELECT Status FROM MemberLogInDetails WHERE (MemberId=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1, memberId);			
			ResultSet rs=pstmt.executeQuery();
			memberStatus=rs.getBoolean("Status");
			con.commit();	
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			memberStatus=false;
		}
		return memberStatus;
	}
	public boolean getBookStatus(int bookId) {
		
		boolean bookStatus=false;
		String select_sql="SELECT Status FROM BooksDetails WHERE (BookId=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1, bookId);			
			ResultSet rs=pstmt.executeQuery();
			bookStatus=rs.getBoolean("Status");
			con.commit();	
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			bookStatus=false;
		}
		return bookStatus;
	}
	public void changeLibrarianStatus(int librarianId,boolean status) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE LibrarianLogInDetails SET Status=? WHERE LibrarianId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setBoolean(1, status);
			pstmt.setInt(2, librarianId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void changeMemberStatus(int memberId,boolean status) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE MemberLogInDetails SET Status=? WHERE MemberId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setBoolean(1, status);
			pstmt.setInt(2, memberId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void changeBookStatus(int bookId,boolean status) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE BooksDetails SET Status=? WHERE BookId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setBoolean(1, status);
			pstmt.setInt(2, bookId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public double getPendingAmount(int memberId) {
		
		double amount=0;
		String select_sql="SELECT PendingAmount FROM MemberLogInDetails WHERE (MemberId=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1, memberId);			
			ResultSet rs=pstmt.executeQuery();
			amount=rs.getDouble("PendingAmount");
			con.commit();	
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return amount;
	}
	
	public void issueBook(int memberId,int bookId,String returnDate) {
		
		String insert_sql="INSERT INTO BorrowedBooks (MemberId,BookId,IssuedDate,DueDate) VALUES (?,?,CURRENT_TIMESTAMP,?)";    
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(insert_sql);
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, bookId);
			pstmt.setString(3, returnDate);			
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
				
			con.setAutoCommit(true);			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void returnBook(int memberId,int bookId) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE BorrowedBooks SET  ReturnedTime=CURRENT_TIMESTAMP WHERE (MemberId=? AND BookId=? AND ReturnedTime=?)";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setInt(1, memberId);
			pstmt.setInt(2, bookId);
			pstmt.setString(3, "0");
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String getReturnedDate(int memberId,int bookId) {
		
		String returnedDate=null;
		String select_sql="SELECT ReturnedTime FROM BorrowedBooks WHERE (MemberId=? AND BookId=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1, memberId);	
			pstmt.setInt(2, bookId);
			ResultSet rs=pstmt.executeQuery();
			returnedDate=rs.getString("ReturnedTime");
			while(rs.next()) {
				returnedDate=rs.getString("ReturnedTime");
			}
			con.commit();	
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return returnedDate;
	}
	public String getDueDate(int memberId,int bookId) {
		
		String date=null;
		String select_sql="SELECT DueDate FROM BorrowedBooks WHERE (MemberId=? AND BookId=? AND ReturnedStatus=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(select_sql);
			pstmt.setInt(1, memberId);	
			pstmt.setInt(2, bookId);
			pstmt.setBoolean(3, false);
			ResultSet rs=pstmt.executeQuery();
			date=rs.getString("DueDate");
			while(rs.next()) {
				date=rs.getString("DueDate");
			}
			con.commit();	
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			date="Nill";
		}
		return date;
	}

	public void addPendingAmount(int memberId,double amount) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE MemberLogInDetails SET PendingAmount=? WHERE (MemberId=?)";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setDouble(1, amount);
			pstmt.setInt(2, memberId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void changeReturnedStatus(int memberId,int bookId,boolean status) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE BorrowedBooks SET ReturnedStatus=? WHERE (MemberId=? AND BookId=?)";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setBoolean(1, status);
			pstmt.setInt(2, memberId);
			pstmt.setInt(3, bookId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resetDataBase() {
		
		Statement stmt= null;	
		String delete_sql5="DELETE FROM BorrowedBooks";
		String delete_sql4="DELETE FROM LibraryLogInDetails";
		String delete_sql3="DELETE FROM LibrarianDetails";
        String delete_sql2="DELETE FROM MemberLogInDetails";
		String delete_sql1="DELETE FROM MemberDetails";
		
		try
		{
			con.setAutoCommit(false);
			
			stmt=con.createStatement();
			stmt.executeUpdate(delete_sql5);
			con.commit();
			stmt.close();
			
			stmt=con.createStatement();
			stmt.executeUpdate(delete_sql4);
			con.commit();
			stmt.close();
			
			stmt=con.createStatement();
			stmt.executeUpdate(delete_sql3);
			con.commit();
			stmt.close();
			
			stmt=con.createStatement();
			stmt.executeUpdate(delete_sql2);
			con.commit();
			stmt.close();
						
			stmt=con.createStatement();
			stmt.executeUpdate(delete_sql1);
			con.commit();
			stmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNoOfBooks(int bookId) {
		
		int noOfBooks=0;
		String select_sql="SELECT NoOfCopies FROM BooksDetails WHERE (BookId=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(select_sql);			
			pstmt.setInt(1, bookId);
			ResultSet rs=pstmt.executeQuery();
			noOfBooks=rs.getInt("NoOfCopies");		
			con.commit();	
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
			noOfBooks=0;
		}
		return noOfBooks;
	}
	public int getNoOfBooksAv(int bookId) {
		
		int noOfBooksAv=0;
		String select_sql="SELECT NoOfCopiesAvailable FROM BooksDetails WHERE (BookId=?)";
		PreparedStatement pstmt=null;
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(select_sql);			
			pstmt.setInt(1, bookId);
			ResultSet rs=pstmt.executeQuery();
			noOfBooksAv=rs.getInt("NoOfCopiesAvailable");		
			con.commit();	
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
			noOfBooksAv=0;
		}
		return noOfBooksAv;
	}
	public void changeNoOfBooks(int bookId,int noOfBooks) {
		
		String update_sql="UPDATE BooksDetails SET NoOfCopies=? WHERE (BookId=?)";
		PreparedStatement pstmt=null;
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);			
			pstmt.setInt(1, noOfBooks);
			pstmt.setInt(2, bookId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void changeNoOfBooksAv(int bookId,int noOfBooksAv) {
		
		String update_sql="UPDATE BooksDetails SET NoOfCopiesAvailable=? WHERE (BookId=?)";
		PreparedStatement pstmt=null;
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);			
			pstmt.setInt(1, noOfBooksAv);
			pstmt.setInt(2, bookId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	//---------//
	public ArrayList<HashMap<String,Object>> getBorrowedBooks(int memberId) {
		
		String Select_sql="SELECT * FROM BorrowedBooks WHERE (MemberId=?)";
		PreparedStatement pstmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);		
			pstmt.setInt(1, memberId);
			ResultSet rs=pstmt.executeQuery();
			con.commit();		
			
			while(rs.next()) {
				map=new HashMap<>();
				map.put("MemberId",rs.getInt("MemberId"));
				map.put("BookId", rs.getInt("BookId"));
				map.put("IssuedDate", rs.getString("IssuedDate"));
		 		map.put("DueDate", rs.getString("DueDate"));
				map.put("ReturnedTime",rs.getString("ReturnedTime"));		
				map.put("ReturnedStatus", rs.getBoolean("ReturnedStatus"));	
				list.add(map);
			}
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	public  ArrayList<HashMap<String,Object>> getMyBooks(int memberId){
		
		String Select_sql="SELECT * FROM BorrowedBooks WHERE (MemberId=? AND ReturnedStatus=?)";
		PreparedStatement pstmt=null;
		HashMap<String,Object> map=null;
		ArrayList<HashMap<String,Object>> list=new ArrayList<>();
		try {
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(Select_sql);		
			pstmt.setInt(1, memberId);
			pstmt.setBoolean(2, false);
			ResultSet rs=pstmt.executeQuery();
			con.commit();		
			
			while(rs.next()) {
				map=new HashMap<>();
				map.put("MemberId",rs.getInt("MemberId"));
				map.put("BookId", rs.getInt("BookId"));
				map.put("IssuedDate", rs.getString("IssuedDate"));
		 		map.put("DueDate", rs.getString("DueDate"));
				map.put("ReturnedTime",rs.getString("ReturnedTime"));		
				map.put("ReturnedStatus", rs.getBoolean("ReturnedStatus"));	
				list.add(map);
			}
			rs.close();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void changeName(int memberId,String firstName,String lastName) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE MemberDetails SET  FirstName=?,LastName=? WHERE MemberId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, firstName.toUpperCase());
			pstmt.setString(2, lastName.toUpperCase());
			pstmt.setInt(3, memberId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void changeAddress(int memberId,String currAddress,String permAddress) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE MemberDetails SET  CurrentAddress=?,PermanentAddress=? WHERE MemberId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, currAddress.toUpperCase());
			pstmt.setString(2, permAddress.toUpperCase());
			pstmt.setInt(3, memberId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void changePhoneNo(int memberId,String phoneNo) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE MemberDetails SET PhoneNo=? WHERE MemberId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, phoneNo);
			pstmt.setInt(2, memberId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public void changePassword(int memberId,String password) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE MemberLogInDetails SET Password=? WHERE MemberId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, password);
			pstmt.setInt(2, memberId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void changeLibName(int librarianId,String firstName,String lastName) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE LibrarianDetails SET FirstName=?,LastName=? WHERE LibrarianId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setInt(3, librarianId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public void changeLibAddress(int librarianId,String currAddress,String permAddress) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE LibrarianDetails SET CurrentAddress=?,PermanentAddress=? WHERE LibrarianId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, currAddress);
			pstmt.setString(2, permAddress);
			pstmt.setInt(3, librarianId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public void changeLibPhoneNo(int librarianId,String phoneNo) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE LibrarianDetails SET PhoneNo=? WHERE LibrarianId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, phoneNo);
			pstmt.setInt(2, librarianId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	public void changeLibPassword(int librarianId,String password) {
		
		PreparedStatement pstmt= null;	
		String update_sql="UPDATE LibrarianLogInDetails SET Password=? WHERE LibrarianId=?";
		try
		{
			con.setAutoCommit(false);
			
			pstmt=con.prepareStatement(update_sql);
			pstmt.setString(1, password);
			pstmt.setInt(2, librarianId);
			pstmt.executeUpdate();
			con.commit();
			pstmt.close();
			
			con.setAutoCommit(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
}//DBQuery ends
