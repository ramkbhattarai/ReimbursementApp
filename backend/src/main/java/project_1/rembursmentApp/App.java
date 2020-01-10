package project_1.rembursmentApp;


import java.util.List;

import com.revature.models.Reim;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.models.User;
import com.revature.services.ReimService;
import com.revature.services.RoleService;
import com.revature.services.StatusService;
import com.revature.services.TypeService;
import com.revature.services.UserService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Testing" );
        UserService us = new UserService();
        ReimService rs = new ReimService();
        TypeService ts = new TypeService();
        RoleService ros = new RoleService();
        StatusService ss = new StatusService();
        
        List<User> users = us.getAllUsers();
        for(User u : users) {
        	System.out.println(u);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        List<Reim> reims = rs.getAllReims();
        for(Reim r : reims) {
        	System.out.println(r);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        List<Type> types = ts.getAllTypes();
        for(Type t : types) {
        	System.out.println(t);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        List<Role> roles = ros.getAllRoles();
        for(Role r : roles) {
        	System.out.println(r);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        List<Status> statuses = ss.getAllStatus();
        for(Status s : statuses) {
        	System.out.println(s);
        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
