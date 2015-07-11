package edu.buffalo.cse605.legacy;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("unchecked")

public class LambdaUI extends JPanel {

  private static final long serialVersionUID = -610832332802571450L;

  private Term tt = new Term();
  private Term tt2 = new Term();
  private Vector env = new Vector();
  private Vector envident = new Vector();
  private boolean b1 = false;
  private boolean b2 = false;
  private JTextField input;
  private JTextArea output;
  private JComboBox operation;

  public LambdaUI() {

    setLayout(new GridBagLayout());
    setBorder(new EmptyBorder(10, 10, 10, 10));
    // the layout constraints
    GridBagConstraints c = new GridBagConstraints();

    // COMPONENT AT 0,0
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.1;
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 1;
    add(new JLabel(), c);

    // COMPONENT AT 0,1
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.1;
    c.gridx = 1;
    c.gridy = 0;
    c.gridwidth = 1;
    c.ipadx = 20;
    c.insets = new Insets(15, 0, 0, 0);
    c.anchor = GridBagConstraints.BASELINE_TRAILING;
    add(new JLabel("operation:"), c);

    // COMPONENT AT 0,2
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.1;
    c.gridx = 2;
    c.gridy = 0;
    c.gridwidth = 1;
    c.ipadx = 0;
    c.anchor = GridBagConstraints.BASELINE_LEADING;
    add(createComputeCombo(), c);

    // COMPONENT AT 0,4
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.1;
    c.gridx = 4;
    c.gridy = 0;
    c.gridwidth = 1;
    add(new JLabel(), c);

    // COMPONENT AT 1,1
    c.fill = GridBagConstraints.NONE;
    c.weightx = 0.1;
    c.gridx = 1;
    c.gridy = 1;
    c.gridwidth = 1;
    c.ipadx = 20;
    c.insets = new Insets(15, 0, 0, 0);
    c.anchor = GridBagConstraints.BASELINE_TRAILING;
    add(new JLabel("λ-expression:"), c);

    // COMPONENT AT 1,2..3
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 1;
    c.gridwidth = 2;
    c.ipadx = 0;
    c.insets = new Insets(15, 0, 0, 4);
    c.anchor = GridBagConstraints.BASELINE_LEADING;
    add(createInputText(), c);

    // COMPONENT AT 2,2..3
    c.fill = GridBagConstraints.NONE;
    c.gridx = 2;
    c.gridy = 2;
    c.gridwidth = 2;
    c.ipadx = 0;
    c.insets = new Insets(0, 0, 0, 0);
    c.anchor = GridBagConstraints.BASELINE_TRAILING;
    add(createInputButtons(), c);

    // COMPONENT AT 3,1..3
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1;
    c.weighty = 1;
    c.gridx = 1;
    c.gridy = 3;
    c.gridwidth = 3;
    c.ipadx = 0;
    c.insets = new Insets(4, 4, 4, 4);
    c.anchor = GridBagConstraints.CENTER;
    add(createOutputArea(), c);
  }

  private JComponent createInputButtons() {

    JPanel panel = new JPanel();
    panel.setBorder(new EmptyBorder(0, 0, 0, 0));
    panel.add(createComputeButton());
    panel.add(createLoadButton());
    return panel;
  }

  private JComponent createComputeButton() {

    JButton result = new JButton("Compute");
    result.setToolTipText("F9");
    result.setForeground(Color.black);
    result.setBackground(Color.lightGray);
    result.addActionListener(new ComputeActionListener());
    return result;
  }

  private JComponent createOutputArea() {

    JScrollPane pane = new JScrollPane(createOutputText());
    pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    return pane;
  }

  private JTextArea createOutputText() {

    output = new JTextArea(10, 72);
    output.setEditable(false);
    output.setBackground(Color.black);
    output.setForeground(Color.yellow);
    output.setFont(new Font("monospaced", Font.PLAIN, 14));
    return output;
  }

  private JTextField createInputText() {

    input = new JTextField(50);
    input.setFont(new Font("monospaced", Font.PLAIN, 14));
    return input;
  }

  private JComponent createComputeCombo() {

    operation = new JComboBox();
    operation.setEditable(false);
    operation.addItem("ALPHA");
    operation.addItem("DERIVE");
    operation.addItem("LET");
    operation.addItem("LETREC");
    operation.addItem("NORM");
    operation.addItem("STEP");
    operation.addItem("SUBST");
    return operation;
  }

  private JButton createLoadButton() {

    JButton result = new JButton("Load File...");
    result.addActionListener(new LoadFileActionListener(this));
    return result;
  }

  private class ComputeActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

