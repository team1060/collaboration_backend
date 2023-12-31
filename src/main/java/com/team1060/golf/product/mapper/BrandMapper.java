package com.team1060.golf.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.team1060.golf.product.api.request.ModifyBrandRequest;
import com.team1060.golf.product.api.request.RegisterBrandRequest;
import com.team1060.golf.product.api.response.ViewBrand;

@Mapper
public interface BrandMapper {

	/**
	 * <pre>
     * 브랜드를 등록하는 메서드입니다.
     * </pre>
     * 
     * @since 2023.12.26
     * @author GHL
     * 
     * @param request 브랜드 등록 요청 정보
     * @return 처리된 행의 수
     */
    int insertBrand(RegisterBrandRequest request);

    /**
     * <pre>
     * 모든 브랜드를 조회하는 메서드입니다.
     * </pre>
     * 
     * @since 2023.12.26
     * @author GHL
     * 
     * @return 브랜드 목록
     */
    List<ViewBrand> selectAllBrands();

    /**
     * <pre>
     * 특정 브랜드를 조회하는 메서드입니다.
     * </pre>
     * 
     * @since 2023.12.26
     * @author GHL
     * 
     * @param brandNo 조회할 브랜드 번호
     * @return 조회된 브랜드 정보
     */
    ViewBrand selectBrand(Long brand_no);

    /**
     * <pre>
     * 브랜드 정보를 업데이트하는 메서드입니다.
     * </pre>
     * 
     * @since 2023.12.26
     * @author GHL
     * 
     * @param request 브랜드 정보 업데이트 요청 정보
     * @return 처리된 행의 수
     */
    int updateBrand(ModifyBrandRequest request);

    /**
     * <pre>
     * 특정 브랜드를 삭제하는 메서드입니다.
     * </pre>
     * 
     * @since 2023.12.26
     * @author GHL
     * 
     * @param brandNo 삭제할 브랜드 번호
     * @return 처리된 행의 수
     */
    int deleteBrand(Long brand_no);

}
