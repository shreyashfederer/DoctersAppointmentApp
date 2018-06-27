package com.example.ameya.hellodoc;

import android.nfc.Tag;
import android.util.Log;

/**
 * Created by ameya on 10/9/17.
 */







public class PriorityDecide {
    public PriorityDecide() {
    }

    public String name;
    int prio;
    // public String physically;
    //  public String sugar;
    //  public String age;


    public PriorityDecide(String n, int p) {
        this.name = n;
        this.prio = p;
        // physically=p;
        // sugar=s;
        // age=a;

    }

  /*  public int getPhysically() {
        if(physically.equals("Yes")) {
            Log.v("E_VALUE","getphyic value: "+physically);
            return 1;

        }
        else
            return 0;

    }

    public int getSugar() {
        if(sugar.equals("Yes"))
            return 1;
        else
            return 0;

    }

    public int getAge() {
        if(Integer.parseInt(age)<15 ||Integer.parseInt(age)>60)
        {

            return 1;

        }
        else return 0;
    }

  /*  public int compareTo(PriorityDecide priorityDecide)
    {
        int priority;
       /* if(getPhysically()>priorityDecide.getPhysically() && getSugar()>priorityDecide.getSugar())
        {
            if(getAge()>priorityDecide.getAge())
                priority= 1;
        }
        else if(getPhysically()>priorityDecide.getPhysically() && getSugar()>priorityDecide.getSugar())
        {
            if (getAge()<priorityDecide.getAge())
            {
                priority= 1;
            }
        }
        else if (getPhysically()>priorityDecide.getPhysically() && getSugar()<priorityDecide.getSugar())
        {
            if (getAge()>priorityDecide.getAge())
            {
                priority=   0;
            }
        }
        else  if (getPhysically()>priorityDecide.getPhysically() && getSugar()<priorityDecide.getSugar()&&(getAge()<priorityDecide.getAge()))
        {

                priority= -1;
        }

        else  if (getPhysically()<priorityDecide.getPhysically() && getSugar()>priorityDecide.getSugar())
        {
            if(getAge()>priorityDecide.getAge())
                priority= 0;
        }
        else  if (getPhysically()<priorityDecide.getPhysically() && getSugar()>priorityDecide.getSugar())
        {
            if (getAge()<priorityDecide.getAge())
            {
                priority= 0;
            }
        }
        else  if (getPhysically()<priorityDecide.getPhysically() && getSugar()<priorityDecide.getSugar())
        {
            if(getAge()>priorityDecide.getAge())
                priority=  0;
        }
        else{
            priority=  -1;}*/

           /*if(getAge()==priorityDecide.getAge())
           {
               Log.v("E_VALUE","getage value: "+getAge());
               return 0;
           }else if (getAge()>priorityDecide.getAge())
           {
               Log.v("E_VALUE","getage value: "+getAge());
               return 1;
           }
           else
               return -1;*/

          /*  if(getPhysically()==priorityDecide.getPhysically())
            {
                return 0;
            }
            else if(getPhysically()>priorityDecide.getPhysically())
                return 1;
        else
            return -1;
    }
    */

}

