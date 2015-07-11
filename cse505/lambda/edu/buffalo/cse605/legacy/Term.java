package edu.buffalo.cse605.legacy;

/******************************************/
/* The Lambda Calculus Package for Java   */
/* CSE 505 Assignment 5                   */
/* December 8, 1999                       */
/* Filename: Term.java                    */
/*                                        */
/* Authors:                               */
/* Adrienne Antkowiak (ama5 2653-8564)    */
/* Eric Decker (ejdecker 2672-8081)       */
/******************************************/

import java.util.*;

/* CLASS Var: Represents a variable term */
class Var {

  public String s;
}

/* CLASS Abst: Represents an abstraction term */
class Abst {

  public String s;
  public Term t;
}

/* CLASS App: Represents an application */
class App {

  public Term t1;
  public Term t2;
}

/*
 * CLASS SizeTerm: Returned by the Step_Size function for DERIVE operations. Int a that specifies
 * how many spaces in to start underlining a beta or eta reduction for a term, and int b. specifying
 * how many dashes to underline a beta or eta reduction. Boolean n represents if a environment
 * variable was subsituted in the Step_Size function.
 */

class SizeTerm {

  public int a;
  public int b;
  public String c;
  public boolean n = false;
}

@SuppressWarnings("unchecked")
class Term {

  /* FUNCTION SetVar: Sets Term class as a Variable */
  public void SetVar() {

    V = new Var();
    V.s = new String();
    L = null;
    A = null;
  }

  /* FUNCTION SetAbst: Sets Term class as an abstraction */
  public void SetAbst() {

    V = null;
    L = new Abst();
    L.t = new Term();
    L.s = new String();
    A = null;
  }

  /* FUNCTION SetApp: Sets Term class as an application */
  public void SetApp() {

    V = null;
    L = null;
    A = new App();
    A.t1 = new Term();
    A.t2 = new Term();
  }

  /*
   * FUNCTION appparse: Returns the position of the space between two terms in an abstract
   */
  public int appparse(String s) {

    int leftp = 0;
    int rightp = 0;
    for (int i = 0; i < s.length(); i++) {
      switch (s.charAt(i)) {
        case '(':
          leftp++;
          break;
        case ')':
          rightp++;
          break;
        case ' ':
          if (leftp == (rightp + 1))
            return i;
          break;
      }
    }
    return 0;
  }

  /*
   * FUNCTION BuildChurch: Recursively creates a term for church numerals
   */
  public void BuildChurch(int c) {

    if (c == 0) {
      SetVar();
      V.s = "x";
    }
    else {
      SetApp();
      A.t1.SetVar();
      A.t1.V.s = "f";
      A.t2.BuildChurch(c - 1);
    }
  }

  /* FUNCTION Church: Called by parsetoterm in building church numerals */
  public void Church(int c) {

    SetAbst();
    L.s = "f";
    L.t.SetAbst();
    L.t.L.s = "x";
    L.t.L.t.BuildChurch(c);
  }

  /*
   * FUNCTION parsetoterm: Recursively constructs a term based on the contents of String s
   */
  public boolean parsetoterm(String s) {

    Character f;
    int loc;
    Term tt;
    tt = new Term();
    Integer conv;
    f = new Character(s.charAt(0));
    switch (f.charValue()) {
      case 'L':
        loc = s.indexOf(".");
        if (loc < 1 || loc - 1 > s.length())
          return false;
        SetAbst();
        L.s = s.substring(1, loc);
        return L.t.parsetoterm(s.substring(loc + 1));
      case '(':
        loc = appparse(s);
        if (loc == 0 || loc - 1 > s.length())
          return false;
        SetApp();
        return A.t1.parsetoterm(s.substring(1, loc))
            && A.t2.parsetoterm(s.substring(loc + 1, s.length() - 1));
    }
    if (f.isLetter(f.charValue())) {
      SetVar();
      V.s = s;
      return true;
    }
    if (f.isDigit(f.charValue())) {
      conv = new Integer(0);
      Church(conv.parseInt(s));
      return true;
    }
    return false;
  }

