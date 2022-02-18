package com.my.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface MainDao {
	
	
	/**
	 * 테이블 테스트
	 * @method : selectStudentAttend
	 * @author : 금룡
	 * @create : 2021. 12. 23.
	 */
	public List<Map<String, Object>> selectStudentAttend(Map<String, Object> reqMap);
	
	
	/**
	 * 게시판 조회
	 * @method : selectBbs
	 * @author : 금룡
	 * @create : 2021. 12. 23.
	 */
	public List<Map<String, Object>> selectBbs(Map<String, Object> reqMap);
	
	
	/**
	 * 룡게시판 조회
	 * @method : selectGold
	 * @author : 금룡
	 * @create : 2021. 12. 27.
	 */
	public List<Map<String, Object>> selectGold(Map<String, Object> reqMap);
	
	
	/**
	 * 관리자 게시판 조회
	 * @method : selectAdmin
	 * @author : 금룡
	 * @create : 2022. 2. 16.
	 */
	public List<Map<String, Object>> selectAdmin(Map<String, Object> reqMap);
	
	
	/**
	 * 사용자게시판 조회
	 * @method : selectUser
	 * @author : 금룡
	 * @create : 2021. 12. 27.
	 */
	public List<Map<String, Object>> selectUsers(Map<String, Object> reqMap);
	
	
	/**
	 * 룡게시판 갯수 조회
	 * @method : selectBoard
	 * @author : 금룡
	 * @create : 2022. 2. 16.
	 */
	public int selectBoard(Map<String, Object> reqMap);
	
	
	/**
	 * 관리자게시물 갯수 조회
	 * @method : selectAdminBoard
	 * @author : 금룡
	 * @create : 2022. 2. 16.
	 */
	public int selectAdminBoard(Map<String, Object> reqMap);
	
	
	/**
	 * 사용자게시물 갯수 조회
	 * @method : selectUserBoard
	 * @author : 금룡
	 * @create : 2022. 2. 16.
	 */
	public int selectUserBoard(Map<String, Object> reqMap);
	
	
	/**
	 * 룡게시판 신규작성
	 * @method : insertInput
	 * @author : 금룡
	 * @create : 2021. 12. 28.
	 */
	public Map<String, Object> insertInput(Map<String, Object> reqMap);
	
	
	/**
	 * 룡게시판 답글작성
	 * @method : insertComment
	 * @author : 금룡
	 * @create : 2022. 2. 15.
	 */
	public Map<String, Object> bbsComment(Map<String, Object> reqMap);
	
	
	/**
	 * 룡게시판 상세조회 페이지
	 * @method : selectBbsDetil
	 * @author : 금룡
	 * @create : 2022. 1. 7.
	 */
	public List<Map<String, Object>> selectBbsDetil(Map<String, Object> reqMap);
	
	
	/**
	 * 스트링 시큐리티 로그인 정보 가져오기
	 * @method : selectUser
	 * @author : 금룡
	 * @create : 2022. 2. 14.
	 */
	public Map<String, Object> selectUser(Map<String, Object> reqMap);
	
	
	/**
	 * 룡게시판 상세정보 팝업
	 * @method : detailPopup
	 * @author : 금룡
	 * @create : 2022. 1. 14.
	 */
	public Map<String, Object> selectBbsDetil1(Map<String, Object> reqMap);
	
	
	/**
	 * 룡게시판 상세정보 수정 
	 * @method : detailInput
	 * @author : 금룡
	 * @create : 2022. 1. 12.
	 */
	public Map<String, Object> detailInput(Map<String, Object> reqMap);
	
	
	/**
	 * 룡게시판 상세정보 삭제
	 * @method : deleteBbs
	 * @author : 금룡
	 * @create : 2022. 1. 13.
	 */
	public Map<String, Object> deleteBbs(Map<String, Object> reqMap);

	
	/**
	 * 룡게시판 서류 제출처리
	 * @method : submitStastus
	 * @author : 금룡
	 * @create : 2022. 2. 15.
	 */
	public Map<String, Object> submitStastus(Map<String, Object> reqMap);
	
	
	/**
	 * 룡게시판 일괄작성 페이지
	 * @method : insertInput
	 * @author : 금룡
	 * @create : 2022. 1. 24.
	 */
	public Map<String, Object> multiInput(Map<String, Object> reqMap);
	
	
	/**
	 * 상세정보 댓글작성
	 * @method : detailComment
	 * @author : 금룡
	 * @create : 2022. 2. 17.
	*/
	public Map<String, Object> insertDetailComment(Map<String, Object> reqMap);

	
	/**
	 * 상세정보 댓글 조회
	 * @method : selectDetailComment
	 * @author : 금룡
	 * @create : 2022. 2. 17.
	 */
	public List<Map<String, Object>> selectDetailComment(Map<String, Object> reqMap);

}
