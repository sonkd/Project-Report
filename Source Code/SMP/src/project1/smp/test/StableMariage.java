//package project1.smp.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class StableMariage
{  
    public static void main (String [] args) throws FileNotFoundException
    {
        Scanner in = new Scanner (new File ("marriage_in.txt"));
        try (PrintWriter out = new PrintWriter (new File ("marriage_out.txt"))) {
            Person [] men;
            Person [] women;
            int problemSize;
            
            out.println("Stable Marriage\n");
            while (in.hasNext())
            {
                problemSize = in.nextInt();
                out.println(problemSize + " couples");
                men = readInfo(in, problemSize);
                women = readInfo(in, problemSize);
                
                for (int i = 0; i < problemSize; i++)
                {
                    placeMan(men, women, i);
                }
                printSolution(out, men);
            }
            out.println("End of Stable Marriage");
        }
    }
    
    private static void placeMan(Person[] men, Person[] women, int man)
    {
        int womanPreferred = men[man].getPreference(men[man].getNextChoice());//nguoi phu nu ua thich
        int spurnedMan;//tu choi
        
        if (women[womanPreferred].getCurrentMatch() == Person.UNMATCHED)
        {
            men[man].setCurrentMatch(womanPreferred);
            women[womanPreferred].setCurrentMatch(man);
        }
        else if (women[womanPreferred].wantsToChange(man))
        {
            spurnedMan = women[womanPreferred].getCurrentMatch();
            men[man].setCurrentMatch(womanPreferred);
            women[womanPreferred].setCurrentMatch(man);
            men[spurnedMan].incrementNextChoice();
            placeMan(men, women, spurnedMan);
        }
        else
        {
            men[man].incrementNextChoice();
            placeMan(men, women, man);
        }
    }
    
    private static void printSolution (PrintWriter out, Person [] people)
    {
        for (int i = 0; i < people.length; i++)
        {
            out.println("Pair man " + i + " with woman " + 
                people[i].getCurrentMatch());
        }
        out.println();
    }
    
    public static Person[] readInfo(Scanner in, int n)
    {
        Person[] people = new Person[n];
        for (int i = 0; i < n; i++)
        {
            people[i] = new Person(n);
            people[i].read(in);
        }
        return people;
    }
}

class Person
{
    private int [] preferences;
    private int currentMatch;
    private int nextChoice;
    public static final int UNMATCHED = -1;
    
    Person (int n)
    {
        preferences = new int[n];
    }
    
    public boolean wantsToChange (int potentialMate)
    {
        for (int i = 0; i < preferences.length; i++)
        {
            if (preferences[i] == currentMatch)
            {
                return false;
            }
            else if (preferences[i] == potentialMate)
            {
                return true;
            }
        }
        return true;    // never reached
    }
    
    public void read (Scanner in)
    {
        currentMatch = UNMATCHED;
        nextChoice = 0;
        for (int i = 0; i < preferences.length; i++)
        {
            preferences[i] = in.nextInt();
        }
    }
    
    public int getNextChoice()
    {
        return nextChoice;
    }
    
    public void incrementNextChoice()
    {
        nextChoice++;
    }
    
    public int getCurrentMatch ()
    {
        return currentMatch;
    }
    
    public void setCurrentMatch (int who)
    {
        currentMatch = who;
    }
    
    public int getPreference(int i)
    {
        return preferences[i];
    }
}
