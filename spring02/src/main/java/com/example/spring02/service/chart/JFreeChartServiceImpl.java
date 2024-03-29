package com.example.spring02.service.chart;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.inject.Inject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dao.CartDAO;
import com.example.spring02.model.shop.dto.CartDTO;

@Service
public class JFreeChartServiceImpl implements JFreeChartService {

	@Inject
	CartDAO cartDao;
	
	@Override
	public JFreeChart createChart() {
		List<CartDTO> list = cartDao.cartMoney();
		// 파이 차트가 아닌 경우
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(CartDTO dto : list) {
			dataset.setValue(dto.getMoney(), "과일", dto.getProduct_name());
		}
		// 파이 차트의 경우
//		DefaultPieDataset dataset = new DefaultPieDataset();
//		for(CartDTO dto : list) {
//			dataset.setValue(dto.getProduct_name(), dto.getMoney());
//		}
		JFreeChart chart = null;
		String title = "장바구니 통계";
		try {
//		선그래프
//			chart = ChartFactory.createLineChart(title, "상품명", "금액", dataset, PlotOrientation.VERTICAL, true, true, false);
//		막대그래프
			chart = ChartFactory.createBarChart(title,"상품명", "금액", dataset, PlotOrientation.VERTICAL, true, true, false);
//		파이차트
//			chart = ChartFactory.createPieChart(title, dataset, true, true, false);
//			제목
			chart.getTitle().setFont(new Font("돋움", Font.PLAIN, 10));
//			범례
			chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 10));
			
			Font font = new Font("돋움", Font.PLAIN, 12);
			Color color = new Color(0, 0, 0);
			StandardChartTheme chartTheme = (StandardChartTheme)StandardChartTheme.createJFreeTheme();
			chartTheme.setExtraLargeFont(font);
			chartTheme.setLargeFont(font);
			chartTheme.setRegularFont(font);
			chartTheme.setSmallFont(font);
			
			chartTheme.setAxisLabelPaint(color);
			chartTheme.setLegendItemPaint(color);
			chartTheme.setItemLabelPaint(color);
			chartTheme.apply(chart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return chart;
	}

}
