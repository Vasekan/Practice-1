package ru.mirea.elizavetov.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalculateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculateFragment newInstance(String param1, String param2) {
        CalculateFragment fragment = new CalculateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Button button;
    private String line="";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        TextView textView=  view.findViewById(R.id.answer);

        showLine(view,textView,R.id.zero);
        showLine(view,textView,R.id.one);
        showLine(view,textView,R.id.two);
        showLine(view,textView,R.id.three);
        showLine(view,textView,R.id.four);
        showLine(view,textView,R.id.five);
        showLine(view,textView,R.id.six);
        showLine(view,textView,R.id.seven);
        showLine(view,textView,R.id.eight);
        showLine(view,textView,R.id.nine);
        showLine(view,textView,R.id.point);
        showLine(view,textView,R.id.plus);
        showLine(view,textView,R.id.minus);
        showLine(view,textView,R.id.divided);
        showLine(view,textView,R.id.times);
        showLine(view,textView,R.id.delete);
        showLine(view,textView,R.id.equals);

        return view;
    }

    public void showLine(View view,TextView textView, int number){
        button =  view.findViewById(number);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                line+=((Button)v).getText().toString();
                if (((Button)v).getText().toString().equals("C"))
                    line="";
                if (((Button)v).getText().toString().equals("="))
                {
                    String cutline = (String) textView.getText();
                    int k=0,j=0,countA=0,countB=0;
                    String a = "",b= "",c= "";
                    float answer = 0, first=0,second=0;
                    for (int i = 0; i < cutline.length(); i++) {
                        if (cutline.charAt(i)=='-' && i==0)
                            countA++;
                        else
                        {
                            if ((cutline.charAt(i)=='+'||cutline.charAt(i)=='-'||cutline.charAt(i)=='*'||cutline.charAt(i)=='/') && k==0)
                            {
                                k++;
                                j=i;
                            }

                            if (k==0)
                                a+=(String.valueOf(cutline.charAt(i)));
                            else
                            {
                                c =(String.valueOf(cutline.charAt(j)));
                                if (i > j)
                                {
                                    if (cutline.charAt(i)=='-' && i==j+1)
                                        countB++;
                                    else
                                        b+=(String.valueOf(cutline.charAt(i)));
                                }
                            }
                        }
                    }
                    first=Float.parseFloat(a);
                    second=Float.parseFloat(b);
                    System.out.println(first+" "+c+" "+second);
                    if (countA==1)
                        first=-first;
                    if (countB==1)
                        second=-second;
                    if (c.equals("+"))
                        answer=first+second;
                    if (c.equals("-"))
                        answer=first-second;
                    if (c.equals("*"))
                        answer = first * second;
                    if (c.equals("/"))
                        answer=first/second;
                    textView.setText(Float.toString(answer));
                    line="";
                }
                else textView.setText(line);
            }
        });
    }
}