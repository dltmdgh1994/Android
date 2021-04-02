package org.lsh.pokemonbook;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Pokemon implements Parcelable,Serializable{
    //intent에 객체를 담기 위해 Serializable이 필요
    //String뿐만이 아니라 여러가지 타입이 섞여 있어서 intent에 객체를 담기 위해 Parcelable도 필요
    int num;
    String name;
    int img;
    String[] types;
    int candy;
    int cp;
    int att;
    int dep;
    int hel;
    String pro;
    String[] techs;

    public Pokemon(Parcel pc){
        readFromParcel(pc);
    }

    public Pokemon(int num, String name, int img, String[] types, int candy, int cp, int att, int dep, int hel, String pro, String[] techs) {
        this.num = num;
        this.name = name;
        this.img = img;
        this.types = types;
        this.candy = candy;
        this.cp = cp;
        this.att = att;
        this.dep = dep;
        this.hel = hel;
        this.pro = pro;
        this.techs = techs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(num);
        dest.writeString(name);
        dest.writeInt(img);
        dest.writeStringArray(types);
        dest.writeInt(candy);
        dest.writeInt(cp);
        dest.writeInt(att);
        dest.writeInt(dep);
        dest.writeInt(hel);
        dest.writeString(pro);
        dest.writeStringArray(techs);
    }

    private void readFromParcel(Parcel pc){
        this.num = pc.readInt();
        this.name = pc.readString();
        this.img = pc.readInt();
        this.types = pc.createStringArray();         //String[]
        this.candy = pc.readInt();
        this.cp = pc.readInt();
        this.att = pc.readInt();
        this.dep = pc.readInt();
        this.hel = pc.readInt();
        this.pro = pc.readString();
        this.techs = pc.createStringArray();
        //this.img = pc.readParcelable(Bitmap.class.getClassLoader()); 비트맵
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public int getCandy() {
        return candy;
    }

    public void setCandy(int candy) {
        this.candy = candy;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getAtt() {
        return att;
    }

    public void setAtt(int att) {
        this.att = att;
    }

    public int getDep() {
        return dep;
    }

    public void setDep(int dep) {
        this.dep = dep;
    }

    public int getHel() {
        return hel;
    }

    public void setHel(int hel) {
        this.hel = hel;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String[] getTechs() {
        return techs;
    }

    public void setTechs(String[] techs) {
        this.techs = techs;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };
}
