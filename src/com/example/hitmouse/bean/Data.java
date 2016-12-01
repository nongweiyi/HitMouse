/**
 * 
 */
package com.example.hitmouse.bean;

import java.util.ArrayList;
import java.util.List;

import com.example.hitmouse.R;

/**@Description: 
 * 
 * @author 农韦依
 * @date 2016年9月25日
 */
public class Data {
	
	public static List<LevelData> getLevelDataList(){
		int image_awordFlagId1=R.drawable.menu_board;
		int image_awordFlagId2=R.drawable.dog4;
		
		List<LevelData> list=new ArrayList<LevelData>();
		int[] image_levelIdArr1 ={
			R.drawable.lock,
			R.drawable.lock,
			R.drawable.lock,
			R.drawable.lock
		};
		
		/*for(int index=0;index<4;index++){
			image_levelIdArr1[index]=R.drawable.unlock;
		}*/
		LevelData levelData1=new LevelData(image_awordFlagId1, image_awordFlagId2, image_levelIdArr1);
		
		for (int i = 0; i < 10; i++) {
			list.add(levelData1);
		}
		
		return list;
		
	}

}
