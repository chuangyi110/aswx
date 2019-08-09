package com.lzdn.aswxmall.db.service;

import com.lzdn.aswxmall.db.dao.AswxmallSystemMapper;
import com.lzdn.aswxmall.db.domain.AswxmallSystem;
import com.lzdn.aswxmall.db.domain.AswxmallSystemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AswxmallSystemConfigService {
    @Resource
    private AswxmallSystemMapper systemMapper;

    public Map<String, String> queryAll() {
        AswxmallSystemExample example = new AswxmallSystemExample();
        example.or().andDeletedEqualTo(false);

        List<AswxmallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> systemConfigs = new HashMap<>();
        for (AswxmallSystem item : systemList) {
            systemConfigs.put(item.getKeyName(), item.getKeyValue());
        }

        return systemConfigs;
    }

    public Map<String, String> listMail() {
        AswxmallSystemExample example = new AswxmallSystemExample();
        example.or().andKeyNameLike("Aswxmall_mall_%").andDeletedEqualTo(false);
        List<AswxmallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(AswxmallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listWx() {
        AswxmallSystemExample example = new AswxmallSystemExample();
        example.or().andKeyNameLike("Aswxmall_wx_%").andDeletedEqualTo(false);
        List<AswxmallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(AswxmallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listOrder() {
        AswxmallSystemExample example = new AswxmallSystemExample();
        example.or().andKeyNameLike("Aswxmall_order_%").andDeletedEqualTo(false);
        List<AswxmallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(AswxmallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public Map<String, String> listExpress() {
        AswxmallSystemExample example = new AswxmallSystemExample();
        example.or().andKeyNameLike("Aswxmall_express_%").andDeletedEqualTo(false);
        List<AswxmallSystem> systemList = systemMapper.selectByExample(example);
        Map<String, String> data = new HashMap<>();
        for(AswxmallSystem system : systemList){
            data.put(system.getKeyName(), system.getKeyValue());
        }
        return data;
    }

    public void updateConfig(Map<String, String> data) {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            AswxmallSystemExample example = new AswxmallSystemExample();
            example.or().andKeyNameEqualTo(entry.getKey()).andDeletedEqualTo(false);

            AswxmallSystem system = new AswxmallSystem();
            system.setKeyName(entry.getKey());
            system.setKeyValue(entry.getValue());
            system.setUpdateTime(LocalDateTime.now());
            systemMapper.updateByExampleSelective(system, example);
        }

    }

    public void addConfig(String key, String value) {
        AswxmallSystem system = new AswxmallSystem();
        system.setKeyName(key);
        system.setKeyValue(value);
        system.setAddTime(LocalDateTime.now());
        system.setUpdateTime(LocalDateTime.now());
        systemMapper.insertSelective(system);
    }
}
