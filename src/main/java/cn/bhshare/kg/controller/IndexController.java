package cn.bhshare.kg.controller;

import cn.bhshare.kg.models.Drug;
import cn.bhshare.kg.services.IIndexService;
import cn.bhshare.kg.tools.Result;
import org.apache.ibatis.annotations.Param;
import org.neo4j.driver.v1.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.neo4j.driver.v1.Values.parameters;

@Controller
public class IndexController {

    @Resource
    private IIndexService indexService;

    @RequestMapping(value = "search/{search}")
    @ResponseBody
    public Object test(HttpServletRequest request, HttpServletResponse response, @PathVariable("search") String search) {
        List<Drug> list = indexService.searchByDrugName(search);
//        Map<String, Object> map = new HashMap<>();
//        if (list.size() == 1) {
//            return "redirect:/info/" + list.get(0);
//        } else {
//        map.put("code", 200);
//        map.put("data", list);
        return Result.ok(list);
//        }
//
//        try {
//            Driver driver = createDrive();
//            Session session = driver.session();
//            StatementResult result = session.run("MATCH (a:Drug) where {name} in a.name or {name} in a.Synonym " +
//                            " RETURN a.name as name, a.PubChemInfo as PubchemInfo , a.dkg_id as dkg_id ",
//                    parameters("name", search));
//
//            while (result.hasNext()) {
//                Record record = result.next();
//                System.out.println(record.get("PubchemInfo") + " " + record.get("name") + " " + record.get("dkg_id"));
//
//                response.getWriter().write("{\"status\":-1,\"tips\":"+record.get("PubchemInfo")+"\"}");
//            }
//
//            session.close();
//            driver.close();
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

    }

    @RequestMapping("/")
    public String index0(HttpServletRequest request) {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        return "/index";
    }

    @RequestMapping(value = "/search")
    public String search(HttpServletRequest request, @Param("search") String search) {
        indexService.getSearchByDrugName(request, search);
        return "/search";

    }

