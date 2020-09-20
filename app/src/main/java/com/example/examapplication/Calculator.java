package com.example.examapplication;

import android.app.AlertDialog;
import android.util.*;

import java.util.Stack;

public class Calculator {

    static int count=0;
    private double operator1, operator2, ans;
    private String expec="";
    public void setException(String excep)
    {
        expec=excep;
    }
    public String getException()
    {
        return expec;
    }
    public Double getoperator1() {
        return operator1;
    }

    public Double getoperator2() {
        return operator1;
    }

    public Double getans() {
        return ans;
    }

    enum Operation {
        add, subtract, multiply, divide, percent, sqrt, none;
    }

    ;

    enum SaveClearFunstions {
        MR, MC, mPlus, mMinus, Ac, empty;
    }

    Operation operation;

    SaveClearFunstions saveClear;

    double memoryVar=0;

    public Double memoryFunction(SaveClearFunstions sc) {
        saveClear = sc;
        try{
        switch (saveClear) {
            case MC: {
                memoryVar=0;
                ans=0;
                           }
            break;
            case MR: {
                if(operation==Operation.none)
                {
                    ans=memoryVar;
                    operator1=memoryVar;
                }
                else
                {

                    operator1=ans;
                    operator2=memoryVar;

                    performOperation();
                }
                return memoryVar;
            }

            case mPlus: {
                memoryVar += ans;
            }
            break;
            case mMinus: {
                memoryVar -=ans;
            }
            break;
            case Ac: {
               clear();
            }
            break;

        }
        }
        catch(Exception e)
        {
            operator1=0;operator2=0;operation=Operation.none;
            expec="Memory Error.Press AC to start again.";
        }
    return memoryVar;
    }

    Calculator() {
        clear();
    }

    void clear() {
        operator1 = 0;
        operator2 = 0;
        operation = Operation.none;
        ans = 0;
        expec="";
    }

    private void performOperation()    // Will be called by the setoperand and setoperator method for getting the answer of the operation we performed.
    {
        switch (operation) {
            case add:
                ans = operator1 + operator2;
                break;
            case subtract:
                ans = operator1 - operator2;
                break;
            case divide:
                if(operator2!=0){
                    ans = operator1 / operator2;
                }
                else
                {
                    ans=0;
                    expec="Divide by Zero is not possible.";
                }
                break;
            case multiply:
                ans = operator1 * operator2;
                break;
            case percent:
                ans = (operator1 / 100);
                operation = Operation.none;
                operator1 = ans;
                break; //As percent will be applied only on one operation, we make sure to initialize our ans variable of the calcular to the answer we get by performing the operation on the single operation.
            case sqrt:
                ans = Math.sqrt(operator1);
                operation = Operation.none;
                operator1 = ans;
                break;// same as % for the square root
        }

    }

    public void equalsOperation()  //for performing more operations on the already performed operations, we press = which will make the answer that we got after
    {                               // performing the operation on the two operands and then putting that ans in the operator1 and making operator2=0 and operation=none.
        performOperation();
        operation = Operation.none;

        operator1 = ans;
        operator2 = 0;
    }

    public boolean setOperation(Operation op) {
        count=0;
        Operation tempOperation = operation;      //making a temproroy operation variable which will keep the last operation that we would have performed in our app.
        operation = op;                           //Assigning the Operation variable that we pressed in our app recently to the operation variable of the class
        if (op == Operation.percent && operator2 != 0)   //this if statement is for the situation when we are doing something like this: (op1+(op2%)) eg:finding tax.
        {                                        //we will check and make sure that operator 2 is not null as the percent operation has to be performed on operator2 and we will verify if the operation we have selected lastly is %.
            operator2 = (operator2 *operator1)/ 100;            //Now we will perform % on the operator2 and save its output to operator2
            operation = tempOperation;            // we will assign the first operator that we had chose before the percent to the operator variable of the class.In the eg we have taken it is plus.
            performOperation();
            ans = operator1;//Now when we have assigned the value of operation to + we will perform the performOperation function on the new value of the operator2 and the old value of operator 1.
            return true;                        // we have taken return type boolean as if the answer value have changed, it should be directly visible in the texviews.
        } else {
            if (op == Operation.percent && operator2 == 0)// Now if we have performed the % operation on the first operator itself then we need to call the perform Operation again
            {                                       //... as we have assigned the first operator and then chosen a % operator on that, so befor we assign the sencond operator, we need to solve the problem on the first one.
                performOperation();
                return true;
            }
        }
        if (op == Operation.sqrt && operator2 != 0) {
            operator2 = Math.sqrt(operator2);
            operation = tempOperation;
            performOperation();
            ans = operator1;
            return true;
        } else {
            if (op == Operation.sqrt && operator2 == 0) {
                performOperation();
                return true;
            }
        }
        return false;  //return false if the we have not chosen % or Square root.
    }

    public void dotPerform()
    {
        count++;

    }
    public boolean setOperand(double num) {
        int flag = 0;

            if (operation == Operation.none) {
                operator1 = 10 * operator1 + num;

                if(count>0)
                {operator1=(operator1/(count*10));}
                ans = operator1;
                return false;
            } else {
                operator2 = 10 * operator2 + num;
                if(count>0)
                {operator2=(operator2/(count*10));}
            }

            if (operator1 != 0 && operator2 != 0 || operation == Operation.percent || operation == Operation.sqrt || operation==Operation.divide) {
                performOperation();
                flag = 1;
            }

            if (flag == 1) {
                return true;
            }



        return false;
    }
}