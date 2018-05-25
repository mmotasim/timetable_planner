/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Noorul Ameen
 */
public class sub_input_read implements Serializable{

    int subread(int sem, subject s[]) throws FileNotFoundException, IOException {
        File fp = new File("subjects");
        String path = fp.getAbsolutePath();
        String fpath = path + "\\" + sem + "sem.txt";
        BufferedReader fr = new BufferedReader(new FileReader(new File(fpath)));
        String st;
        int c = -1;

        while ((st = fr.readLine()) != null) {
            c++;
            s[c] = new subject();
            String[] temp = st.split("-");
            s[c].s_name = temp[0];
            s[c].subcode = temp[1];
            s[c].sub_sf = temp[2];
            s[c].t_name = temp[3];
            s[c].noc = Integer.parseInt(temp[4]);
        }
        fr.close();
        return c + 1;

    }

    void lab_read(int sem, lab l[]) throws IOException {

        File fp = new File("subjects");
        String path = fp.getAbsolutePath();
        String fpath = path + "\\" + sem + "semlab.txt";
        BufferedReader fr = new BufferedReader(new FileReader(new File(fpath)));
        String st;
        int c = -1;

        while ((st = fr.readLine()) != null) {
            c++;
            l[c] = new lab();
            String[] temp = st.split("-");
            l[c].l_code = temp[0];
            l[c].l_name = temp[1];
            l[c].not = Integer.parseInt(temp[2]);
            l[c].l_teach[0] = temp[3];

        }
        fr.close();

    }
}
