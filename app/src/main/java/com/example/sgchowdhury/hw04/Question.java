package com.example.sgchowdhury.hw04;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sg chowdhury on 29-09-2017.
 */

public class Question implements Parcelable {

    String question;
    int id;
    String imagehref;
    int answer;
    List<String> choices = new ArrayList<>();

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagehref() {
        return imagehref;
    }

    public void setImagehref(String imagehref) {
        this.imagehref = imagehref;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public static Creator<Question> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.imagehref);

        dest.writeInt(this.answer);
        dest.writeString(this.question);
        dest.writeInt(this.id);
        dest.writeStringList(this.choices);
    }

    public Question() {
    }

    protected Question(Parcel in) {
        this.imagehref = in.readString();
        this.answer = in.readInt();
        this.question = in.readString();
        this.id = in.readInt();
        this.choices = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
