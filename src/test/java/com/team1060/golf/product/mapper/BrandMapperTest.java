
package com.team1060.golf.product.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.team1060.golf.product.api.request.ModifyBrandRequest;
import com.team1060.golf.product.api.request.RegisterBrandRequest;
import com.team1060.golf.product.api.response.ViewBrand;

import lombok.extern.log4j.Log4j2;

//JUnit5 사용 시 작성, MybatisTest 2.0.1버전 이상에서 생략 가능
//@ExtendWith(SpringExtension.class)
//JUnit4 사용 시 작성
//@RunWith(SpringRunner.class)

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

//@MybatisTest 어노테이션 추가

//실 데이터베이스에 연결 시 필요한 어노테이션
@Log4j2
public class BrandMapperTest {
	
	@Autowired
	private BrandMapper brandMapper;
	
	@Test
	public void assertEqualsTest() throws Exception {
		assertEquals(1, 2);
	}
	
	@Test
	public void brandMapperTest() {
		log.info(brandMapper);
	}
	
	@Test
	public void insertBrandTest() {

	        RegisterBrandRequest request = RegisterBrandRequest.builder().brand_name("테스트 브랜드명").build();
	        System.out.println("Received request: " + request);
	        brandMapper.insertBrand(request);

	}
    @Test
    public void SelectAllBrandsTest() {
        List<ViewBrand> brands = brandMapper.selectAllBrands();
        System.out.println(brands);
        assertNotNull(brands);
        // 예상되는 결과에 기반하여 검증 수행
    }

    @Test
    public void selectBrandTest() {
        Long brandNo = 1L; // 기존의 브랜드 번호로 대체
        ViewBrand brand = brandMapper.selectBrand(brandNo);
        assertNotNull(brand);
        // 예상되는 결과에 기반하여 검증 수행
    }

    @Test
    public void updateBrandTest() {
    	ModifyBrandRequest request = ModifyBrandRequest.builder()
    			.brand_no(2L).brand_name("testn").build();
        // 필요한 경우 요청 속성 설정
    	
        int result = brandMapper.updateBrand(request);
        log.info(request);
        log.info(result);
        assertEquals(1, result); // 1개의 행이 업데이트되어야 한다고 가정
    }

    @Test
    public void deleteBrandTest() {
        Long brandNo = 1L; // 기존의 브랜드 번호로 대체
        int result = brandMapper.deleteBrand(brandNo);
        assertEquals(1, result); // 1개의 행이 삭제되어야 한다고 가정
    }
	
	
}
