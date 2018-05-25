
import com.sun.glass.events.KeyEvent;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author AFZAAL
 */
public class main_jf extends javax.swing.JFrame {

    /**
     * Creates new form main_jf
     */
    int no_sem;
    int[] sem_array;
    String prev_btcn;

    semester[] sem;
    boolean flag_sem_details;
    String priority_lock = null;

    filereader fobj;
    time_tab ttobj;
    String sec_btn;//represents currently active section 
    String sem_btn;//represents currently active semester
    int sec_i;//sections index
    int sem_i;//semesters index
    private String teach_name;
    Color bg;
    JButton b;
    int prev_sem_i = 1;
    int prev_sec_i = 1;
    String temp_tt[][] = new String[6][9];
    String temp_teach_f[][] = new String[6][9];
    private genrate_doc docobj;
    private int teacher_select_flag = 1;
    DefaultTableModel dm;
    private String current_block_teacher = "";

    public main_jf() {

        initComponents();
        Dimension scsz = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, scsz.width, scsz.height);
    }

    void change_bg() {
        b.setBackground(Color.CYAN);
    }

    void change_bgn() {
        b.setBackground(bg);
    }

    void upd_prty() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                sem[sem_i - 1].sec[sec_i - 1].prty[i][j] = -1;
            }
        }
        int i, j;
        for (int l = 0; l < sem[sem_i - 1].sec[sec_i - 1].nol_pw; l++) {
            i = sem[sem_i - 1].sec[sec_i - 1].sp_day_p[l][0];
            j = sem[sem_i - 1].sec[sec_i - 1].sp_day_p[l][1];
            sem[sem_i - 1].sec[sec_i - 1].prty[i][j] = 99;
            sem[sem_i - 1].sec[sec_i - 1].prty[i][j + 1] = 99;
            sem[sem_i - 1].sec[sec_i - 1].prty[i][j + 2] = 99;

        }

    }

    void update_sub_priority(int index, int day, int period) {
        get_semnsec();
        sem[sem_i - 1].sec[sec_i - 1].s[index].teach[day][period] = 1;
        sem[sem_i - 1].sec[sec_i - 1].prty[day][period] = index;
        sem[sem_i - 1].sec[sec_i - 1].s[index].sc++;
    }

    void filecreate_presc() {
        for (int i = 0; i < 6; i++) {
            try {
                fobj.fload(sem[sem_i - 1].sec[sec_i - 1].s[i].t_name, sem[sem_i - 1].sec[sec_i - 1].s[i].teach);
            } catch (IOException ex) {

            }
        }
    }

    void save_prev() {

        try {
            if (prev_btcn == "sp1_b1") {
                sem_array[0] = Integer.parseInt(tf_semname.getText());
                sem[0].n = Integer.parseInt(tf_nosec.getText());

            } else if (prev_btcn == "sp1_b2") {
                sem_array[1] = Integer.parseInt(tf_semname.getText());
                sem[1].n = Integer.parseInt(tf_nosec.getText());
            } else if (prev_btcn == "sp1_b3") {
                sem_array[2] = Integer.parseInt(tf_semname.getText());
                sem[2].n = Integer.parseInt(tf_nosec.getText());
            } else if (prev_btcn == "sp1_b4") {
                sem_array[3] = Integer.parseInt(tf_semname.getText());
                sem[3].n = Integer.parseInt(tf_nosec.getText());
            } else if (prev_btcn == "sp1_b5") {
                sem_array[4] = Integer.parseInt(tf_semname.getText());
                sem[4].n = Integer.parseInt(tf_nosec.getText());
            } else if (prev_btcn == "sp1_b6") {
                sem_array[5] = Integer.parseInt(tf_semname.getText());
                sem[5].n = Integer.parseInt(tf_nosec.getText());
            } else if (prev_btcn == "sp1_b7") {
                sem_array[6] = Integer.parseInt(tf_semname.getText());
                sem[6].n = Integer.parseInt(tf_nosec.getText());
            } else if (prev_btcn == "sp1_b8") {
                sem_array[7] = Integer.parseInt(tf_semname.getText());
                sem[7].n = Integer.parseInt(tf_nosec.getText());
            }
        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(null, "please enter valid details expecting numeric data.");
        }
        jp3_parent.setVisible(true);
    }

    void get_semnsec()//retturns the current semester and section into sem index(sem_i) and sec index(sec_i)
    {
        sem_i = Integer.parseInt(sem_btn.substring(5));//index of sem array whose details have to be stored
        sec_i = Integer.parseInt(sec_btn.substring(6));//index of section whose sec name have to be stored
    }

    void get_prev_semnsec()//retturns the previous semester and section into sem index(sem_i) and sec index(sec_i)
    {
        prev_sem_i = Integer.parseInt(sem_btn.substring(5));
        prev_sec_i = Integer.parseInt(sec_btn.substring(6));
    }

    void change_background(int i) {
        if (i == 1) {
            sp1_b1.setBackground(Color.magenta);
            sp1_b2.setBackground(bg);
            sp1_b3.setBackground(bg);
            sp1_b4.setBackground(bg);
            sp1_b5.setBackground(bg);
            sp1_b6.setBackground(bg);
            sp1_b7.setBackground(bg);
            sp1_b8.setBackground(bg);
        } else if (i == 2) {
            sp1_b2.setBackground(Color.magenta);
            sp1_b1.setBackground(bg);
            sp1_b3.setBackground(bg);
            sp1_b4.setBackground(bg);
            sp1_b5.setBackground(bg);
            sp1_b6.setBackground(bg);
            sp1_b7.setBackground(bg);
            sp1_b8.setBackground(bg);
        } else if (i == 3) {
            sp1_b3.setBackground(Color.magenta);
            sp1_b2.setBackground(bg);
            sp1_b1.setBackground(bg);
            sp1_b4.setBackground(bg);
            sp1_b5.setBackground(bg);
            sp1_b6.setBackground(bg);
            sp1_b7.setBackground(bg);
            sp1_b8.setBackground(bg);
        } else if (i == 4) {
            sp1_b4.setBackground(Color.magenta);
            sp1_b2.setBackground(bg);
            sp1_b3.setBackground(bg);
            sp1_b1.setBackground(bg);
            sp1_b5.setBackground(bg);
            sp1_b6.setBackground(bg);
            sp1_b7.setBackground(bg);
            sp1_b8.setBackground(bg);
        } else if (i == 5) {
            sp1_b5.setBackground(Color.magenta);
            sp1_b2.setBackground(bg);
            sp1_b3.setBackground(bg);
            sp1_b4.setBackground(bg);
            sp1_b1.setBackground(bg);
            sp1_b6.setBackground(bg);
            sp1_b7.setBackground(bg);
            sp1_b8.setBackground(bg);
        } else if (i == 6) {
            sp1_b6.setBackground(Color.magenta);
            sp1_b2.setBackground(bg);
            sp1_b3.setBackground(bg);
            sp1_b4.setBackground(bg);
            sp1_b5.setBackground(bg);
            sp1_b1.setBackground(bg);
            sp1_b7.setBackground(bg);
            sp1_b8.setBackground(bg);
        } else if (i == 7) {
            sp1_b7.setBackground(Color.magenta);
            sp1_b2.setBackground(bg);
            sp1_b3.setBackground(bg);
            sp1_b4.setBackground(bg);
            sp1_b5.setBackground(bg);
            sp1_b6.setBackground(bg);
            sp1_b1.setBackground(bg);
            sp1_b8.setBackground(bg);
        } else {
            sp1_b8.setBackground(Color.magenta);
            sp1_b2.setBackground(bg);
            sp1_b3.setBackground(bg);
            sp1_b4.setBackground(bg);
            sp1_b5.setBackground(bg);
            sp1_b6.setBackground(bg);
            sp1_b7.setBackground(bg);
            sp1_b1.setBackground(bg);
        }
    }

    void def_sec_bg() {
        jp3_sb1.setBackground(bg);
        jp3_sb2.setBackground(bg);
        jp3_sb3.setBackground(bg);
        jp3_sb4.setBackground(bg);
        jp3_sb5.setBackground(bg);
        jp3_sb6.setBackground(bg);
        jp3_sb7.setBackground(bg);
        jp3_sb8.setBackground(bg);
    }

    void def_sem_bg() {
        sp1_b8.setBackground(bg);
        sp1_b2.setBackground(bg);
        sp1_b3.setBackground(bg);
        sp1_b4.setBackground(bg);
        sp1_b5.setBackground(bg);
        sp1_b6.setBackground(bg);
        sp1_b7.setBackground(bg);
        sp1_b1.setBackground(bg);
    }

    void change_backgroundsec(int i) {
        if (i == 1) {
            jp3_sb1.setBackground(Color.magenta);
            jp3_sb2.setBackground(bg);
            jp3_sb3.setBackground(bg);
            jp3_sb4.setBackground(bg);
            jp3_sb5.setBackground(bg);
            jp3_sb6.setBackground(bg);
            jp3_sb7.setBackground(bg);
            jp3_sb8.setBackground(bg);
        } else if (i == 2) {
            jp3_sb2.setBackground(Color.magenta);
            jp3_sb1.setBackground(bg);
            jp3_sb3.setBackground(bg);
            jp3_sb4.setBackground(bg);
            jp3_sb5.setBackground(bg);
            jp3_sb6.setBackground(bg);
            jp3_sb7.setBackground(bg);
            jp3_sb8.setBackground(bg);
        } else if (i == 3) {
            jp3_sb3.setBackground(Color.magenta);
            jp3_sb2.setBackground(bg);
            jp3_sb1.setBackground(bg);
            jp3_sb4.setBackground(bg);
            jp3_sb5.setBackground(bg);
            jp3_sb6.setBackground(bg);
            jp3_sb7.setBackground(bg);
            jp3_sb8.setBackground(bg);
        } else if (i == 4) {
            jp3_sb4.setBackground(Color.magenta);
            jp3_sb2.setBackground(bg);
            jp3_sb3.setBackground(bg);
            jp3_sb1.setBackground(bg);
            jp3_sb5.setBackground(bg);
            jp3_sb6.setBackground(bg);
            jp3_sb7.setBackground(bg);
            jp3_sb8.setBackground(bg);
        } else if (i == 5) {
            jp3_sb5.setBackground(Color.magenta);
            jp3_sb2.setBackground(bg);
            jp3_sb3.setBackground(bg);
            jp3_sb4.setBackground(bg);
            jp3_sb1.setBackground(bg);
            jp3_sb6.setBackground(bg);
            jp3_sb7.setBackground(bg);
            jp3_sb8.setBackground(bg);
        } else if (i == 6) {
            jp3_sb6.setBackground(Color.magenta);
            jp3_sb2.setBackground(bg);
            jp3_sb3.setBackground(bg);
            jp3_sb4.setBackground(bg);
            jp3_sb5.setBackground(bg);
            jp3_sb1.setBackground(bg);
            jp3_sb7.setBackground(bg);
            jp3_sb8.setBackground(bg);
        } else if (i == 7) {
            jp3_sb7.setBackground(Color.magenta);
            jp3_sb2.setBackground(bg);
            jp3_sb3.setBackground(bg);
            jp3_sb4.setBackground(bg);
            jp3_sb5.setBackground(bg);
            jp3_sb6.setBackground(bg);
            jp3_sb1.setBackground(bg);
            jp3_sb8.setBackground(bg);
        } else {
            jp3_sb8.setBackground(Color.magenta);
            jp3_sb2.setBackground(bg);
            jp3_sb3.setBackground(bg);
            jp3_sb4.setBackground(bg);
            jp3_sb5.setBackground(bg);
            jp3_sb6.setBackground(bg);
            jp3_sb7.setBackground(bg);
            jp3_sb1.setBackground(bg);
        }
    }

    boolean check_priority() {
        for (int i = 0; i < no_sem; i++) {
            for (int j = 0; j < sem[i].n; j++) {
                if (sem[i].sec[j].prioritydone == false) {
                    JOptionPane.showMessageDialog(null, "you can generate only after giving all the inputs.");
                    return false;
                }

            }
        }
        return true;
    }

    boolean check_complete() {
        for (int i = 0; i < no_sem; i++) {

            if (sem_array[i] == 0 || sem[i].n == 0) {
                return false;
            } else if (sem_array[i] != 0 && (sem_array[i] < 3 || sem_array[i] > 8)) {
                JOptionPane.showMessageDialog(null, " please eneter valid semester:3<=sem name<=8.");
                return false;
            } else if (sem[i].n > 8) {
                JOptionPane.showMessageDialog(null, " the maximum number of sections allowed is 8.");
                return false;
            }

        }
        return true;
    }

    void disp_current(String bname) {
        int i;
        System.out.println(bname);
        i = Integer.parseInt(bname.substring(5));
        tf_semname.setText(Integer.toString(sem_array[i - 1]));
        tf_nosec.setText(Integer.toString(sem[i - 1].n));
    }

    void disp_lab(int i) {
        if (i == 1) {
            tf_l1.setVisible(true);
            tf_ln1.setVisible(true);
            ljc_1.setVisible(true);

        } else if (i == 2) {
            tf_l1.setVisible(true);
            tf_ln1.setVisible(true);
            ljc_1.setVisible(true);
            tf_l2.setVisible(true);
            tf_ln2.setVisible(true);
            ljc_2.setVisible(true);
        } else if (i == 3) {
            tf_l1.setVisible(true);
            tf_ln1.setVisible(true);
            ljc_1.setVisible(true);
            tf_l2.setVisible(true);
            tf_ln2.setVisible(true);
            ljc_2.setVisible(true);
            tf_l3.setVisible(true);
            tf_ln3.setVisible(true);
            ljc_3.setVisible(true);

        } else {
            tf_l1.setVisible(true);
            tf_ln1.setVisible(true);
            ljc_1.setVisible(true);
            tf_l2.setVisible(true);
            tf_ln2.setVisible(true);
            ljc_2.setVisible(true);
            tf_l3.setVisible(true);
            tf_ln3.setVisible(true);
            ljc_3.setVisible(true);
            tf_l4.setVisible(true);
            tf_ln4.setVisible(true);
            ljc_4.setVisible(true);
        }
    }

    void change_bg(int i) {

    }

    void disp_sec(int i) {
        if (sem[i].n == 1) {
            jp3_sb1.setVisible(true);
            jp3_sb2.setVisible(false);
            jp3_sb3.setVisible(false);
            jp3_sb4.setVisible(false);
            jp3_sb5.setVisible(false);
            jp3_sb6.setVisible(false);
            jp3_sb7.setVisible(false);
            jp3_sb8.setVisible(false);
        } else if (sem[i].n == 2) {
            jp3_sb1.setVisible(true);
            jp3_sb2.setVisible(true);
            jp3_sb3.setVisible(false);
            jp3_sb4.setVisible(false);
            jp3_sb5.setVisible(false);
            jp3_sb6.setVisible(false);
            jp3_sb7.setVisible(false);
            jp3_sb8.setVisible(false);
        } else if (sem[i].n == 3) {
            jp3_sb1.setVisible(true);
            jp3_sb2.setVisible(true);
            jp3_sb3.setVisible(true);
            jp3_sb4.setVisible(false);
            jp3_sb5.setVisible(false);
            jp3_sb6.setVisible(false);
            jp3_sb7.setVisible(false);
            jp3_sb8.setVisible(false);
        } else if (sem[i].n == 4) {
            jp3_sb1.setVisible(true);
            jp3_sb2.setVisible(true);
            jp3_sb3.setVisible(true);
            jp3_sb4.setVisible(true);
            jp3_sb5.setVisible(false);
            jp3_sb6.setVisible(false);
            jp3_sb7.setVisible(false);
            jp3_sb8.setVisible(false);
        } else if (sem[i].n == 5) {
            jp3_sb1.setVisible(true);
            jp3_sb2.setVisible(true);
            jp3_sb3.setVisible(true);
            jp3_sb4.setVisible(true);
            jp3_sb5.setVisible(true);
            jp3_sb6.setVisible(false);
            jp3_sb7.setVisible(false);
            jp3_sb8.setVisible(false);
        } else if (sem[i].n == 6) {

            jp3_sb1.setVisible(true);
            jp3_sb2.setVisible(true);
            jp3_sb3.setVisible(true);
            jp3_sb4.setVisible(true);
            jp3_sb5.setVisible(true);
            jp3_sb6.setVisible(true);
            jp3_sb7.setVisible(false);
            jp3_sb8.setVisible(false);
        } else if (sem[i].n == 7) {
            jp3_sb1.setVisible(true);
            jp3_sb2.setVisible(true);
            jp3_sb3.setVisible(true);
            jp3_sb4.setVisible(true);
            jp3_sb5.setVisible(true);
            jp3_sb6.setVisible(true);
            jp3_sb7.setVisible(true);
            jp3_sb8.setVisible(false);
        } else {
            jp3_sb1.setVisible(true);
            jp3_sb2.setVisible(true);
            jp3_sb3.setVisible(true);
            jp3_sb4.setVisible(true);
            jp3_sb5.setVisible(true);
            jp3_sb6.setVisible(true);
            jp3_sb7.setVisible(true);
            jp3_sb8.setVisible(true);

        }

    }

    void display_sub_details() {
        //displaying appropriate subjects  
        tf_s1.setText(sem[sem_i - 1].sec[sec_i - 1].s[0].s_name);
        tf_s2.setText(sem[sem_i - 1].sec[sec_i - 1].s[1].s_name);
        tf_s3.setText(sem[sem_i - 1].sec[sec_i - 1].s[2].s_name);
        tf_s4.setText(sem[sem_i - 1].sec[sec_i - 1].s[3].s_name);
        tf_s5.setText(sem[sem_i - 1].sec[sec_i - 1].s[4].s_name);
        tf_s6.setText(sem[sem_i - 1].sec[sec_i - 1].s[5].s_name);
        //displaying appropriate noc 
        tf_noc1.setText(Integer.toString(sem[sem_i - 1].sec[sec_i - 1].s[0].noc));
        tf_noc2.setText(Integer.toString(sem[sem_i - 1].sec[sec_i - 1].s[1].noc));
        tf_noc3.setText(Integer.toString(sem[sem_i - 1].sec[sec_i - 1].s[2].noc));
        tf_noc4.setText(Integer.toString(sem[sem_i - 1].sec[sec_i - 1].s[3].noc));
        tf_noc5.setText(Integer.toString(sem[sem_i - 1].sec[sec_i - 1].s[4].noc));
        tf_noc6.setText(Integer.toString(sem[sem_i - 1].sec[sec_i - 1].s[5].noc));
        //displaying appropriate teachers
        tf_t1.setText(sem[sem_i - 1].sec[sec_i - 1].s[0].t_name);
        tf_t2.setText(sem[sem_i - 1].sec[sec_i - 1].s[1].t_name);
        tf_t3.setText(sem[sem_i - 1].sec[sec_i - 1].s[2].t_name);
        tf_t4.setText(sem[sem_i - 1].sec[sec_i - 1].s[3].t_name);
        tf_t5.setText(sem[sem_i - 1].sec[sec_i - 1].s[4].t_name);
        tf_t6.setText(sem[sem_i - 1].sec[sec_i - 1].s[5].t_name);
    }

    void display_lab_fields() {
        if (sem[sem_i - 1].sec[sec_i - 1].nol == 1) {
            tf_l1.setVisible(true);
            tf_ln1.setVisible(true);
            ljc_1.setVisible(true);
            tf_l2.setVisible(false);
            tf_ln2.setVisible(false);
            ljc_2.setVisible(false);
            tf_l3.setVisible(false);
            tf_ln3.setVisible(false);
            ljc_3.setVisible(false);
            tf_l4.setVisible(false);
            tf_ln4.setVisible(false);
            ljc_4.setVisible(false);

            b_saveld.setVisible(true);
            jLabel13.setVisible(true);
            jLabel14.setVisible(true);

        } else if (sem[sem_i - 1].sec[sec_i - 1].nol == 2) {
            tf_l1.setVisible(true);
            tf_ln1.setVisible(true);
            ljc_1.setVisible(true);
            tf_l2.setVisible(true);
            tf_ln2.setVisible(true);
            ljc_2.setVisible(true);
            tf_l3.setVisible(false);
            tf_ln3.setVisible(false);
            ljc_3.setVisible(false);
            tf_l4.setVisible(false);
            tf_ln4.setVisible(false);
            ljc_4.setVisible(false);

            b_saveld.setVisible(true);
            jLabel13.setVisible(true);
            jLabel14.setVisible(true);

        } else if (sem[sem_i - 1].sec[sec_i - 1].nol == 3) {
            tf_l1.setVisible(true);
            tf_ln1.setVisible(true);
            ljc_1.setVisible(true);
            tf_l2.setVisible(true);
            tf_ln2.setVisible(true);
            ljc_2.setVisible(true);
            tf_l3.setVisible(true);
            tf_ln3.setVisible(true);
            ljc_3.setVisible(true);
            tf_l4.setVisible(false);
            tf_ln4.setVisible(false);
            ljc_4.setVisible(false);

            b_saveld.setVisible(true);
            jLabel13.setVisible(true);
            jLabel14.setVisible(true);

        } else if (sem[sem_i - 1].sec[sec_i - 1].nol == 4) {
            tf_l1.setVisible(true);
            tf_ln1.setVisible(true);
            ljc_1.setVisible(true);
            tf_l2.setVisible(true);
            tf_ln2.setVisible(true);
            ljc_2.setVisible(true);
            tf_l3.setVisible(true);
            tf_ln3.setVisible(true);
            ljc_3.setVisible(true);
            tf_l4.setVisible(true);
            tf_ln4.setVisible(true);
            ljc_4.setVisible(true);

            b_saveld.setVisible(true);
            jLabel13.setVisible(true);
            jLabel14.setVisible(true);

        }
    }

    String getday(int day) {
        switch (day) {
            case 0:
                return "monday";
            case 1:
                return "tuesday";
            case 2:
                return "wednesday";
            case 3:
                return "thursday";
            case 4:
                return "friday";
            case 5:
                return "saturday";

        }

        return "day";

    }

    public static void clearTable(final JTable table) {
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                table.setValueAt("", i, j);
            }
        }
    }

    int get_day_index(String day) {
        switch (day) {
            case "mon":
                return 0;
            case "tue":
                return 1;
            case "wed":
                return 2;
            case "thu":
                return 3;
            case "fri":
                return 4;
            case "sat":
                return 5;

        }

        return 0;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel32 = new javax.swing.JLabel();
        table_select = new javax.swing.JPopupMenu();
        DELETE = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        parentpane = new javax.swing.JPanel();
        jp1 = new javax.swing.JPanel();
        b_sp = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jp2 = new javax.swing.JPanel();
        l_nos = new javax.swing.JLabel();
        tf_nos = new javax.swing.JTextField();
        jp2_b_nxt = new javax.swing.JButton();
        jp3 = new javax.swing.JPanel();
        jp3_sp1 = new javax.swing.JScrollPane();
        sp1_jp1 = new javax.swing.JPanel();
        sp1_b1 = new javax.swing.JButton();
        sp1_b2 = new javax.swing.JButton();
        sp1_b3 = new javax.swing.JButton();
        sp1_b4 = new javax.swing.JButton();
        sp1_b5 = new javax.swing.JButton();
        sp1_b6 = new javax.swing.JButton();
        sp1_b7 = new javax.swing.JButton();
        sp1_b8 = new javax.swing.JButton();
        jp3_parent = new javax.swing.JPanel();
        cd1 = new javax.swing.JPanel();
        l_semname = new javax.swing.JLabel();
        l_semdet = new javax.swing.JLabel();
        tf_semname = new javax.swing.JTextField();
        l_nosec = new javax.swing.JLabel();
        tf_nosec = new javax.swing.JTextField();
        jp3_b_submit = new javax.swing.JButton();
        cd2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jp3_sb1 = new javax.swing.JButton();
        jp3_sb2 = new javax.swing.JButton();
        jp3_sb3 = new javax.swing.JButton();
        jp3_sb4 = new javax.swing.JButton();
        jp3_sb5 = new javax.swing.JButton();
        jp3_sb6 = new javax.swing.JButton();
        jp3_sb7 = new javax.swing.JButton();
        jp3_sb8 = new javax.swing.JButton();
        jp_sec = new javax.swing.JPanel();
        l_namesec = new javax.swing.JLabel();
        tf_namesec = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        b_sbmt_secname = new javax.swing.JButton();
        tf_e_semname = new javax.swing.JTextField();
        tab_container = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tp_1 = new javax.swing.JTabbedPane();
        b_subld = new javax.swing.JPanel();
        cd1_ld = new javax.swing.JPanel();
        b_nolsub = new javax.swing.JButton();
        tf_l2 = new javax.swing.JTextField();
        tf_ln1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_l4 = new javax.swing.JTextField();
        tf_l3 = new javax.swing.JTextField();
        tf_l1 = new javax.swing.JTextField();
        tf_nol = new javax.swing.JTextField();
        tf_ln2 = new javax.swing.JTextField();
        tf_ln4 = new javax.swing.JTextField();
        tf_ln3 = new javax.swing.JTextField();
        ljc_1 = new javax.swing.JComboBox();
        ljc_2 = new javax.swing.JComboBox();
        ljc_3 = new javax.swing.JComboBox();
        ljc_4 = new javax.swing.JComboBox();
        b_saveld = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cd2_ld = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        tf_nol_pw = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cb_labday = new javax.swing.JComboBox();
        cb_strtprd = new javax.swing.JComboBox();
        b_sub_lp = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tble_lp = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        tp1jp1 = new javax.swing.JPanel();
        jp_pres = new javax.swing.JPanel();
        jp_pres_done = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tble_sublist = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        tf_nosub = new javax.swing.JTextField();
        sublist_sub = new javax.swing.JButton();
        sub_modify = new javax.swing.JButton();
        jp7 = new javax.swing.JPanel();
        b_prescribed = new javax.swing.JButton();
        b_custom = new javax.swing.JButton();
        jp_cust = new javax.swing.JPanel();
        l_nosubj = new javax.swing.JLabel();
        s_tf1 = new javax.swing.JTextField();
        s_tf2 = new javax.swing.JTextField();
        s_tf3 = new javax.swing.JTextField();
        s_tf4 = new javax.swing.JTextField();
        s_tf5 = new javax.swing.JTextField();
        s_tf6 = new javax.swing.JTextField();
        s_tf7 = new javax.swing.JTextField();
        s_tf8 = new javax.swing.JTextField();
        s_tf9 = new javax.swing.JTextField();
        s_tf10 = new javax.swing.JTextField();
        s_tf11 = new javax.swing.JTextField();
        s_tf12 = new javax.swing.JTextField();
        s_tf13 = new javax.swing.JTextField();
        b_jpcust_submit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jp_subdetails = new javax.swing.JPanel();
        cd1_subdetails = new javax.swing.JPanel();
        tf_noc7 = new javax.swing.JTextField();
        tf_noc5 = new javax.swing.JTextField();
        jc_4 = new javax.swing.JComboBox();
        tf_s6 = new javax.swing.JTextField();
        jc_8 = new javax.swing.JComboBox();
        jlab_noc = new javax.swing.JLabel();
        tf_s5 = new javax.swing.JTextField();
        tf_s8 = new javax.swing.JTextField();
        jc_7 = new javax.swing.JComboBox();
        tf_s9 = new javax.swing.JTextField();
        jc_3 = new javax.swing.JComboBox();
        tf_t6 = new javax.swing.JTextField();
        tf_s10 = new javax.swing.JTextField();
        tf_noc9 = new javax.swing.JTextField();
        tf_s7 = new javax.swing.JTextField();
        tf_noc1 = new javax.swing.JTextField();
        tf_t4 = new javax.swing.JTextField();
        tf_noc6 = new javax.swing.JTextField();
        tf_noc3 = new javax.swing.JTextField();
        tf_noc4 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jc_2 = new javax.swing.JComboBox();
        tf_s3 = new javax.swing.JTextField();
        tf_t5 = new javax.swing.JTextField();
        jc_10 = new javax.swing.JComboBox();
        tf_s1 = new javax.swing.JTextField();
        tf_noc2 = new javax.swing.JTextField();
        tf_t1 = new javax.swing.JTextField();
        tf_t3 = new javax.swing.JTextField();
        bdone_subdet = new javax.swing.JButton();
        tf_t9 = new javax.swing.JTextField();
        tf_t7 = new javax.swing.JTextField();
        tf_t10 = new javax.swing.JTextField();
        tf_t8 = new javax.swing.JTextField();
        tf_t2 = new javax.swing.JTextField();
        tf_noc8 = new javax.swing.JTextField();
        jc_9 = new javax.swing.JComboBox();
        tf_s2 = new javax.swing.JTextField();
        jc_6 = new javax.swing.JComboBox();
        tf_s4 = new javax.swing.JTextField();
        jlab_sublist = new javax.swing.JLabel();
        jc_1 = new javax.swing.JComboBox();
        tf_noc10 = new javax.swing.JTextField();
        jc_5 = new javax.swing.JComboBox();
        jp_priority = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jc_days = new javax.swing.JComboBox();
        jc_sublist = new javax.swing.JComboBox();
        jc_period = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        priority_table = new javax.swing.JTable();
        b_del_prio = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        timetable = new javax.swing.JPanel();
        tt_generated3 = new javax.swing.JPanel();
        displaytt = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        generatett = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        b_1_1 = new javax.swing.JButton();
        b_1_2 = new javax.swing.JButton();
        b_1_3 = new javax.swing.JButton();
        b_1_4 = new javax.swing.JButton();
        b_1_5 = new javax.swing.JButton();
        b_1_6 = new javax.swing.JButton();
        b_1_7 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        b_2_1 = new javax.swing.JButton();
        b_2_2 = new javax.swing.JButton();
        b_2_3 = new javax.swing.JButton();
        b_2_4 = new javax.swing.JButton();
        b_2_5 = new javax.swing.JButton();
        b_2_6 = new javax.swing.JButton();
        b_2_7 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        b_3_1 = new javax.swing.JButton();
        b_3_2 = new javax.swing.JButton();
        b_3_3 = new javax.swing.JButton();
        b_3_4 = new javax.swing.JButton();
        b_3_5 = new javax.swing.JButton();
        b_3_6 = new javax.swing.JButton();
        b_3_7 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        b_4_1 = new javax.swing.JButton();
        b_4_2 = new javax.swing.JButton();
        b_4_3 = new javax.swing.JButton();
        b_4_4 = new javax.swing.JButton();
        b_4_5 = new javax.swing.JButton();
        b_4_6 = new javax.swing.JButton();
        b_4_7 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        b_5_1 = new javax.swing.JButton();
        b_5_2 = new javax.swing.JButton();
        b_5_3 = new javax.swing.JButton();
        b_5_4 = new javax.swing.JButton();
        b_5_5 = new javax.swing.JButton();
        b_5_6 = new javax.swing.JButton();
        b_5_7 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        b_6_1 = new javax.swing.JButton();
        b_6_2 = new javax.swing.JButton();
        b_6_3 = new javax.swing.JButton();
        b_6_4 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        b_ok = new javax.swing.JButton();
        b_next_teacher = new javax.swing.JButton();
        b_done_jp5 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();

        jDialog1.setTitle("ABOUT US");
        jDialog1.setAlwaysOnTop(true);

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Presentation1 (2).png"))); // NOI18N

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel32)
        );

        DELETE.setText("jMenuItem1");
        DELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETEActionPerformed(evt);
            }
        });
        table_select.add(DELETE);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));

        parentpane.setBackground(new java.awt.Color(204, 204, 255));
        parentpane.setLayout(new java.awt.CardLayout());

        b_sp.setText("START PLANNING");
        b_sp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_spActionPerformed(evt);
            }
        });

        jButton5.setText("EXIT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp1Layout = new javax.swing.GroupLayout(jp1);
        jp1.setLayout(jp1Layout);
        jp1Layout.setHorizontalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp1Layout.createSequentialGroup()
                .addContainerGap(511, Short.MAX_VALUE)
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(567, Short.MAX_VALUE))
        );
        jp1Layout.setVerticalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp1Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(b_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(914, Short.MAX_VALUE))
        );

        parentpane.add(jp1, "card2");

        l_nos.setText("NUMBER OF SEMESTERS");

        tf_nos.setText("NUMBER");
        tf_nos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_nosMouseClicked(evt);
            }
        });
        tf_nos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_nosKeyReleased(evt);
            }
        });

        jp2_b_nxt.setText("NEXT");
        jp2_b_nxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp2_b_nxtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp2Layout = new javax.swing.GroupLayout(jp2);
        jp2.setLayout(jp2Layout);
        jp2Layout.setHorizontalGroup(
            jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp2Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(l_nos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(tf_nos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jp2_b_nxt, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(720, Short.MAX_VALUE))
        );
        jp2Layout.setVerticalGroup(
            jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp2Layout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_nos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_nos, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jp2_b_nxt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1010, Short.MAX_VALUE))
        );

        parentpane.add(jp2, "card3");

        jp3_sp1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jp3_sp1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        sp1_b1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        sp1_b1.setText("SEMESTER 1");
        sp1_b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sp1_b1MouseClicked(evt);
            }
        });
        sp1_b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sp1_b1ActionPerformed(evt);
            }
        });

        sp1_b2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        sp1_b2.setText("SEMESTER 2");
        sp1_b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sp1_b2ActionPerformed(evt);
            }
        });

        sp1_b3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        sp1_b3.setText("SEMESTER 3");
        sp1_b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sp1_b3ActionPerformed(evt);
            }
        });

        sp1_b4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        sp1_b4.setText("SEMESTER 4");
        sp1_b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sp1_b4ActionPerformed(evt);
            }
        });

        sp1_b5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        sp1_b5.setText("SEMESTER 5");
        sp1_b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sp1_b5ActionPerformed(evt);
            }
        });

        sp1_b6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        sp1_b6.setText("SEMESTER 6");
        sp1_b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sp1_b6ActionPerformed(evt);
            }
        });

        sp1_b7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        sp1_b7.setText("SEMESTER 7");
        sp1_b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sp1_b7ActionPerformed(evt);
            }
        });

        sp1_b8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        sp1_b8.setText("SEMESTER 8");
        sp1_b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sp1_b8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sp1_jp1Layout = new javax.swing.GroupLayout(sp1_jp1);
        sp1_jp1.setLayout(sp1_jp1Layout);
        sp1_jp1Layout.setHorizontalGroup(
            sp1_jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sp1_jp1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(sp1_jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sp1_b8)
                    .addComponent(sp1_b2)
                    .addComponent(sp1_b3)
                    .addComponent(sp1_b4)
                    .addComponent(sp1_b5)
                    .addComponent(sp1_b6)
                    .addComponent(sp1_b7)
                    .addComponent(sp1_b1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(127, 127, 127))
        );

        sp1_jp1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {sp1_b1, sp1_b2, sp1_b3, sp1_b4, sp1_b5, sp1_b6, sp1_b7, sp1_b8});

        sp1_jp1Layout.setVerticalGroup(
            sp1_jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sp1_jp1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(sp1_b1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(sp1_b2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(sp1_b3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(sp1_b4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(sp1_b5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(sp1_b6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(sp1_b7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(sp1_b8)
                .addGap(100, 100, 100))
        );

        sp1_jp1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {sp1_b1, sp1_b2, sp1_b3, sp1_b4, sp1_b5, sp1_b6, sp1_b7, sp1_b8});

        jp3_sp1.setViewportView(sp1_jp1);

        jp3_parent.setLayout(new java.awt.CardLayout());

        l_semname.setText("SEMESTER NAME ");

        l_semdet.setText("SEMESTER DETAILS");

        tf_semname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_semnameMouseClicked(evt);
            }
        });
        tf_semname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_semnameActionPerformed(evt);
            }
        });
        tf_semname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_semnameKeyPressed(evt);
            }
        });

        l_nosec.setText("NUMBER OF SECTIONS");

        tf_nosec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_nosecMouseClicked(evt);
            }
        });
        tf_nosec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nosecActionPerformed(evt);
            }
        });
        tf_nosec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_nosecKeyPressed(evt);
            }
        });

        jp3_b_submit.setText("SUBMIT");
        jp3_b_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp3_b_submitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cd1Layout = new javax.swing.GroupLayout(cd1);
        cd1.setLayout(cd1Layout);
        cd1Layout.setHorizontalGroup(
            cd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cd1Layout.createSequentialGroup()
                .addComponent(l_semdet, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cd1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(cd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l_semname)
                    .addComponent(l_nosec, javax.swing.GroupLayout.DEFAULT_SIZE, 888, Short.MAX_VALUE))
                .addGap(88, 88, 88)
                .addGroup(cd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp3_b_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_nosec, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_semname, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        cd1Layout.setVerticalGroup(
            cd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cd1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(l_semdet, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(cd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_semname, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_semname, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(cd1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l_nosec, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_nosec, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jp3_b_submit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(993, Short.MAX_VALUE))
        );

        jp3_parent.add(cd1, "card2");

        jp3_sb1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jp3_sb1.setText("SECTION 1");
        jp3_sb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jp3_sb1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jp3_sb1MouseReleased(evt);
            }
        });
        jp3_sb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp3_sb1ActionPerformed(evt);
            }
        });

        jp3_sb2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jp3_sb2.setText("SECTION 2");
        jp3_sb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp3_sb2ActionPerformed(evt);
            }
        });

        jp3_sb3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jp3_sb3.setText("SECTION 3");
        jp3_sb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp3_sb3ActionPerformed(evt);
            }
        });

        jp3_sb4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jp3_sb4.setText("SECTION 4");
        jp3_sb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp3_sb4ActionPerformed(evt);
            }
        });

        jp3_sb5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jp3_sb5.setText("SECTION 5");
        jp3_sb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp3_sb5ActionPerformed(evt);
            }
        });

        jp3_sb6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jp3_sb6.setText("SECTION 6");
        jp3_sb6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp3_sb6ActionPerformed(evt);
            }
        });

        jp3_sb7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jp3_sb7.setText("SECTION 7");
        jp3_sb7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp3_sb7ActionPerformed(evt);
            }
        });

        jp3_sb8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jp3_sb8.setText("SECTION 8");
        jp3_sb8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp3_sb8ActionPerformed(evt);
            }
        });

        l_namesec.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        l_namesec.setText("NAME OF THE SECTION");

        tf_namesec.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_namesec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_namesecMouseClicked(evt);
            }
        });
        tf_namesec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_namesecActionPerformed(evt);
            }
        });
        tf_namesec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_namesecKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("SEMESTER");

        b_sbmt_secname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        b_sbmt_secname.setText("SUBMIT");
        b_sbmt_secname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sbmt_secnameActionPerformed(evt);
            }
        });

        tf_e_semname.setEditable(false);
        tf_e_semname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_e_semname.setText("sem");
        tf_e_semname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_e_semnameActionPerformed(evt);
            }
        });

        tab_container.setLayout(new java.awt.CardLayout());

        tp_1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tp_1AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tp_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tp_1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tp_1MousePressed(evt);
            }
        });

        b_subld.setLayout(new java.awt.CardLayout());

        b_nolsub.setText("SUBMIT");
        b_nolsub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_nolsubActionPerformed(evt);
            }
        });

        tf_l2.setText("LAB2");
        tf_l2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_l2ActionPerformed(evt);
            }
        });

        tf_ln1.setText("Select Teacher-->");
        tf_ln1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_ln1ActionPerformed(evt);
            }
        });

        jLabel3.setText("NUMBER OF LABS ");

        tf_l4.setText("LAB 4");
        tf_l4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_l4ActionPerformed(evt);
            }
        });

        tf_l3.setText("LAB3");
        tf_l3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_l3ActionPerformed(evt);
            }
        });

        tf_l1.setText("LAB1");

        tf_nol.setText("NUMBER");
        tf_nol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_nolMouseClicked(evt);
            }
        });
        tf_nol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nolActionPerformed(evt);
            }
        });
        tf_nol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_nolKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_nolKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tf_nolKeyTyped(evt);
            }
        });

        tf_ln2.setText("Select Teacher-->");
        tf_ln2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_ln2ActionPerformed(evt);
            }
        });

        tf_ln4.setText("Select Teacher-->");
        tf_ln4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_ln4ActionPerformed(evt);
            }
        });

        tf_ln3.setText("Select Teacher-->");
        tf_ln3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_ln3ActionPerformed(evt);
            }
        });

        ljc_1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        ljc_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ljc_1ActionPerformed(evt);
            }
        });

        ljc_2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        ljc_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ljc_2ActionPerformed(evt);
            }
        });

        ljc_3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        ljc_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ljc_3ActionPerformed(evt);
            }
        });

        ljc_4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        ljc_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ljc_4ActionPerformed(evt);
            }
        });

        b_saveld.setText("SAVE");
        b_saveld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_saveldActionPerformed(evt);
            }
        });

        jLabel13.setText("LAB CODE");

        jLabel14.setText("TEACHER NAME");

        javax.swing.GroupLayout cd1_ldLayout = new javax.swing.GroupLayout(cd1_ld);
        cd1_ld.setLayout(cd1_ldLayout);
        cd1_ldLayout.setHorizontalGroup(
            cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cd1_ldLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(cd1_ldLayout.createSequentialGroup()
                            .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(cd1_ldLayout.createSequentialGroup()
                                    .addComponent(tf_l4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_ln4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(cd1_ldLayout.createSequentialGroup()
                                    .addComponent(tf_l2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_ln2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(cd1_ldLayout.createSequentialGroup()
                                    .addComponent(tf_l3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_ln3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(cd1_ldLayout.createSequentialGroup()
                                    .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(tf_l1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_ln1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ljc_2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ljc_1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ljc_3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ljc_4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(9, 9, 9))
                        .addComponent(b_saveld, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cd1_ldLayout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(80, 80, 80)
                            .addComponent(tf_nol, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(b_nolsub, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(720, Short.MAX_VALUE))
        );

        cd1_ldLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ljc_1, ljc_2, ljc_3, ljc_4});

        cd1_ldLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tf_ln1, tf_ln2, tf_ln3, tf_ln4});

        cd1_ldLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tf_l1, tf_l2, tf_l3, tf_l4});

        cd1_ldLayout.setVerticalGroup(
            cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cd1_ldLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_nol, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_nolsub, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cd1_ldLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_l1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ln1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ljc_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_ln2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_l2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ljc_2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_l3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ln3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ljc_3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(cd1_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_l4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_ln4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ljc_4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(b_saveld, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(535, Short.MAX_VALUE))
        );

        cd1_ldLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ljc_1, ljc_2, ljc_3, ljc_4});

        cd1_ldLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tf_ln1, tf_ln2, tf_ln3, tf_ln4});

        cd1_ldLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tf_l1, tf_l2, tf_l3, tf_l4});

        b_subld.add(cd1_ld, "card2");

        jLabel8.setText("How many labs per week ");

        tf_nol_pw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_nol_pwKeyReleased(evt);
            }
        });

        jButton3.setText("SUBMIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setText("LAB DAY");

        jLabel10.setText("STARTING PERIOD");

        cb_labday.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MON", "TUE", "WED", "THU", "FRI", "SAT" }));

        cb_strtprd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));

        b_sub_lp.setText("SUBMIT");
        b_sub_lp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sub_lpActionPerformed(evt);
            }
        });

        tble_lp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "LAB DAY", "STARTING PERIOD"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tble_lp.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tble_lp.getTableHeader().setReorderingAllowed(false);
        tble_lp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tble_lpMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tble_lpMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tble_lp);
        tble_lp.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tble_lp.getColumnModel().getColumnCount() > 0) {
            tble_lp.getColumnModel().getColumn(0).setResizable(false);
            tble_lp.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton4.setText("NEXT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel31.setText("Select the lab to be deleted, and  press Delete.");

        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cd2_ldLayout = new javax.swing.GroupLayout(cd2_ld);
        cd2_ld.setLayout(cd2_ldLayout);
        cd2_ldLayout.setHorizontalGroup(
            cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cd2_ldLayout.createSequentialGroup()
                .addGap(471, 471, 471)
                .addGroup(cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cd2_ldLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cd2_ldLayout.createSequentialGroup()
                                .addGroup(cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                                    .addGroup(cd2_ldLayout.createSequentialGroup()
                                        .addGroup(cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addGroup(cd2_ldLayout.createSequentialGroup()
                                                .addComponent(cb_labday, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(120, 120, 120)
                                                .addComponent(cb_strtprd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(89, 89, 89))
                            .addGroup(cd2_ldLayout.createSequentialGroup()
                                .addGap(178, 178, 178)
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cd2_ldLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(cd2_ldLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                .addComponent(tf_nol_pw))
                            .addGroup(cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(b_sub_lp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(464, 464, 464))
        );
        cd2_ldLayout.setVerticalGroup(
            cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cd2_ldLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cd2_ldLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(tf_nol_pw, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_strtprd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_sub_lp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_labday, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(cd2_ldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(jButton8))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cd2_ldLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cb_labday, cb_strtprd});

        cd2_ldLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {b_sub_lp, jButton8});

        b_subld.add(cd2_ld, "card3");

        tp_1.addTab("LABS", b_subld);

        tp1jp1.setLayout(new java.awt.CardLayout());

        jp_pres_done.setText("DONE");
        jp_pres_done.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jp_pres_doneActionPerformed(evt);
            }
        });

        tble_sublist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SL.NO", "SUB CODE", "SUB NAME"
            }
        ));
        jScrollPane7.setViewportView(tble_sublist);

        jLabel15.setText("NUMBER OF SUBJECTS");

        tf_nosub.setText("NUMBER");
        tf_nosub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_nosubMouseClicked(evt);
            }
        });
        tf_nosub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_nosubActionPerformed(evt);
            }
        });

        sublist_sub.setText("SUBMIT");
        sublist_sub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sublist_subActionPerformed(evt);
            }
        });

        sub_modify.setText("MODIFY");
        sub_modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub_modifyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_presLayout = new javax.swing.GroupLayout(jp_pres);
        jp_pres.setLayout(jp_presLayout);
        jp_presLayout.setHorizontalGroup(
            jp_presLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_presLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_presLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_presLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(tf_nosub, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(sublist_sub))
                    .addComponent(sub_modify)
                    .addGroup(jp_presLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jp_pres_done, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(709, Short.MAX_VALUE))
        );
        jp_presLayout.setVerticalGroup(
            jp_presLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_presLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_presLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_nosub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sublist_sub))
                .addGap(18, 18, 18)
                .addComponent(sub_modify)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jp_pres_done)
                .addContainerGap(454, Short.MAX_VALUE))
        );

        tp1jp1.add(jp_pres, "card4");

        b_prescribed.setText("PRESCRIBED");
        b_prescribed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_prescribedActionPerformed(evt);
            }
        });

        b_custom.setText("CUSTOM");
        b_custom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_customActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp7Layout = new javax.swing.GroupLayout(jp7);
        jp7.setLayout(jp7Layout);
        jp7Layout.setHorizontalGroup(
            jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp7Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(b_prescribed)
                .addGap(68, 68, 68)
                .addComponent(b_custom, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(987, Short.MAX_VALUE))
        );
        jp7Layout.setVerticalGroup(
            jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp7Layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addGroup(jp7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_prescribed)
                    .addComponent(b_custom))
                .addContainerGap(698, Short.MAX_VALUE))
        );

        tp1jp1.add(jp7, "card2");

        l_nosubj.setText("NUMBER OF SUBJECTS");

        s_tf2.setText("jTextField2");

        s_tf3.setText("jTextField3");

        s_tf4.setText("jTextField4");

        s_tf5.setText("jTextField5");

        s_tf6.setText("jTextField6");
        s_tf6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s_tf6ActionPerformed(evt);
            }
        });

        s_tf7.setText("jTextField7");

        s_tf8.setText("jTextField8");

        s_tf9.setText("jTextField9");

        s_tf10.setText("jTextField10");

        s_tf11.setText("jTextField11");

        s_tf12.setText("jTextField12");

        s_tf13.setText("jTextField13");

        b_jpcust_submit.setText("SUBMIT");
        b_jpcust_submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_jpcust_submitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_custLayout = new javax.swing.GroupLayout(jp_cust);
        jp_cust.setLayout(jp_custLayout);
        jp_custLayout.setHorizontalGroup(
            jp_custLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_custLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jp_custLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(b_jpcust_submit)
                    .addGroup(jp_custLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jp_custLayout.createSequentialGroup()
                            .addComponent(l_nosubj, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(68, 68, 68)
                            .addComponent(s_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jp_custLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(s_tf13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_tf12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_tf11, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_tf9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_tf10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_tf8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_tf7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_tf6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_tf5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_tf4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_tf3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(s_tf2, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp_custLayout.setVerticalGroup(
            jp_custLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_custLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jp_custLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_nosubj)
                    .addComponent(s_tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(s_tf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s_tf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s_tf4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s_tf5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s_tf6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s_tf7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s_tf8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s_tf9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s_tf10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(s_tf11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(s_tf12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(s_tf13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(b_jpcust_submit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tp1jp1.add(jp_cust, "card3");

        jLabel11.setText("you have'nt entered lab details , please enter lab details and come back");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tp1jp1.add(jPanel2, "card5");

        tp_1.addTab("SUBJECT LIST", tp1jp1);

        jp_subdetails.setLayout(new java.awt.CardLayout());

        tf_noc7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_noc7.setText("NO. OF CLASSES");
        tf_noc7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_noc7MouseClicked(evt);
            }
        });

        tf_noc5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_noc5.setText("NO. OF CLASSES");
        tf_noc5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_noc5MouseClicked(evt);
            }
        });

        jc_4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        jc_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_4ActionPerformed(evt);
            }
        });

        tf_s6.setEditable(false);
        tf_s6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_s6.setText("SUBJECT6");
        tf_s6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_s6ActionPerformed(evt);
            }
        });

        jc_8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        jc_8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_8ActionPerformed(evt);
            }
        });

        jlab_noc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jlab_noc.setText("NUMBER OF CLASSES/WEEK");

        tf_s5.setEditable(false);
        tf_s5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_s5.setText("SUBJECT5");

        tf_s8.setEditable(false);
        tf_s8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_s8.setText("SUBJECT 8");
        tf_s8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_s8ActionPerformed(evt);
            }
        });

        jc_7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        jc_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_7ActionPerformed(evt);
            }
        });

        tf_s9.setEditable(false);
        tf_s9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_s9.setText("SUBJECT 9");
        tf_s9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_s9ActionPerformed(evt);
            }
        });

        jc_3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        jc_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_3ActionPerformed(evt);
            }
        });

        tf_t6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        tf_s10.setEditable(false);
        tf_s10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_s10.setText("SUBJECT 10");
        tf_s10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_s10ActionPerformed(evt);
            }
        });

        tf_noc9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_noc9.setText("NO. OF CLASSES");
        tf_noc9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_noc9MouseClicked(evt);
            }
        });

        tf_s7.setEditable(false);
        tf_s7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_s7.setText("SUBJECT7");

        tf_noc1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_noc1.setText("NO. OF CLASSES");
        tf_noc1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_noc1MouseClicked(evt);
            }
        });

        tf_t4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_t4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_t4ActionPerformed(evt);
            }
        });

        tf_noc6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_noc6.setText("NO. OF CLASSES");
        tf_noc6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_noc6MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tf_noc6MouseReleased(evt);
            }
        });
        tf_noc6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_noc6ActionPerformed(evt);
            }
        });

        tf_noc3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_noc3.setText("NO. OF CLASSES");
        tf_noc3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_noc3MouseClicked(evt);
            }
        });
        tf_noc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_noc3ActionPerformed(evt);
            }
        });

        tf_noc4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_noc4.setText("NO. OF CLASSES");
        tf_noc4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_noc4MouseClicked(evt);
            }
        });
        tf_noc4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_noc4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("TEACHER");

        jc_2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        jc_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_2ActionPerformed(evt);
            }
        });

        tf_s3.setEditable(false);
        tf_s3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_s3.setText("SUBJECT3");

        tf_t5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        jc_10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        jc_10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_10ActionPerformed(evt);
            }
        });

        tf_s1.setEditable(false);
        tf_s1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_s1.setText("SUBJECT1");
        tf_s1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_s1ActionPerformed(evt);
            }
        });

        tf_noc2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_noc2.setText("NO. OF CLASSES");
        tf_noc2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_noc2MouseClicked(evt);
            }
        });
        tf_noc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_noc2ActionPerformed(evt);
            }
        });

        tf_t1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_t1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_t1ActionPerformed(evt);
            }
        });

        tf_t3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_t3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_t3ActionPerformed(evt);
            }
        });

        bdone_subdet.setText("DONE");
        bdone_subdet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bdone_subdetActionPerformed(evt);
            }
        });

        tf_t9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        tf_t7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        tf_t10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        tf_t8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        tf_t2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N

        tf_noc8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_noc8.setText("NO. OF CLASSES");
        tf_noc8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_noc8MouseClicked(evt);
            }
        });

        jc_9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        jc_9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_9ActionPerformed(evt);
            }
        });

        tf_s2.setEditable(false);
        tf_s2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_s2.setText("SUBJECT2");
        tf_s2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_s2ActionPerformed(evt);
            }
        });

        jc_6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        jc_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_6ActionPerformed(evt);
            }
        });

        tf_s4.setEditable(false);
        tf_s4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_s4.setText("SUBJECT4");
        tf_s4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_s4ActionPerformed(evt);
            }
        });

        jlab_sublist.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jlab_sublist.setText("             SUBJECTS");

        jc_1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        jc_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_1ActionPerformed(evt);
            }
        });

        tf_noc10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tf_noc10.setText("NO. OF CLASSES");
        tf_noc10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_noc10MouseClicked(evt);
            }
        });

        jc_5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        jc_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cd1_subdetailsLayout = new javax.swing.GroupLayout(cd1_subdetails);
        cd1_subdetails.setLayout(cd1_subdetailsLayout);
        cd1_subdetailsLayout.setHorizontalGroup(
            cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cd1_subdetailsLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bdone_subdet, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cd1_subdetailsLayout.createSequentialGroup()
                            .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tf_s2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cd1_subdetailsLayout.createSequentialGroup()
                                    .addComponent(tf_s10, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_noc10, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cd1_subdetailsLayout.createSequentialGroup()
                                    .addComponent(tf_s9, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_noc9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cd1_subdetailsLayout.createSequentialGroup()
                                    .addComponent(tf_s8, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_noc8, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cd1_subdetailsLayout.createSequentialGroup()
                                    .addComponent(tf_s7, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_noc7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cd1_subdetailsLayout.createSequentialGroup()
                                    .addComponent(tf_s6, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_noc6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cd1_subdetailsLayout.createSequentialGroup()
                                    .addComponent(tf_s5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_noc5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cd1_subdetailsLayout.createSequentialGroup()
                                    .addComponent(tf_s4, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_noc4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(cd1_subdetailsLayout.createSequentialGroup()
                                    .addComponent(tf_s3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(cd1_subdetailsLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tf_noc2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(cd1_subdetailsLayout.createSequentialGroup()
                                            .addGap(43, 43, 43)
                                            .addComponent(tf_noc3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cd1_subdetailsLayout.createSequentialGroup()
                                    .addComponent(tf_s1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tf_noc1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(48, 48, 48)
                            .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tf_t3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_t2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_t5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_t6, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_t7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_t8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_t9, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_t10, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_t1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_t4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(70, 70, 70)
                            .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jc_1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jc_2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jc_3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jc_4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jc_5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jc_6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jc_7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jc_8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jc_9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jc_10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(cd1_subdetailsLayout.createSequentialGroup()
                            .addComponent(jlab_sublist, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jlab_noc, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(87, 87, 87)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(416, Short.MAX_VALUE))
        );

        cd1_subdetailsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tf_t1, tf_t10, tf_t2, tf_t3, tf_t4, tf_t5, tf_t6, tf_t7, tf_t8, tf_t9});

        cd1_subdetailsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tf_s1, tf_s10, tf_s2, tf_s3, tf_s4, tf_s5, tf_s6, tf_s7, tf_s8, tf_s9});

        cd1_subdetailsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jc_1, jc_10, jc_2, jc_3, jc_4, jc_5, jc_6, jc_7, jc_8, jc_9});

        cd1_subdetailsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tf_noc1, tf_noc10, tf_noc2, tf_noc3, tf_noc4, tf_noc5, tf_noc6, tf_noc7, tf_noc8, tf_noc9});

        cd1_subdetailsLayout.setVerticalGroup(
            cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cd1_subdetailsLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlab_sublist, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlab_noc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_s1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_noc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_t1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jc_1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cd1_subdetailsLayout.createSequentialGroup()
                        .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jc_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_s2)
                            .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tf_noc2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tf_t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(22, 22, 22)
                        .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_s3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_noc3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jc_3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_t3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_s4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_noc4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jc_4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_t4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_s5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_noc5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jc_5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_t5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_s6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_noc6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jc_6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_t6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_s7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_noc7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jc_7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_s8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_noc8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jc_8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_t8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_s9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_noc9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jc_9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(cd1_subdetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_s10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tf_noc10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jc_10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cd1_subdetailsLayout.createSequentialGroup()
                        .addComponent(tf_t7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(tf_t9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(tf_t10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addComponent(bdone_subdet, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
        );

        cd1_subdetailsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tf_t1, tf_t10, tf_t2, tf_t3, tf_t4, tf_t5, tf_t6, tf_t7, tf_t8, tf_t9});

        cd1_subdetailsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jc_1, jc_10, jc_2, jc_3, jc_4, jc_5, jc_6, jc_7, jc_8, jc_9});

        cd1_subdetailsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tf_s1, tf_s10, tf_s2, tf_s3, tf_s4, tf_s5, tf_s6, tf_s7, tf_s8, tf_s9});

        cd1_subdetailsLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tf_noc1, tf_noc10, tf_noc2, tf_noc3, tf_noc4, tf_noc5, tf_noc6, tf_noc7, tf_noc8, tf_noc9});

        jp_subdetails.add(cd1_subdetails, "card2");

        tp_1.addTab("SUBJECT DETAILS", jp_subdetails);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 15)); // NOI18N
        jLabel2.setText("Give priorities from the boxes below and submit for each priority.");

        jLabel5.setText("DAY");

        jLabel6.setText("SUBJECT");

        jLabel7.setText("PERIOD");

        jc_days.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MON", "TUE", "WED", "THU", "FRI", "SAT" }));

        jc_sublist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jc_sublistActionPerformed(evt);
            }
        });

        jc_period.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7" }));

        jButton1.setText("SUBMIT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("PROCEED");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        priority_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SUBJECT", "DAY", "PERIOD"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        priority_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        priority_table.getTableHeader().setReorderingAllowed(false);
        priority_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                priority_tableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(priority_table);
        if (priority_table.getColumnModel().getColumnCount() > 0) {
            priority_table.getColumnModel().getColumn(0).setResizable(false);
            priority_table.getColumnModel().getColumn(1).setResizable(false);
            priority_table.getColumnModel().getColumn(2).setResizable(false);
        }

        b_del_prio.setText("DELETE");
        b_del_prio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_del_prioActionPerformed(evt);
            }
        });

        jLabel30.setText("To delete the entered priority ,select the row and click delete.");

        javax.swing.GroupLayout jp_priorityLayout = new javax.swing.GroupLayout(jp_priority);
        jp_priority.setLayout(jp_priorityLayout);
        jp_priorityLayout.setHorizontalGroup(
            jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_priorityLayout.createSequentialGroup()
                .addGroup(jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addGroup(jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jp_priorityLayout.createSequentialGroup()
                            .addGap(33, 33, 33)
                            .addGroup(jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jp_priorityLayout.createSequentialGroup()
                                    .addGroup(jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jc_days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(60, 60, 60)
                                    .addGroup(jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jc_sublist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(46, 46, 46)
                                    .addGroup(jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jp_priorityLayout.createSequentialGroup()
                                            .addComponent(jc_period, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(17, 17, 17))
                                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jp_priorityLayout.createSequentialGroup()
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(17, 17, 17)))
                            .addGap(28, 28, 28)
                            .addGroup(jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(b_del_prio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)))
                        .addGroup(jp_priorityLayout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jp_priorityLayout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(899, Short.MAX_VALUE))
        );

        jp_priorityLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {b_del_prio, jButton1});

        jp_priorityLayout.setVerticalGroup(
            jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_priorityLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jc_days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jc_sublist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jc_period, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jp_priorityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_del_prio)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap())
        );

        tp_1.addTab("PRIORITIES", jp_priority);

        displaytt.setEnabled(false);

        javax.swing.GroupLayout displayttLayout = new javax.swing.GroupLayout(displaytt);
        displaytt.setLayout(displayttLayout);
        displayttLayout.setHorizontalGroup(
            displayttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1097, Short.MAX_VALUE)
        );
        displayttLayout.setVerticalGroup(
            displayttLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 653, Short.MAX_VALUE)
        );

        jButton9.setText("GENERATE TIME TABLE");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton7.setText("SAVE THIS");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton10.setText("VIEW TIMETABLE");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jProgressBar1.setIndeterminate(true);

        javax.swing.GroupLayout tt_generated3Layout = new javax.swing.GroupLayout(tt_generated3);
        tt_generated3.setLayout(tt_generated3Layout);
        tt_generated3Layout.setHorizontalGroup(
            tt_generated3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tt_generated3Layout.createSequentialGroup()
                .addGroup(tt_generated3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tt_generated3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(179, 179, 179)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tt_generated3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(displaytt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(260, Short.MAX_VALUE))
            .addGroup(tt_generated3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tt_generated3Layout.createSequentialGroup()
                    .addGap(306, 306, 306)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(549, Short.MAX_VALUE)))
        );
        tt_generated3Layout.setVerticalGroup(
            tt_generated3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tt_generated3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(tt_generated3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(displaytt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
            .addGroup(tt_generated3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tt_generated3Layout.createSequentialGroup()
                    .addGap(419, 419, 419)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(419, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout timetableLayout = new javax.swing.GroupLayout(timetable);
        timetable.setLayout(timetableLayout);
        timetableLayout.setHorizontalGroup(
            timetableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timetableLayout.createSequentialGroup()
                .addComponent(tt_generated3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        timetableLayout.setVerticalGroup(
            timetableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timetableLayout.createSequentialGroup()
                .addComponent(tt_generated3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 35, Short.MAX_VALUE))
        );

        tp_1.addTab("TIMETABLE", timetable);

        jScrollPane1.setViewportView(tp_1);

        tab_container.add(jScrollPane1, "card2");

        javax.swing.GroupLayout jp_secLayout = new javax.swing.GroupLayout(jp_sec);
        jp_sec.setLayout(jp_secLayout);
        jp_secLayout.setHorizontalGroup(
            jp_secLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_secLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_secLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l_namesec, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(jp_secLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_secLayout.createSequentialGroup()
                        .addComponent(tf_namesec, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b_sbmt_secname))
                    .addComponent(tf_e_semname, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tab_container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1166, Short.MAX_VALUE)
        );
        jp_secLayout.setVerticalGroup(
            jp_secLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_secLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp_secLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_e_semname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_secLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_namesec)
                    .addComponent(tf_namesec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_sbmt_secname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tab_container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp3_sb5)
                    .addComponent(jp3_sb1))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jp3_sb2)
                    .addComponent(jp3_sb6))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jp3_sb7)
                        .addGap(52, 52, 52)
                        .addComponent(jp3_sb8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jp3_sb3)
                        .addGap(52, 52, 52)
                        .addComponent(jp3_sb4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp_sec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jp3_sb1)
                            .addComponent(jp3_sb2)
                            .addComponent(jp3_sb3))
                        .addGap(18, 18, 18)
                        .addComponent(jp3_sb6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jp3_sb4)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jp3_sb5)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jp3_sb7)
                                .addComponent(jp3_sb8)))))
                .addGap(26, 26, 26)
                .addComponent(jp_sec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cd2Layout = new javax.swing.GroupLayout(cd2);
        cd2.setLayout(cd2Layout);
        cd2Layout.setHorizontalGroup(
            cd2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        cd2Layout.setVerticalGroup(
            cd2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cd2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jp3_parent.add(cd2, "card3");

        javax.swing.GroupLayout generatettLayout = new javax.swing.GroupLayout(generatett);
        generatett.setLayout(generatettLayout);
        generatettLayout.setHorizontalGroup(
            generatettLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1183, Short.MAX_VALUE)
        );
        generatettLayout.setVerticalGroup(
            generatettLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1247, Short.MAX_VALUE)
        );

        jp3_parent.add(generatett, "card4");

        javax.swing.GroupLayout jp3Layout = new javax.swing.GroupLayout(jp3);
        jp3.setLayout(jp3Layout);
        jp3Layout.setHorizontalGroup(
            jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jp3_sp1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp3_parent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp3Layout.setVerticalGroup(
            jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp3_parent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jp3_sp1)
        );

        parentpane.add(jp3, "card4");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("PLEASE BLOCK THE TEACHERS WHO ARE TAKING FOR OTHER BRANCHES");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DR.G.T.RAJU", "DR.GIRIJAMMA H.A", "H R SHASHIDHAR", "DR.S SATISH KUMAR", "DR. KIRAN P", "HEMANTH S", "T.SATISH KUMAR", "BHAVANI SHANKAR.K", "ANJAN KUMAR K.N", "SUDHAMANI M.J", "GAYATRI V.KANADE", "SANJAY P KALAS", "MAHIMA M KATTI", "SWATHI G", "DEVARAJ B.M", "NANDINI N", "CHANDRASHEKAR B.S", "SAMPADA K.S", "KARANAM SUNIL KUMAR", "RAMYASHREE A N", "GOWRIMOL D", "MAREDDY SREELAKSHMI", "POORNIMA S", "RESHMA JAKABAL", "CHETAN D.S", "MEDHA GOURAYYA", "VIDYA Y" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel16.setText("TEACHER NAME");

        jPanel5.setLayout(new java.awt.GridLayout(6, 8, 2, 2));

        jLabel17.setText("MON");
        jPanel5.add(jLabel17);

        b_1_1.setText("--");
        b_1_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        b_1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_1_1ActionPerformed(evt);
            }
        });
        jPanel5.add(b_1_1);

        b_1_2.setText("--");
        b_1_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        b_1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_1_2ActionPerformed(evt);
            }
        });
        jPanel5.add(b_1_2);

        b_1_3.setText("--");
        b_1_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_1_3);

        b_1_4.setText("--");
        b_1_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_1_4);

        b_1_5.setText("--");
        b_1_5.setFocusTraversalPolicyProvider(true);
        b_1_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_1_5);

        b_1_6.setText("--");
        b_1_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_1_6);

        b_1_7.setText("--");
        b_1_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_1_7);

        jLabel18.setText("TUE");
        jPanel5.add(jLabel18);

        b_2_1.setText("--");
        b_2_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        b_2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_2_1ActionPerformed(evt);
            }
        });
        jPanel5.add(b_2_1);

        b_2_2.setText("--");
        b_2_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_2_2);

        b_2_3.setText("--");
        b_2_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_2_3);

        b_2_4.setText("--");
        b_2_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_2_4);

        b_2_5.setText("--");
        b_2_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_2_5);

        b_2_6.setText("--");
        b_2_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_2_6);

        b_2_7.setText("--");
        b_2_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_2_7);

        jLabel19.setText("WED");
        jPanel5.add(jLabel19);

        b_3_1.setText("--");
        b_3_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_3_1);

        b_3_2.setText("--");
        b_3_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        b_3_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_3_2ActionPerformed(evt);
            }
        });
        jPanel5.add(b_3_2);

        b_3_3.setText("--");
        b_3_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        b_3_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_3_3ActionPerformed(evt);
            }
        });
        jPanel5.add(b_3_3);

        b_3_4.setText("--");
        b_3_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_3_4);

        b_3_5.setText("--");
        b_3_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_3_5);

        b_3_6.setText("--");
        b_3_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_3_6);

        b_3_7.setText("--");
        b_3_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_3_7);

        jLabel21.setText("THU");
        jPanel5.add(jLabel21);

        b_4_1.setText("--");
        b_4_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_4_1);

        b_4_2.setText("--");
        b_4_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_4_2);

        b_4_3.setText("--");
        b_4_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_4_3);

        b_4_4.setText("--");
        b_4_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_4_4);

        b_4_5.setText("--");
        b_4_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_4_5);

        b_4_6.setText("--");
        b_4_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        b_4_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_4_6ActionPerformed(evt);
            }
        });
        jPanel5.add(b_4_6);

        b_4_7.setText("--");
        b_4_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_4_7);

        jLabel20.setText("FRI");
        jPanel5.add(jLabel20);

        b_5_1.setText("--");
        b_5_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_5_1);

        b_5_2.setText("--");
        b_5_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_5_2);

        b_5_3.setText("--");
        b_5_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_5_3);

        b_5_4.setText("--");
        b_5_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_5_4);

        b_5_5.setText("--");
        b_5_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        b_5_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_5_5ActionPerformed(evt);
            }
        });
        jPanel5.add(b_5_5);

        b_5_6.setText("--");
        b_5_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_5_6);

        b_5_7.setText("--");
        b_5_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_5_7);

        jLabel22.setText("SAT");
        jPanel5.add(jLabel22);

        b_6_1.setText("--");
        b_6_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_6_1);

        b_6_2.setText("--");
        b_6_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        b_6_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_6_2ActionPerformed(evt);
            }
        });
        jPanel5.add(b_6_2);

        b_6_3.setText("--");
        b_6_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_6_3);

        b_6_4.setText("--");
        b_6_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mouse_clicked_bta(evt);
            }
        });
        jPanel5.add(b_6_4);

        jLabel23.setText("1");

        b_ok.setText("OK");
        b_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_okActionPerformed(evt);
            }
        });

        b_next_teacher.setText("SAVE THIS");
        b_next_teacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_next_teacherActionPerformed(evt);
            }
        });

        b_done_jp5.setText("NEXT");
        b_done_jp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_done_jp5ActionPerformed(evt);
            }
        });

        jLabel24.setText("2");

        jLabel25.setText("3");

        jLabel26.setText("4");

        jLabel27.setText("5");

        jLabel28.setText("6");

        jLabel29.setText("7");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(74, 74, 74)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(b_ok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(159, 159, 159))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_done_jp5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_next_teacher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(jLabel23)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel25)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel28)
                        .addGap(68, 68, 68)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(636, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(b_ok)
                .addGap(71, 71, 71)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(b_next_teacher)
                .addGap(18, 18, 18)
                .addComponent(b_done_jp5)
                .addGap(262, 262, 262))
        );

        parentpane.add(jPanel3, "card5");

        jScrollPane2.setViewportView(parentpane);

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("EXIT");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem2.setText("Read Me");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);
        jMenu2.add(jSeparator1);

        jMenuItem3.setText("About");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1437, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b_spActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_spActionPerformed
        // TODO add your handling code here:
        jp1.setVisible(false);
        jPanel3.setVisible(true);
        bg = b_sp.getBackground();
        jTextField1.setText(null);
        fobj = new filereader();
        fobj.initialize_directories();


    }//GEN-LAST:event_b_spActionPerformed

    private void tf_nosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_nosMouseClicked
        // TODO add your handling code here:
        tf_nos.setForeground(Color.BLACK);
        tf_nos.setText(null);
    }//GEN-LAST:event_tf_nosMouseClicked

    private void tf_nosecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nosecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nosecActionPerformed

    private void jp2_b_nxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp2_b_nxtActionPerformed
        // TODO add your handling code here:

        try {
            tab_container.setVisible(true);
            tf_nos.setForeground(Color.BLACK);
            no_sem = Integer.parseInt(tf_nos.getText());
            if (no_sem > 8) {
                tf_nos.setForeground(Color.RED);
                tf_nos.setText("1<no.of sem<9");
                return;
            }
            sem = new semester[no_sem];
            for (int i = 0; i < no_sem; i++) {
                sem[i] = new semester();
            }
            flag_sem_details = true;
            sem_array = new int[no_sem];
            jp2.setVisible(false);
            jp3_b_submit.setVisible(false);
            jp3.setVisible(true);
            if (no_sem == 1) {
                sp1_b2.setVisible(false);
                sp1_b3.setVisible(false);
                sp1_b4.setVisible(false);
                sp1_b5.setVisible(false);
                sp1_b6.setVisible(false);
                sp1_b7.setVisible(false);
                sp1_b8.setVisible(false);
            } else if (no_sem == 2) {
                sp1_b3.setVisible(false);
                sp1_b4.setVisible(false);
                sp1_b5.setVisible(false);
                sp1_b6.setVisible(false);
                sp1_b7.setVisible(false);
                sp1_b8.setVisible(false);
            } else if (no_sem == 3) {
                sp1_b4.setVisible(false);
                sp1_b5.setVisible(false);
                sp1_b6.setVisible(false);
                sp1_b7.setVisible(false);
                sp1_b8.setVisible(false);
            } else if (no_sem == 4) {
                sp1_b5.setVisible(false);
                sp1_b6.setVisible(false);
                sp1_b7.setVisible(false);
                sp1_b8.setVisible(false);
            } else if (no_sem == 5) {
                sp1_b6.setVisible(false);
                sp1_b7.setVisible(false);
                sp1_b8.setVisible(false);
            } else if (no_sem == 6) {
                sp1_b7.setVisible(false);
                sp1_b8.setVisible(false);
            } else if (no_sem == 7) {
                sp1_b8.setVisible(false);
            }
            jp3_parent.setVisible(false);
            jp3_b_submit.setVisible(false);

        } catch (NumberFormatException ex) {
            tf_nos.setForeground(Color.RED);
            tf_nos.setText("enter a valid number");
        }

    }//GEN-LAST:event_jp2_b_nxtActionPerformed

    private void sp1_b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sp1_b1ActionPerformed
        // TODO add your handling code here:
        change_background(1);
        jScrollPane2.getHorizontalScrollBar().setValue(jScrollPane2.getHorizontalScrollBar().getValue() + 100);//position
        if (flag_sem_details) {
            save_prev();
            disp_current("sp1_b1");
            prev_btcn = "sp1_b1";
            if (check_complete()) {
                jp3_b_submit.setVisible(true);
            }
        } else {
            def_sec_bg();
            generatett.setVisible(false);
            disp_sec(0);
            jp_sec.setVisible(false);
            cd2.setVisible(true);
            sem_btn = "sp1_b1";
            tf_e_semname.setText(Integer.toString(sem_array[0]));
        }


    }//GEN-LAST:event_sp1_b1ActionPerformed

    private void sp1_b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sp1_b2ActionPerformed
        change_background(2);
        if (flag_sem_details) {
            save_prev();

            disp_current("sp1_b2");
            prev_btcn = "sp1_b2";
            if (check_complete()) {
                jp3_b_submit.setVisible(true);
            }

        } else {
            def_sec_bg();
            generatett.setVisible(false);
            disp_sec(1);
            jp_sec.setVisible(false);
            cd2.setVisible(true);
            sem_btn = "sp1_b2";
            tf_e_semname.setText(Integer.toString(sem_array[1]));
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_sp1_b2ActionPerformed

    private void sp1_b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sp1_b3ActionPerformed
        // TODO add your handling code here:
        change_background(3);
        if (flag_sem_details) {

            save_prev();
            disp_current("sp1_b3");
            prev_btcn = "sp1_b3";
            if (check_complete()) {
                jp3_b_submit.setVisible(true);
            }
        } else {
            def_sec_bg();
            generatett.setVisible(false);
            disp_sec(2);
            jp_sec.setVisible(false);
            cd2.setVisible(true);
            sem_btn = "sp1_b3";
            tf_e_semname.setText(Integer.toString(sem_array[2]));
        }

    }//GEN-LAST:event_sp1_b3ActionPerformed

    private void sp1_b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sp1_b4ActionPerformed
        change_background(4);
        if (flag_sem_details) {
            save_prev();
            disp_current("sp1_b4");
            prev_btcn = "sp1_b4";
            if (check_complete()) {
                jp3_b_submit.setVisible(true);
            }

        } else {
            def_sec_bg();
            generatett.setVisible(false);
            disp_sec(3);
            jp_sec.setVisible(false);
            cd2.setVisible(true);
            sem_btn = "sp1_b4";
            tf_e_semname.setText(Integer.toString(sem_array[3]));
        }

    }//GEN-LAST:event_sp1_b4ActionPerformed

    private void sp1_b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sp1_b5ActionPerformed
        // TODO add your handling code here:
        change_background(5);
        if (flag_sem_details) {
            save_prev();
            disp_current("sp1_b5");
            prev_btcn = "sp1_b5";
            if (check_complete()) {
                jp3_b_submit.setVisible(true);
            }

        } else {
            def_sec_bg();
            generatett.setVisible(false);
            disp_sec(4);
            jp_sec.setVisible(false);
            cd2.setVisible(true);
            sem_btn = "sp1_b5";
            tf_e_semname.setText(Integer.toString(sem_array[4]));
        }

    }//GEN-LAST:event_sp1_b5ActionPerformed

    private void sp1_b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sp1_b6ActionPerformed
        change_background(6);
        if (flag_sem_details) {
            save_prev();
            disp_current("sp1_b6");
            prev_btcn = "sp1_b6";
            if (check_complete()) {
                jp3_b_submit.setVisible(true);
            }

        } else {
            def_sec_bg();
            generatett.setVisible(false);
            disp_sec(5);
            jp_sec.setVisible(false);
            cd2.setVisible(true);
            sem_btn = "sp1_b6";
            tf_e_semname.setText(Integer.toString(sem_array[5]));
        }


    }//GEN-LAST:event_sp1_b6ActionPerformed

    private void sp1_b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sp1_b7ActionPerformed
        change_background(7);
        if (flag_sem_details) {
            save_prev();
            disp_current("sp1_b7");
            prev_btcn = "sp1_b7";
            if (check_complete()) {
                jp3_b_submit.setVisible(true);
            }

        } else {
            def_sec_bg();
            generatett.setVisible(false);
            disp_sec(6);
            jp_sec.setVisible(false);
            cd2.setVisible(true);
            sem_btn = "sp1_b7";
            tf_e_semname.setText(Integer.toString(sem_array[6]));
        }

    }//GEN-LAST:event_sp1_b7ActionPerformed

    private void sp1_b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sp1_b8ActionPerformed
        change_background(8);
        if (flag_sem_details) {
            save_prev();
            disp_current("sp1_b8");

            prev_btcn = "sp1_b8";
            if (check_complete()) {
                jp3_b_submit.setVisible(true);
            }

        } else {
            def_sec_bg();
            //
            //
            generatett.setVisible(false);
            disp_sec(7);
            jp_sec.setVisible(false);
            cd2.setVisible(true);
            sem_btn = "sp1_b8";
            tf_e_semname.setText(Integer.toString(sem_array[7]));
        }

    }//GEN-LAST:event_sp1_b8ActionPerformed

    private void tf_semnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_semnameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_semnameKeyPressed

    private void jp3_b_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp3_b_submitActionPerformed
        // TODO add your handling code here:
        boolean rep_flag = false;
        save_prev();

        for (int i = 0; i < no_sem; i++) {
            for (int j = 0; j < no_sem; j++) {
                if (i != j && sem_array[i] == sem_array[j]) {
                    JOptionPane.showMessageDialog(null, "SORRY, TWO SEMESTERS HAVE THE SAME NAME , PLEASE CHECK.");
                    rep_flag = true;
                    break;
                }

            }
            if (rep_flag == true) {
                break;
            }
        }
        for (int i = 0; i < no_sem; i++) {
            if (sem[i].n > 8) {
                JOptionPane.showMessageDialog(null, "SORRY,MAXIMUM NUMBER OF SECTIONS ALLOWED = 8");
                rep_flag = true;
                break;
            }
        }

        if (rep_flag == false) {
            cd1.setVisible(false);
            flag_sem_details = false;
            generatett.setVisible(true);
            def_sem_bg();

            for (int i = 0; i < no_sem; i++) {
                sem[i].sec = new section[sem[i].n];
                sem[i].sect_array = new String[sem[i].n];//to store names of section of respective semeseters
                for (int j = 0; j < sem[i].n; j++) {
                    sem[i].sect_array[j] = "sec_name";
                    sem[i].sec[j] = new section();
                }
            }
        }
    }//GEN-LAST:event_jp3_b_submitActionPerformed

    private void s_tf6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s_tf6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_s_tf6ActionPerformed

    private void b_customActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_customActionPerformed
        // TODO add your handling code here:
        jp7.setVisible(false);
        jp_cust.setVisible(true);
    }//GEN-LAST:event_b_customActionPerformed

    private void jp3_sb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp3_sb1ActionPerformed
        // TODO add your handling code here:
        sec_btn = "jp3_sb1";

        section_code(1);
    }//GEN-LAST:event_jp3_sb1ActionPerformed

    private void jp3_sb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp3_sb2ActionPerformed
        // TODO add your handling code here:
        sec_btn = "jp3_sb2";
        section_code(2);

    }//GEN-LAST:event_jp3_sb2ActionPerformed

    private void jp3_sb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp3_sb3ActionPerformed
        // TODO add your handling code here:
        sec_btn = "jp3_sb3";
        section_code(3);
    }//GEN-LAST:event_jp3_sb3ActionPerformed

    private void jp3_sb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp3_sb4ActionPerformed
        // TODO add your handling code here:
        sec_btn = "jp3_sb4";
        section_code(4);
    }//GEN-LAST:event_jp3_sb4ActionPerformed

    private void jp3_sb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp3_sb5ActionPerformed
        // TODO add your handling code here:
        sec_btn = "jp3_sb5";
        section_code(5);
    }//GEN-LAST:event_jp3_sb5ActionPerformed

    private void jp3_sb6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp3_sb6ActionPerformed
        // TODO add your handling code here:
        sec_btn = "jp3_sb6";
        section_code(6);
    }//GEN-LAST:event_jp3_sb6ActionPerformed

    private void jp3_sb7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp3_sb7ActionPerformed
        // TODO add your handling code here:
        sec_btn = "jp3_sb7";
        section_code(7);

    }//GEN-LAST:event_jp3_sb7ActionPerformed

    private void jp3_sb8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp3_sb8ActionPerformed
        // TODO add your handling code here
        sec_btn = "jp3_sb8";
        section_code(8);

    }//GEN-LAST:event_jp3_sb8ActionPerformed

    private void b_prescribedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_prescribedActionPerformed
        try {
            // TODO add your handling code here:
            jp_cust.setVisible(false);
            jp7.setVisible(false);
            //ta_sublist.setEditable(false);
            jp_pres.setVisible(true);
            get_semnsec();
            sem[sem_i - 1].sec[sec_i - 1].nos = 6;
            fobj = new filereader();
            fobj.f_temp_load(Integer.toString(sem_array[Integer.parseInt(sem_btn.substring(5)) - 1]));
            //ta_sublist.setText(null);
            for (int i = 0; i < 6; i++) {
                // ta_sublist.append(Integer.toString(i+1)+":  "+fobj.temp[i][0]+"\t"+fobj.temp[i][1]+"\n");    
            }

        } catch (IOException ex) {
            Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_b_prescribedActionPerformed

    private void tf_namesecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_namesecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_namesecActionPerformed

    private void b_sbmt_secnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sbmt_secnameActionPerformed
        // TODO add your handling code here:
        get_semnsec();

        String s = tf_namesec.getText();
        if (s.equals("") || s.equals("error")) {
            tf_namesec.setForeground(Color.RED);
            tf_namesec.setText("error");
            return;
        }
        boolean f = false;
        for (int i = 0; i < sem[sem_i - 1].n; i++) {
            if (sem[sem_i - 1].sect_array[i].equals(s)) {
                f = true;
                JOptionPane.showMessageDialog(null, "Two sections cannot have same name");
                break;
            }
        }
        if (f == false) {
            sem[sem_i - 1].sect_array[sec_i - 1] = tf_namesec.getText();
        }


    }//GEN-LAST:event_b_sbmt_secnameActionPerformed

    private void tf_s1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_s1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_s1ActionPerformed

    private void tf_s2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_s2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_s2ActionPerformed

    private void tf_s4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_s4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_s4ActionPerformed

    private void tf_s10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_s10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_s10ActionPerformed

    private void tf_s8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_s8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_s8ActionPerformed

    private void tf_s6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_s6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_s6ActionPerformed

    private void tf_s9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_s9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_s9ActionPerformed

    private void jc_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_1ActionPerformed
        // TODO add your handling code here
        teach_name = (String) jc_1.getSelectedItem();
        tf_t1.setText(teach_name);

    }//GEN-LAST:event_jc_1ActionPerformed

    private void tf_noc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_noc3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_noc3ActionPerformed

    private void b_jpcust_submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_jpcust_submitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_jpcust_submitActionPerformed

    private void tf_noc1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_noc1MouseClicked
        // TODO add your handling code here:
        tf_noc1.setForeground(Color.BLACK);
        tf_noc1.setText(null);
    }//GEN-LAST:event_tf_noc1MouseClicked

    private void tf_namesecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_namesecMouseClicked
        // TODO add your handling code here:
        tf_namesec.setForeground(Color.BLACK);
        tf_namesec.setText(null);
    }//GEN-LAST:event_tf_namesecMouseClicked

    private void tf_semnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_semnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_semnameActionPerformed

    private void tf_noc2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_noc2MouseClicked
        // TODO add your handling code here:
        tf_noc2.setForeground(Color.BLACK);
        tf_noc2.setText(null);
    }//GEN-LAST:event_tf_noc2MouseClicked

    private void tf_noc3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_noc3MouseClicked
        // TODO add your handling code here:
        tf_noc3.setForeground(Color.BLACK);
        tf_noc3.setText(null);
    }//GEN-LAST:event_tf_noc3MouseClicked

    private void tf_noc4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_noc4MouseClicked

        // TODO add your handling code here:
        tf_noc4.setForeground(Color.BLACK);
        tf_noc4.setText(null);
    }//GEN-LAST:event_tf_noc4MouseClicked

    private void tf_noc5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_noc5MouseClicked
        // TODO add your handling code here:

        tf_noc5.setForeground(Color.BLACK);
        tf_noc5.setText(null);
    }//GEN-LAST:event_tf_noc5MouseClicked

    private void tf_noc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_noc4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_noc4ActionPerformed

    private void tf_noc6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_noc6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_noc6ActionPerformed

    private void tf_noc6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_noc6MouseClicked
        // TODO add your handling code here:
        tf_noc6.setForeground(Color.BLACK);
        tf_noc6.setText(null);
    }//GEN-LAST:event_tf_noc6MouseClicked

    private void tf_noc7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_noc7MouseClicked
        // TODO add your handling code here:
        tf_noc7.setForeground(Color.BLACK);
        tf_noc7.setText(null);
    }//GEN-LAST:event_tf_noc7MouseClicked

    private void tf_noc8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_noc8MouseClicked
        // TODO add your handling code here:
        tf_noc8.setForeground(Color.BLACK);
        tf_noc8.setText(null);
    }//GEN-LAST:event_tf_noc8MouseClicked

    private void tf_noc9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_noc9MouseClicked
        // TODO add your handling code here:
        tf_noc9.setForeground(Color.BLACK);
        tf_noc9.setText(null);
    }//GEN-LAST:event_tf_noc9MouseClicked

    private void tf_noc10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_noc10MouseClicked
        // TODO add your handling code here:
        tf_noc10.setForeground(Color.BLACK);
        tf_noc10.setText(null);
    }//GEN-LAST:event_tf_noc10MouseClicked

    private void tf_noc6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_noc6MouseReleased
        // TODO add your handling code here

    }//GEN-LAST:event_tf_noc6MouseReleased

    private void jc_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_2ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) jc_2.getSelectedItem();
        tf_t2.setText(teach_name);
    }//GEN-LAST:event_jc_2ActionPerformed

    private void jc_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_3ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) jc_3.getSelectedItem();
        tf_t3.setText(teach_name);
    }//GEN-LAST:event_jc_3ActionPerformed

    private void tf_t4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_t4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_t4ActionPerformed

    private void tf_t3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_t3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_t3ActionPerformed

    private void tf_t1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_t1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_t1ActionPerformed

    private void jc_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_4ActionPerformed
        // TODO add your handling code here
        teach_name = (String) jc_4.getSelectedItem();
        tf_t4.setText(teach_name);
    }//GEN-LAST:event_jc_4ActionPerformed

    private void jc_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_5ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) jc_5.getSelectedItem();
        tf_t5.setText(teach_name);
    }//GEN-LAST:event_jc_5ActionPerformed

    private void jc_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_6ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) jc_6.getSelectedItem();
        tf_t6.setText(teach_name);
    }//GEN-LAST:event_jc_6ActionPerformed

    private void jc_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_7ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) jc_7.getSelectedItem();
        tf_t7.setText(teach_name);
    }//GEN-LAST:event_jc_7ActionPerformed

    private void jc_8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_8ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) jc_8.getSelectedItem();
        tf_t8.setText(teach_name);
    }//GEN-LAST:event_jc_8ActionPerformed

    private void jc_9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_9ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) jc_9.getSelectedItem();
        tf_t9.setText(teach_name);
    }//GEN-LAST:event_jc_9ActionPerformed

    private void jc_10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_10ActionPerformed
        teach_name = (String) jc_10.getSelectedItem();
        tf_t10.setText(teach_name);
    }//GEN-LAST:event_jc_10ActionPerformed

    private void bdone_subdetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bdone_subdetActionPerformed
        // TODO add your handling code here:
        get_semnsec();
        int free_classes = 39 - (sem[sem_i - 1].sec[sec_i - 1].nol_pw * 3);
        int req_classes = 0;

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int res = JOptionPane.showConfirmDialog(null, "Are you sure ,you want to proceed!", "CONFIRM", dialogButton);

        if (res == JOptionPane.YES_OPTION) {
            sem[sem_i - 1].sec[sec_i - 1].subdone = true;
            if (priority_lock == null) {
                priority_lock = Integer.toString(sem_i) + Integer.toString(sec_i);
            }

            try {
                //loading the no. of classes from text fields.
                get_noc_tname();
                filecreate_presc();
                String tss;//temporary sem sec
                tss = Integer.toString(sem_i) + Integer.toString(sec_i);
                for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
                    if (sem[sem_i - 1].sec[sec_i - 1].s[i].t_name.equals("") || sem[sem_i - 1].sec[sec_i - 1].s[i].t_name.equals(null)) {
                        JOptionPane.showMessageDialog(null, "TEACHER NAME EXPECTED");
                        return;
                    }
                }
                for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
                    req_classes = req_classes + sem[sem_i - 1].sec[sec_i - 1].s[i].noc;

                }
                if (req_classes > free_classes) {
                    JOptionPane.showMessageDialog(null, "REQUEST DENIED : REQUESTED CLASSES > AVAILABLE FREE CLASSES");
                    return;
                }

                if (tss.equals(priority_lock)) {
                    tp_1.setEnabledAt(3, true);
                    tp_1.setSelectedIndex(3);
                } else {
                    JOptionPane.showMessageDialog(null, "please complete entering the priorities of " + sem_array[Integer.parseInt(priority_lock.substring(0, 0)) - 1] + "sem"
                            + sem[Integer.parseInt(priority_lock.substring(0, 0)) - 1].sect_array[Integer.parseInt(priority_lock.substring(1, 1)) - 1] + "section");
                    System.out.println("priority_lock =" + priority_lock);

                }
                produce_sublist();// for priorities
                block_sec_buttons();
                block_sem_buttons();
                sem[sem_i - 1].sec[sec_i - 1].priority_buff = new String[30][3];
                disable_buttons(cd1_subdetails);
                enable_buttons(jp_priority);

            } catch (NumberFormatException ex) {
                //write a toast message that says enter appropriate data in order to proceed 
                JOptionPane.showMessageDialog(null, "NUMERIC DATA EXPECTED ");
            }

        }


    }//GEN-LAST:event_bdone_subdetActionPerformed

    private void tf_l2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_l2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_l2ActionPerformed

    private void tf_ln1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_ln1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_ln1ActionPerformed

    private void ljc_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ljc_1ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) ljc_1.getSelectedItem();
        tf_ln1.setText(teach_name);
    }//GEN-LAST:event_ljc_1ActionPerformed

    private void ljc_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ljc_3ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) ljc_3.getSelectedItem();
        tf_ln3.setText(teach_name);
    }//GEN-LAST:event_ljc_3ActionPerformed

    private void tf_ln2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_ln2ActionPerformed
        teach_name = (String) ljc_2.getSelectedItem();
        tf_ln2.setText(teach_name);        // TODO add your handling code here:
    }//GEN-LAST:event_tf_ln2ActionPerformed

    private void tf_ln3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_ln3ActionPerformed
        // TODO add your handling code here:

        teach_name = (String) ljc_3.getSelectedItem();
        tf_ln3.setText(teach_name);
    }//GEN-LAST:event_tf_ln3ActionPerformed

    private void tf_ln4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_ln4ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) ljc_4.getSelectedItem();
        tf_ln4.setText(teach_name);
    }//GEN-LAST:event_tf_ln4ActionPerformed

    private void b_nolsubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_nolsubActionPerformed
        // TODO add your handling code here:

        get_semnsec();
        String name;
        name = tf_namesec.getText();
        if (sem[sem_i - 1].sect_array[sec_i - 1].equals("sec_name")) {
            JOptionPane.showMessageDialog(null, "PLEASE ENTER SECTION NAME TO PROCEED");
        } else {
            try {
                sem[sem_i - 1].sec[sec_i - 1].nol = Integer.parseInt(tf_nol.getText());
                for (int i = 0; i < 4; i++) {
                    sem[sem_i - 1].sec[sec_i - 1].l[i] = new lab();
                    sem[sem_i - 1].sec[sec_i - 1].l[i].l_teach[0] = new String();
                }
                //code to display the tetxt fields based on number of labs
                display_lab_fields();

            } catch (NumberFormatException ex) {
                tf_nol.setForeground(Color.red);
                tf_nol.setText("enter a valid number");
                tf_nol.setForeground(Color.BLACK);
            }

        }

    }//GEN-LAST:event_b_nolsubActionPerformed

    private void b_saveldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_saveldActionPerformed
        // TODO add your handling code here:

        get_semnsec();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int res = JOptionPane.showConfirmDialog(null, "Are you sure ,you want to proceed!", "CONFIRM", dialogButton);

        if (res == JOptionPane.YES_OPTION) {
            if (sem[sem_i - 1].sec[sec_i - 1].nol == 1) {
                sem[sem_i - 1].sec[sec_i - 1].l[0].l_code = tf_l1.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[0].l_teach[0] = tf_ln1.getText();
            } else if (sem[sem_i - 1].sec[sec_i - 1].nol == 2) {
                sem[sem_i - 1].sec[sec_i - 1].l[0].l_code = tf_l1.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[0].l_teach[0] = tf_ln1.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[1].l_code = tf_l2.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[1].l_teach[0] = tf_ln2.getText();
            } else if (sem[sem_i - 1].sec[sec_i - 1].nol == 3) {
                sem[sem_i - 1].sec[sec_i - 1].l[0].l_code = tf_l1.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[0].l_teach[0] = tf_ln1.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[1].l_code = tf_l2.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[1].l_teach[0] = tf_ln2.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[2].l_code = tf_l3.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[2].l_teach[0] = tf_ln3.getText();
            } else if (sem[sem_i - 1].sec[sec_i - 1].nol == 4) {
                sem[sem_i - 1].sec[sec_i - 1].l[0].l_code = tf_l1.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[0].l_teach[0] = tf_ln1.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[1].l_code = tf_l2.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[1].l_teach[0] = tf_ln2.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[2].l_code = tf_l3.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[2].l_teach[0] = tf_ln3.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[3].l_code = tf_l4.getText();
                sem[sem_i - 1].sec[sec_i - 1].l[3].l_teach[0] = tf_ln4.getText();
            }
            sem[sem_i - 1].sec[sec_i - 1].lab_save = true;
            cd1_ld.setVisible(false);
            cd2_ld.setVisible(true);
            flush_rows(tble_lp);

            block_sec_buttons();
            block_sem_buttons();
            disable_buttons(cd1_ld);
            enable_buttons(cd2_ld);
            b_saveld.setEnabled(false);
            b_nolsub.setEnabled(false);
        }

       // clearTable(tble_lp);

    }//GEN-LAST:event_b_saveldActionPerformed

    private void tf_nolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nolKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tf_nolKeyTyped

    private void sp1_b1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sp1_b1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_sp1_b1MouseClicked

    private void jp3_sb1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp3_sb1MouseReleased
        // TODO add your handling code here:


    }//GEN-LAST:event_jp3_sb1MouseReleased

    private void jp3_sb1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jp3_sb1MousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jp3_sb1MousePressed

    private void b_sub_lpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sub_lpActionPerformed
        // TODO add your handling code here:

        get_semnsec();
        boolean tf = false;
        dm = (DefaultTableModel) tble_lp.getModel();
        if (sem[sem_i - 1].sec[sec_i - 1].nol_pw == -1) {
            JOptionPane.showMessageDialog(null, "Please enter the number of labs per week before entering lab details.");
        } else if (sem[sem_i - 1].sec[sec_i - 1].count_nol_pw < sem[sem_i - 1].sec[sec_i - 1].nol_pw) {

            for (int i = 0; i < dm.getRowCount(); i++) {
                if (dm.getValueAt(i, 0) == cb_labday.getSelectedItem()) {
                    JOptionPane.showMessageDialog(null, "Invalid data entered! No two labs can be on same day.");
                    tf = true;
                    break;
                }
            }
            int d;
            if (tf == false) {

                /*      if (cb_strtprd.getSelectedIndex() != 0 || cb_strtprd.getSelectedIndex() != 4) {
                 JOptionPane.showMessageDialog(null, "Invalid period! Input 1st or 3rd period.");
                 return;
                 }*/
                /* tble_lp.setValueAt(sem[sem_i - 1].sec[sec_i - 1].count_nol_pw, sem[sem_i - 1].sec[sec_i - 1].count_nol_pw - 1, 0);
                 tble_lp.setValueAt(cb_labday.getSelectedItem(), sem[sem_i - 1].sec[sec_i - 1].count_nol_pw - 1, 1);
                 tble_lp.setValueAt(cb_strtprd.getSelectedItem(), sem[sem_i - 1].sec[sec_i - 1].count_nol_pw - 1, 2);*/
                // d = sem[sem_i - 1].sec[sec_i - 1].count_nol_pw;
              /*  sem[sem_i - 1].sec[sec_i - 1].sp_day[d - 1][0] = cb_labday.getSelectedIndex();
                 sem[sem_i - 1].sec[sec_i - 1].sp_day[d - 1][1] = cb_strtprd.getSelectedIndex();*/
                for (int j = 0; j < sem[sem_i - 1].sec[sec_i - 1].nol; j++) {
                    try {
                        sem[sem_i - 1].sec[sec_i - 1].read_lt(sem_array[sem_i - 1], sem[sem_i - 1].sect_array[sec_i - 1],
                                sem[sem_i - 1].sec[sec_i - 1].l[j].l_teach[0],
                                cb_labday.getSelectedIndex(),
                                cb_strtprd.getSelectedIndex(), sem[sem_i - 1].sec[sec_i - 1].l[j].l_code);
                    } catch (IOException ex) {
                        Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (sem[sem_i - 1].sec[sec_i - 1].ftc) {
                    //  sem[sem_i - 1].sec[sec_i - 1].count_nol_pw--;
                    for (int j = 0; j < sem[sem_i - 1].sec[sec_i - 1].nol; j++) {
                        try {

                            sem[sem_i - 1].sec[sec_i - 1].lab_restor(sem_array[sem_i - 1], sem[sem_i - 1].sect_array[sec_i - 1],
                                    sem[sem_i - 1].sec[sec_i - 1].l[j].l_teach[0],
                                    cb_labday.getSelectedIndex(),
                                    cb_strtprd.getSelectedIndex(), sem[sem_i - 1].sec[sec_i - 1].l[j].l_code);
                        } catch (IOException ex) {
                            Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else// that is once sure that a the selected day and period is valid add row or else dont
                {
                    sem[sem_i - 1].sec[sec_i - 1].sp_day[sem[sem_i - 1].sec[sec_i - 1].count_nol_pw][0] = (String) cb_labday.getSelectedItem();
                    sem[sem_i - 1].sec[sec_i - 1].sp_day[sem[sem_i - 1].sec[sec_i - 1].count_nol_pw][1] = (String) cb_strtprd.getSelectedItem();
                    sem[sem_i - 1].sec[sec_i - 1].sp_day_p[sem[sem_i - 1].sec[sec_i - 1].count_nol_pw][0] = cb_labday.getSelectedIndex();
                    sem[sem_i - 1].sec[sec_i - 1].sp_day_p[sem[sem_i - 1].sec[sec_i - 1].count_nol_pw][1] = cb_strtprd.getSelectedIndex();
                    addrow("no_sub", (String) cb_labday.getSelectedItem(), (String) cb_strtprd.getSelectedItem(), tble_lp);

                }

            }
        } else if (sem[sem_i - 1].sec[sec_i - 1].count_nol_pw >= sem[sem_i - 1].sec[sec_i - 1].nol_pw) {
            JOptionPane.showMessageDialog(null, "you have entered all the lab details.");
        }


    }//GEN-LAST:event_b_sub_lpActionPerformed

    private void tf_noc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_noc2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_noc2ActionPerformed

    private void tf_nolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_nolMouseClicked
        // TODO add your handling code here:
        tf_nol.setText(null);
    }//GEN-LAST:event_tf_nolMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        get_semnsec();
        if (Integer.parseInt(tf_nol_pw.getText()) < sem[sem_i - 1].sec[sec_i - 1].nol) {
            JOptionPane.showMessageDialog(null, "Invalid input, must be atleast equal to number of labs entererd : " + sem[sem_i - 1].sec[sec_i - 1].nol);
            return;
        }
        if (Integer.parseInt(tf_nol_pw.getText()) > 6) {
            JOptionPane.showMessageDialog(null, "please leave the software ASAP!,6 working days and "
                    + tf_nol_pw.getText() + " labs seriously??");
            return;
        }
        try {

            sem[sem_i - 1].sec[sec_i - 1].nol_pw = Integer.parseInt(tf_nol_pw.getText());
            sem[sem_i - 1].sec[sec_i - 1].sp_day = new String[sem[sem_i - 1].sec[sec_i - 1].nol_pw][2];
            sem[sem_i - 1].sec[sec_i - 1].sp_day_p = new int[sem[sem_i - 1].sec[sec_i - 1].nol_pw][2];
        } catch (NumberFormatException ex) {
            tf_nol_pw.setForeground(Color.red);
            tf_nol_pw.setText("enter a valid number");
            tf_nol_pw.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        get_semnsec();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int res = JOptionPane.showConfirmDialog(null, "Are you sure ,you want to proceed!", "CONFIRM", dialogButton);

        if (res == JOptionPane.YES_OPTION) {
            if (sem[sem_i - 1].sec[sec_i - 1].count_nol_pw != sem[sem_i - 1].sec[sec_i - 1].nol_pw) {
                JOptionPane.showMessageDialog(null, "Enter all the lab details.");

            } else {
                upd_prty();
                sem[sem_i - 1].sec[sec_i - 1].labdone = true;
                tp_1.setEnabledAt(1, true);
                tp_1.setSelectedIndex(1);
                fobj = new filereader();

              //  clearTable(tble_sublist);
                try {

                    produce_sub_table();
                    enable_sec_buttons();
                    enable_sem_buttons();
                    //flush_rows(tble_lp);
                    disable_buttons(cd2_ld);
                    disable_buttons(cd1_ld);

                    enable_buttons(jp_pres);
                    sublist_sub.setEnabled(false);

                    cd2_ld.setVisible(false);
                    jp_pres.setVisible(true);


                    /*   for (int l = 0; l < sem[sem_i - 1].sec[sec_i - 1].nol_pw; l++) {
                     sem[sem_i - 1].sec[sec_i - 1].sp_day[l][0] = get_day_index((String) tble_lp.getValueAt(l, 1));
                     sem[sem_i - 1].sec[sec_i - 1].sp_day[l][1] = (Integer.parseInt((String)tble_lp.getValueAt(l, 2))-1);
                     }
                
                     for (int l = 0; l < sem[sem_i - 1].sec[sec_i - 1].nol_pw; l++) {
                     for (int j = 0; j < sem[sem_i - 1].sec[sec_i - 1].nol; j++) {
                     try {
                     if (sem[sem_i - 1].sec[sec_i - 1].ftc == false) {
                     sem[sem_i - 1].sec[sec_i - 1].read_lt(sem_array[sem_i - 1], sem[sem_i - 1].sect_array[sec_i - 1],
                     sem[sem_i - 1].sec[sec_i - 1].l[j].l_teach[0],
                     sem[sem_i - 1].sec[sec_i - 1].sp_day[l][0], sem[sem_i - 1].sec[sec_i - 1].sp_day[l][1]);
                     }
                
                     } catch (IOException ex) {
                     Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     }
                
                     }
                     if (sem[sem_i - 1].sec[sec_i - 1].ftc) {
                     for (int l = 0; l < sem[sem_i - 1].sec[sec_i - 1].nol_pw; l++) {
                     for (int j = 0; j < sem[sem_i - 1].sec[sec_i - 1].nol; j++) {
                     try {
                
                     sem[sem_i - 1].sec[sec_i - 1].lab_restor(sem_array[sem_i - 1], sem[sem_i - 1].sect_array[sec_i - 1], sem[sem_i - 1].sec[sec_i - 1].l[j].l_teach[0],
                     sem[sem_i - 1].sec[sec_i - 1].sp_day[l][0], sem[sem_i - 1].sec[sec_i - 1].sp_day[l][1]);
                
                     } catch (IOException ex) {
                     Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     }
                
                     }
                     }*/
                } catch (IOException ex) {
                    Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void ljc_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ljc_2ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) ljc_2.getSelectedItem();
        tf_ln2.setText(teach_name);
    }//GEN-LAST:event_ljc_2ActionPerformed

    private void ljc_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ljc_4ActionPerformed
        // TODO add your handling code here:
        teach_name = (String) ljc_4.getSelectedItem();
        tf_ln4.setText(teach_name);
    }//GEN-LAST:event_ljc_4ActionPerformed

    private void tble_lpMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tble_lpMouseReleased
        // TODO add your handling code here:
        get_semnsec();
        if (tble_lp.getSelectedRow() != -1 && tble_lp.getSelectedRow() < sem[sem_i - 1].sec[sec_i - 1].nol_pw) {
            tble_lp.add(table_select, tble_lp.getSelectedRow());
        }
    }//GEN-LAST:event_tble_lpMouseReleased

    private void tble_lpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tble_lpMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tble_lpMouseClicked

    private void DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEActionPerformed
        // TODO add your handling code here:
        get_semnsec();


    }//GEN-LAST:event_DELETEActionPerformed

    private void tf_nolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_nolActionPerformed

    private void tf_l3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_l3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_l3ActionPerformed

    private void tf_l4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_l4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_l4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        boolean tf = false;
        int index, day, period;
        get_semnsec();

        if (jc_days.getSelectedItem() == "SAT" && Integer.parseInt((String) jc_period.getSelectedItem()) > 4) {
            JOptionPane.showMessageDialog(null, "Invalid input");
            return;
        }

        for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].count_no_priority - 1; i++) {
            if (priority_table.getValueAt(i, 0) == jc_days.getSelectedItem()
                    && priority_table.getValueAt(i, 2) == jc_period.getSelectedItem()) {
                JOptionPane.showMessageDialog(null, "priority conflict! two priorities given at the same day and period");
                tf = true;
                break;
            }
        }
        if (tf == false) {

            /*  priority_table.setValueAt(jc_days.getSelectedItem(), sem[sem_i - 1].sec[sec_i - 1].count_no_priority, 1);
             priority_table.setValueAt(jc_sublist.getSelectedItem(), sem[sem_i - 1].sec[sec_i - 1].count_no_priority, 2);
             priority_table.setValueAt(jc_period.getSelectedItem(), sem[sem_i - 1].sec[sec_i - 1].count_no_priority, 3);
             priority_table.setValueAt(sem[sem_i - 1].sec[sec_i - 1].count_no_priority + 1, sem[sem_i - 1].sec[sec_i - 1].count_no_priority, 0);*/
            index = jc_sublist.getSelectedIndex();
            day = jc_days.getSelectedIndex();
            period = jc_period.getSelectedIndex();
            if (sem[sem_i - 1].sec[sec_i - 1].s[index].sc == sem[sem_i - 1].sec[sec_i - 1].s[index].noc) {
                JOptionPane.showMessageDialog(null, "PRIORITY DENIED : YOU'LL EXCEED THE NUMBER OF CLASSES PER WEEK");
                return;
            }

            try {
                if (class_free(day, period, index)) {
                    addrow((String) jc_sublist.getSelectedItem(),
                            (String) jc_days.getSelectedItem(), (String) jc_period.getSelectedItem(), priority_table);
                    sem[sem_i - 1].sec[sec_i - 1].priority_buff[sem[sem_i - 1].sec[sec_i - 1].count_no_priority][0] = (String) jc_sublist.getSelectedItem();
                    sem[sem_i - 1].sec[sec_i - 1].priority_buff[sem[sem_i - 1].sec[sec_i - 1].count_no_priority][1] = (String) jc_days.getSelectedItem();
                    sem[sem_i - 1].sec[sec_i - 1].priority_buff[sem[sem_i - 1].sec[sec_i - 1].count_no_priority][2] = (String) jc_period.getSelectedItem();
                    sem[sem_i - 1].sec[sec_i - 1].count_no_priority++;
                    update_sub_priority(index, day, period);
                    //   fobj.update_tempload(sem[sem_i - 1].sec[sec_i - 1].s[index].t_name);
                    // fobj.update_temparray(sem[sem_i - 1].sec[sec_i - 1].s[index].t_name, day, period, sem[sem_i - 1].sec[sec_i - 1].s[index].subcode,
                    //       sem_array[sem_i - 1], sem[sem_i - 1].sect_array[sec_i - 1]);
                    String st = sem[sem_i - 1].sec[sec_i - 1].s[index].subcode;
                    fobj.temp[day][period] = Integer.toString(sem_array[sem_i - 1]) + sem[sem_i - 1].sect_array[sec_i - 1] + "-" + st;
                    fobj.update_teachfile(sem[sem_i - 1].sec[sec_i - 1].s[index].t_name);

                }
            } catch (IOException ex) {
                Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);

            }
    }//GEN-LAST:event_jButton1ActionPerformed
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        get_semnsec();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int res = JOptionPane.showConfirmDialog(null, "Are you sure ,you want to proceed!", "CONFIRM", dialogButton);

        if (res == JOptionPane.YES_OPTION) {
            sem[sem_i - 1].sec[sec_i - 1].prioritydone = true;
            priority_lock = null;
            printo();
            //tp_1.setVisible(false);
            tp_1.setEnabledAt(4, true);
            tp_1.setSelectedIndex(4);
            try {
                save_details();// saving sub counts for regeneration
            } catch (IOException ex) {
                Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
            }
            enable_sec_buttons();
            enable_sem_buttons();
            //     flush_rows(priority_table);
            disable_buttons(jp_priority);
            jProgressBar1.setVisible(false);
            jButton7.setVisible(false);
            jButton10.setVisible(false);

        } else {

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tf_e_semnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_e_semnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_e_semnameActionPerformed

    private void tf_nosubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_nosubActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tf_nosubActionPerformed

    private void tf_nosubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_nosubMouseClicked
        // TODO add your handling code here:
        if (tf_nosub.isEditable()) {
            tf_nosub.setForeground(Color.BLACK);
            tf_nosub.setText(null);
        }

    }//GEN-LAST:event_tf_nosubMouseClicked

    private void jp_pres_doneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jp_pres_doneActionPerformed

        get_semnsec();
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int res = JOptionPane.showConfirmDialog(null, "Are you sure ,you want to proceed!", "CONFIRM", dialogButton);

        if (res == JOptionPane.YES_OPTION) {
            for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++)//since number of prescribed subjects=6
            {
                sem[sem_i - 1].sec[sec_i - 1].s[i] = new subject();//allocating memory for all subjects
                sem[sem_i - 1].sec[sec_i - 1].s[i].s_name = (String) tble_sublist.getValueAt(i, 2);
                sem[sem_i - 1].sec[sec_i - 1].s[i].subcode = (String) tble_sublist.getValueAt(i, 1);
                if (sem[sem_i - 1].sec[sec_i - 1].modifysub == false) {
                    sem[sem_i - 1].sec[sec_i - 1].s[i].sub_sf = fobj.sublist[i][2];
                } else {
                    produce_subsf();
                }
            }
            for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
                System.out.println(tble_sublist.getValueAt(i, 2) + "\t" + tble_sublist.getValueAt(i, 1)
                        + "\t" + sem[sem_i - 1].sec[sec_i - 1].s[i].sub_sf);
            }
            show_subjects();

            sem[sem_i - 1].sec[sec_i - 1].doneflag = true;
            sem[sem_i - 1].sec[sec_i - 1].modifysub = false;

            tp_1.setEnabledAt(2, true);
            tp_1.setSelectedIndex(2);
            disable_buttons(jp_pres);
            enable_buttons(cd1_subdetails);
        }

        // TODO add your handling code here:

    }//GEN-LAST:event_jp_pres_doneActionPerformed

    private void sublist_subActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sublist_subActionPerformed
        // TODO add your handling code here:

        get_semnsec();
        if ((Integer.parseInt(tf_nosub.getText())) > 10) {
            JOptionPane.showMessageDialog(null, "please keep the number of subjects under 10");
        } else {
            try {
                sem[sem_i - 1].sec[sec_i - 1].nos = Integer.parseInt(tf_nosub.getText());

                for (int i = 0; i < tble_sublist.getColumnCount(); i++) {
                    tble_sublist.setEditingColumn(i);
                }
                DefaultTableModel model = (DefaultTableModel) tble_sublist.getModel();
                Vector temp = null;

                for (int i = 0; i < model.getRowCount(); i++) {
                    model.removeRow(i);
                    i--;
                }
                for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
                    model.addRow(temp);
                }

            } catch (NumberFormatException nfe) {
                tf_nosub.setForeground(Color.RED);
                tf_nosub.setText("NUMERIC VALUE EXPECTED");
                tf_nosub.setForeground(Color.BLACK);
            }
        }
    }//GEN-LAST:event_sublist_subActionPerformed

    private void sub_modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub_modifyActionPerformed
        // TODO add your handling code here:

        get_semnsec();
        if (sem[sem_i - 1].sec[sec_i - 1].modifysub == false) {
            int op = JOptionPane.YES_NO_OPTION;
            int res = JOptionPane.showConfirmDialog(null, "are you sure you want to enter your subject list.", "confirm", op);
            if (res == JOptionPane.YES_OPTION) {
                tf_nosub.setEditable(true);
                sublist_sub.setVisible(true);
                sublist_sub.setEnabled(true);
                sem[sem_i - 1].sec[sec_i - 1].modifysub = true;
                DefaultTableModel model = (DefaultTableModel) tble_sublist.getModel();

                for (int i = 0; i < tble_sublist.getRowCount(); i++) {

                    model.removeRow(i);
                    i--;
                }
                sub_modify.setText("PRESCRIBED");
            }

        } else {
            sem[sem_i - 1].sec[sec_i - 1].modifysub = false;
            try {
                produce_sub_table();
            } catch (IOException ex) {
                Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
            }
            tf_nosub.setEditable(false);
            sublist_sub.setEnabled(false);
            sub_modify.setText("MODIFY");
        }


    }//GEN-LAST:event_sub_modifyActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int res = JOptionPane.showConfirmDialog(null, "ARE YOU SURE YOU WANT TO EXIT?", "Confirm Close", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            close();
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        int res = JOptionPane.showConfirmDialog(null, "ARE YOU SURE YOU WANT TO EXIT?", "Confirm Close", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            close();
        }


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed


    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:

        get_semnsec();
        
        jButton9.setEnabled(false);
        
        jProgressBar1.setVisible(true);
        
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {

            @Override
            protected Boolean doInBackground() throws Exception {
                prepare_generate();
                return sem[sem_i - 1].sec[sec_i - 1].generatedone;

            }

            @Override
            protected void done() {
                try {
                    if (get()) {
                        jButton7.setVisible(true);
                        jButton10.setVisible(true);
                        jProgressBar1.setVisible(false);
                        displaytt.setEnabled(true);
                        
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Sorry! cannot generate the requested timetable!\n"
                                   +" Timetable planner checked approximtely : 2652528598121910586363084800000000 possibilities."
                               +"\nseems like you need to generate manually, good luck with it!!");
                } catch (InterruptedException ex) {
                    Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        };
        worker.execute();

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:..
        get_semnsec();
        docobj = new genrate_doc();
        String fnam = Integer.toString(sem_array[sem_i - 1]) + sem[sem_i - 1].sect_array[sec_i - 1] + "tt";
        try {
            docobj.create_tt_word(fnam, temp_tt);
        } catch (IOException ex) {
            Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
        }
        jButton9.setEnabled(false);
      
        sem[sem_i - 1].sec[sec_i - 1].dusted = true;
        try {
            update_teach_after_tt();
        } catch (IOException ex) {
            Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (check_dusted()) {
            try {
                create_teacher_doc();
            } catch (IOException ex) {
                Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void tf_nolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nolKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tf_nolKeyPressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        String s = (String) jComboBox1.getSelectedItem();
        jTextField1.setText(s);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void b_1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_1_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_1_2ActionPerformed

    private void b_3_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_3_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_3_2ActionPerformed

    private void b_3_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_3_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_3_3ActionPerformed

    private void b_4_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_4_6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_4_6ActionPerformed

    private void b_5_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_5_5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_5_5ActionPerformed

    private void b_6_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_6_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_6_2ActionPerformed

    private void b_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_okActionPerformed

        current_block_teacher = jTextField1.getText();
        if (current_block_teacher.equals("")) {
            JOptionPane.showMessageDialog(null, "PLEASE SELECT A TEACHER.");
        } else {

            if (teacher_select_flag == 0) {
                teacher_select_flag = 0;
                int option = JOptionPane.YES_NO_OPTION;
                int result = JOptionPane.showConfirmDialog(null, "ARE YOU SURE YOU WANT TO DISCONTINUE ? DATA WILL BE LOST", "CONFIRM", option);
                if (result == JOptionPane.YES_OPTION) {
                    String tname = jTextField1.getText();
                    tname = "generate data\\" + tname + ".txt";
                    File f = new File(tname);
                    if (f.exists()) {
                        int option1 = JOptionPane.YES_NO_OPTION;
                        int result1 = JOptionPane.showConfirmDialog(null, "FILE ALREADY PRESENT ! DO YOU WANT TO OVERWRITE ?", "OVERWRITE ?", option1);
                        if (result1 == JOptionPane.YES_OPTION) {
                            f.delete();
                            flush_buttons();

                        }
                    }
                }

            } else {
                teacher_select_flag = 0;
                String tname = jTextField1.getText();
                tname = "generate data\\" + tname + ".txt";
                File f = new File(tname);
                if (f.exists()) {
                    int option1 = JOptionPane.YES_NO_OPTION;
                    int result1 = JOptionPane.showConfirmDialog(null, "FILE ALREADY PRESENT ! DO YOU WANT TO OVERWRITE ?", "OVERWRITE ?", option1);
                    if (result1 == JOptionPane.YES_OPTION) {
                        f.delete();
                        flush_buttons();

                    }

                }

            }
        }
    }//GEN-LAST:event_b_okActionPerformed

    private void mouse_clicked_bta(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouse_clicked_bta
        //mouse clicked listener for blocking teacher arrays
        JButton b = new JButton();
        b = (JButton) evt.getSource();
        if (b.getBackground() == Color.RED) {
            b.setBackground(bg);
            b.setText("--");
        } else {
            b.setBackground(Color.RED);
            b.setText("X");
        }


    }//GEN-LAST:event_mouse_clicked_bta

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void b_next_teacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_next_teacherActionPerformed
        // TODO add your handling code here:
        if (current_block_teacher.equals("")) {
            JOptionPane.showMessageDialog(null, "PLEASE SELECT A TEACHER NAME");
            return;
        }
        if (!jTextField1.getText().equals(current_block_teacher)) {
            int oprion2 = JOptionPane.YES_NO_OPTION;
            int res3 = JOptionPane.showConfirmDialog(null, "CONTENTS WILL BE WRITTEN ONTO " + current_block_teacher + "'S FILE. ARE YOU SURE ?", "NAME CHANGED", oprion2);
            if (res3 == JOptionPane.YES_OPTION) {
                String teacher_name = "generate data\\" + current_block_teacher + ".txt";
                File f = new File(teacher_name);
                if (f.exists()) {
                    int option1 = JOptionPane.YES_NO_OPTION;
                    int result1 = JOptionPane.showConfirmDialog(null, "FILE ALREADY PRESENT ! DO YOU WANT TO OVERWRITE ?", "OVERWRITE ?", option1);

                    if (result1 == JOptionPane.YES_OPTION) {
                        f.delete();
                        buttons_to_file();
                        flush_buttons();
                        jTextField1.setText(null);
                        teacher_select_flag = 1;
                    }

                } else {
                    buttons_to_file();
                    flush_buttons();
                    jTextField1.setText(null);
                    teacher_select_flag = 1;
                }
            } else {
                JOptionPane.showMessageDialog(null, "PLEASE PRESS OK BUTTON AFTER SELECTING NEW TEACHER.");
            }

        } else {
            String teacher_name1 = "generate data\\" + current_block_teacher + ".txt";
            File f1 = new File(teacher_name1);
            if (f1.exists()) {
                int option11 = JOptionPane.YES_NO_OPTION;
                int result11 = JOptionPane.showConfirmDialog(null, "FILE ALREADY PRESENT ! DO YOU WANT TO OVERWRITE ?", "OVERWRITE ?", option11);
                if (result11 == JOptionPane.YES_OPTION) {
                    f1.delete();
                    buttons_to_file();
                    flush_buttons();
                    jTextField1.setText("");
                    teacher_select_flag = 1;
                }

            } else {
                buttons_to_file();
                flush_buttons();
                jTextField1.setText("");

                teacher_select_flag = 1;
            }

        }


    }//GEN-LAST:event_b_next_teacherActionPerformed

    private void b_done_jp5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_done_jp5ActionPerformed

        if (current_block_teacher.equals("")) {
            jPanel3.setVisible(false);
            jp2.setVisible(true);
            jScrollPane2.getVerticalScrollBar().setValue(0);//scroll bar's position
            return;
        }
        if (teacher_select_flag == 0) {
            int option11 = JOptionPane.YES_NO_OPTION;
            int result11 = JOptionPane.showConfirmDialog(null, "YOU HAVENT SAVED THIS TEACHER'S DATA. DATA WOULD BE LOST IF YOU DONT SAVE", "CONFIRM", option11);
            if (result11 == JOptionPane.YES_OPTION) {
                jPanel3.setVisible(false);
                jp2.setVisible(true);
                jScrollPane2.getVerticalScrollBar().setValue(0);//scroll bar's position
                return;

            }
        } else {
            jPanel3.setVisible(false);
            jp2.setVisible(true);
            jScrollPane2.getVerticalScrollBar().setValue(0);//scroll bar's position
            return;
        }


    }//GEN-LAST:event_b_done_jp5ActionPerformed

    private void b_del_prioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_del_prioActionPerformed
        get_semnsec();
        if (dm.getRowCount() > 0) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            if (priority_table.isRowSelected(priority_table.getSelectedRow())) {
                int res = JOptionPane.showConfirmDialog(null, "Are you sure ,you want to delete?", "CONFIRM", dialogButton);

                if (res == JOptionPane.YES_OPTION) {
                    try {
                        update_deletedpriority();
                    } catch (IOException ex) {
                        Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dm.removeRow(priority_table.getSelectedRow());
                    sem[sem_i - 1].sec[sec_i - 1].count_no_priority--;
                }

            } else {
                JOptionPane.showConfirmDialog(null, "Please select a priority", "ERROR", JOptionPane.OK_OPTION);

            }

        } else {
            JOptionPane.showConfirmDialog(null, "No priority entered", "ERROR", JOptionPane.OK_OPTION);
        }

    }//GEN-LAST:event_b_del_prioActionPerformed

    private void b_1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_1_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_1_1ActionPerformed

    private void b_2_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_2_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b_2_1ActionPerformed

    private void priority_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_priority_tableMouseClicked


    }//GEN-LAST:event_priority_tableMouseClicked

    private void jc_sublistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jc_sublistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jc_sublistActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        dm = (DefaultTableModel) tble_lp.getModel();
        get_semnsec();
        if (dm.getRowCount() > 0) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            if (tble_lp.isRowSelected(tble_lp.getSelectedRow())) {
                int res = JOptionPane.showConfirmDialog(null, "Are you sure ,you want to delete?", "CONFIRM", dialogButton);

                if (res == JOptionPane.YES_OPTION) {
                    update_deletedlab();
                    dm.removeRow(tble_lp.getSelectedRow());
                    sem[sem_i - 1].sec[sec_i - 1].count_nol_pw--;
                }

            } else {
                JOptionPane.showConfirmDialog(null, "Please select a priority", "ERROR", JOptionPane.OK_OPTION);

            }

        } else {
            JOptionPane.showConfirmDialog(null, "No priority entered", "ERROR", JOptionPane.OK_OPTION);
        }


    }//GEN-LAST:event_jButton8ActionPerformed

    private void tf_namesecKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_namesecKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            b_sbmt_secname.doClick();
        }
    }//GEN-LAST:event_tf_namesecKeyReleased

    private void tf_nolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nolKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            b_nolsub.doClick();
        }
    }//GEN-LAST:event_tf_nolKeyReleased

    private void tf_nosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jp2_b_nxt.doClick();
        }
    }//GEN-LAST:event_tf_nosKeyReleased

    private void tf_nosecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nosecKeyPressed
        int i;
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tf_semname.requestFocus();
            Component[] c = sp1_jp1.getComponents();
            for (i = 0; i < sp1_jp1.getComponentCount(); i++) {

                JButton j = (JButton) c[i];
                if (j.getBackground() == Color.MAGENTA) {
                    break;
                }

            }
            int count = 0;
            while (count <= 7) {
                if (c[(i + 1) % 8].isVisible()) {
                    JButton j = (JButton) c[(i + 1) % 8];
                    j.doClick();
                    break;
                } else {
                    i = (i + 1) % 8;
                    count++;
                }

            }

        }
    }//GEN-LAST:event_tf_nosecKeyPressed

    private void tf_semnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_semnameMouseClicked
        tf_semname.setText(null);
    }//GEN-LAST:event_tf_semnameMouseClicked

    private void tf_nosecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_nosecMouseClicked
        tf_nosec.setText(null);
    }//GEN-LAST:event_tf_nosecMouseClicked

    private void tf_nol_pwKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_nol_pwKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton3.doClick();
        }
    }//GEN-LAST:event_tf_nol_pwKeyReleased

    private void tp_1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tp_1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tp_1AncestorAdded

    private void tp_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tp_1MouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_tp_1MouseClicked

    private void tp_1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tp_1MousePressed

    }//GEN-LAST:event_tp_1MousePressed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        
        try {
            display_tt();
        } catch (IOException ex) {
            Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                /* if ("Nimbus".equals(info.getName())) {
                 javax.swing.UIManager.setLookAndFeel(info.getClassName());
                 break;*/
// prints "com.oyoaha.swing.plaf.oyoaha.OyoahaLookAndFeel"
                UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel"); //com.jtattoo.plaf.noire.NoireLookAndFeel
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(main_jf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_jf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_jf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_jf.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }
                new main_jf().setVisible(true);

            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem DELETE;
    private javax.swing.JButton b_1_1;
    private javax.swing.JButton b_1_2;
    private javax.swing.JButton b_1_3;
    private javax.swing.JButton b_1_4;
    private javax.swing.JButton b_1_5;
    private javax.swing.JButton b_1_6;
    private javax.swing.JButton b_1_7;
    private javax.swing.JButton b_2_1;
    private javax.swing.JButton b_2_2;
    private javax.swing.JButton b_2_3;
    private javax.swing.JButton b_2_4;
    private javax.swing.JButton b_2_5;
    private javax.swing.JButton b_2_6;
    private javax.swing.JButton b_2_7;
    private javax.swing.JButton b_3_1;
    private javax.swing.JButton b_3_2;
    private javax.swing.JButton b_3_3;
    private javax.swing.JButton b_3_4;
    private javax.swing.JButton b_3_5;
    private javax.swing.JButton b_3_6;
    private javax.swing.JButton b_3_7;
    private javax.swing.JButton b_4_1;
    private javax.swing.JButton b_4_2;
    private javax.swing.JButton b_4_3;
    private javax.swing.JButton b_4_4;
    private javax.swing.JButton b_4_5;
    private javax.swing.JButton b_4_6;
    private javax.swing.JButton b_4_7;
    private javax.swing.JButton b_5_1;
    private javax.swing.JButton b_5_2;
    private javax.swing.JButton b_5_3;
    private javax.swing.JButton b_5_4;
    private javax.swing.JButton b_5_5;
    private javax.swing.JButton b_5_6;
    private javax.swing.JButton b_5_7;
    private javax.swing.JButton b_6_1;
    private javax.swing.JButton b_6_2;
    private javax.swing.JButton b_6_3;
    private javax.swing.JButton b_6_4;
    private javax.swing.JButton b_custom;
    private javax.swing.JButton b_del_prio;
    private javax.swing.JButton b_done_jp5;
    private javax.swing.JButton b_jpcust_submit;
    private javax.swing.JButton b_next_teacher;
    private javax.swing.JButton b_nolsub;
    private javax.swing.JButton b_ok;
    private javax.swing.JButton b_prescribed;
    private javax.swing.JButton b_saveld;
    private javax.swing.JButton b_sbmt_secname;
    private javax.swing.JButton b_sp;
    private javax.swing.JButton b_sub_lp;
    private javax.swing.JPanel b_subld;
    private javax.swing.JButton bdone_subdet;
    private javax.swing.JComboBox cb_labday;
    private javax.swing.JComboBox cb_strtprd;
    private javax.swing.JPanel cd1;
    private javax.swing.JPanel cd1_ld;
    private javax.swing.JPanel cd1_subdetails;
    private javax.swing.JPanel cd2;
    private javax.swing.JPanel cd2_ld;
    private javax.swing.JPanel displaytt;
    private javax.swing.JPanel generatett;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JComboBox jc_1;
    private javax.swing.JComboBox jc_10;
    private javax.swing.JComboBox jc_2;
    private javax.swing.JComboBox jc_3;
    private javax.swing.JComboBox jc_4;
    private javax.swing.JComboBox jc_5;
    private javax.swing.JComboBox jc_6;
    private javax.swing.JComboBox jc_7;
    private javax.swing.JComboBox jc_8;
    private javax.swing.JComboBox jc_9;
    private javax.swing.JComboBox jc_days;
    private javax.swing.JComboBox jc_period;
    private javax.swing.JComboBox jc_sublist;
    private javax.swing.JLabel jlab_noc;
    private javax.swing.JLabel jlab_sublist;
    private javax.swing.JPanel jp1;
    private javax.swing.JPanel jp2;
    private javax.swing.JButton jp2_b_nxt;
    private javax.swing.JPanel jp3;
    private javax.swing.JButton jp3_b_submit;
    private javax.swing.JPanel jp3_parent;
    private javax.swing.JButton jp3_sb1;
    private javax.swing.JButton jp3_sb2;
    private javax.swing.JButton jp3_sb3;
    private javax.swing.JButton jp3_sb4;
    private javax.swing.JButton jp3_sb5;
    private javax.swing.JButton jp3_sb6;
    private javax.swing.JButton jp3_sb7;
    private javax.swing.JButton jp3_sb8;
    private javax.swing.JScrollPane jp3_sp1;
    private javax.swing.JPanel jp7;
    private javax.swing.JPanel jp_cust;
    private javax.swing.JPanel jp_pres;
    private javax.swing.JButton jp_pres_done;
    private javax.swing.JPanel jp_priority;
    private javax.swing.JPanel jp_sec;
    private javax.swing.JPanel jp_subdetails;
    private javax.swing.JLabel l_namesec;
    private javax.swing.JLabel l_nos;
    private javax.swing.JLabel l_nosec;
    private javax.swing.JLabel l_nosubj;
    private javax.swing.JLabel l_semdet;
    private javax.swing.JLabel l_semname;
    private javax.swing.JComboBox ljc_1;
    private javax.swing.JComboBox ljc_2;
    private javax.swing.JComboBox ljc_3;
    private javax.swing.JComboBox ljc_4;
    private javax.swing.JPanel parentpane;
    private javax.swing.JTable priority_table;
    private javax.swing.JTextField s_tf1;
    private javax.swing.JTextField s_tf10;
    private javax.swing.JTextField s_tf11;
    private javax.swing.JTextField s_tf12;
    private javax.swing.JTextField s_tf13;
    private javax.swing.JTextField s_tf2;
    private javax.swing.JTextField s_tf3;
    private javax.swing.JTextField s_tf4;
    private javax.swing.JTextField s_tf5;
    private javax.swing.JTextField s_tf6;
    private javax.swing.JTextField s_tf7;
    private javax.swing.JTextField s_tf8;
    private javax.swing.JTextField s_tf9;
    private javax.swing.JButton sp1_b1;
    private javax.swing.JButton sp1_b2;
    private javax.swing.JButton sp1_b3;
    private javax.swing.JButton sp1_b4;
    private javax.swing.JButton sp1_b5;
    private javax.swing.JButton sp1_b6;
    private javax.swing.JButton sp1_b7;
    private javax.swing.JButton sp1_b8;
    private javax.swing.JPanel sp1_jp1;
    private javax.swing.JButton sub_modify;
    private javax.swing.JButton sublist_sub;
    private javax.swing.JPanel tab_container;
    private javax.swing.JPopupMenu table_select;
    private javax.swing.JTable tble_lp;
    private javax.swing.JTable tble_sublist;
    private javax.swing.JTextField tf_e_semname;
    private javax.swing.JTextField tf_l1;
    private javax.swing.JTextField tf_l2;
    private javax.swing.JTextField tf_l3;
    private javax.swing.JTextField tf_l4;
    private javax.swing.JTextField tf_ln1;
    private javax.swing.JTextField tf_ln2;
    private javax.swing.JTextField tf_ln3;
    private javax.swing.JTextField tf_ln4;
    private javax.swing.JTextField tf_namesec;
    private javax.swing.JTextField tf_noc1;
    private javax.swing.JTextField tf_noc10;
    private javax.swing.JTextField tf_noc2;
    private javax.swing.JTextField tf_noc3;
    private javax.swing.JTextField tf_noc4;
    private javax.swing.JTextField tf_noc5;
    private javax.swing.JTextField tf_noc6;
    private javax.swing.JTextField tf_noc7;
    private javax.swing.JTextField tf_noc8;
    private javax.swing.JTextField tf_noc9;
    private javax.swing.JTextField tf_nol;
    private javax.swing.JTextField tf_nol_pw;
    private javax.swing.JTextField tf_nos;
    private javax.swing.JTextField tf_nosec;
    private javax.swing.JTextField tf_nosub;
    private javax.swing.JTextField tf_s1;
    private javax.swing.JTextField tf_s10;
    private javax.swing.JTextField tf_s2;
    private javax.swing.JTextField tf_s3;
    private javax.swing.JTextField tf_s4;
    private javax.swing.JTextField tf_s5;
    private javax.swing.JTextField tf_s6;
    private javax.swing.JTextField tf_s7;
    private javax.swing.JTextField tf_s8;
    private javax.swing.JTextField tf_s9;
    private javax.swing.JTextField tf_semname;
    private javax.swing.JTextField tf_t1;
    private javax.swing.JTextField tf_t10;
    private javax.swing.JTextField tf_t2;
    private javax.swing.JTextField tf_t3;
    private javax.swing.JTextField tf_t4;
    private javax.swing.JTextField tf_t5;
    private javax.swing.JTextField tf_t6;
    private javax.swing.JTextField tf_t7;
    private javax.swing.JTextField tf_t8;
    private javax.swing.JTextField tf_t9;
    private javax.swing.JPanel timetable;
    private javax.swing.JPanel tp1jp1;
    private javax.swing.JTabbedPane tp_1;
    private javax.swing.JPanel tt_generated3;
    // End of variables declaration//GEN-END:variables

    private void produce_sublist() {
        get_semnsec();
        jc_sublist.removeAllItems();
        for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
            jc_sublist.addItem(sem[sem_i - 1].sec[sec_i - 1].s[i].s_name);
        }

    }

    private void printo() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(sem[sem_i - 1].sec[sec_i - 1].prty[i][j] + " ");

            }
            System.out.println();
        }
        for (int k = 0; k < 6; k++) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {

                    System.out.print(sem[sem_i - 1].sec[sec_i - 1].s[k].teach[i][j] + " ");

                }
                System.out.println();
            }
            System.out.println("----------------------------------------");

        }

    }

    private boolean class_free(int day, int period, int index) throws IOException {

        get_semnsec();
        fobj.update_tempload(sem[sem_i - 1].sec[sec_i - 1].s[index].t_name);
        if (sem[sem_i - 1].sec[sec_i - 1].prty[day][period] == -1 && "0".equals(fobj.temp[day][period])) {
            return true;

        }
        if (!"0".equals(fobj.temp[day][period])) {
            JOptionPane.showMessageDialog(null, "Teacher: " + sem[sem_i - 1].sec[sec_i - 1].s[index].t_name + "alredy occupied");
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Cannot update the requested prioriy, class already occupied.");
            return false;

        }

    }

    private void copy_prty_tt() {

        get_semnsec();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                sem[sem_i - 1].sec[sec_i - 1].tt[i][j] = sem[sem_i - 1].sec[sec_i - 1].prty[i][j];
            }
        }

    }

    private void tt_display() {
        get_semnsec();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(sem[sem_i - 1].sec[sec_i - 1].tt[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private void update_teacharray() throws IOException {

        get_semnsec();
        for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
            fobj.fload(sem[sem_i - 1].sec[sec_i - 1].s[i].t_name, sem[sem_i - 1].sec[sec_i - 1].s[i].teach);
        }

    }

    private void update_teach_after_tt() throws IOException {

        get_semnsec();
        for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
            fobj.update_tempload(sem[sem_i - 1].sec[sec_i - 1].s[i].t_name);
            fobj.fstore(sem[sem_i - 1].sec[sec_i - 1].s[i].t_name, sem[sem_i - 1].sec[sec_i - 1].s[i].teach, sem_array[sem_i - 1], sem[sem_i - 1].sect_array[sec_i - 1], sem[sem_i - 1].sec[sec_i - 1].s[i].subcode);

        }

    }

    private void produce_sub_table() throws IOException {
        get_semnsec();
        fobj.f_temp_load(Integer.toString(sem_array[Integer.parseInt(sem_btn.substring(5)) - 1]));
        sem[sem_i - 1].sec[sec_i - 1].nos = fobj.subcount;
        tf_nosub.setForeground(Color.BLACK);
        tf_nosub.setText(Integer.toString(sem[sem_i - 1].sec[sec_i - 1].nos));
        tf_nosub.setEditable(false);
        sublist_sub.setEnabled(false);
        Vector temp = null;
        DefaultTableModel model = (DefaultTableModel) tble_sublist.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i--;
        }

        for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
            model.addRow(temp);
            tble_sublist.setValueAt(Integer.toString(i + 1), i, 0);
            tble_sublist.setValueAt(fobj.sublist[i][0], i, 2);
            tble_sublist.setValueAt(fobj.sublist[i][1], i, 1);

        }

    }

    private void genrate_sublist() {

    }

    private void show_subjects() {
        get_semnsec();
        if (sem[sem_i - 1].sec[sec_i - 1].nos == 3) {
            tf_s1.setText(sem[sem_i - 1].sec[sec_i - 1].s[0].s_name);
            tf_s2.setText(sem[sem_i - 1].sec[sec_i - 1].s[1].s_name);
            tf_s3.setText(sem[sem_i - 1].sec[sec_i - 1].s[2].s_name);
            tf_s4.setVisible(false);
            tf_noc4.setVisible(false);
            tf_t4.setVisible(false);
            tf_s5.setVisible(false);
            tf_noc5.setVisible(false);
            tf_t5.setVisible(false);
            tf_s6.setVisible(false);
            tf_noc6.setVisible(false);
            tf_t6.setVisible(false);

            tf_s7.setVisible(false);
            tf_noc7.setVisible(false);
            tf_t7.setVisible(false);
            tf_s8.setVisible(false);
            tf_noc8.setVisible(false);
            tf_t8.setVisible(false);
            tf_s9.setVisible(false);
            tf_noc9.setVisible(false);
            tf_t9.setVisible(false);
            tf_s10.setVisible(false);
            tf_noc10.setVisible(false);
            tf_t10.setVisible(false);
            jc_7.setVisible(false);
            jc_8.setVisible(false);
            jc_9.setVisible(false);
            jc_10.setVisible(false);
            jc_5.setVisible(false);
            jc_6.setVisible(false);
            jc_4.setVisible(false);
        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 4) {
            tf_s1.setText(sem[sem_i - 1].sec[sec_i - 1].s[0].s_name);
            tf_s2.setText(sem[sem_i - 1].sec[sec_i - 1].s[1].s_name);
            tf_s3.setText(sem[sem_i - 1].sec[sec_i - 1].s[2].s_name);
            tf_s4.setText(sem[sem_i - 1].sec[sec_i - 1].s[3].s_name);
            tf_s5.setVisible(false);
            tf_noc5.setVisible(false);
            tf_t5.setVisible(false);
            tf_s6.setVisible(false);
            tf_noc6.setVisible(false);
            tf_t6.setVisible(false);

            tf_s7.setVisible(false);
            tf_noc7.setVisible(false);
            tf_t7.setVisible(false);
            tf_s8.setVisible(false);
            tf_noc8.setVisible(false);
            tf_t8.setVisible(false);
            tf_s9.setVisible(false);
            tf_noc9.setVisible(false);
            tf_t9.setVisible(false);
            tf_s10.setVisible(false);
            tf_noc10.setVisible(false);
            tf_t10.setVisible(false);
            jc_7.setVisible(false);
            jc_8.setVisible(false);
            jc_9.setVisible(false);
            jc_10.setVisible(false);
            jc_5.setVisible(false);
            jc_6.setVisible(false);
        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 5) {
            tf_s1.setText(sem[sem_i - 1].sec[sec_i - 1].s[0].s_name);
            tf_s2.setText(sem[sem_i - 1].sec[sec_i - 1].s[1].s_name);
            tf_s3.setText(sem[sem_i - 1].sec[sec_i - 1].s[2].s_name);
            tf_s4.setText(sem[sem_i - 1].sec[sec_i - 1].s[3].s_name);
            tf_s5.setText(sem[sem_i - 1].sec[sec_i - 1].s[4].s_name);
            tf_s6.setVisible(false);
            tf_noc6.setVisible(false);
            tf_t6.setVisible(false);
            tf_s7.setVisible(false);
            tf_noc7.setVisible(false);
            tf_t7.setVisible(false);
            tf_s8.setVisible(false);
            tf_noc8.setVisible(false);
            tf_t8.setVisible(false);
            tf_s9.setVisible(false);
            tf_noc9.setVisible(false);
            tf_t9.setVisible(false);
            tf_s10.setVisible(false);
            tf_noc10.setVisible(false);
            tf_t10.setVisible(false);
            jc_7.setVisible(false);
            jc_8.setVisible(false);
            jc_9.setVisible(false);
            jc_10.setVisible(false);
            jc_6.setVisible(false);

        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 6) {
            tf_s1.setText(sem[sem_i - 1].sec[sec_i - 1].s[0].s_name);
            tf_s2.setText(sem[sem_i - 1].sec[sec_i - 1].s[1].s_name);
            tf_s3.setText(sem[sem_i - 1].sec[sec_i - 1].s[2].s_name);
            tf_s4.setText(sem[sem_i - 1].sec[sec_i - 1].s[3].s_name);
            tf_s5.setText(sem[sem_i - 1].sec[sec_i - 1].s[4].s_name);
            tf_s6.setText(sem[sem_i - 1].sec[sec_i - 1].s[5].s_name);
            tf_s7.setVisible(false);
            tf_noc7.setVisible(false);
            tf_t7.setVisible(false);
            tf_s8.setVisible(false);
            tf_noc8.setVisible(false);
            tf_t8.setVisible(false);
            tf_s9.setVisible(false);
            tf_noc9.setVisible(false);
            tf_t9.setVisible(false);
            tf_s10.setVisible(false);
            tf_noc10.setVisible(false);
            tf_t10.setVisible(false);
            jc_7.setVisible(false);
            jc_8.setVisible(false);
            jc_9.setVisible(false);
            jc_10.setVisible(false);
        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 7) {
            tf_s1.setText(sem[sem_i - 1].sec[sec_i - 1].s[0].s_name);
            tf_s2.setText(sem[sem_i - 1].sec[sec_i - 1].s[1].s_name);
            tf_s3.setText(sem[sem_i - 1].sec[sec_i - 1].s[2].s_name);
            tf_s4.setText(sem[sem_i - 1].sec[sec_i - 1].s[3].s_name);
            tf_s5.setText(sem[sem_i - 1].sec[sec_i - 1].s[4].s_name);
            tf_s6.setText(sem[sem_i - 1].sec[sec_i - 1].s[5].s_name);
            tf_s7.setText(sem[sem_i - 1].sec[sec_i - 1].s[6].s_name);
            tf_s8.setVisible(false);
            tf_noc8.setVisible(false);
            tf_t8.setVisible(false);
            tf_s9.setVisible(false);
            tf_noc9.setVisible(false);
            tf_t9.setVisible(false);
            tf_s10.setVisible(false);
            tf_noc10.setVisible(false);
            tf_t10.setVisible(false);
            jc_8.setVisible(false);
            jc_9.setVisible(false);
            jc_10.setVisible(false);

        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 8) {
            tf_s1.setText(sem[sem_i - 1].sec[sec_i - 1].s[0].s_name);
            tf_s2.setText(sem[sem_i - 1].sec[sec_i - 1].s[1].s_name);
            tf_s3.setText(sem[sem_i - 1].sec[sec_i - 1].s[2].s_name);
            tf_s4.setText(sem[sem_i - 1].sec[sec_i - 1].s[3].s_name);
            tf_s5.setText(sem[sem_i - 1].sec[sec_i - 1].s[4].s_name);
            tf_s6.setText(sem[sem_i - 1].sec[sec_i - 1].s[5].s_name);
            tf_s7.setText(sem[sem_i - 1].sec[sec_i - 1].s[6].s_name);
            tf_s8.setText(sem[sem_i - 1].sec[sec_i - 1].s[7].s_name);
            tf_s9.setVisible(false);
            tf_noc9.setVisible(false);
            tf_t9.setVisible(false);
            tf_s10.setVisible(false);
            tf_noc10.setVisible(false);
            tf_t10.setVisible(false);
            jc_9.setVisible(false);
            jc_10.setVisible(false);

        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 9) {
            tf_s1.setText(sem[sem_i - 1].sec[sec_i - 1].s[0].s_name);
            tf_s2.setText(sem[sem_i - 1].sec[sec_i - 1].s[1].s_name);
            tf_s3.setText(sem[sem_i - 1].sec[sec_i - 1].s[2].s_name);
            tf_s4.setText(sem[sem_i - 1].sec[sec_i - 1].s[3].s_name);
            tf_s5.setText(sem[sem_i - 1].sec[sec_i - 1].s[4].s_name);
            tf_s6.setText(sem[sem_i - 1].sec[sec_i - 1].s[5].s_name);
            tf_s7.setText(sem[sem_i - 1].sec[sec_i - 1].s[6].s_name);
            tf_s8.setText(sem[sem_i - 1].sec[sec_i - 1].s[7].s_name);
            tf_s9.setText(sem[sem_i - 1].sec[sec_i - 1].s[8].s_name);
            tf_s10.setVisible(false);
            tf_noc10.setVisible(false);
            tf_t10.setVisible(false);
            jc_10.setVisible(false);

        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 10) {
            tf_s1.setText(sem[sem_i - 1].sec[sec_i - 1].s[0].s_name);
            tf_s2.setText(sem[sem_i - 1].sec[sec_i - 1].s[1].s_name);
            tf_s3.setText(sem[sem_i - 1].sec[sec_i - 1].s[2].s_name);
            tf_s4.setText(sem[sem_i - 1].sec[sec_i - 1].s[3].s_name);
            tf_s5.setText(sem[sem_i - 1].sec[sec_i - 1].s[4].s_name);
            tf_s6.setText(sem[sem_i - 1].sec[sec_i - 1].s[5].s_name);
            tf_s7.setText(sem[sem_i - 1].sec[sec_i - 1].s[6].s_name);
            tf_s8.setText(sem[sem_i - 1].sec[sec_i - 1].s[7].s_name);
            tf_s9.setText(sem[sem_i - 1].sec[sec_i - 1].s[8].s_name);
            tf_s10.setText(sem[sem_i - 1].sec[sec_i - 1].s[9].s_name);

        }
    }

    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }

    private void produce_subsf() {

        String sf;

    }

    void prepare_generate() {
        get_semnsec();
        int tt_gen_count = 0;

        if (check_priority() && !sem[sem_i - 1].sec[sec_i - 1].generatedone) {
            ttobj = new time_tab();

            while (tt_gen_count < 10) {// loop till a valid tt is generated(giving 10 shots)
                copy_prty_tt();
                try {
                    update_teacharray();//loads teacher files into teach array
                } catch (IOException ex) {
                    Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
                }
                ttobj.set_time_tab();
                int psc = 0;
                psc = ttobj.psc_0(sem[sem_i - 1].sec[sec_i - 1].tt);
                try {
                    ttobj.timetable(tt_gen_count, 0, 0, 0, psc, sem[sem_i - 1].sec[sec_i - 1].s,
                            sem[sem_i - 1].sec[sec_i - 1].tt, sem[sem_i - 1].sec[sec_i - 1].nos);
                    if (tt_not_complete()) {
                        tt_gen_count++;
                        System.out.println("tt count:\t" + tt_gen_count);
                        restore_details();
                    } else {
                        tt_display();
                        sem[sem_i - 1].sec[sec_i - 1].generatedone = true;
                        return;
                    }

                } catch (IOException ex) {
                    Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            //ttoutput.setVisible(true);
            // tt_option.setVisible(false);
            // tt_display();
        }

    }

    private void create_tt_template() {

        Graphics2D gfx = (Graphics2D) displaytt.getGraphics();
        gfx.drawRect(5, 5, 1000, 550);
        gfx.drawLine(5, (550 / 7), 1005, (550 / 7));
        for (int i = 2; i <= 6; i++) {

            gfx.drawLine(5, (550 / 7) * i, (1005 / 10) * 3, (550 / 7) * i);

        }
        for (int i = 2; i <= 6; i++) {

            gfx.drawLine((1005 / 10) * 4, (550 / 7) * i, (1005 / 10) * 6, (550 / 7) * i);

        }
        for (int i = 2; i <= 6; i++) {

            gfx.drawLine((1005 / 10) * 7, (550 / 7) * i, (1005), (550 / 7) * i);

        }
        Font f;
        gfx.setFont(new Font("serif", Font.BOLD, 18));
        for (int i = 1; i <= 9; i++) {
            gfx.drawLine((1005 / 10) * i, 5, (1005 / 10) * i, 555);
        }
        // DISPLAYS THE DAYS
        gfx.drawString("DAY/", 20, 35);
        gfx.drawString("TIME", 20, 55);
        gfx.drawString("MON", 20, 125);
        gfx.drawString("TUE", 20, (75 * 2) + 50);
        gfx.drawString("WED", 20, (75 * 3) + 50);
        gfx.drawString("THU", 20, (75 * 4) + 55);
        gfx.drawString("FRI", 20, (75 * 5) + 60);
        gfx.drawString("SAT", 20, (75 * 6) + 65);
        // DISPLAYS TIME IN FIRST ROW
        gfx.drawString("8:40-", 125, 35);
        gfx.drawString("9:40", 125, 55);
        gfx.drawString("9:40-", (105 * 2) + 20, 35);
        gfx.drawString("10:40", (105 * 2) + 20, 55);
        gfx.drawString("10:40-", (105 * 3) + 10, 35);
        gfx.drawString("11:00", (105 * 3) + 10, 55);
        gfx.drawString("11:00-", (105 * 4) + 10, 35);
        gfx.drawString("12:00", (105 * 4) + 10, 55);
        gfx.drawString("12:00-", (105 * 5) + 8, 35);
        gfx.drawString("1:00", (105 * 5) + 8, 55);
        gfx.drawString("1:00-", (105 * 6) + 8, 35);
        gfx.drawString("1:40", (105 * 6) + 2, 55);
        gfx.drawString("1:40-", (105 * 7) + 2, 35);
        gfx.drawString("2:30", (105 * 7), 55);
        gfx.drawString("2:30-", (105 * 8), 35);
        gfx.drawString("3:20", (105 * 8), 55);
        gfx.drawString("3:20-", (105 * 9), 35);
        gfx.drawString("4:10", (105 * 9), 55);

        gfx.setFont(new Font("serif", Font.ITALIC, 30));
        // PRINTS SHORT BREAK
        gfx.drawString("B", (105 * 3) + 25, 125);
        gfx.drawString("R", (105 * 3) + 25, (75 * 2) + 50);
        gfx.drawString("E", (105 * 3) + 25, (75 * 3) + 50);
        gfx.drawString("A", (105 * 3) + 25, (75 * 4) + 55);
        gfx.drawString("K", (105 * 3) + 25, (75 * 5) + 60);

        //PRINTS LUNCH BREAK
        gfx.drawString("B", (105 * 6) + 8, 125);
        gfx.drawString("R", (105 * 6) + 8, (75 * 2) + 50);
        gfx.drawString("E", (105 * 6) + 8, (75 * 3) + 50);
        gfx.drawString("A", (105 * 6) + 8, (75 * 4) + 55);
        gfx.drawString("K", (105 * 6) + 8, (75 * 5) + 60);
    }

    private void display_tt() throws IOException {
        get_semnsec();
        String sub;
        int x = 0;
        int y;
        Graphics2D gfx = (Graphics2D) displaytt.getGraphics();
        Color bg = gfx.getBackground();
        gfx.setFont(new Font("serif", Font.BOLD, 22));
        gfx.clearRect(0, 0, 1010, 600);
        gfx.setBackground(bg);
        create_tt_template();

        String subcode;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (j != 2) {
                    if (sem[sem_i - 1].sec[sec_i - 1].tt[i][j] != 99) {
                        subcode = sem[sem_i - 1].sec[sec_i - 1].s[sem[sem_i - 1].sec[sec_i - 1].tt[i][j]].sub_sf;
                    } else {
                        subcode = "LAB";
                    }
                    temp_tt[i][j] = subcode;
                } else {
                    temp_tt[i][j] = " ";
                }

            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                if (j != 5) {
                    if (sem[sem_i - 1].sec[sec_i - 1].tt[i][j - 1] != 99) {
                        subcode = sem[sem_i - 1].sec[sec_i - 1].s[sem[sem_i - 1].sec[sec_i - 1].tt[i][j - 1]].sub_sf;
                    } else {
                        subcode = "LAB";
                    }
                    temp_tt[i][j] = subcode;

                } else {
                    temp_tt[i][j] = " ";

                }
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 6; j < 9; j++) {
                if (sem[sem_i - 1].sec[sec_i - 1].tt[i][j - 2] != 99 && sem[sem_i - 1].sec[sec_i - 1].tt[i][j - 2] != -1) {
                    subcode = sem[sem_i - 1].sec[sec_i - 1].s[sem[sem_i - 1].sec[sec_i - 1].tt[i][j - 2]].sub_sf;

                } else if (sem[sem_i - 1].sec[sec_i - 1].tt[i][j - 2] != -1) {
                    subcode = "LAB";
                } else {
                    subcode = " ";
                }
                temp_tt[i][j] = subcode;
            }
        }
        int offset;
        for (int i = 0; i < 6; i++) {
            offset = 25;
            for (int j = 0; j < 9; j++, offset -= 2) {
                gfx.drawString(temp_tt[i][j], (105 * (j + 1) + offset), (75 * (i + 1)) + 50);
            }

        }

    }

    private void save_details() throws IOException {

        get_semnsec();
        //  update_teacharray();
        for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
            sem[sem_i - 1].sec[sec_i - 1].tempsc[i] = sem[sem_i - 1].sec[sec_i - 1].s[i].sc;
        }

    }

    private void restore_details() {

        for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
            sem[sem_i - 1].sec[sec_i - 1].s[i].sc = sem[sem_i - 1].sec[sec_i - 1].tempsc[i];
        }

    }

    private boolean check_dusted() {
        for (int i = 0; i < no_sem; i++) {
            for (int j = 0; j < sem[i].n; j++) {
                if (sem[i].sec[j].dusted == false) {
                    return false;
                }
            }
        }
        return true;

    }

    private void create_teacher_doc() throws IOException {
        get_semnsec();
        for (int i = 0; i < no_sem; i++) {
            for (int j = 0; j < sem[i].n; j++) {
                for (int k = 0; k < sem[i].sec[j].nos; k++) {
                    prepare_teacher_array(sem[i].sec[j].s[k].t_name, k);
                    docobj.teach_doc(sem[i].sec[j].s[k].t_name, temp_teach_f);
                }

            }
        }
    }

    private void prepare_teacher_array(String tname, int sub_in) throws IOException {
        fobj.update_tempload(tname);
        produce_teachf(sub_in);

    }

    private void produce_teachf(int sub_in) {

        int section_ind;
        int semester_ind;
        char semnsec[] = new char[10];
        String shortf;
        String ss;

        for (int i = 0; i < 6; i++) {
            for (int j = 0, y = 0; j < 7; j++, y++) {

                if (!(fobj.temp[i][j].equals("0")) && !(fobj.temp[i][j].contains("l") || fobj.temp[i][j].contains("L"))) {
                    semnsec = fobj.temp[i][j].toCharArray();
                    System.out.println("0th char:" + semnsec[0]);
                    System.out.println("sem ");
                    semester_ind = derive_semindex(semnsec);

                    section_ind = derive_secindex(semnsec, semester_ind);
                    shortf = fobj.temp[i][j].substring(0, 1) + fobj.temp[i][j].substring(1, 2) + "-" + sem[semester_ind].sec[section_ind].s[sub_in].sub_sf;

                } else {
                    shortf = fobj.temp[i][j];
                }
                if (y == 2 || y == 5) {
                    temp_teach_f[i][y++] = "";
                    temp_teach_f[i][y] = shortf;
                } else {
                    temp_teach_f[i][y] = shortf;

                }
            }
        }

    }

    private int derive_semindex(char[] semnsec) {
        String temp;
        for (int i = 0; i < no_sem; i++) {
            temp = Integer.toString(sem_array[i]);
            if ((semnsec[0] == temp.charAt(0))) {
                System.out.println("sem :" + sem_array[i]);
                return i;
            }
        }
        return -1;

    }

    private int derive_secindex(char[] semnsec, int semester_ind) {
        char sections[] = new char[2];
        for (int i = 0; i < sem[semester_ind].n; i++) {
            sections = sem[semester_ind].sect_array[i].toCharArray();
            if (sections[0] == semnsec[1]) {
                return i;
            }
        }
        return -1;

    }

    private void get_noc_tname() {
        get_semnsec();
        if (sem[sem_i - 1].sec[sec_i - 1].nos == 1) {
            sem[sem_i - 1].sec[sec_i - 1].s[0].noc = Integer.parseInt(tf_noc1.getText());

            sem[sem_i - 1].sec[sec_i - 1].s[0].t_name = tf_t1.getText();
        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 2) {
            sem[sem_i - 1].sec[sec_i - 1].s[0].noc = Integer.parseInt(tf_noc1.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[1].noc = Integer.parseInt(tf_noc2.getText());

            sem[sem_i - 1].sec[sec_i - 1].s[0].t_name = tf_t1.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[1].t_name = tf_t2.getText();
        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 3) {
            sem[sem_i - 1].sec[sec_i - 1].s[0].noc = Integer.parseInt(tf_noc1.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[1].noc = Integer.parseInt(tf_noc2.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[2].noc = Integer.parseInt(tf_noc3.getText());

            sem[sem_i - 1].sec[sec_i - 1].s[0].t_name = tf_t1.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[1].t_name = tf_t2.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[2].t_name = tf_t3.getText();
        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 4) {
            sem[sem_i - 1].sec[sec_i - 1].s[0].noc = Integer.parseInt(tf_noc1.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[1].noc = Integer.parseInt(tf_noc2.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[2].noc = Integer.parseInt(tf_noc3.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[3].noc = Integer.parseInt(tf_noc4.getText());

            sem[sem_i - 1].sec[sec_i - 1].s[0].t_name = tf_t1.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[1].t_name = tf_t2.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[2].t_name = tf_t3.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[3].t_name = tf_t4.getText();
        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 5) {
            sem[sem_i - 1].sec[sec_i - 1].s[0].noc = Integer.parseInt(tf_noc1.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[1].noc = Integer.parseInt(tf_noc2.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[2].noc = Integer.parseInt(tf_noc3.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[3].noc = Integer.parseInt(tf_noc4.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[4].noc = Integer.parseInt(tf_noc5.getText());

            sem[sem_i - 1].sec[sec_i - 1].s[0].t_name = tf_t1.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[1].t_name = tf_t2.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[2].t_name = tf_t3.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[3].t_name = tf_t4.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[4].t_name = tf_t5.getText();
        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 6) {
            sem[sem_i - 1].sec[sec_i - 1].s[0].noc = Integer.parseInt(tf_noc1.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[1].noc = Integer.parseInt(tf_noc2.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[2].noc = Integer.parseInt(tf_noc3.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[3].noc = Integer.parseInt(tf_noc4.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[4].noc = Integer.parseInt(tf_noc5.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[5].noc = Integer.parseInt(tf_noc6.getText());
            //loading the teacher names from the text fields
            sem[sem_i - 1].sec[sec_i - 1].s[0].t_name = tf_t1.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[1].t_name = tf_t2.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[2].t_name = tf_t3.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[3].t_name = tf_t4.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[4].t_name = tf_t5.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[5].t_name = tf_t6.getText();
        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 7) {
            sem[sem_i - 1].sec[sec_i - 1].s[0].noc = Integer.parseInt(tf_noc1.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[1].noc = Integer.parseInt(tf_noc2.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[2].noc = Integer.parseInt(tf_noc3.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[3].noc = Integer.parseInt(tf_noc4.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[4].noc = Integer.parseInt(tf_noc5.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[5].noc = Integer.parseInt(tf_noc6.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[6].noc = Integer.parseInt(tf_noc7.getText());

            sem[sem_i - 1].sec[sec_i - 1].s[0].t_name = tf_t1.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[1].t_name = tf_t2.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[2].t_name = tf_t3.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[3].t_name = tf_t4.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[4].t_name = tf_t5.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[5].t_name = tf_t6.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[6].t_name = tf_t7.getText();

        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 8) {
            sem[sem_i - 1].sec[sec_i - 1].s[0].noc = Integer.parseInt(tf_noc1.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[1].noc = Integer.parseInt(tf_noc2.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[2].noc = Integer.parseInt(tf_noc3.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[3].noc = Integer.parseInt(tf_noc4.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[4].noc = Integer.parseInt(tf_noc5.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[5].noc = Integer.parseInt(tf_noc6.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[6].noc = Integer.parseInt(tf_noc7.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[7].noc = Integer.parseInt(tf_noc8.getText());

            sem[sem_i - 1].sec[sec_i - 1].s[0].t_name = tf_t1.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[1].t_name = tf_t2.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[2].t_name = tf_t3.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[3].t_name = tf_t4.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[4].t_name = tf_t5.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[5].t_name = tf_t6.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[6].t_name = tf_t7.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[7].t_name = tf_t8.getText();

        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 9) {
            sem[sem_i - 1].sec[sec_i - 1].s[0].noc = Integer.parseInt(tf_noc1.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[1].noc = Integer.parseInt(tf_noc2.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[2].noc = Integer.parseInt(tf_noc3.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[3].noc = Integer.parseInt(tf_noc4.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[4].noc = Integer.parseInt(tf_noc5.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[5].noc = Integer.parseInt(tf_noc6.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[6].noc = Integer.parseInt(tf_noc7.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[7].noc = Integer.parseInt(tf_noc8.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[8].noc = Integer.parseInt(tf_noc9.getText());

            sem[sem_i - 1].sec[sec_i - 1].s[0].t_name = tf_t1.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[1].t_name = tf_t2.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[2].t_name = tf_t3.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[3].t_name = tf_t4.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[4].t_name = tf_t5.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[5].t_name = tf_t6.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[6].t_name = tf_t7.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[7].t_name = tf_t8.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[8].t_name = tf_t9.getText();

        } else if (sem[sem_i - 1].sec[sec_i - 1].nos == 10) {
            sem[sem_i - 1].sec[sec_i - 1].s[0].noc = Integer.parseInt(tf_noc1.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[1].noc = Integer.parseInt(tf_noc2.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[2].noc = Integer.parseInt(tf_noc3.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[3].noc = Integer.parseInt(tf_noc4.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[4].noc = Integer.parseInt(tf_noc5.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[5].noc = Integer.parseInt(tf_noc6.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[6].noc = Integer.parseInt(tf_noc7.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[7].noc = Integer.parseInt(tf_noc8.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[8].noc = Integer.parseInt(tf_noc9.getText());
            sem[sem_i - 1].sec[sec_i - 1].s[9].noc = Integer.parseInt(tf_noc10.getText());

            sem[sem_i - 1].sec[sec_i - 1].s[0].t_name = tf_t1.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[1].t_name = tf_t2.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[2].t_name = tf_t3.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[3].t_name = tf_t4.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[4].t_name = tf_t5.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[5].t_name = tf_t6.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[6].t_name = tf_t7.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[7].t_name = tf_t8.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[8].t_name = tf_t9.getText();
            sem[sem_i - 1].sec[sec_i - 1].s[9].t_name = tf_t10.getText();

        }
    }

    private boolean tt_not_complete() {

        get_semnsec();
        for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
            //check if all the subjects counts have reached the no. of classes per week(noc)
            if (sem[sem_i - 1].sec[sec_i - 1].s[i].noc != sem[sem_i - 1].sec[sec_i - 1].s[i].sc) {
                return true;
            }
        }
        return false;
    }

    private void flush_buttons() {
        Component[] c = jPanel5.getComponents();
        for (int i = 0; i < jPanel5.getComponentCount(); i++) {
            try {
                JButton b1 = (JButton) c[i];
                b1.setBackground(bg);
                b1.setText("--");
            } catch (Exception e) {
                continue;
            }
        }
    }

    private void buttons_to_file() {
        int array_i = 0;
        int array_j = 0;
        for (int i = 0; i < jPanel5.getComponentCount(); i++) {
            Component[] c1 = jPanel5.getComponents();
            try {
                JButton b1 = (JButton) c1[i];
                if (b1.getBackground() == Color.RED) {
                    fobj.temp[array_i][array_j++] = "blocked";
                } else {
                    fobj.temp[array_i][array_j++] = "0";
                }
                if (array_j == 7) {
                    array_j = 0;
                    array_i++;
                }

                if (array_i == 6) {
                    break;
                }

            } catch (Exception e) {
                continue;
            }
        }
        fobj.temp[5][4] = "0";
        fobj.temp[5][5] = "0";
        fobj.temp[5][6] = "0";
        try {
            fobj.update_teachfile(current_block_teacher);
        } catch (IOException ex) {
            Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addrow(String sub, String day, String period, JTable table) {

        get_semnsec();
        dm = (DefaultTableModel) table.getModel();
        if ("no_sub".equals(sub)) {
            String[] rowdata = {day, period};
            dm.addRow(rowdata);
            sem[sem_i - 1].sec[sec_i - 1].count_nol_pw++;
        } else {
            String[] rowdata = {sub, day, period};
            dm.addRow(rowdata);

        }

    }

    private void update_deletedpriority() throws IOException {

        get_semnsec();
        dm = (DefaultTableModel) priority_table.getModel();
        String s = (String) dm.getValueAt(priority_table.getSelectedRow(), 0);//subject
        String d = (String) dm.getValueAt(priority_table.getSelectedRow(), 1);//day
        int p = Integer.parseInt((String) dm.getValueAt(priority_table.getSelectedRow(), 2)) - 1;//period
        int s_index = get_sindex(s);
        int d_index = get_dindex(d);
        //restoring the priority
        sem[sem_i - 1].sec[sec_i - 1].s[s_index].teach[d_index][p] = -1;
        sem[sem_i - 1].sec[sec_i - 1].prty[d_index][p] = -1;
        sem[sem_i - 1].sec[sec_i - 1].s[s_index].sc--;
        fobj.update_tempload(sem[sem_i - 1].sec[sec_i - 1].s[s_index].t_name);
        fobj.temp[d_index][p] = "0";
        fobj.update_teachfile(sem[sem_i - 1].sec[sec_i - 1].s[s_index].t_name);

    }

    private int get_sindex(String s) {

        get_semnsec();
        for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nos; i++) {
            if (sem[sem_i - 1].sec[sec_i - 1].s[i].s_name.equals(s)) {
                return i;
            }
        }
        return 0;

    }

    private int get_dindex(String d) {

        if (d.equals("MON")) {
            return 0;
        }
        if (d.equals("TUE")) {
            return 1;
        }
        if (d.equals("WED")) {
            return 2;
        }
        if (d.equals("THU")) {
            return 3;
        }
        if (d.equals("FRI")) {
            return 4;
        }
        if (d.equals("SAT")) {
            return 5;
        }

        return 0;

    }

    private void update_deletedlab() {
        get_semnsec();
        int day = get_dindex((String) dm.getValueAt(tble_lp.getSelectedRow(), 0));
        int s_per = Integer.parseInt((String) dm.getValueAt(tble_lp.getSelectedRow(), 1)) - 1;
        for (int j = 0; j < sem[sem_i - 1].sec[sec_i - 1].nol; j++) {
            try {
                sem[sem_i - 1].sec[sec_i - 1].lab_restor(sem_array[sem_i - 1], sem[sem_i - 1].sect_array[sec_i - 1],
                        sem[sem_i - 1].sec[sec_i - 1].l[j].l_teach[0],
                        day,
                        s_per, sem[sem_i - 1].sec[sec_i - 1].l[j].l_code);
            } catch (IOException ex) {
                Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private void flush_rows(JTable tble_lp) {

        dm = (DefaultTableModel) tble_lp.getModel();

        for (int i = 0; i < dm.getRowCount(); i++) {
            dm.removeRow(i);
            i--;
        }

    }

    private void display_lab_table() {

        get_semnsec();

        dm = (DefaultTableModel) tble_lp.getModel();
        String day;
        Vector v = null;
        for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].nol_pw; i++) {
            dm.addRow(v);
            day = sem[sem_i - 1].sec[sec_i - 1].sp_day[i][0];
            dm.setValueAt(day, i, 0);
            day = sem[sem_i - 1].sec[sec_i - 1].sp_day[i][1];
            dm.setValueAt(day, i, 1);
        }

    }

    void block_sec_buttons() {
        jp3_sb1.setEnabled(false);
        jp3_sb2.setEnabled(false);
        jp3_sb3.setEnabled(false);
        jp3_sb4.setEnabled(false);
        jp3_sb5.setEnabled(false);
        jp3_sb6.setEnabled(false);
        jp3_sb7.setEnabled(false);
        jp3_sb8.setEnabled(false);
    }

    void enable_sec_buttons() {
        jp3_sb1.setEnabled(true);
        jp3_sb2.setEnabled(true);
        jp3_sb3.setEnabled(true);
        jp3_sb4.setEnabled(true);
        jp3_sb5.setEnabled(true);
        jp3_sb6.setEnabled(true);
        jp3_sb7.setEnabled(true);
        jp3_sb8.setEnabled(true);

    }

    void block_sem_buttons() {
        Component[] c = sp1_jp1.getComponents();
        for (int i = 0; i < sp1_jp1.getComponentCount(); i++) {
            JButton j = (JButton) c[i];
            j.setEnabled(false);
        }
    }

    void enable_sem_buttons() {
        Component[] c = sp1_jp1.getComponents();
        for (int i = 0; i < sp1_jp1.getComponentCount(); i++) {
            JButton j = (JButton) c[i];
            j.setEnabled(true);
        }
    }

    private void display_priority_table() {

        get_semnsec();
        dm = (DefaultTableModel) priority_table.getModel();
        Vector v = null;
        for (int i = 0; i < sem[sem_i - 1].sec[sec_i - 1].count_no_priority; i++) {
            dm.addRow(v);
            dm.setValueAt(sem[sem_i - 1].sec[sec_i - 1].priority_buff[i][0], i, 0);
            dm.setValueAt(sem[sem_i - 1].sec[sec_i - 1].priority_buff[i][1], i, 1);
            dm.setValueAt(sem[sem_i - 1].sec[sec_i - 1].priority_buff[i][2], i, 2);
        }

    }

    private void section_code(int sec) {

        get_semnsec();

        if (sem[prev_sem_i - 1].sec[prev_sec_i - 1].modifysub == true)//added on 19th november 11:00 pm
        {
            JOptionPane.showMessageDialog(null, "please save and proceed");
        } else {

            change_backgroundsec(sec);
            jp_sec.setVisible(true);
            jp_pres.setVisible(false);

            String tss;// temporary sem sec
            tss = Integer.toString(sem_i) + Integer.toString(sec_i);

            if (sem[sem_i - 1].sec[sec_i - 1].labdone == false) {
                tp_1.setSelectedIndex(0);
                tp_1.setEnabledAt(1, false);
                tp_1.setEnabledAt(2, false);
                tp_1.setEnabledAt(3, false);
                tp_1.setEnabledAt(4, false);
                enable_buttons(cd1_ld);
                enable_buttons(cd2_ld);

            } else {
                tp_1.setEnabledAt(1, true);
                try {
                    produce_sub_table();//added on 19th november 11:00 pm
                } catch (IOException ex) {
                    Logger.getLogger(main_jf.class.getName()).log(Level.SEVERE, null, ex);
                }
                tf_nosub.setText(Integer.toString(sem[sem_i - 1].sec[sec_i - 1].nos));
                enable_buttons(jp_pres);
                disable_buttons(cd1_ld);
                disable_buttons(cd2_ld);
                tf_nosub.setEditable(false);
                sublist_sub.setEnabled(false);
                tp_1.setEnabledAt(2, false);
                tp_1.setEnabledAt(3, false);
                tp_1.setEnabledAt(4, false);
                tp_1.setSelectedIndex(1);

            }

            if (sem[sem_i - 1].sec[sec_i - 1].doneflag)//if subject list is chosen 
            {
                tp_1.setEnabledAt(2, true);
                tp_1.setSelectedIndex(2);
                show_subjects();//with sub details filled
                disable_buttons(cd1_ld);
                disable_buttons(cd1_ld);
                disable_buttons(jp_pres);
                enable_buttons(cd1_subdetails);

            } else {

            }

            tf_namesec.setText(sem[sem_i - 1].sect_array[sec_i - 1]);
            if (sem[sem_i - 1].sec[sec_i - 1].nol == 0) {
                tf_l1.setVisible(false);
                tf_l2.setVisible(false);
                tf_l3.setVisible(false);
                tf_l4.setVisible(false);
                tf_ln1.setVisible(false);
                tf_ln2.setVisible(false);
                tf_ln3.setVisible(false);
                tf_ln4.setVisible(false);
                ljc_1.setVisible(false);
                ljc_2.setVisible(false);
                ljc_3.setVisible(false);
                ljc_4.setVisible(false);
                b_saveld.setVisible(false);
                jLabel13.setVisible(false);
                jLabel14.setVisible(false);

            }
            if (sem[sem_i - 1].sec[sec_i - 1].lab_save == false) {
                cd1_ld.setVisible(true);
                cd2_ld.setVisible(false);
                display_lab_fields();
                enable_buttons(cd1_ld);
                b_saveld.setEnabled(true);
                b_nolsub.setEnabled(true);

            } else {

                cd2_ld.setVisible(true);
                cd1_ld.setVisible(false);
                if (sem[sem_i - 1].sec[sec_i - 1].labdone == false) {
                    flush_rows(tble_lp);

                } else {
                    flush_rows(tble_lp);
                    display_lab_table();
                }
                //enable_buttons(cd2_ld);
                //disable_buttons(cd1_ld);

            }
            tf_nol.setText(Integer.toString(sem[sem_i - 1].sec[sec_i - 1].nol));

            if (tss.equals(priority_lock)) {
                tp_1.setEnabledAt(3, true);
                tp_1.setSelectedIndex(3);

            } else {
                flush_rows(priority_table);
                display_priority_table();
                if (sem[sem_i - 1].sec[sec_i - 1].subdone == true) {
                    tp_1.setEnabledAt(3, true);

                }

            }

            if (sem[sem_i - 1].sec[sec_i - 1].prioritydone == true) {

                tp_1.setEnabledAt(4, true);
                tp_1.setSelectedIndex(4);
                disable_buttons(jp_priority);
                disable_buttons(cd1_ld);
                disable_buttons(cd2_ld);
                disable_buttons(jp_pres);
                disable_buttons(cd1_subdetails);

            } else {

            }

            if (sem[sem_i - 1].sec[sec_i - 1].generatedone) {
                tp_1.setEnabledAt(4, true);
                tp_1.setSelectedIndex(4);
                disable_buttons(cd1_ld);
                disable_buttons(cd2_ld);
                disable_buttons(jp_pres);
                disable_buttons(cd1_subdetails);
                disable_buttons(jp_priority);
                /*  for (int i = 0; i < 4; i++) {
                 tp_1.setEnabledAt(i, false);
                 }*/
            } else {

            }
            if (sem[sem_i - 1].sec[sec_i - 1].dusted == true) {
                jButton9.setEnabled(false);
                //jButton6.setEnabled(false);
            } else {
                jButton9.setEnabled(true);
                //jButton6.setEnabled(true);

            }

        }

        get_prev_semnsec();

    }

    private void disable_buttons(JPanel pane) {
        Component[] c = pane.getComponents();
        for (int i = 0; i < cd2_ld.getComponentCount(); i++) {
            try {
                JButton j = (JButton) c[i];
                j.setEnabled(false);
            } catch (Exception e) {
                continue;
            }
        }

    }

    private void enable_buttons(JPanel pane) {
        Component[] c = pane.getComponents();
        for (int i = 0; i < cd2_ld.getComponentCount(); i++) {
            try {
                JButton j = (JButton) c[i];
                j.setEnabled(true);
            } catch (Exception e) {
                continue;
            }
        }

    }
}
