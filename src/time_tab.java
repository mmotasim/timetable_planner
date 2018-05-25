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
 * @author AFZAAL
 */
public class time_tab implements Serializable {

    int psc;
    int[] day;
    int sub;
    int sub_array[];
    RandomNumberGenerator rday;
    RandomNumberGenerator rsub;
    placeable p;
    private int c;
    private int flag;
    transient BufferedReader obj;
    private int st;
    private int end;
    /* void fill()
     {
     for(int i=0;i<5;i++)
     {
     for(int subj=0;subj<inputd.nos;subj++)
     {
     if(p.place(subj, i, 6))
     {
     section.tt[i][6]=subj;
     inputd.s[subj].sc++;
     inputd.s[subj].teach[i][6]=2;
     section.tsc++;
     if(complete())
     print();
     }
     }
     }
     }*/

    void set_time_tab() {
        psc = 0;
        rday = new RandomNumberGenerator();
        rsub = new RandomNumberGenerator();
        p = new placeable();
        flag = 1;
        sub_array = new int[6];
        rday.randomgenerator(6);
        rsub.randomgenerator(6);
        day = new int[6];
        obj = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 6; i++)//no. of days and subjects are six (vtu specific "not generic")
        {
            day[i] = rday.pRandom(i);
            sub_array[i] = rsub.pRandom(i);
        }
    }
   void display(int tt[][])
    {
         for (int j = 0; j < 6; j++) {
            for (int k = 0; k < 7; k++) {
                
                System.out.print(tt[j][k]+" ");
            }
               
            System.out.println();
            
            
        }
         System.out.println("________________________________________________________");
         System.out.println();
    }
    boolean complete(subject s[],int nos) {

        for (int i = 0; i < nos; i++) {
            if (s[i].sc != s[i].noc) {
                return false;
            }
        }
        return true;

    }

    void timetable(int tt_gen_count,int d, int di, int period, int pesc, subject s[], int tt[][],int nos) throws IOException {
        if (period <= 3) {
            if (pesc == 6) {
                period = period + 1;
                pesc = take_psc(period, tt);
                di = 0;
                //  print();
            }

        } else {
            if (pesc == 5) {
                period = period + 1;
                pesc = take_psc(period, tt);
                di = 0;
                //print();
            }

        }

        d = day[di];

        if (tt[d][period] == -1) {
            for (int subjct = 0; subjct < nos; subjct++) {
                int subj = sub_array[subjct];
                if (p.place(tt_gen_count,subj, d, period, s, tt)) {
                    tt[d][period] = subj;
                    s[subj].sc++;
                    s[subj].teach[d][period] = 2;
                    pesc++;
                    //section.tsc++;
                    //display(tt);
                    if (complete(s,nos)) {

                   //end = period;
                        //st = d;
                        print(s, tt,nos);
                    } else {
                        timetable(tt_gen_count,d, (di == 5) ? 0 : di + 1, period, pesc, s, tt,nos);
                        if (flag == 1)//once printed return back***
                        {
                            s[subj].sc--;//restores when backtracking
                            tt[d][period] = -1;
                            s[subj].teach[d][period] = 0;
                            pesc--;
                            //  section.tsc--;
                        }

                    }

                }
            }
        } else {
            timetable(tt_gen_count,d, (di == 5) ? 0 : di + 1, period, pesc, s, tt,nos);
        }
    }

    private void print(subject s[], int tt[][],int nos) throws IOException {
        c++;
        for (int j = 0; j < 6; j++) {
            for (int k = 0; k < 7; k++) {
                if (tt[j][k] != -1) {
                    if (tt[j][k] <nos) {
                        System.out.print(s[tt[j][k]].sub_sf + "\t|");
                    } else {
                        System.out.print("lab\t|");
                    }
                } else {
                    System.out.print("---");
                }

            }
            System.out.println();
            System.out.println("________________________________________________________");
        }
        flag = 0;
          //System.out.println("\n do you want one more timetable\t 1:yes 2:no done");
        //flag=Integer.parseInt(obj.readLine());

    }

    int psc_0(int tt[][]) {
        for (int d = 0; d < 6; d++) {
            if (tt[d][0] != -1) {
                psc++;
            }
        }
        return psc;
    }

    private int take_psc(int period, int tt[][]) {
        psc = 0;
        for (int d = 0; d < 6; d++) {
            if (tt[d][period] != -1) {
                psc++;
            }
        }
        return psc;
    }
}
