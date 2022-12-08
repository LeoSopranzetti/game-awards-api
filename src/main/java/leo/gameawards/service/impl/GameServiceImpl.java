package leo.gameawards.service.impl;

import leo.gameawards.domain.model.Game;
import leo.gameawards.domain.model.GameRepository;
import leo.gameawards.service.GameService;
import leo.gameawards.service.exception.BusinessException;
import leo.gameawards.service.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository repository;

    @Override
    public List<Game> findAll() {
        List<Game> games = repository.findAll(Sort.by(Sort.Direction.DESC, "votes"));
        return games;
    }

    @Override
    public Game findById(Long id) {
        Optional<Game> game = repository.findById(id);
        return game.orElseThrow(() -> new NoContentException());
    }

    @Override
    public void insert(Game game) {
        repository.save(game);
    }

    @Override
    public void update(Long id, Game game) {
        Game gameDb = findById(id);
        if (gameDb.getId().equals((game.getId()))){
            repository.save(game);
        } else {
            throw new BusinessException("Os IDs para alteração são divergentes");
        }
    }

    @Override
    public void delete(Long id) {
        Game gameDb = findById(id);
        repository.delete(gameDb);
    }

    @Override
    public void vote(Long id) {
        Game gameDb = findById(id);
        gameDb.setVotes(gameDb.getVotes() + 1);

        update(id, gameDb);
    }
}
