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
public class MainController {

	@Autowired
	private MainService mainService;
	
	
	/*=========================EXAMPLE==========================*/
	@RequestMapping("/")
	public String index(Authentication authentication) {
		SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
		System.out.println(user);
		return "system/main";
	}

	@RequestMapping("/login/loginPage")
	public String login() {
		return "login/loginPage";
	}
	
	@RequestMapping("/aaaa")
	@ResponseBody
	public Map<String, Object> aaaa(@RequestParam Map<String, Object> reqMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", mainService.selectStudentAttend(reqMap));
		return map;
	}
	
	/*=========================EXAMPLE==========================*/
	
	
	/**
	 * 테스트 페이지(화면)
	 * @method : testPage
	 * @create : 2020-10-14
	 * @param model
	 * @author : MH.Heo
	 * @throws Exception 
	 */	
	@RequestMapping("/system/testPage.do")
	public String testPage(Model model,@RequestParam Map<String, Object> reqMap, Authentication authentication) {
		SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
		
		System.out.println("-------------------- "+user.getNickname()+" 접속1"+" --------------------");
		
		return "system/main";
	}
	
	
	/**
	 * 테스트 페이지2(화면)
	 * @method : testPage2
	 * @create : 2020-10-14
	 * @param model
	 * @author : MH.Heo
	 * @throws Exception 
	 */
	@RequestMapping("/system/testPage2.do")
	public String testPage2(Model model,@RequestParam Map<String, Object> reqMap, Authentication authentication) {
		SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
		
		System.out.println("-------------------- "+user.getNickname()+" 접속2"+" --------------------");
		
		return "system/main2";
	}
	
	
	/**
	 * 골드(화면)
	 * @method : gold
	 * @create : 2021-12-22
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/gold.do")
	public String gold(Model model,@RequestParam Map<String, Object> reqMap, Authentication authentication) {
		SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
		
		System.out.println("-------------------- "+user.getNickname()+" 접속3"+" --------------------");
		List<?> InFo = mainService.selectBbs(reqMap);
		model.addAttribute("InFo",InFo);
		model.addAllAttributes(reqMap);
		
		return "system/gold";
	}
	
//	----------------------------------<**** 룡게시판 ****>--------------------------------------------------------
	/**
	 * 룡게시판 목록 화면!@!!(return을 jsp로 하는거는 전부다 화면!!)
	 * @method : goldBbsPage
	 * @create : 2021-12-27
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/goldBbsPage.do")
	public String goldBbs(Model model,@RequestParam Map<String, Object> reqMap, Authentication authentication) {
		
//		SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
//		System.out.println("-------------------- "+user.getNickname()+" 접속4"+" --------------------");
		
		System.out.println("reqMap 허민혁 테스트 : "+reqMap);
		
		int nowPage = 1; //현재 페이지
		if(reqMap.get("nowPage") == null){ // 
			reqMap.put("nowPage", 1);      // 
		}
		else{
			String test = reqMap.get("nowPage").toString();
			nowPage = Integer.parseInt(test);
			reqMap.put("nowPage", nowPage);
		}
		int InFoNum = mainService.selectBoard(reqMap); // 룡게시물 갯수 함수 지정
		
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
		
		List<?> InFo = mainService.selectGold1(reqMap);
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
		
		return "system/goldBbsIndex";
	}
	
	
	
	
	
	/**
	 * 룡게시판 신규 작성 화면 페이지!!!(return을 jsp로 하는거는 전부다 화면!!)
	 * @method : InputPage
	 * @create : 2021-12-28
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/InputPage.do")
	public String input(Model model,@RequestParam Map<String, Object> reqMap, Authentication authentication) {
		SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
		System.out.println("-------------------- "+user.getNickname()+" 접속5"+" --------------------");
		
		model.addAllAttributes(reqMap);
		
		return "system/goldBbsWrite";
	}
	
	
	/**
	 * 룡게시판 신규 작성 기능!!
	 * @method : InsertInput
	 * @create : 2021-12-28
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/Insert.do")
	@ResponseBody
	public Map<String, Object> Insert(@RequestParam Map<String, Object> reqMap, Authentication authentication ) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println("저장 시작");//이거는 확인용 
		System.out.println("넘기는 값 :"+reqMap);//이거는 확인용 
		
		String status = Const.STATUS_CD_TWH;
		reqMap.put("status", status);
		map.put("result", mainService.insertInput(reqMap));
		
		System.out.println(map);//이거는 확인용 
		System.out.println("저장 끝");//이거는 확인용
		
		return map;
	}
	
	
	/**
	 * 룡게시판 답글 작성 화면 페이지!!!(return을 jsp로 하는거는 전부다 화면!!)
	 * @method : insertComment
	 * @create : 2021-12-28
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/CommentPage.do")
	public String Comment(Model model,@RequestParam Map<String, Object> reqMap, Authentication authentication) {
//		SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
//		System.out.println("-------------------- "+user.getNickname()+" 접속5"+" --------------------");
		
		model.addAllAttributes(reqMap);
		
		return "system/goldBbsComment";
	}

	
	/**
	 * 룡게시판 답글 작성 기능!!
	 * @method : bbsComment
	 * @create : 2022-2-17
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/bbsComment.do")
	@ResponseBody
	public Map<String, Object> bbsComment(@RequestParam Map<String, Object> reqMap, Authentication authentication ) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println("댓글 작성 시작");//이거는 확인용 
		System.out.println("넘기는 값 :"+reqMap);//이거는 확인용 
		
		String status = Const.STATUS_CD_TWH;
		reqMap.put("status", status);
		map.put("result", mainService.bbsComment(reqMap));
		
		System.out.println(map);//이거는 확인용 
		System.out.println("댓글 작성 끝");//이거는 확인용
		
		return map;
	}
	
	
	/**
	 * 룡게시판 상세조회 화면!@!!(return을 jsp로 하는거는 전부다 화면!!)
	 * @method : gold
	 * @create : 2022-1-7
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/goldBbsDetail.do")
	public String Detail(Model model,@RequestParam Map<String, Object> reqMap, Authentication authentication) {
//		SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
//		System.out.println("-------------------- "+user.getNickname()+" 접속6"+" --------------------");
		
		List<?> InFoCom = mainService.selectDetailComment(reqMap);
		model.addAttribute("InFoCom",InFoCom); // T_CM_COMMRNT 전체 SELECT
		System.out.println("selectDetailComment : " + reqMap);
		model.addAllAttributes(reqMap);
		
		return "system/goldDetail";
	}
	
	
	/**
	 * 룡게시판 상세정보 조회(기능)
	 * @method : gold
	 * @create : 2022-1-12
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/selectBbsDetil.do")
	@ResponseBody
	public Map<String, Object> selectBbsDetil(@RequestParam Map<String, Object> reqMap, Authentication authentication ) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println(" /system/selectBbsDetil.do 조회가 시작된다");//이거는 확인용 
		System.out.println("넘기는 값 :"+reqMap);//이거는 확인용 
		
		map.put("result", mainService.selectBbsDetil(reqMap));
//		map.put("result", mainService.selectBbsDetil(reqMap));
		
		System.out.println(map);//이거는 확인용 
		System.out.println("/system/selectBbsDetil.do 조회가 끝났다");//이거는 확인용 
		
		return map;
	}
	
	
	/**
	 * 룡게시판 상세정보 수정(기능)
	 * @method : gold
	 * @create : 2022-1-12
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/detailInput.do")
	@ResponseBody
	public Map<String, Object> DetailInput(@RequestParam Map<String, Object> reqMap, Authentication authentication ) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("수정기능 시작");
		System.out.println("sql에 넘어가는 값 : "+reqMap);
		
		map.put("result", mainService.detailInput(reqMap));
		
		System.out.println("jsp로 넘어가는 값"+map);
		System.out.println("수정기능 종료");
		
		return map;
	}
	
	
	/**
	 * 룡게시판 상세정보 삭제(기능)
	 * @method : gold
	 * @create : 2022-1-12
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/deleteBbs.do")
	@ResponseBody
	public Map<String, Object> deleteBbs(@RequestParam Map<String, Object> reqMap, Authentication authentication ) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", mainService.deleteBbs(reqMap));
		
		return map;
	}
	
	
	/**
	 * 룡게시판 상세정보 일괄삭제(기능)
	 * @method : gold
	 * @create : 2022-1-12
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
    @RequestMapping("/system/AllDeleteBbs.do")
    @ResponseBody
    public boolean deleteBbs1(@RequestParam Map<String, Object> reqMap, Authentication authentication ) {
        //그러면 이제 여기로 와서 컨트롤러랑 jsp가 데이터를 주고 받는 놈은 항상 reqMap이놈이여 ㅇㅋ??/ㅇㅋ/
        //그러면 아까의 form정보가 reqMap에 담겨져서 넘어온거양 그래서 map형태는 get이랑 put함수가 있거든?
        //put은 맨날 했던거 처럼 {"이름":값} 넣어주는 함수 get은 데이터를 가져오는 함수 이해??/얍얍/
    	
        //그래서 우리가 아까 form에 bbsIdStr이라는 이름으로 설정했잔아?/어어/ 그래서 그 이름을 get하면 정보가 가져와지는 거야 
        //근데 toString()을 붙이는 이유는 그 정보가 넘어올때 문자열 형식으로 오는게 아니라서 그냥 문자열 형식으로 바꿔주는거양 이거는 그냥 외워 string
        //에러 난다 그러면 .toString()붙여서 강제로 문자열로 만들어주는거임 ㅇㅋ??/우선은 오케인데 문자열로바뀌는 이유가 있겠지?/ 
        //문자열로 바뀌는 이유가 아니라 우리는 문자열로 정보를 받아야 그거를 짜르던 멀 해서 bbsId정보를 예쁘게 가담을수 있으닌깐 문자열로 바꿔주는거지
        //문자열로 바뀌는 이유는 없고 그냥 toStirng()함수를 쓰면 문자열로 강제로 바껴 예를 들어서 111이라는 숫자가 있는데 이거를 111.toString()해주면
        //111이 아니라 "111"이렇게 문자열로 바뀌는거야 ㅇㅋ???/얍/
        
        //그러고 이제 10000,20000이렇게 넘어왔는데 우리는 10000이 한번 필요하고 20000이 한번 필요해서 따로 필요한거잔어?/아항/
        //그래서 문자열을 짤라서 배열로 만들어주느거야 그러면 배열의[0] = 10000    배열의[1] = 20000들어가고 우리는 그거를 for문의 구분지으면 되닌깐
        //근데 만드는 방법은 우리가 ,을 이용해서 구분 지었잔어? 그래서 split(",")으로 하면 ,기준으로 짤라서 알아서 배열에 순서대로 넣어주는거양 ㅇㅋ??/얍/
        //그래서 이제 담겼으닌깐 배열 사이즈 size만큼 for문을 돌면서 삭제를 반복시켜주면 되겠지?
        //근데 여기서 우리가 삭제 할때 넘겨주는 정보가 reqMap이잔아??/어어
        //그리고 필요한 이름은 bbsId고 그래서 for문돌때 bbsId라는 이름으로 계속 초기화 시켜주고 처음 배열의 [0]번째 값을 넘겨줘서 삭제 한번 돌리면 처음께
        //삭제가 되겠지?/타임플리즈 삭제가 아니라 넣는거 아녀? 넣고 삭제까지 프로세스가 for문에서 돌잔아/아아 삭제가 데이터? 이해 완료
        //쨌든 그렇게 해서 for문을 다시 돌면 배열의 2번째 값을 또 bbsId라는 이름으로 넣어주고 다시 데이터삭제를 시키면 2번째 데이터가 삭제되겠지?/ㅇㅇ
        //그렇게 해서 배열 만큼 돌려가면서 삭제 시켜주는 거양
        
    	//retrr은 그냥 returm하기 위한 값?인거 같은데
        //test랑 size가 같으면 retrr이 진실이 되면서 
        //return에 그냥 1이 들어가 종료.
    	String bbsStr = reqMap.get("bbsIdStr").toString();
        System.out.println("김금룡 테스트  "+bbsStr);
        String[] bbsidstr = bbsStr.split(",");
        System.out.println("김금룡 테스트 00 "+bbsidstr);
        int test = 0;
        boolean retrr = false;
//        String[] ajaxMsg = reqMap.getParameterValues("valueArr");
        int size = bbsidstr.length;
        for(int i=0; i<size; i++) {
        	reqMap.put("bbsId", bbsidstr[i]);  //넣어주고
        	System.out.println("김금룡 테스트 11 "+reqMap.get("bbsId"));
        	mainService.deleteBbs(reqMap);  //삭제하고
        	test++;
        }
        if(test == size){
        	retrr = true;
        }
        return retrr;
    }
    
    /**
     * 룡게시판 서류 제출처리(기능)
     * @method : submit
     * @create : 2022-2-15
     * @param model
     * @author : 금룡
     * @throws Exception 
     */
    @RequestMapping("/system/submit.do")
    @ResponseBody
    public boolean submit(@RequestParam Map<String, Object> reqMap, Authentication authentication ) {
    	String bbsStr = reqMap.get("bbsIdStr").toString();
    	System.out.println("김금룡 테스트  "+bbsStr);
    	String[] bbsidstr = bbsStr.split(",");
    	System.out.println("김금룡 테스트 00 "+bbsidstr);
    	int test = 0;
    	String status = Const.STATUS_CD_OUT;
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
    
    
    /**
     * 룡게시판 다중작성 화면 페이지(return을 jsp로 하는거는 전부다 화면!!)
     * @method : multlInputPage
     * @create : 2021-12-28
     * @param model
     * @author : 금룡
     * @throws Exception 
     */
    @RequestMapping("/system/mulInputPage.do")
    public String multlInputPage(Model model,@RequestParam Map<String, Object> reqMap, Authentication authentication) {
    	SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
    	System.out.println("-------------------- "+user.getNickname()+" 다중작성 페이지"+" --------------------");
    	
    	model.addAllAttributes(reqMap);
    	
    	return "system/goldBbsMultiWrite";
    }
    
    
    /**
     * 룡게시판 다중작성 기능
     * @method : multiInput
     * @create : 2021-12-28
     * @param model
     * @author : 금룡
     * @throws Exception 
     */
    @RequestMapping("/system/multiInput.do")
    @ResponseBody
    public boolean multiInput(@RequestParam Map<String, Object> reqMap, Authentication authentication ) {
//    	Map<String, Object> map = new HashMap<String, Object>();
//    	map.put("result", mainService.multiInput(reqMap));
    
    	String testtitle = reqMap.get("testTitle").toString();
    	String testwrite = reqMap.get("testWrite").toString();
    	String testcn = reqMap.get("testCn").toString();
        System.out.println("룡게시판 일괄작성 기능  testtitle : "+testtitle);
        System.out.println("룡게시판 일괄작성 기능  testwrite : "+testwrite);
        System.out.println("룡게시판 일괄작성 기능  testcn : "+testcn);
        String[] testtitlestr = testtitle.split(",");
        String[] testwritestr = testwrite.split(",");
        String[] testcnstr = testcn.split(",");
        System.out.println("룡게시판 일괄작성 기능 testtitlestr : "+testtitlestr);
        System.out.println("룡게시판 일괄작성 기능 testwritestr : "+testwritestr);
        System.out.println("룡게시판 일괄작성 기능 testcnstr : "+testcnstr);
        int test = 0;
        boolean re = false;
        int size = testtitlestr.length;
        String status = Const.STATUS_CD_TWH;
        System.out.println("룡게시판 일괄작성 기능 Size : "+size);
        System.out.println("룡게시판 일괄작성 기능 reqMap :  "+reqMap); //너는 컨트롤러 할때 마다 일단 맨날 넘어가는 값 다 찍어봐서 이해완벽히
        //될때까지 계속 찍으면서 연습하삼 ㅇ
        for(int i=0; i<size; i++) {
        	reqMap.put("title", testtitlestr[i]);  //제목 reqMap의 "title"이름으로 입력 
        	reqMap.put("writer", testwritestr[i]);  //글쓴이 reqMap의 "write"이름으로 입력
        	reqMap.put("cn", testcnstr[i]);  	  //내용 reqMap의 "cn"이름으로 입력
        	//reqMap이 SQl로 전달이 되잔아 ㅇㅇ 형태는 map이 {"이름",값} 이고 어어 sql에서 #{이름} 이거라고 말을 내가 몇번했으!! 기달
        	//sql은 정해진 이름 정보를 reqMap에서 찾는다고 근데 지금은 reqMap에 "bbsTitle"로 정보를 넣고 sql에서는 #{title}로 찾으니 못 찾음
        	//찾아야 하는 부분을 ?,?,?로 표시가 되어있음 물음표(?)로 표시되었다는 건 받을때나 보낼때 이름이 일치하지 않는다는 사실
        	
        	//일괄삭제했을 때는 아이디로 찾아서 삭제하면 되는데
        	//일괄작성할 때는 아이디는 임의로 만들어지고, 
        	//NVL((SELECT MAX(BBS_ID)+1 FROM T_CM_BBS),10000)<!-- NVL (해당 값이 없으면, 여기값 출력) -->\
        	//제목, 글쓴이, 내용은 일괄삭제할 때처럼 각자 합쳐서 컨트롤러로 넘김, 컨트롤러에서는 각자 ','를 없애고 하나하나 일일이
        	//해체하여 title, write, cn으로 넣어준다. 
        	
        	//reqMap은 sql로 넘길때 필요한 주머니 개념. 
        	//주머니 안에 put으로 {"이름", 값}형태로 넘겨준다. 예)reqMap.put("title", testtitlestr[i]);
        	//get은 데이터를 불러올때 쓰는 함수이다. 예)String testtitle = reqMap.get("testTitle").toString();
        	reqMap.put("status", status);
        	System.out.println("SQL 로 넘기는 값 :  "+i+"번째  : reqMap : "+reqMap); //이거는 확인용 
        	mainService.multiInput(reqMap);  //sql의 multiInput함수로 reqMep 넘김
        	test++;
        }
        if(test == size){
        	re = true;
        }
        return re;
    }
	
    
	/**
	 * 룡게시판 상세조회 팝업 화면!!!(return을 jsp로 하는거는 전부다 화면!!)
	 * @method : gold
	 * @create : 2022-1-14
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/detailPopup.do")
	public String detailPopup(Model model,@RequestParam Map<String, Object> reqMap, Authentication authentication) {
		
		System.out.println("팝업호출");
		System.out.println("sql에 넘어가는 값 : "+ reqMap);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", mainService.selectBbsDetil1(reqMap));
		model.addAttribute("InFo",map); //요 모델이 실질적으로 jsp로 넘겨주는 설정이당 내가 잠시 햇갈림 
		model.addAttribute("TEST","111김금룡"); // 요렇게 하면 TEST란 이름에 111김금룡 값이 저장된 상태로 넘어가는거임 우리는 지금 
		// 데이터를 1개가 아니라 정보를 많이 불러와서 map에 담고 그거를 다시 담은거 뿐이지 형태는 이름 : 값 임..  그래서 이거를 jsp에서 똑같이 ${TEST}하면 저게 보이겠지?
		// 근데 지금은 데이터를 return하는게 아니고 jsp를 불러서 화면을 뿌려주는거잔어? 그래서 그때는 retunr 값이 없으닌깐 jsp랑 통신 하는 model이라는 놈한테
		// 넣어줘서 넘겨주느거야 이해가됨? ㄱㄷ 한번 다시읽어봄
		// 조금더 쉽게 말하면 jsp(화면을 리턴하는 컨트롤러는 ) model이랑 항상 쌍이라고 생각하면됨 jsp를 로딩하기 전에 model에 있는
		// 모든 데이터를 가지고 넘어가는거야 jsp에 그래서 화면이 호출되기 전에 model에 addAttribute요 함수로 지정해서 이름을 넘겨주면 jsp에서 
		// ${ㄹㅈㄷㄹㅈㄷㄹㅈ} 이렇게 쓸수 있는거야
		// 예시를 보여주면 이해가 좀 되나??? 조금은 됨 그래서 Info안에 있어서 .을 쓴거고? 그지 숩게 보면 {}요게 구분 이고 {}이거 맨 앞에 있는 놈부터 이름 
		// 써주고 =올때는 .으로 구분 짓고  {InFo={result={bbsTitle=   Info.result.bbsTitle을 하면  "제목1" 이게 보이는거지 이해 됨?? 
		// 그러면 map.put도 result안에 정보를 넣어주는 꼴이네? 그지 map이든 model이든 reqmap이든
		// 전부다 저장소라고 생각하면됨 그래서 그거를 그냥 선언해주고 거기에 정보를 담는거지 근데 형식은 전부다 ("저장하는 이름" : 실실적인 저장값)요렇게 담아주는거지
		// map은 put, model은 addAttribute을 써서 하는 거고? 그징
		// model.addAllAttributes(reqMap);이거는 이름이 없는데 어케 알아? 그거는 없어도 되긴함 근데 저거는 흠..왜 쓰나면 우리가 필요한 정보를 jsp에서 sql로 
		// 넘길때 reqMap으로 데이터를 넣어서 보내자아?? 그지? 얍 근데 그 정보가 다시 필요할때가 있어 jsp내 에서 그래서 그 정보를 다시 한번 가져와주는 역할일껄? 잠시만 나도 
		// 햇갈리네 하나만  테스트 해보자  아항 
		// 내가 지금 한 테스트 이해됨? 이러면 model를 안써도 되는 거 아님? map으로도 가능한거 아녀? 
		// 아니지 map은 가능한거고 테스트 포인트는 
		// 결론은 데이터를 넘기는 역할을 하는건느 ,model이 맞아 근데 지금 model에 함수가 2개 있잔아?
		// addAllAttributes = 이거는 사용하는 경우가 아까 TEST처럼 값 1개인 경우 이름을 지정해서 넘길때
		// addAttribute = 이거는 map형태나 list형태로 이미 여러값이 모여있는 형태면 이걸로 그냥 넘기면 아까처럼 할 수 있는거
		// 방금 우리는 그거를 몰라서 다시 Info에 담아서 구지 Info,result.bbsTile이렇게 한거고 model.addAllAttributes(map); 이렇게 
		// 하면 바로 resut.bbsTitle해서 불러올수 있넹 잘 보면 All이란 단어가 있고 우에는 없어 이해? 이해됨 
		
		model.addAllAttributes(reqMap);
		model.addAllAttributes(map);
		
		System.out.println("jsp로 넘어가는 값222 : "+ model); 
		System.out.println("jsp로 넘어가는 값333 : "+ reqMap); 
		System.out.println("jsp로 넘어가는 값444 : "+ map); 
		System.out.println("팝업호출 끝!");
		
		
		return "system/detailPopup";
	}
	
	/**
	 * 상세정보 댓글 작성 기능!!
	 * @method : detailComment
	 * @create : 2022-2-17
	 * @param model
	 * @author : 금룡
	 * @throws Exception 
	 */
	@RequestMapping("/system/insertDetailComment.do")
	@ResponseBody
	public Map<String, Object> insertDetailComment(@RequestParam Map<String, Object> reqMap, Authentication authentication ) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println("댓글 작성 시작");//이거는 확인용 
		System.out.println("넘기는 값 :"+reqMap);//이거는 확인용 
		
		map.put("result", mainService.insertDetailComment(reqMap));
		
		System.out.println(map);//이거는 확인용 
		System.out.println("댓글 작성 끝");//이거는 확인용
		
		return map;
	}
	
	
//	/**
//	 * 상세정보 댓글 정보 조회 기능!!!(return을 jsp로 하는거는 전부다 화면!!)
//	 * @method : selectDetailComment
//	 * @create : 2022-2-17
//	 * @param model
//	 * @author : 금룡
//	 * @throws Exception 
//	 */
//	@RequestMapping("/system/selectDetailComment.do")
//	public String selectDetailComment(Model model,@RequestParam Map<String, Object> reqMap, Authentication authentication) {
//		//	SecurityUserDetail user = (SecurityUserDetail) authentication.getPrincipal();
//		//	System.out.println("-------------------- "+user.getNickname()+" 접속4"+" --------------------");
//		
//		
//		System.out.println("댓글 작성 시작");//이거는 확인용 
//		System.out.println("넘기는 값 :"+reqMap);//이거는 확인용 
//
//		List<?> InFoCom = mainService.selectDetailComment(reqMap);
//		model.addAttribute("InFoCom",InFoCom); // T_CM_COMMRNT 전체 SELECT
//		
//		model.addAllAttributes(reqMap);
//		
//		return "system/goldDetail";
//	}
	
//	/**
//	 * 상세정보 댓글 정보 조회 기능!!!(return을 jsp로 하는거는 전부다 화면!!)
//	 * @method : selectDetailComment
//	 * @create : 2022-2-17
//	 * @param model
//	 * @author : 금룡
//	 * @throws Exception 
//	 */
//	@RequestMapping("/system/selectDetailComment.do")
//	@ResponseBody
//	public Map<String, Object> selectDetailComment(@RequestParam Map<String, Object> reqMap, Authentication authentication ) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		System.out.println("조회가 시작된다");//이거는 확인용 
//		System.out.println("넘기는 값 :"+reqMap);//이거는 확인용 
//		
//		map.put("result", mainService.selectDetailComment(reqMap));
//		
//		System.out.println(map);//이거는 확인용 
//		System.out.println("조회가 끝났다");//이거는 확인용 
//		
//		return map;
//	}

	
//	----------------------------------<**** 룡게시판 끝 ****>--------------------------------------------------------

	
}
