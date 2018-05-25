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
public class section extends inputd implements Serializable {

    char section;
    public int nos;
    public int[][] tt = new int[6][7];
    time_tab ttobj;
    int i, j;int nol_pw=-1 ;
    String [][] sp_day;
    String [][] priority_buff;
    int [][] sp_day_p;
     int count_nol_pw  ,count_no_priority=0;     
    boolean doneflag=false//done flag for subject 
            ,lab_save=false,labdone=false,subdone=false,prioritydone=false,generatedone=false,modifysub=false,dusted=false;
    filereader objfp;
   
     transient BufferedReader obj;
    private int ch;
    public int[] tempsc=new int[10];
    private int[][][] temp_teach;
    public int[][] temp_prty;

    void section_tt(char sect, int sem, int flag) throws IOException, ClassNotFoundException {
      //  inputd obji;
        //obji = new inputd();

        if (flag == 0) {
            tempsc = new int[6];
            temp_teach = new int[6][6][7];
         //   temp_tt = new int[6][7];
            objfp = new filereader();
            ttobj = new time_tab();
          
            obj = new BufferedReader(new InputStreamReader(System.in));
            inputld(sem,sect);
            nos=inputsd(sem,nos);

            pread(nos);
            for (i = 0; i < 6; i++) {
                for (j = 0; j < 7; j++) {
                    tt[i][j] = prty[i][j];
                }
            }
            for (i = 0; i < 6; i++) {
                for (j = 0; j < 7; j++) {
                    System.out.print(tt[i][j] + "\t");
                }
                System.out.println();
            }
            for (i = 0; i <nos; i++) {
                objfp.f_temp_load(s[i].t_name);
//                objfp.fstore(s[i].t_name, i, s,sem,sect);
            }

        } else {
            for (i = 0; i < nos; i++) {
                objfp.fload_2(s[i].teach, s[i].t_name);

            }
            for (i = 0; i <nos; i++) {

                tempsc[i] = s[i].sc;
            }
            for (i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    for (int k = 0; k < 7; k++) {
                        temp_teach[i][j][k] = s[i].teach[j][k];
                      //  temp_tt[j][k] = tt[j][k];
                    }
                }
            }

            ttobj.set_time_tab();
            int psc = 0;
            psc = ttobj.psc_0(tt);

            System.out.println("about to generated");
          //  ttobj.timetable(0, 0, 0, psc, s, tt,nos);
            section tempobj = new section();
            do {
                System.out.println("\n do you want one more timetable\t 1:yes 2:no done");
                ch = Integer.parseInt(obj.readLine());
                if (ch == 1) {
                    for (i = 0; i < 6; i++) {
                        for (int j = 0; j < 6; j++) {
                            for (int k = 0; k < 7; k++) {
                                s[i].teach[j][k] = temp_teach[i][j][k];
//                                tt[j][k] = temp_tt[j][k];
                            }
                        }
                    }

                    for (i = 0; i <nos; i++) {

                        s[i].sc = tempsc[i];
                    }
                    ttobj.set_time_tab();
                    psc = ttobj.psc_0(tt);
            //        ttobj.timetable(0, 0, 0, psc, s, tt,nos);
                }

            } while (ch == 1);

            System.out.println(" generated");

            System.out.println("the subject counts are\n");
            for (i = 0; i <nos; i++) {
                System.out.println(s[i].sc);
            }
            for (i = 0; i <nos; i++) {
                objfp.f_temp_load(s[i].t_name);
//                objfp.fstore(s[i].t_name, i, s,sem,sect);

            }
           
            /* String semes=Integer.toString(sem);
             objm.serialize(sect,semes,this);//try putting appropriare secton for this
                
             */
            objfp.tt_store(tt, sem, sect, this, nos);
             

        }

    }
}
