/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 *
 * @author Noorul Ameen
 */
public class semester implements Serializable {

    section[] sec;
    int n;
    String[] sect_array;
    char sect1;
    private int index;
    BufferedReader obj;

    void sem(int s, int flag) throws IOException, ClassNotFoundException {

        if (flag == 0) {
            obj = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("enter the number of sections");
            n = Integer.parseInt(obj.readLine());
            sec = new section[n];
            sect_array = new String[n];
            for (int i = 0; i < n; i++) {
                System.out.println("enter the section" + (i + 1) + " of semester" + s);
                sect_array[i] =  obj.readLine();
                obj.skip(1);//to skip enter
                sec[i] = new section();
                sec[i].section_tt(sect1, s, flag);//not sect
            }

        } else {
            for (int i = 0; i < n; i++) {
                index = 5;
                while (true) {
                    System.out.println("enter the section of semester" + s);
                    String sect =  obj.readLine();
                    obj.skip(1);//to skip enter
                    for (int j = 0; j < n; j++) {
                        if (sect_array[j] == sect) {
                            index = j;
                            break;
                        }
                    }
                    if (index == 5) {
                        System.out.println("no such section entered as input");
                    } else {
                        break;
                    }
                }
                sec[index].section_tt(sect1, s, flag);
            }
        }

    }

}
