package springbook2.ch1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 *  컨테이너 인프라 빈 자바코드로 등록하는 방법
 */
@Configuration
@ComponentScan("springbook2.ch1") // <context:component-scan>
// @ComponentScan(basePackageClasses=ServiceMaker.class) // 마커 인터페이스를 이용한 빈 스캔 패키지 지정. 이방법을 더 선호 p202참조
// @ComponentScan(basePackageClasses="myprojext", excludeFilters=@Filter(Configuration.class))  // @Configuration 클래스만 제외
// @ComponentScan(basePackageClasses="myprojext", excludeFilters=@Filter(type=FilterType.ASSIGNABLE_TYPE, value=AppConfig.class))  // 특정 클래스 제외
// @ImportResource("/../...xml") // 외부 xml파일 설정 가져오기
// @EnableTransactionManagement // <tx:annotation-driven />
public class AppConfig {

}
