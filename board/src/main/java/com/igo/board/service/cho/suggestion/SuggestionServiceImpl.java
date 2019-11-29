package com.igo.board.service.cho.suggestion;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.igo.board.model.cho.dao.suggestion.SuggestionDAO;
import com.igo.board.model.cho.dto.suggestion.Sg_goodbadDTO;
import com.igo.board.model.cho.dto.suggestion.SuggestionDTO;

@Service
@Transactional
public class SuggestionServiceImpl implements SuggestionService {

	@Autowired
	SuggestionDAO suggestionDao;

	@Override
	public List<SuggestionDTO> list(int start, int end, String search_option, String keyword) {
		return suggestionDao.list(start, end, search_option, keyword);
	}

	@Override
	public void write(SuggestionDTO suggestionDTO) {
		suggestionDao.write(suggestionDTO);
	}

	@Override
	public SuggestionDTO updateForm(int sg_idx) {
		return suggestionDao.view(sg_idx);
	}

	@Override
	public void update(SuggestionDTO suggestionDTO) {
		if(suggestionDTO.getSg_file() == null) {
			String fileName = suggestionDao.view(suggestionDTO.getSg_idx()).getSg_file();
			suggestionDTO.setSg_file(fileName);
		}
		suggestionDao.update(suggestionDTO);
	}

	@Override
	public void delete(int sg_idx) {
		suggestionDao.delete(sg_idx);
	}

	@Override
	public SuggestionDTO view(int sg_idx, HttpServletResponse response, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;
		
		if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("cookie" + sg_idx)) {
                    viewCookie = cookies[i];
                }
            }
        }
		if (viewCookie == null) {
			Cookie newCookie = new Cookie("cookie" + sg_idx, "|" + sg_idx + "|");
			response.addCookie(newCookie);
			suggestionDao.increaseView(sg_idx);
		}
		return suggestionDao.view(sg_idx);
	}

	@Override
	public int countArticle(String search_option, String keyword) {
		return suggestionDao.countArticle(search_option, keyword);
	}

	@Override
   public String imgUpload(MultipartHttpServletRequest mRequest) {
      String saveFileName = "";
      
      try {
         // 사진 저장위치
         String uploadPath = mRequest.getSession().getServletContext().getRealPath("/") + "resources/suggestion/images/";
         String savedPath = calcPath(uploadPath);
         Iterator<String> iter = mRequest.getFileNames();
         while(iter.hasNext()) {
            // 파일이름
            String uploadFileName = iter.next();
            
            MultipartFile mFile = mRequest.getFile(uploadFileName);
            String originalFileName = mFile.getOriginalFilename();
            UUID uuid = UUID.randomUUID(); // 사진 이름 중복 방지
            if(originalFileName != "") {
               saveFileName += uuid + "_" + originalFileName;
               
               byte[] data = mFile.getBytes();
               new File(uploadPath + "\\" + savedPath + "\\" + saveFileName);
               FileOutputStream fos = new FileOutputStream(uploadPath + "\\" + savedPath + "\\" + saveFileName);
               fos.write(data);
               fos.close();
            }
         }
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
      return saveFileName;
   }
	
	public String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		// 디렉토리 생성
		makeDir(uploadPath, yearPath, monthPath, datePath);
		return datePath;
	}
	
	public void makeDir(String uploadPath, String... paths) {
		// 디렉토리가 존재하면 통과
	if(new File(paths[paths.length - 1]).exists()) {
		return;
	}
	for(String path : paths) {
		File dirPath = new File(uploadPath + path);
		if(!dirPath.exists()) { // 디렉토리가 존재하지 않으면
			dirPath.mkdir(); // 디렉토리 생성
			}
		}
		
	}

	@Override
	public int increaseGood(int sg_idx, HttpServletRequest request) {
		Sg_goodbadDTO sg_goodbadDTO = new Sg_goodbadDTO();
		sg_goodbadDTO.setSg_gb_refidx(sg_idx);
		String ip = request.getRemoteAddr(); // 사용자 IP 주소 확인
		sg_goodbadDTO.setSg_gb_ip(ip);
		sg_goodbadDTO.setSg_gb_feel("good");
		
		if(suggestionDao.selectGood(sg_goodbadDTO) == 0) {
			if(suggestionDao.selectBad(sg_goodbadDTO) == 0) { // 처음 누름
				suggestionDao.insertGoodBad(sg_goodbadDTO);
				suggestionDao.increaseGood(sg_idx);
				return 1;
			} else { // 싫어요 -> 좋아요
				suggestionDao.deleteGoodBad(sg_goodbadDTO);
				suggestionDao.insertGoodBad(sg_goodbadDTO);
				suggestionDao.increaseGood(sg_idx);
				return 3;
			}
		} else { // 좋아요 취소
			suggestionDao.deleteGoodBad(sg_goodbadDTO);
			return 2;
		}
	}

	@Override
	public int increaseBad(int sg_idx, HttpServletRequest request) {
		Sg_goodbadDTO sg_goodbadDTO = new Sg_goodbadDTO();
		sg_goodbadDTO.setSg_gb_refidx(sg_idx);
		String ip = request.getRemoteAddr(); // 사용자 IP 주소 확인
		sg_goodbadDTO.setSg_gb_ip(ip);
		sg_goodbadDTO.setSg_gb_feel("bad");
		
		if(suggestionDao.selectGood(sg_goodbadDTO) == 0) {
			if(suggestionDao.selectBad(sg_goodbadDTO) == 0) { // 처음 누름
				suggestionDao.insertGoodBad(sg_goodbadDTO);
				suggestionDao.increaseBad(sg_idx);
				return 1;
			} else { // 좋아요 -> 싫어요
				suggestionDao.deleteGoodBad(sg_goodbadDTO);
				suggestionDao.insertGoodBad(sg_goodbadDTO);
				suggestionDao.increaseGood(sg_idx);
				return 3;
			}
		} else { // 싫어요 취소
			suggestionDao.deleteGoodBad(sg_goodbadDTO);
			return 2;
		}
	}
	
}
