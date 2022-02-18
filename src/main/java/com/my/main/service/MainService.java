package com.my.main.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.my.main.dao.MainDao;

@Service
public class MainService {

	@Resource
	private MainDao mainDao;

	/**
	 * 테이블 테스트
	 * @method : selectStudentAttend
	 * @author : 금룡
	 * @create : 2021. 12. 23.
	 */
	public List<Map<String, Object>> selectStudentAttend(Map<String, Object> reqMap){
		return mainDao.selectStudentAttend(reqMap);
	}
	
	/**
	 * 게시판 조회
	 * @method : selectBbs
	 * @author : 금룡
	 * @create : 2021. 12. 23.
	 */
	public List<Map<String, Object>> selectBbs(Map<String, Object> reqMap){
		return mainDao.selectBbs(reqMap);
	}
	
	
	/**
	 * 룡게시판 조회
	 * @method : selectGold
	 * @author : 금룡
	 * @create : 2021. 12. 27.
	 */
	public List<Map<String, Object>> selectGold1(Map<String, Object> reqMap){
		return mainDao.selectGold(reqMap);
	}
	
	
	/**
	 * 관리자 게시판 조회
	 * @method : selectAdmin
	 * @author : 금룡
	 * @create : 2022. 2. 16.
	 */
	public List<Map<String, Object>> selectAdmin(Map<String, Object> reqMap){
		return mainDao.selectAdmin(reqMap);
	}
	
	
	/**
	 * 사용자 게시판 조회
	 * @method : selectUsers
	 * @author : 금룡
	 * @create : 2022. 2. 16.
	 */
	public List<Map<String, Object>> selectUsers(Map<String, Object> reqMap){
		return mainDao.selectUsers(reqMap);
	}
	
	
	/**
	 * 룡게시물 갯수 조회
	 * @method : selectGold
	 * @author : 금룡
	 * @create : 2021. 12. 27.
	 */
	public int selectBoard(Map<String, Object> reqMap){
		return mainDao.selectBoard(reqMap);
	}
	
	
	/**
	 * 관리자게시물 갯수 조회
	 * @method : selectAdminBoard
	 * @author : 금룡
	 * @create : 2022. 2. 16.
	 */
	public int selectAdminBoard(Map<String, Object> reqMap){
		return mainDao.selectAdminBoard(reqMap);
	}
	
	
	/**
	 * 룡게시물 갯수 조회
	 * @method : selectUserBoard
	 * @author : 금룡
	 * @create : 2022. 2. 16.
	 */
	public int selectUserBoard(Map<String, Object> reqMap){
		return mainDao.selectUserBoard(reqMap);
	}
	
	
	/**
	 * 룡게시판 신규작성
	 * @method : insertInput
	 * @author : 금룡
	 * @create : 2021. 12. 28.
	 */
	public Map<String, Object> insertInput(Map<String, Object> reqMap){
		return mainDao.insertInput(reqMap);
	}
	
	
	/**
	 * 룡게시판 답글작성
	 * @method : insertComment
	 * @author : 금룡
	 * @create : 2022. 2. 15.
	 */
	public Map<String, Object> bbsComment(Map<String, Object> reqMap){
		return mainDao.bbsComment(reqMap);
	}
	
	
	/**
	 * 룔게시판 상세조회
	 * @method : selectBbsDetil
	 * @author : 금룡
	 * @create : 2022. 1. 7.
	 */
	public List<Map<String, Object>> selectBbsDetil(Map<String, Object> reqMap){
		return mainDao.selectBbsDetil(reqMap);
	}
	
	
	/**
	 * 룔게시판 상세조회
	 * @method : selectBbsDetil1
	 * @author : 금룡
	 * @create : 2022. 1. 7.
	 */
	public Map<String, Object> selectBbsDetil1(Map<String, Object> reqMap){
		return mainDao.selectBbsDetil1(reqMap);
	}
	
	
	/**
	 * 룡게시판 상세정보 수정
	 * @method : detailInput
	 * @author : 금룡
	 * @create : 2021. 1. 12.
	 */
	public Map<String, Object> detailInput(Map<String, Object> reqMap){
		return mainDao.detailInput(reqMap);
	}

	
	/**
	 * 룡게시판 서류 제출처리
	 * @method : submitStastus
	 * @author : 금룡
	 * @create : 2021. 2. 15.
	 */
	public Map<String, Object> submitStastus(Map<String, Object> reqMap){
		return mainDao.submitStastus(reqMap);
	}
	
	
	/**
	 * 룡게시판 상세정보 삭제
	 * @method : deleteBbs
	 * @author : 금룡
	 * @create : 2021. 1. 13.
	 */
	public Map<String, Object> deleteBbs(Map<String, Object> reqMap){
		return mainDao.deleteBbs(reqMap);
	}
	
	
	/**
	 * 룡게시판 일괄작성
	 * @method : multiInput
	 * @author : 금룡
	 * @create : 2022. 1. 24.
	 */
	public Map<String, Object> multiInput(Map<String, Object> reqMap){
		return mainDao.multiInput(reqMap);
	}
	
	
	/**
	 * 룡게시판 페이징 (총 페이지 수 구하기)
	 * @method : getToalPage
	 * @author : 금룡
	 * @create : 2022. 2. 10.
	 */
	public int getToalPage(int pagenum, int total){
		int totalPageCnt = 0;
		totalPageCnt = (int)Math.ceil((float)total/ (float)pagenum);
		System.out.println("허민혁 확인 : "+totalPageCnt);
		
		return totalPageCnt;
	}
	
	
	/**
	 * 룡게시판 페이징 (시작번호)
	 * @method : getStartCnt
	 * @author : 금룡
	 * @create : 2022. 2. 10.
	 */
	public int getStartCnt(int nowpage, int boardcut){
		int staetCnt = 0;
		staetCnt = nowpage * boardcut - (boardcut-1);
//		System.out.println("시작 : "+staetCnt);
		
		return staetCnt;
	}
	
	
	/**
	 * 룡게시판 페이징 (끝번호)
	 * @method : getEndCnt
	 * @author : 금룡
	 * @create : 2022. 2. 10.
	 */
	public int getEndCnt(int nowpage, int boardcut){
		int endCnt = 0;
		endCnt = nowpage * boardcut;
//		System.out.println("끝 : "+endCnt);
		
		return endCnt;
	}
	
	
	/**
	 * 룡게시판 페이징 (보이는 시작번호)
	 * @method : getEndCnt
	 * @author : 금룡
	 * @create : 2022. 2. 10.
	 */
	public int getStartViewPage(int nowpage, int pagecut){
		int startView = 0;
		startView = ((nowpage - 1) / pagecut) * pagecut + 1;
		
		return startView;
	}
	
	
	/**
	 * 룡게시판 페이징 (보이는 끝번호)
	 * @method : getEndCnt
	 * @author : 금룡
	 * @create : 2022. 2. 10.
	 */
	public int getEndViewPage(int startpage, int pagecut){
		int endView =0;
		endView = startpage + pagecut - 1;
		
		return endView;
	}
	
	
	/**
	 * 상세정보 댓글작성
	 * @method : detailComment
	 * @author : 금룡
	 * @create : 2022. 2. 17.
	 */
	public Map<String, Object> insertDetailComment(Map<String, Object> reqMap){
		return mainDao.insertDetailComment(reqMap);
	}
	
	/**
	 * 상세정보 댓글작성
	 * @method : detailComment
	 * @author : 금룡
	 * @create : 2022. 2. 17.
	 */
	public List<Map<String, Object>> selectDetailComment(Map<String, Object> reqMap){
		return mainDao.selectDetailComment(reqMap);
	}
	
	
	
}
