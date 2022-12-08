package leo.gameawards.controller.games;

import leo.gameawards.controller.BaseRestController;
import leo.gameawards.domain.model.Game;
import leo.gameawards.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController extends BaseRestController {

    @Autowired
    private GameService businnesLayer;

    @GetMapping("games")
    public ResponseEntity<List<Game>> findAll(){
        List<Game> games =businnesLayer.findAll();
        return ResponseEntity.ok(games);
    }

    @GetMapping("games/{id}")
    public ResponseEntity<Game> findById(@PathVariable Long id){
        return ResponseEntity.ok(businnesLayer.findById(id));
    }

    @PostMapping("games")
    public ResponseEntity<Game> insert(@RequestBody Game game){
        businnesLayer.insert(game);
        return ResponseEntity.ok(game);
    }

    @PutMapping("games/{id}")
    public ResponseEntity<Game> insert(@PathVariable Long id, @RequestBody Game game){
        businnesLayer.update(id, game);
        return ResponseEntity.ok(game);
    }

    @DeleteMapping("games/{id}")
    public ResponseEntity<Game> insert(@PathVariable Long id){
        businnesLayer.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("games/{id}")
    public ResponseEntity<Game> vote(@PathVariable Long id){
        businnesLayer.vote(id);
        return ResponseEntity.ok().build();
    }


}
