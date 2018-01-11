package com.llayjun.bookstore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.omg.CORBA.UserException;

import com.llayjun.bookstore.domain.Users;
import com.llayjun.bookstore.service.UserService;
import com.mchange.v2.codegen.bean.BeangenUtils;

public class RegisterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 处理验证码
		String _userCode = req.getParameter("ckcode");
		String _realCode = (String) req.getSession().getAttribute("checkcode_session");
		// 如果两个验证码不一致，则调回注册界面
		if (!_realCode.equals(_userCode)) {
			req.setAttribute("ckcode_msg", "验证码错误");
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
			return;
		}

		// 获取表单数据
		Users _users = new Users();
		_users.setActiveCode(UUID.randomUUID().toString());// 手动设置激活码
		try {
			BeanUtils.populate(_users, req.getParameterMap());

			// 调用业务逻辑
			UserService _uUserService = new UserService();
			_uUserService.regist(_users);

			// 分发转向
			req.getSession().setAttribute("user", _users);// 把用户信息封装到session中
			req.getRequestDispatcher("/registersuccess.jsp").forward(req, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			req.setAttribute("user_msg", e.getMessage());
			req.getRequestDispatcher("/register.jsp").forward(req, resp);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
