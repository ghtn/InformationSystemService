package com.ghtn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ghtn.model.Tree;
import com.ghtn.model.User;
import com.ghtn.util.ConstantUtil;
import com.ghtn.util.JsonUtil;
import com.ghtn.util.StringUtil;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-11-1 Time: 上午10:10
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/tree")
public class TreeController extends BaseController {

	private static Logger logger = Logger.getLogger(TreeController.class);

	@RequestMapping("/list")
	@ResponseBody
	public List<Tree> listField(String account, HttpSession session) {
		if (!StringUtil.isNullStr(account)) {
			String gson = (String) session.getAttribute(account);
			if (gson != null) {
				User user = JsonUtil.gsonToUser(gson);
				if (user != null) {
					List<Tree> treeList = this.getAllTree();
					List<Tree> list = new ArrayList<Tree>();
					int grade = user.getGrade();
					int count = 0;
					int flag[] = new int[12];
					if (grade == ConstantUtil.MAX_GRADE) {// 超级管理员
						for(int i = 0; i < 12; i++){
							flag[i] = 1;
						}
						Tree tree1 = new Tree();
						tree1.setId(700);
						tree1.setText("系统管理");
						tree1.setLeaf(false);
						tree1.setExpanded(true);
						List<Tree> children1 = new ArrayList<Tree>();
						
						Tree t0 = new Tree();
						t0.setId(701);
						t0.setText("用户管理");
						t0.setLeaf(true);
						t0.setUrl("userBaseContainer");
						children1.add(t0);
						
						t0 = new Tree();
						t0.setId(702);
						t0.setText("权限管理");
						t0.setLeaf(true);
						t0.setUrl("gradeBaseContainer");
						children1.add(t0);
						
						t0 = new Tree();
						t0.setId(703);
						t0.setText("基础维护");
						t0.setLeaf(false);
						t0.setExpanded(false);
						List<Tree> children2 = new ArrayList<Tree>();
						
						Tree t1 = new Tree();
						t1.setId(71);
						t1.setText("来源维护");
						t1.setLeaf(true);
						t1.setUrl("sourceEditBaseContainer");
						children2.add(t1);
						
						t1 = new Tree();
						t1.setId(72);
						t1.setText("职务管理");
						t1.setLeaf(true);
						t1.setUrl("dutyEditBaseContainer");
						children2.add(t1);
						
						t1 = new Tree();
						t1.setId(73);
						t1.setText("职称管理");
						t1.setLeaf(true);
						t1.setUrl("jobTitleEditBaseContainer");
						children2.add(t1);
						
						t1 = new Tree();
						t1.setId(74);
						t1.setText("工别管理");
						t1.setLeaf(true);
						t1.setUrl("jobDistEditBaseContainer");
						children2.add(t1);
						
						t1 = new Tree();
						t1.setId(75);
						t1.setText("工种管理");
						t1.setLeaf(true);
						t1.setUrl("jobTypeEditBaseContainer");
						children2.add(t1);
						
						t1 = new Tree();
						t1.setId(76);
						t1.setText("生产线管理");
						t1.setLeaf(true);
						t1.setUrl("productionLineEditBaseContainer");
						children2.add(t1);
						
						t0.setChildren(children2);
						children1.add(t0);
						
						tree1.setChildren(children1);
						list.add(tree1);
					} else {
						int s = grade / 2;
						while (grade != 0) {
							flag[count++] = grade % 2;
							grade = s;
							s = grade / 2;
						}
					}
					Tree tree1 = new Tree();
					tree1.setId(500);
					tree1.setText("人事管理");
					tree1.setLeaf(false);
					tree1.setExpanded(true);
					List<Tree> children1 = new ArrayList<Tree>();
					for (int i = 11; i >= 8; i--) {
						if (flag[i] != 0) {
							children1.add(treeList.get(i));
						}
					}
					if (children1.size() > 0) {
						tree1.setChildren(children1);
						list.add(tree1);
					}
					
					Tree tree2 = new Tree();
					tree2.setId(600);
					tree2.setText("证书管理");
					tree2.setLeaf(false);
					tree2.setExpanded(true);
					List<Tree> children2 = new ArrayList<Tree>();
					for (int i = 7; i >= 5; i--) {
						if (flag[i] != 0) {
							children2.add(treeList.get(i));
						}
					}
					if (children2.size() > 0) {
						tree2.setChildren(children2);
						list.add(tree2);
					}
					
					Tree tree3 = new Tree();
					tree3.setId(400);
					tree3.setText("在线考试");
					tree3.setLeaf(false);
					tree3.setExpanded(true);
					List<Tree> children3 = new ArrayList<Tree>();
					for (int i = 4; i >= 0; i--) {
						if (flag[i] != 0) {
							children3.add(treeList.get(i));
						}
					}
					if (children3.size() > 0) {
						tree3.setChildren(children3);
						list.add(tree3);
					}
					return list;
				}
			}
		}
		return null;
	}

	private List<Tree> getAllTree() {

		List<Tree> list = new ArrayList<Tree>();
		Tree t0 = new Tree();
		t0.setId(405);
		t0.setText("成绩管理");
		t0.setLeaf(true);
		t0.setUrl("scoreBaseContainer");
		list.add(t0);

		t0 = new Tree();
		t0.setId(404);
		t0.setText("考试管理");
		t0.setLeaf(true);
		t0.setUrl("examBaseContainer");
		list.add(t0);

		t0 = new Tree();
		t0.setId(403);
		t0.setText("试卷管理");
		t0.setLeaf(true);
		t0.setUrl("paperBaseContainer");
		list.add(t0);

		t0 = new Tree();
		t0.setId(402);
		t0.setText("制作试卷");
		t0.setLeaf(true);
		t0.setUrl("makePaperContainer");
		list.add(t0);

		t0 = new Tree();
		t0.setId(401);
		t0.setText("题库管理");
		t0.setLeaf(true);
		t0.setUrl("subjectBaseContainer");
		list.add(t0);

		t0 = new Tree();
		t0.setId(603);
		t0.setText("删除证书");
		t0.setLeaf(true);
		t0.setUrl("contractRemoveBaseContainer");
		list.add(t0);

		t0 = new Tree();
		t0.setId(602);
		t0.setText("查询证书");
		t0.setLeaf(true);
		t0.setUrl("contractQueryBaseContainer");
		list.add(t0);

		t0 = new Tree();
		t0.setId(601);
		t0.setText("证书录入");
		t0.setLeaf(true);
		t0.setUrl("contractAddBaseContainer");
		list.add(t0);

		t0 = new Tree();
		t0.setId(504);
		t0.setText("人员复职");
		t0.setLeaf(true);
		t0.setUrl("restoralBaseContainer");
		list.add(t0);

		t0 = new Tree();
		t0.setId(503);
		t0.setText("人员离职");
		t0.setLeaf(true);
		t0.setUrl("dimissionBaseContainer");
		list.add(t0);

		t0 = new Tree();
		t0.setId(502);
		t0.setText("人员调动");
		t0.setLeaf(true);
		t0.setUrl("transferBaseContainer");
		list.add(t0);

		t0 = new Tree();
		t0.setId(501);
		t0.setText("人事信息");
		t0.setLeaf(true);
		t0.setUrl("employeeBaseContainer");
		list.add(t0);

		return list;
	}
}
