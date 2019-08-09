package com.lzdn.aswxmall.wx.service;

import com.lzdn.aswxmall.db.domain.AswxmallRegion;
import com.lzdn.aswxmall.db.service.AswxmallRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhy
 * @date 2019-01-17 23:07
 **/
@Component
public class GetRegionService {

	@Autowired
	private AswxmallRegionService regionService;

	private static List<AswxmallRegion> aswxmallRegions;

	protected List<AswxmallRegion> getAswxmallRegions() {
		if(aswxmallRegions==null){
			createRegion();
		}
		return aswxmallRegions;
	}

	private synchronized void createRegion(){
		if (aswxmallRegions == null) {
			aswxmallRegions = regionService.getAll();
		}
	}
}
