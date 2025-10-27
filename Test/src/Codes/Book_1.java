
package Codes;
import java.util.Scanner;


public class Book_1 {
    String book;
    int price;
    String author;
    
    void add(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Book name");
        book=sc.nextLine();
        System.out.println("Enter Book price");
        price=sc.nextInt();
        System.out.println("Enter Author name");
        author=sc.nextLine();


    }
    void display(){
        System.out.println("Book name: "+book);
        System.out.println("Book price: "+price);
        System.out.println("Author name: "+author);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of books");
        int n = sc.nextInt();
        sc.nextLine();

        Book_1[] books=new Book_1[n];
        for(int i=0;i<n;i++){
            System.out.println("Enter details");
            books[i]=new Book_1();
            books[i].add();
        }

        System.out.println("Deatils:");
        for(int i=0;i<n;i++){
            books[i].display();
        }
    }
}
