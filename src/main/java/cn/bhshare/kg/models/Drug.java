package cn.bhshare.kg.models;

import java.util.List;

public class Drug {
    private List name;
    private String dkg_id;
    private List Synonym;
    private String cross_references;
    private String PubChemInfo;

    public List getName() {
        return name;
    }

    public void setName(List name) {
        this.name = name;
    }

    public String getDkg_id() {
        return dkg_id;
    }

    public void setDkg_id(String dkg_id) {
        this.dkg_id = dkg_id;
    }

    public List getSynonym() {
        return Synonym;
    }

    public void setSynonym(List synonym) {
        Synonym = synonym;
    }

    public String getCross_references() {
        return cross_references;
    }

    public void setCross_references(String cross_references) {
        this.cross_references = cross_references;
    }

    public String getPubChemInfo() {
        return PubChemInfo;
    }

    public void setPubChemInfo(String pubChemInfo) {
        PubChemInfo = pubChemInfo;
    }
}
