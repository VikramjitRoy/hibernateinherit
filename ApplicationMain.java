import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class ApplicationMain {
	static Scanner scan = new Scanner(System.in);
	static Connection con  = null;
	static Statement stmt = null;
	static String sql =null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_DB","root","Welcome123");
			stmt = con.createStatement();
			//input();
			int ch;
			while(true){
				System.out.println("1. Sum of salary\n2. No. of employees in each department.\n3. Sum of Salary paid to DEPARTMENT_ID 50.\n4. number of employees from each department where at least two employee present in department.\n5. EXit ");
				ch = scan.nextInt();
				switch(ch){
				case 1:
					sumOfSalary();
					break;
				case 2:
					noOfEmp();
					break;
				case 3:
					sumSalDept50();
					break;
				case 4:
					noOfEmpAtleast2();
					break;
				case 5:
					System.exit(0);
					break;
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static void noOfEmpAtleast2() throws SQLException {
		// TODO Auto-generated method stub
		sql = "SELECT Department_ID,count(*) FROM Employee group by Department_ID having count(*) > 2";
		executeSQL(sql);
		
	}
	private static void sumSalDept50() throws SQLException {
		// TODO Auto-generated method stub
		sql = "SELECT Department_ID,count(*) FROM Employee WHERE Department_Id = 50 group by Department_ID";
		executeSQL(sql);
	}
	private static void noOfEmp() throws SQLException {
		sql = "SELECT Department_Id,count(*) FROM Employee group by Department_ID";
		executeSQL(sql);
		
	}
	private static void sumOfSalary() throws SQLException {
		// TODO Auto-generated method stub
		sql = "SELECT Department_ID,sum(salary),Department_ID FROM Employee group by Department_ID";
		executeSQL(sql);		
	}
	private static void executeSQL(String sql) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet resultset = stmt.executeQuery(sql);
		while(resultset.next()){
			
			System.out.format("%-8d%-12d\n",resultset.getInt(1),resultset.getInt(2));
		}	
		
	}
	//CREATE TABLE EMPLOYEE (EMPLOYEE_ID int primary key,first_name varchar(50),last_name varchar(50),joining_Date int,
	//JOB_ID varchar(15),salary int,department_id int);
	private static void input() throws SQLException {
		// TODO Auto-generated method stub
		String insertSQL = "INSERT INTO Employee VALUES (?,?,?,?,?,?,?)";
		PreparedStatement insertEmp= con.prepareStatement(insertSQL);
		System.out.println("Employee Id");
		int id = scan.nextInt();
		insertEmp.setInt(1,id);
		scan.nextLine();
		System.out.println("First name:");
		String fname = scan.nextLine();
		insertEmp.setString(2,fname);
		System.out.println(("Last name :"));
		String lname = scan.nextLine();
		insertEmp.setString(3,lname);
		System.out.println("Joining date :");
		int jdate = scan.nextInt();
		insertEmp.setInt(4,jdate);
		scan.nextLine();
		System.out.println("Job ID :");
		String jid = scan.nextLine();
		insertEmp.setString(5,jid);
		System.out.println("Salary :");
		int sal = scan.nextInt();
		insertEmp.setInt(6,sal);
		System.out.println("Departmrnt ID :");
		int did = scan.nextInt();
		insertEmp.setInt(7,did);
		scan.nextLine();
		insertEmp.execute();
	}

}
