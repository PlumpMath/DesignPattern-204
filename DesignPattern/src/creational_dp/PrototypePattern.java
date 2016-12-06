package creational_dp;

import java.io.IOException;
import java.util.Scanner;

interface Prototype {  
	  
    public Prototype getClone();  
     
}

class EmployeeRecord implements Prototype{  
    
    private int id;  
    private String name, designation;  
    private double salary;  
    private String address;  
       
    public EmployeeRecord(){  
             System.out.println("   Employee Records of Rahul&Co. ");  
             System.out.println("---------------------------------------------");  
             System.out.println("Eid"+"\t"+"Ename"+"\t"+"Edesignation"+"\t"+"Esalary"+"\t\t"+"Eaddress");  
       
 }  
   
  public  EmployeeRecord(int id, String name, String designation, double salary, String address) {  
           
         this();  
         this.id = id;  
         this.name = name;  
         this.designation = designation;  
         this.salary = salary;  
         this.address = address;  
     }  
       
   public void showRecord(){  
           
         System.out.println(id+"\t"+name+"\t"+designation+"\t"+salary+"\t"+address);  
    }  
   
     @Override  
     public Prototype getClone() {  
           
         return new EmployeeRecord(id,name,designation,salary,address);  
     }  
 } 

public class PrototypePattern {

	public static void main(String[] args)throws IOException{
		Scanner scan=new Scanner(System.in); 
        System.out.print("Enter Employee Id: ");  
        int eid=scan.nextInt(); 
        System.out.print("\n");  
          
        System.out.print("Enter Employee Name: ");  
        String ename=scan.nextLine();  
        System.out.print("\n");  
          
        System.out.print("Enter Employee Designation: ");  
        String edesignation=scan.nextLine();
        System.out.print("\n");  
          
        System.out.print("Enter Employee Address: ");  
        String eaddress=scan.nextLine();
        System.out.print("\n");  
          
        System.out.print("Enter Employee Salary: ");  
        double esalary= scan.nextDouble();  
        System.out.print("\n");  
           
        EmployeeRecord e1=new EmployeeRecord(eid,ename,edesignation,esalary,eaddress);  
          
        e1.showRecord();  
        System.out.println("\n");  
        EmployeeRecord e2=(EmployeeRecord) e1.getClone();  
        e2.showRecord();  
	}

}


