package com.norbiupdate1.pokesmile.norbiupdate1;

/**
 * Created by enorsza on 2015.02.02..
 */
public class Update1Codes {
    private int id;
    private int update1Code;
    private String foodName;
    private int cH;
    private int gI;
    private String categorie;

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) { this.categorie = categorie; }

    public long getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getUpdate1Code() { return update1Code; }

    public void setUpdate1Code(int update1Code) {
        this.update1Code = update1Code;
    }

    public int getgI() {
        return gI;
    }

    public void setgI(int gI) {

        this.gI = gI;
    }

    public double getcH() {
        return cH;
    }

    public void setcH(int cH) {
        this.cH = cH;
    }

    public String getFoodName() {

        return titleize(foodName);
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return foodName;
    }

    String titleize(String source){
        boolean cap = true;
        char[]  out = source.toCharArray();
        int i, len = source.length();
        for(i=0; i<len; i++){
            switch(out[i]){
                case 'û':
                    out[i] = 'ű';
                    break;
                case 'Û':
                    out[i] = 'Ű';
                    break;
                case 'ô':
                    out[i] = 'ő';
                    break;
                case 'Ô':
                    out[i] = 'Ő';
                    break;
                default:
                    break;
            }
            if(Character.isWhitespace(out[i])){
                cap = true;
                continue;
            }
            if(cap){
                out[i] = Character.toUpperCase(out[i]);
                cap = false;
            }
        }
        return new String(out);
    }
}
