package com.example.spring03;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// 스프링 부트 애플리케이션 bean(시작 클래스)
@SpringBootApplication
// mapper 자동 스캔
@MapperScan("com.example.spring03.model")
public class Spring03BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring03BootApplication.class, args);
	}
	// <bean>태그에 대응되는 코드 설정
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFatory bean = new SqlSessionFactoryBean();
		// 데이터소스 설정(application.properties 참조)
		bean.setDatSource(dataSource);
		// xml mapper를 사용할 경우 아래 코드 추가
		// import org.springframework.core.io.Resource;
		// Resource[] res = new PathMatchingResourcePatternResolver()
		// .getResources("classpath:mappers/*Mapper.xml");
		// bean.setMapperLocations(res);
		return bean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory factory) {
		return new SqlSessionTemplate(factory);
	}

}
