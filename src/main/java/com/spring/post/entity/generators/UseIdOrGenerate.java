//package com.spring.post.entity.generators;
//
//import com.spring.post.entity.ancestor.BaseEntity;
//import org.hibernate.HibernateException;
//import org.hibernate.engine.spi.SharedSessionContractImplementor;
//import org.hibernate.id.IdentityGenerator;
//
//import java.io.Serializable;
//
//public class UseIdOrGenerate extends IdentityGenerator {
//
//    @Override
//    public Serializable generate(SharedSessionContractImplementor s, Object obj) {
//        if (obj == null){ throw new HibernateException(new NullPointerException());}
//
//        if (((BaseEntity) obj).getId() == null) {
//            return super.generate(s, obj);
//        }
//        return ((BaseEntity) obj).getId();
//
//    }
//}
