package servlet;

import java.io.BufferedReader;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;

public class NoteServlet extends HttpServlet {

   
      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            String path = getServletContext().getRealPath("/WEB-INF/note.txt");
                        
            //read all files
            BufferedReader readFile;
            readFile = new BufferedReader(new FileReader(new File(path)));
            Note note = new Note(readFile.readLine(), readFile.readLine());
            request.setAttribute("note", note);
           
            readFile.close();
            
            if(request.getParameter("edit") == null){
                getServletContext().getRequestDispatcher("/WEB-INF/viewNote.jsp").forward(request, response);
                return;
            }else{
                getServletContext().getRequestDispatcher("/WEB-INF/editNote.jsp").forward(request, response);
                return;
            }
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String path = getServletContext().getRealPath("/WEB-INF/note.txt");
            
            //write the file
            PrintWriter writeFile;
            writeFile = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
            writeFile.println(request.getParameter("title"));
            writeFile.println(request.getParameter("content"));
            writeFile.close();
            
            BufferedReader readAgain;
            readAgain = new BufferedReader(new FileReader(new File(path)));
            Note note = new Note(readAgain.readLine(), readAgain.readLine());
            request.setAttribute("note", note);
            
            readAgain.close();
            getServletContext().getRequestDispatcher("/WEB-INF/viewNote.jsp").forward(request, response);
            return;
    }

}
