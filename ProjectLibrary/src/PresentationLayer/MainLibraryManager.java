package PresentationLayer;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.Scanner;

import ApplicationLayer.Admin;
import ApplicationLayer.IAdmin;
import ApplicationLayer.ILibraryOfficials;
import ApplicationLayer.ILoginCheck;
import ApplicationLayer.IMembers;
import ApplicationLayer.Library;
import ApplicationLayer.LibraryOfficials;
import ApplicationLayer.LoginCheck;
import ApplicationLayer.Members;

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
					
					IMembers member=new Members();
					int bookId=0;
					//String date;
					ArrayList<HashMap<String,Object>> list=null;
					HashMap<String,Object> map=null;
					
					while(true) {
						
						System.out.println("Enter 1- View List of Books\n"+"Enter 2- Search Book\n"+"Enter 3- Borrow Book\n"+
						                   "Enter 4- Return Book\n"+"Enter 5- view Due Date\n"+"Enter 6- View Payment Details\n"+
								           "Enter 7- Pay Amount\n"+"Enter 8- View Member Details\n"+"Enter 9- View Borrowed Books\n"+
						                   "Enter 10- My Books\n"+"Enter 11- Edit Details\n"+"Enter 12- Log Out\n");
						int choice=sc.nextInt();
						sc.nextLine();
						if(choice==1) {
							
							list=new ArrayList<>();
							list=member.getBookDetails();
							printBookDetails(list);
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
									map=new HashMap<>();
									map=member.getBookDetails(bookId);
									printBookDetails(map);
									System.out.println();
									break;
								case 2:
									System.out.println("Enter Book Title to search:");
									title=sc.nextLine();
									list=new ArrayList<>();
									list=member.getBookDetails(title);
									printBookDetails(list);
									System.out.println();
									break;
								case 3:
									System.out.println("Enter Book Title:");
									title=sc.nextLine();
									System.out.println("Enter Book Author to search:");
									author=sc.nextLine();
									list=new ArrayList<>();
									list=member.getBookDetails(title,author);
									printBookDetails(list);
									System.out.println();
									break;
								case 4:
									System.out.println("Enter Book ISBN to search:");
									ISBN=sc.nextLine();
									list=new ArrayList<>();
									list=member.getBookDetailsUISBN(ISBN);
									printBookDetails(list);
									System.out.println();
									break;
								case 5:
									System.out.println("Enter Book Bar Code to search:");
									barCode=sc.nextLine();
									list=new ArrayList<>();
									list=member.getBookDetailsUBarCode(barCode);
									printBookDetails(list);
									System.out.println();
									break;
									
								case 6:
									System.out.println("Enter Book Title:");
									title=sc.nextLine();
									System.out.println("Enter Year of Book Published to search:");
									yearPublished=sc.nextLine();
									list=new ArrayList<>();
									list=member.getBookDetailsUYear(title, yearPublished);
									printBookDetails(list);
									System.out.println();
									break;
								case 7:
									System.out.println("Enter Year of Book Published to search:");
									yearPublished=sc.nextLine();
									list=new ArrayList<>();
									list=member.getBookDetailsUYear(yearPublished);
									printBookDetails(list);
									System.out.println();
									break;
								case 8:
									System.out.println("Enter Publisher of Book to search:");
									publisher=sc.nextLine();
									list=new ArrayList<>();
									list=member.getBookDetailsUPublisher(publisher);
									printBookDetails(list);
									System.out.println();
									break;
								case 9:
									System.out.println("Enter Book Title:");
									title=sc.nextLine();
									System.out.println("Enter Publisher of Book to search:");
									publisher=sc.nextLine();
									list=new ArrayList<>();
									list=member.getBookDetailsUPublisher(title, publisher);
									printBookDetails(list);
									System.out.println();
									break;
								case 10:
									System.out.println("Enter Book Title:");
									title=sc.nextLine();
									System.out.println("Enter Author of Book:");
									author=sc.nextLine();
									System.out.println("Enter Publisher of Book to search:");
									publisher=sc.nextLine();
									list=new ArrayList<>();
									list=member.getBookDetailsUPublisher(title,author, publisher);
									printBookDetails(list);
									System.out.println();
									break;
								case 11:
									System.out.println("Enter Author of Book to search:");
									author=sc.nextLine();
									list=new ArrayList<>();
									list=member.getBookDetailsUAuthor(author);
									printBookDetails(list);
									System.out.println();
									break;
								case 12:
									System.out.println("Enter Author of Book:");
									author=sc.nextLine();
									System.out.println("Enter Year Published to search:");
									yearPublished=sc.nextLine();
									list=new ArrayList<>();
									list=member.getBookDetailsUAuthor(author,yearPublished);
									printBookDetails(list);
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
							System.out.println("Enter 'y' to Borrow Book otherwise press any key:");
							String str=sc.nextLine();
							
							System.out.println();
							if(str.equalsIgnoreCase("y")) {
								String msg;
								System.out.println("Please note that If not return by the due date, Rs:5/- will be charged for each day past due date.");
								msg=member.issueBook(memberId, bookId);
								System.out.println(msg);
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
								System.out.println("Book Id("+bookId+") is successfully returned");
								System.out.println("You have pending amount of Rs:"+(member.getPendingAmount(memberId))+"/- for late Return.\n");
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
							System.out.println("The Pending amount to be pad is Rs:"+member.getPendingAmount(memberId)+"/- due to late return of Books you borrowed.");
//							System.out.println("Press 'y' to proceed payment:");
//							String str=sc.nextLine();
//							if(str.equalsIgnoreCase("y")) {
								System.out.println("Redirecting to payment page....");
								System.out.println("Enter the Amount: ");
								double amount=sc.nextDouble();
								sc.nextLine();
								System.out.println("Press 'y' to confirm otherwise press any key: ");
								String str=sc.nextLine();
								if(str.equalsIgnoreCase("y")) {
									String message;
									message=member.payAmount(memberId,amount);
									System.out.println(message+"\n");
								}													
								else {
									System.out.println("Transaction canceled.");
									System.out.println("The Pending amount is Rs:"+member.getPendingAmount(memberId)+"\n");
								}
						}
						else if(choice==8) {
							map=new HashMap<>();
							map=member.get_MemberDetails(memberId);
							printMemberDetails(map);
							System.out.println();
						}
						else if(choice==9) {
							list=new ArrayList<>();
							list=member.getBorrowedBooks(memberId);
							printBorrowedBooks(list);
							System.out.println();
						}
						else if(choice==10) {
							list=new ArrayList<>();
							list=member.getMyBooks(memberId);
							printBorrowedBooks(list);
							System.out.println();
						}
						else if(choice==11) {
							String ex=null;
							String firstName,lastName,currAddress,permAddress,phoneNo,new_password;
							
							while(true) {
								System.out.println("Enter 1- Change Name\n"+"Enter 2- Change Address\n"+"Enter 3- Change Phone No.\n"+
							                       "Enter 4- Change Password\n"+"Enter 5- EXIT ");
								int op=sc.nextInt();
								sc.nextLine();
								switch(op) {
								case 1:
									System.out.println("Enter the First Name :");
									firstName=sc.nextLine();
									System.out.println("Enter the Last Name :");
									lastName=sc.nextLine();
									System.out.println("Are you sure to change the Name? (y/n):");
									String str=sc.nextLine();
									if(str.equalsIgnoreCase("y")) {
										member.changeName(memberId, firstName, lastName);
										System.out.println("Name is changed successfully!\n");
									}
									else {
										System.out.println("Updating Canceled!\n");
									}
									break;
								case 2:
									System.out.println("Enter the Current Address :");
									currAddress=sc.nextLine();
									System.out.println("Enter the Permanent Address :");
									permAddress=sc.nextLine();
									System.out.println("Are you sure to change the Address? (y/n):");
									str=sc.nextLine();
									if(str.equalsIgnoreCase("y")) {
										member.changeAddress(memberId, currAddress, permAddress);
										System.out.println("Address is changed successfully!\n");
									}
									else {
										System.out.println("Updating Canceled!\n");
									}
									break;
								case 3:
									System.out.println("Enter the Phone No. :");
									phoneNo=sc.nextLine();
									System.out.println("Are you sure to change the phone No.? (y/n) :");
									str=sc.nextLine();
									if(str.equalsIgnoreCase("y")) {
										member.changePhoneNo(memberId, phoneNo);
										System.out.println("Phone No. is changed successfully!\n");
									}
									else {
										System.out.println("Updating Canceled!\n");
									}
									break;
								case 4:
									System.out.println("Please Log In again to confirm! ");
									System.out.println("Enter the User Name:");
									userName=sc.nextLine();
									System.out.println("Enter the password");
									password=sc.nextLine();
									if(login.get_member_LogIn(userName, password)) {
										System.out.println("successfull");
										System.out.println("Enter the new Password:");
										new_password=sc.nextLine();
										System.out.println("Re-Enter the new Password:");
										String re_password=sc.nextLine();
										if(new_password.equals(re_password)) {
											System.out.println("Are you sure to change your Password? (y/n):");
											String ch=sc.nextLine();
											if(ch.equalsIgnoreCase("y")) {
												member.changePassword(memberId, new_password);
												System.out.println("Password is changed successfully! \n");
											}
											else
												System.out.println("Changing Password is canceled!\n");
										}
										else {
											System.out.println("passwords do not match! please try again.\n");
										}
									}
									else {
										System.out.println("Invalid username or password! please try again\n");
									}
									break;
								case 5:
									ex="exit";
									break;
								default:
									System.out.println("Enter value between 1-5 only please!\n");
									continue;
								} // switch ends
								if(ex=="exit")
									break;
							}//while ends
						}
						else if(choice==12) {
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
					
					ILibraryOfficials libOff=new LibraryOfficials();
					String firstName,lastName,gender,permAddress,currAddress,phoneNo,emailId,userNameM,passwordM;
					String ISBN,barCode,title,author,publisher,yearPublished,department;
					double bookMRP;
					int age,bookId,memberId,noOfBooks;
					String s;
					ArrayList<HashMap<String,Object>> list=null;
					HashMap<String,Object> map=null;					
					while(true) {
						try {
							System.out.println("Enter 1- Add Book\n"+"Enter 2- Delete Book\n"+"Enter 3- Edit Book\n"+
						                       "Enter 4- Issue Book\n"+"Enter 5- Return Book\n"+"Enter 6- Veiw Details of Book\n"+
									           "Enter 7- Add Member\n"+"Enter 8- Delete Member\n"+"Enter 9- Edit Member\n"+
						                       "Enter 10- View Member's Details\n"+"Enter 11- Search Book\n"+"Enter 12- View Book Status\n"+
									            "Enter 13- change Book Status\n"+
								                "Enter 14- Change Pending Payment\n"+"Enter 15- change My Password\n"+"Enter 16- Log Out\n--");
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
								System.out.println("Enter the No. of Copies: ");
								noOfBooks=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter 'y' to proceed further, otherwise press any key");
								s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									libOff.addBook(ISBN, barCode, title, author, publisher, yearPublished, bookMRP, department,noOfBooks);
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
								System.out.println("Enter 'y' to proceed further otherwise press any key");
								s=sc.nextLine();
								if(s.equalsIgnoreCase("y")) {
									String msg;
									System.out.println("Please note that If not return by the due date, Rs:5/- will be charged for each day past due date.");
									msg=libOff.issueBook(memberId, bookId);
									System.out.println(msg);
									System.out.println();
									
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
									System.out.println("Book Id("+bookId+") is successfully returned");
									System.out.println("You have pending amount of Rs:"+(libOff.getPendingAmount(memberId))+"/- for late Return.\n");						
								}
								else {
									System.out.println("Canceled!\n");
								}
							}
							else if(choice==6) {
								list=new ArrayList<>();
								list=libOff.getBookDetails();
								printBookDetails(list);
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
									map=new HashMap<>();
									map=libOff.get_MemberDetails(memberId);
									printMemberDetails(map);
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
										map=new HashMap<>();									
										map=libOff.getBookDetails(bookId);
										System.out.println();
										printBookDetails(map);
										break;
									case 2:
										System.out.println("Enter Book Title to search:");
										title=sc.nextLine();
										list=new ArrayList<>();
										list=libOff.getBookDetails(title);
										printBookDetails(list);
										System.out.println();
										break;
									case 3:
										System.out.println("Enter Book Title:");
										title=sc.nextLine();
										System.out.println("Enter Book Author to search:");
										author=sc.nextLine();
										list=new ArrayList<>();
										list=libOff.getBookDetails(title,author);
										printBookDetails(list);
										System.out.println();
										break;
									case 4:
										System.out.println("Enter Book ISBN to search:");
										ISBN=sc.nextLine();
										list=new ArrayList<>();
										list=libOff.getBookDetailsUISBN(ISBN);
										printBookDetails(list);
										System.out.println();
										break;
									case 5:
										System.out.println("Enter Book Bar Code to search:");
										barCode=sc.nextLine();
										list=new ArrayList<>();
										list=libOff.getBookDetailsUBarCode(barCode);
										printBookDetails(list);
										System.out.println();
										break;
									case 6:
										System.out.println("Enter Book Title:");
										title=sc.nextLine();
										System.out.println("Enter Year of Book Published to search:");
										yearPublished=sc.nextLine();
										list=new ArrayList<>();
										list=libOff.getBookDetailsUYear(title, yearPublished);
										printBookDetails(list);
										System.out.println();
										break;
									case 7:
										System.out.println("Enter Year of Book Published to search:");
										yearPublished=sc.nextLine();
										list=new ArrayList<>();
										list=libOff.getBookDetailsUYear(yearPublished);
										printBookDetails(list);
										System.out.println();
										break;
									case 8:
										System.out.println("Enter Publisher of Book to search:");
										publisher=sc.nextLine();
										list=new ArrayList<>();
										list=libOff.getBookDetailsUPublisher(publisher);
										printBookDetails(list);
										System.out.println();
										break;
									case 9:
										System.out.println("Enter Book Title:");
										title=sc.nextLine();
										System.out.println("Enter Publisher of Book to search:");
										publisher=sc.nextLine();
										list=new ArrayList<>();
										list=libOff.getBookDetailsUPublisher(title, publisher);
										printBookDetails(list);
										System.out.println();
										break;
									case 10:
										System.out.println("Enter Book Title:");
										title=sc.nextLine();
										System.out.println("Enter Author of Book:");
										author=sc.nextLine();
										System.out.println("Enter Publisher of Book to search:");
										publisher=sc.nextLine();
										list=new ArrayList<>();
										list=libOff.getBookDetailsUPublisher(title,author, publisher);
										printBookDetails(list);
										System.out.println();
										break;
									case 11:
										System.out.println("Enter Author of Book to search:");
										author=sc.nextLine();
										list=new ArrayList<>();
										list=libOff.getBookDetailsUAuthor(author);
										printBookDetails(list);
										System.out.println();
										break;
									case 12:
										System.out.println("Enter Author of Book:");
										author=sc.nextLine();
										System.out.println("Enter Year Published to search:");
										yearPublished=sc.nextLine();
										list=new ArrayList<>();
										list=libOff.getBookDetailsUAuthor(author,yearPublished);
										printBookDetails(list);
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
								System.out.println("Enter the Pending Amount : ");
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
							else if(choice==15) {
								System.out.println("Please Log In again to confirm! ");
								System.out.println("Enter the User Name:");
								userName=sc.nextLine();
								System.out.println("Enter the password");
								password=sc.nextLine();
								String new_password;
								if(login.get_librarian_LogIn(userName, password)) {
									System.out.println("successfull");
									System.out.println("Enter the new Password:");
									new_password=sc.nextLine();
									System.out.println("Re-Enter the new Password:");
									String re_password=sc.nextLine();
									if(new_password.equals(re_password)) {
										System.out.println("Are you sure to change your Password? (y/n):");
										String ch=sc.nextLine();
										if(ch.equalsIgnoreCase("y")) {
											libOff.changeLibPassword(librarianId, new_password);
											System.out.println("Password is changed successfully! \n");
										}
										else
											System.out.println("Changing Password is canceled!\n");
									}
									else {
										System.out.println("passwords do not match! please try again.\n");
									}
								}
								else {
									System.out.println("Invalid username or password! please try again\n");
								}
							}
							else if(choice==16) {                //exit
								System.out.println("Logged out successfully! \n");
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
					
					IAdmin admin=new Admin();
					String firstName,lastName,gender,permAddress,currAddress,phoneNo,emailId,userNameL,passwordL,govProof,proofId;
					int age,noOfBooks;
					int librarianId=0,memberId=0;
					ArrayList<HashMap<String,Object>> list=null;
					HashMap<String,Object> map=null;
					
					while(true) {					
						try {
							System.out.println("Enter 1- Add Library Officials\n"+"Enter 2- Delete Library Offcials\n"+
						                       "Enter 3- Edit Library Officials\n"+"Enter 4- View Details of Library Officials\n"+
									           "Enter 5- Add Member\n"+"Enter 6- Delete Member\n"+"Enter 7- Edit Member\n"+
						                       "Enter 8- View Details of Member\n"+
									           "Enter 9- Books\n"+"Enter 10- Log Out\n--");
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
								
								while(true) {
									System.out.println("Enter 1- Edit All\n"+"Enter 2- Edit Name\n"+"Enter 3- Edit Address\n"+
								                       "Enter 4- Edit PhoneNo\n"+"Enter 5- Exit\n");
									int ch=sc.nextInt();
									sc.nextLine();
									String ex=null;
									switch(ch) {
									case 1:
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
										break;
									case 2:
										System.out.println("Enter the Librarian Id: ");
										librarianId=sc.nextInt();
										sc.nextLine();
										System.out.println("Enter the FirstName: ");
										firstName=sc.nextLine();
										System.out.println("Enter the LastName: ");
										lastName=sc.nextLine();
										System.out.println("Enter 'y' to proceed further : ");
										s=sc.nextLine();
										if(s.equalsIgnoreCase("y")) {
											admin.changeLibName(librarianId, firstName, lastName);
											System.out.println("Editing successfull! \n");
										}
										else {
											System.out.println("Editing canceled! \n");
										}
										break;
									case 3:
										System.out.println("Enter the Librarian Id: ");
										librarianId=sc.nextInt();
										sc.nextLine();
										System.out.println("Enter the Current Address: ");
										currAddress=sc.nextLine();
										System.out.println("Enter the Permanent Address: ");
										permAddress=sc.nextLine();
										System.out.println("Enter 'y' to proceed further : ");
										s=sc.nextLine();
										if(s.equalsIgnoreCase("y")) {
											admin.changeLibAddress(librarianId, currAddress, permAddress);
											System.out.println("Editing successfull! \n");
										}
										else {
											System.out.println("Editing canceled! \n");
										}
										break;
									case 4:
										System.out.println("Enter the Librarian Id: ");
										librarianId=sc.nextInt();
										sc.nextLine();
										System.out.println("Enter the Phone No. : ");
										phoneNo=sc.nextLine();
										System.out.println("Enter 'y' to proceed further : ");
										s=sc.nextLine();
										if(s.equalsIgnoreCase("y")) {
											admin.changeLibPhoneNo(librarianId, phoneNo);
											System.out.println("Editing successfull! \n");
										}
										else {
											System.out.println("Editing canceled! \n");
										}
										break;										
									case 5:
										ex="exit";
										break;
									default:
										continue;
									}//switch ends
									if(ex=="exit")
										break;
								}//while ends
							}
							else if(choice==4) {
								
								System.out.println("Enter the Library Official Id : ");
								librarianId=sc.nextInt();
								sc.nextLine();
								System.out.println("Enter 'y' to proceed further otherwise press any key");
								String s=sc.nextLine();
								System.out.println();
								if(s.equalsIgnoreCase("y")) {
									map=new HashMap<>();
									map=admin.get_LibrarianDetails(librarianId);
									printLibrarianDetails(map);									
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
									map=new HashMap<>();
									map=admin.get_MemberDetails(memberId);
									printMemberDetails(map);
									
								}
								else {
									System.out.println("Canceled !\n");
									continue;
								}
							}
							else if(choice==9) {
								
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
										System.out.println("Enter No. of Copies: ");
										noOfBooks=sc.nextInt();
										sc.nextLine();
										
										System.out.println("Enter 'y' to proceed further, otherwise press any key");
										String s=sc.nextLine();
										System.out.println();
										if(s.equalsIgnoreCase("y")) {
											admin.addBook(ISBN, barCode, title, author, publisher, yearPublished, bookMRP, department,noOfBooks);
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
										
										System.out.println("Enter 'y' to proceed further otherwise press any key");
										s=sc.nextLine();
										if(s.equalsIgnoreCase("y")) {
											String msg;
											System.out.println("Please note that If not return by the due date, Rs:5/- will be charged for each day past due date.");
											msg=admin.issueBook(memberId, bookId);
											System.out.println(msg);
											System.out.println();
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
											System.out.println("Book Id("+bookId+") is succcessfully returned");
											System.out.println("You have pending amount of Rs:"+(admin.getPendingAmount(memberId))+"/- for late Return.\n");						
										}
										else {
											System.out.println("Canceled!\n");
										}
										break;
									case 6:										
										list=new ArrayList<>();
										list=admin.getBookDetails();
										printBookDetails(list);
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
												map=new HashMap<>();
												map=admin.getBookDetails(bookId);
												printBookDetails(map);
												System.out.println();
												break;
											case 2:
												System.out.println("Enter Book Title to search:");
												title=sc.nextLine();
												list=new ArrayList<>();
												list=admin.getBookDetails(title);
												printBookDetails(list);
												System.out.println();
												break;
											case 3:
												System.out.println("Enter Book Title:");
												title=sc.nextLine();
												System.out.println("Enter Book Author to search:");
												author=sc.nextLine();
												list=new ArrayList<>();
												list=admin.getBookDetails(title,author);
												printBookDetails(list);
												System.out.println();
												break;
											case 4:
												System.out.println("Enter Book ISBN to search:");
												ISBN=sc.nextLine();
												list=new ArrayList<>();
												list=admin.getBookDetailsUISBN(ISBN);
												printBookDetails(list);
												System.out.println();
												break;
											case 5:
												System.out.println("Enter Book Bar Code to search:");
												barCode=sc.nextLine();
												list=new ArrayList<>();
												list=admin.getBookDetailsUBarCode(barCode);
												printBookDetails(list);
												System.out.println();
												break;
												
											case 6:
												System.out.println("Enter Book Title:");
												title=sc.nextLine();
												System.out.println("Enter Year of Book Published to search:");
												yearPublished=sc.nextLine();
												list=new ArrayList<>();
												list=admin.getBookDetailsUYear(title, yearPublished);
												printBookDetails(list);
												System.out.println();
												break;
											case 7:
												System.out.println("Enter Year of Book Published to search:");
												yearPublished=sc.nextLine();
												list=new ArrayList<>();
												list=admin.getBookDetailsUYear(yearPublished);
												printBookDetails(list);
												System.out.println();
												break;
											case 8:
												System.out.println("Enter Publisher of Book to search:");
												publisher=sc.nextLine();
												list=new ArrayList<>();
												list=admin.getBookDetailsUPublisher(publisher);
												printBookDetails(list);
												System.out.println();
												break;
											case 9:
												System.out.println("Enter Book Title:");
												title=sc.nextLine();
												System.out.println("Enter Publisher of Book to search:");
												publisher=sc.nextLine();
												list=new ArrayList<>();
												list=admin.getBookDetailsUPublisher(title, publisher);
												printBookDetails(list);
												System.out.println();
												break;
											case 10:
												System.out.println("Enter Book Title:");
												title=sc.nextLine();
												System.out.println("Enter Author of Book:");
												author=sc.nextLine();
												System.out.println("Enter Publisher of Book to search:");
												publisher=sc.nextLine();
												list=new ArrayList<>();
												list=admin.getBookDetailsUPublisher(title,author, publisher);
												printBookDetails(list);
												System.out.println();
												break;
											case 11:
												System.out.println("Enter Author of Book to search:");
												author=sc.nextLine();
												list=new ArrayList<>();
												list=admin.getBookDetailsUAuthor(author);
												printBookDetails(list);
												System.out.println();
												break;
											case 12:
												System.out.println("Enter Author of Book:");
												author=sc.nextLine();
												System.out.println("Enter Year Published to search:");
												yearPublished=sc.nextLine();
												list=new ArrayList<>();
												list=admin.getBookDetailsUAuthor(author,yearPublished);
												printBookDetails(list);
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
										System.out.println("Enter the Pending Amount : ");
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
							else if(choice==10) {             //exit
								System.out.println("Logged out successfully! \n");
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
	
	private static void printLibrarianDetails(HashMap<String,Object> map) {
		
System.out.println("Library Official Details:- \n");
		
		System.out.println("Library Official Id: "+map.get("LibrarianId"));
		System.out.println("First Name: "+map.get("FirstName"));
		System.out.println("Last Name : "+map.get("LastName"));
		System.out.println("Age       : "+map.get("Age"));
		System.out.println("Gender    : "+map.get("Gender"));
		System.out.println("Current Address    : "+map.get("CurrentAddress"));
		System.out.println("Permanent Address  : "+map.get("PermanentAddress"));
		System.out.println("Phone No. : "+map.get("PhoneNo"));
		System.out.println("Email Id  : "+map.get("EmailId"));
		System.out.println("Government Proof submitted : "+map.get("GovernmentProof"));
		System.out.println("Proof Valid Id             : "+map.get("ProofId"));
		System.out.println();
	}
	private static void printMemberDetails(HashMap<String,Object> map) {
		
System.out.println("Member Details:- \n");
		
		System.out.println("Member Id : "+map.get("MemberId"));
		System.out.println("First Name: "+map.get("FirstName"));
		System.out.println("Last Name : "+map.get("LastName"));
		System.out.println("Age       : "+map.get("Age"));
		System.out.println("Gender    : "+map.get("Gender"));
		System.out.println("Current Address    : "+map.get("CurrentAddress"));
		System.out.println("Permanent Address  : "+map.get("PermanentAddress"));
		System.out.println("Phone No. : "+map.get("PhoneNo"));
		System.out.println("Email Id  : "+map.get("EmailId"));
		System.out.println();
	}
	private static void printBookDetails(ArrayList<HashMap<String,Object>> list) {
		
		boolean check=false;
		//Object status;
		int hello=0;
		hello++;
		//System.out.println("--"+hello);
		if(!list.isEmpty()) {
			System.out.println("List of Total Books +"+hello);
			for(HashMap<String,Object> map:list) {
				check=true;
				System.out.print("Book Id : "+map.get("BookId"));
				System.out.print("  ISBN : "+map.get("ISBN"));
		 		System.out.print("  Title : "+map.get("Title"));
				System.out.print("  Author : "+map.get("Author"));
				System.out.print("  Publisher : "+map.get("Publisher"));
				System.out.print("  Year Published : "+map.get("YearPublished"));
				System.out.print("  MRP of Book : "+map.get("BookMRP"));
				System.out.print("  Department : "+map.get("Department"));		
				System.out.print("  Status : "+map.get("Status"));
	//			if(status=="true")
	//				System.out.print("  Status : Available");
	//			else
	//				System.out.print("  Status : Not Available");
				System.out.println();
			}
		}
		else 
			System.out.println("list is null!");
		System.out.println();
		if(!check)
			System.out.println("No books available matching with your description!! please try again ***\n");
	}
	
	private static void printBookDetails(HashMap<String,Object> map) {
		
		System.out.println("Details of Book Id("+map.get("BookId")+")");
		System.out.println("Book Id : "+map.get("BookId"));
		System.out.println("ISBN    : "+map.get("ISBN"));
 		System.out.println("Title          : "+map.get("Title"));
		System.out.println("Author         : "+map.get("Author"));
		System.out.println("Publisher      : "+map.get("Publisher"));
		System.out.println("Year Published : "+map.get("YearPublished"));
		System.out.println("MRP of Book : "+map.get("BookMRP"));
		System.out.println("Department  : "+map.get("Department"));
		System.out.println("Status :"+map.get("Status"));
		System.out.println();
	}
	private static void printBorrowedBooks(ArrayList<HashMap<String,Object>> list) {
		
		boolean check=false;
		if(!list.isEmpty()) {
			System.out.println("Details of Books :-");
			for(HashMap<String,Object> map:list) {
				check=true;
				System.out.print("Member Id  : "+map.get("MemberId"));
				System.out.print("  Book Id : "+map.get("BookId"));
				System.out.print("  Issued Date : "+map.get("IssuedDate"));
		 		System.out.print("  Due Date : "+map.get("DueDate"));
				System.out.print("  Returned Date : "+map.get("ReturnedTime"));	
				System.out.print("  Returned Status :"+map.get("ReturnedStatus"));
				System.out.println();
			}
		}
		else 
			System.out.println("list is null!");
		System.out.println();
		if(!check)
			System.out.println("No books available!!\n");
	}
}
