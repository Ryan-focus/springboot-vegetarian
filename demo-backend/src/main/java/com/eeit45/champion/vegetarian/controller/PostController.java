package com.eeit45.champion.vegetarian.controller;

import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eeit45.champion.vegetarian.dto.ProductRequest;
import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.Product;
import com.eeit45.champion.vegetarian.service.PostService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Validated
@RestController
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/newFoodPost")
	public String processMainAction(Model m) {

		Post post = new Post();
		m.addAttribute("posts", post);
		return "createPost";
	}

	@PostMapping("/PostNew")
	public ResponseEntity<Boolean> createPostImage(@RequestPart @Valid Post post) throws IOException {

		String headUrl = null;
		String headImgFileName = "pimages/PostsPhoto";
		String defaultImgurl = "images/PostsPhoto/defaultPostImage.jpg";

//		if (result.hasErrors()) {
//			return "createPost";
//		}

//		MultipartFile picture = post.getPostImage();
//		if (picture.getSize() != 0) {
//			// byte[] bytes = picture.getBytes();
//			String filename = picture.getOriginalFilename();
//			String suffix = filename.substring(filename.lastIndexOf('.'));// 副檔名
//			System.out.println("副檔名" + suffix);// .jpg
//			// 新的檔名
//			String newFileName = new Date().getTime() + suffix;
//			System.out.println("新檔名" + newFileName);// 1478509873038.jpg
//
//			//String savePath = request.getSession().getServletContext().getRealPath("/") + headImgFileName;
//			 String savePath = "c:/temp";
//			System.out.println(savePath);
//			File headImage = new File(savePath, newFileName);
//
//			// 儲存置資料庫路徑
//			headUrl = headImgFileName + "/" + newFileName;
//			System.out.println(headUrl);
//			picture.transferTo(headImage);
//
//		} else {
//			headUrl = defaultImgurl;
//		}

		ZoneId zoneId = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.now();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);
		Date date = Date.from(zdt.toInstant());

		post.setImgurl(headUrl);
		post.setPostedDate(date);
		post.setPostStatus("待審核");
		
		Boolean addresult = postService.addPostImage(post);
		return ResponseEntity.status(HttpStatus.CREATED).body(addresult);

//		if (addresult) {
//			model.addAttribute("message", "發表成功");
//			return "showResultForm";
//		} else {
//			model.addAttribute("message", "發表失敗");
//			return "showResultForm";
//		}

	}

	@GetMapping(path = "/postIndex")
	public ResponseEntity<List<Post>> showAllPost(Model model) {
		List<Post> findallPost = postService.findallPost();
		
		if(findallPost != null){
            return ResponseEntity.status(HttpStatus.OK).body(findallPost);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

	}
	
	//測試食記管理者後台
	@GetMapping(path = "/postStatusList")
	public ResponseEntity<List<Post>> showPost2(Model model) {
		List<Post> findallPost = postService.findPostByStatus();

		if(findallPost != null){
            return ResponseEntity.status(HttpStatus.OK).body(findallPost);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
		
	}
	
	@GetMapping(path="/auditPost" )
	public String auditPost(@RequestParam("id") int id,Model model) {
		Post post = postService.findPost(id);
		if (post != null) {
			model.addAttribute("post", post);
			return "auditPost";
		} else {
			model.addAttribute("message", "失敗");
			return "showResultForm";
		}
	}	
	
	@GetMapping(path="/auditPost2" )
	public String sendauditPost(@RequestParam("id") int id,@RequestParam("cd") String condition,Post post,Model model) {
		
		post.setPostStatus(condition);
		post.setPostId(id);
		boolean updateCondition = postService.updateCondition(post);
		if (updateCondition) {
			model.addAttribute("message", "更新成功");
			return "redirect:/posts/postStatusList";
		} else {
			model.addAttribute("message", "更新失敗");
			return "showResultForm";
		}
	}

	@GetMapping(value = "/showPost/{id}")
	public ResponseEntity<Post> showPost(@PathVariable("id") int id, Model model) {
		
		Post post = postService.findPost(id);
		 if(post != null){
	            return ResponseEntity.status(HttpStatus.OK).body(post);
	        }else{
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }

	}

	@GetMapping(value = "/deletePost/{id}")
	public ResponseEntity<Post> deletePost(@PathVariable("id") int id, Model model) {

		boolean post = postService.deletePost(id);
		
		if(post) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return null;
		
//		if (postService.deletePost(id)) {
//			model.addAttribute("message", "刪除成功");
//			return "showResultForm";
//		} else {
//			model.addAttribute("message", "刪除失敗");
//			return "showResultForm";
//		}

	}

	@GetMapping(value = "/editPost/{id}")
	public String editPost(@PathVariable("id") int id, Model model) {

		Post post = postService.findPost(id);
		if (post != null) {
			model.addAttribute("posts", post);
			return "editPost";
		} else {
			model.addAttribute("message", "錯誤");
			return "showResultForm";
		}

	}

	@PostMapping(path = "/editPost/{id}", produces = "text/html ; charset=UTF-8")
	public String UpdatePostImage(@ModelAttribute("posts") Post post, BindingResult result,ModelMap model,
			HttpServletRequest request, @PathVariable("id") int id) throws IOException {

		String headUrl = null;
		String headImgFileName = "pimages/PostsPhoto";
		String defaultImgurl = "images/PostsPhoto/defaultPostImage.jpg";

		if (result.hasErrors()) {
			return "editPost";
		}

		MultipartFile picture = post.getPostImage();
		if (picture.getSize() != 0) {
			// byte[] bytes = picture.getBytes();
			String filename = picture.getOriginalFilename();
			String suffix = filename.substring(filename.lastIndexOf('.'));// 副檔名
			System.out.println("副檔名" + suffix);// .jpg
			// 新的檔名
			String newFileName = new Date().getTime() + suffix;
			System.out.println("新檔名" + newFileName);// 1478509873038.jpg

			String savePath = request.getSession().getServletContext().getRealPath("/") + headImgFileName;
			// String savePath = "c:/temp/upload";
			System.out.println(savePath);
			File headImage = new File(savePath, newFileName);

			// 儲存置資料庫路徑
			headUrl = headImgFileName + "/" + newFileName;
			System.out.println(headUrl);
			picture.transferTo(headImage);

		} else {
			headUrl = defaultImgurl;
		}

		post.setImgurl(headUrl);
		post.setPostId(id);
		boolean updatePost = postService.updatePost(post);

		if (updatePost) {
			model.addAttribute("message", "更新成功");
			return "showResultForm";
		} else {
			model.addAttribute("message", "更新失敗");
			return "showResultForm";
		}
	}

}