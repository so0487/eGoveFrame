package kr.or.ddit.bbs.web;

import java.awt.color.CMMException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmmn.intercepter.CommIntercepter;
import egovframework.rte.fdl.cmmn.exception.EgovBizException;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.or.ddit.bbs.service.BbsService;
import kr.or.ddit.bbs.service.FaqService;
import kr.or.ddit.sample.service.SampleDefaultVO;

@Controller
@RequestMapping(value = {"/test","/test2"})
public class BbsController {

//	@Autowired
//	@Resource(name = "bbsService")
//	private BbsService bbsSe;

//	@Resource(name = "bbsService")
//	private FaqService faqService;
	
	@Autowired
	@Qualifier(value = "qnaService")
	private FaqService faqService;
	
	@Autowired
	@Qualifier(value = "bbsService")
	private BbsService qnaService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;

	private final static Logger LOGGER = LoggerFactory.getLogger(CommIntercepter.class);
	
//	url /test/test.do
//	@RequestMapping(value = {"/test.do", "/test2.do"}, method = RequestMethod.POST)
//	@PostMapping(value = {"/test.do", "/test2.do"})
	@GetMapping(value = {"/test.do", "/test2.do"})
//	@PutMapping
//	@DeleteMapping
	public String selectList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model, @RequestParam Map params) throws EgovBizException, Exception{
 
		LOGGER.debug("params : {}", params);
		
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List result = qnaService.getList(searchVO);
		
		model.addAttribute("resultList",result);
		
		//페이징 처리 부분
		paginationInfo.setTotalRecordCount(100);
		model.addAttribute("paginationInfo",paginationInfo);
		
		
		
		
//		System.out.println("=========bbs============");
//		System.out.println(bbsSe.getTitle());
		
//		System.out.println("=========faq============");
//		System.out.println(faqService.getName());
//		System.out.println(faqService.getTitle());
		
//		System.out.println("=========qna============");
//		System.out.println(qnaService.getName());
//		System.out.println(qnaService.getTitle());
		
		return "test";
	}
	
}
