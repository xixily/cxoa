package com.chaoxing.oa.controller.pub;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chaoxing.oa.entity.page.common.Json;
import com.chaoxing.oa.util.http.HttpProvider;

@Controller
@RequestMapping("/public/websocket")
public class WebChatController {

	@RequestMapping(value="login")
	@ResponseBody
	public Json login(HttpServletRequest request, HttpSession session){
		byte[] ee = new byte[10];
		try {
			HttpProvider.sendPost("", null);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("");
		
		return null;
	}
	
	@RequestMapping(value="foward")
	@ResponseBody
	public void dispatch(HttpServletRequest request, HttpSession session){
		
	}
}
