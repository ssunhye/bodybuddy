// package com.web.curation.controller.account;

// import com.web.curation.service.FollowService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import javax.servlet.http.HttpServletRequest;


// @RequiredArgsConstructor
// @RestController
// public class FollowController {

//     @Autowired
//     FollowService followService;

//     @RequestMapping("/follow")
//     public String follow(HttpServletRequest request) throws Exception {
//         String l = request.getParameter("user_id");
//         String p = request.getParameter("page_id");

//         int login_id = Integer.parseInt(l);
//         int page_id = Integer.parseInt(p);

//         followService.save(login_id, page_id);

//         String redirect_url = "redirect:/account/userinfo/";

//         return redirect_url;
//     }

//     @RequestMapping("/unfollow")
//     public String unfollow(HttpServletRequest request) throws Exception {
//         String l = request.getParameter("user_id");
//         String p = request.getParameter("page_id");

//         int login_id = Integer.parseInt(l);
//         int page_id = Integer.parseInt(p);

//         followService.deleteByFollowingIdAndFollowerId(page_id, login_id);
//         String redirect_url = "redirect:/account/userinfo/";

//         return redirect_url;
//     }
// }
