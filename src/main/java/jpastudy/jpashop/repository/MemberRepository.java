package jpastudy.jpashop.repository;

import jpastudy.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    //@PersistenceContext
    //or @AutoWir... 대체가능
    private final EntityManager em;

    //등록
    public void save(Member member) {
        em.persist(member);

    }
    // id로 멤버 조회
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    //Member 모두 조회
    public List<Member> findAll() {
        //typedQuery
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
    //name으로 Member 하나 또는 여러개 조회
    public  List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name =:name",Member.class).setParameter(
                "name",name).getResultList();}


}
