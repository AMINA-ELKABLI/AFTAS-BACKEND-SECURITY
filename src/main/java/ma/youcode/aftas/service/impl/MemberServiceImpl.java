package ma.youcode.aftas.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.aftas.model.Member;
import ma.youcode.aftas.exception.ConcurrentUpdateException;
import ma.youcode.aftas.exception.ResourceAlreadyExistsException;
import ma.youcode.aftas.exception.ResourceNotFoundException;
import ma.youcode.aftas.repository.MembreRepository;
import ma.youcode.aftas.service.IMemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService {
    private final MembreRepository memberRepository;
    @Override
    public Member save(Member member) {
        if(memberRepository.findByNum(member.getNum()).isPresent()){
            throw new ResourceAlreadyExistsException("Member Number already exists");
        }
        return memberRepository.save(member);
    }

    @Override
    public Member update(Member member, Integer num) {
        Member existingMember = memberRepository.findByNum(num)
                .orElseThrow(() -> new ResourceNotFoundException("Membre introuvable avec le numéro " + num));
         if(!existingMember.getNum().equals(member.getNum())){
             throw new ConcurrentUpdateException("A concurrent update was detected. Please try again.");
         }
         return memberRepository.save(member);
    }

    @Override
    public List<Member> findAll(Pageable pageable)
    {
        return memberRepository.findAll(pageable).getContent();
    }

    @Override
    public boolean delete(Integer num) {
        Optional<Member> memberOptional = memberRepository.findByNum(num);

        if (memberOptional.isPresent()) {
            memberRepository.delete(memberOptional.get());
            return true;
        } else {
            throw new NoSuchElementException("No member found with number:" + num);
        }

    }


    @Override
    public Optional<Member> findByNum(Integer num) {
        return memberRepository.findByNum(num);
    }

    @Override
    public Long countMembers() {
        return memberRepository.count();
    }

    private void MemberExiste(Member member) {
        Optional<Member> existingMember = memberRepository.findByNum(member.getNum());

        if (existingMember.isPresent()) {
            throw new IllegalArgumentException("Member with the same number already exists");
        }
    }
}
