/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Noorul Ameen
 */
public class subject implements Serializable {

    public String subcode = null, t_name = null, s_name = null;
    int noc, sc;
    int[][] teach;
    String sub_sf;

    subject() {
        sc = 0;//subject count
        noc = 0;//no. of classes per week
        teach = new int[6][7];//teachers details
        subcode = new String();
        t_name = new String();
        s_name = new String();
        sub_sf = new String();
    }

    void load() throws IOException {
        filereader f = new filereader();
        f.fload(t_name, teach);

    }

    void store() {

    }

}
