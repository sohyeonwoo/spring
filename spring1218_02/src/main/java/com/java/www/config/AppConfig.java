package com.java.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class AppConfig {

	//mybatis 객체 생성
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		
		//db연결 부분 : //mybatis session 연결 = application.properties에서 정보를 가져와 db의 dataSource를 가져옴.
		SqlSessionFactoryBean sessionF = new SqlSessionFactoryBean();
		sessionF.setDataSource(dataSource);
		//mapper연결부분 : //query문이 담긴 mapper 파일을 모두 가져옴
		Resource[] res = new PathMatchingResourcePatternResolver().getResources(
				"classpath:/mapper/**/*.xml"); // mapper는 폴더이름 // ** : 모든 폴더
		sessionF.setMapperLocations(res);
		
		return sessionF.getObject(); // mybatis db + mapper 정보가 등러가 있는 모든 객체
	}
	//mybatis 객체 1개를 리턴
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionF) {
		return new SqlSessionTemplate(sqlSessionF); // mybatis에 사용할 1개 객체를 가져옴
	}
	
	
}
