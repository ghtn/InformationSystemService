package com.ghtn.util;

import java.util.ArrayList;
import java.util.List;

import com.ghtn.model.Tree;

public class GradeUtil {
	public static int[] getGradeArray(int grade){
		int count = 0;
		int flag[] = new int[12];
		if (grade == ConstantUtil.MAX_GRADE) {// 超级管理员
			for(int i = 0; i < 12; i++){
				flag[i] = 1;
			}
		} else {
			int s = grade / 2;
			while (grade != 0) {
				flag[count++] = grade % 2;
				grade = s;
				s = grade / 2;
			}
		}
		return flag;
	}
}
