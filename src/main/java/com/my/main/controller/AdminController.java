package com.my.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.main.service.MainService;
import com.my.security.SecurityUserDetail;

import com.my.main.common.Const; //요렇게 하면 common아래에 Const파일을 사용하겠다 

@Controller
public class AdminController {

	@Autowired
	private MainService mainService;
	
	
	/*=========================EXAMPLE==========================*/
	
	
	/*=========================EXAMPLE==========================*/
	/**
	 * 관리자 게시판 목록 화면!@!!(return을 jsp로 하는거는 전부다 화면!!)
	 * @method : adminBbsPage
	 * @create : 2022-2-16
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/adminBbsPage.do")
	public String adminBbsPage(Model model,@RequestParam Map<String, Object> reqMap, Authentication authentication) {
		
//		SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
//		System.out.println("-------------------- "+user.getNickname()+" 접속4"+" --------------------");
		
		String status = Const.STATUS_CD_OUT;
		reqMap.put("status", status);
		
		int nowPage = 1; //현재 페이지
		if(reqMap.get("nowPage") == null){ // 
			reqMap.put("nowPage", 1);      // 
		}
		else{
			String test = reqMap.get("nowPage").toString();  // 현재 화면 값을 가져와서 문자으로 변환하고
			nowPage = Integer.parseInt(test);                // 다시 정수로 변환하여
			reqMap.put("nowPage", nowPage);                  // put으로 집어넣음
		}
		int InFoNum = mainService.selectAdminBoard(reqMap); // 게시물 갯수 함수 지정
		System.out.println("관리자 게시물 갯수  : " + InFoNum);
		int TotalPage = 0;                             //총 페이지 수
		TotalPage = mainService.getToalPage(Const.BOARD_CNT,InFoNum); //총 표시되는 페이지 수
		
		int startCnt=0;                                 //전체 시작페이지 번호
		int endCnt=0; 									//전체 끝페이지 번호
		
		int startPage=0;                                //보이는 시작페이지 번호
		int endPage=0;                                  //보이는 끝페이지 번호
		
		startCnt = mainService.getStartCnt(nowPage, Const.BOARD_CNT); // 첫번째 페이지
		endCnt = mainService.getEndCnt(nowPage, Const.BOARD_CNT);     // 마지막 페이지
		
		startPage = mainService.getStartViewPage(nowPage, Const.PAGE_CNT);  // 시작 페이지
		endPage = mainService.getEndViewPage(startPage, Const.PAGE_CNT);    // 끝 페이지
		if(endPage > TotalPage){
			endPage = TotalPage;
		}
		
		reqMap.put("paramStartPageCnt",startCnt);  //
		reqMap.put("paramEndPageCnt",endCnt);      //
		
		System.out.println("reqMap 관리자 테스트 : "+reqMap);
		
		List<?> InFo = mainService.selectAdmin(reqMap);
		model.addAttribute("InFo",InFo); //게시물 내용
		
		model.addAttribute("InFoNum",InFoNum);          // 총 게시물 수
		model.addAttribute("BoardCnt",Const.BOARD_CNT); // 한페이지당 게시물 수
		model.addAttribute("TotalPage",TotalPage);      // 총 페이지 수
		model.addAttribute("nowPage",nowPage);          // 현재 페이지
		model.addAttribute("startCnt",startCnt);        // 첫번째 페이지
		model.addAttribute("endCnt",endCnt);            // 마지막 페이지
		model.addAttribute("startPage",startPage);      // 시작 페이지
		model.addAttribute("endPage",endPage);          // 끝 페이지
		model.addAllAttributes(reqMap);
		
		return "system/adminBbsIndex";
	}
	
	
	/**
     * 관리자 게시판 승인완료 처리(기능)
     * @method : submitAdmin
     * @create : 2022-2-16
     * @param model
     * @author : 금룡
     * @throws Exception 
     */
    @RequestMapping("/system/submitAdmin.do")
    @ResponseBody
    public boolean submitAdmin(@RequestParam Map<String, Object> reqMap, Authentication authentication ) {
    	String bbsStr = reqMap.get("bbsIdStr").toString();
    	System.out.println("김금룡 테스트  "+bbsStr);
    	String[] bbsidstr = bbsStr.split(",");
    	System.out.println("김금룡 테스트 00 "+bbsidstr);
    	int test = 0;
    	String status = Const.STATUS_CD_PWD;
    	boolean retrr = false;
//      String[] ajaxMsg = reqMap.getParameterValues("valueArr");
    	int size = bbsidstr.length;
    	for(int i=0; i<size; i++) {
    		reqMap.put("bbsId", bbsidstr[i]);  //넣어주고
    		reqMap.put("status", status);
    		System.out.println("김금룡 테스트 11 "+reqMap.get("bbsId"));
    		mainService.submitStastus(reqMap);  //제출하고
    		test++;
    	}
    	if(test == size){
    		retrr = true;
    	}
    	return retrr;
    }
	
	
	
//	----------------------------------<**** 룡게시판 ****>--------------------------------------------------------

	
}
