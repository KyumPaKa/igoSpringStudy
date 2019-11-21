package com.example.spring05.model.dto;

public class AnswerDTO {

	private int answer_idx; // 응답 일련번호
	private int survey_idx; // 문제번호
	private int num; // 선택한 번호
	private int sum_num; // 카운트
	private double rate; // 비율
	
	public int getAnswer_idx() {
		return answer_idx;
	}
	public void setAnswer_idx(int answer_idx) {
		this.answer_idx = answer_idx;
	}
	public int getSurvey_idx() {
		return survey_idx;
	}
	public void setSurvey_idx(int survey_idx) {
		this.survey_idx = survey_idx;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSum_num() {
		return sum_num;
	}
	public void setSum_num(int sum_num) {
		this.sum_num = sum_num;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "AnswerDTO [answer_idx=" + answer_idx + ", survey_idx=" + survey_idx + ", num=" + num + ", sum_num="
				+ sum_num + ", rate=" + rate + "]";
	}
	
}
