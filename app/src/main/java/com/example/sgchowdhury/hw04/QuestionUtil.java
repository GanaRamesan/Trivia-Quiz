package com.example.sgchowdhury.hw04;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sg chowdhury on 30-09-2017.
 */

public class QuestionUtil {



    static ArrayList<Question> parseQuestion(String in) throws JSONException {


        ArrayList<Question> questionArrayList = new ArrayList<>();
        JSONObject root = new JSONObject(in);
        Log.d("answer",root+"");
        JSONArray questions = root.getJSONArray("questions");

        for(int i = 0;i<questions.length();i++){
            JSONObject questionJSONObject = questions.getJSONObject(i);
            Question question = new Question();
            question.setId(questionJSONObject.getInt("id"));
            question.setQuestion(questionJSONObject.getString("text"));
            if (questionJSONObject.has("image"))
            {
                question.setImagehref(questionJSONObject.getString("image"));

            }


            JSONArray array =  questionJSONObject.getJSONObject("choices").getJSONArray("choice");
            question.setAnswer(questionJSONObject.getJSONObject("choices").getInt("answer"));




            for (int k = 0;k<array.length();k++)
            { question.getChoices().add((String) array.get(k));
                //Log.d("shrirupa", question.getChoices().get(k));

            }


            questionArrayList.add(question);
        }


        return questionArrayList;
    }


}
