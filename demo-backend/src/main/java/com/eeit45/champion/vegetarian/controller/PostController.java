package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.model.Post;
import com.eeit45.champion.vegetarian.model.PostFavorite;
import com.eeit45.champion.vegetarian.model.User;
import com.eeit45.champion.vegetarian.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Validated
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    //食記發表頁面
    @GetMapping("/newFoodPost")
    public String processMainAction(Model m) {

        Post post = new Post();
        m.addAttribute("posts", post);
        return "createPost";
    }

    //發表食記
    @PostMapping("/PostNew")
    public ResponseEntity<Boolean> createPostImage(@RequestParam(value = "title") String title,
                                                   @RequestParam(value = "postedText") String postedText,
                                                   @RequestPart(value = "postImage") MultipartFile postImage, Post post, HttpServletRequest request)
            throws IOException {

        String imageUrl = null;
        String fileFolderName = "testimages/PostsPhoto";
        String defaultImgurl = "images/PostsPhoto/defaultPostImage.jpg";

        if (postImage.getSize() != 0) {
            // byte[] bytes = picture.getBytes();
            String filename = postImage.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf('.'));// 副檔名
            String newFileName = new Date().getTime() + suffix;// 新的檔名

            String saveDir = request.getSession().getServletContext().getRealPath("/") + fileFolderName;
            File saveFileDir = new File(saveDir);

            if (!saveFileDir.exists()) {
                saveFileDir.mkdirs();
            }
            System.out.println(saveDir);

            imageUrl = fileFolderName + "/" + newFileName;  // 儲存資料庫路徑
            System.out.println(imageUrl);
            File headImage = new File(saveDir, newFileName);
            postImage.transferTo(headImage);

        } else {
            imageUrl = defaultImgurl;
        }

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());

        post.setTitle(title);
        post.setPostedText(postedText);
        post.setImgurl(imageUrl);
        post.setPostedDate(date);
        post.setPostStatus("待審核");

        Boolean addresult = postService.addPostImage(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(addresult);

    }

    //後台食記文章總覽
    @GetMapping(path = "/postIndex")
    public ResponseEntity<List<Post>> showAllPost() {
        List<Post> findallPost = postService.findallPost();

        if (findallPost != null) {
            return ResponseEntity.status(HttpStatus.OK).body(findallPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    //前台食記文章總覽
    @GetMapping(path = "/postStatusList")
    public ResponseEntity<List<Post>> showPost2(Model model) {
        List<Post> findallPost = postService.findPostByStatus();

        if (findallPost != null) {
            return ResponseEntity.status(HttpStatus.OK).body(findallPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    //後台審核食記
    @GetMapping(path = "/auditPost/{id}")
    public ResponseEntity<Post> auditPost(@PathVariable("id") int id, Model model) {

        Post post = postService.findPost(id);
        if (post != null) {
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping(path = "/auditPost/{id}")
    public ResponseEntity<Post> sendauditPost(@PathVariable("id") int id,
                                              @RequestParam("cd") String condition, Post post) {

        post.setPostStatus(condition);
        post.setPostId(id);
        Post updateCondition = postService.updateCondition(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(updateCondition);

    }

    //查詢單筆文章
    @GetMapping(value = "/showPost/{id}")
    public ResponseEntity<Post> showPost(@PathVariable("id") int id, HttpServletRequest request) {

        Post post = postService.findPost(id);
        if (post != null) {
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

    //刪除文章
    @GetMapping(value = "/deletePost/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable("id") int id) {

        boolean post = postService.deletePost(id);
        if (post) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
    }

    @GetMapping(value = "/editPost/{id}")
    public ResponseEntity<Post> editPost(@PathVariable("id") int id) {

        Post post = postService.findPost(id);
        if (post != null) {
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping(path = "/editPost/{id}")
    public ResponseEntity<Boolean> UpdatePostImage(@RequestParam(value = "title") String title,
                                                   @RequestParam(value = "postedText") String postedText,
                                                   @RequestPart(value = "postImage") MultipartFile postImage,
                                                   @PathVariable("id") int id,
                                                   Post post, HttpServletRequest request
    ) throws IOException {
        String imageUrl = post.getImgurl();
        String fileFolderName = "testimages/PostsPhoto";
        String defaultImgurl = "images/PostsPhoto/defaultPostImage.jpg";

        if (postImage.getSize() != 0) {
            // byte[] bytes = picture.getBytes();
            String filename = postImage.getOriginalFilename();
            String suffix = filename.substring(filename.lastIndexOf('.'));// 副檔名
            String newFileName = new Date().getTime() + suffix;// 新的檔名

            String saveDir = request.getSession().getServletContext().getRealPath("/") + fileFolderName;
            File saveFileDir = new File(saveDir);

            if (!saveFileDir.exists()) {
                saveFileDir.mkdirs();
            }
            System.out.println(saveDir);

            imageUrl = fileFolderName + "/" + newFileName;  // 儲存資料庫路徑
            System.out.println(imageUrl);
            File headImage = new File(saveDir, newFileName);
            postImage.transferTo(headImage);

        } else {
            imageUrl = defaultImgurl;
        }

        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());

        post.setPostId(id);
        post.setTitle(title);
        post.setPostedText(postedText);
        post.setImgurl(imageUrl);
        post.setPostedDate(date);
        post.setPostStatus("待審核");

        Boolean addresult = postService.updatePost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(addresult);
    }

    //搜尋收藏文章
    @GetMapping(value = "/favtest/{id}")
    public ResponseEntity<PostFavorite> showfav(@PathVariable("id") Integer id, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        //String userId2 = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

//		System.out.println(userId2);

        Integer userId;//用户id
        if (user == null) {
            return null;
        } else {
            userId = user.getUserId();
        }
        //Integer userId = 1564;
        PostFavorite post = postService.findByFavorite(id, userId);
        //boolean flag = postService.isFavorite(id, userId);

        if (post != null) {
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @PostMapping("/favtest/{id}")
    public ResponseEntity<Boolean> addfav(@PathVariable("id") int id, Post post, HttpServletRequest request)
            throws IOException {


        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        Date date = Date.from(zdt.toInstant());


        User user = null;
        user.getUserId();
        Integer userId;//用户id
        if (user == null) {
            return null;
        } else {
            userId = user.getUserId();
        }
        //Integer userId = user.getUserId();
        //Integer userId = 1564;

        postService.addFavPost(id, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}