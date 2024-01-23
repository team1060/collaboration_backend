package com.team1060.golf.golf.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.team1060.golf.golf.api.request.RegisterAndModifyGolf;
import com.team1060.golf.golf.api.response.ViewGolf;
import com.team1060.golf.golf.service.GolfService;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 골프장 api 
 * </pre>
 * @author KJY
 * @since 2023.12.20
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GolfApi {

	private final GolfService golfService;
	
	// 테스트용 코드 
	@GetMapping("/")
    public @ResponseBody String Hello(){
        return "backend";
    }
	
	// 골프장 전체조회 
	@GetMapping({"/reservation/golf", "/golf"})
	@CrossOrigin
	public List<ViewGolf> selectAll() {
		return golfService.selectAll();
	}
	
	// 골프장 등록 
	@PostMapping("/admin/golf")
	@CrossOrigin
	public ResponseEntity<String> registerGolf(@RequestBody RegisterAndModifyGolf request){
		try {
			golfService.register(request);
			return ResponseEntity.ok("골프장 등록 성공");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("골프장 등록 실패" + e.getMessage());
		}
	}
	
	// 골프장 1개 조회 
	@GetMapping({"/admin/golf/{golf_no}", "/golf/{golf_no}"})
	@CrossOrigin
	public ViewGolf select(@PathVariable(name = "golf_no") Long golf_no) {
		return golfService.select(golf_no);
	}
	
	// 골프장 1개 수정 
	@PutMapping("/admin/golf/{golf_no}")
	@CrossOrigin
	public ResponseEntity<String> modifyGolf(@RequestBody RegisterAndModifyGolf request){
		try {
			golfService.modifyGolf(request);
			return ResponseEntity.ok("골프장 수정 성공");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("골프장 수정 실패" + e.getMessage());
		}
	}
	
	/*
	 * 골프장 지역별로 조회 
	 * @PathVariable이 16진수로 자동으로 변환해줌 
	 */
	@GetMapping("/golf/info/{region}")
	@CrossOrigin
	public List<ViewGolf> selectRegion(@PathVariable(name = "region") String region){
		return golfService.selectRegion(region);
	}
	
	// 골프장 삭제 
	@DeleteMapping("/admin/golf/info/{golf_no}")
	@CrossOrigin
	public ResponseEntity<String> removeGolf(@PathVariable(name = "golf_no") Long golf_no){
		try {
			golfService.removeGolf(golf_no);
			return ResponseEntity.ok("골프장 삭제 성공");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("골프장 삭제 실패" + e.getMessage());
		}
	}
}












