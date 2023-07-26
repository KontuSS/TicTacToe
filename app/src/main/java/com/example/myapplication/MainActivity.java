package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    int tour = 0;

    ArrayList<Character> fieldO = new ArrayList<>();
    ArrayList<Character> fieldX = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View A = findViewById(R.id.a);
        A.setOnClickListener(this);
        View B = findViewById(R.id.b);
        B.setOnClickListener(this);
        View C = findViewById(R.id.c);
        C.setOnClickListener(this);
        View D = findViewById(R.id.d);
        D.setOnClickListener(this);
        View E = findViewById(R.id.e);
        E.setOnClickListener(this);
        View F = findViewById(R.id.f);
        F.setOnClickListener(this);
        View G = findViewById(R.id.g);
        G.setOnClickListener(this);
        View H = findViewById(R.id.h);
        H.setOnClickListener(this);
        View I = findViewById(R.id.i);
        I.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if (tour==1) //Tura O
        {
            renderSign(v, R.drawable.o);
            tour=0;
            String id = getResources().getResourceEntryName(v.getId());
            fieldO.add(id.charAt(0));
            checkWin();
        }
        else if (tour==0) //Tura X
        {
            renderSign(v, R.drawable.x);
            tour=1;
            String id = getResources().getResourceEntryName(v.getId());
            fieldX.add(id.charAt(0));
            checkWin();
        }
    }

    private void afterWin()
    {
        findViewById(R.id.a).setClickable(false);
        findViewById(R.id.b).setClickable(false);
        findViewById(R.id.c).setClickable(false);
        findViewById(R.id.d).setClickable(false);
        findViewById(R.id.e).setClickable(false);
        findViewById(R.id.f).setClickable(false);
        findViewById(R.id.g).setClickable(false);
        findViewById(R.id.h).setClickable(false);
        findViewById(R.id.i).setClickable(false);
    }

    private void restartApp() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void onRestartButtonClick(View view) {
        restartApp();
    }
    public void checkWin ()
    {
        if (tour==0)
        {
            if (
                    (fieldO.contains('a') && fieldO.contains('b') && fieldO.contains('c')) ||
                            (fieldO.contains('d') && fieldO.contains('e') && fieldO.contains('f')) ||
                            (fieldO.contains('g') && fieldO.contains('h') && fieldO.contains('i')) ||
                            (fieldO.contains('a') && fieldO.contains('d') && fieldO.contains('g')) ||
                            (fieldO.contains('b') && fieldO.contains('e') && fieldO.contains('h')) ||
                            (fieldO.contains('e') && fieldO.contains('f') && fieldO.contains('i')) ||
                            (fieldO.contains('a') && fieldO.contains('e') && fieldO.contains('i')) ||
                            (fieldO.contains('c') && fieldO.contains('e') && fieldO.contains('g')))
            {
                String text = "O wins";
                ((TextView)findViewById(R.id.textView)).setText(text);
                ((Button)findViewById(R.id.button)).setVisibility(View.VISIBLE);
                afterWin();
            }
        }
        else if (tour==1)
        {
            if (
                    (fieldX.contains('a') && fieldX.contains('b') && fieldX.contains('c')) ||
                            (fieldX.contains('d') && fieldX.contains('e') && fieldX.contains('f')) ||
                            (fieldX.contains('g') && fieldX.contains('h') && fieldX.contains('i')) ||
                            (fieldX.contains('a') && fieldX.contains('d') && fieldX.contains('g')) ||
                            (fieldX.contains('b') && fieldX.contains('e') && fieldX.contains('h')) ||
                            (fieldX.contains('e') && fieldX.contains('f') && fieldX.contains('i')) ||
                            (fieldX.contains('a') && fieldX.contains('e') && fieldX.contains('i')) ||
                            (fieldX.contains('c') && fieldX.contains('e') && fieldX.contains('g')))
            {
                String text = "X wins";
                ((TextView)findViewById(R.id.textView)).setText(text);
                ((Button)findViewById(R.id.button)).setVisibility(View.VISIBLE);
                afterWin();
            }
        }
        //Tie check
        if (fieldO.size()==4 && fieldX.size()==5)
        {
            String text = "Tie";
            ((TextView)findViewById(R.id.textView)).setText(text);
            ((Button)findViewById(R.id.button)).setVisibility(View.VISIBLE);
            afterWin();
        }
    }

    private void renderSign(View v, int picRes)
    {
        ViewGroup parentLayout = (ViewGroup)v.getParent();

        ImageView newImageView = new ImageView(this);

        newImageView.setLayoutParams(v.getLayoutParams());
        newImageView.setId(v.getId());
        newImageView.setImageResource(picRes);

        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) newImageView.getLayoutParams();
        ConstraintLayout.LayoutParams existingLayoutParams = (ConstraintLayout.LayoutParams) v.getLayoutParams();

        layoutParams.leftToLeft = existingLayoutParams.leftToLeft;
        layoutParams.topToTop = existingLayoutParams.topToTop;
        layoutParams.rightToRight = existingLayoutParams.rightToRight;
        layoutParams.bottomToBottom = existingLayoutParams.bottomToBottom;

        layoutParams.verticalBias = existingLayoutParams.verticalBias;
        layoutParams.horizontalBias = existingLayoutParams.horizontalBias;

        newImageView.setLayoutParams(layoutParams);

        int viewIndex = parentLayout.indexOfChild(v);
        parentLayout.removeViewAt(viewIndex);
        parentLayout.addView(newImageView, viewIndex);
    }
}