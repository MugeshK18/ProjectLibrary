package ApplicationLayer;

public interface ILoginCheck {

    public boolean get_admin_LogIn(String userName,String password);
	public boolean get_librarian_LogIn(String userName,String password);
	public boolean get_member_LogIn(String userName,String password);
	
	public int get_LibrarianId();
	public int get_MemberId();
}
