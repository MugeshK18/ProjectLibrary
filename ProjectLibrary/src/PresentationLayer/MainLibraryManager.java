package PresentationLayer;

import java.util.*; 
import ApplicationLayer.*;

public class MainLibraryManager {

	public static void main(String[] args) {
		

		ILoginCheck login= new LoginCheck();
		Scanner sc=new Scanner(System.in);
		try {
			int opt=0;
			
			System.out.println("     Welcome to New Community Library!! ");
			System.out.println("       1,2nd Main Road,Adyar,Chennai.    ");
			System.out.println("*********************************************\n\n");
			while(true) {
				System.out.println("LogIn Menu:- ");
				System.out.println("Enter 1 - Members \n"+"Enter 2 - Library Officals \n"+"Enter 3 - Admin\n"
				                   +"Enter 4 - Close the Application");
				try {
				opt=sc.nextInt();
				sc.nextLine();
				}
				catch(Exception e) {
					System.out.println("Enter an Integer value between 1-4 please!");
					break;
				}
				System.out.println();
				if(opt==1) {                // Member Login
					int memberId;
					
					System.out.println("Enter the User Name :");
					String userName=sc.nextLine();
					System.out.println("Enter the Password  :");
					String password=sc.nextLine();
					System.out.println();
					
					if(login.get_member_LogIn(userName, password)) {
						System.out.println("LogIn Succesfull!!");
						memberId=login.get_MemberId();
						System.out.println("Welcome !\n"); //"Welcome "+getMemberName()+"! \n");
						System.out.println("Member Id="+memberId);
					}
					else {
						System.out.println("Invalid UserName or Password! Please try again");
						System.out.println();
						continue;
					}
					
					IMembers member=new Library();
					int bookId=0;
					String date;
					
					while(true) {
						
						System.out.println("Enter 1- View List of Books\n"+"Enter 2- Search Book\n"+"Enter 3- Borrow Book\n"+
						                   "Enter 4- Return Book\n"+"Enter 5- view Due Date\n"+"Enter 6- View Payment Details\n"+
								           "Enter 7- View Member Details\n"+"Enter 8- Log Out\n");
						int choice=sc.nextInt();
						sc.nextLine();
						if(choice==1) {
							member.getBookDetails();
							System.out.println();
						}
						else if(choice==2) {
							
							String ISBN,barCode,title,author,publisher,yearPublished; //department;
							
							while(true) {
								
								System.out.println("Enter below options to search with:- ");
								System.out.println("1- BookId            "+" 2- Title\n"+
								                   "3- Title & Author    "+" 4- ISBN\n"+
								                   "5- Bar Code          "+" 6- Title & Year\n"+
								                   "7- Year              "+" 8- Publisher\n"+
								                   "9- Title & Publisher "+" 10- Title & Author & Publisher\n"+
								                   "11- Author           "+" 12- Author & Year\n"+
								                   "13- EXIT\n--");
								int c=sc.nextInt();
								sc.nextLine();
								String ex=null;
								switch(c) {
								case 1:
									System.out.println("Enter BookId to search:");
									bookId=sc.nextInt();
									sc.nextLine();
									member.getBookDetails(bookId);
									System.out.println();
									break;
								case 2:
									System.out.println("Enter Book Title to search:");
									title=sc.nextLine();
									member.getBookDetails(title);
									System.out.println();
									break;
								case 3:
									System.out.println("Enter Book Title:");
									title=sc.nextLine();
									System.out.println("Enter Book Author to search:");
									author=sc.nextLine();
									member.getBookDetails(title,author);
									System.out.println();
									break;
								case 4:
									System.out.println("Enter Book ISBN to search:");
									ISBN=sc.nextLine();
									member.getBookDetailsUISBN(ISBN);
									System.out.println();
									break;
								case 5:
									System.out.println("Enter Book Bar Code to search:");
									barCode=sc.nextLine();
									member.getBookDetailsUBarCode(barCode);
									System.out.println();
									break;
								case 6:
									System.out.println("Enter Book Title:");
									title=sc.nextLine();
									System.out.println("Enter Year of Book Published to search:");
									yearPublished=sc.nextLine();
									member.getBookDetailsUYear(title, yearPublished);
									System.out.println();
									break;
								case 7:
									System.out.println("Enter Year of Book Published to search:");
									yearPublished=sc.nextLine();
									member.getBookDetailsUYear(yearPublished);
									System.out.println();
									break;
								case 8:
									System.out.println("Enter Publisher of Book to search:");
									publisher=sc.nextLine();
									member.getBookDetailsUPublisher(publisher);
									System.out.println();
									break;
								case 9:
									System.out.println("Enter Book Title:");
									title=sc.nextLine();
									System.out.println("Enter Publisher of Book to search:");
									publisher=sc.nextLine();
									member.getBookDetailsUPublisher(title, publisher);
									System.out.println();
									break;
								case 10:
									System.out.println("Enter Book Title:");
									title=sc.nextLine();
									System.out.println("Enter Author of Book:");
									author=sc.nextLine();
									System.out.println("Enter Publisher of Book to search:");
									publisher=sc.nextLine();							
									member.getBookDetailsUPublisher(title,author, publisher);
									System.out.println();
									break;
								case 11:
									System.out.println("Enter Author of Book to search:");
									author=sc.nextLine();
									member.getBookDetailsUAuthor(author);
									System.out.println();
									break;
								case 12:
									System.out.println("Enter Author of Book:");
									author=sc.nextLine();
									System.out.println("Enter Year Published to search:");
									yearPublished=sc.nextLine();
									member.getBookDetailsUAuthor(author,yearPublished);
									System.out.println();
									break;
								case 13:
									ex="exit";
									break;
								default:
									System.out.println("Enter values between 1-13\n");
									continue;											
								}//switch ends
								if(ex=="exit")
									break;
							}//while ends
						}
						else if(choice==3) {
							
							System.out.println("Enter the Book Id:");
							bookId=sc.nextInt();
							sc.nextLine();
							System.out.println("Enter the Due Date (yyy-mm-dd):");
							date=sc.nextLine();
							System.out.println("Enter 'y' to Borrow Book otherwise press any key:");
							String str=sc.nextLine();
							System.out.println();
							if(str.equalsIgnoreCase("y")) {
								member.issueBook(memberId, bookId, date);
								System.out.println();
							}
							else{
								System.out.println("Canceled! \n");
								continue;
							}
						}
						else if(choice==4) {
							System.out.println("Enter the Book Id: ");
							bookId=sc.nextInt();
							sc.nextLine();
							System.out.println("Enter 'y' to Return Book otherwise press any key:");
							String str=sc.nextLine();
							System.out.println();
							if(str.equalsIgnoreCase("y")) {
								member.returnBook(memberId, bookId);
								System.out.println();
							}
							else {
								System.out.println("Canceled ! \n");
								continue;
							}
						}	
						else if(choice==5) {
							System.out.println("Enter the Book Id:");
							bookId=sc.nextInt();
							sc.nextLine();
							System.out.println();
							System.out.println("Due Date is "+member.getDueDate(memberId, bookId));
							System.out.println();
						}
						else if(choice==6) {
							System.out.println("The Pending amount to be paid is Rs:"+member.getPendingAmount(memberId)+"/- because of late return of Books. \n");
						}
						else if(choice==7) {
							member.get_MemberDetails(memberId);
							System.out.println();
						}
						else if(choice==8) {
							System.out.println("Logged Out sucessfully! \n");
							break;
						}
						else {
							System.out.println("Enter value between 1-7 \n");
							continue;
						}
						
					}//while ends															
				} //if ends
				else if(opt==2) {           // LibraryOfficials Login
					
					int librarianId;
					
					System.out.println("Enter the User Name :");
					String userName=sc.nextLine();
					System.out.println("Enter the Password  :");
					String password=sc.nextLine();
					System.out.println();
					
					if(login.get_librarian_LogIn(userName, password)) {
						System.out.println("LogIn Succesfull!!");
						librarianId=login.get_LibrarianId();
						
						System.out.println("Welcome !\n"); //"Welcome "+getlibrarianName()+"! \n");
						System.out.println("Library Offical Id="+librarianId+"\n");
					}
					else {
						System.out.println("Invalid UserName or Password! Please try again");
						System.out.println();
						continue;
					}
					
					ILibraryOfficials libOff=new Library();
					String firstName,lastName,gender,permAddress,currAddress,phoneNo,emailId,userNameM,passwordM;
					String ISBN,barCode,title,author,publisher,yearPublished,department;
					double bookMRP;
					int age,bookId,memberId;
					String s;
					
					while(true) {
						try {
							System.out.println("Enter 1- Add Book\n"+"Enter 2- Delete Book\n"+"Enter 3- Edit Book\n"+
						                       "Enter 4- Issue Book\n"+"Enter 5- Return Book\n"+"Enter 6- Veiw Details of Book\n"+
									           "Enter 7- Add Member\n"+"Enter 8- Delete Member\n"+"Enter 9- Edit Member\n"+
						                       "Enter 10- View Member's Details\n"+"Enter 11- Search Book\n"+"Enter 12- View Book Status\n"+
									            "Enter 13- change Book Status\n"+
								                "Enter 14- Change Pending Payment\n"+"Enter 15- EXIT\n--");
							int choice=sc.nextInt();
							sc.nextLine();
							System.out.println();
							if(choice==1) {
								System.out.println("Enter the ISBN : ");
								ISBN=sc.nextLine();
								System.out.println("Enter the Bar Code : ");
								barCode=sc.nextLine();
								System.out.println("Enter the Title of the Book : ");
								title=sc.nextLine();
								System.out.println("Enter the Author of the Book : ");
								author=sc.nextLine();
								System.out.println("Enter the Publisher of the Book : ");
								publisher=sc.nextLine();
								System.out.println("Enter the Year Published : ");
								yearPublished=sc.nextLine();
								System.out.println("Enter the MRP of the Book : ");
								bookMRP=sc.nextDouble();
								sc.nextLine();
								System.out.println("Enter the Department this Book belongs : ");
								department=sc.nextLine();
								
								System.out.println("Enter 'y' to proceed further, otherwise press any key");
								s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									libOff.addBook(ISBN, barCode, title, author, publisher, yearPublished, bookMRP, department);
									System.out.println("Susccesfully Book is added in Database\n");
								}
								else {
									System.out.println("Adding Book is cancelled\n");
									continue;
								}
							}
							else if(choice==2) {
								System.out.println("Enter the Book Id : ");
								bookId=sc.nextInt();									
								sc.nextLine();
								System.out.println("Are you sure to Delete this Book from the Database? (y/n)");
								s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									libOff.deleteBook(bookId);
									System.out.println("Successfully Deleted\n");
								}
								else {
									System.out.println("Deleting Book is cancelled\n");
									continue;
								}
							}
							else if(choice==3) {
								System.out.println("Enter the Book Id : ");
								bookId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter the ISBN : ");
								ISBN=sc.nextLine();
								System.out.println("Enter the Bar Code : ");
								barCode=sc.nextLine();
								System.out.println("Enter the Title of the Book : ");
								title=sc.nextLine();
								System.out.println("Enter the Author of the Book : ");
								author=sc.nextLine();
								System.out.println("Enter the Publisher of the Book : ");
								publisher=sc.nextLine();
								System.out.println("Enter the Year Published : ");
								yearPublished=sc.nextLine();
								System.out.println("Enter the MRP of the Book : ");
								bookMRP=sc.nextDouble();
								sc.nextLine();
								System.out.println("Enter the Department this Book belongs : ");
								department=sc.nextLine();
								
								System.out.println("Are you sure to Edit this Book Details? (y/n)");
								s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									libOff.editBookDetails(bookId, ISBN, barCode, title, author, publisher, yearPublished, bookMRP, department);
									System.out.println("Editing Book Details is successfull");
									System.out.println();
								}
								else {
									System.out.println("Editing Book is cancelled\n");
									continue;
								}
															
							}
							else if(choice==4) {
								System.out.println("Enter Member Id:");
								memberId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter Book Id:");
								bookId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter the Return Date or Due date (YYYY-MM-DD) :");
								String date=sc.nextLine();
								System.out.println("Enter 'y' to proceed further otherwise press any key");
								s=sc.nextLine();
								if(s.equalsIgnoreCase("y")) {
									libOff.issueBook(memberId, bookId, date);
									
								}
								else {
									System.out.println("Canceled!\n");
								}
							}
							else if(choice==5) {
								System.out.println("Enter Member Id:");
								memberId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter Book Id:");
								bookId=sc.nextInt();
								sc.nextLine();
								System.out.println("If any 'Fine' for late submission,enter the amount otherwise enter '0' ");
								double amount=sc.nextDouble();
								s=sc.nextLine();
								System.out.println("Enter 'y' to proceed further otherwise press any key");
								s=sc.nextLine();
								if(s.equalsIgnoreCase("y")) {
									libOff.returnBook(memberId, bookId);
									libOff.addPendingAmount(memberId, amount);
									System.out.println("You have pending amount of Rs:"+(libOff.getPendingAmount(memberId))+"/- for late Return.\n");						
								}
								else {
									System.out.println("Canceled!\n");
								}
							}
							else if(choice==6) {
								libOff.getBookDetails();
								System.out.println();
							}
							else if(choice==7) {
								while(true) {
									try {
										System.out.println("Enter - First Name : ");
										firstName=sc.nextLine();
										System.out.println("Enter - Last Name : ");
										lastName=sc.nextLine();
										System.out.println("Enter - Age : ");
										age=sc.nextInt();
										sc.nextLine();
										System.out.println("Enter - Gender (optional) : ");
										gender=sc.nextLine();
										System.out.println("Enter - Current Address : ");
										currAddress=sc.nextLine();
										System.out.println("Enter - Permanent Address (optional) : ");
										permAddress=sc.nextLine();
										System.out.println("Enter - Phone Number : ");
										phoneNo=sc.nextLine();
										System.out.println("Enter - Email Id : ");
										emailId=sc.nextLine();
										System.out.println("Enter a User Name : ");
										userNameM=sc.nextLine();
										System.out.println("Enter a password : ");
										passwordM=sc.nextLine();
										
										libOff.createMember(firstName, lastName, age, gender, permAddress, currAddress, phoneNo, emailId, userNameM, passwordM);
										System.out.println("Member Added susccessfully in Database");
										break;
									}
									catch(Exception e) {
										System.out.println("Enter a valid input please!");
										break;
									}
								}//while ends
							}
							else if(choice==8) {
								System.out.println("Enter the Member Id");
								memberId=sc.nextInt();
								sc.nextLine();
								System.out.println("Are you sure to delete this Member? (y/n)");
								s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									libOff.delete_Member(memberId);
									System.out.println("Member Id("+memberId+") is Deleted\n");
								}
								else {
									System.out.println("Deleting Member is Canceled !\n");
									continue;
								}
														
							}
							else if(choice==9) {
								System.out.println("Enter the Member Id : ");
								memberId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter - First Name : ");
								firstName=sc.nextLine();
								System.out.println("Enter - Last Name : ");
								lastName=sc.nextLine();
								System.out.println("Enter - Age : ");
								age=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter - Gender (optional) : ");
								gender=sc.nextLine();
								System.out.println("Enter - Current Address : ");
								currAddress=sc.nextLine();
								System.out.println("Enter - Permanent Address (optional) : ");
								permAddress=sc.nextLine();
								System.out.println("Enter - Phone Number : ");
								phoneNo=sc.nextLine();
								System.out.println("Enter - Email Id : ");
								emailId=sc.nextLine();
								System.out.println("Are you sure to Edit this Member? (y/n)");
								s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									libOff.edit_MemberDetails(memberId, firstName, lastName, age, gender, permAddress, currAddress, phoneNo, emailId);
									System.out.println("Editing Successfull!\n");
								}
								else {
									System.out.println("Editing Member Details is Canceled !\n");
									continue;
								}
							}
							else if(choice==10) {
								System.out.println("Enter the Member Id");
								memberId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter 'y' to proceed further otherwise press any key");
								s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									libOff.get_MemberDetails(memberId);
									
								}
								else {
									System.out.println("Canceled !\n");
									continue;
								}
							}
							else if(choice==11) {
								while(true) {
									System.out.println("Enter below options to search with:- ");
									System.out.println("1- BookId            "+" 2- Title\n"+
									                   "3- Title & Author    "+" 4- ISBN\n"+
									                   "5- Bar Code          "+" 6- Title & Year\n"+
									                   "7- Year              "+" 8- Publisher\n"+
									                   "9- Title & Publisher "+" 10- Title & Author & Publisher\n"+
									                   "11- Author           "+" 12- Author & Year\n"+
									                   "13- EXIT\n--");
									int c=sc.nextInt();
									sc.nextLine();
									String ex=null;
									switch(c) {
									case 1:
										System.out.println("Enter BookId to search:");
										bookId=sc.nextInt();
										sc.nextLine();
										libOff.getBookDetails(bookId);
										System.out.println();
										break;
									case 2:
										System.out.println("Enter Book Title to search:");
										title=sc.nextLine();
										libOff.getBookDetails(title);
										System.out.println();
										break;
									case 3:
										System.out.println("Enter Book Title:");
										title=sc.nextLine();
										System.out.println("Enter Book Author to search:");
										author=sc.nextLine();
										libOff.getBookDetails(title,author);
										System.out.println();
										break;
									case 4:
										System.out.println("Enter Book ISBN to search:");
										ISBN=sc.nextLine();
										libOff.getBookDetailsUISBN(ISBN);
										System.out.println();
										break;
									case 5:
										System.out.println("Enter Book Bar Code to search:");
										barCode=sc.nextLine();
										libOff.getBookDetailsUBarCode(barCode);
										System.out.println();
										break;
									case 6:
										System.out.println("Enter Book Title:");
										title=sc.nextLine();
										System.out.println("Enter Year of Book Published to search:");
										yearPublished=sc.nextLine();
										libOff.getBookDetailsUYear(title, yearPublished);
										System.out.println();
										break;
									case 7:
										System.out.println("Enter Year of Book Published to search:");
										yearPublished=sc.nextLine();
										libOff.getBookDetailsUYear(yearPublished);
										System.out.println();
										break;
									case 8:
										System.out.println("Enter Publisher of Book to search:");
										publisher=sc.nextLine();
										libOff.getBookDetailsUPublisher(publisher);
										System.out.println();
										break;
									case 9:
										System.out.println("Enter Book Title:");
										title=sc.nextLine();
										System.out.println("Enter Publisher of Book to search:");
										publisher=sc.nextLine();
										libOff.getBookDetailsUPublisher(title, publisher);
										System.out.println();
										break;
									case 10:
										System.out.println("Enter Book Title:");
										title=sc.nextLine();
										System.out.println("Enter Author of Book:");
										author=sc.nextLine();
										System.out.println("Enter Publisher of Book to search:");
										publisher=sc.nextLine();							
										libOff.getBookDetailsUPublisher(title,author, publisher);
										System.out.println();
										break;
									case 11:
										System.out.println("Enter Author of Book to search:");
										author=sc.nextLine();
										libOff.getBookDetailsUAuthor(author);
										System.out.println();
										break;
									case 12:
										System.out.println("Enter Author of Book:");
										author=sc.nextLine();
										System.out.println("Enter Year Published to search:");
										yearPublished=sc.nextLine();
										libOff.getBookDetailsUAuthor(author,yearPublished);
										System.out.println();
										break;
									case 13:
										ex="exit";
										break;
									default:
										System.out.println("Enter values between 1-13\n");
										continue;											
									}//switch ends
									if(ex=="exit")
										break;
								}//while ends
							}
							else if(choice==12) {
								System.out.println("Enter the Book Id:");
								bookId=sc.nextInt();
								sc.nextLine();
								libOff.getBookStatus(bookId);
								System.out.println();
							}
							else if(choice==13) {
								System.out.println("Enter the Book Id:");
								bookId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter 1 for Available or 0 for Not Available : ");
								int o=sc.nextInt();
								sc.nextLine();
								System.out.println("Are you sure to change it? (y/n)");
								s=sc.nextLine();
								if(s.equalsIgnoreCase("y")) {
									if(o==1)
										libOff.changeBookStatus(bookId, true);
									else if(o==0)
										libOff.changeBookStatus(bookId, false);
									else 
										System.out.println("Inappropriate Value\n");
									System.out.println("Status is changed Succesfully\n");
								}
								else {
									System.out.println("Canceled\n");
								}
							}
							else if(choice==14) {
								System.out.println("Enter Member Id:");
								memberId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter the Pending Amount or Enter '0' if paid fully : ");
								double amount=sc.nextDouble();
								sc.nextLine();
								System.out.println("Enter 'y' to proceed further : ");
								s=sc.nextLine();
								if(s.equalsIgnoreCase("y")) {
									libOff.changePendingAmount(memberId, amount);
									System.out.println("Changed Successfull!\n");
								}
								else {
									System.out.println("Canceled !\n");
								}
							}
							else if(choice==15) {                //exit
								break;
							}
							else {
								System.out.println("Enter value between 1-11 please!\n");
								continue;
							}
						}
						catch(Exception e) {
							System.out.println("Enter a Integer value between 1-11 please! \n");
							break;
						}
					}
				}
				else if(opt==3) {                       // Admin Login
					
					System.out.println("Enter the User Name :");
					String userName=sc.nextLine();
					System.out.println("Enter the Password  :");
					String password=sc.nextLine();
					System.out.println();
					
					if(login.get_admin_LogIn(userName, password)) {
						
						System.out.println("LogIn Succesfull!!");
						System.out.println("Welcome Admin!\n"); 
						
					}
					else {
						System.out.println("Invalid UserName or Password! Please try again");
						System.out.println();
						continue;
					}
					
					IAdmin admin=new Library();
					String firstName,lastName,gender,permAddress,currAddress,phoneNo,emailId,userNameL,passwordL,govProof,proofId;
					int age;
					int librarianId=0,memberId=0;
					while(true) {					
						try {
							System.out.println("Enter 1- Add Library Officials\n"+"Enter 2- Delete Library Offcials\n"+
						                       "Enter 3- Edit Library Officials\n"+"Enter 4- View Details of Library Officials\n"+
									           "Enter 5- Add Member\n"+"Enter 6- Delete Member\n"+"Enter 7- Edit Member\n"+
						                       "Enter 8- View Details of Member\n"+
									           "Enter 9- Reset Database\n"+"Enter 10- Books\n"+"Enter 11- Log Out\n--");
							int choice=sc.nextInt();
							sc.nextLine();
							System.out.println();
							if(choice==1) {
								while(true) {
									try {
										System.out.println("Enter - First Name : ");
										firstName=sc.nextLine();
										System.out.println("Enter - Last Name : ");
										lastName=sc.nextLine();
										System.out.println("Enter - Age : ");
										age=sc.nextInt();
										sc.nextLine();
										System.out.println("Enter - Gender (optional) : ");
										gender=sc.nextLine();
										System.out.println("Enter - Current Address : ");
										currAddress=sc.nextLine();
										System.out.println("Enter - Permanent Address (optional) : ");
										permAddress=sc.nextLine();
										System.out.println("Enter - Phone Number : ");
										phoneNo=sc.nextLine();
										System.out.println("Enter - Email Id : ");
										emailId=sc.nextLine();
										System.out.println("Enter a User Name : ");
										userNameL=sc.nextLine();
										System.out.println("Enter a password : ");
										passwordL=sc.nextLine();
										System.out.println("Enter the name of Government Proof : ");
										govProof=sc.nextLine();
										System.out.println("Enter the Valid Id of the Government Proof : ");
										proofId=sc.nextLine();
										
										admin.createLibraryOfficials(firstName, lastName, age, gender, permAddress, currAddress, phoneNo, emailId, govProof, proofId,userNameL, passwordL);
										System.out.println("Library Official Added susccessfully in Database");
										break;
									}
									catch(Exception e) {
										System.out.println("Enter a valid input please!");
										break;
									}
								}// while ends
							}//if ends
							else if(choice==2) {
								System.out.println("Enter the Library Official Id : ");
								librarianId=sc.nextInt();
								sc.nextLine();
								System.out.println("Are you sure to delete this Library Official? (y/n)");
								String s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									admin.delete_Librarian(librarianId);
									System.out.println("Library Officials Id("+librarianId+") is Deleted\n");
								}
								else {
									System.out.println("Deleting Library Officials is Canceled !\n");
									continue;
								}
							}
							else if(choice==3) {
								
								System.out.println("Enter the Library Official Id : ");
								librarianId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter - First Name : ");
								firstName=sc.nextLine();
								System.out.println("Enter - Last Name : ");
								lastName=sc.nextLine();
								System.out.println("Enter - Age : ");
								age=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter - Gender (optional) : ");
								gender=sc.nextLine();
								System.out.println("Enter - Current Address : ");
								currAddress=sc.nextLine();
								System.out.println("Enter - Permanent Address (optional) : ");
								permAddress=sc.nextLine();
								System.out.println("Enter - Phone Number : ");
								phoneNo=sc.nextLine();
								System.out.println("Enter - Email Id : ");
								emailId=sc.nextLine();
								System.out.println("Are you sure to Edit this Library Official? (y/n)");
								String s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									admin.edit_LibrarianDetails(librarianId, firstName, lastName, age, gender, permAddress, currAddress, phoneNo, emailId);
									System.out.println("Editing Successfull!\n");
								}
								else {
									System.out.println("Editing Library Officials Details is Canceled !\n");
									continue;
								}
							}
							else if(choice==4) {
								
								System.out.println("Enter the Library Official Id : ");
								librarianId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter 'y' to proceed further otherwise press any key");
								String s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									admin.get_LibrarianDetails(librarianId);
									
								}
								else {
									System.out.println("Canceled !\n");
									continue;
								}
							}
							else if(choice==5) {
								System.out.println("Enter - First Name : ");
								firstName=sc.nextLine();
								System.out.println("Enter - Last Name : ");
								lastName=sc.nextLine();
								System.out.println("Enter - Age : ");
								age=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter - Gender (optional) : ");
								gender=sc.nextLine();
								System.out.println("Enter - Current Address : ");
								currAddress=sc.nextLine();
								System.out.println("Enter - Permanent Address (optional) : ");
								permAddress=sc.nextLine();
								System.out.println("Enter - Phone Number : ");
								phoneNo=sc.nextLine();
								System.out.println("Enter - Email Id : ");
								emailId=sc.nextLine();
								System.out.println("Enter a User Name : ");
								userName=sc.nextLine();
								System.out.println("Enter a password : ");
								password=sc.nextLine();
								System.out.println("Enter 'y' to proceed further otherwise press any key");
								String s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
								admin.createMember(firstName, lastName, age, gender, permAddress, currAddress, phoneNo, emailId, userName, password);
								System.out.println("Member Added susccessfully in Database\n");
								
								}
								else {
									System.out.println("Adding Member is Canceled\n");
									continue;
								}
								
							}
							else if(choice==6) {
								System.out.println("Enter the Member Id");
								memberId=sc.nextInt();
								sc.nextLine();
								System.out.println("Are you sure to delete this Member? (y/n)");
								String s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									admin.delete_Member(memberId);
									System.out.println("Member Id("+memberId+") is Deleted\n");
								}
								else {
									System.out.println("Deleting Member is Canceled !\n");
									continue;
								}
							}
							else if(choice==7) {
								System.out.println("Enter the Member Id : ");
								memberId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter - First Name : ");
								firstName=sc.nextLine();
								System.out.println("Enter - Last Name : ");
								lastName=sc.nextLine();
								System.out.println("Enter - Age : ");
								age=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter - Gender (optional) : ");
								gender=sc.nextLine();
								System.out.println("Enter - Current Address : ");
								currAddress=sc.nextLine();
								System.out.println("Enter - Permanent Address (optional) : ");
								permAddress=sc.nextLine();
								System.out.println("Enter - Phone Number : ");
								phoneNo=sc.nextLine();
								System.out.println("Enter - Email Id : ");
								emailId=sc.nextLine();
								System.out.println("Are you sure to Edit this Member? (y/n)");
								String s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									admin.edit_MemberDetails(memberId, firstName, lastName, age, gender, permAddress, currAddress, phoneNo, emailId);
									System.out.println("Editing Successfull!\n");
								}
								else {
									System.out.println("Editing Member Details is Canceled !\n");
									continue;
								}
							}
							else if(choice==8) {
								
								System.out.println("Enter the Member Id");
								memberId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter 'y' to proceed further otherwise press any key");
								String s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									admin.get_MemberDetails(memberId);
									
								}
								else {
									System.out.println("Canceled !\n");
									continue;
								}
							}
							else if(choice==9) {
								//;
							}
							else if(choice==10) {
								
								String ISBN,barCode,title,author,publisher,yearPublished,department;
								double bookMRP;
								int bookId=0;
								String ex=null;
								while(true) {
									System.out.println("Enter 1- Add Book\n"+"Enter 2- Delete Book\n"+"Enter 3- Edit Book\n"+
								                "Enter 4- Issue Book\n"+"Enter 5- Return Book\n"+"Enter 6- Details of Total Book\n"+
											    "Enter 7- Search Books\n"+"Enter 8- View Book Status\n"+"Enter 9- change Book Status\n"+
								                "Enter 10- Change Pending Payment\n"+"Enter 11- EXIT\n");
									int ch=sc.nextInt();
									sc.nextLine();
									switch(ch) {
									case 1:
										System.out.println("Enter the ISBN : ");
										ISBN=sc.nextLine();
										System.out.println("Enter the Bar Code : ");
										barCode=sc.nextLine();
										System.out.println("Enter the Title of the Book : ");
										title=sc.nextLine();
										System.out.println("Enter the Author of the Book : ");
										author=sc.nextLine();
										System.out.println("Enter the Publisher of the Book : ");
										publisher=sc.nextLine();
										System.out.println("Enter the Year Published : ");
										yearPublished=sc.nextLine();
										System.out.println("Enter the MRP of the Book : ");
										bookMRP=sc.nextDouble();
										sc.nextLine();
										System.out.println("Enter the Department this Book belongs : ");
										department=sc.nextLine();
										
										System.out.println("Enter 'y' to proceed further, otherwise press any key");
										String s=sc.nextLine();
										System.out.println();
										if(s.equalsIgnoreCase("y")) {
											admin.addBook(ISBN, barCode, title, author, publisher, yearPublished, bookMRP, department);
											System.out.println("Susccesfully Book is added in Database\n");
										}
										else {
											System.out.println("Adding Book is cancelled\n");
											continue;
										}
										break;
									case 2:
										System.out.println("Enter the Book Id : ");
										bookId=sc.nextInt();									
										sc.nextLine();
										System.out.println("Are you sure to Delete this Book from the Database? (y/n)");
										s=sc.nextLine();
										System.out.println();
										if(s.equalsIgnoreCase("y")) {
											admin.deleteBook(bookId);
											System.out.println("Successfully Deleted\n");
										}
										else {
											System.out.println("Deleting Book is cancelled\n");
											continue;
										}
										break;
									case 3:
										System.out.println("Enter the Book Id : ");
										bookId=sc.nextInt();
										sc.nextLine();
										System.out.println("Enter the ISBN : ");
										ISBN=sc.nextLine();
										System.out.println("Enter the Bar Code : ");
										barCode=sc.nextLine();
										System.out.println("Enter the Title of the Book : ");
										title=sc.nextLine();
										System.out.println("Enter the Author of the Book : ");
										author=sc.nextLine();
										System.out.println("Enter the Publisher of the Book : ");
										publisher=sc.nextLine();
										System.out.println("Enter the Year Published : ");
										yearPublished=sc.nextLine();
										System.out.println("Enter the MRP of the Book : ");
										bookMRP=sc.nextDouble();
										sc.nextLine();
										System.out.println("Enter the Department this Book belongs : ");
										department=sc.nextLine();
										
										System.out.println("Are you sure to Edit this Book Details? (y/n)");
										s=sc.nextLine();
										System.out.println();
										if(s.equalsIgnoreCase("y")) {
											admin.editBookDetails(bookId, ISBN, barCode, title, author, publisher, yearPublished, bookMRP, department);
											System.out.println("Editing Book Details is successfull");
											System.out.println();
										}
										else {
											System.out.println("Editing Book is cancelled\n");
											continue;
										}
										break;
									case 4:
										System.out.println("Enter Member Id:");
										memberId=sc.nextInt();
										sc.nextLine();
										System.out.println("Enter Book Id:");
										bookId=sc.nextInt();
										sc.nextLine();
										System.out.println("Enter the Return Date or Due date (YYYY-MM-DD) :");
										String date=sc.nextLine();
										System.out.println("Enter 'y' to proceed further otherwise press any key");
										s=sc.nextLine();
										if(s.equalsIgnoreCase("y")) {
											admin.issueBook(memberId, bookId, date);
											
										}
										else {
											System.out.println("Canceled!\n");
										}
										break;
									case 5:
										System.out.println("Enter Member Id:");
										memberId=sc.nextInt();
										sc.nextLine();
										System.out.println("Enter Book Id:");
										bookId=sc.nextInt();
										sc.nextLine();
										System.out.println("If any 'Fine' for late submission,enter the amount otherwise enter '0' ");
										double amount=sc.nextDouble();
										s=sc.nextLine();
										System.out.println("Enter 'y' to proceed further otherwise press any key");
										s=sc.nextLine();
										if(s.equalsIgnoreCase("y")) {
											admin.returnBook(memberId, bookId);
											admin.addPendingAmount(memberId, amount);
											System.out.println("You have pending amount of Rs:"+(admin.getPendingAmount(memberId))+"/- for late Return.\n");						
										}
										else {
											System.out.println("Canceled!\n");
										}
										break;
									case 6:
										admin.getBookDetails();
										System.out.println();
										break;
									case 7:
										while(true) {
											System.out.println("Enter below options to search with:- ");
											System.out.println("1- BookId            "+" 2- Title\n"+
											                   "3- Title & Author    "+" 4- ISBN\n"+
											                   "5- Bar Code          "+" 6- Title & Year\n"+
											                   "7- Year              "+" 8- Publisher\n"+
											                   "9- Title & Publisher "+" 10- Title & Author & Publisher\n"+
											                   "11- Author           "+" 12- Author & Year\n"+
											                   "13- EXIT\n--");
											int c=sc.nextInt();
											sc.nextLine();
											switch(c) {
											case 1:
												System.out.println("Enter BookId to search:");
												bookId=sc.nextInt();
												sc.nextLine();
												admin.getBookDetails(bookId);
												System.out.println();
												break;
											case 2:
												System.out.println("Enter Book Title to search:");
												title=sc.nextLine();
												admin.getBookDetails(title);
												System.out.println();
												break;
											case 3:
												System.out.println("Enter Book Title:");
												title=sc.nextLine();
												System.out.println("Enter Book Author to search:");
												author=sc.nextLine();
												admin.getBookDetails(title,author);
												System.out.println();
												break;
											case 4:
												System.out.println("Enter Book ISBN to search:");
												ISBN=sc.nextLine();
												admin.getBookDetailsUISBN(ISBN);
												System.out.println();
												break;
											case 5:
												System.out.println("Enter Book Bar Code to search:");
												barCode=sc.nextLine();
												admin.getBookDetailsUBarCode(barCode);
												System.out.println();
												break;
											case 6:
												System.out.println("Enter Book Title:");
												title=sc.nextLine();
												System.out.println("Enter Year of Book Published to search:");
												yearPublished=sc.nextLine();
												admin.getBookDetailsUYear(title, yearPublished);
												System.out.println();
												break;
											case 7:
												System.out.println("Enter Year of Book Published to search:");
												yearPublished=sc.nextLine();
												admin.getBookDetailsUYear(yearPublished);
												System.out.println();
												break;
											case 8:
												System.out.println("Enter Publisher of Book to search:");
												publisher=sc.nextLine();
												admin.getBookDetailsUPublisher(publisher);
												System.out.println();
												break;
											case 9:
												System.out.println("Enter Book Title:");
												title=sc.nextLine();
												System.out.println("Enter Publisher of Book to search:");
												publisher=sc.nextLine();
												admin.getBookDetailsUPublisher(title, publisher);
												System.out.println();
												break;
											case 10:
												System.out.println("Enter Book Title:");
												title=sc.nextLine();
												System.out.println("Enter Author of Book:");
												author=sc.nextLine();
												System.out.println("Enter Publisher of Book to search:");
												publisher=sc.nextLine();							
												admin.getBookDetailsUPublisher(title,author, publisher);
												System.out.println();
												break;
											case 11:
												System.out.println("Enter Author of Book to search:");
												author=sc.nextLine();
												admin.getBookDetailsUAuthor(author);
												System.out.println();
												break;
											case 12:
												System.out.println("Enter Author of Book:");
												author=sc.nextLine();
												System.out.println("Enter Year Published to search:");
												yearPublished=sc.nextLine();
												admin.getBookDetailsUAuthor(author,yearPublished);
												System.out.println();
												break;
											case 13:
												ex="exit";
												break;
											default:
												System.out.println("Enter values between 1-13\n");
												continue;											
											}//switch ends
											if(ex=="exit")
												break;
										}//while ends
										break;
									case 8:
										System.out.println("Enter the Book Id:");
										bookId=sc.nextInt();
										sc.nextLine();
										if(admin.getBookStatus(bookId))
											System.out.println("Book Id ("+bookId+") is available\n");
										else
											System.out.println("Book Id ("+bookId+") is NOT available\n");
										break;
									case 9:
										System.out.println("Enter the Book Id:");
										bookId=sc.nextInt();
										sc.nextLine();
										System.out.println("Enter 1 for Available or 0 for Not Available : ");
										int o=sc.nextInt();
										sc.nextLine();
										System.out.println("Are you sure to change it? (y/n)");
										s=sc.nextLine();
										if(s.equalsIgnoreCase("y")) {
											if(o==1)
												admin.changeBookStatus(bookId, true);
											else if(o==0)
												admin.changeBookStatus(bookId, false);
											else 
												System.out.println("Inappropriate Value\n");
											System.out.println("Status is changed Succesfully\n");
										}
										else {
											System.out.println("Canceled\n");
										}
										break;
									case 10:
										System.out.println("Enter Member Id:");
										memberId=sc.nextInt();
										sc.nextLine();
										System.out.println("Enter the Pending Amount or Enter '0' if paid fully : ");
										amount=sc.nextDouble();
										sc.nextLine();
										System.out.println("Enter 'y' to proceed further : ");
										s=sc.nextLine();
										if(s.equalsIgnoreCase("y")) {
											admin.changePendingAmount(memberId, amount);
											System.out.println("Changed Successfull!\n");
										}
										else {
											System.out.println("Canceled !\n");
										}
										break;
									case 11:
										ex="exit";
										break;
									default:
										System.out.println("Enter values between 1-8\n");
										continue;
									}//switch ends
									if(ex=="exit")
										break;
									
								}//while ends
							}
							else if(choice==11) {             //exit
								break;
							}
							else {
								System.out.println("Enter values between 1-6 please");
								continue;
							}
						}
						catch(Exception e) {
							System.out.println("Enter a integer value between 1-6 please!");
							break;
						}
					}//while ends							
				}
				else if(opt==4) {           //closes the application
					System.out.println("       Thank you!! Application closed.");
					System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					new Library().closeConnection();
					break;
				}
				else {
					System.out.println("Enter value between 1 - 4 please!\n");
					continue;
				}
				
				
			}//outer most while ends	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
			
		}
	}//main ends
}
