/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 *
 * @author AFZAAL
 */
public class Tt_backtrack implements Serializable{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n what do you want to do?\n1:generate timetable\t2:modify the existing time table");
        int ch = Integer.parseInt(obj.readLine());
        if (ch == 1) {
            System.out.println("enter the number of semesters");
            int n = Integer.parseInt(obj.readLine());
            semester[] sem = new semester[n];
            int flag = 0;
            int[] sem_array = new int[n];
            for (int i = 0; i < n; i++) {
                sem[i] = new semester();
                System.out.println("enter the semester" + (i + 1));
                int s = Integer.parseInt(obj.readLine());
                sem_array[i] = s;
                sem[i].sem(s, flag);
            }
            flag = 1;
            int index, s;
            System.out.println("\n to print the timetable");
            for (int i = 0; i < n; i++) {
                index = 5;
                while (true)//loop till right semester is given as input
                {
                    System.out.println("enter the semester");
                    s = Integer.parseInt(obj.readLine());
                    for (int j = 0; j < n; j++)//for picking the approiate sections object
                    {
                        if (sem_array[j] == s) {

                            index = j;
                            break;
                        }

                    }
                    if (index == 5) {
                        System.out.println("sorry no such semester entered as input");
                    } else {
                        break;
                    }

                }
                  
                sem[index].sem(s, flag);
            }

        }
        else
        {
            System.out.println("enter the semester");
            int sem = Integer.parseInt(obj.readLine());
            System.out.println("enter the section");
            char sect=(char)obj.read();
            String fname=Integer.toString(sem)+sect+"se.txt";
            File fp=new File(fname);
            boolean status = fp.exists();
          
        }
    }
}
