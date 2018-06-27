package com.example.ameya.hellodoc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Vector;

import static com.example.ameya.hellodoc.Doc_Details.priorityQueue;

public class ShowAppointmentList extends AppCompatActivity {

    private ListView mListView;
    public String value,p_name,p_physic,p_sugar,p_age;
    public Query mQuery;
    public DatabaseReference mAppointmentRef,mPatientInfoRef;
    public int i=0,count=0;
     ArrayAdapter<String> arrayAdapter;
  //  public PriorityQueue<PriorityDecide> priorityQueue;

  //  Iterator<PriorityDecide> iterator;
    //PriorityDecide[] priorityDecidearray=new PriorityDecide[11];
    public Vector<PriorityDecide> priorityDecidearray=new Vector<PriorityDecide>();

    private ArrayList<String> mPatientList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appointment_list);



        priorityQueue= new PriorityQueue<PriorityDecide>(10, new Comparator<PriorityDecide>() {
            @Override
            public int compare(PriorityDecide priorityDecide, PriorityDecide t1) {
                /*if (priorityDecide.getPhysically()>t1.getPhysically() && priorityDecide.getSugar()>t1.getSugar()&&(priorityDecide.getAge()>t1.getAge()))return 1;
                if (priorityDecide.getPhysically()>t1.getPhysically() && priorityDecide.getSugar()>t1.getSugar()&&(priorityDecide.getAge()<t1.getAge()))return 1;
                if (priorityDecide.getPhysically()>t1.getPhysically() && priorityDecide.getSugar()<t1.getSugar()&&(priorityDecide.getAge()>t1.getAge()))return 1;
                if (priorityDecide.getPhysically()>t1.getPhysically() && priorityDecide.getSugar()<t1.getSugar()&&(priorityDecide.getAge()<t1.getAge()))return -1;
                if (priorityDecide.getPhysically()<t1.getPhysically() && priorityDecide.getSugar()>t1.getSugar()&&(priorityDecide.getAge()>t1.getAge()))return 1;
                if (priorityDecide.getPhysically()<t1.getPhysically() && priorityDecide.getSugar()>t1.getSugar()&&(priorityDecide.getAge()<t1.getAge()))return -1;
                if (priorityDecide.getPhysically()<t1.getPhysically() && priorityDecide.getSugar()<t1.getSugar()&&(priorityDecide.getAge()>t1.getAge()))return -1;
                else
                    return  0;*/
                if(priorityDecide.prio<t1.prio)
                    return 1;
                else if(priorityDecide.prio>t1.prio)
                    return -1;
                else
                    return 0;
            }
        });

        mListView = (ListView) findViewById(R.id.appointment_lllist);
        mAppointmentRef= FirebaseDatabase.getInstance().getReference().child("Doctors").child("Appointments").child(Doc_Details.key);
        mPatientInfoRef=FirebaseDatabase.getInstance().getReference().child("Patients");


        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mPatientList);
        mListView.setAdapter(arrayAdapter);


        mAppointmentRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                value=dataSnapshot.getValue(String.class);
                Log.v("E_VALUE", "Patient list : " + value+dataSnapshot.getChildrenCount());

                mQuery=mPatientInfoRef.orderByChild("user_id").equalTo(value);
                mQuery.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                        Map<String,String> map=dataSnapshot.getValue(genericTypeIndicator);
                        p_name=map.get("name");
                        p_physic=map.get("physically");
                        p_sugar=map.get("sugar");
                        p_age=map.get("age");
                        int priority= getPriority(p_physic,p_sugar,p_age);
                         PriorityDecide priorityDecide=new PriorityDecide(p_name,priority);
                       add(priorityDecide);
                        count++;

                       // priorityQueue.add(new PriorityDecide(p_name,priority));
                      // arrayAdapter.add(p_name);
                      // arrayAdapter.notifyDataSetChanged();



                    }


                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {


                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                    //priorityqueue pq=new priorityqueue();
                    // pq.addNode(p_name,p_physic,p_sugar,p_age);



                });



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

        if(count==4)
        {
            display();
        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(ShowAppointmentList.this,Doc_take_appointment.class);
                intent.putExtra("Doc_Name",mListView.getItemIdAtPosition(i));
                startActivity(intent);
            }
        });



       /* while (!priorityQueue.isEmpty())
        {
            Log.v("E_VALUE", "head" +priorityQueue.peek().name );
            arrayAdapter.add(priorityQueue.poll().name);
            arrayAdapter.notifyDataSetChanged();
        }*/

    }

    private void display() {

        while (!priorityDecidearray.isEmpty())
        {
            Log.v("E_VALUE", "t" +"caed" );

            arrayAdapter.add(poll().name);
            arrayAdapter.notifyDataSetChanged();
        }
    }

    private void add(PriorityDecide pd) {

        priorityDecidearray.add(pd);
        int index=size()-1;

        heapify_up(index);
        Log.v("E_VALUE", "head" +priorityDecidearray.firstElement().name);
    }

    private PriorityDecide poll()
    {
        PriorityDecide root=priorityDecidearray.firstElement();

        priorityDecidearray.setElementAt(priorityDecidearray.lastElement(),0);
        priorityDecidearray.remove(size()-1);
        heapify_down(0);
        return root;
    }

    private void heapify_down(int i) {

        int left=LEFT(i);
        int right=RIGHT(i);

        int largest=i;

        if(left<size() && priorityDecidearray.get(left).prio<priorityDecidearray.get(i).prio)
            largest=left;

        if(right<size() && priorityDecidearray.get(right).prio < priorityDecidearray.get(i).prio)
            largest=right;

        if(largest!=i)
        {
            swap(i,largest);
            heapify_down(largest);
        }
    }

    private int RIGHT(int i) {
        return 2*i+2;
    }

    private int LEFT(int i) {
        return 2*i+1;
    }

    private void heapify_up(int index) {

        if(index>0 && priorityDecidearray.get(parent(index)).prio>priorityDecidearray.get(index).prio)
        {
            Log.v("E_VALUE", "head" +"heapify up" );
            swap(index,parent(index));

            heapify_up(parent(index));
        }
    }

    private void swap(int index, int parent) {

        PriorityDecide temp=priorityDecidearray.get(index);
        priorityDecidearray.setElementAt(priorityDecidearray.get(parent),index);
        priorityDecidearray.setElementAt(temp,parent);
    }

    private int parent(int index) {

        if(index==0)
            return  0;
        return (index-1)/2;
    }

    private int size() {

        return priorityDecidearray.size();
    }



    private int getPriority(String physic,String sugar,String a) {
        int priority;

        int age=Integer.parseInt(a);

        if(physic.equals("Yes") && sugar.equals("Yes") &&( age<15 || age>60))
            priority=7;
        else if(physic.equals("Yes") && sugar.equals("Yes") &&( age>15 && age<60))
            priority=6;
        else if(physic.equals("Yes") && sugar.equals("No") &&( age<15 || age>60))
            priority=5;
        else if(physic.equals("Yes") && sugar.equals("No") &&( age>15 && age<60))
            priority=4;
        else if(physic.equals("No") && sugar.equals("Yes") &&(age<15 || age>60))
            priority=3;
        else if(physic.equals("No") && sugar.equals("Yes") &&(age>15 && age<60))
            priority=2;
        else if(physic.equals("No") && sugar.equals("No") &&( age<15 || age>60))
            priority=1;
        else
            priority=0;

        return  priority;

    }

}

