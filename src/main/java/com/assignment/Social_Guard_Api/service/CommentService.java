package com.assignment.Social_Guard_Api.service;
import com.assignment.Social_Guard_Api.model.Comment;
import com.assignment.Social_Guard_Api.model.Post;
import com.assignment.Social_Guard_Api.repository.CommentRepository;
import com.assignment.Social_Guard_Api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@Service
public class CommentService {
   @Autowired
   private CommentRepository commentRepository;
   @Autowired
    private PostRepository postRepository;
   @Autowired
   private RedisService redisService;
   public Comment addComment(Long postiD , Comment comment){
       Optional<Post> Oppost = postRepository.findById(postiD);
       if(Oppost.isPresent()){
     Post post = Oppost.get();
     comment.setPost(post);
     if(comment.getDepthLevel()>20){
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Depth Limit Exceeded!");
           }
     if("BOT".equalsIgnoreCase(comment.getAuthorType())){

         if(comment.getBot() == null){
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such type of Bot");
         }
         if(post.getAuthor() == null){
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post has no user author!");
         }
         Long botid = comment.getBot().getId();
         Long userid = post.getAuthor().getId();
         if(redisService.coolDownActive(botid,userid)){
           throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS , "Try again After 10 minutes!");
       }

         Long count = redisService.CheckingBotReply(postiD);
         if(count>100){
             throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS,"Bot Reply Limit Exceeded!");
         }
         redisService.setTimer(botid,userid);
         redisService.incrementScore(postiD,1);
         if(redisService.isNotificationTimer(userid)){
             redisService.storeNotificationTimer(userid , "Bot "+ comment.getBot().getName()+" replied to your post");
         }else{
             System.out.println("Push Notification Sent to User");
             redisService.storeNotificationTimer(userid , "Bot "+ comment.getBot().getName()+" replied to your post");
             redisService.setNotificationTimer(userid);
         }
     }else{
         redisService.incrementScore(postiD,50);
     }

      return  commentRepository.save(comment);
       }else {
           throw new RuntimeException("No such Post");
       }
   }
}
