package com.ghtn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ghtn.model.User;
import com.ghtn.util.GradeUtil;
import com.ghtn.util.JsonUtil;

/**
 * 权限验证拦截器
 * 
 * @author kangking
 *
 */
public class GradeValidator extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		String account = request.getParameter("account");
		if (account != null) {
			String gson = (String) request.getSession().getAttribute(account);
			if (gson != null) {
				User user = JsonUtil.gsonToUser(gson);
				if (user != null) {
					if (illegal(uri, GradeUtil.getGradeArray(user.getGrade()))) {
						return false;
					}
				}
			}
		}
		return super.preHandle(request, response, handler);
	}

	/**
	 * 检查是否非法
	 * 
	 * @param uri
	 * @param flag
	 * @return
	 */
	private boolean illegal(String uri, int flag[]) {
		// listEmployeeByPage
		if (uri.indexOf("/InformationSystemService/employee/listEmployeeByPage") >= 0){
			if( flag[11] != 1 && flag[10] != 1 && flag[9] != 1 && flag[8] != 1 ){
				return true;
			}
		}
		// add
		if (uri.indexOf("/InformationSystemService/employee/add") >= 0){
			if( flag[11] != 1){
				return true;
			}
		}
		// updateInfo
		if (uri.indexOf("/InformationSystemService/employee/updateInfo") >= 0){
			if( flag[11] != 1){
				return true;
			}
		}
		// updateEdit
		if (uri.indexOf("/InformationSystemService/employee/updateEdit") >= 0){
			if( flag[10] != 1){
				return true;
			}
		}
		// listTransferEmployeeByPage
		if (uri.indexOf("/InformationSystemService/employee/listTransferEmployeeByPage") >= 0){
			if( flag[10] != 1){
				return true;
			}
		}
		// updateRestoral
		if (uri.indexOf("/InformationSystemService/employee/updateRestoral") >= 0){
			if( flag[8] != 1){
				return true;
			}
		}
		// updateDimission
		if (uri.indexOf("/InformationSystemService/employee/updateDimission") >= 0){
			if( flag[9] != 1){
				return true;
			}
		}
		// remove
		if (uri.indexOf("/InformationSystemService/employee/remove") >= 0){
			if( flag[11] != 1){
				return true;
			}
		}
		// uploadFile
		if (uri.indexOf("/InformationSystemService/employee/uploadFile") >= 0){
			if( flag[11] != 1){
				return true;
			}
		}
		// downloadTemplate
		if (uri.indexOf("/InformationSystemService/employee/downloadTemplate") >= 0){
			if( flag[11] != 1){
				return true;
			}
		}
		// exportEmployee
		if (uri.indexOf("/InformationSystemService/employee/exportEmployee") >= 0){
			if( flag[11] != 1){
				return true;
			}
		}
		// add
		if (uri.indexOf("/InformationSystemService/contract/add") >= 0){
			if( flag[7] != 1){
				return true;
			}
		}
		// listContractByPage
		if (uri.indexOf("/InformationSystemService/contract/listContractByPage") >= 0){
			if( flag[6] != 1){
				return true;
			}
		}
		// update
		if (uri.indexOf("/InformationSystemService/contract/update") >= 0){
			if( flag[6] != 1){
				return true;
			}
		}
		// remove
		if (uri.indexOf("/InformationSystemService/contract/remove") >= 0){
			if( flag[5] != 1){
				return true;
			}
		}
		return false;
	}
}
