package com.mimi.zfw.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mimi.zfw.model.UserModel;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.pageUtil.CommonPageObject;
import com.mimi.zfw.web.bind.annotation.CurrentUser;


@Controller("indexController")
public class IndexController {

//	@Autowired
//	@Qualifier("BirdEyeViewService")
//	private BirdEyeViewService bevService;

	// @Resource(name = "ReportService")
	// private ReportService reportService;
	
	@Autowired
	@Qualifier("IUserService")
	private IUserService userService;


    @RequestMapping("/aa")
    public String index(@CurrentUser UserModel loginUser, Model model) {
        Set<String> permissions = userService.findPermissions(loginUser.getUserName());
//        List<Resource> menus = resourceService.findMenus(permissions);
//        model.addAttribute("menus", menus);
        model.addAttribute("loginedUserName", loginUser.getUserName());
        return "index";
    }

//    @RequiresPermissions("bb")
    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletRequest request,Model model) {
//        model.addAttribute("userList", userService.listAll());
//        return "user/list";
		CommonPageObject<UserModel> page = userService.listAll(0);
		request.setAttribute("page", page);
		request.setAttribute("sn", request.getServerName());
		return "index";
    }

    @RequestMapping(value = "/login"    )
    public String showLoginForm(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "login";
    }
	
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index(HttpServletRequest request) throws ServletRequestBindingException {
//		String sBevName = (String) request.getAttribute("sBevName");
//		String sBevName = ServletRequestUtils.getStringParameter(request, "sBevName");
//		Page<BirdEyeViewModel> bevsPage;
//		if(StringUtils.isBlank(sBevName)){
//			bevsPage = bevService.listAll(1, 10);
//		}else{
//			BirdEyeViewQueryModel bevqm = new BirdEyeViewQueryModel();
//			bevqm.setBevName(sBevName);
//			bevsPage = bevService.query(1, 10, bevqm);
//		}
//		request.setAttribute("bevPage", bevsPage);
		CommonPageObject<UserModel> page = userService.listAll(0);
		request.setAttribute("page", page);
		request.setAttribute("sn", request.getServerName());
		return "index";
	}

	@RequestMapping(value = "/error_all", method = { RequestMethod.GET })
	public String errorAll(HttpServletRequest request) throws ServletRequestBindingException {
		return "error_all";
	}	

	@RequestMapping(value = "/upload", method = { RequestMethod.GET })
	public String upload(HttpServletRequest request) {
//		Page<BirdEyeViewModel> bevsPage = bevService.listAll(1, 10);
//		Page<UserModel> userPage = userService.listAll(1, 10);
//		request.setAttribute("bevPage", bevsPage);
//		request.setAttribute("userPage", userPage);
		return "upload";
	}
	
	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	public String test(HttpServletRequest request,HttpServletResponse response) {
//		Page<BirdEyeViewModel> bevsPage = bevService.listAll(1, 10);
//		Page<UserModel> userPage = userService.listAll(1, 10);
//		request.setAttribute("bevPage", bevsPage);
//		request.setAttribute("userPage", userPage);
//		return "test";
//		try {
//			response.sendRedirect("http://192.168.1.114:8080/webBirdEyeView/test2.jsp?kkk=fsd");
////			request.getRequestDispatcher("http://192.168.1.114:8080/webBirdEyeView/test.jsp?kkk=fsd").forward(request, response);
////		} catch (ServletException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "redirect:http://192.168.1.114:8080/webBirdEyeView/test2.jsp?kkk=fsd";
//		return "http://192.168.1.114:8080/webBirdEyeView/test.jsp?kkk=fsd";
		request.setAttribute("dataUrl", "http://192.168.1.114:8080/webBirdEyeView/test2.jsp?kkk=fsd");
		return "templetcontent/content";
	}

	@RequestMapping(value = "/show/{type}/{id}", method = { RequestMethod.GET })
	public String show(HttpServletRequest request,@PathVariable String type,@PathVariable Integer id) {
		request.setAttribute("bevId", id);
		if("bev".equals(type)){
			return "mobile/index";
		}else{
			if(id==1){
				return "mobile/pano";
			}else if(id==2){
				return "mobile/pano2";
			}else if(id==3){
				return "mobile/pano3";
			}else{
				return "mobile/pano2";
			}
		}
	}
	
//	@RequestMapping(value = "/show/json/bev/{id}", method = RequestMethod.GET)  
//    public @ResponseBody BirdEyeViewModel porfile(@PathVariable int id) { 
//        return bevService.get(id);  
//    }  
	
//	@RequestMapping(value = "/login", method = { RequestMethod.GET,RequestMethod.POST })
//	public String login(HttpServletRequest request) {
//		request.getSession().setAttribute("message", null);
//		request.getSession().setAttribute("loginedUser", null);
//		return "login";
//	}

//	@RequestMapping(value = "/backstage", method = { RequestMethod.GET,RequestMethod.POST })
//	public String backstage(HttpServletRequest request) {
////		request.getSession().setAttribute("message", null);
////		request.getSession().setAttribute("loginedUser", null);
//		return "backstage/index";
//	}

	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("state", null);
		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		String codedFileName = null;
		OutputStream os = null;
		try {
			// 进行转码，使其支持中文文件名
			codedFileName = java.net.URLEncoder.encode("中文", "UTF-8");
			codedFileName = java.net.URLDecoder.decode(codedFileName,
					"ISO-8859-1");
			response.setHeader("content-disposition", "attachment;filename="
					+ codedFileName + ".xls");

			os = response.getOutputStream();
			// List<ImportError> errors = reportService.exportExcel(os);
			// if (errors != null && !errors.isEmpty()) {
			// for (ImportError error : errors) {
			// System.out.println(error.getContent());
			// }
			// }
		} catch (UnsupportedEncodingException e1) {
			System.out.println(111111111);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(2222222);
		} finally {
			try {
				os.flush();
				System.out.println(333333333);
				os.close();
				System.out.println(44444444);
			} catch (IOException e) {
				System.out.println(55555555);
			}
			session.setAttribute("state", "open");
		}
		System.out.println("文件生成...");
	}

	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public String handleFormUpload(@RequestParam("file") MultipartFile file)
			throws IOException {
		if (!file.isEmpty()) {
			// try {
			// List<ImportError> errors =
			// reportService.importExcel(file.getInputStream());
			// for(ImportError error:errors){
			// System.out.println(error.getName()+":"+error.getContent());
			// }
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
		}
		return "redirect:/index";
	}
}
