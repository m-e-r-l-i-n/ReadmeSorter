import java.io.*;
import java.util.*;

public class ReadmeSort
{
    public static void main(String args[])throws Exception
    {
        BufferedReader bu=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter input file:");
        String s=bu.readLine();
        File in=new File(s);
        System.out.print("Enter output file:");
        s=bu.readLine();
        File op=new File(s);
        StringBuilder sb=new StringBuilder();

        bu=new BufferedReader(new FileReader(in));
        s=bu.readLine();
        ArrayList<Lines> al=new ArrayList<>();
        while(s.length()>0)
        {
            s=s.trim();
            String st[]=s.split(" ");

            if(st[0].charAt(0)=='#')    //heading
            {
                Lines l=new Lines();
                l.text.add(s.trim());
                al.add(l);
            }
            else
            {
                if(al.size()>0) al.get(al.size()-1).text.add(s);    //adding texts to last heading
                else sb.append(s+"\n");
            }
            s=bu.readLine();
        }

        sort(al);
        for(Lines line:al)
        for(String txt:line.text)
            add(sb,txt);

        PrintStream p=new PrintStream(op);
        System.setOut(p);
        System.out.print(sb);
    }

    static void add(StringBuilder sb, String s)
    {
        if(s.charAt(0)=='#') sb.append(s+"\n");     //the \n are not so important, but lets keep it that way just for safety
        else
        {
            if(!s.endsWith("<br>")) s+="<br>";
            sb.append(s+"\n");
        }
    }

    static void sort(ArrayList<Lines> al)
    {
        Collections.sort(al, new Comparator<Lines>() {
            @Override
            public int compare(Lines o1, Lines o2) {
                return o1.text.get(0).compareTo(o2.text.get(0));
            }
        });
    }
}
