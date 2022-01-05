package cn.bhshare.kg.services;

import cn.bhshare.kg.models.Drug;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IIndexService {

    void index(HttpServletRequest request);

    List<Drug> searchByDrugName(String name);

    void infos(HttpServletRequest request, String dkgId);

    void getSearchByDrugName(HttpServletRequest request, String search);

//    boolean checkEmail(String uEmail);
//
//    boolean checkUsername(String username);
//
//    boolean dosignup(CUser user);
//
//    boolean dologin(String login, String password, HttpServletRequest request, HttpServletResponse response, String remember);
//
//    boolean forget(CUser user, HttpServletRequest request);
//
//    void person(HttpServletRequest request);
//
//    void college(Integer sId, HttpServletRequest request);
//
//    void college_introduce(Integer sId, HttpServletRequest request);
//
//    void college_position(Integer sId, HttpServletRequest request);
//
//    void college_in(Integer sId, HttpServletRequest request);
//
//    void college_out(Integer sId, HttpServletRequest request);
//
//    void detail(Integer vId, HttpServletRequest request);
//
//    void person_center(Integer uId, HttpServletRequest request);
//
//    boolean sendComment(String content, Integer vId, String[] imgArr, HttpServletRequest request);
//
//    boolean sendComment2(String content, Integer vId, Integer cId, Integer uId, HttpServletRequest request);
//
//    void view(Integer num,Integer sId, HttpServletRequest request);
//
//    void search(HttpServletRequest request);
//
//    void searchResult(String s, HttpServletRequest request);
//
//    void find(HttpServletRequest request);
//
//    void comment(HttpServletRequest request);
//
//    boolean delComment(Integer cId, HttpServletRequest request);
//
//    void getComment(Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException;
//
//    void comment_detail(Integer cId, HttpServletRequest request);
//
//    void modify(HttpServletRequest request);
//
//    boolean domodify(CUser cUser, HttpServletRequest request);
//
//    Integer do_modify_email(String password, HttpServletRequest request);
//
//    Integer do_modify_pass(String pass1, String pass2, HttpServletRequest request);
//
//    boolean doheadicon(String icon, HttpServletRequest request);
//
//    CUpdate checkUpdate(String version, HttpServletRequest request);
//
//    boolean qqlogin(String openid, HttpServletRequest request, HttpServletResponse response);
}