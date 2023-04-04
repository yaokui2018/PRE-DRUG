package cn.bhshare.kg.controller;

import cn.bhshare.kg.models.Drug;
import cn.bhshare.kg.services.IIndexService;
import cn.bhshare.kg.tools.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

@Controller
public class IndexController {

    @Resource
    private IIndexService indexService;

    @RequestMapping(value = "search/{search}")
    @ResponseBody
    public Object search(@PathVariable("search") String search) {
        List<Drug> list = indexService.searchByDrugName(search);
        return Result.ok(list);
    }

    @RequestMapping("/")
    public String index0() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/search")
    public String search(HttpServletRequest request, @Param("search") String search) {
        indexService.getSearchByDrugName(request, search);
        return "/search";
    }

    @RequestMapping(value = "/search-cpi")
    public String search_cpi(HttpServletRequest request, @Param("search") String search) {
        request.setAttribute("search", search);
        return "/search_cpi";
    }

    @RequestMapping("/infos/{dkgId}")
    public String infos(HttpServletRequest request, @PathVariable String dkgId) {
        indexService.infos(request, dkgId);
//        request.setAttribute("isApp", isApp);
        return "/infos";
    }
}