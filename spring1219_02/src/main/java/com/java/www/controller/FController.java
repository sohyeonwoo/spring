id,pwpackage com.java.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.www.dto.MemberDto;
import com.java.www.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {
	@Autowired
	MService mService;
	@Autowired
	HttpSession session;
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	@RequestMapping("logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	@RequestMapping("doLogin")
	public String doLogin(MemberDto mdto,Model model) {
		String id = mdto.getId();
		String pw = mdto.getPw();
		
		MemberDto memDto = mService.login(id,pw);
		if(memDto !=null) {
			session.setAttribute("session_id",memDto.getId());
			session.setAttribute("session_name",memDto.getName());
		}
		
		return "doLogin";
	}

}
