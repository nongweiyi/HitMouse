/**
 * 
 */
package com.example.hitmouse.bean;

/**
 * @Description:
 * 
 * @author 农韦依
 * @date 2016年9月25日
 */
public class LevelData {
	private int image_awordFlagId;
	private int image_awardId;
	int[] image_levelIdArr;

	public LevelData(int image_awordFlagId, int image_awardId, int[] image_levelIdArr) {
		super();
		this.image_awordFlagId = image_awordFlagId;
		this.image_awardId = image_awardId;
		this.image_levelIdArr = image_levelIdArr;
	}

	public int getImage_awordFlagId() {
		return image_awordFlagId;
	}

	public void setImage_awordFlagId(int image_awordFlagId) {
		this.image_awordFlagId = image_awordFlagId;
	}

	public int getImage_awardId() {
		return image_awardId;
	}

	public void setImage_awardId(int image_awardId) {
		this.image_awardId = image_awardId;
	}

	public int[] getImage_levelIdArr() {
		return image_levelIdArr;
	}

	public void setImage_levelIdArr(int[] image_levelIdArr) {
		this.image_levelIdArr = image_levelIdArr;
	}

}
