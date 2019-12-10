package com.igo.board.controller.cho;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.igo.board.model.cho.dto.AttachDTO;
import com.igo.board.model.cho.dto.BoardDTO;
import com.igo.board.service.cho.BoardService;
import com.igo.board.service.cho.Pager;
import com.igo.board.service.han.AdminService;

@Controller
@RequestMapping(value = {"/fb", "/qa"})public class FreeBoardController {

	@Resource(name="freeBoardServiceImpl")
	BoardService boardService;
	
	@Inject
	AdminService adminService;
	
	@RequestMapping("/list.do")
	public String list(Model model, @RequestParam int refIdx, @RequestParam(defaultValue="1") int curPage, 
			@RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword) {
		// 레코드 갯수 계산
		int count = boardService.countArticle(search_option, keyword, refIdx);
		// 페이지 나누기 관련 처리
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageBegin();
		int end = pager.getPageEnd();
		List<BoardDTO> list = boardService.list(start, end, search_option, keyword, refIdx);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		model.addAttribute("search_option", search_option);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pager", pager);
		model.addAttribute("refIdx", refIdx);
		return "freeBoard/list";
	}
	
	@RequestMapping("/view.do")
	public String view(@RequestParam int idx, @RequestParam int refIdx, Model model, HttpServletResponse response, HttpServletRequest request, Principal principal, 
			@RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword, @RequestParam(required = false) String category) {
		model.addAttribute("dto", boardService.view(idx, response, request));
		model.addAttribute("refIdx", refIdx);
		model.addAttribute("feel", boardService.selectFeel(idx, principal));
		model.addAttribute("replyCnt", boardService.replyCount(idx));
		model.addAttribute("search_option", search_option);
		model.addAttribute("keyword", keyword);
		model.addAttribute("category", category);
		return "freeBoard/view";
	}
	
	@ResponseBody
	@RequestMapping("/good.do")
	public int good(@RequestParam int idx, Principal principal) {
		return boardService.good(idx, principal);
	}
	
	@ResponseBody
	@RequestMapping("/bad.do")
	public int bad(@RequestParam int idx, Principal principal) {
		return boardService.bad(idx, principal);
	}
	
	@RequestMapping("/delete.do")
	public String delete(@RequestParam int idx, @RequestParam int refIdx, HttpServletRequest request, 
			@RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword) {
		boardService.delete(idx);
		String path = request.getRequestURI();
		String[] paths = path.split("/", -1);
		if (paths[2].equals("qa")) {
			return "redirect:/qa/list.do?refIdx=" + refIdx + "&search_option=" + search_option + "&keyword=" + keyword;
		} else {
			return "redirect:/fb/list.do?refIdx=" + refIdx + "&search_option=" + search_option + "&keyword=" + keyword;
		}
	}
	
	@RequestMapping("/deleteAdmin.do")
	public String deleteAdmin(@RequestParam int idx, @RequestParam int refIdx, @RequestParam String category, Principal principal) {
		boardService.delete(idx);
		adminService.updateblacklist(principal.getName(), category);
		return "redirect:/admin/sanction.do";
	}
	
	@RequestMapping("/writeForm.do")
	public String writeForm(@RequestParam int refIdx, Model model, HttpServletRequest request, 
			@RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword) {
		model.addAttribute("refIdx", refIdx);
		model.addAttribute("secretCheck", boardService.secretCheck(refIdx));		
		model.addAttribute("search_option", search_option);
		model.addAttribute("keyword", keyword);
		String path = request.getRequestURI();
		String[] paths = path.split("/", -1);
		model.addAttribute("fq", paths[2]);		
		return "freeBoard/writeForm";
	}
	
	@RequestMapping("/write.do")
	@Transactional
	public String write(@ModelAttribute BoardDTO boardDTO, MultipartHttpServletRequest mRequest, Principal principal, HttpServletRequest request,
			@RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword) {
		boardService.imgUpload(mRequest, 0);
		boardDTO.setRefWriter(principal.getName());
		boardService.write(boardDTO);
		adminService.checkRole(principal.getName(), "board");
		String path = request.getRequestURI();
		String[] paths = path.split("/", -1);
		if (paths[2].equals("qa")) {
			return "redirect:/qa/list.do?refIdx=" + boardDTO.getRefIdx() + "&search_option=" + search_option + "&keyword=" + keyword;
		} else {
			return "redirect:/fb/list.do?refIdx=" + boardDTO.getRefIdx() + "&search_option=" + search_option + "&keyword=" + keyword;
		}
	}
	
	@RequestMapping("/updateForm.do")
	public String updateForm(@RequestParam int idx, @RequestParam int refIdx, Model model, HttpServletRequest request, 
			@RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword) {
		model.addAttribute("dto", boardService.updateForm(idx));
		model.addAttribute("refIdx", refIdx);
		model.addAttribute("secretCheck", boardService.secretCheck(refIdx));		
		model.addAttribute("search_option", search_option);
		model.addAttribute("keyword", keyword);
		String path = request.getRequestURI();
		String[] paths = path.split("/", -1);
		model.addAttribute("fq", paths[2]);
		return "freeBoard/updateForm";
	}
	
	@RequestMapping("/update.do")
	@Transactional
	public String update(@ModelAttribute BoardDTO boardDTO, MultipartHttpServletRequest mRequest, 
			@RequestParam(defaultValue="all") String search_option, @RequestParam(defaultValue="") String keyword) {
		boardService.imgUpload(mRequest, boardDTO.getIdx());
		boardService.update(boardDTO);
		String path = mRequest.getRequestURI();
		String[] paths = path.split("/", -1);
		if (paths[2].equals("qa")) {
			return "redirect:/fb/list.do?refIdx=" + boardDTO.getRefIdx() + "&search_option=" + search_option + "&keyword=" + keyword;
		} else {
			return "redirect:/fb/list.do?refIdx=" + boardDTO.getRefIdx() + "&search_option=" + search_option + "&keyword=" + keyword;
		}
	}
	
	@RequestMapping("/fileDelete.do")
	@ResponseBody
	public void fileDelete(@RequestParam int idx) {
		boardService.fileDelete(idx);
	}
	
	@RequestMapping("download.do")
	public void down(@ModelAttribute AttachDTO attachDTO, HttpServletResponse response, HttpServletRequest request) throws Exception {
		// MIME Type 을 application/octet-stream 타입으로 변경
		// 무조건 팝업(다운로드창)이 뜨게 된다.
		response.setContentType("application/octet-stream");
		// 브라우저는 ISO-8859-1을 인식하기 때문에
		// UTF-8 -> ISO-8859-1로 디코딩, 인코딩 한다.
		String fileName = new String(attachDTO.getFileName().getBytes("UTF-8"), "iso-8859-1");
		// 파일명 지정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName.substring(37) + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream os = response.getOutputStream();
		String originpath = request.getSession().getServletContext().getRealPath("/") + "resources/freeBoard/images/";
		FileInputStream fis = new FileInputStream(originpath + File.separator + fileName);
		int n = 0;
		byte[] b = new byte[512];
		while ((n = fis.read(b)) != -1) {
			os.write(b, 0, n);
		}
        fis.close();
        os.flush();
        os.close();
    }
	
	@RequestMapping("/imageUpload.do")
	@ResponseBody
	public void upload(@RequestParam MultipartFile upload, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// http header 설정
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// http body
		// 업로드한 파일 이름
		String fileName = upload.getOriginalFilename();
		UUID uuid = UUID.randomUUID(); // 사진 이름 중복 방지
		fileName = uuid + "_" + fileName;
		// 바이트 배열로 변환
		byte[] bytes = upload.getBytes();
		// 이미지를 업로드할 디렉토리(배포 경로로 설정)
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + "resources/freeBoard/images/";
		OutputStream out = new FileOutputStream(new File(uploadPath + fileName));
		// 서버에 저장됨
		out.write(bytes);
		String callback = request.getParameter("CKEditorFuncNum");
		PrintWriter printwriter = response.getWriter();
		String fileUrl = request.getContextPath() + "/resources/freeBoard/images/" + fileName;
		printwriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + callback + ",'"
                + fileUrl
                + "','이미지를 업로드 하였습니다.'"
                + ")</script>");
		printwriter.flush();
		if (out != null) {
            out.close();
        }
        if (printwriter != null) {
            printwriter.close();
        }
	}
	
}
