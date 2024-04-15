package merch.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import merch.model.MerchService;
import merch.model.MerchVO;

import merch.model.*;

public class MerchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
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
			String url = "/merch/listOneMerch.jsp";
			RequestDispatcher success = req.getRequestDispatcher(url);
			success.forward(req, res);
			
		}
		
		//新增商品
		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String merchName = req.getParameter("merchName");
				String merchNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (merchName == null || merchName.trim().length() == 0) {
					errorMsgs.add("周邊商品名稱: 請勿空白");
				} else if(!merchName.trim().matches(merchNameReg)) {
					errorMsgs.add("周邊商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				byte[] merchImg = null;
//				Part pic = req.getPart("merchImg");
//				if (pic != null) {
//		            try {
//		                // 讀取圖片檔案並轉換為byte陣列
//		            	InputStream is = pic.getInputStream();
//		                byte[] in = new byte[is.available()];
//		                is.read(in));
//		                is.close();
//		                ;
//		                
//		                
//
//		            	} catch (IOException e) {
//		                
//		            	}

				
				
				String merchInfo = req.getParameter("merchInfo").trim();
				
				
				Integer merchPrice = null;
				try {
					merchPrice = Integer.valueOf(req.getParameter("merchPrice").trim());
				} catch (NumberFormatException e) {
					merchPrice = 0;
					errorMsgs.add("周邊商品價格請填數字.");
				}
				
				
				String merchStatus = String.valueOf(req.getParameter("merchStatus").trim());

				MerchVO merchVO = new MerchVO();
				merchVO.setMerchName(merchName);
				merchVO.setMerchImg(merchImg);
				merchVO.setMerchInfo(merchInfo);
				merchVO.setMerchPrice(merchPrice);
				merchVO.setMerchStatus(merchStatus);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("merchVO", merchVO);
					RequestDispatcher failureReq = req.getRequestDispatcher("/merch/addMerch.jsp");
					failureReq.forward(req, res);
					return;	//程式中斷
				}
				
				/***************************2.開始新增資料***************************************/
				MerchService merchSvc = new MerchService();
				merchVO = merchSvc.addMerch(merchName,merchImg, merchInfo, merchPrice, merchStatus);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/merch/listAllMerch.jsp";
				RequestDispatcher success = req.getRequestDispatcher(url);
				success.forward(req, res);				
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer merchID = Integer.valueOf(req.getParameter("merchID"));
				
				/***************************2.開始查詢資料****************************************/
				MerchService merchSvc = new MerchService();
				MerchVO merchVO = merchSvc.getOneMerch(merchID);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("merchVO", merchVO);
				String url = "/merch/updateMerch.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		//更改商品
		if ("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer merchID = Integer.valueOf(req.getParameter("merchID"));
				String merchName = req.getParameter("merchName");
				String merchNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (merchName == null || merchName.trim().length() == 0) {
					errorMsgs.add("周邊商品名稱: 請勿空白");
				} else if(!merchName.trim().matches(merchNameReg)) {
					errorMsgs.add("周邊商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				byte[] merchImg = null;
				
				String merchInfo = req.getParameter("merchInfo").trim();


				Integer merchPrice = null;
				try {
					merchPrice = Integer.valueOf(req.getParameter("merchPrice").trim());
				} catch (NumberFormatException e) {
					merchPrice = 0;
					errorMsgs.add("周邊商品價格請填數字.");
				}

				
				String merchStatus = String.valueOf(req.getParameter("merchStatus").trim());


				MerchVO merchVO = new MerchVO();
				merchVO.setMerchID(merchID);
				merchVO.setMerchName(merchName);
				merchVO.setMerchImg(merchImg);
				merchVO.setMerchInfo(merchInfo);
				merchVO.setMerchPrice(merchPrice);
				merchVO.setMerchStatus(merchStatus);


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("merchVO", merchVO);
					RequestDispatcher failureReq = req.getRequestDispatcher("/merch/updateMerch.jsp");
					failureReq.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MerchService merchSvc = new MerchService();
				merchVO = merchSvc.updateMerch(merchID,merchName,merchImg, merchInfo, merchPrice, merchStatus);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("merchVO", merchVO);
				String url = "/merch/listOneMerch.jsp";
				RequestDispatcher success = req.getRequestDispatcher(url);
				success.forward(req, res);
		}
	}
	
	

}