  /*
   * FUNCTION parserectoterm: Builds a recursive term based on the contents of Strings s and ident
   * (the term's identifier)
   */
  public boolean parserectoterm(String s, String ident) {

    Term tt1;
    Term tt2;
    boolean b1;
    boolean b2;
    tt1 = new Term();
    tt2 = new Term();
    b1 = tt1.parsetoterm("Lf.(Lx.(f (x x)) Lx.(f (x x)))");
    b2 = tt2.parsetoterm("L" + ident + "." + s);
    if ((b1 && b2) == false)
      return false;
    SetApp();
    A.t1 = tt1;
    A.t2 = tt2;
    return true;
  }

  /*
   * FUNCTION showterm: Returns a string representing the contents of the Term
   */
  public String showterm() {

    String s;
    s = new String("");
    if (V != null) {
      s = s + V.s;
      return s;
    }
    else {
      if (L != null) {
        s = s + "L";
        s = s + L.s;
        s = s + ".";
        s = s + L.t.showterm();
        return s;
      }
      else {
        s = s + "(";
        s = s + A.t1.showterm();
        s = s + " ";
        s = s + A.t2.showterm();
        s = s + ")";
        return s;
      }
    }
  }

  /* FUNCTION TermType: Returns a char based on the type of the term */
  public char TermType() {

    if (V != null)
      return 'V';
    if (L != null)
      return 'L';
    return 'A';
  }

  /*
   * FUNCTION Alpha: Returns true if the Term class is alpha equivalent to the argument Term sub
   */
  public boolean Alpha(Term sub, Vector bind) {

    if (TermType() == 'V' && sub.TermType() == 'V') {
      if (bind.isEmpty() == true)
        return V.s.equals(sub.V.s);
      for (int i = 0; i < bind.size(); i = i + 2) {
        if (V.s.equals(bind.elementAt(i)))
          return sub.V.s.equals(bind.elementAt(i + 1));
        if (sub.V.s.equals(bind.elementAt(i + 1)))
          return V.s.equals(bind.elementAt(i));
      }
      return V.s.equals(sub.V.s);
    }
    if (TermType() == 'L' && sub.TermType() == 'L') {
      bind.insertElementAt(sub.L.s, 0);
      bind.insertElementAt(L.s, 0);
      return L.t.Alpha(sub.L.t, bind);
    }
    if (TermType() == 'A' && sub.TermType() == 'A') {
      return A.t1.Alpha(sub.A.t1, bind) && A.t2.Alpha(sub.A.t2, bind);
    }
    return false;
  }

  /*
   * FUNCTION occurs_free_in: Returns true if string s occurs free in the term
   */
  public boolean occurs_free_in(String s) {

    if (TermType() == 'V')
      return (V.s.equals(s));
    if (TermType() == 'L')
      return !(L.s.equals(s)) && L.t.occurs_free_in(s);
    if (TermType() == 'A')
      return (A.t1.occurs_free_in(s) || A.t2.occurs_free_in(s));
    return false;
  }

  /*
   * FUNCTION Subst: Substitutes string s in the Term class with Term x
   */
  public void Subst(String s, Term x) {

    if (TermType() == 'V') {
      if (V.s.equals(s)) {
        if (x.TermType() == 'V')
          V.s = x.V.s;
        if (x.TermType() == 'A') {
          SetApp();
          A.t1 = x.A.t1;
          A.t2 = x.A.t2;
        }
        if (x.TermType() == 'L') {
          SetAbst();
          L.s = x.L.s;
          L.t = x.L.t;
        }
      }
      return;
    }
    if (TermType() == 'A') {
      A.t1.Subst(s, x);
      A.t2.Subst(s, x);
    }
    if (TermType() == 'L') {
      if (L.s.equals(s))
        ;
      else {
        if (!(x.occurs_free_in(L.s)))
          L.t.Subst(s, x);
        else {
          String m = L.s + "'";
          Term temp1;
          temp1 = new Term();
          temp1.SetVar();
          temp1.V.s = m;
          L.t.Subst(L.s, temp1);
          L.s = m;
          L.t.Subst(s, x);
        }
      }
    }
  }

