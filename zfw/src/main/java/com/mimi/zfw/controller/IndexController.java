package com.mimi.zfw.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.ActionEnter;
import com.baidu.ueditor.um.Uploader;
import com.mimi.zfw.Constants;
import com.mimi.zfw.service.IUserService;
import com.mimi.zfw.util.APIHttpClient;

//import com.mimi.zfw.web.bind.annotation.CurrentUser;

@Controller("indexController")
public class IndexController {

    // @Autowired
    // @Qualifier("BirdEyeViewService")
    // private BirdEyeViewService bevService;

    // @Resource(name = "ReportService")
    // private ReportService reportService;

    @Resource
    private IUserService userService;

    // @RequestMapping("/aa")
    // public String index(@CurrentUser User loginUser, Model model) {
    // // Set<String> permissions =
    // userService.findPermissions(loginUser.getName());
    // // List<Resource> menus = resourceService.findMenus(permissions);
    // // model.addAttribute("menus", menus);
    // model.addAttribute("loginedUserName", loginUser.getName());
    // return "index";
    // }

    // @RequiresPermissions("bb")
    @RequestMapping(method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model) {
	// model.addAttribute("userList", userService.listAll());
	// return "user/list";
	// CommonPageObject<User> page = userService.listAll();
	// request.setAttribute("page", page);
	request.setAttribute("page", userService.listAll());
	request.setAttribute("sn", request.getServerName());
	return "index";
    }

    @RequestMapping(value = "/unauthorized")
    public String unauthorized() {
	return "index";
    }

