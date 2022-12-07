package QAPSpringBoot.QapFinal;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class TournamentController {
    @Autowired
    private TournamentRepository repo;

    @GetMapping("/tournament")
    public List<Tournament> getAllTournament() {
        return (List<Tournament>) repo.findAll();
    }

    @PostMapping("/tournament")
    public void createTournament(@RequestBody Tournament tournament) {
        repo.save(tournament);
    }

    @PutMapping("/tournament/{id}")
    public void updateTournament(@PathVariable String id, @RequestBody Tournament tournament, HttpServletResponse response) {
        Optional<Tournament> returnValue = Optional.ofNullable(repo.findById(Long.parseLong(id)));
        Tournament tournamentToUpdate;

        if (returnValue.isPresent()) {
            tournamentToUpdate = returnValue.get();

            tournamentToUpdate.setStartDate(tournament.getStartDate());

            repo.save(tournamentToUpdate);
        } else {
            try {
                response.sendError(404, "Tournament with id: " + tournament.getId() + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

