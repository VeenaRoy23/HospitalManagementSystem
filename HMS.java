import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;
import java.nio.file.*;

class Employee implements Serializable{

    static Scanner s=new Scanner(System.in);
    String name;
    int age;
    String address;
    String empId;
    long contact;
    String duty;
    int count=10;

    String insert(String name,int age,String address,long contact,String duty)throws IOException
    {
        FileWriter fout=new FileWriter("Data.txt",true);
        
       
        fout.write("NAME: ");
        fout.write(name);
        fout.write("\n");
        fout.write("AGE: ");
        fout.write(age+"");
        fout.write("\n");
        fout.write("ADDRESS: ");
        fout.write(address);
        fout.write("\n");
        fout.write("CONTACT: ");
        fout.write(contact+" ");
        fout.write("\n");
        fout.write("SPECIALIZATION: ");
        fout.write(duty);
        fout.write("\n");
        fout.write("EMPLOYEE ID: ");
        empId=name.substring(0,4)+count;
        fout.write(empId);
        fout.write("\n"); 
        count++;
        fout.write("\n");
        fout.write("\n"); 

        fout.close();

        return empId;

    }
    
    void delete(String name) throws IOException 
    {
    int flag=0;
        String str = "NAME: " + name;
        Path filePath = Paths.get("Data.txt");
        List<String> lines = Files.readAllLines(filePath);
        Iterator<String> iterator = lines.iterator();
        FileOutputStream fout = new FileOutputStream("Data.txt");
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.contains(str)) {
            flag=1;
                System.out.println("Deleted " + name);
                for (int i = 0; i < 6; i++) {
                    iterator.remove();
                    iterator.next();
                }
            } 
            else {
                fout.write((line + System.lineSeparator()).getBytes());
            }
        }
	if(flag==0)
        {
        	System.out.println("Employee not found!");
        }    

        fout.close();
    }


   void update(String duty,String name) throws IOException
   {
        String str = "NAME: " + name;
        Path filePath = Paths.get("Data.txt");
        List<String> lines = Files.readAllLines(filePath);
        Iterator<String> iterator = lines.iterator();
        FileOutputStream fout = new FileOutputStream("Data.txt");
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.contains(str))
            {
                fout.write((line + System.lineSeparator()).getBytes());
                for(int i=0;i<5;i++)
                {
                    String str1=iterator.next();
                    fout.write((str1 + System.lineSeparator()).getBytes());
                }
                iterator.next();
                fout.write((("SPECIALIZATION: "+duty)+System.lineSeparator()).getBytes());
            }
            else{
                 fout.write((line + System.lineSeparator()).getBytes());
        }
   }
   System.out.println("\n"+name+" updated to "+duty);
}

   void search(String ID) throws IOException
   {
   int flag=0;
    String str = "EMPLOYEE ID: " + ID;
    Path filePath = Paths.get("Data.txt");
    List<String> lines = Files.readAllLines(filePath);
    Iterator<String> iterator = lines.iterator();
    FileOutputStream fout = new FileOutputStream("Data.txt");

    while (iterator.hasNext()) {
        String line = iterator.next();
        if (line.contains(str)) {
        flag=1;
            System.out.println("\n");
            System.out.println(line);
            fout.write((line + System.lineSeparator()).getBytes());
            for (int i = 0; i < 6; i++) {
                String str1=iterator.next();
                System.out.println(str1);
                fout.write((str1 + System.lineSeparator()).getBytes());
            }
        }
        else{
            fout.write((line + System.lineSeparator()).getBytes());
        }
        
    }
    if(flag==0)
    {
    System.out.println("Employee not found");
   }
            
       fout.close();
   }



void display() throws IOException
{
	Path filePath = Paths.get("Data.txt");
    List<String> lines = Files.readAllLines(filePath);
    Iterator<String> iterator = lines.iterator();
    FileOutputStream fout = new FileOutputStream("Data.txt");

    while (iterator.hasNext()) {
        String line = iterator.next();
        
            System.out.println(line);
            fout.write((line + System.lineSeparator()).getBytes());
            
        }
        
  }
  
}


class Roombooking implements Serializable {
    static Scanner s = new Scanner(System.in);
    String name;
    boolean isAvailable;
    int age;
    String address;
    int roomno;
    long contact;
    int singlecount = 101;
    int doublecount=201;
    int highcount=303;
    int roomAllocation(int numberofinmates)
    {
        if(numberofinmates==1)
        {
            roomno=singlecount;
            singlecount++;
        }
        else if(numberofinmates==2)
        {
            roomno=doublecount;
            doublecount++;
        }
        else
        {
            roomno=highcount;
            highcount++;
        }
        return roomno;
    }
    void insert(int roomno,int inmates,String name,int age,String address,long contact) throws IOException {
        FileWriter fouts = new FileWriter("Address.txt", true);
        fouts.write("ROOMNO : ");
        fouts.write(roomno + "");
        fouts.write("\n");
        fouts.write("NAME: ");
        fouts.write(name);
        fouts.write("\n");
        fouts.write("AGE: ");
        fouts.write(age + "");
        fouts.write("\n");
        s.nextLine(); 
        fouts.write("ADDRESS: ");
        fouts.write(address);
        fouts.write("\n");
        fouts.write("CONTACT: ");
        fouts.write(contact + " ");
        fouts.write("\n");
        s.nextLine(); 
        fouts.write("NO.OF INMATES:");
        fouts.write(inmates + "");
        fouts.write("\n");
        fouts.write("\n");
        fouts.close();
        return;

     
 }

