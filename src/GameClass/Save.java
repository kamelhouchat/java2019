package GameClass;

//import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream; 

class Save extends ObjectOutputStream 
{ 
    public Save (OutputStream out) throws IOException 
    { 
        super(out); 
        
    } 
    
       /*   
   public static void main(String[] args) throws IOException, 
    ClassNotFoundException  
    { 
        FileOutputStream fout = new FileOutputStream("file.txt"); 
        Save oot = new Save(fout); 
        Character c = 'A'; 
          
        //illustrating annotateClass(Class<?> cl) method 
        oot.annotateClass(Character.class); 
          
        //Write the specified object to the ObjectOutputStream 
        oot.writeObject(c); 
          
        //flushing the stream 
        oot.flush(); 
          
        //closing the stream 
        oot.close(); 
          
        FileInputStream fin = new FileInputStream("file.txt"); 
        ObjectInputStream oit = new ObjectInputStream(fin); 
        System.out.print(oit.readObject()); 
        oit.close(); 
    } */
} 