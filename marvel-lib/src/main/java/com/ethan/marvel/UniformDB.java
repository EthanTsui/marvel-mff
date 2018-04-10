/**
 * 
 */
package com.ethan.marvel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ethan.marvel.mffdata.DataLoader;

/**
 * @author GBTW0011
 * created date: 2017年1月17日
 */
public class UniformDB {
    private volatile static UniformDB instance = null;
    private static final Integer LOCK = new Integer(0);
    
    private Map<String, Uniform> uniforms = new TreeMap<String, Uniform>();
    private Map<String, List<Uniform>> uniformsByType = new TreeMap<String, List<Uniform>>();
    
    
    private UniformDB() {
        load();
    }
    
    
    public static UniformDB getInstance() {
        if(instance==null) {
            synchronized(LOCK) {
                if(instance==null) {
                    instance=new UniformDB();
                }
            }
        }
        return instance;
    }
    
    public List<Uniform> getUniformsByType(String type) {
        return uniformsByType.get(type);
    }
    
    public void load() {
        try {
            uniformsByType.put("0", new ArrayList<Uniform>());
            uniformsByType.put("1", new ArrayList<Uniform>());
            uniformsByType.put("2", new ArrayList<Uniform>());
            uniformsByType.put("3", new ArrayList<Uniform>());
            
            DataLoader herodl = new DataLoader();
            herodl.loadData(new File("/data/marvel/mffdata/HERO_LIST.csv.txt.decode.txt"), "UTF-8", "\t", 0);
            
            DataLoader heromaploader = new DataLoader();
            heromaploader.loadData(new File("/data/marvel/heroidmapping.txt"), "UTF-8", "\t", 0);
            
            
            // load hero costume
            int position = 0;
            String lastCostume="";
            while(herodl.next()) {
                if("0".equals(herodl.getData("VISIBLE"))) {
                    continue;
                }
                
                String thisCostume = herodl.getData("PREFAB");
                if(lastCostume.equals(thisCostume)) {
                    continue;
                }
                lastCostume=thisCostume;
                position++;
                
                if("0".equals(herodl.getData("HERO_COSTUME_GROUP_ID"))) {
                    continue;
                }
                
                Uniform uniform = new Uniform();
                uniform.setImgidx(position);
                uniform.setUniformId(herodl.getData("HERO_COSTUME_GROUP_ID"));
                uniform.setHerotype(herodl.getData("HERO_TYPE"));
                
                uniformsByType.get(herodl.getData("HERO_TYPE")).add(uniform);
                
//                System.out.println(LocalizationHelper.getInstance().getText("zhtw", "ITEM_"+herodl.getData("HERO_COSTUME_GROUP_ID"))+", "+herodl.getData("HERO_COSTUME_GROUP_ID")+", position: "+position);
                
                String heroGid = herodl.getData("HERO_GROUP_ID");
                uniform.setHero(HeroDB.getInstance().lookupHero(Integer.parseInt(heromaploader.getData(heroGid, "HERO_ID"))));
                
                uniforms.put(herodl.getData("HERO_COSTUME_GROUP_ID"), uniform);
                
                
            }
            
            
            // load uniform
            DataLoader uniformdl = new DataLoader();
            uniformdl.loadData(new File("/data/marvel/mffdata/UNIFORM_UPGRADE.csv.txt.decode.txt"), "UTF-8", "\t", 0);
            
            lastCostume="";
            while(uniformdl.next()) {
                String thisCostume = uniformdl.getData("UNIFORM_GROUP_ID");
                if(lastCostume.equals(thisCostume)) {
                    continue;
                }
                
                if("6".equals(uniformdl.getData("UNIFORM_GRADE"))) {
                    continue;
                }
                
                Uniform uni = uniforms.get(thisCostume);
                Uniform runi = uniforms.get(uniformdl.getData("REQUIRE_UNIFORM"));
                
//                System.out.println(thisCostume+", req; "+uniformdl.getData("REQUIRE_UNIFORM")+", "+uniformdl.getData("UNIFORM_GRADE"));
                
                uni.addRequiredUniform(Integer.parseInt(uniformdl.getData("UNIFORM_GRADE")), runi);
                
            }
            
            
            
        }
        catch(Exception err) {
            err.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        UniformDB.getInstance().load();
        
        System.out.println("uniforms: "+UniformDB.getInstance().getUniforms().values().size());
        
        for(Uniform uni: UniformDB.getInstance().getUniforms().values()) {
//            System.out.println(uni.getImgidx()+", "+uni.getUniformId());
            StringBuilder sb = new StringBuilder(100);
            int counter=0;
            for(Uniform uni2: UniformDB.getInstance().getUniforms().values()) {
                if(uni.getImgidx()==uni2.getImgidx()) {
                    continue;
                }
                
                for(int i=1;i<=5;i++) {
                    if(uni2.getRequiredUniforms().get(i).getUniformId().equals(uni.getUniformId())) {
                        //System.out.println(uni2.getUniformId());
                        sb.append(LocalizationHelper.getInstance().getText("zhtw",  "ITEM_"+uni2.getUniformId()));
                        sb.append(", #"+i+"\t");
                        counter++;
                        break;
                    }
                }
                
                
            }
            
            System.out.println(uni.getImgidx()+"\t"+uni.getUniformId()+ "\t"+LocalizationHelper.getInstance().getText("zhtw",  "ITEM_"+uni.getUniformId()) +"\t"+counter+"\t"+sb.toString());
            
            
        }
        
    }


    /**
     * @return the uniforms
     */
    public Map<String, Uniform> getUniforms() {
        return uniforms;
    }
    
    
}
