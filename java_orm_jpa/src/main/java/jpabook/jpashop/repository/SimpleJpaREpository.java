//package jpabook.jpashop.repository;
//
//import java.io.Serializable;
//
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//@Repository
//@Transactional(readyOnly=true)
//public class SimpleJpaREpository <T, ID extends Serializable> implements JpaRepository<T, ID>, JpaSpecificationExecutor<T>{
//    
//    @Transactional
//    public <S extends T> S save(S entity) {
//        // ...
//    }
//
//}
