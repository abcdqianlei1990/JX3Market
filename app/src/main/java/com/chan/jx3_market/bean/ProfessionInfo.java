package com.chan.jx3_market.bean;

import java.util.ArrayList;

/**
 * Created by channey on 2017/4/11.
 */

public class ProfessionInfo {
    private String profession;
    private ArrayList<String> subProfession;

    public ProfessionInfo() {
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public ArrayList<String> getSubProfession() {
        return subProfession;
    }

    public void setSubProfession(ArrayList<String> subProfession) {
        this.subProfession = subProfession;
    }
}
