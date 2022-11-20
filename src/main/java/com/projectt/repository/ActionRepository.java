package com.projectt.repository;

import com.projectt.domain.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionRepository extends JpaRepository<Article, Long>  {


}