  /*
   * FUNCTION Member: Returns true if string s is present in the environment vector v
   */
  public boolean Member(String s, Vector v) {

    for (int i = 0; i < v.size(); i++) {
      if (s.equals(v.elementAt(i)))
        return true;
    }
    return false;
  }

  /*
   * FUNCTION Lookup: Returns a term that matches string s in the environment
   */
  public Term Lookup(String s, Vector v1, Vector v2) {

    Term temp;
    String sttemp;
    temp = new Term();
    temp.SetVar();
    temp.V.s = "empty";
    for (int i = 0; i < v1.size(); i++) {
      sttemp = (String) v1.elementAt(i);
      if (s.equals(sttemp)) {
        temp = (Term) v2.elementAt(i);
        return temp;
      }
    }
    return temp;
  }

  /*
   * FUNCTION CopyTerm: Copies the content of Term t to the current Term class
   */
  public void CopyTerm(Term t) {

    switch (t.TermType()) {
      case 'V':
        SetVar();
        V.s = t.V.s;
        break;
      case 'L':
        SetAbst();
        L.s = t.L.s;
        L.t.CopyTerm(t.L.t);
        break;
      case 'A':
        SetApp();
        A.t1.CopyTerm(t.A.t1);
        A.t2.CopyTerm(t.A.t2);
        break;
    }
  }

  /* FUNCTION Step: Takes a one step reduction of a term */
  public void Step(Vector env, Vector envident, Vector blist) {

    int look;
    Term tt1;
    tt1 = new Term();
    if (TermType() == 'V') {
      if (!(Member(V.s, blist)) && Member(V.s, envident)) {
        tt1 = Lookup(V.s, envident, env);
        CopyTerm(tt1);
      }
      return;
    }
    if (TermType() == 'L') {
      if (L.t.TermType() == 'A') {
        if (L.t.A.t2.TermType() == 'V') {
          if (L.s.equals(L.t.A.t2.V.s) && !(L.t.A.t1.occurs_free_in(L.s)))
            CopyTerm(L.t.A.t1);
          else {
            blist.insertElementAt(L.s, 0);
            L.t.Step(env, envident, blist);
          }
        }
        else
          L.t.Step(env, envident, blist);
      }
      else
        L.t.Step(env, envident, blist);
      return;
    }
    if (A.t1.TermType() == 'L') {
      tt1.CopyTerm(A.t1.L.t);
      tt1.Subst(A.t1.L.s, A.t2);
      CopyTerm(tt1);
      return;
    }
    tt1.CopyTerm(A.t1);
    tt1.Step(env, envident, blist);
    Vector b;
    b = new Vector();
    if (tt1.Alpha(A.t1, b))
      A.t2.Step(env, envident, blist);
    else
      A.t1.CopyTerm(tt1);
    return;
  }

  /*
   * FUNCTION Redexes: Returns true if there is a beta or eta reduction that can be performed on a
   * given term
   */
  public boolean Redexes(Vector env, Vector envident, Vector blist) {

    Term tt1;
    tt1 = new Term();
    if (TermType() == 'V') {
      if (!(Member(V.s, blist)) && Member(V.s, envident)) {
        tt1 = Lookup(V.s, envident, env);
        return tt1.Redexes(env, envident, blist);
      }
      else
        return false;
    }
    if (TermType() == 'L') {
      if (L.t.TermType() == 'A') {
        if (L.t.A.t2.TermType() == 'V') {
          if (L.s.equals(L.t.A.t2.V.s) && !(L.t.A.t1.occurs_free_in(L.s)))
            return true;
          else
            return L.t.A.t2.Redexes(env, envident, blist);
        }
        blist.insertElementAt(L.s, 0);
        return L.t.Redexes(env, envident, blist);
      }
      blist.insertElementAt(L.s, 0);
      return L.t.Redexes(env, envident, blist);
    }
    if (A.t1.TermType() == 'L')
      return true;
    if (A.t1.TermType() == 'V') {
      if (!(Member(A.t1.V.s, blist)) && Member(A.t1.V.s, envident)) {
        tt1 = Lookup(A.t1.V.s, envident, env);
        Term tt2;
        tt2 = new Term();
        tt2.SetApp();
        tt2.A.t1.CopyTerm(tt1);
        tt2.A.t2.CopyTerm(A.t2);
        return tt2.Redexes(env, envident, blist);
      }
      else
        return A.t2.Redexes(env, envident, blist);
    }
    return (A.t1.Redexes(env, envident, blist) || A.t2.Redexes(env, envident, blist));
  }

