package com.est.helllow.controller;

import com.est.helllow.domain.User;
import com.est.helllow.domain.dto.PostRequestDto;
import com.est.helllow.domain.dto.PostRes;
import com.est.helllow.domain.dto.PostResponseDto;

import com.est.helllow.domain.dto.PostTextResponseDto;
import com.est.helllow.exception.BaseException;
import com.est.helllow.service.PostService;
import com.est.helllow.service.S3Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ViewController {
	private final PostService postService;
	private final S3Service s3Service;


	// @GetMapping("/post-management/posts-search")
	// public String searchPost(@RequestParam(value = "searchText") String searchText, Model model) {
	// 	PostSearchCondition postSearchCondition = new PostSearchCondition(searchText);
	// 	List<PostResponseDto> posts = postService.searchPost(postSearchCondition);
	// 	model.addAttribute("postList", posts);
	// 	return "mainpage"; // "post-list"는 검색 결과를 보여줄 Thymeleaf 템플릿 파일 이름입니다.
	// }

	@GetMapping("/postList")
	public String findAllPosts(Model model) {
		List<PostTextResponseDto> postList = postService.findAll().stream()
			.map(PostTextResponseDto::new)
			.collect(Collectors.toList());
		model.addAttribute("postList", postList);
		return "mainpage"; // "posts"는 게시글 목록을 보여줄 Thymeleaf 템플릿 파일 이름입니다.
	}

	@PostMapping("/post")
	public String handleFileUpload(
		@RequestParam("category") String category,
		@RequestParam("title") String postTitle,
		@RequestParam("content") String postContent,
		@RequestParam("file") MultipartFile file,
		Model model) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userId = extractUserId(authentication);

			if (userId == null) {
				throw new IllegalStateException("사용자 인증 정보가 없습니다.");
			}

			if (file == null || file.isEmpty()) {
				throw new IllegalStateException("업로드할 파일이 없습니다.");
			}

			String imageUrl = s3Service.uploadImg(file);
			PostRequestDto requestDto = new PostRequestDto(category, postTitle, postContent);
			postService.savePost(userId, requestDto, imageUrl);
			return "redirect:/postList";
		} catch (Exception e) {
			model.addAttribute("error", "파일 업로드 실패: " + e.getMessage());
			return "redirect:/postList";
		}
	}

	private String extractUserId(Authentication authentication) {
		if (authentication == null) {
			return null;
		}

		if (authentication.getPrincipal() instanceof DefaultOAuth2User) {
			DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
			return oauthUser.getAttribute("email");
		} else if (authentication.getPrincipal() instanceof String) {
			return (String) authentication.getPrincipal();
		}

		return null;
	}



	@GetMapping("/postListGive")
	public String findAllPostGive(Model model) {
		List<PostTextResponseDto> postList = postService.findByCategory("handover").stream()
			.map(PostTextResponseDto::new)
			.collect(Collectors.toList());
		model.addAttribute("postList", postList);
		return "give"; // "posts"는 게시글 목록을 보여줄 Thymeleaf 템플릿 파일 이름입니다.
	}

	@GetMapping("/postListFood")
	public String findAllPostFood(Model model) {
		// 카테고리 '음식'에 해당하는 게시글만 필터링
		List<PostTextResponseDto> postList = postService.findByCategory("menu").stream()
			.map(PostTextResponseDto::new)
			.collect(Collectors.toList());
		model.addAttribute("postList", postList);
		return "food"; // 'food'는 음식 관련 게시글 목록을 보여줄 Thymeleaf 템플릿 파일 이름
	}

	@GetMapping("/postListPicture")
	public String findAllPostPicture(Model model) {
		List<PostTextResponseDto> postList = postService.findByCategory("photo").stream()
			.map(PostTextResponseDto::new)
			.collect(Collectors.toList());
		model.addAttribute("postList", postList);
		return "picture"; // "posts"는 게시글 목록을 보여줄 Thymeleaf 템플릿 파일 이름입니다.
	}

	@GetMapping("/postListAll")
	public String findAllPostAll(Model model) {
		List<PostTextResponseDto> postList = postService.findAll().stream()
			.map(PostTextResponseDto::new)
			.collect(Collectors.toList());
		model.addAttribute("postList", postList);
		return "all"; // "posts"는 게시글 목록을 보여줄 Thymeleaf 템플릿 파일 이름입니다.
	}

	@GetMapping("/list/{id}")
	public String getPosts(@PathVariable("id") String postId, Model model) {
		try {
			PostRes postRes = postService.getPost(postId);
			model.addAttribute("post", postRes);
			return "list"; // "list"는 게시글 상세 정보를 보여줄 Thymeleaf 템플릿 파일 이름
		} catch (BaseException exception) {
			model.addAttribute("error", exception.getExceptionCode());
			return "error"; // "error"는 에러 페이지를 보여줄 Thymeleaf 템플릿 파일 이름
		}
	}








}
