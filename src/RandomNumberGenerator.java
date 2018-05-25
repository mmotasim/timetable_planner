/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
 public class RandomNumberGenerator implements Serializable
{
 
      int p,i;
      int []r=new int[6];
      void randomgenerator(int n)
      {
          for( i=0;i<n;i++)
          {
              r[i]=i;
          }
          for(i=0;i<5;i++)
          {
              p=(int)(Math.random()*5);
             
               long time=System.nanoTime();
              int q=(int)(Math.random()*(time%5));
              int s=(int)(Math.random()*i);
            //   System.out.println("time");
            //  System.out.println(time);
              int t=r[p];
              r[p]=r[q];
              r[q]=t;
              t=r[q];
              r[q]=r[s];
              r[s]=t;
          }
    }
    public int pRandom(int i) {
      return r[i] ;
    }
}