      tt = new Term();
      tt2 = new Term();
      String oldtext = output.getText();
      String choice = (String) operation.getSelectedItem();
      String intext = input.getText().trim();
      // Check the operation
      if (choice.equals("ALPHA")) {
        String inputstr = intext;
        int loc = inputstr.indexOf("=");
        output.setText("ALPHA: " + inputstr + "\n");
        if (loc < 2)
          output.setText(oldtext + "Error - Expected '=' between two terms\n\n");
        else {
          b1 = tt.parsetoterm((inputstr.substring(0, loc)).trim());
          b2 = tt2.parsetoterm((inputstr.substring(loc + 1)).trim());
          if ((b1 && b2) == false) {
            output.setText(output.getText() + "Error - One or more bad terms.\n\n");
          }
          else {
            Vector b;
            b = new Vector();
            oldtext = output.getText();
            if (tt.Alpha(tt2, b) == true)
              output.setText(oldtext + "==> TRUE\n\n");
            else
              output.setText(oldtext + "==> FALSE\n\n");
          }
        }
      }
      if (choice.equals("DERIVE")) {
        b1 = tt.parsetoterm(intext);
        Vector blist = new Vector();
        String st = new String();
        SizeTerm ts;
        output.setText("DERIVE:\n>>> " + input.getText() + "\n");
        if (b1 == false)
          output.setText(output.getText() + "Bad term.");
        else {
          while (tt.Redexes(env, envident, blist)) {
            ts = tt.Step_Size(env, envident, blist, 0);
            st = tt.showterm();
            if (ts.n == true)
              output.setText(output.getText() + "\n=   " + st + "\n");
            else {
              String st2;
              st2 = tt.Underline("    ", ts.a, ts.b);
              output.setText(output.getText() + st2);
              output.setText(output.getText() + "\n==> " + st + "\n");
            }
            tt = null;
            tt = new Term();
            tt.parsetoterm(st);
          }
          output.setText(output.getText() + "\n");
        }
      }
      if (choice.equals("LET")) {
        String inputstr = intext;
        int loc = inputstr.indexOf("=");
        if (loc < 2)
          output.setText("LET Error - Expected '=' between an identifier and a term.\n\n");
        else {
          envident.insertElementAt((inputstr.substring(0, loc)).trim(), 0);
          b1 = tt.parsetoterm((inputstr.substring(loc + 1)).trim());
          if (b1 == false)
            output.setText(output.getText() + "Bad term.");
          else {
            env.insertElementAt(tt, 0);
            output.setText("LET: Added identifier " + ((inputstr.substring(0, loc)).trim())
                + " to environment.\n\n");
          }
        }
      }
      if (choice.equals("LETREC")) {
        String inputstr = intext;
        int loc = inputstr.indexOf("=");
        if (loc < 2)
          output.setText("LETREC Error - Expected '=' between an identifier and a term.\n\n");
        else {
          envident.insertElementAt((inputstr.substring(0, loc)).trim(), 0);
          b1 = tt.parserectoterm((inputstr.substring(loc + 1)).trim(), (inputstr.substring(0, loc))
              .trim());
          if (b1 == false)
            output.setText(output.getText() + "Bad term.");
          else {
            env.insertElementAt(tt, 0);
            output.setText("LETREC: Added identifier " + ((inputstr.substring(0, loc)).trim())
                + " to environment.\n\n");
          }
        }
      }
      if (choice.equals("NORM")) {
        b1 = tt.parsetoterm(intext);
        Vector b;
        b = new Vector();
        String st;
        if (b1 == false)
          output.setText(output.getText() + "Bad term.");
        else {
          while (tt.Redexes(env, envident, b)) {
            tt.Step(env, envident, b);
            st = tt.showterm();
            tt = null;
            tt = new Term();
            tt.parsetoterm(st);
          }
          output.setText("NORM: " + intext + "\n==> " + tt.showterm() + "\n\n");
        }
      }
      if (choice.equals("STEP")) {
        b1 = tt.parsetoterm(intext);
        Vector b;
        b = new Vector();
        if (b1 == false)
          output.setText(output.getText() + "Bad term.");
        else {
          tt.Step(env, envident, b);
          output.setText("STEP: " + intext + "\n==> " + tt.showterm() + "\n\n");
        }
      }
      if (choice.equals("SUBST")) {
        String inputstr = intext;
        int loc1 = inputstr.indexOf("[");
        int loc2 = inputstr.indexOf("<");
        int loc3 = inputstr.indexOf("]");
        if (loc1 < 2 || loc2 < loc1 || loc3 < loc2)
          output.setText("SUBST: " + intext + "\nError - Incorrect substitution syntax.\n\n");
        else {
          b1 = tt.parsetoterm((inputstr.substring(0, loc1)).trim());
          b2 = tt2.parsetoterm((inputstr.substring(loc2 + 2, loc3)).trim());
          if (b1 == false)
            output.setText(output.getText() + "Bad term.");
          else {
            String v = ((inputstr.substring(loc1 + 1, loc2)).trim());
            tt.Subst(v, tt2);
            output.setText("SUBST: " + intext + "\n==> " + tt.showterm() + "\n\n");
          }
        }
      }
    }
  }

  private class LoadFileActionListener implements ActionListener {

    private Component parent;

    public LoadFileActionListener(Component parent) {

      this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      int loc1;
      BufferedReader r = null;
      // Pulls up a FileDialog to pick a file
      JFileChooser fd = new JFileChooser();
      fd.setFileSelectionMode(JFileChooser.FILES_ONLY);
      if (fd.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
        output.setText("Loading file...");
        try {
          r = new BufferedReader(new InputStreamReader(new FileInputStream(fd.getSelectedFile())));
        }
        catch (FileNotFoundException g) {
          output.setText("File not found.");
          return;
        }
      }
      else {
        return;
      }
      try {
        String stin;
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
      output.setText(output.getText() + "\nLoad complete.");
    }
  }
}
