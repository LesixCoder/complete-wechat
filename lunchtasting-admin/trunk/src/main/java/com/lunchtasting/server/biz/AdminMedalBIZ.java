package com.lunchtasting.server.biz;

import java.util.HashMap;
import java.util.List;

import com.lunchtasting.server.po.lt.Medal;

public interface AdminMedalBIZ {
	Long saveMedal(Medal medal);
	Long updateMedal(Medal medal);
	HashMap queryMedalList(HashMap map);
	Medal queryMedalById(String id);
	int queryMedalCount(HashMap map);
	int deleteMedal(String id);
	int queryMedalByName(String name);
	List queryMedalNotLimit();
}
