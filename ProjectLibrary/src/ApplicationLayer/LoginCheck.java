package ApplicationLayer;

import DataLayer.*;

public class LoginCheck implements ILoginCheck{

	IDBQuery query=DBQuery.get_instance();
	
	public boolean get_admin_LogIn(String userName,String password) {
		
		return query.admin_LogIn(userName, password);
	}
	public boolean get_librarian_LogIn(String userName,String password) {
			
		return query.librarian_LogIn(userName, password);
	}
	public boolean get_member_LogIn(String userName,String password) {
			
		return query.member_LogIn(userName, password);
	}
	
	public int get_LibrarianId() {
		return query.getLibrarianId();
	}
	public int get_MemberId() {
		return query.getMemberId();
	}
}
