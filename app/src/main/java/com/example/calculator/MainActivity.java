package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calculator.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //创建Button对象   也就是activity_main.xml里所设置的ID
    Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_pt;
    Button btn_mul,btn_div,btn_add,btn_sub;
    Button btn_clr,btn_del,btn_eq,btn_swi,btn_sqrt;
    EditText et_input;
    boolean secondClick = false;    //判断是否是按下=并计算成功后的输入

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_0= (Button) findViewById(R.id.btn_0);
        btn_1= (Button) findViewById(R.id.btn_1);
        btn_2= (Button) findViewById(R.id.btn_2);
        btn_3= (Button) findViewById(R.id.btn_3);
        btn_4= (Button) findViewById(R.id.btn_4);
        btn_5= (Button) findViewById(R.id.btn_5);
        btn_6= (Button) findViewById(R.id.btn_6);
        btn_7= (Button) findViewById(R.id.btn_7);
        btn_8= (Button) findViewById(R.id.btn_8);
        btn_9= (Button) findViewById(R.id.btn_9);
        btn_pt= (Button) findViewById(R.id.btn_pt);
        btn_add= (Button) findViewById(R.id.btn_add);
        btn_sub= (Button) findViewById(R.id.btn_sub);
        btn_mul= (Button) findViewById(R.id.btn_mul);
        btn_div= (Button) findViewById(R.id.btn_div);
        btn_clr= (Button) findViewById(R.id.btn_clr);
        btn_del= (Button) findViewById(R.id.btn_del);
        btn_eq= (Button) findViewById(R.id.btn_eq);
        btn_swi= (Button) findViewById(R.id.btn_swi);
        btn_sqrt= (Button) findViewById(R.id.btn_sqrt);
        et_input= (EditText) findViewById(R.id.et_input);

        //给按钮设置的点击事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_pt.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_clr.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_eq.setOnClickListener(this);
        btn_swi.setOnClickListener(this);
        btn_sqrt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str=et_input.getText().toString();
        int id = v.getId();
        if (id==R.id.btn_0||id==R.id.btn_1||id==R.id.btn_2||id==R.id.btn_3||id==R.id.btn_4||id==R.id.btn_5||id==R.id.btn_6||id==R.id.btn_7||id==R.id.btn_8||id==R.id.btn_9) {
            if(secondClick){                    // 第二次计算清空第一次结果
                str="";
                secondClick = false;
            }
            if(id==R.id.btn_0&&str.length()>=3){
                if(str.charAt(str.length()-2)=='÷')
                    Toast.makeText(MainActivity.this,"÷后面不能是0",Toast.LENGTH_SHORT).show();
                else
                    et_input.setText(str + ((Button) v).getText());
            }
            else
                et_input.setText(str + ((Button) v).getText());
        } else if(id==R.id.btn_sqrt){
            if(secondClick){                    // 第二次计算清空第一次结果
                secondClick = false;
            }
            if(!str.equals("")){
                if(str.charAt(str.length()-1)=='.')
                    Toast.makeText(MainActivity.this,"√前面不能是.",Toast.LENGTH_SHORT).show();
                else
                    et_input.setText(str + ((Button) v).getText());
            }
            else
                et_input.setText(str + ((Button) v).getText());
        }else if(id==R.id.btn_pt){
            if(!str.contains(" ")){
                if(str.equals("")||str.charAt(str.length()-1)=='√'){
                    Toast.makeText(MainActivity.this,".前面必须是数字",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!str.contains("."))
                        et_input.setText(str + ((Button) v).getText());
                }
            }
            else{
                if(str.charAt(str.length()-1)==' '||str.charAt(str.length()-1)=='√'){
                    Toast.makeText(MainActivity.this,".前面必须是数字",Toast.LENGTH_SHORT).show();
                }
                else{
                    int begin = str.length()-1;
                    for(;begin>=0;begin--){
                        if(str.charAt(begin)==' ')
                            break;
                        else
                            continue;
                    }
                    if(!str.substring(begin+1,str.length()).contains("."))
                        et_input.setText(str + ((Button) v).getText());
                }
            }
        }else if (id == R.id.btn_add || id == R.id.btn_sub || id == R.id.btn_mul || id == R.id.btn_div) {
            if(secondClick){                    // 第二次计算清空第一次结果
                secondClick = false;
            }
           if(str.equals("")){
               if(id == R.id.btn_mul || id == R.id.btn_div)
                   Toast.makeText(MainActivity.this,"×或÷不能作为单运算符",Toast.LENGTH_SHORT).show();
               else if(id == R.id.btn_sub)
                   et_input.setText("-");
           }
           else if(str.charAt(str.length()-1)=='.'){
               Toast.makeText(MainActivity.this,".后面必须是数字",Toast.LENGTH_SHORT).show();
           }
           else if(str.charAt(str.length()-1)==' '){
               et_input.setText(str.substring(0,str.length()-2)+((Button) v).getText() + " ");
           }
           else if(str.charAt(str.length()-1)=='√'){
               Toast.makeText(MainActivity.this,"根号后面不能是算符",Toast.LENGTH_SHORT).show();
           }
           else if(str.charAt(str.length()-1)=='-'){
               Toast.makeText(MainActivity.this,"-后面是数字",Toast.LENGTH_SHORT).show();
           }
           else{
               et_input.setText(str + " " + ((Button) v).getText() + " ");
           }
        } else if (id == R.id.btn_clr) {
            et_input.setText("");
        } else if (id == R.id.btn_del) { //判断是否为空，然后在进行删除
            if (str != null && !str.equals("")) {
                if(str.charAt(str.length()-1)==' ')
                    et_input.setText(str.substring(0, str.length() - 3));
                else
                    et_input.setText(str.substring(0, str.length() - 1));
            }
        } else if (id == R.id.btn_swi){
            if(!str.contains(" ")&&!str.equals("")){
                if(str.charAt(str.length()-1)=='.'||str.charAt(str.length()-1)=='√'){
                    Toast.makeText(MainActivity.this,"表达式不正确",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(str.charAt(0)=='-')
                        et_input.setText(str.substring(1,str.length()));
                    else
                        et_input.setText("-"+str);
                }
            }
        }else if (id == R.id.btn_eq) { //单独运算最后结果
            getResult();
        }
    }

    private void getResult() {
        String str=et_input.getText().toString();
        if(str==null||str.equals(""))
            return ;
        if(str.length()>=3&&str.charAt(str.length()-1)==' '){
            Toast.makeText(MainActivity.this,"算式最后一位不能是算符",Toast.LENGTH_SHORT).show();
            return;
        }
        if(str.contains("√")&&!str.contains(" ")){          //不带算符的当个含根号算式运算
            if(sqrtCount(str)!=null){
                et_input.setText(sqrtCount(str));
                secondClick = true;
                return;
            }
        }
        if(!str.contains(" ")){
            return ;
        }
        String[] list = str.split(" ");
        ArrayList<Double> numList = new ArrayList<>();
        for(int i=0;i<list.length;i+=2){
            if(list[i].charAt(list[i].length()-1)=='.'||list[i].charAt(list[i].length()-1)=='√'){
                Toast.makeText(MainActivity.this,"算式中有错误或不完全",Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                if(list[i].contains("√")){
                    if(sqrtCount(list[i])!=null)
                        numList.add(Double.parseDouble(sqrtCount(list[i])));
                }
                else{
                    numList.add(Double.parseDouble(list[i]));
                }
            }
        }

        ArrayList<Integer> waitDelList = new ArrayList<>();     // 记录处理乘法和除法后要消除的数字的位置
        for(int i=1;i<list.length;i+=2){
            if(list[i].equals("×")){
                double temp = numList.get((i+1)/2)*numList.get((i+1)/2-1);
                waitDelList.add((i+1)/2-1);
                numList.set((i+1)/2,temp);
            }
            if(list[i].equals("÷")){
                double temp = numList.get((i+1)/2-1)/numList.get((i+1)/2);
                waitDelList.add((i+1)/2-1);
                numList.set((i+1)/2,temp);
            }
        }
        int j = 0;
        for(int i : waitDelList){                               // 删除要删除位置的数字
            numList.remove(i-j++);
        }
        for(int i=1;i<list.length;i+=2){
            if(list[i].equals("+")){
                double temp = numList.get(0)+numList.get(1);
                numList.remove(1);
                numList.set(0,temp);
            }
            if(list[i].equals("-")){
                double temp = numList.get(1);
                numList.remove(1);
                numList.set(0,numList.get(0)-temp);
            }
        }
        secondClick = true;
        et_input.setText(numList.get(0).toString());
    }

    private String sqrtCount(String str){               //根式运算
        boolean isMinus = false;        // 判断是否为负值
        double tempNum = 1;
        if(str.charAt(str.length()-1)=='√'||str.charAt(str.length()-1)=='.'){
            Toast.makeText(MainActivity.this,"错误算式，无法计算",Toast.LENGTH_SHORT).show();
            return null;
        }
        if(str.charAt(0)=='-'){         // 处理负值
            if(str.length()==2){        // 判别 -√ 错误
                Toast.makeText(MainActivity.this,"错误算式，无法计算",Toast.LENGTH_SHORT).show();
                return null;
            }
            isMinus = true;
            str = str.substring(1,str.length());
        }
        int end = str.length();
        int begin = str.length()-1;
        ArrayList<Double> splitList = new ArrayList<>();
        for(;begin>=0;begin--){
            if(str.charAt(begin)=='√'){
                if(end-begin==1){
                    splitList.set(splitList.size()-1,Math.sqrt(splitList.get(splitList.size()-1)));
                    end = begin;
                }
                else{
                    splitList.add(Math.sqrt(Double.parseDouble(str.substring(begin+1,end))));
                    end = begin;
                }
            }
        }
        if(str.charAt(0)!='√')
            splitList.set(splitList.size()-1,Double.parseDouble(str.substring(0,end))*splitList.get(splitList.size()-1));
        for(Double d : splitList)
            tempNum = tempNum*d;
        if(isMinus)
            tempNum = -tempNum;
        return tempNum+"";
    }
}

