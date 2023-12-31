package MyList.Server.list.controller;

import MyList.Server.exception.CustomException;
import MyList.Server.list.dto.request.BucketListRequestDTO;
import MyList.Server.list.dto.response.BucketListResponseDTO;
import MyList.Server.list.entity.*;
import MyList.Server.list.service.BucketListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BucketListController {
    private final BucketListService bucketListService;

    @Async
    @RequestMapping(value = "/bucket/search",method = RequestMethod.POST)
    public ResponseEntity<CompletedPercentage> searchBucket(@RequestBody BucketListRequestDTO bucketListRequestDTO){
        System.out.println("searchBucket = " + bucketListRequestDTO);

        List<BucketList> allListBucket = bucketListService.searchAll(bucketListRequestDTO.getUserId());
        List<CompletedBucketList> completedTodo = bucketListService.searchCompleted(bucketListRequestDTO.getUserId());

        double percentage = bucketListService.completedPercentage(allListBucket, completedTodo);

        CompletedPercentage completedPercentage = new CompletedPercentage(allListBucket, percentage);

        return ResponseEntity.ok(completedPercentage);
    }

    @RequestMapping(value = "/bucket/save", method = RequestMethod.POST)
    public ResponseEntity<List<BucketList>> saveBucket(@RequestBody BucketListRequestDTO bucketListRequestDTO) {
        if(bucketListRequestDTO.getContent() == null || bucketListRequestDTO.getContent().trim().isEmpty()){
            throw new CustomException(HttpStatus.BAD_REQUEST, "글이 없습니다.");
        }
        System.out.println("saveBucket = " + bucketListRequestDTO);
        bucketListService.add(bucketListRequestDTO);
        List<BucketList> allListBucket = bucketListService.searchAll(bucketListRequestDTO.getUserId());
        return ResponseEntity.ok(allListBucket);
    }

    @RequestMapping(value = "/bucket/update",method = RequestMethod.POST)
    public ResponseEntity<List<BucketList>> updateBucket(@RequestBody BucketListResponseDTO bucketListResponseDTO) {
        bucketListService.updateBucketList(bucketListResponseDTO);
        List<BucketList> allListBucket = bucketListService.searchAll(bucketListResponseDTO.getUserId());
        return ResponseEntity.ok(allListBucket);
    }

    @RequestMapping(value = "/bucket/delete",method = RequestMethod.DELETE)
    public ResponseEntity<List<BucketList>> deleteBucket(@RequestBody BucketListResponseDTO bucketListResponseDTO) {
        bucketListService.deleteBucketList(bucketListResponseDTO.getId());
        List<BucketList> allListBucket = bucketListService.searchAll(bucketListResponseDTO.getUserId());
        return ResponseEntity.ok(allListBucket);
    }

    @RequestMapping(value = "/bucket/deleteAll",method = RequestMethod.DELETE)
    public ResponseEntity<List<BucketList>> deleteAllBucket(@RequestBody BucketListResponseDTO bucketListResponseDTO) {
        bucketListService.deleteAll();
        List<BucketList> allListBucket = bucketListService.searchAll(bucketListResponseDTO.getUserId());
        return ResponseEntity.ok(allListBucket);
    }

    @PostMapping("/bucket/completed")
    public ResponseEntity<List<BucketList>> completedTodoList(@RequestParam Long id) {
        BucketList bucketList = bucketListService.save_completedBucketList(id);
        List<BucketList> allListBucket = bucketListService.searchAll(bucketList.getUserId());
        return ResponseEntity.ok()
                .body(allListBucket);
    }
}
