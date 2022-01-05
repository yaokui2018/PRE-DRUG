package cn.bhshare.kg.models;

public class SideEffectAttr {
    private String is_placebo;
    private String frequency_description;
    private String MedDRA_concept_type;
    private float frequency_lower;
    private float frequency_upper;


    public String getIs_placebo() {
        return is_placebo;
    }

    public void setIs_placebo(String is_placebo) {
        this.is_placebo = is_placebo;
    }

    public String getFrequency_description() {
        return frequency_description;
    }

    public void setFrequency_description(String frequency_description) {
        this.frequency_description = frequency_description;
    }

    public String getMedDRA_concept_type() {
        return MedDRA_concept_type;
    }

    public void setMedDRA_concept_type(String medDRA_concept_type) {
        MedDRA_concept_type = medDRA_concept_type;
    }

    public float getFrequency_lower() {
        return frequency_lower;
    }

    public void setFrequency_lower(float frequency_lower) {
        this.frequency_lower = frequency_lower;
    }

    public float getFrequency_upper() {
        return frequency_upper;
    }

    public void setFrequency_upper(float frequency_upper) {
        this.frequency_upper = frequency_upper;
    }
}