    @RequestMapping("/infos/{dkgId}")
    public String infos(HttpServletRequest request, @PathVariable String dkgId) {
        indexService.infos(request, dkgId);
//        request.setAttribute("isApp", isApp);
        return "/infos";
    }

//
//    @GetMapping("/person")
//    public String person(Integer isApp,HttpServletRequest request) {
//        userService.person(request);
//        request.setAttribute("isApp",isApp);
//        return "/person";
//    }
//
//    @GetMapping("/signup")
//    public String signup(HttpServletRequest request) {
//        return "/signup";
//    }
//
//    @GetMapping("/login")
//    public String login(String uri,HttpServletRequest request) {
//        request.setAttribute("login",CookieUtils.getCookie("login"));
//        request.setAttribute("pass", CookieUtils.getCookie("pass"));
//        request.setAttribute("remember",CookieUtils.getCookie("remember"));
//        request.setAttribute("uri",uri==null?"index":uri);
//        return "/login";
//    }
//
//    @GetMapping("/forget")
//    public String forget() {
//        return "/forget";
//    }
//
//    @GetMapping("/modify_email")
//    public String modify_email(HttpServletRequest request) {
//        userService.modify(request);
//        return "/modify_email";
//    }
//
//    @GetMapping("/modify_password")
//    public String modify_password() {
//        return "/modify_password";
//    }
//
//    /**
//     * 修改资料
//     */
//    @GetMapping("/modify")
//    public String modify(HttpServletRequest request) {
//        userService.modify(request);
//        return "/modify";
//    }
//
//    @GetMapping("/logout")
//    public String logout() {
//        CookieUtils.removeCookie("user");
//        return "redirect:/login";
//    }
//
//    @GetMapping("/college")
//    public String college(Integer isApp,Integer sId,HttpServletRequest request) {
//        userService.college(sId,request);
//        request.setAttribute("isApp",isApp);
//        return "/college";
//    }
//
//    /**
//     * 学校简介
//     */
//    @GetMapping("/college_introduce")
//    public String college_introduce(Integer sId,HttpServletRequest request) {
//        userService.college_introduce(sId,request);
//        return "/college_introduce";
//    }
//
//    /**
//     * 地理位置
//     */
//    @GetMapping("/college_position")
//    public String college_position(Integer sId,HttpServletRequest request) {
//        userService.college_position(sId,request);
//        return "/college_position";
//    }
//
//    /**
//     * 校内景点
//     */
//    @GetMapping("/college_in")
//    public String college_in(Integer sId,HttpServletRequest request) {
//        userService.college_in(sId,request);
//        return "/college_in";
//    }
//
//    /**
//     * 周边景点
//     */
//    @GetMapping("/college_out")
//    public String college_out(Integer sId,HttpServletRequest request) {
//        userService.college_out(sId,request);
//        return "/college_out";
//    }
//
//    /**
//     * 景点详情
//     */
//    @GetMapping("/detail")
//    public String detail(Integer vId,HttpServletRequest request) {
//        userService.detail(vId,request);
//        return "/detail";
//    }
//
//    /**
//     * 评论详情
//     */
//    @GetMapping("/comment_detail")
//    public String comment_detail(Integer cId,HttpServletRequest request) {
//        userService.comment_detail(cId,request);
//        return "/comment_detail";
//    }
//
//    /**
//     * 评论详情-后台
//     */
//    @GetMapping("/commentLook")
//    public String commentLook(Integer cId,HttpServletRequest request) {
//        userService.comment_detail(cId,request);
//        return "/admin/commentLook";
//    }
//
//    /**
//     * 用户资料
//     */
//    @GetMapping("/person_center")
//    public String person_center(Integer uId,HttpServletRequest request) {
//        userService.person_center(uId,request);
//        return "/person_center";
//    }
//
//    /**
//     * 景点列表
//     * num 1.名胜古迹 2.城市公园 3.游山玩水 4.酒店住宿
//     * sId 学校id
//     */
//    @GetMapping("/view")
//    public String view(Integer num,Integer sId,HttpServletRequest request) {
//        userService.view(num,sId,request);
//        return "/view";
//    }
//
//    /**
//     * 搜索
//     */
//    @GetMapping("/search")
//    public String search(HttpServletRequest request) {
//        userService.search(request);
//        return "/search";
//    }
//
//    /**
//     * 搜索结果
//     */
//    @GetMapping("/searchResult")
//    public String searchResult(String s , HttpServletRequest request) {
//        userService.searchResult(s,request);
//        return "/search_result";
//    }
//
//    /**
//     * 发现
//     */
//    @GetMapping("/find")
//    public String find(Integer isApp, HttpServletRequest request) {
//        userService.find(request);
//        request.setAttribute("isApp",isApp);
//        return "/find";
//    }
//
//    /**
//     * 我的评论
//     */
//    @GetMapping("/comment")
//    public String comment( HttpServletRequest request) {
//        userService.comment(request);
//        return "/comment";
//    }
//
//    /**
//     * 更换头像
//     */
//    @GetMapping("/headicon")
//    public String headicon( HttpServletRequest request) {
//        userService.modify(request);
//        return "/headicon";
//    }
//
//    /**
//     * 关于我们
//     */
//    @GetMapping("/contac_us")
//    public String contac_us( HttpServletRequest request) {
//        return "/contac_us";
//    }
//
//    /**
//     * qq登录
//     */
//    @GetMapping("/qqlogin.html")
//    public String qqlogin( HttpServletRequest request) {
//        return "/qqlogin";
//    }
//
//    /**
//     * qq登录-APP
//     */
//    @RequestMapping("/qqlogin_app.html")
//    public String qqlogin_app(String openid, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setAttribute("openId",openid);
//        return "/qqlogin_app";
//    }
//
//    /**
//     * APP下载
//     */
//    @RequestMapping("/download")
//    public String download(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        CUpdate update = userService.checkUpdate("sdvs0", request);
//        request.setAttribute("update",update);
//        return "/download";
//    }
//
//
//
//
//
//
//
//
//    // ---------------------------------------------post------------------------------------
//
//
//
//
//
//    /**
//     * 获取邮箱验证码 注册
//     */
//    @PostMapping("/ecode_signup")
//    public void ecode_signup(@Param("uEmail") String uEmail, HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("application/json");
//        try {
//            CheckCode checkCode = new CheckCode();
//            if (!userService.checkEmail(uEmail)) {
//                if (Ecode.doGet(uEmail, "注册邮箱验证码", request))
//                    response.getWriter().write("{\"status\":\"1\",\"tips\":\"验证码已发送至你的邮箱，请注意查收\"}");
//                else
//                    response.getWriter().write("{\"status\":0,\"tips\":\"系统繁忙！请稍后再试。\"}");
//            }
//            else
//                response.getWriter().write("{\"status\":-1,\"tips\":\"该邮箱已被注册！\"}");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 找回密码 邮箱验证
//     */
//    @PostMapping("/ecode_forget")
//    public void ecode_forget(String uEmail, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
//        if (userService.checkEmail(uEmail)) {
//            if (Ecode.doGet(uEmail, "找回密码-邮箱验证", request))
//                response.getWriter().write("{\"status\":\"1\",\"tips\":\"验证码已发送至你的邮箱，请注意查收\"}");
//            else
//                response.getWriter().write("{\"status\":0,\"tips\":\"系统繁忙！请稍后再试。\"}");
//        }
//        else
//            response.getWriter().write("{\"status\":-1,\"tips\":\"该邮箱尚未被注册！\"}");
//    }
//
//    /**
//     * 获取邮箱验证码 更换邮箱
//     */
//    @PostMapping("/ecode_modify")
//    public void ecode_modify(@Param("uEmail") String uEmail, HttpServletRequest request, HttpServletResponse response) {
//        response.setContentType("application/json");
//        try {
//            if (!userService.checkEmail(uEmail)) {
//                if (Ecode.doGet(uEmail, "更换邮箱验证码", request))
//                    response.getWriter().write("{\"status\":\"1\",\"tips\":\"验证码已发送至你的邮箱，请注意查收\"}");
//                else
//                    response.getWriter().write("{\"status\":0,\"tips\":\"系统繁忙！请稍后再试。\"}");
//            }
//            else
//                response.getWriter().write("{\"status\":-1,\"tips\":\"该邮箱已被占用！\"}");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 注册 表单
//     */
//    @PostMapping("/dosignup")
//    public void dosignup(CUser user, @Param("emailcode") String emailcode, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        CheckCode checkCode = new CheckCode();
//        if (checkCode.checkEmail(user.getuEmail(),request)) {
//            if (checkCode.checkEcode(emailcode, request)) {
//                user.setuIp(GetIpAddr.getIpAddr(request));
//                if (userService.checkUsername(user.getuName())){
//                    response.getWriter().write("{\"status\":-3,\"tips\":\"该用户名已被注册！\"}");
//                }
//                else if (userService.dosignup(user)) {
//                    request.getSession().removeAttribute("ecode");
//                    response.getWriter().write("{\"status\":1,\"tips\":\"恭喜，注册成功。\"}");
//                } else
//                    response.getWriter().write("{\"status\":0,\"tips\":\"注册失败，请稍后再试。\"}");
//            } else
//                response.getWriter().write("{\"status\":-1,\"tips\":\"邮箱验证码不正确！\"}");
//        }
//        else
//            response.getWriter().write("{\"status\":-2,\"tips\":\"请勿肆意修改邮箱！！\"}");
//    }
//
//    /**
//     * 登录 表单
//     */
//    @PostMapping("/dologin")
//    public void mydologin(String login,String password,@Param("remember") String remember, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        boolean bl = userService.dologin(login, password, request, response, remember);
//        if (bl) {
//            response.getWriter().write("{\"status\":1,\"tips\":\"登录成功！\"}");
//        } else {
//            response.getWriter().write("{\"status\":0,\"tips\":\"登录失败，用户名或密码错误！\"}");
//        }
//    }
//
//    /**
//     * qq登录
//     */
//    @RequestMapping("/qqlogin")
//    public void mydologin(String openid, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        boolean bl = userService.qqlogin(openid, request, response);
//        if (bl) {
//            response.getWriter().write("{\"status\":1,\"tips\":\"QQ登录成功。\"}");
//        } else {
//            response.getWriter().write("{\"status\":0,\"tips\":\"QQ登录失败！\"}");
//        }
//    }
//
//    /**
//     * 找回密码 表单
//     */
//    @PostMapping("/doforget")
//    public void doforget(CUser user, @Param("emailcode") String emailcode,HttpServletRequest request, HttpServletResponse response) throws IOException {
//        CheckCode checkCode = new CheckCode();
//        if(!checkCode.checkEcode(emailcode,request)) {
//            response.getWriter().write("{\"status\":0,\"tips\":\"邮箱验证码不正确。\"}");
//            return;
//        }
//        else {//重置密码
//            boolean bs = userService.forget(user, request);
//            if (bs) {
//                request.getSession().removeAttribute("useremail");
//                request.getSession().removeAttribute("ecode");
//                CookieUtils.removeCookie("emailcode");
//                response.getWriter().write("{\"status\":1,\"tips\":\"密码重置成功。\"}");
//            } else
//                response.getWriter().write("{\"status\":0,\"tips\":\"密码重置失败，请稍后再试。\"}");
//
//        }
//    }
//
//   /**
//     * 更换邮箱 表单
//     */
//    @PostMapping("/do_modify_email")
//    public void do_modify_email(String password, @Param("emailcode") String emailcode,HttpServletRequest request, HttpServletResponse response) throws IOException {
//        CheckCode checkCode = new CheckCode();
//        if(!checkCode.checkEcode(emailcode,request)) {
//            response.getWriter().write("{\"status\":0,\"tips\":\"邮箱验证码不正确。\"}");
//            return;
//        }
//        else {//重置密码
//            Integer bs = userService.do_modify_email(password, request);
//            if (bs.equals(1)) {
//                request.getSession().removeAttribute("useremail");
//                CookieUtils.removeCookie("emailcode");
//                response.getWriter().write("{\"status\":1,\"tips\":\"更换邮箱成功。\"}");
//            }else if (bs.equals(-1)){
//                response.getWriter().write("{\"status\":-1,\"tips\":\"账号密码不正确！\"}");
//            }
//            else
//                response.getWriter().write("{\"status\":0,\"tips\":\"邮箱更换失败，请稍后再试。\"}");
//
//        }
//    }
//
//   /**
//     * 修改密码 表单
//     */
//    @PostMapping("/do_modify_pass")
//    public void do_modify_pass(String pass1,String pass2,HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Integer bs = userService.do_modify_pass(pass1,pass2, request);
//        if (bs.equals(1)) {
//            response.getWriter().write("{\"status\":1,\"tips\":\"修改密码成功。\"}");
//        }else if (bs.equals(-1)){
//            response.getWriter().write("{\"status\":-1,\"tips\":\"原密码不正确！\"}");
//        }
//        else
//            response.getWriter().write("{\"status\":0,\"tips\":\"修改失败，请稍后再试。\"}");
//
//    }
//
//    /**
//     * 提交评论
//     */
//    @PostMapping("/sendComment")
//    public void sendComment(String content,Integer vId,@RequestParam("imgArr[]") String[] imgArr,HttpServletRequest request, HttpServletResponse response) throws IOException {
//        boolean bs = userService.sendComment(content, vId,imgArr,request);
//        if (bs) {
//            response.getWriter().write("{\"status\":1,\"tips\":\"评论成功。\"}");
//        } else
//            response.getWriter().write("{\"status\":0,\"tips\":\"评论失败，请稍后再试。\"}");
//    }
//    /**
//     * 回复评论
//     */
//    @PostMapping("/sendComment2")
//    public void sendComment2(String content,Integer vId,Integer cId,Integer uId,HttpServletRequest request, HttpServletResponse response) throws IOException {
//        boolean bs = userService.sendComment2(content, vId,cId,uId,request);
//        if (bs) {
//            response.getWriter().write("{\"status\":1,\"tips\":\"评论成功。\"}");
//        } else
//            response.getWriter().write("{\"status\":0,\"tips\":\"评论失败，请稍后再试。\"}");
//    }
//
//    /**
//     * 清除搜索历史
//     */
//    @PostMapping("/clearSearch")
//    public void clearSearch(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        CookieUtils.removeCookie("search");
//        response.getWriter().write("{\"status\":1,\"tips\":\"清除成功。\"}");
//    }
//
//    /**
//     * 删除评论
//     */
//    @PostMapping("/delComment")
//    public void delComment(Integer cId,HttpServletRequest request, HttpServletResponse response) throws IOException {
//        boolean b = userService.delComment(cId,request);
//        if (b)
//            response.getWriter().write("{\"status\":1,\"tips\":\"删除成功。\"}");
//        else
//            response.getWriter().write("{\"status\":0,\"tips\":\"删除失败。\"}");
//    }
//
//    /**
//     * 加载评论
//     *  id ： 最新cid
//     */
//    @PostMapping("/getComment")
//    public void getComment(Integer id,HttpServletRequest request, HttpServletResponse response) throws IOException {
//        userService.getComment(id,request,response);
//    }
//
//    /**
//     * 修改资料
//     */
//    @PostMapping("/domodify")
//    public void domodify(CUser cUser, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        boolean bl = userService.domodify(cUser, request);
//        if (bl) {
//            response.getWriter().write("{\"status\":1,\"tips\":\"修改成功！\"}");
//        } else {
//            response.getWriter().write("{\"status\":0,\"tips\":\"修改失败，请稍后再试。\"}");
//        }
//    }
//
//    /**
//     * 更换头像
//     */
//    @PostMapping("/doheadicon")
//    public void doheadicon(String icon, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        boolean bl = userService.doheadicon(icon, request);
//        if (bl) {
//            response.getWriter().write("{\"status\":1,\"tips\":\"更换成功！\"}");
//        } else {
//            response.getWriter().write("{\"status\":0,\"tips\":\"头像更换失败，请稍后再试。\"}");
//        }
//    }
//
//    /**
//     * 检查更新
//     */
//    @ResponseBody
//    @PostMapping("/checkUpdate")
//    public CUpdate checkUpdate(String version, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        CUpdate update = userService.checkUpdate(version, request);
//        return update;
//    }
//

}