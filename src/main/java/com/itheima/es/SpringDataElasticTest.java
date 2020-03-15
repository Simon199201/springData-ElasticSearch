package com.itheima.es;

import com.itheima.es.entity.Article;
import com.itheima.es.repositories.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringDataElasticTest {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void createIndex() throws Exception{
        //创建索引，并配置映射关系
        elasticsearchTemplate.createIndex(Article.class);
    }
    @Test
    public void addDocument() throws Exception{
        //创建索引，并配置映射关系
        for (int i = 2; i < 10; i++) {
            Article article = new Article();
            article.setId(i);
            article.setTitle(i+"快讯！外媒：印尼总统将接受新冠病毒检测");
            article.setContent("西班牙新增新冠肺炎确诊病例2000例\n");
            articleRepository.save(article);
        }
        for (int i = 10; i < 20; i++) {
            Article article = new Article();
            article.setId(i);
            article.setTitle(i+"境外入境安徽人员一律集中隔离14天\n");
            article.setContent("据介绍，新冠肺炎疫情发生以来，安徽省组织各方力量开展疫情防控工作\n");
            articleRepository.save(article);
        }
    }

    @Test
    public void deleteDocument() throws Exception{
        //根据id删除
        articleRepository.deleteById((long) 2);
        //全部删除
        articleRepository.deleteAll();
    }

    @Test
    public void findAll(){
        Iterable<Article> repositoryAll = articleRepository.findAll();
        repositoryAll.forEach(System.out::println);
    }

    @Test
    public void findOne(){
        Optional<Article> option = articleRepository.findById(12l);
        Article article = option.get();
        System.out.println(article);
    }
}
