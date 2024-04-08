package merch.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import merch.model.*;

public class MerchServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException{
		
		res.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//查詢商品編號
		if("findByPrimaryKey".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str = req.getParameter("merchID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入周邊商品邊編號");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureReq = req.getRequestDispatcher("/merch/select_page.jsp");
				failureReq.forward(req, res);
				return;//程式中斷
			}
			
			Integer merchID = null;
			try {
				merchID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("周邊商品編號格式不正確");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureReq = req.getRequestDispatcher("/merch/select_page.jsp");
				failureReq.forward(req, res);
				return;//程式中斷
			}
			
			/***************************2.開始查詢資料*****************************************/
			MerchService merchSvc = new MerchService();
			MerchVO merchVO = merchSvc.findByPrimaryKey(merchID);
			if (merchVO == null) {
				errorMsgs.add("查無此商品");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureReq = req.getRequestDispatcher("/merch/select_page.jsp");
				failureReq.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("merchVO", merchVO);
			String url = "/merch/listOneEmp.jsp";
			RequestDispatcher success = req.getRequestDispatcher(url);
			success.forward(req, res);
			
		}
	}
	
	

}
