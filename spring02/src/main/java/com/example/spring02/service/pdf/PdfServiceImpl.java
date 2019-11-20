package com.example.spring02.service.pdf;

import java.io.FileOutputStream;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfServiceImpl implements PdfService {

	@Inject
	CartService cartService;
	
	@Override
	public String createPdf() {
		String result = "";
		try {
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/IGO/Desktop/sample.pdf")); // 위치 파일명 저장
			document.open();
			BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/malgun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); // 폰트 지정
			Font font = new Font(baseFont, 12); // 폰트, 크기
			PdfPTable table = new PdfPTable(4); // 4개셀을 가진 테이블
			Chunk chunk = new Chunk("장바구니", font); // 타이틀
			Paragraph ph = new Paragraph(chunk); // 문단 생성
			ph.setAlignment(Element.ALIGN_CENTER); // 문단 가운데 정렬
			document.add(ph); // 문단 추가
			
			document.add(chunk.NEWLINE); // 줄 바꿈
			document.add(chunk.NEWLINE);
			
			PdfPCell cell1 = new PdfPCell(new Phrase("상품명", font)); // 셀 생성
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER); // 셀 정렬
			PdfPCell cell2 = new PdfPCell(new Phrase("단가", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell3 = new PdfPCell(new Phrase("수량", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell4 = new PdfPCell(new Phrase("금액", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(cell1); // 셀추가
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			
			List<CartDTO> items = cartService.listCart("park");
			for(int i = 0; i < items.size(); i++) {
				CartDTO dto = items.get(i);
				PdfPCell cellProductName = new PdfPCell(new Phrase(dto.getProduct_name(), font));
				PdfPCell cellPrice = new PdfPCell(new Phrase(""+dto.getPrice(), font));
				PdfPCell cellAmount = new PdfPCell(new Phrase(""+dto.getAmount(), font));
				PdfPCell cellMoney = new PdfPCell(new Phrase(""+dto.getMoney(), font));
				table.addCell(cellProductName);
				table.addCell(cellPrice);
				table.addCell(cellAmount);
				table.addCell(cellMoney);
			}
			document.add(table);
			document.close();
			result = "pdf 파일이 생성되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			result = "pdf 파일 생성 실패...";
		}
		return result;
	}

}
