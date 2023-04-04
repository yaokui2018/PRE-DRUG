package cn.bhshare.kg.services;

import cn.bhshare.kg.models.Drug;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IIndexService {

    void index(HttpServletRequest request);

    List<Drug> searchByDrugName(String name);

    void infos(HttpServletRequest request, String dkgId);

    void getSearchByDrugName(HttpServletRequest request, String search);

}