package kr.or.ddit.bbs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import kr.or.ddit.bbs.service.BbsService;
import kr.or.ddit.bbs.service.FaqService;
import kr.or.ddit.sample.service.SampleDefaultVO;

@Service("qnaService")
public class QnaServiceImpl implements FaqService, BbsService {

	@Override
	public String getName() throws EgovBizException {
		// TODO Auto-generated method stub
		return "qna";
	}

	@Override
	public String getTitle() throws EgovBizException {
		// TODO Auto-generated method stub
		return "질의응답";
	}

	@Override
	public List getList(SampleDefaultVO searchVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