  /*
   * FUNCTION Term_Size: Returns the length of a term as it would appear as a string
   */
  public int Term_Size() {

    switch (TermType()) {
      case 'V':
        return V.s.length();
      case 'L':
        return (2 + L.s.length() + L.t.Term_Size());
      case 'A':
        return (A.t1.Term_Size() + A.t2.Term_Size() + 3);
    }
    return 0;
  }

  /*
   * FUNCTION Step_Size: Similar to Step but returns the SizeTerm class which is used for the DERIVE
   * operation
   */
  public SizeTerm Step_Size(Vector env, Vector envident, Vector blist, int sz) {

    int look;
    Term tt1;
    tt1 = new Term();
    SizeTerm vsz;
    vsz = new SizeTerm();
    if (TermType() == 'V') {
      if (!(Member(V.s, blist)) && Member(V.s, envident)) {
        tt1 = Lookup(V.s, envident, env);
        CopyTerm(tt1);
        vsz.a = 0;
        vsz.b = 0;
        vsz.n = true;
        return vsz;
      }
      vsz.a = sz + V.s.length();
      vsz.b = V.s.length();
      return vsz;
    }
    if (TermType() == 'L') {
      if (L.t.TermType() == 'A') {
        if (L.t.A.t2.TermType() == 'V') {
          if (L.s.equals(L.t.A.t2.V.s) && !(L.t.A.t1.occurs_free_in(L.s))) {
            vsz.a = sz;
            vsz.b = Term_Size();
            CopyTerm(L.t.A.t1);
            return vsz;
          }
          else {
            blist.insertElementAt(L.s, 0);
            vsz = L.t.Step_Size(env, envident, blist, sz + 2 + L.s.length());
          }
        }
        else
          vsz = L.t.Step_Size(env, envident, blist, sz + 2 + L.s.length());
      }
      else
        vsz = L.t.Step_Size(env, envident, blist, sz + 2 + L.s.length());
      return vsz;
    }
    if (A.t1.TermType() == 'L') {
      tt1.CopyTerm(A.t1.L.t);
      vsz.a = sz;
      vsz.b = Term_Size();
      tt1.Subst(A.t1.L.s, A.t2);
      CopyTerm(tt1);
      return vsz;
    }
    tt1.CopyTerm(A.t1);
    vsz = tt1.Step_Size(env, envident, blist, sz + 1);
    Vector b;
    b = new Vector();
    if (tt1.Alpha(A.t1, b)) {
      vsz = A.t2.Step_Size(env, envident, blist, sz + 2 + A.t1.Term_Size());
      return vsz;
    }
    else
      vsz = A.t1.Step_Size(env, envident, blist, sz + 1);
    return vsz;
  }

  /*
   * FUNCTION Underline: Returns a string of spaces and dashes to underline a beta or eta reduction
   * for the DERIVE operation
   */
  public String Underline(String s, int a, int b) {

    String r = s;
    for (int i = 0; i < a; i++)
      s = s + " ";
    for (int j = 0; j < b; j++)
      s = s + "-";
    return s;
  }

  public Var V;
  public Abst L;
  public App A;
}