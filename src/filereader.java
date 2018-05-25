 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author AFZAAL
 */
public class filereader implements Serializable {

    String[][] temp = new String[6][7];
    String[][] sublist= new String[15][5];
    int subcount=0;
    void fload(String fnam, int[][] teach) throws IOException {
        FileReader f1;
        f1 = null;
        String fname = "generate data\\"+fnam + ".txt";
        File f = new File(fname);
        Boolean status = f.exists();
        if (status == true) {
            f1 = new FileReader(f);
            BufferedReader fp;
            fp = new BufferedReader(f1);
            String s;
            s = null;
            int c = -1;
            while ((s = fp.readLine()) != null) {
                c++;
                String[] l = s.split(" ");
                for (int i = 0; i < 7; i++) {
                    if (!l[i].equals("0")) {
                        if (l[i].contains("l")||l[i].contains("L"))//every lab code has letter 'L' in it
                        {
                            teach[c][i] = 3;//represents lab subject period
                        } else {
                            teach[c][i] = 1;//if it is a normal subject
                        }
                    } else {
                        teach[c][i] = 0;
                    }
                }
            }
            fp.close();
        } else {
            FileWriter fp;
            fp = new FileWriter(fname);
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    fp.write("0 ");
                    teach[i][j] = 0;

                }
                fp.write("\r\n");
            }
            fp.close();
        }
    }
    void update_temparray(String tname,int day,int period,String subcode,int sem,String sec) throws IOException
    {
        
        if(!"0".equals(temp[day][period]))
        {
            JOptionPane.showMessageDialog(null, "sorry"+tname+"already ocuupied on requested day and period.");
            
            
        }
        else
        {
            temp[day][period]=Integer.toString(sem)+sec+"-"+subcode;
           update_teachfile(tname);
            
        }
        
        
    }
    void update_tempload(String tname) throws FileNotFoundException, IOException// to load the file onto a temp array
    {
          String fname, s;
        fname = "generate data\\"+tname + ".txt";
        BufferedReader fp = new BufferedReader(new  FileReader(new File(fname)));
        int c = -1;
        while ((s = fp.readLine()) != null) {
            c++;
            String[] l = s.split(" ");
            for (int i = 0; i < 7; i++) {
                temp[c][i] = l[i];
            }
        }
        fp.close();
    }
    
    void update_teachfile(String tname) throws IOException
    {
         BufferedWriter fp = new BufferedWriter(new FileWriter(new File("generate data\\"+tname+".txt")));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                    fp.write(temp[i][j]+" "); 
            }
            fp.write("\r\n");
        }
        fp.close();

    }

    void fload_2(int[][] teach, String fnam) throws FileNotFoundException, IOException {
        FileReader f1;
        f1 = null;
        String fname = "generate data\\"+fnam + ".txt";
        File f = new File(fname);
        f1 = new FileReader(f);
        BufferedReader fp;
        fp = new BufferedReader(f1);
        String s;
        s = null;
        int c = -1;
        while ((s = fp.readLine()) != null) {
            c++;
            String[] l = s.split(" ");
            for (int i = 0; i < 7; i++) {
                if (teach[c][i] != 2) {
                    if (!l[i].equals("0")) {
                        if (l[i].contains("l"))//to indicate lab in teach array
                        {
                            teach[c][i] = 3;
                        } else {
                            teach[c][i] = 1;//if it is a normal subject
                        }
                    } else {
                        teach[c][i] = 0;
                    }
                }

            }
        }
        fp.close();

    }

    void fstore(String fnam, int teach[][], int sem, String sect,String subcode) throws IOException {

        String fname, st;

        fname = "generate data\\"+fnam + ".txt";
        BufferedWriter fp = new BufferedWriter(new FileWriter(new File(fname)));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (teach[i][j] == 2) {
                    st = subcode;
                    fp.write(Integer.toString(sem) + sect + "-" + st + " ");
                } else {
                    st = temp[i][j];
                    fp.write(st + " ");
                }

            }
            fp.write("\r\n");

        }
        fp.close();
    }

    void f_temp_load(String fnam) throws FileNotFoundException, IOException {// to load subjects from the file(dont touch)

        String fname, s;
        fname = "subjects\\"+fnam + "sem.txt";
        BufferedReader fp = new BufferedReader(new FileReader(new File(fname)));
        int c = -1;
        while ((s = fp.readLine()) != null) {
            c++;
            String[] l = s.split("-");
            for (int i = 0; i < 3; i++) {
                sublist[c][i] = l[i];
            }
        }
        subcount=c+1;
        fp.close();

    }
    
    void updt_prteach(String fname, int sem, char sect) throws IOException {
        BufferedWriter fp = new BufferedWriter(new FileWriter(new File(fname)));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (temp[i][j].contains(Integer.toString(sem) + sect)) {
                    fp.write("0 ");// since teacher is removed remove those classes from her file 
                } else {
                    fp.write(temp[i][j] + " ");
                }
            }
            fp.write("\r\n");

        }
        fp.close();

    }

    void store_prteach(String fname, int[][] teach, int sem, char sec) throws IOException {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (temp[i][j] != "0") {

                        if (temp[i][j].contains("l")) {
                            teach[i][j] = 6;//to indicate labs that new teach is allocated
                        } 
                            teach[i][j] = 1;
                } else {
                    teach[i][j] = 0;
                }

            }
        }
    }
void tt_store(int[][] tt, int sem, char sec, section sect, int nos) throws IOException {
        String fname, st;

        fname = Integer.toString(sem) + sec + "time_table.txt";
        BufferedWriter fp = new BufferedWriter(new FileWriter(new File(fname)));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (tt[i][j] < nos && tt[i][j] != -1) {
                    fp.write(sect.s[tt[i][j]].sub_sf + " ");
                } else if (tt[i][j] == -1) {
                    fp.write("-- ");
                } else {
                    fp.write("lab ");
                }
            }
            fp.write("\r\n");

        }
        fp.close();

    }
void modified_tt_store(String fnam,section sec) throws IOException {
        String fname, st;

        fname = fnam+"_modified.txt";
        BufferedWriter fp = new BufferedWriter(new FileWriter(new File(fname)));
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (sec.tt[i][j] < sec.nos && sec.tt[i][j] != -1) {
                    int sub=sec.tt[i][j];
                    fp.write(sec.s[sub].sub_sf + " ");
                } else if (sec.tt[i][j] == -1) {
                    fp.write("-- ");
                } else {
                    fp.write("lab ");
                }
            }
            fp.write("\r\n");

        }
        fp.close();
        }
       
void fetch_subjectlist(String[] subj,String sem)
{
    String fname=sem+".txt";
    
    
}
void initialize_directories()
{
    File fp= new File("generate data");
    File fp2=new File("teacher files");
    File fp3=new File("timetable");
    
    if(fp.exists())
    {
       String[] entries1=fp.list();
    String[] entries2=fp2.list();
    String[] entries3=fp3.list();
    
    for(String s:entries1)
    {
        File currentfile=new File(fp.getName()+"\\"+s);
        currentfile.delete();
    }
    for(String s:entries2)
    {
        File currentfile=new File(fp2.getName()+"\\"+s);
        currentfile.delete();
    }
    for(String s:entries3)
    {
        File currentfile=new File(fp3.getName()+"\\"+s);
        currentfile.delete();
    }
    fp.delete();
    fp2.delete();
    fp3.delete();
    
    }
    
        fp.mkdir();
        fp2.mkdir();
        fp3.mkdir();
    
    
}
    }

    