    void delete(int roomno) throws IOException {
        String str = "ROOMNO : "+roomno;
        int flag=0;
        Path filePath = Paths.get("Address.txt");
        List<String> lines = Files.readAllLines(filePath);
        Iterator<String> iterator = lines.iterator();
        FileOutputStream fouts = new FileOutputStream("Address.txt");

        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.contains(str)) {
            flag=1;
                System.out.println( "\nThankYou..visit again");
               
                for (int i = 0; i < 6; i++) {
                    iterator.remove();
                    if (iterator.hasNext()) {
                        iterator.next();
                    }
                }
            } else {
                fouts.write((line + System.lineSeparator()).getBytes());
            }
        }
        if(flag==0)
        {
        	System.out.println("Room not found!");
        }    

        fouts.close();
    }
    
    
   void search(int roomno) throws IOException
   {
   int flag=0;
    String str = "ROOMNO : "+roomno;
    Path filePath = Paths.get("Address.txt");
    List<String> lines = Files.readAllLines(filePath);
    Iterator<String> iterator = lines.iterator();
    FileOutputStream fout = new FileOutputStream("Address.txt");

    while (iterator.hasNext()) {
        String line = iterator.next();
        if (line.contains(str)) {
        flag=1;
            System.out.println(line);
            fout.write((line + System.lineSeparator()).getBytes());
            for (int i = 0; i < 6; i++) {
                String str1=iterator.next();
                System.out.println(str1);
                fout.write((str1 + System.lineSeparator()).getBytes());
            }
        }
        else{
            fout.write((line + System.lineSeparator()).getBytes());
        }
        
    }
        if(flag==0)
        {
        	System.out.println("Room not found!");
        }    
       fout.close();
   }

void display() throws IOException
{
	Path filePath = Paths.get("Address.txt");
    List<String> lines = Files.readAllLines(filePath);
    Iterator<String> iterator = lines.iterator();
    FileOutputStream fout = new FileOutputStream("Address.txt");

    while (iterator.hasNext()) {
        String line = iterator.next();
        
            System.out.println(line);
            fout.write((line + System.lineSeparator()).getBytes());
            
        }
        
  }
 }
    
        






class HMS{
    public static void main(String args[]) throws IOException
    {
        int n,a,age,choice,roomno,inmates;
        String empId,name,specialization,address;
        long contact;
        Scanner s=new Scanner(System.in);
        Employee e=new Employee();
        Roombooking r=new Roombooking();
        
        do{
            System.out.println("\n1:EMPLOYEE\n2:INMATES\n3:EXIT\n");
            a=s.nextInt();
            switch(a)
            {
                case 1:
                    do{
                        System.out.println("\nMENU\n1:Register employee\n2:Search employee\n3:Update details\n4:Delete employee\n5:Display\n6:Display with specialization\n7:Exit");
                        choice=s.nextInt();
                        switch(choice)
                        {
                            case 1:
                                name=s.nextLine();
                                System.out.print("NAME:");
                                name=s.nextLine();
                                System.out.print("AGE:");
                                age=s.nextInt();
                                address=s.nextLine();
                                System.out.print("ADDRESS:");
                                address=s.nextLine();
                                System.out.print("CONTACT:");
                                contact=s.nextLong();
                                specialization=s.nextLine();
                                System.out.print("SPECIALIZATION:");
                                specialization=s.nextLine();
                                empId=e.insert(name,age,address,contact,specialization);
                                System.out.println("\nWelcome!...Your ID is "+empId+"\n");
                                break;
                            
                            case 2:
                                name=s.nextLine();
                                System.out.println("Enter the ID:");
                                empId=s.nextLine();
                                e.search(empId);
                                break;
                                
                            case 3:
                                name=s.nextLine();
                                System.out.println("Enter the name:");
                                name=s.nextLine();
                                System.out.println("Enter the new specialization:");
                                specialization=s.nextLine();
                                e.update(specialization,name);
                                break;
                            case 4:
                                name=s.nextLine();
                                System.out.print("Enter the name:");
                                name=s.nextLine();
                                e.delete(name);
                                break;
                                
                              /* case 6:
                              
                               	System.out.print("Enter specialization:");
                               	specialization=s.nextLine();
                               	specialization=s.nextLine();
                               	e.displaySpecialization(specialization);

                                */
                               case 5:
                               	e.display();
                               	break;
                                
                            case 7:
                                System.out.println("EXITING..");
                                break;
                                
                            

                        }
                    }while(choice!=7);
                    break;

                case 2:
                    do{
                        System.out.println("\nMENU\n1:Register Inmates\n2:delete Inmates\n3:Search room details\n4:Display \n5:Exit");
                        choice = s.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.print("NO.OF INMATES:");
                                inmates = s.nextInt();
                                roomno=r.roomAllocation(inmates);
                                System.out.println("Room allocated: "+roomno);
                                System.out.print("NAME:");
                                name = s.nextLine();
                                 System.out.print("AGE:");
                                age = s.nextInt();
                                 System.out.print("ADDRESS:");
                                address = s.nextLine();
                                 System.out.print("CONTACT:");
                                contact = s.nextLong();
                                r.insert(roomno,inmates,name,age,address,contact);
                                System.out.println("\nWelcome...Inmate Registered Successfully!\n");
                                break;

                            case 2:
                                s.nextLine(); 
                                System.out.print("Enter the Room No.:");
                                roomno = s.nextInt();
                                r.delete(roomno);
                                break;

                            case 3:
                                System.out.println("Room No:");
                                roomno=s.nextInt();
                                r.search(roomno);
                                break;
                                
                               case 4:
                               	r.display();
                               	break;
                                
                            case 5:
                                System.out.println("EXITING..");
                                break;
                        }
                        
                     } while (choice != 5);
                case 3:
                    System.out.println("EXITING..");
                    break;
                
            }

            
        }while(a!=3);
    }
   
}
