package com.lifeistech.kuizuapuri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final int QUESTION_COUNT = 10;


    TextView textView;

    Button ansButton1;
    Button ansButton2;
    Button ansButton3;
    Button ansButton4;
    Button nextButton;

    Random random = new Random();


    String[] problems; //①問題文の配列
    String[][] answers; //②選択肢の配列　※これは二重配列というものだよ、"java 二重配列"で検索してみよう！
    String[] kaisetus;
    int[] correctAnswerNos; //③正解の選択肢Noの配列
    int[] answerSituations; //④ユーザーの回答状況 -1:未回答 0:正解 1:不正解
    int point;
    int answerCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView1);

        ansButton1 = findViewById(R.id.ans_botton_1);
        ansButton2 = findViewById(R.id.ans_button_2);
        ansButton3 = findViewById(R.id.ans_button_3);
        ansButton4 = findViewById(R.id.ans_button_4);
        nextButton = findViewById(R.id.next_button);


        problems = new String[QUESTION_COUNT];
        answers = new String[QUESTION_COUNT][];
        kaisetus = new String[QUESTION_COUNT];
        correctAnswerNos = new int[QUESTION_COUNT];
        answerSituations = new int[QUESTION_COUNT];
        for (int i = 0; i < QUESTION_COUNT; i++) {
            answerSituations[i] = 0;
        }

        questionInit();


        point = 0;
        answerCount = 0;
        QuizDetahyouzi(answerCount); //answerCount番目のクイズデータを設定する

        setTitle("問題");
    }

    //問題を初期化するメソッド
    private void questionInit(){
        //1問目
        problems[0] = "鎌倉時代末期に成立した随筆「徒然草」の作者は誰か答えよ。";
        answers[0] = new String[]{"兼好法師", "紀友則", "琵琶法師", "紀貫之"};
        kaisetus[0] = "琵琶法師は平家物語、紀貫之は土佐日記を書いているよ。";
        correctAnswerNos[0] = 0;//※0番目の選択肢が正解の時

        //2問目
        problems[1] = "熱・電気を伝えやすい物体「導体」の中で、一番抵抗が大きい物質を答えよ。";
        answers[1] = new String[]{"鉄", "アルミニウム", "ニクロム", "金"};
        kaisetus[1] = "一番抵抗が低い導体は「銀」だよ。";
        correctAnswerNos[1] = 2;//※2番目の選択肢が正解の時

        //3問目
        problems[2] = "1960年にアフリカで17か国が独立し、◯◯と呼ばれました。◯◯に入る言葉を答えよ。";
        answers[2] = new String[]{"植民地支配の終わり", "アフリカの年", "アフリカ独立記念", "アフリカ解放記念"};
        kaisetus[2] = "この時独立したのはフランス植民地であったところが多いよ。";
        correctAnswerNos[2] = 1;//※1番目の選択肢が正解の時

        //4問目
        problems[3] = "ポリエチレンテレフタラートの燃え方の特徴を答えよ。";
        answers[3] = new String[]{"溶けて燃える", "着火してもすぐ火が消える", "ろうの匂いがする", "すすを出して燃える"};
        kaisetus[3] = "ポリエチレンテレフタラートはペットボトル容器に使われているよ。";
        correctAnswerNos[3] = 3;//※3番目の選択肢が正解の時

        //5問目
        problems[4] = "次のうち、不可算名詞ではない単語を答えよ。";
        answers[4] = new String[]{"furniture", "tree", "air", "art"};
        kaisetus[4] = "furniture(家具)は、総称として使われる名詞で漠然としているので不可算名詞となるよ。";
        correctAnswerNos[4] = 1;

        //6問目
        problems[5] = "sinθの日本語名称を答えよ。";
        answers[5] = new String[]{"正弦", "正接", "余弦", "負弦"};
        kaisetus[5] = "余弦はcosθ、正接はtanθ。sinθ、cosθ、tanθ3つまとめて「三角比」と呼ぶよ。";
        correctAnswerNos[5] = 0;

        //7問目
        problems[6] = "東西ドイツの統一がされた年を答えよ。";
        answers[6] = new String[]{"1990年", "1989年", "1971年", "1995年"};
        kaisetus[6] = "1989年に冷戦の象徴だったベルリンの壁が多くの民衆の手で壊され、翌年1990年10月3日に東ドイツと西ドイツが統一した。";
        correctAnswerNos[6] = 0;

        //8問目
        problems[7] = "次の中から芥川龍之介が書いた作品ではないものを答えよ。";
        answers[7] = new String[]{"杜子春", "鼻", "山椒大夫", "地獄変"};
        kaisetus[7] = "「山椒大夫」の作者は森鴎外だよ。";
        correctAnswerNos[7] = 2;

        //9問目
        problems[8] = "原子量・分子量・式量にgをつけた質量の中には6×10の23乗個の粒子が含まれる。この数を何と言うか答えよ。";
        answers[8] = new String[]{"存在数", "アボカドロ数", "モル数", "アボガドロ数"};
        kaisetus[8] = "名前の由来はイタリア出身の物理学者「アボガドロ」からきている。";
        correctAnswerNos[8] = 3;

        //10問目
        problems[9] = "二等辺三角形の定理ではないものを答えよ。";
        answers[9] = new String[]{"2つの底角は等しい", "2辺が等しい三角形", "頂点の二等分線は底辺を垂直に2等分する", "3つの角が全て等しい"};
        kaisetus[9] = "「2辺が等しい三角形」は定義と呼ばれているよ。";
        correctAnswerNos[9] = 1;

        //問題を増やす場合、ここに書く


        //QUESTIONS_COUNT問目
    }

    //クイズデータをViewに設定する処理。quizNoは問題番号を表していて、基本的に外部からanswerCountを入れるよ
    private void QuizDetahyouzi(int quizNo){
        textView.setText(problems[quizNo]); //quizNo番目の問題文を入れる
        ansButton1.setText(answers[quizNo][0]); //quizNo番目の0番目の選択肢を入れる
        ansButton2.setText(answers[quizNo][1]); //quizNo番目の1番目の選択肢を入れる
        ansButton3.setText(answers[quizNo][2]);
        ansButton4.setText(answers[quizNo][3]);
    }


    public void ansButton1(View v){
        shoriQuiz(0);
    }

    public void ansButton2(View v){
        shoriQuiz(1);
    }

    public void ansButton3(View v){
        shoriQuiz(2);
    }

    public void ansButton4(View v){
        shoriQuiz(3);
    }

    public void nextButton(View v){
        answerCount += 1;

        if(answerCount >= QUESTION_COUNT){
            //クイズが終了

            Intent intent = new Intent(this,ResultActivity.class);
            intent.putExtra("tokuten",point);
            startActivity(intent);
            finish();


//            textView.setText(point + "点");
            //ボタンを見えなくするよ
//            nextButton.setVisibility(View.GONE);
//            ansButton1.setVisibility(View.GONE);
//            ansButton2.setVisibility(View.GONE);
//            ansButton3.setVisibility(View.GONE);
//            ansButton4.setVisibility(View.GONE);
        }else{
            nextButton.setVisibility(View.GONE);
            ansButton1.setVisibility(View.VISIBLE);
            ansButton2.setVisibility(View.VISIBLE);
            ansButton3.setVisibility(View.VISIBLE);
            ansButton4.setVisibility(View.VISIBLE);

            //次の問題の表示
            QuizDetahyouzi(answerCount);
        }


    }


    //クイズを処理するメソッド(引数のintには押したボタンの番号を入れるよ)
    private void shoriQuiz(int index){
        boolean answer = false;

        //押したボタンの番号と正解番号が一緒なら正解にする
        if(index == correctAnswerNos[answerCount]){
            answer = true;
        }

        if (answer) {
            Toast.makeText(this, "正解！", Toast.LENGTH_SHORT).show();
        }else{
            //正しい回答の表示
            String correctChoice = answers[answerCount][correctAnswerNos[answerCount]];
            Toast.makeText(this, "不正解...。正解は、" + correctChoice, Toast.LENGTH_SHORT).show();
        }

        //解説を表示するには後々に↓らへんを書き換えることになるかなー
        if (answer) {
            point++;
            Log.d("answer", "正解:" + point);
        }else{
            Log.d("answer", "不正解:" + point);
        }

        textView.setText(kaisetus[answerCount]);

        nextButton.setVisibility(View.VISIBLE);
        ansButton1.setVisibility(View.GONE);
        ansButton2.setVisibility(View.GONE);
        ansButton3.setVisibility(View.GONE);
        ansButton4.setVisibility(View.GONE);


    }
}

