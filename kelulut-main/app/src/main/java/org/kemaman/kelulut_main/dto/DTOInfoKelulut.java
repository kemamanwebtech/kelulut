package org.kemaman.kelulut_main.dto;

/**
 * Created by raf-win10 on 1/25/2017.
 */

public class DTOInfoKelulut {
    String genusName = "";
    String about = "";
    String genusNameShort = "";

    public DTOInfoKelulut(String inGenusName, String inAbout){
        this.genusName = inGenusName;
        this.about = inAbout;
        this.genusNameShort = inGenusName.substring(0, 3);
    }

    public String getGenusName() {
        return genusName;
    }

    public String getAbout() {
        return about;
    }

    public String getGenusNameShort() {
        return genusNameShort;
    }


}