    @RequestMapping(value = "/unauthenticated")
    public String unauthenticated() {
	return "index";
    }

    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req, Model model) {
	String exceptionClassName = (String) req
		.getAttribute("shiroLoginFailure");
	String error = null;
	if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
	    error = "用户名/密码错误";
	} else if (IncorrectCredentialsException.class.getName().equals(
		exceptionClassName)) {
	    error = "用户名/密码错误";
	} else if (exceptionClassName != null) {
	    error = "其他错误：" + exceptionClassName;
	}
	model.addAttribute("error", error);
	return "login";
    }

    @RequestMapping(value = "/index", method = { RequestMethod.GET })
    public String index(HttpServletRequest request)
	    throws ServletRequestBindingException {
	// String sBevName = (String) request.getAttribute("sBevName");
	// String sBevName = ServletRequestUtils.getStringParameter(request,
	// "sBevName");
	// Page<BirdEyeViewModel> bevsPage;
	// if(StringUtils.isBlank(sBevName)){
	// bevsPage = bevService.listAll(1, 10);
	// }else{
	// BirdEyeViewQueryModel bevqm = new BirdEyeViewQueryModel();
	// bevqm.setBevName(sBevName);
	// bevsPage = bevService.query(1, 10, bevqm);
	// }
	// request.setAttribute("bevPage", bevsPage);
	// CommonPageObject<User> page = userService.listAll();
	// request.setAttribute("page", page);
	request.setAttribute("page", userService.listAll());
	request.setAttribute("sn", request.getServerName());
	return "index";
    }

    @RequestMapping(value = "/error_all", method = { RequestMethod.GET })
    public String errorAll(HttpServletRequest request)
	    throws ServletRequestBindingException {
	return "error_all";
    }

    @RequestMapping(value = "/umeditorHome", method = { RequestMethod.GET })
    public String umeditorHome(HttpServletRequest request)
	    throws ServletRequestBindingException {
	return "ueditor_test";
    }

    @RequestMapping(value = "/umeditor/controller", method = {
	    RequestMethod.GET, RequestMethod.POST })
    public void umeditorController(HttpServletRequest request,
	    HttpServletResponse response) {
	try {
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
	    Uploader up = new Uploader(request);
	    up.setSavePath("upload");
	    String[] fileType = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
	    up.setAllowFiles(fileType);
	    up.setMaxSize(10000); // 单位KB
	    up.upload();

	    String callback = request.getParameter("callback");

	    String result = "{\"name\":\"" + up.getFileName()
		    + "\", \"originalName\": \"" + up.getOriginalName()
		    + "\", \"size\": " + up.getSize() + ", \"state\": \""
		    + up.getState() + "\", \"type\": \"" + up.getType()
		    + "\", \"url\": \"" + up.getUrl() + "\"}";

	    result = result.replaceAll("\\\\", "\\\\");

	    System.out.println(callback + "_" + result);
	    if (callback == null) {
		response.getWriter().print(result);
	    } else {
		response.getWriter().print(
			"<script>" + callback + "(" + result + ")</script>");
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @RequestMapping(value = "/ueditorHome", method = { RequestMethod.GET })
    public String ueditorHome(HttpServletRequest request)
	    throws ServletRequestBindingException {
	return "ueditor_test";
    }

    @RequestMapping(value = "/ueditor/controller", method = {
	    RequestMethod.GET, RequestMethod.POST })
    public void ueditorController(HttpServletRequest request,
	    HttpServletResponse response)
	    throws ServletRequestBindingException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setHeader("Content-Type", "text/html");
	String rootPath = request.getSession().getServletContext()
		.getRealPath("/");
	// System.out.println(request.getSession().)
	response.getWriter().write(new ActionEnter(request, rootPath).exec());
    }

    @RequestMapping(value = "/upload", method = { RequestMethod.GET,
	    RequestMethod.POST })
    public String upload(HttpServletRequest request,
	    @RequestParam("theFile") MultipartFile theFile) {
	// Page<BirdEyeViewModel> bevsPage = bevService.listAll(1, 10);
	// Page<User> userPage = userService.listAll(1, 10);
	// request.setAttribute("bevPage", bevsPage);
	// request.setAttribute("userPage", userPage);
	try {
	    // saveFileToServer(theFile,request.getSession().getServletContext().getRealPath("/WEB-INF/upload")+"/");
	    saveFileToServer(theFile,
		    "C:\\Users\\Administrator\\Desktop\\test\\img_test\\");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return "index";
    }

    public String saveFileToServer(MultipartFile multifile, String path)
	    throws IOException {
	// 创建目录
	File dir = new File(path);
	if (!dir.exists()) {
	    dir.mkdir();
	}
	// 读取文件流并保持在指定路径
	InputStream inputStream = multifile.getInputStream();
	OutputStream outputStream = new FileOutputStream(path
		+ multifile.getOriginalFilename());
	byte[] buffer = multifile.getBytes();
	int bytesum = 0;
	int byteread = 0;
	while ((byteread = inputStream.read(buffer)) != -1) {
	    bytesum += byteread;
	    outputStream.write(buffer, 0, byteread);
	    outputStream.flush();
	}
	outputStream.close();
	inputStream.close();

	return path + multifile.getOriginalFilename();
    }


    @RequestMapping(value = "/bmapEditor", method = { RequestMethod.GET })
    public String bmapEditor(HttpServletRequest request, HttpServletResponse response) {
	return "bmapEditor";
    }

    @RequestMapping(value = "/bmap", method = { RequestMethod.GET })
    public String bmap(HttpServletRequest request, HttpServletResponse response) {
	return "bmapTest";
    }

    @RequestMapping(value = "/industry/realEstate/json/bmap/list", method = RequestMethod.GET)
    public @ResponseBody
    Object jsonList(String params) {
	String ak = Constants.BAIDU_MAP_AK;
	int geoTableId = Constants.BAIDU_MAP_REAL_ESTATE_GEOTABLE_ID;
	String searchUrl = Constants.BAIDU_MAP_BOUND_SEARCH_URL;
	String url = searchUrl + "?ak=" + ak + "&geotable_id=" + geoTableId;
	if (params!=null && !"".equals(params.trim())) {
	    url += params;
	}
	JSONArray joarr = new JSONArray();
//	JSONArray.fromObject(params);
	for(int i=0;i<1;i++){
		JSONObject tjo = new JSONObject();
		tjo.put("address", "\u5e7f\u4e1c\u7701\u8087\u5e86\u5e02\u7aef\u5dde\u533a\u666f\u5fb7\u8def18\u53f7");
		tjo.put("city", "\u8087\u5e86\u5e02");
		tjo.put("content_id", 1);
		tjo.put("title", "\u661f\u6e56\u540d\u4ed5\u4f1a");
		tjo.put("location", "[112.495924,23.068761]");
		joarr.add(tjo);
	}
	JSONObject jo = new JSONObject();
	jo.put("status", 0);
	jo.put("size", 1);
	jo.put("total", 1);
	jo.put("contents", joarr);
	System.out.println(jo.toString());
	return jo.toString();
//	return '{"status":0,"total":1,"size":1,"contents":[{"address":"\u5e7f\u4e1c\u7701\u8087\u5e86\u5e02\u7aef\u5dde\u533a\u666f\u5fb7\u8def18\u53f7","city":"\u8087\u5e86\u5e02","content_id":1,"create_time":1421809324,"district":"\u7aef\u5dde\u533a","geotable_id":92345,"location":[112.495924,23.068761],"modify_time":1421891993,"on_sale":0,"price":9500,"province":"\u5e7f\u4e1c\u7701","tags":"\u697c\u76d8","title":"\u661f\u6e56\u540d\u4ed5\u4f1a","uid":627101714,"coord_type":3,"type":0,"distance":0,"weight":0}]}';
//	return APIHttpClient.httpClientPost(url);
    }
    

    @RequestMapping(value = "/test", method = { RequestMethod.GET })
    public String test(HttpServletRequest request, HttpServletResponse response) {
	// Page<BirdEyeViewModel> bevsPage = bevService.listAll(1, 10);
	// Page<User> userPage = userService.listAll(1, 10);
	// request.setAttribute("bevPage", bevsPage);
	// request.setAttribute("userPage", userPage);
	// return "test";
	// try {
	// response.sendRedirect("http://192.168.1.114:8080/webBirdEyeView/test2.jsp?kkk=fsd");
	// //
	// request.getRequestDispatcher("http://192.168.1.114:8080/webBirdEyeView/test.jsp?kkk=fsd").forward(request,
	// response);
	// // } catch (ServletException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return
	// "redirect:http://192.168.1.114:8080/webBirdEyeView/test2.jsp?kkk=fsd";
	// return "http://192.168.1.114:8080/webBirdEyeView/test.jsp?kkk=fsd";
	request.setAttribute("dataUrl",
		"http://192.168.1.114:8080/webBirdEyeView/test2.jsp?kkk=fsd");
	return "templetcontent/content";
    }

    @RequestMapping(value = "/show/{type}/{id}", method = { RequestMethod.GET })
    public String show(HttpServletRequest request, @PathVariable String type,
	    @PathVariable Integer id) {
	request.setAttribute("bevId", id);
	if ("bev".equals(type)) {
	    return "mobile/index";
	} else {
	    if (id == 1) {
		return "mobile/pano";
	    } else if (id == 2) {
		return "mobile/pano2";
	    } else if (id == 3) {
		return "mobile/pano3";
	    } else {
		return "mobile/pano2";
	    }
	}
    }

    // @RequestMapping(value = "/show/json/bev/{id}", method =
    // RequestMethod.GET)
    // public @ResponseBody BirdEyeViewModel porfile(@PathVariable int id) {
    // return bevService.get(id);
    // }

    // @RequestMapping(value = "/login", method = {
    // RequestMethod.GET,RequestMethod.POST })
    // public String login(HttpServletRequest request) {
    // request.getSession().setAttribute("message", null);
    // request.getSession().setAttribute("loginedUser", null);
    // return "login";
    // }

    // @RequestMapping(value = "/backstage", method = {
    // RequestMethod.GET,RequestMethod.POST })
    // public String backstage(HttpServletRequest request) {
    // // request.getSession().setAttribute("message", null);
    // // request.getSession().setAttribute("loginedUser", null);
    // return "backstage/index";
    // }

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