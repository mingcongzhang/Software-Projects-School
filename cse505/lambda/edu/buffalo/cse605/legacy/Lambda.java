package edu.buffalo.cse605.legacy;

/******************************************/
/* The Lambda Calculus Package for Java   */
/* CSE 505 Assignment 5                   */
/* December 8, 1999                       */
/* Filename: Lambda.java                  */
/*                                        */
/* Authors:                               */
/* Adrienne Antkowiak (ama5 2653-8564)    */
/* Eric Decker (ejdecker 2672-8081)       */
/******************************************/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import edu.buffalo.cse605.legacy.Term.*;

@SuppressWarnings("unchecked")
public class Lambda {

  private static Term tt;
  private static Term tt2;
  private static Vector env;
  private static Vector envident;
  private static TextArea result_text;
  private static TextArea input_text;
  private static Choice compute_choice;
  private static Button load_button;
  private static Button compute_button;
  private static FileDialog fd;
  private static Frame f;
  private static Panel ff;
  private static BufferedReader r;
  private static String stin;
  private static boolean b1;
  private static boolean b2;

  /* main function draws screen and handles events */
  public static void main(String argv[]) {

    Font font;
    stin = new String();
    env = new Vector();
    envident = new Vector();
    tt = new Term();
    tt2 = new Term();
    font = new Font("Courier", Font.PLAIN, 14);
    ff = new Panel();
    f = new Frame();
    f.setTitle("The Lambda Calculus Package");
    ff.setBackground(Color.white);
    load_button = new Button("Load File");
    load_button.setForeground(Color.black);
    load_button.setBackground(Color.lightGray);
    load_button.setLocation(10000, 100);
    load_button.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        int loc1;
        // Pulls up a FileDialog to pick a file
        fd = new FileDialog(f, "Load environment definitions");
        fd.setVisible(true);
        result_text.setText("Loading file...");
        try {
          r = new BufferedReader(new InputStreamReader(new FileInputStream(fd.getDirectory()
              + fd.getFile())));
        }
        catch (FileNotFoundException g) {
          result_text.setText("File not found.");
        }
        try {
          while ((stin = r.readLine()) != null) {
            if (stin.length() > 7) {
              // A line is no good if it is not > 7 characters
              if (stin.substring(0, 6).equals("letrec")) {
                // Parses a recursive term
                stin = stin.substring(7);
                loc1 = stin.indexOf("=");
                if (loc1 < 1 || loc1 - 2 > stin.length()) {
                }
                else {

                  envident.insertElementAt((stin.substring(0, loc1)).trim(), 0);
                  tt = null;
                  tt = new Term();
                  tt.parserectoterm((stin.substring(loc1 + 1)).trim(), (stin.substring(0, loc1))
                      .trim());
                  env.insertElementAt(tt, 0);
                }
              }
              if (stin.substring(0, 3).equals("let")) {
                // Parses a regular term
                stin = stin.substring(4);
                loc1 = stin.indexOf("=");
                if (loc1 < 1 || loc1 - 2 > stin.length()) {
                }
                else {
                  envident.insertElementAt((stin.substring(0, loc1)).trim(), 0);
                  tt = null;
                  tt = new Term();
                  tt.parsetoterm((stin.substring(loc1 + 1)).trim());
                  env.insertElementAt(tt, 0);
                }
              }

            }
          }
        }
        catch (IOException h) {
        }
        result_text.setText(result_text.getText() + "\nLoad complete.");
      }
    });
    ff.add(load_button);
    compute_choice = new Choice();
    compute_choice.addItem("ALPHA");
    compute_choice.addItem("DERIVE");
    compute_choice.addItem("LET");
    compute_choice.addItem("LETREC");
    compute_choice.addItem("NORM");
    compute_choice.addItem("STEP");
    compute_choice.addItem("SUBST");
    compute_choice.setForeground(Color.black);
    compute_choice.setBackground(Color.lightGray);
    ff.add(compute_choice);
    input_text = new TextArea(1, 47);
    input_text.setForeground(Color.blue);
    input_text.setBackground(Color.white);
    input_text.setFont(font);
    ff.add(input_text);
    result_text = new TextArea(10, 55);
    result_text.setBackground(Color.white);
    result_text.setForeground(Color.red);
    result_text.setFont(font);
    ff.add(result_text);
    compute_button = new Button("Compute");
    compute_button.setForeground(Color.black);
    compute_button.setBackground(Color.lightGray);
    compute_button.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent e) {

        tt = null;
        tt2 = null;
        tt = new Term();
        tt2 = new Term();
        String oldtext = result_text.getText();
        String choice = compute_choice.getSelectedItem();
        String intext = (input_text.getText()).trim();
        // Check the operation
        if (choice.equals("ALPHA")) {
          String inputstr = intext;
          int loc = inputstr.indexOf("=");
          result_text.setText("ALPHA: " + inputstr + "\n");
          if (loc < 2)
            result_text.setText(oldtext + "Error - Expected '=' between two terms\n\n");
          else {
            b1 = tt.parsetoterm((inputstr.substring(0, loc)).trim());
            b2 = tt2.parsetoterm((inputstr.substring(loc + 1)).trim());
            if ((b1 && b2) == false) {
              result_text.setText(result_text.getText() + "Error - One or more bad terms.\n\n");
            }
            else {
              Vector b;
              b = new Vector();
              oldtext = result_text.getText();
              if (tt.Alpha(tt2, b) == true)
                result_text.setText(oldtext + "==> TRUE\n\n");
              else
                result_text.setText(oldtext + "==> FALSE\n\n");
            }
          }
        }
        if (choice.equals("DERIVE")) {
          b1 = tt.parsetoterm(intext);
          Vector blist;
          blist = new Vector();
          String st;
          st = new String();
          SizeTerm ts;
          result_text.setText("DERIVE:\n>>> " + input_text.getText() + "\n");
          if (b1 == false)
            result_text.setText(result_text.getText() + "Bad term.");
          else {
            while (tt.Redexes(env, envident, blist)) {
              ts = tt.Step_Size(env, envident, blist, 0);
              st = tt.showterm();
              if (ts.n == true)
                result_text.setText(result_text.getText() + "\n=   " + st + "\n");
              else {
                String st2;
                st2 = tt.Underline("    ", ts.a, ts.b);
                result_text.setText(result_text.getText() + st2);
                result_text.setText(result_text.getText() + "\n==> " + st + "\n");
              }
              tt = null;
              tt = new Term();
              tt.parsetoterm(st);
            }
            result_text.setText(result_text.getText() + "\n");
          }
        }
        if (choice.equals("LET")) {
          String inputstr = intext;
          int loc = inputstr.indexOf("=");
          if (loc < 2)
            result_text.setText("LET Error - Expected '=' between an identifier and a term.\n\n");
          else {
            envident.insertElementAt((inputstr.substring(0, loc)).trim(), 0);
            b1 = tt.parsetoterm((inputstr.substring(loc + 1)).trim());
            if (b1 == false)
              result_text.setText(result_text.getText() + "Bad term.");
            else {
              env.insertElementAt(tt, 0);
              result_text.setText("LET: Added identifier " + ((inputstr.substring(0, loc)).trim())
                  + " to environment.\n\n");
            }
          }
        }
        if (choice.equals("LETREC")) {
          String inputstr = intext;
          int loc = inputstr.indexOf("=");
          if (loc < 2)
            result_text
                .setText("LETREC Error - Expected '=' between an identifier and a term.\n\n");
          else {
            envident.insertElementAt((inputstr.substring(0, loc)).trim(), 0);
            b1 = tt.parserectoterm((inputstr.substring(loc + 1)).trim(), (inputstr
                .substring(0, loc)).trim());
            if (b1 == false)
              result_text.setText(result_text.getText() + "Bad term.");
            else {
              env.insertElementAt(tt, 0);
              result_text.setText("LETREC: Added identifier "
                  + ((inputstr.substring(0, loc)).trim()) + " to environment.\n\n");
            }
          }
        }
        if (choice.equals("NORM")) {
          b1 = tt.parsetoterm(intext);
          Vector b;
          b = new Vector();
          String st;
          if (b1 == false)
            result_text.setText(result_text.getText() + "Bad term.");
          else {
            while (tt.Redexes(env, envident, b)) {
              tt.Step(env, envident, b);
              st = tt.showterm();
              tt = null;
              tt = new Term();
              tt.parsetoterm(st);
            }
            result_text.setText("NORM: " + intext + "\n==> " + tt.showterm() + "\n\n");
          }
        }
        if (choice.equals("STEP")) {
          b1 = tt.parsetoterm(intext);
          Vector b;
          b = new Vector();
          if (b1 == false)
            result_text.setText(result_text.getText() + "Bad term.");
          else {
            tt.Step(env, envident, b);
            result_text.setText("STEP: " + intext + "\n==> " + tt.showterm() + "\n\n");
          }
        }
        if (choice.equals("SUBST")) {
          String inputstr = intext;
          int loc1 = inputstr.indexOf("[");
          int loc2 = inputstr.indexOf("<");
          int loc3 = inputstr.indexOf("]");
          if (loc1 < 2 || loc2 < loc1 || loc3 < loc2)
            result_text
                .setText("SUBST: " + intext + "\nError - Incorrect substitution syntax.\n\n");
          else {
            b1 = tt.parsetoterm((inputstr.substring(0, loc1)).trim());
            b2 = tt2.parsetoterm((inputstr.substring(loc2 + 2, loc3)).trim());
            if (b1 == false)
              result_text.setText(result_text.getText() + "Bad term.");
            else {
              String v = ((inputstr.substring(loc1 + 1, loc2)).trim());
              tt.Subst(v, tt2);
              result_text.setText("SUBST: " + intext + "\n==> " + tt.showterm() + "\n\n");
            }
          }
        }
      }
    });
    ff.add(compute_button);
    f.add(ff);
    f.setSize(750, 275);
    f.setVisible(true);
  }
}
