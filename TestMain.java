import java.util.Scanner;

import org.hibernate.*;  
import org.hibernate.cfg.*;

public class TestMain {


	public static void main(String[] args) {  
		AnnotationConfiguration cfg=new AnnotationConfiguration();  
		Session session=cfg.configure("hibernate.cfg.xml").buildSessionFactory().openSession();  

		Transaction t=session.beginTransaction();  
		Scanner scan = new Scanner(System.in);

//		Employee e1=new Employee();  
//		e1.setName("sonoo");  
//
//		Regular_Employee e2=new Regular_Employee();  
//		e2.setName("Vivek Kumar");  
//		e2.setSalary(50000);  
//		e2.setBonus(5);  
//
//		Contract_Employee e3=new Contract_Employee();  
//		e3.setName("Arjun Kumar");  
//		e3.setPay_per_hour(1000);  
//		e3.setContract_duration("15 hours");  
		
		
			System.out.println("1.Employee\n2.Regular Employe\n3.Contract Employee\n4.Exit");
			int ch = scan.nextInt();
			scan.nextLine();
			String name;
			switch(ch){
			case 1:
				Employee e =new Employee();
				System.out.println("enter name");
				e.setName(scan.nextLine());
				session.persist(e);
				break;
			case 2:
				Regular_Employee e2 = new Regular_Employee();
				System.out.println("enter name");
				e2.setName(scan.nextLine());
				System.out.println("Enter salary");
				e2.setSalary(scan.nextFloat());
				System.out.println("Enter contact duration");
				e2.setBonus(scan.nextInt());
				session.persist(e2);
				break;
			case 3:
				Contract_Employee e3=new Contract_Employee();
				System.out.println("enter name");
				e3.setName(scan.nextLine());
				System.out.println("Enter pay per hour");
				e3.setPay_per_hour(scan.nextInt());
				System.out.println("Enter contact duration");
				e3.setContract_duration(scan.nextLine());
				session.persist(e3);
				break;
			case 4:
				System.exit(1);
			}
			t.commit();  
			 session.close();
			System.out.println("success"); 
				
	
		  
		 
		 
	}  
}  

