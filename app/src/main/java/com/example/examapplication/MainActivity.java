package com.example.examapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView displayAns, displayProcess;
    int flag=0;
    Calculator calc=new Calculator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
        displayProcess=(TextView)findViewById(R.id.buttonHistory);
        displayAns=(TextView)findViewById(R.id.buttonAnswer);
    }

    public void dotPressed(View view)
    {
        if(flag==0)
        {
        calc.dotPerform();
        displayProcess.append(".");
        flag=1;
        }
    }

    public void operationPressed(View view) {

        if (calc.getoperator1()!=0) {
            Calculator.Operation op = Calculator.Operation.none;

            Boolean b = false;

            switch (view.getId()) {
                case R.id.buttonadd:
                    op = Calculator.Operation.add;
                    Log.d("add","add pressed");
                    b = calc.setOperation(op);

                    break;
                case R.id.buttonsub:
                    op = Calculator.Operation.subtract;
                    b = calc.setOperation(op);

                    Log.d("sub","sub pressed");

                    break;
                case R.id.buttondiv:
                    op = Calculator.Operation.divide;
                    b = calc.setOperation(op);

                    Log.d("div","div pressed");

                    break;
                case R.id.buttonmul:
                    op = Calculator.Operation.multiply;
                    b = calc.setOperation(op);

                    Log.d("mul","mul pressed");

                    break;
                case R.id.buttonper:
                    op = Calculator.Operation.percent;
                    b = calc.setOperation(op);

                    Log.d("per","per pressed");

                    break;
                case R.id.buttonsqrt:
                    op = Calculator.Operation.sqrt;
                    b = calc.setOperation(op);

                    Log.d("sqrt","sqrt pressed");

                    break;
            }
            Button btn=(Button)view;
            displayProcess.append(btn.getText().toString());
            if(!calc.getException().equals("")) {
                displayProcess.setText("");

                displayAns.setText(calc.getException());
                calc.setException("");
            }
            else
            {
                displayAns.setText(calc.getans()+"");

                flag=0;
            }
        }
    }

    public void digit_pressed(View view)
    {
        Boolean b=false;
        switch(view.getId()) {
            case R.id.button0:
                b= calc.setOperand(0);
                break;
            case R.id.button1:
                b= calc.setOperand(1);
                break;
            case R.id.button2:
                b= calc.setOperand(2);
                break;
            case R.id.button3:
                b= calc.setOperand(3);
                break;
            case R.id.button4:
                b= calc.setOperand(4);
                break;
            case R.id.button5:
                b= calc.setOperand(5);
                break;
            case R.id.button6:
                b= calc.setOperand(6);
                break;
            case R.id.button7:
                b= calc.setOperand(7);
                break;
            case R.id.button8:
                b= calc.setOperand(8);
                break;
            case R.id.button9:
                b= calc.setOperand(9);
                break;

        }

        calc.equalsOperation();
        Button btn=(Button)view;

        printanswer((btn.getText().toString()));

        if(calc.getException().equals("")) {
            if (b == true) {
                displayAns.setText(calc.getans() + "");
            }
        }
        else
        {
            displayProcess.setText("");

            displayAns.setText(calc.getException());
        }
    }

    public void equalsPressed(View view)
    {
        calc.equalsOperation();
        displayProcess.setText(calc.getoperator1()+"");
        displayAns.setText(calc.getoperator1()+"");
        flag=0;
    }

    public void memoryButtonClicked(View view)
    {

        Calculator.SaveClearFunstions sc=Calculator.SaveClearFunstions.empty;

        switch(view.getId())
        {
            case R.id.buttonMC:
                sc=Calculator.SaveClearFunstions.MC;
                calc.memoryFunction(sc);
                break;
            case R.id.buttonMR:
                sc= Calculator.SaveClearFunstions.MR;
                double varRe=calc.memoryFunction(sc);

                    displayProcess.append(calc.getoperator1()+"");
                    displayAns.setText(varRe+"");


                break;
            case R.id.buttonMplus:
                sc=Calculator.SaveClearFunstions.mPlus;
                calc.memoryFunction(sc);
                break;
            case R.id.buttonMminus:
                sc=Calculator.SaveClearFunstions.mMinus;
                calc.memoryFunction(sc);
                break;
            case R.id.buttonAC:
                sc=Calculator.SaveClearFunstions.Ac;
                calc.memoryFunction(sc);
                displayAns.setText("");
                displayProcess.setText("");
                break;

        }
        if(!calc.getException().equals("")) {
            displayProcess.setText("");

            displayAns.setText(calc.getException());
            calc.setException("");
        }

    }


    public void printanswer(String str)
    {
        displayProcess.append(str);
    }

}
