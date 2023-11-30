package com.example.appveterinaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;


public class Animal implements Parcelable {

    public String nFicha;
    public String nAnimal;
    public String tAnimal;
    public String eAnimal;

    public Animal() {

    }

    public String getnFicha() {
        return nFicha;
    }

    public void setnFicha(String nFicha) {
        this.nFicha = nFicha;
    }

    public String getnAnimal() {
        return nAnimal;
    }

    public void setnAnimal(String nAnimal) {
        this.nAnimal = nAnimal;
    }

    public String gettAnimal() {
        return tAnimal;
    }

    public void settAnimal(String tAnimal) {
        this.tAnimal = tAnimal;
    }

    public String geteAnimal() {
        return eAnimal;
    }

    public void seteAnimal(String eAnimal) {
        this.eAnimal = eAnimal;
    }

    @Override
    public String toString() {
        return nAnimal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nFicha);
        dest.writeString(this.nAnimal);
        dest.writeString(this.tAnimal);
        dest.writeString(this.eAnimal);
    }

    public void readFromParcel(Parcel source) {
        this.nFicha = source.readString();
        this.nAnimal = source.readString();
        this.tAnimal = source.readString();
        this.eAnimal = source.readString();
    }

    protected Animal(Parcel in) {
        this.nFicha = in.readString();
        this.nAnimal = in.readString();
        this.tAnimal = in.readString();
        this.eAnimal = in.readString();
    }
    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel source) {
            return new Animal(source);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

}

