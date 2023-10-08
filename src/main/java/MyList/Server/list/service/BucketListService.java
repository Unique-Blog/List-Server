package MyList.Server.list.service;

import MyList.Server.list.dto.request.BucketListRequestDTO;
import MyList.Server.list.dto.request.TodoListRequestDTO;
import MyList.Server.list.dto.response.BucketListResponseDTO;
import MyList.Server.list.entity.BucketList;
import MyList.Server.list.repository.BucketListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BucketListService {

    @Autowired
    private BucketListRepository bucketListRepository;

    @Transactional
    public BucketList add(BucketListRequestDTO bucketListRequestDTO){
        BucketList bucketList = BucketList.builder()
                .content(bucketListRequestDTO.getContent())
                .completed(bucketListRequestDTO.getCompleted())
                .userId(bucketListRequestDTO.getUserId())
                .build();
        return this.bucketListRepository.save(bucketList);
    }

    public  BucketList searchById(Long id){
        return this.bucketListRepository.findTodoListById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public List<BucketList> searchAll(String memberId){
        return this.bucketListRepository.findAllByUserId(memberId);
    }


    public BucketList updateById(BucketListResponseDTO bucketListResponseDTO){
        BucketList bucketList = this.searchById(bucketListResponseDTO.getId());
        if(bucketListResponseDTO.getContent() != null){
            bucketList.setContent(bucketListResponseDTO.getContent());
        }
        if(bucketListResponseDTO.getCompleted() != null){
            bucketList.setCompleted(bucketListResponseDTO.getCompleted());
        }
        return this.bucketListRepository.save(bucketList);
    }
    public void deleteById(Long id){
        this.bucketListRepository.deleteById(id);
    }

    public void deleteAll(){
        this.bucketListRepository.deleteAll();
    }
}