/*
mAppointmentRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value=dataSnapshot.child("Patients").getValue(String.class);
                Log.v("E_VALUE", "Patient list : " + value);

                mQuery=mPatientInfoRef.orderByChild("user_id").equalTo(value);
                mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                      //  arrayAdapter.add(p_name);
                      //  arrayAdapter.notifyDataSetChanged();
                        Log.v("E_VALUE", "head" +"hello");
                        GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {};
                        Map<String,String> map=dataSnapshot.getValue(genericTypeIndicator);
                        p_name=map.get("name");
                        p_physic=map.get("physically");
                        p_sugar=map.get("sugar");
                        p_age=map.get("age");

                         PriorityDecide priorityDecide=new PriorityDecide(p_name,p_physic,p_sugar,p_age);
                        priorityDecidearray[i]=priorityDecide;
                        i++;
                       // priorityQueue.add(new PriorityDecide(p_name,p_physic,p_sugar,p_age));
                       // arrayAdapter.add(p_name);
                      //  arrayAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
*/

/*class node extends ShowAppointmentList
{
String name,physic,sugar,age ;
    int priori;
    node l_link,r_link;
    node(String n,String p,String s,String a,int prio)
    {
        name=n;
        physic=p;
        sugar=s;
        age=a;
        l_link=null;
        r_link=null;
        priori=prio;
    }

}
class priorityqueue
{
node head;
    priorityqueue()
    {
        head=null;
    }
    public void addNode(String n,String p,String s,String a  )
    {
        node temp;

        temp=new node(n,p,s,a,0);

        int priority=getPriority(temp);
        temp.priori=priority;

        if(head==null)
        {

        }

    }
    public int getPriority(node temp)
    {
        int age=Integer.parseInt(temp.age);
        int priority=-1;
        if(temp.physic.equals("Yes") && temp.sugar.equals("Yes") &&( age<15 || age>60))
            priority=7;
        else if(temp.physic.equals("Yes") && temp.sugar.equals("Yes") &&( age>15 && age<60))
            priority=6;
        else if(temp.physic.equals("Yes") && temp.sugar.equals("No") &&( age<15 || age>60))
            priority=5;
        else if(temp.physic.equals("Yes") && temp.sugar.equals("No") &&( age>15 && age<60))
            priority=4;
        else if(temp.physic.equals("No") && temp.sugar.equals("Yes") &&(age<15 || age>60))
            priority=3;
        else if(temp.physic.equals("No") && temp.sugar.equals("Yes") &&(age>15 && age<60))
            priority=2;
        else if(temp.physic.equals("No") && temp.sugar.equals("No") &&( age<15 || age>60))
            priority=1;
        else
            priority=0;

      return  priority;


 mAppointmentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> appointments=dataSnapshot.getChildren().iterator();
                while (appointments.hasNext())
                {
                    DataSnapshot entry=appointments.next();
                    p_name=entry.child("name").getValue().toString();
                    p_age=entry.child("age").getValue().toString();
                    p_physic=entry.child("physically").getValue().toString();
                    p_sugar=entry.child("sugar").getValue().toString();
                    PriorityDecide priorityDecide=new PriorityDecide(p_name,p_physic,p_sugar,p_age);
                    priorityDecidearray[i]=priorityDecide;
                    i++;
                }
                //value=dataSnapshot.child("Patients").getValue(String.class);

                Log.v("E_VALUE", "Patient list : " + value);
                uid[count]=value;
                count++;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}*/

