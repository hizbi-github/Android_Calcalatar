package com.example.calcalatar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import org.mariuszgromada.math.mxparser.*;

public class Calculator_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

    }


    Button numBut;
    Button opBut;
    Button unaryOpBut;

    TextView textResult;
    TextView textCurrentCalc;
    String stringOperand_1 = "";
    String stringOperand_2 = "";
    String stringOperator = "";
    String stringUnaryOperator = "";
    String stringPercentage = "";

    boolean isFullOperand_1 = false;
    boolean isFullOperand_2 = false;
    boolean isFullOperator = false;
    boolean isFullUnaryOperator = false;
    boolean percentageCalculated = false;
    boolean isNegate = false;



    public void onNumButClick (View view1)
    {

        numBut = (Button) view1;

        textResult = findViewById(R.id.textResult);

        String stringOperand = "";


        if (textResult.getText().toString().equals("0"))
        {
            textResult.setText(numBut.getText().toString());
        }
        else
        {
            stringOperand = textResult.getText().toString();
            textResult.setText(stringOperand + numBut.getText().toString());
        }

        isFullOperand_1 = true;

    }


    public void onOpButClick (View view2)
    {
        opBut = (Button) view2;     //Converting the View generated by the Button(View) into the Real Button

        textResult = findViewById(R.id.textResult);     //Assigning ID from layout TextView to our Class TextView
        stringOperand_1 = textResult.getText().toString();   //Creating a String and putting the text from TextView in it

        textResult.setText("");     //Modifying the TextView itself

        textCurrentCalc = findViewById(R.id.textCurrentCalc);       //Assigning ID from layout TextView to our Class TextView
        textCurrentCalc.setText(stringOperand_1 + " " + opBut.getText().toString() + " ");  //Modifying the TextView itself

        stringOperator = opBut.getText().toString();     //Creating a String and putting the text from Button in it

        isFullOperator = true;
    }


    public void onUnaryOpButClick (View view3)
    {
        unaryOpBut = (Button) view3;

        textResult = findViewById(R.id.textResult);
        String stringOperand = textResult.getText().toString();
        stringUnaryOperator = unaryOpBut.getText().toString();
        isFullUnaryOperator = true;

        String resultString = "";

        Double operand = Double.parseDouble(stringOperand);
        Double result = 0.0;

        textCurrentCalc = findViewById(R.id.textCurrentCalc);

        switch (stringUnaryOperator)
        {
            case "%":
            {
                if (isFullOperator == false)
                {
                    textCurrentCalc.setText(stringOperand + "% " );
                    textResult.setText("");

                    result = operand / 100;
                    resultString = Double.toString(result);

                    if (resultString.substring(resultString.length() - 2, resultString.length()).equals(".0"))
                    {
                        resultString = resultString.substring(0, resultString.length() - 2);
                    }

                    textResult.setText(resultString);



                    isFullOperand_1 = false;
                }
                else if ((isFullOperand_1 == true) && (stringOperand.equals("") == false) && (isFullOperator == true))
                {
                    Double operand_1 = Double.parseDouble(stringOperand_1);
                    stringOperand_2 = textResult.getText().toString();
                    Double operand_2 = Double.parseDouble(stringOperand_2);


                    if (stringOperator.equals("+") || stringOperator.equals("-"))
                    {
                        operand_2 = (operand_2 / 100) * operand_1;

                        stringPercentage = Double.toString(operand_2);
                        percentageCalculated = true;

                        textCurrentCalc.setText(stringOperand_1 + " " + stringOperator + " " +stringOperand_2 + "%" + " = " + stringOperand_1 + " " + stringOperator + " " + stringPercentage + " ");
                    }
                    else if (stringOperator.equals("x") || stringOperator.equals("÷"))
                    {
                        operand_2 = (operand_2 / 100);

                        stringPercentage = Double.toString(operand_2);
                        percentageCalculated = true;

                        textCurrentCalc.setText("(" + stringOperand_1 + " " + stringOperator + " " + stringOperand_2 + ")" + "%" + " = " + stringOperand_1 + " " + stringOperator + " " + stringPercentage + " ");
                    }

                    textResult.setText("");

                    stringOperand_2 = Double.toString(operand_2);

                }

                break;
            }

            case  "1/x":
            {
                textCurrentCalc.setText("1/(" + stringOperand + ") " );
                textResult.setText("");

                result = 1 / operand;
                resultString = Double.toString(result);

                if (resultString.substring(resultString.length() - 2, resultString.length()).equals(".0"))
                {
                    resultString = resultString.substring(0, resultString.length() - 2);
                }

                textResult.setText(resultString);

                break;
            }

            case "x\u00b2":
            {
                textCurrentCalc.setText(stringOperand + "\u00b2 " );
                textResult.setText("");

                result = operand * operand;
                resultString = Double.toString(result);

                if (resultString.substring(resultString.length() - 2, resultString.length()).equals(".0"))
                {
                    resultString = resultString.substring(0, resultString.length() - 2);
                }

                textResult.setText(resultString);

                break;
            }

            case "\u00b2\u221a":
            {
                textCurrentCalc.setText("\u00b2\u221a" + stringOperand + " " );
                textResult.setText("");

                result = Math.sqrt(operand);
                resultString = Double.toString(result);

                if (resultString.substring(resultString.length() - 2, resultString.length()).equals(".0"))
                {
                    resultString = resultString.substring(0, resultString.length() - 2);
                }

                textResult.setText(resultString);

                break;
            }
        }

    }


    public void onClearButClick (View view4)
    {
        textCurrentCalc = findViewById(R.id.textCurrentCalc);
        textResult = findViewById(R.id.textResult);

        textCurrentCalc.setText("");
        textResult.setText("0");

        stringOperand_1 = "";
        stringOperand_2 = "";
        stringOperator = "";

        isFullOperand_1 = false;
        isFullOperand_2 = false;
        isFullOperator = false;
        isFullUnaryOperator = false;
        percentageCalculated = false;
        isNegate = false;
    }


    public void onClearEntryButClick (View view5)
    {
        textCurrentCalc = findViewById(R.id.textCurrentCalc);
        textResult = findViewById(R.id.textResult);

        textCurrentCalc.setText("");
        textResult.setText("0");

        stringOperand_1 = "";
        stringOperand_2 = "";
        stringOperator = "";

        isFullOperand_1 = false;
        isFullOperand_2 = false;
        isFullOperator = false;
        isFullUnaryOperator = false;
        percentageCalculated = false;
        isNegate = false;
    }


    public void onResultButClick (View view6)
    {

        textResult = findViewById(R.id.textResult);

        if (percentageCalculated == false)
        {
            stringOperand_2 = textResult.getText().toString();
        }


        if (stringOperator.equals("") || stringOperand_2.equals(""))
        {
            textResult.setText("Incomplete!");
        }
        else
        {
            double operand1 = 0.0;
            double operand2 = 0.0;
            double result = 0.0;

            String resultString;


            operand1 = Double.parseDouble(stringOperand_1);
            operand2 = Double.parseDouble(stringOperand_2);


            textCurrentCalc = findViewById(R.id.textCurrentCalc);
            textCurrentCalc.setText(stringOperand_1 + " " + stringOperator + " " + stringOperand_2 + " " + "=" + " ");


            switch (stringOperator)
            {

                case "+":
                {
                    result = operand1 + operand2;
                    resultString = Double.toString(result);

                    if (resultString.substring(resultString.length() - 2, resultString.length()).equals(".0"))
                    {
                        resultString = resultString.substring(0, resultString.length() - 2);
                    }

                    textResult.setText(resultString);

                    break;
                }

                case "-":
                {
                    result = operand1 - operand2;
                    resultString = Double.toString(result);

                    if (resultString.substring(resultString.length() - 2, resultString.length()).equals(".0"))
                    {
                        resultString = resultString.substring(0, resultString.length() - 2);
                    }

                    textResult.setText(resultString);

                    break;
                }

                case "x":
                {
                    result = operand1 * operand2;
                    resultString = Double.toString(result);

                    if (resultString.substring(resultString.length() - 2, resultString.length()).equals(".0"))
                    {
                        resultString = resultString.substring(0, resultString.length() - 2);
                    }

                    textResult.setText(resultString);

                    break;
                }

                case "÷":
                {
                    if ((operand2 == 0) || (operand2 == 0.0))
                    {
                        Toast.makeText(getApplicationContext(), "Why are you stupid?", Toast.LENGTH_SHORT).show();  // Very cool way!
                    }
                    else
                    {
                        result = operand1 / operand2;
                        resultString = Double.toString(result);

                        if (resultString.substring(resultString.length() - 2, resultString.length()).equals(".0"))
                        {
                            resultString = resultString.substring(0, resultString.length() - 2);
                        }

                        textResult.setText(resultString);
                    }

                    break;
                }

            }

        }

    }


    public void onDPButClick (View view7)
    {
        textResult = findViewById(R.id.textResult);

        String stringOperand = "";


        if (textResult.getText().toString().equals("0"))
        {
            textResult.setText("0.");
        }
        else
        {
            stringOperand = textResult.getText().toString();
            textResult.setText(stringOperand + ".");
        }
    }


    public void onBackSpaceButClick (View view8)
    {
        textResult = findViewById(R.id.textResult);
        String stringResult = textResult.getText().toString();

        textCurrentCalc = findViewById(R.id.textCurrentCalc);
        String stringTextCurrentCalc = textCurrentCalc.getText().toString();

        String stringOperand = "";

        if ((isFullOperator == true || isFullUnaryOperator == true) && (stringResult.equals("0") || stringResult.equals("")) && stringTextCurrentCalc.equals("") == false)
        {
            stringOperator = "";
            isFullOperator = false;

            stringResult = stringTextCurrentCalc;
            stringResult = stringResult.replaceAll(" ", "");
            stringResult = stringResult.substring(0, stringResult.length() - 1);
            textResult.setText(stringResult);

            stringTextCurrentCalc = "";
            textCurrentCalc.setText(stringTextCurrentCalc);

        }
        else if ((stringResult.length() == 1 || stringResult.equals("") == true) && (isFullOperator == false || isFullUnaryOperator == true))
        {
            stringResult = "0";
            textResult.setText(stringResult);
        }
        else if ((stringResult.length() == 1 || stringResult.equals("") == true) && (isFullOperator == true || isFullUnaryOperator == true))
        {
            stringResult = "0";
            textResult.setText(stringResult);
        }
        else if ((isFullOperator == false) && (stringResult.equals("0") == false))
        {
            stringResult = stringResult.substring(0, stringResult.length() - 1);
            textResult.setText(stringResult);
        }
        else if ((isFullOperator == true) && (stringResult.equals("0") == false))
        {
            stringResult = stringResult.substring(0, stringResult.length() - 1);
            textResult.setText(stringResult);
        }

    }


    public void onNegateButClick (View view9)
    {
        textResult = findViewById(R.id.textResult);
        String stringResult = textResult.getText().toString();

        if (stringResult.equals("0") == false)
        {
            if (isNegate == false)
            {
                stringResult = "-" + stringResult;
                textResult.setText(stringResult);

                isNegate = true;
            }
            else if (isNegate == true)
            {
                stringResult = stringResult.substring(1, stringResult.length());
                textResult.setText(stringResult);

                isNegate = false;
            }

        }
    }


}