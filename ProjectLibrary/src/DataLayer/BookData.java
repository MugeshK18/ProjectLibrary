package DataLayer;

public final class BookData {

	private final String ISBN,barCode,title,author,publisher,yearPublished,Department;
	private final double bookMRP;
	private final int noOfBooks;
	private final int bookId;
	public BookData(String iSBN, String barCode, String title, String author, String publisher, String yearPublished,
			double bookMRP, String department,int noOfBooks) {
		ISBN = iSBN;
		this.barCode = barCode;
		this.title = title.toUpperCase();
		this.author = author.toUpperCase();
		this.publisher = publisher.toUpperCase();
		this.yearPublished = yearPublished;
		this.bookMRP = bookMRP;
		Department = department.toUpperCase();
		this.noOfBooks=noOfBooks;
		bookId=0;
	}
	public BookData(int bookId,String iSBN, String barCode, String title, String author, String publisher, String yearPublished,
			double bookMRP, String department,int noOfBooks) {
		this.bookId=bookId;
		ISBN = iSBN;
		this.barCode = barCode;
		this.title = title.toUpperCase();
		this.author = author.toUpperCase();
		this.publisher = publisher.toUpperCase();
		this.yearPublished = yearPublished;
		this.bookMRP = bookMRP;
		Department = department.toUpperCase();
		this.noOfBooks=noOfBooks;
	}
	
	public int getBookId() {
		return bookId;
	}
	public String getISBN() {
		return ISBN;
	}
	public String getBarCode() {
		return barCode;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	public String getPublisher() {
		return publisher;
	}
	public String getYearPublished() {
		return yearPublished;
	}
	public String getDepartment() {
		return Department;
	}
	public double getBookMRP() {
		return bookMRP;
	}
	public int getNoOfBooks() {
		return noOfBooks;
	}
	
}
