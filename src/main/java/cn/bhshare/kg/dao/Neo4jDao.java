package cn.bhshare.kg.dao;

import cn.bhshare.kg.models.Drug;
import cn.bhshare.kg.models.SideEffect;
import cn.bhshare.kg.models.SideEffectAttr;
import org.neo4j.driver.v1.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;

@Repository
@Service
public class Neo4jDao {

    private Driver GraphDatabaseDriver = null;

    private Driver createDrive() {
        if (GraphDatabaseDriver == null) {
            GraphDatabaseDriver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "1"));
        }
        return GraphDatabaseDriver;
    }

    private void closeConnect(Session session, Driver driver) {
        session.close();
        driver.close();
        GraphDatabaseDriver = null;
    }

    /**
     * 根据名称查询药物
     *
     * @param name 药物名（首字母一般大写）
     * @return 药物Dkg_id列表
     */
    public List<Drug> searchByDrugName(String name) {
        Driver driver = createDrive();
        Session session = driver.session();
        StatementResult result = session.run("MATCH (a:Drug) where {name} in a.name or {name} in a.Synonym or a.PubChemInfo contains {info} " +
                        " RETURN a.dkg_id as dkg_id, a.name as name",
                parameters("name", name, "info", "\"CID\": "+name+","));
        List<Drug> list = new ArrayList<>();
        while (result.hasNext()) {
            Drug drug = new Drug();
            Record record = result.next();
            drug.setName(record.get("name").asList());
            drug.setDkg_id(record.get("dkg_id").asString());
            list.add(drug);
        }
        closeConnect(session, driver);
        return list;
    }

    /**
     * 查询药物信息
     *
     * @param dkgId id
     */
    public Drug getDrugByDkgid(String dkgId) {
        Driver driver = createDrive();
        Session session = driver.session();
        StatementResult result = session.run("MATCH (a:Drug{dkg_id:{id}}) RETURN a.name as name, a.Synonym as Synonym, " +
                        "a.PubChemInfo as PubChemInfo, a.cross_references as cross_references ",
                parameters("id", dkgId));
        Drug drug = new Drug();
        if (result.hasNext()) {
            Record record = result.next();
            drug.setName(record.get("name").asList());
            try {
                drug.setSynonym(record.get("Synonym").asList());
                drug.setPubChemInfo(record.get("PubChemInfo").asString());
            } catch (Exception e) {
                System.out.println("药物数据未更新PubChemInfo：" + e);
            }
            drug.setCross_references(record.get("cross_references").asString());
        } else {
            drug = null;
        }
        closeConnect(session, driver);
        return drug;
    }

    /**
     * 获取副作用列表
     *
     * @param dkgId 药物id
     * @return 副作用列表
     */
    public List<SideEffect> getSideEffectByDkgid(String dkgId) {
        Driver driver = createDrive();
        Session session = driver.session();
        StatementResult result = session.run("MATCH (n)-[r:CAUSES_DrSe]->(p) where n.dkg_id = {id} RETURN p.name as name, r.attribution as attribution order by r.attribution",
                parameters("id", dkgId));
        List<SideEffect> sideEffectList = new ArrayList<>();
        SideEffect sideEffect;
//        boolean flag = false;

        while (result.hasNext()) {
//            flag = true;
            sideEffect = new SideEffect();
            Record record = result.next();
            sideEffect.setName(String.valueOf(record.get("name")).replace("[\"","").replace("\"]",""));
            try {
                sideEffect.setAttrString(record.get("attribution").asString());
            } catch (Exception e) {
                System.out.println("副作用属性未更新SideEffectAttr：" + e);
            }
            sideEffectList.add(sideEffect);
        }

//        if (flag == false) {
//            sideEffectList = null;
//        }
        closeConnect(session, driver);
        return sideEffectList;
    }

//    /**
//     * 用户登录
//     * @param users
//     * @return
//     */
//    @Override
//    public Users login(Users users){
//        return this.selectOne(mapperNamespace + ".login",users);
//    }
//
//    /**
//     * 登录更新ip等信息
//     * @param users
//     * @return
//     */
//    @Override
//    public Boolean loginUpdate(Users users){
//        try {
//            this.update(mapperNamespace+".loginUpdate",users,"");
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * 用户注册
//     * @param users
//     * @return
//     */
//    @Override
//    public boolean signin(Users users){
//        try {
//            this.insert(mapperNamespace+".signin",users,"");
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean checkEmail(String email){
//        try {
//            return this.selectOne(mapperNamespace+".checkEmail", email)!=null;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public Users lookUser(int id) {
//        try {
//            return this.selectOne(mapperNamespace+".lookUser",id);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public boolean editUser(Users users) {
//        try {
//            this.update(mapperNamespace+".editUser",users,"");
//            return  true;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean deleteUser(int id) {
//        return  this.delete(mapperNamespace+".deleteUser",id)>0;
//    }
//
//    @Override
//    public List<Users> getUsersList() {
//        try {
//            List<Users> list = this.selectList(mapperNamespace+".getUsersList");
//            return  list;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }
}
