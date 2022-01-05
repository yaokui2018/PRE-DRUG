package cn.bhshare.kg.services.impl;

import cn.bhshare.kg.dao.Neo4jDao;
import cn.bhshare.kg.models.*;
import cn.bhshare.kg.services.IIndexService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;


@Service
public class IndexServiceImpl implements IIndexService {

    @Resource
    Neo4jDao neo4jDao;

//	@Autowired
//    CUserMapper userMapper;
//	@Autowired
//    CSchoolMapper schoolMapper;
//	@Autowired
//    CViewMapper viewMapper;
//	@Autowired
//    CPictureMapper pictureMapper;
//	@Autowired
//    CCommentMapper commentMapper;
//	@Autowired
//	CIndexMapper indexMapper;
//	@Autowired
//	CUpdateMapper updateMapper;


    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
    String pathPic = this.getClass().getResource("/").getPath() + "static/images/picture/";

    /**
     * 主页
     */
    @Override
    public void index(HttpServletRequest request) {
        //景点推荐-8个
//		CViewExample cViewExample = new CViewExample();
//		cViewExample.or().andVFlagNotEqualTo(0).andVFlagNotEqualTo(4);
//		cViewExample.setOrderByClause("v_hot desc");
//		List<CView> cViewList = viewMapper.selectByExample(cViewExample);
//		int i = 0;
//		for (CView cView : cViewList){
//			if (i>7)
//				break;
//			CPictureExample cPictureExample = new CPictureExample();
//			cPictureExample.or().andPFlagEqualTo(1).andPVsidEqualTo(cView.getvId());
//			List<CPicture> pictureList = pictureMapper.selectByExample(cPictureExample);
//			if (pictureList.size()!=0)
//				cViewList.get(i).setvEat(pictureList.get(0).getpFilename());//将图片名暂存eat字段
//			else
//				cViewList.get(i).setvEat(null);
//			i++;
//		}
//		//轮播图
//		CIndexExample cIndexExample = new CIndexExample();
//		cIndexExample.or().andIDisplayEqualTo(1);
//		cIndexExample.setOrderByClause("i_index");
//		List<CIndex> cIndexList = indexMapper.selectByExample(cIndexExample);
//
//		request.setAttribute("cViewList",cViewList.subList(0,cViewList.size()<8?cViewList.size():8));
//		request.setAttribute("cIndexList",cIndexList);
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
        if (search==null || search.equalsIgnoreCase("")){
            request.setAttribute("size", -1);
            return;
        }
        List<Drug> drugList = neo4jDao.searchByDrugName(search);
        request.setAttribute("drugList", drugList);
        request.setAttribute("size", drugList.size());
        request.setAttribute("search", search);
    }


}
