package MyList.Server.list.controller;

import MyList.Server.list.dto.request.BucketListRequestDTO;
import MyList.Server.list.dto.request.TodoListRequestDTO;
import MyList.Server.list.dto.response.BucketListResponseDTO;
import MyList.Server.list.entity.BucketList;
import MyList.Server.list.entity.TodoList;
import MyList.Server.list.service.BucketListService;
import MyList.Server.list.service.TodoListService;
import MyList.Server.login.dto.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BucketListController {
    private final BucketListService bucketListService;

    @RequestMapping(value = "/bucket/search",method = RequestMethod.POST)
    public ResponseEntity<List<BucketList>> searchBucket(@RequestBody BucketListRequestDTO bucketListRequestDTO){
        System.out.println("searchBucket = " + bucketListRequestDTO);
        List<BucketList> allListTodo = bucketListService.searchAll(bucketListRequestDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/bucket/save", method = RequestMethod.POST)
    public ResponseEntity<List<BucketList>> saveBucket(@RequestBody BucketListRequestDTO bucketListRequestDTO) {
        System.out.println("saveBucket = " + bucketListRequestDTO);
        bucketListService.add(bucketListRequestDTO);
        List<BucketList> allListTodo = bucketListService.searchAll(bucketListRequestDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/bucket/update/",method = RequestMethod.POST)
    public ResponseEntity<List<BucketList>> updateBucket(@RequestBody BucketListResponseDTO bucketListResponseDTO) {
        bucketListService.updateById(bucketListResponseDTO);
        List<BucketList> allListTodo = bucketListService.searchAll(bucketListResponseDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/bucket/delete/",method = RequestMethod.DELETE)
    public ResponseEntity<List<BucketList>> deleteBucket(@RequestBody BucketListResponseDTO bucketListResponseDTO) {
        bucketListService.deleteById(bucketListResponseDTO.getId());
        List<BucketList> allListTodo = bucketListService.searchAll(bucketListResponseDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @RequestMapping(value = "/bucket/deleteAll",method = RequestMethod.DELETE)
    public ResponseEntity<List<BucketList>> deleteAllBucket(@RequestBody BucketListResponseDTO bucketListResponseDTO) {
        bucketListService.deleteAll();
        List<BucketList> allListTodo = bucketListService.searchAll(bucketListResponseDTO.getUserId());
        return ResponseEntity.ok(allListTodo);
    }

    @PostMapping("/bucket/completed")
    public ResponseEntity<String> completedTodoList(@RequestParam Long id) {

        bucketListService.save_completedBucketList(id);
        return ResponseEntity.ok()
                .body("좋아요를 누른 글을 성공적으로 저장했습니다.");
    }
}
