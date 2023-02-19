package my.school;

import my.school.exam.ExamConverter;
import my.school.exam.ExamRepository;
import my.school.homework.HomeworkConverter;
import my.school.homework.HomeworkRepository;
import my.school.reply.Reply;
import my.school.reply.ReplyConverter;
import my.school.reply.ReplyRepository;
import my.school.task.TaskConverter;
import my.school.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.persistence.EntityManagerFactory;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "my.school")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "my.school")
public class AppConfig implements WebMvcConfigurer {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("mySchool");
        return emfb;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(taskConverter());
        registry.addConverter(examConverter());
        registry.addConverter(homeworkConverter());
        registry.addConverter(replyConverter());
    }
    @Bean
    public TaskConverter taskConverter(){
        return new TaskConverter(taskRepository);
    }
    @Bean
    public ExamConverter examConverter(){
        return new ExamConverter(examRepository);
    }
    @Bean
    public HomeworkConverter homeworkConverter() {
        return new HomeworkConverter(homeworkRepository);
    }
    @Bean
    public ReplyConverter replyConverter(){
        return new ReplyConverter(replyRepository);
    }

}
