package cn.bhshare.kg.models;

import java.util.List;

public class CrossReferences {
    private List KEGG;
    private List UMLS;
    private List TTD;
    private List DrugBank;
    private List PharmGKB;
    private List MeSH;
    private List PubChem_CID;

    public List getKEGG() {
        return KEGG;
    }

    public void setKEGG(List KEGG) {
        this.KEGG = KEGG;
    }

    public List getUMLS() {
        return UMLS;
    }

    public void setUMLS(List UMLS) {
        this.UMLS = UMLS;
    }

    public List getTTD() {
        return TTD;
    }

    public void setTTD(List TTD) {
        this.TTD = TTD;
    }

    public List getDrugBank() {
        return DrugBank;
    }

    public void setDrugBank(List drugBank) {
        DrugBank = drugBank;
    }

    public List getPharmGKB() {
        return PharmGKB;
    }

    public void setPharmGKB(List pharmGKB) {
        PharmGKB = pharmGKB;
    }

    public List getMeSH() {
        return MeSH;
    }

    public void setMeSH(List meSH) {
        MeSH = meSH;
    }

    public List getPubChem_CID() {
        return PubChem_CID;
    }

    public void setPubChem_CID(List pubChem_CID) {
        PubChem_CID = pubChem_CID;
    }
}
