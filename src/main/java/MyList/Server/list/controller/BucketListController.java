package MyList.Server.list.controller;

import MyList.Server.list.dto.request.BucketListRequestDTO;
import MyList.Server.list.dto.response.BucketListResponseDTO;
import MyList.Server.list.entity.BucketList;
import MyList.Server.list.service.BucketListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<BucketList>> completedTodoList(@RequestParam Long id) {
        BucketList bucketList = bucketListService.save_completedBucketList(id);
        List<BucketList> allListTodo = bucketListService.searchAll(bucketList.getUserId());
        return ResponseEntity.ok()
                .body(allListTodo);
    }
}
