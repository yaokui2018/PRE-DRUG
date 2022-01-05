package cn.bhshare.kg.models;

import java.util.List;

public class PubChemInfo {
    private long CID = -1;
    private String MolecularFormula = "-";
    private String MolecularWeight = "-";
    private String CanonicalSMILES = "-";
    private String InChI = "-";
    private String InChIKey = "-";
    private String IUPACName = "-";
    private float XLogP = -1f;
    private String ExactMass = "-";
    private String MonoisotopicMass = "-";
    private int TPSA = -1;
    private int Complexity = -1;
    private int Charge = -1;
    private int HBondDonorCount = -1;
    private int HBondAcceptorCount = -1;
    private int RotatableBondCount = -1;
    private int HeavyAtomCount = -1;
    private int IsotopeAtomCount = -1;
    private int AtomStereoCount = -1;
    private int DefinedAtomStereoCount = -1;
    private int UndefinedAtomStereoCount = -1;
    private int BondStereoCount = -1;
    private int DefinedBondStereoCount = -1;
    private int UndefinedBondStereoCount = -1;
    private int CovalentUnitCount = -1;
    private int ConformerCount3D = -1;
    private String Fingerprint2D = "-";
    private String Title = "-";

    public long getCID() {
        return CID;
    }

    public void setCID(long CID) {
        this.CID = CID;
    }

    public String getMolecularFormula() {
        return MolecularFormula;
    }

    public void setMolecularFormula(String molecularFormula) {
        MolecularFormula = molecularFormula;
    }

    public String getMolecularWeight() {
        return MolecularWeight;
    }

    public void setMolecularWeight(String molecularWeight) {
        MolecularWeight = molecularWeight;
    }

    public String getCanonicalSMILES() {
        return CanonicalSMILES;
    }

    public void setCanonicalSMILES(String canonicalSMILES) {
        CanonicalSMILES = canonicalSMILES;
    }

    public String getInChI() {
        return InChI;
    }

    public void setInChI(String inChI) {
        InChI = inChI;
    }

    public String getInChIKey() {
        return InChIKey;
    }

    public void setInChIKey(String inChIKey) {
        InChIKey = inChIKey;
    }

    public String getIUPACName() {
        return IUPACName;
    }

    public void setIUPACName(String IUPACName) {
        this.IUPACName = IUPACName;
    }

    public float getXLogP() {
        return XLogP;
    }

    public void setXLogP(float XLogP) {
        this.XLogP = XLogP;
    }

    public String getExactMass() {
        return ExactMass;
    }

    public void setExactMass(String exactMass) {
        ExactMass = exactMass;
    }

    public String getMonoisotopicMass() {
        return MonoisotopicMass;
    }

    public void setMonoisotopicMass(String monoisotopicMass) {
        MonoisotopicMass = monoisotopicMass;
    }

    public int getTPSA() {
        return TPSA;
    }

    public void setTPSA(int TPSA) {
        this.TPSA = TPSA;
    }

    public int getComplexity() {
        return Complexity;
    }

    public void setComplexity(int complexity) {
        Complexity = complexity;
    }

    public int getCharge() {
        return Charge;
    }

    public void setCharge(int charge) {
        Charge = charge;
    }

    public int getHBondDonorCount() {
        return HBondDonorCount;
    }

    public void setHBondDonorCount(int HBondDonorCount) {
        this.HBondDonorCount = HBondDonorCount;
    }

    public int getHBondAcceptorCount() {
        return HBondAcceptorCount;
    }

    public void setHBondAcceptorCount(int HBondAcceptorCount) {
        this.HBondAcceptorCount = HBondAcceptorCount;
    }

    public int getRotatableBondCount() {
        return RotatableBondCount;
    }

    public void setRotatableBondCount(int rotatableBondCount) {
        RotatableBondCount = rotatableBondCount;
    }

    public int getHeavyAtomCount() {
        return HeavyAtomCount;
    }

    public void setHeavyAtomCount(int heavyAtomCount) {
        HeavyAtomCount = heavyAtomCount;
    }

    public int getIsotopeAtomCount() {
        return IsotopeAtomCount;
    }

    public void setIsotopeAtomCount(int isotopeAtomCount) {
        IsotopeAtomCount = isotopeAtomCount;
    }

    public int getAtomStereoCount() {
        return AtomStereoCount;
    }

    public void setAtomStereoCount(int atomStereoCount) {
        AtomStereoCount = atomStereoCount;
    }

    public int getDefinedAtomStereoCount() {
        return DefinedAtomStereoCount;
    }

    public void setDefinedAtomStereoCount(int definedAtomStereoCount) {
        DefinedAtomStereoCount = definedAtomStereoCount;
    }

    public int getUndefinedAtomStereoCount() {
        return UndefinedAtomStereoCount;
    }

    public void setUndefinedAtomStereoCount(int undefinedAtomStereoCount) {
        UndefinedAtomStereoCount = undefinedAtomStereoCount;
    }

    public int getBondStereoCount() {
        return BondStereoCount;
    }

    public void setBondStereoCount(int bondStereoCount) {
        BondStereoCount = bondStereoCount;
    }

    public int getDefinedBondStereoCount() {
        return DefinedBondStereoCount;
    }

    public void setDefinedBondStereoCount(int definedBondStereoCount) {
        DefinedBondStereoCount = definedBondStereoCount;
    }

    public int getUndefinedBondStereoCount() {
        return UndefinedBondStereoCount;
    }

    public void setUndefinedBondStereoCount(int undefinedBondStereoCount) {
        UndefinedBondStereoCount = undefinedBondStereoCount;
    }

    public int getCovalentUnitCount() {
        return CovalentUnitCount;
    }

    public void setCovalentUnitCount(int covalentUnitCount) {
        CovalentUnitCount = covalentUnitCount;
    }

    public int getConformerCount3D() {
        return ConformerCount3D;
    }

    public void setConformerCount3D(int conformerCount3D) {
        ConformerCount3D = conformerCount3D;
    }

    public String getFingerprint2D() {
        return Fingerprint2D;
    }

    public void setFingerprint2D(String fingerprint2D) {
        Fingerprint2D = fingerprint2D;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
