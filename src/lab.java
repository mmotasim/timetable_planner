/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Noorul Ameen
 */
public class lab implements Serializable {

    public String l_code, l_name;
    public String[] l_teach = new String[1];
    int not;//remember
    public int nol;
    int day, s_per;
    public boolean ftc = false;//flag for teacher occupied
    

    lab() {

    }

    void read_lt(int sem, String sect, String tname, int lday, int sper,String lcode) throws IOException {
        /*  System.out.println("eneter the number of teachers");
         BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
         not=Integer.parseInt(obj.readLine());
         System.out.println("the number of teachers are "+not);

      
      
         System.out.println("enter the teacher's name handling this lab and a subject");
         for(int i=0;i<not;i++)//runs only once since only 1 teachewr is considered*/

        {
            FileReader f1;
            f1 = null;
            FileWriter f2;
            f2 = null;
            ftc=false;
            String fname ="generate data\\"+tname + ".txt";
            File fp = new File(fname);
            Boolean status = fp.exists();
            if (status) {
                f1 = new FileReader(fp);
                BufferedReader fr = new BufferedReader(f1);

                String s = null;

                String[][] temp = new String[6][7];
                int c = -1;
                while ((s = fr.readLine()) != null) {
                    c++;
                    String[] l = s.split(" ");

                    for (int j = 0; j < 7; j++) {
                        //       temp[c][j+1]=new String();
                        temp[c][j] = l[j];
                        if (c == lday && j == sper) {
                            if (l[j].equals("0") && l[j + 1].equals("0") && l[j + 2].equals("0")) {
                                temp[c][j] = Integer.toString(sem) + sect + "-" + lcode;
                                temp[c][++j] = Integer.toString(sem) + sect + "-" + lcode;
                                temp[c][++j] = Integer.toString(sem) + sect + "-" + lcode;

                            } else {
                                
                                JOptionPane.showMessageDialog(null, tname + " already occupied, cannot handle the requested LAB");
                                ftc = true;
                                return;
                            }
                        }

                    }
                }
                if (!ftc) {
                    fr.close();
                    f2 = new FileWriter(fp);
                    BufferedWriter fw = new BufferedWriter(f2);
                    for (int j = 0; j < 6; j++) {
                        for (int k = 0; k < 7; k++) {
                            fw.write(temp[j][k]);
                            fw.write(" ");
                        }
                        fw.write("\r\n");
                    }
                    fw.close();
                }
            } else {
                f2 = new FileWriter(fp);
                BufferedWriter fw = new BufferedWriter(f2);
                for (int j = 0; j < 6; j++) {
                    for (int k = 0; k < 7; k++) {
                        if (j == lday && (k  == sper)) {
                            for (int t = 1; t <= 3; t++) {
                                fw.write(Integer.toString(sem) + sect + "-" + lcode);
                                fw.write(" ");
                                k++;
                            }
                            k--;
                        } else {
                            fw.write("0 ");
                        }
                    }
                    fw.write("\r\n");
                }
                fw.close();
            }

        }
    }

    void lab_restor(int sem, String sect, String tname, int lday, int sper,String lcode) throws FileNotFoundException, IOException {
        FileReader f1;
        f1 = null;
        FileWriter f2;
        f2 = null;
        String fname = "generate data\\"+tname + ".txt";
        File fp = new File(fname);
        ftc=false;
        Boolean status = fp.exists();
        if (status) {
            f1 = new FileReader(fp);
            BufferedReader fr = new BufferedReader(f1);

            String s = null;

            String[][] temp = new String[6][7];
            int c = -1;
            while ((s = fr.readLine()) != null) {
                c++;
                String[] l = s.split(" ");

                for (int j = 0; j < 7; j++) {
                    //       temp[c][j+1]=new String();
                    temp[c][j] = l[j];
                    if (c == lday && j == sper) {//check again is it j or j+1
                        if (l[j].equals(Integer.toString(sem) + sect + "-" + lcode) && l[j + 1].equals(Integer.toString(sem) + sect + "-" + lcode)
                                && l[j + 2].equals(Integer.toString(sem) + sect + "-" + lcode)) {
                            temp[c][j] = "0";
                            temp[c][++j] = "0";
                            temp[c][++j] = "0";

                        }

                    }

                }

            }
            fr.close();
            f2 = new FileWriter(fp);
            BufferedWriter fw = new BufferedWriter(f2);
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 7; k++) {
                    fw.write(temp[j][k]);
                    fw.write(" ");
                }
                fw.write("\r\n");
            }
            fw.close();

        }

    }

    //inputd iobj=new inputd();

    void upd_lp(int i, int prty[][], subject s[]) {
        prty[day][s_per - 1] = 6 + i;
        prty[day][s_per] = 6 + i;
        prty[day][s_per + 1] = 6 + i;

        /*     for(int j=0;j<nos;j++)
         {
         if(l_teach[0]==s[j].t_name)
         {
         s[j].teach[day][s_per-1]=1;
         s[j].teach[day][s_per]=1;
         s[j].teach[day][s_per+1]=1;
         break;
         }
         }*/
    }
}
