package lgl.lotion.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// vue 빌드 spring 연동 시, 새로고침 및 url 직접입력에 대한 404에러 해결
@Controller
public class CustomerErrorController implements ErrorController {
	private final String ERROR_PATH = "/error";

	@GetMapping(ERROR_PATH)
	public String redirectRoot() {
		return "index.html";
	}

	public String getErrorPath() {
		return "/error";
	}
}
