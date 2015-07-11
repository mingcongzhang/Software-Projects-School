package edu.buffalo.cse605.legacy;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Launcher {

  private static void createAndShowGUI() {

    JDialog dialog = new JDialog();
    dialog.setPreferredSize(new Dimension(640, 480));
    dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    dialog.setTitle("The Lambda Calculus Package");
    dialog.add(new LambdaUI());
    dialog.pack();
    dialog.setVisible(true);
  }

  public static void main(String[] args) {

    try {
      // Set System L&F
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (UnsupportedLookAndFeelException e) {
      // handle exception
    }
    catch (ClassNotFoundException e) {
      // handle exception
    }
    catch (InstantiationException e) {
      // handle exception
    }
    catch (IllegalAccessException e) {
      // handle exception
    }

    // Schedule a job for the event dispatch thread:
    // creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {

      public void run() {

        createAndShowGUI();
      }
    });
  }
}