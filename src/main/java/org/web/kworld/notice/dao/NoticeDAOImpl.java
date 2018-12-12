package org.web.kworld.notice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.web.kworld.notice.vo.NoticeVO;

import com.util.page.SearchCriteria;

@Repository
public class NoticeDAOImpl implements NoticeDAO{
	@Inject
	private SqlSession sqlSession;
	
	// Mapper 네임스페이스
	private static final String NAMESPACE="org.web.kworld.notice.NoticeMapper";
	
	@Override
	public void insertNotice(NoticeVO vo) throws Exception {
		sqlSession.insert(NAMESPACE+".insertNotice",vo);
	}

	@Override
	public List<NoticeVO> selectNoticeList(SearchCriteria cri) throws Exception {
		return sqlSession.selectList(NAMESPACE+".selectNoticeList",cri);
	}

	@Override
	public NoticeVO selectNotice(int n_no) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".selectNotice",n_no);
	}

	@Override
	public void updateCnt(int n_no) throws Exception {
		sqlSession.update(NAMESPACE+".updateCnt",n_no);		
	}

	@Override
	public void updateNotice(NoticeVO vo) throws Exception {
		sqlSession.update(NAMESPACE+".updateNotice",vo);		
	}

	@Override
	public void deleteNotice(int n_no) throws Exception {
		sqlSession.update(NAMESPACE+".deleteNotice", n_no);		
	}

	@Override
	public int countNotice(SearchCriteria cri) throws Exception {
		return sqlSession.selectOne(NAMESPACE+".countNotice",cri);
	}

	@Override
	public void addAttach(String f_name) throws Exception {
		sqlSession.insert(NAMESPACE+".addAttach",f_name);
	}

	@Override
	public List<String> getAttach(int n_no) throws Exception {
		return sqlSession.selectList(NAMESPACE+".getAttach",n_no);
		
	}

	@Override
	public void deleteAttach(int n_no) throws Exception {
		sqlSession.delete(NAMESPACE+".deleteAttach",n_no);
		
	}

	@Override
	public void replaceAttcah(String fullName, Integer bno) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("n_no", bno);
		paramMap.put("fullName", fullName);		
		sqlSession.insert(NAMESPACE+".replaceAttcah",paramMap);
		
	}

	@Override
	public void deleteFile(String f_name) throws Exception {
		sqlSession.delete(NAMESPACE+".deleteFile");
	}

}
