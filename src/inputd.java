/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Noorul Ameen
 */
public class inputd extends lab implements Serializable {

    public lab[] l=new lab[4];
    public int[][] prty = new int[6][7];

    public subject[] s = new subject[10];
    int flag_texist = 0;//flag for teacher exist
    //int sem;
    @SuppressWarnings("empty-statement")
    sub_input_read objsir = new sub_input_read();
    private int n;

    int inputsd(int sem, int nos) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);

        /*   for(int i=0;i<nos;i++)
         {
           
         s[i]=new subject();
         System.out.println("enter "+(i+1)+" th"+"subject details");
         System.out.println("enter subject code");
         s[i].subcode=scanner.nextLine();
         System.out.println("enter subject name");
         s[i].s_name=scanner.nextLine();
         System.out.println("enter teachers name");
         s[i].t_name=scanner.nextLine();
         s[i].load();
         System.out.println("enter number of classes per week");
         s[i].noc=Integer.parseInt(obj.readLine());
         }*/
        nos = objsir.subread(sem, s);
        //System.out.println("enter the teachers and number of classes per week");
        for (int i = 0; i < nos; i++) {
            //   System.out.println((i+1)+":"+s[i].s_name+"\t");
            // s[i].t_name=obj.readLine();
            s[i].load();
             //s[i].noc=Integer.parseInt(obj.readLine());

        }
        return nos;

    }

    void pread(int nos) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("are ther any priorities\n1:yes\t2:no");
        int pf = Integer.parseInt(obj.readLine());
        // obj.skip(1);//to skip enter key
        if (pf == 1) {
            System.out.println("enter the priorities");
            while (true) {
                System.out.println("enter the day\n1:monday2:tuesday......");
                int d = Integer.parseInt(obj.readLine());
                d = d - 1;
                System.out.println("enter the subject");
                for (int i = 0; i < nos; i++) {
                    System.out.println((i + 1) + ":" + s[i].s_name + "\t" + s[i].subcode);
                }
                int sub;
                sub = Integer.parseInt(obj.readLine());
                sub = sub - 1;//array [0 to n-1] user enetrs 1 for 0
                System.out.println("enter the period");
                int p = Integer.parseInt(obj.readLine());
                p = p - 1;//1st period in array would be 0
                if (prty[d][p] == -1) {
                    prty[d][p] = sub;
                    s[sub].sc++;
                    s[sub].teach[d][p] = 2;
                } else {
                    if (prty[d][p] < nos) {
                        System.out.println("this period is already occupied with" + s[prty[d][p]].s_name + "\t" + s[prty[d][p]].subcode);
                        System.out.println("do you want yo replace it : y/n");
                        char ch = (char) obj.read();
                        obj.skip(1);
                        if (ch == 'y') {
                            prty[d][p] = sub;
                            s[sub].teach[d][p] = 2;
                            s[sub].sc++;
                        }
                    } else {
                        System.out.println("this is already occupied with lab" + l[prty[d][p] - nos].l_name);
                        System.out.println("sorry cannot replace");
                    }

                }
                System.out.println("are there any more priorities?(y/n)");
                char ch = (char) obj.read();
                if (ch == 'n') {
                    break;
                }
                obj.skip(1);//to skip enter key
            }
        }

    }

    void inputld(int sem, char sect) throws IOException {

        System.out.println("enter the number of labs for this sem");
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        int nol = Integer.parseInt(obj.readLine());
        //lab[] l;
        // obj.skip(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                prty[i][j] = -1;
            }
        }
        l = new lab[nol];
           
        for (int i = 0; i < nol; i++) {
            l[i] = new lab();
            l[i].l_teach=new String[1];
            //l[i].l_teach[0]=new String();
            l[i].l_teach[0] = new String();
            System.out.println("enter " + (i + 1) + "th" + " labdetails");
            System.out.println("enetr the labcode");
            l[i].l_code = obj.readLine();
            System.out.println("enter the lab name");
            l[i].l_name = obj.readLine();

            
            // objsir.lab_read(sem, l);
            System.out.println("enter the teacher handling the lab ");
            l[i].l_teach[0] = obj.readLine();

        }
        System.out.println("enter then number of labs per week");
        n = Integer.parseInt(obj.readLine());
        for (int j = 0; j < n; j++) {

            System.out.println("eneter the lap priority");
            System.out.println("enter the day");
            System.out.println("1:monday\t2:tuesday.....");
            int lday = Integer.parseInt(obj.readLine());
            //obj.skip(1);
            lday -= 1;
            System.out.println("enter the starting period");
            int sper = Integer.parseInt(obj.readLine());
            sper -= 1;
            for (int i = 0; i < nol; i++) {
                l[i].day = lday;
                l[i].s_per = sper;
//                l[i].read_lt(sem, sect, l[i].l_teach[0],lday,sper);
                //    l[i].upd_lp(j, prty, s);     
            }
            prty[lday][sper] = 99;
            prty[lday][sper + 1] = 99;
            prty[lday][sper + 2] = 99;

        }

    }

}
