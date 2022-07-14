package com.eeit45.champion.vegetarian.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eeit45.champion.vegetarian.constant.ForumCategory;
import com.eeit45.champion.vegetarian.model.Forum;
import com.eeit45.champion.vegetarian.service.ForumService;
import com.eeit45.champion.vegetarian.util.Page;

@Validated
@RestController
public class ForumController {
	
	@Autowired
	private ForumService forumService;
	
	@GetMapping("/forums")
	public ResponseEntity<Page<Forum>> getForums(
			@RequestParam(required = false) Integer forumId,
			@RequestParam(required = false) String  forumTitle,
			@RequestParam(required = false) String  forumContent,
			@RequestParam(required = false) ForumCategory forumCategory,
			@RequestParam(required = false) Date    forumCreateTime,
			@RequestParam(required = false) Date    forumUpdateTime	
			){
		Forum forum = new Forum();
		forum.setForumId(forumId);
		forum.setForumTitle(forumTitle);
		forum.setForumContent(forumContent);
		forum.setForumCategory(forumCategory);
		forum.setForumCreateTime(forumCreateTime);
		forum.setForumUpdateTime(forumUpdateTime);
		
		List<Forum> forums = forumService.getForums(forum);
		
		Integer total = forumService.totalForum(forum);
		Page<Forum> page = new Page<>();
		page.setTotal(total);
		page.setResults(forums);
		return ResponseEntity.status(HttpStatus.OK).body(page);
		
	}
	
	@GetMapping("/forums/{forumId}")
	public ResponseEntity<Forum> getForum(@PathVariable Integer forumId){
		Forum forum = forumService.getForumById(forumId);
		
		if(forum!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(forum);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}	
	}

	@PostMapping("/forums")
	public ResponseEntity<Forum> createForum(@RequestBody @Valid Forum forum){
		Integer forumId = forumService.createForum(forum);
		
		forum = forumService.getForumById(forumId);
		return ResponseEntity.status(HttpStatus.CREATED).body(forum);		
	}
	
	@PutMapping("/forums/{forumId}")
	public ResponseEntity<Forum> updateForum(@PathVariable Integer forumId,
											 @RequestBody @Valid Forum forum){
		Forum checkForum = forumService.getForumById(forumId);
		if(checkForum == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		forumService.updateForum(forumId, forum);
		
		forum = forumService.getForumById(forumId);
		
		return ResponseEntity.status(HttpStatus.OK).body(forum);
	}
	
	
	@DeleteMapping("/forums/{forumId}")
	public ResponseEntity<Forum> deleteForum(@PathVariable Integer forumId){
		forumService.deleteForumById(forumId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}
	
	
	
}
