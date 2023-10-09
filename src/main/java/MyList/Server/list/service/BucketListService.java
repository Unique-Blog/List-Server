package MyList.Server.list.service;

import MyList.Server.exception.CustomException;
import MyList.Server.list.dto.request.BucketListRequestDTO;
import MyList.Server.list.dto.response.BucketListResponseDTO;
import MyList.Server.list.entity.BucketList;
import MyList.Server.list.entity.CompletedBucketList;
import MyList.Server.list.repository.BucketListRepository;
import MyList.Server.list.repository.CompletedBucketListRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BucketListService {


    private final BucketListRepository bucketListRepository;
    private final CompletedBucketListRepository completedBucketListRepository;

    @Transactional
    public BucketList add(BucketListRequestDTO bucketListRequestDTO){
        BucketList bucketList = BucketList.builder()
                .content(bucketListRequestDTO.getContent())
                .completed(bucketListRequestDTO.getCompleted())
                .createdAt(LocalDateTime.now())
                .userId(bucketListRequestDTO.getUserId())
                .build();
        return this.bucketListRepository.save(bucketList);
    }

    @Transactional
    public BucketList save_completedBucketList(Long id) {
        BucketList bucketList = bucketListRepository.findBucketListById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "id값에 맞는 BucketList 존재하지 않습니다."));

        if (delete_completedTodoList(bucketList.getCreatedAt(),bucketList)) {// scrap을 한번 더 누르면 DB에 존재하는지 확인한 뒤, 삭제하고 return false

            CompletedBucketList completedBucketList = CompletedBucketList.builder() // scrapSummaryCodeRepository에 저장
                    .content(bucketList.getContent())
                    .createdAt(bucketList.getCreatedAt())
                    .userId(bucketList.getUserId())
                    .build();
            completedBucketListRepository.save(completedBucketList);

            bucketList.setCompleted(true); // summaryCode 객체에서 스크랩 여부를 YES로 변경
            bucketListRepository.save(bucketList);
            return bucketList;
        }
        return bucketList;
    }

    private boolean delete_completedTodoList(LocalDateTime localDateTime, BucketList bucketList) {
        // localDateTime은 밀리초까지 나옴. 그래서 동일한 값이 없다고 판단 하에 검증 필드로 사용
        Optional<CompletedBucketList> completedBucketList = completedBucketListRepository.findCompletedBucketListByCreatedAt(localDateTime);
        if (completedBucketList.isPresent()) {
            completedBucketListRepository.delete(completedBucketList.get());

            bucketList.setCompleted(false); // summaryCode 객체에서 스크랩 여부를 NO로 변경
            bucketListRepository.save(bucketList);
            return false;
        } else {
            return true;
        }
    }

    public  BucketList searchBucketList(Long id){
        return this.bucketListRepository.findBucketListById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public List<BucketList> searchAll(String memberId){
        return this.bucketListRepository.findAllByUserId(memberId);
    }

    public List<CompletedBucketList> searchCompleted(String memberId){
        return this.completedBucketListRepository.findAllByUserId(memberId);
    }


    public BucketList updateBucketList(BucketListResponseDTO bucketListResponseDTO){
        BucketList bucketList = this.searchBucketList(bucketListResponseDTO.getId());
        if(bucketListResponseDTO.getContent() != null){
            bucketList.setContent(bucketListResponseDTO.getContent());
        }
        if(bucketListResponseDTO.getCompleted() != null){
            bucketList.setCompleted(bucketListResponseDTO.getCompleted());
        }
        return this.bucketListRepository.save(bucketList);
    }

    @Transactional
    public void deleteBucketList(Long id){
        BucketList bucketList = bucketListRepository.findBucketListById(id).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "id값에 맞는 BucketList 존재하지 않습니다."));
        this.bucketListRepository.deleteBucketListByCreatedAt(bucketList.getCreatedAt());
        this.completedBucketListRepository.deleteCompletedBucketListByCreatedAt(bucketList.getCreatedAt());
    }

    public void deleteAll(){
        this.bucketListRepository.deleteAll();
        this.completedBucketListRepository.deleteAll();
    }


    public double completedPercentage(List<BucketList> allList, List<CompletedBucketList> completedList) {
        int totalSize = allList.size();
        int completedSize = completedList.size();

        double completionPercentage = (double) completedSize / totalSize * 100.0;

        System.out.println("completionPercentage = " + completionPercentage);

        return completionPercentage;
    }
}
