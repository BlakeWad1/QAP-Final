package QAPSpringBoot.QapFinal;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {
    @Autowired
    private MemberRepository repo;

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return (List<Member>) repo.findAll();
    }

    @PostMapping("/member")
    public void createMember(@RequestBody Member member) {
        repo.save(member);
    }

    @PutMapping("/member/{id}")
    public void updateMember(@PathVariable String id, @RequestBody Member member, HttpServletResponse response) {
        Optional<Member> returnValue = repo.findById(Long.parseLong(id));
        Member memberToUpdate;

        if (returnValue.isPresent()) {
            memberToUpdate = returnValue.get();

            memberToUpdate.setName(member.getName());

            repo.save(memberToUpdate);
        } else {
            try {
                response.sendError(404, "Member with id: " + member.getId() + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}