
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mothi
 */
public class genrate_doc {
    
    void create_tt_word(String fname,String[][]tt) throws FileNotFoundException, IOException{
        XWPFDocument doc= new  XWPFDocument();
        
        
         changeOrientation(doc, "landscape");
        XWPFTable table=doc.createTable(7,10);
        
        table.setCellMargins(200, 200, 200, 200);
       
        XWPFTableRow row0 = table.getRow(0);
        XWPFTableCell r0_cell0 = row0.getCell(0);
        r0_cell0.setText("DAY/TIME");
         XWPFTableRow row1 = table.getRow(1);
         XWPFTableCell r1_cell0 = row1.getCell(0);
         r1_cell0.setText("MON");
         XWPFTableRow row2 = table.getRow(2);
         XWPFTableCell r2_cell0 = row2.getCell(0);
          r2_cell0.setText("TUE");
          XWPFTableRow row3 = table.getRow(3);
         XWPFTableCell r3_cell0 = row3.getCell(0);
          r3_cell0.setText("WED");
           XWPFTableRow row4 = table.getRow(4);
         XWPFTableCell r4_cell0 = row4.getCell(0);
          r4_cell0.setText("THR");
            XWPFTableRow row5 = table.getRow(5);
         XWPFTableCell r5_cell0 = row5.getCell(0);
          r5_cell0.setText("FRI");
            XWPFTableRow row6 = table.getRow(6);
         XWPFTableCell r6_cell0 = row6.getCell(0);
          r6_cell0.setText("SAT");
          
          
          XWPFTableCell r0_cell1 = row0.getCell(1);
            r0_cell1.setText("08:40-09:40");
            XWPFTableCell r0_cell2 = row0.getCell(2);
            r0_cell2.setText("09:40-10:40");
            XWPFTableCell r0_cell3 = row0.getCell(3);
            r0_cell3.setText("10:40-11:00");
            XWPFTableCell r0_cell4 = row0.getCell(4);
            r0_cell4.setText("11:00-12:00");
            XWPFTableCell r0_cell5 = row0.getCell(5);
            r0_cell5.setText("12:00-01:00");
            XWPFTableCell r0_cell6 = row0.getCell(6);
            r0_cell6.setText("01:00-01:40");
            XWPFTableCell r0_cell7 = row0.getCell(7);
            r0_cell7.setText("01:40-02:30");
            XWPFTableCell r0_cell8 = row0.getCell(8);
            r0_cell8.setText("02:30-03:20");
            XWPFTableCell r0_cell9 = row0.getCell(9);
            r0_cell9.setText("03:20-04:10");
            
            // writing the time table
           /* XWPFTableCell r1_cell1 = row1.getCell(1); 
            r1_cell1.setText(tt[0][0]);
            XWPFTableCell r1_cell2 = row1.getCell(2); 
            r1_cell2.setText(tt[0][1]);
            XWPFTableCell r1_cell3 = row1.getCell(3); 
            r1_cell3.setText(tt[0][2]);
            
            XWPFTableCell r1_cell4 = row1.getCell(4); 
            r1_cell4.setText("B");
           
            XWPFTableCell r1_cell5 = row1.getCell(5); 
             r1_cell5.setText(tt[0][3]);
             XWPFTableCell r1_cell6 = row1.getCell(6); 
             r1_cell6.setText(tt[0][4]);*/
            XWPFTableRow row;
             XWPFTableCell cell;
            for(int i=0;i<6;i++)
            {
                  row = table.getRow(i+1);
                for(int j=0;j<9;j++)
                {
                    cell=row.getCell(j+1);
                    cell.setText(tt[i][j]);
                }
            }
            
            
            
            
          
          
        FileOutputStream output= new FileOutputStream("timetable\\"+fname+".docx");
        doc.write(output);
        output.close();
    }
    void changeOrientation(XWPFDocument document, String orientation){
    CTDocument1 doc = document.getDocument();
    CTBody body = doc.getBody();
    CTSectPr section = body.addNewSectPr();
    XWPFParagraph para = document.createParagraph();
    CTP ctp = para.getCTP();
    CTPPr br = ctp.addNewPPr();
    br.setSectPr(section);
     CTPageSz pageSize;
    if (section.isSetPgSz()) {
       pageSize = section.getPgSz();
    } else {
       pageSize = section.addNewPgSz();
    }
    if(orientation.equals("landscape")){
        pageSize.setOrient(STPageOrientation.LANDSCAPE);
        pageSize.setW(BigInteger.valueOf(842 * 20));
        pageSize.setH(BigInteger.valueOf(595 * 20));
    }
    else{
        pageSize.setOrient(STPageOrientation.PORTRAIT);
        pageSize.setH(BigInteger.valueOf(842 * 20));
        pageSize.setW(BigInteger.valueOf(595 * 20));
    }
}
    void teach_doc(String fname,String teach[][]) throws FileNotFoundException, IOException
    {
        File fp=new File("teacher files\\"+fname+".docx");
        if(fp.exists())
        {
           return; 
        }
         XWPFDocument doc= new  XWPFDocument();
        
        
         changeOrientation(doc, "landscape");
        XWPFTable table=doc.createTable(7,10);
        
        table.setCellMargins(200, 200, 200, 200);
       
        XWPFTableRow row0 = table.getRow(0);
        XWPFTableCell r0_cell0 = row0.getCell(0);
        r0_cell0.setText("DAY/TIME");
         XWPFTableRow row1 = table.getRow(1);
         XWPFTableCell r1_cell0 = row1.getCell(0);
         r1_cell0.setText("MON");
         XWPFTableRow row2 = table.getRow(2);
         XWPFTableCell r2_cell0 = row2.getCell(0);
          r2_cell0.setText("TUE");
          XWPFTableRow row3 = table.getRow(3);
         XWPFTableCell r3_cell0 = row3.getCell(0);
          r3_cell0.setText("WED");
           XWPFTableRow row4 = table.getRow(4);
         XWPFTableCell r4_cell0 = row4.getCell(0);
          r4_cell0.setText("THR");
            XWPFTableRow row5 = table.getRow(5);
         XWPFTableCell r5_cell0 = row5.getCell(0);
          r5_cell0.setText("FRI");
            XWPFTableRow row6 = table.getRow(6);
         XWPFTableCell r6_cell0 = row6.getCell(0);
          r6_cell0.setText("SAT");
          
          
          XWPFTableCell r0_cell1 = row0.getCell(1);
            r0_cell1.setText("08:40-09:40");
            XWPFTableCell r0_cell2 = row0.getCell(2);
            r0_cell2.setText("09:40-10:40");
            XWPFTableCell r0_cell3 = row0.getCell(3);
            r0_cell3.setText("10:40-11:00");
            XWPFTableCell r0_cell4 = row0.getCell(4);
            r0_cell4.setText("11:00-12:00");
            XWPFTableCell r0_cell5 = row0.getCell(5);
            r0_cell5.setText("12:00-01:00");
            XWPFTableCell r0_cell6 = row0.getCell(6);
            r0_cell6.setText("01:00-01:40");
            XWPFTableCell r0_cell7 = row0.getCell(7);
            r0_cell7.setText("01:40-02:30");
            XWPFTableCell r0_cell8 = row0.getCell(8);
            r0_cell8.setText("02:30-03:20");
            XWPFTableCell r0_cell9 = row0.getCell(9);
            r0_cell9.setText("03:20-04:10");
            
            // writing the time table
           /* XWPFTableCell r1_cell1 = row1.getCell(1); 
            r1_cell1.setText(tt[0][0]);
            XWPFTableCell r1_cell2 = row1.getCell(2); 
            r1_cell2.setText(tt[0][1]);
            XWPFTableCell r1_cell3 = row1.getCell(3); 
            r1_cell3.setText(tt[0][2]);
            
            XWPFTableCell r1_cell4 = row1.getCell(4); 
            r1_cell4.setText("B");
           
            XWPFTableCell r1_cell5 = row1.getCell(5); 
             r1_cell5.setText(tt[0][3]);
             XWPFTableCell r1_cell6 = row1.getCell(6); 
             r1_cell6.setText(tt[0][4]);*/
            XWPFTableRow row;
             XWPFTableCell cell;
            for(int i=0;i<6;i++)
            {
                  row = table.getRow(i+1);
                for(int j=0;j<9;j++)
                {
                    cell=row.getCell(j+1);
                    cell.setText(teach[i][j]);
                }
            }
            
            
            
            
          
          
        FileOutputStream output= new FileOutputStream("teacher files\\"+fname+".docx");
        doc.write(output);
        output.close();
    }
    
}
