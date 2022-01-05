package cn.bhshare.kg.models;

import java.util.List;

public class SideEffect {
    private String name;
    private List<SideEffectAttr> sideEffectAttrs;
    // 副作用数据（*%-*%）temp
    private String attrString;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SideEffectAttr> getSideEffectAttrs() {
        return sideEffectAttrs;
    }

    public void setSideEffectAttrs(List<SideEffectAttr> sideEffectAttrs) {
        this.sideEffectAttrs = sideEffectAttrs;
    }

    public String getAttrString() {
        return attrString;
    }

    public void setAttrString(String attrString) {
        this.attrString = attrString;
    }
}
