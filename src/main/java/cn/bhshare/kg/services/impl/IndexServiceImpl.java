package cn.bhshare.kg.services.impl;

import cn.bhshare.kg.dao.Neo4jDao;
import cn.bhshare.kg.models.*;
import cn.bhshare.kg.services.IIndexService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Service
public class IndexServiceImpl implements IIndexService {

    @Resource
    Neo4jDao neo4jDao;

    /**
     * 主页
     */
    @Override
    public void index(HttpServletRequest request) {

    }

    @Override
    public List<Drug> searchByDrugName(String name) {
        return neo4jDao.searchByDrugName(name);
    }

    @Override
    public void infos(HttpServletRequest request, String dkgId) {
        Drug drug = neo4jDao.getDrugByDkgid(dkgId);
        if (drug == null) {
            request.setAttribute("pubChemInfo", null);
            request.setAttribute("Synonym", null);
            request.setAttribute("crossReferences", null);
            return;
        }
        JSONObject jsonObject = JSON.parseObject(drug.getPubChemInfo());
        PubChemInfo pubChemInfo = JSONObject.toJavaObject(jsonObject, PubChemInfo.class);
        String cross_references = drug.getCross_references();
        if (cross_references == null || !cross_references.contains("{")) { // 判断是否是JSON
            cross_references = "{}";
        }
        jsonObject = JSON.parseObject(cross_references);
        CrossReferences crossReferences = JSONObject.toJavaObject(jsonObject, CrossReferences.class);
        crossReferences.setPubChem_CID((List) jsonObject.get("PubChem CID"));

        // 判断pubChemInfo信息是否扩充
        if (pubChemInfo == null) {
            pubChemInfo = new PubChemInfo();
            pubChemInfo.setTitle((String) drug.getName().get(0) + " [数据不完全]");
            try {
                pubChemInfo.setCID((Long) crossReferences.getPubChem_CID().get(0));
            } catch (Exception e) {
                System.out.println("Pubchem Cid 不存在");
                System.out.println(e);
            }
        } else if (pubChemInfo.getTitle().equalsIgnoreCase("-")) {
            pubChemInfo.setTitle((String) drug.getName().get(0));
        }

        // 判断同义词数据是否存在
        List synonym = drug.getSynonym();
        if (synonym == null) {
            request.setAttribute("SynonymSize", 0);
        } else {
            request.setAttribute("SynonymSize", synonym.size());
        }

        request.setAttribute("pubChemInfo", pubChemInfo);
        request.setAttribute("Synonym", synonym);
        request.setAttribute("crossReferences", crossReferences);

        // 副作用信息
        List<SideEffect> sideEffectList = neo4jDao.getSideEffectByDkgid(dkgId);

        for (int i = 0; i < sideEffectList.size(); ++i) {
            if (sideEffectList.get(i).getAttrString() == null || sideEffectList.get(i).getAttrString().equalsIgnoreCase("NULL")) { // 数据没更新
                sideEffectList.get(i).setAttrString("-");
                continue;
            } else {
                JSONArray jsonArray = JSONArray.parseArray(sideEffectList.get(i).getAttrString());
                float lower = 10f;
                float upper = -10f;
                for (int i1 = 0; i1 < jsonArray.size(); i1++) {
                    JSONObject object = jsonArray.getJSONObject(i1);
                    SideEffectAttr sideEffectAttr = JSONObject.toJavaObject(object, SideEffectAttr.class);

                    if (!sideEffectAttr.getMedDRA_concept_type().equalsIgnoreCase("LLT")) {
                        continue;
                    }
                    if (sideEffectAttr.getFrequency_lower() < lower) {
                        lower = sideEffectAttr.getFrequency_lower();
                    }
                    if (sideEffectAttr.getFrequency_upper() > upper) {
                        upper = sideEffectAttr.getFrequency_upper();
                    }
                }
                if (lower == 10f && upper == -10f) {
                    sideEffectList.get(i).setAttrString("-");
                } else if (lower == 10f) {
                    sideEffectList.get(i).setAttrString(upper * 100 + "%");
                } else if (upper == -10f) {
                    sideEffectList.get(i).setAttrString(lower * 100 + "%");
                } else {
                    sideEffectList.get(i).setAttrString(lower * 100 + "% - " + upper * 100 + "%");
                }
            }
        }

        request.setAttribute("sideEffectList", sideEffectList);

    }

    @Override
    public void getSearchByDrugName(HttpServletRequest request, String search) {
        if (search == null || search.equalsIgnoreCase("")) {
            request.setAttribute("size", -1);
            return;
        }
        List<Drug> drugList = neo4jDao.searchByDrugName(search);
        request.setAttribute("drugList", drugList);
        request.setAttribute("size", drugList.size());
        request.setAttribute("search", search);
    }


}
